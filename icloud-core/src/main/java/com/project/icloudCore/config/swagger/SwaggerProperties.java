package com.project.icloudCore.config.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "swagger.api-info")
public class SwaggerProperties {
    /**
     * 用用名称
     */
    private String appName;
    /**
     * swagger标题
     */
    private String title;
    /**
     * 描述
     */
    private String description;
    /**
     * 团队URL
     */
    private String termsOfServiceUrl;
    /**
     * license，如apache license
     */
    private String license;
    /**
     * license地址
     */
    private String licenseUrl;
    /**
     * 版本号
     */
    private String version;
    /**
     * 联系人
     */
    private String contactName;
    /**
     * 联系人主页
     */
    private String contactUrl;
    /**
     * 联系人邮件
     */
    private String contactEmail;
    /**
     * 扫描controller包名
     */
    private String basePackage;
    /**
     * oauth配置
     */
    private OauthClient oauth;

    /**
     * oauth客户端配置
     */
    @Data
    public static class OauthClient {
        /**
         * client id
         */
        private String clientId;
        /**
         * client secret
         */
        private String clientSecret;
        /**
         * token获取地址
         */
        private String accessTokenUri;
        /**
         * 授权地址
         */
        private String userAuthorizationUri;
    }
}
