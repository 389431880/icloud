package com.project.icloudCore.utils.weixin;

import com.alibaba.fastjson.JSONObject;
import com.project.icloudCore.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * 微信全局票据
 */
@Component
@Slf4j
public class WeiXinJSSDKUtils {

    @Value("${weixin.subscription.app_id}") // 微信公众号appid
    private String WX_APPID;

    @Value("${weixin.subscription.app_secret}") // 微信公众号appsecret
    private String WX_APPSECRET;

    /**
     * 微信全局票据 ---->>>> access_token
     * @return accessToken
     */
    @Cacheable( value = "getBaseAccessToken", sync = true)
    public String getBaseAccessToken() {

        try {
            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+WX_APPID+"&secret="+ WX_APPSECRET;
            String rs = HttpUtil.sendGet(url);

            JSONObject obj_content = JSONObject.parseObject(rs);
            String accessToken = obj_content.getString("access_token");
            Integer time = Integer.parseInt(obj_content.getString("expires_in"));

            log.info("Set base access_token to redis is successful.parameters time:{},realtime", time, time-3600);
            return accessToken;
        } catch (Exception e) {

            log.error("Get base access_token from redis is error.");
        }

        return null;
    }

    /**
     * jsapi_ticket是公众号用于调用微信JS接口的临时票据
     * @return jsapi_ticket
     */
    @Cacheable( value = "getJsapiTicket", sync = true)
    public String getJsapiTicket() {

        try {
            //缓存中没有、或已经失效
            //获取全局的access_token，唯一票据
            String accessToken = getBaseAccessToken();

            String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+ accessToken +"&type=jsapi";

            String rs = HttpUtil.sendGet(url);

            JSONObject obj_content = JSONObject.parseObject(rs);
            String jsapi_ticket = obj_content.getString("ticket");

            return jsapi_ticket;
        } catch (Exception e) {

            log.error("Get js_api_ticket from redis is error:{}",e);
        }

        return null;
    }

}
