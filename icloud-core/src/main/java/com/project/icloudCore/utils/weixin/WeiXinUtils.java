package com.project.icloudCore.utils.weixin;

import com.project.icloudCore.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信工具类
 **/
@Component
public class WeiXinUtils {

    /**
     * spring配置文件配置了appID
     */
    @Value("${weixin.jscode2session}") // spring配置文件配置了secret
    private String jscode2sessionUrl;

    /**
    * @description: 获取openId
    * @param code code
    * @param appId appid
    * @param secret secret
    * @return Map<String, String>
    */
    @RequestMapping("/openId")
    @ResponseBody
    public Map<String, String> openId(String code, String appId, String secret) { //小程序端获取的CODE
        Map<String, String> result = new HashMap<>();
        result.put("code", "0");
        try {
            boolean check = (StringUtils.isEmpty(code)) ? true : false;
            if (check) {
//                throw new Exception("参数异常");
                result.put("code", "1");
                result.put("remark", "参数异常");
                return result;
            }
            StringBuilder urlPath = new StringBuilder(jscode2sessionUrl); // 微信提供的API
            urlPath.append(String.format("?appid=%s", appId));
            urlPath.append(String.format("&secret=%s", secret));
            urlPath.append(String.format("&js_code=%s", code));
            urlPath.append(String.format("&grant_type=%s", "authorization_code")); // 固定值
            String data = HttpUtil.sendGet(urlPath.toString());

            if (data.contains("openid")) {
                String openId = new JSONObject(data).getString("openid");
                String sessionKey = new JSONObject(data).getString("session_key");

                result.put("openId", openId);
                result.put("sessionKey", sessionKey);
                result.put("appId", appId);
            } else {
                result.put("code", "1");
                result.put("remark", "授权失败！");
            }
        } catch (Exception e) {
            result.put("code", "1");
            result.put("remark", e.getMessage());
            e.printStackTrace();
        }

        return result;
    }
}
