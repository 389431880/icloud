package com.project.icloudCore.config.sms;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JuHeService {

    /**
     *
     */
    @Value("${sms.connTimeOut}")
    private int connTimeOut;

    /**
     *
     */
    @Value("${sms.readTimeOut}")
    private int readTimeOut;

    /**
     *
     */
    @Value("${sms.userAgent}")
    private String userAgent;

    /**
     *
     */
    @Value("${sms.appKey}")
    private String appKey;


    /**
     * 1.屏蔽词检查测
     */
    public void getRequestForBlack() {
        String result = null;
        String url = "http://v.juhe.cn/sms/black"; //请求接口地址
        Map params = new HashMap(); //请求参数
        params.put("word", ""); //需要检测的短信内容，需要UTF8 URLENCODE
        params.put("key", appKey); //应用APPKEY(应用详细页查询)

        try {
            result = net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if (object.getInt("error_code") == 0) {
                log.info(object.get("result").toString());
            } else {
                log.info(object.get("error_code") + ":" + object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送短信
     * @param mobile mobile
     * @param tplId 短信模板id
     * @param code 短信验证码内容
     * @return JSONObject
     */
    public JSONObject getRequestForSend(String mobile, Integer tplId, String code) {

        String result = null;
        //请求接口地址
        String url = "http://v.juhe.cn/sms/send";

        //请求参数
        Map params = new HashMap();
        //接收短信的手机号码
        params.put("mobile", mobile);
        //短信模板ID，请参考个人中心短信模板设置
        params.put("tpl_id", tplId);

        //应用APPKEY(应用详细页查询)
        params.put("key", appKey);

        try {

            String codeStr = URLEncoder.encode("#code#=" + code, "utf-8");
            //变量名和变量值对。如果你的变量名或者变量值中带有#&=中的任意一个特殊符号，请先分别进行urlencode编码后再传递

            params.put("tpl_value", codeStr);

            result = net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);

            return object;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return  网络请求字符串
     * @throws Exception Exception
     */
    public String net(String strUrl, Map params, String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if (method == null || "GET".equals(method)) {
                strUrl = strUrl + "?" + urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (method == null || "GET".equals(method)) {
                conn.setRequestMethod("GET");
            } else {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(connTimeOut);
            conn.setReadTimeout(readTimeOut);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params != null && "POST".equals(method)) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {

                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    /**
     * 将map型转为请求参数型
     * @param data data
     * @return String
     */
    public String urlencode(Map<String, Object> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
