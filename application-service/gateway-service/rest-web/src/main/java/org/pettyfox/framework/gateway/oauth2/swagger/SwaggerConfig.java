package org.pettyfox.framework.gateway.oauth2.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {

    /**
     * 这个东西是项目的根路径，也就是“/oauth/token”前面的那一串
     */
    @Value("${auth_server:}")
    private String authServer;

    /**
     * 这个方法主要是写一些文档的描述
     */
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "平台开发API",
                "提供基础接口",
                "1.0",
                "",
                null,
                "", "", Collections.emptyList());
    }

    /**
     * 主要是这个方法，其他的方法是抽出去的，所以大家不要害怕为啥有这么多方法
     * 在 basePackage 里面写需要生成文档的 controller 路径
     */
    @Bean
    public Docket api() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).defaultValue("bearer xxx").build();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.pettyfox.framework.service.account.interfaces.facade")
                        .or(RequestHandlerSelectors.basePackage("org.pettyfox.framework.gateway.rest"))
                        .or(RequestHandlerSelectors.basePackage("org.pettyfox.framework.service.config.interfaces.facade"))
                        .or(RequestHandlerSelectors.basePackage("org.pettyfox.framework.service.message.interfaces.facade"))
                )
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(securitySchemeToken()))
                .securityContexts(Collections.singletonList(securityContext()))
//                .globalOperationParameters(pars)
                .apiInfo(apiInfo());

    }

    @Bean
    List<GrantType> grantTypes() {
        List<GrantType> grantTypes = new ArrayList<>();
        TokenRequestEndpoint tokenRequestEndpoint = new TokenRequestEndpoint(
                authServer + "/oauth/token",
                "client_2", "123456");
        TokenEndpoint tokenEndpoint = new TokenEndpoint(authServer + "/oauth/token", "access_token");
        grantTypes.add(new AuthorizationCodeGrant(tokenRequestEndpoint, tokenEndpoint));
        return grantTypes;
    }

    private ApiKey securitySchemeToken() {
        return new ApiKey("Bearer", "Authorization", "header");
    }

    /**
     * 这个类决定了你使用哪种认证方式，我这里使用密码模式
     * 其他方式自己摸索一下，完全莫问题啊
     */
    private SecurityScheme securitySchemeOAuth2() {
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(authServer + "/oauth/token");
        AuthorizationScope authorizationScope = new AuthorizationScope("admin", "access_token");
        return new OAuthBuilder()
                .name("Bearer")
                .grantTypes(Collections.singletonList(grantType))
                .scopes(Collections.singletonList(authorizationScope))
                .build();
    }

    /**
     * 这里设置 swagger2 认证的安全上下文
     */
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference("Bearer", scopes())))
                .forPaths(PathSelectors.any())
                .build();
    }

    /**
     * 这里是写允许认证的scope
     */
    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[]{
//                new AuthorizationScope("admin", "All scope is trusted!")
        };
    }


    //默认client认证
//    @Bean
//    public SecurityConfiguration security() {
//        return new SecurityConfiguration
//                ("admin", "123456", "", "", "Bearer access token", ApiKeyVehicle.HEADER, HttpHeaders.AUTHORIZATION, "");
//    }


}