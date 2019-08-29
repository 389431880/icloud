package com.project.icloud.config;

import com.google.common.base.Predicate;
import com.project.icloudCore.config.swagger.SwaggerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

@EnableSwagger2
@Configuration
@ConditionalOnProperty(prefix = "swagger", name = "enabled")
@ConditionalOnClass(Docket.class)
@EnableConfigurationProperties({SwaggerProperties.class})
public class SwaggerConfig {

    @Autowired
    private SwaggerProperties swaggerProperties;

    public static final String SWAGGER_API_NAME = "default";

    @Bean
    public Docket appApi() {
        String basePackage = swaggerProperties.getBasePackage();
        Predicate<RequestHandler> predicate = RequestHandlerSelectors.any();
        if (!StringUtils.isEmpty(basePackage)) {
            predicate = RequestHandlerSelectors.basePackage(basePackage);
        }
        //使用swagger 2.0规范
        return new Docket(DocumentationType.SWAGGER_2)
                //设置api组名
                .groupName(SWAGGER_API_NAME)
                //返回ApiSelectorBuilder对象，控制接口暴露的粒度
                .select()
                //哪些标注、哪些类、哪些包下的接口被暴露，默认所有
                .apis(predicate)
                //那些路径的接口可以被暴露，默认所有
                .paths(PathSelectors.any())
                //返回Docket对象
                .build()
//                .ignoredParameterTypes(UserInfo.class)
                //添加一个servlet路径映射
                .pathMapping("/")
                //日期格式转换器
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //标题
                .title(swaggerProperties.getTitle())
                //说明
                .description(swaggerProperties.getDescription())
                //团队网站
                .termsOfServiceUrl(swaggerProperties.getTermsOfServiceUrl())
                //作者信息
                .contact(new Contact(swaggerProperties.getContactName(), swaggerProperties.getContactUrl(), swaggerProperties.getContactEmail()))
                //许可证
                .license(swaggerProperties.getLicense())
                //许可证地址
                .licenseUrl(swaggerProperties.getLicenseUrl())
                //版本号
                .version(swaggerProperties.getVersion())
                .build();
    }

}
