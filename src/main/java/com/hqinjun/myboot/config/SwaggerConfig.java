package com.hqinjun.myboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
//
//    swagger通过注解表明该接口会生成文档，包括接口名、请求方法、参数、返回信息的等等，如：
//    @ApiIgnore：使用注解忽略该API，不会参与文档生成
//    @ApiOperation：描述该api,如： @ApiOperation(value=”创建用户”, notes=”根据User对象创建用户”)
//    请求方法：@RequestMapping(value = “user”, method = RequestMethod.POST)
//    参数x信息：@ApiImplicitParam(name = “user”, value = “用户详细实体user”, required = true, dataType = “User”)
//    @Api：修饰整个类，描述Controller的作用
//    @ApiParam：单个参数描述
//    @ApiModel：用对象来接收参数
//    @ApiResponses：HTTP响应整体描述
//    @ApiProperty：用对象接收参数时，描述对象的一个字段


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hqinjun.myboot"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot利用swagger构建api文档")
                .contact(new Contact("Hqinjun","http://192.168.159.128:8081","xxxxxx@qq.com"))
                .description("Api 描述")
                .version("1.0")
                .build();
    }

}
