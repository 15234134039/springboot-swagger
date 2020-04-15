package com.it.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2     //开启swagger2
public class SwaggerConfig {

    /**
     * 配置分组 A、B、C
     * @return
     */
    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");
    }

    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("B");
    }

    @Bean
    public Docket docket3(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("C");
    }

    /**
     * 配置了Swagger的Docket bean实例
     *
     * RequestHandlerSelectors 配置扫描接口的方式
     *      basePackage 指定要扫描的包
     *      any 扫描全部
     *      none 不扫描
     *      withClassAnnotation 扫描类上的注解
     *      withMethodAnnotation 扫描方法上的注解
     *
     * paths 过滤的路径
     *
     * enable 是否启动Swagger，为false，则Swagger不能在浏览器中访问，可不设置，默认为true
     *
     * groupName 组名
     * @return
     */
    @Bean
    public Docket docket(Environment environment){

        //设置要显示的Swagger环境
        Profiles profiles = Profiles.of("dev", "test");
        //通过environment.acceptsProfiles判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("张三")
                //.enable(flag)     //改成dev环境后，访问路径http://localhost:8081/swagger-ui.html
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.it.swagger.controller"))
                //.paths(PathSelectors.ant(""))
                .build();
    }

    /**
     * 配置Swagger apiInfo信息
     * @return
     */
    private ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("", "", "");
        return new ApiInfo(
                "我的SwaggerAPI文档",
                "即使再小的帆也能远航",
                "v1.0",
                "",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
}
