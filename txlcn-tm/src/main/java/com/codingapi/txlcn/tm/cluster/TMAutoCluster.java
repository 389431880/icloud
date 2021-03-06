/*
 * Copyright 2017-2019 CodingApi .
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codingapi.txlcn.tm.cluster;

import com.codingapi.txlcn.common.runner.TxLcnInitializer;
import com.codingapi.txlcn.common.runner.TxLcnRunnerOrders;
import com.codingapi.txlcn.common.util.ApplicationInformation;
import com.codingapi.txlcn.tm.config.TxManagerConfig;
import com.codingapi.txlcn.tm.core.storage.FastStorage;
import com.codingapi.txlcn.txmsg.RpcClient;
import com.codingapi.txlcn.txmsg.dto.AppInfo;
import com.codingapi.txlcn.txmsg.params.NotifyConnectParams;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Description: Date: 1/24/19
 *
 * @author codingapi
 */
@Component
@Slf4j
public class TMAutoCluster implements TxLcnInitializer {

    private final FastStorage fastStorage;

    private final RestTemplate restTemplate;

    private final TxManagerConfig txManagerConfig;

    private static final String MANAGER_REFRESH_URL = "http://%s:%s/manager/refresh";

    private final ServerProperties serverProperties;

    private final RpcClient rpcClient;

    private RedisTemplate<String, String> stringRedisTemplate;

    @Autowired
    public TMAutoCluster(FastStorage fastStorage, RestTemplate restTemplate, TxManagerConfig txManagerConfig,
            ServerProperties serverProperties, RpcClient rpcClient,
            RedisTemplate<String, String> stringRedisTemplate) {
        this.fastStorage = fastStorage;
        this.restTemplate = restTemplate;
        this.txManagerConfig = txManagerConfig;
        this.serverProperties = serverProperties;
        this.rpcClient = rpcClient;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void init() throws Exception {

        // 1. 通知 TC 建立连接
        List<TMProperties> tmList = fastStorage.findTMProperties().stream()
                .filter(tmProperties ->
                        !tmProperties.getHost().equals(txManagerConfig.getHost()) || !tmProperties.getTransactionPort().equals(txManagerConfig.getPort()))
                .collect(Collectors.toList());
        for (TMProperties properties : tmList) {
            NotifyConnectParams notifyConnectParams = new NotifyConnectParams();
            notifyConnectParams.setHost(txManagerConfig.getHost());
            notifyConnectParams.setPort(txManagerConfig.getPort());
            String url = String.format(MANAGER_REFRESH_URL, properties.getHost(), properties.getHttpPort());
            try {
                ResponseEntity<Boolean> res = restTemplate.postForEntity(url, notifyConnectParams, Boolean.class);
                if (res.getStatusCode().equals(HttpStatus.OK) || res.getStatusCode().is5xxServerError()) {
                    log.info("manager auto refresh res->{}", res);
                    break;
                } else {
                    fastStorage.removeTMProperties(properties.getHost(), properties.getTransactionPort());
                }
            } catch (Exception e) {
                log.error("manager auto refresh error: {}", e.getMessage());
                //check exception then remove.
                if (e instanceof ResourceAccessException) {
                    ResourceAccessException resourceAccessException = (ResourceAccessException) e;
                    if (resourceAccessException.getCause() != null && resourceAccessException.getCause() instanceof ConnectException) {
                        //can't access .
                        fastStorage.removeTMProperties(properties.getHost(), properties.getTransactionPort());
                    }
                }
            }
        }

        // 2. 保存TM 到快速存储
        if (StringUtils.hasText(txManagerConfig.getHost())) {
            TMProperties tmProperties = new TMProperties();
            tmProperties.setHttpPort(ApplicationInformation.serverPort(serverProperties));
            tmProperties.setHost(txManagerConfig.getHost());
            tmProperties.setTransactionPort(txManagerConfig.getPort());
            fastStorage.saveTMProperties(tmProperties);
        }

        // 3. 通知TC 重新连接
        List<AppInfo> apps = rpcClient.apps();
        Set<String> appSet = Sets.newHashSet();
        apps.forEach(appInfo -> appSet.add(appInfo.getLabelName()));

        Set<String> redisLabelSet = stringRedisTemplate.opsForSet().members("tc.instances");
        for (String label : redisLabelSet) {
            // 已经连上的不用通知
            if (appSet.contains(label)) {
                continue;
            }

            new Thread(()-> notifyClient(label, 3)).start();
        }
    }

    public boolean notifyClient(String label, int count){
        log.info("Try notify client({}) - count {}", label, count);
        String url = String.format("http://%s/notify/reconnect", label);
        Boolean result = false;
        try{
            result = restTemplate.postForObject(url, null, Boolean.class);
        }catch (Exception e){
            log.error(e.getMessage());
            if (e instanceof ResourceAccessException) {
                ResourceAccessException resourceAccessException = (ResourceAccessException) e;
                if (resourceAccessException.getCause() != null && resourceAccessException.getCause() instanceof ConnectException) {
                    //can't access .
                    stringRedisTemplate.opsForSet().remove("tc.instances", label);
                    return false;
                }
            }
        }
        if(result){
            log.info("Notify client({}) successfully!", label);
            return true;
        }
        if(result == false && --count > 0){
            log.warn("Notify client({}) fail. 3s latter try again.", label);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return notifyClient(label, count);
        }
        log.error("Finally, notify client({}) fail!!!", label);
        log.info("remove the tc-instances from redis: {}", label);
        return false;
    }

    @Override
    public void destroy() throws Exception {
        fastStorage.removeTMProperties(txManagerConfig.getHost(), txManagerConfig.getPort());
    }

    @Override
    public int order() {
        return TxLcnRunnerOrders.MIN;
    }
}
