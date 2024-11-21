package com.taotu.hv.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger API文档配置类
 * 用于生成RESTful API文档，方便前后端联调和接口测试
 *
 * @author taotu
 * @version 1.0
 * @date 2024/03/15
 */
@Configuration
public class SwaggerConfig {

    /**
     * 创建API文档配置
     *
     * @return Docket对象
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)  // 使用OpenAPI 3.0规范
                .apiInfo(apiInfo())  // 设置API文档信息
                .select()  // 开始选择API路径和处理器
                .apis(RequestHandlerSelectors.basePackage("com.taotu.news.controller"))  // 设置要生成文档的控制器包路径
                .paths(PathSelectors.any())  // 设置要生成文档的API路径，any表示所有
                .build();  // 构建Docket对象
    }

    /**
     * 创建API基本信息
     *
     * @return API信息对象
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("陶图新闻 API 文档")  // 文档标题
                .description("陶图新闻系统接口文档，包含所有接口说明")  // 文档描述
                .contact(new Contact(
                        "taotu",  // 联系人
                        "http://www.taotu.com",  // 网站
                        "contact@taotu.com"  // 邮箱
                ))
                .version("1.0")  // API版本号
                .build();
    }
}