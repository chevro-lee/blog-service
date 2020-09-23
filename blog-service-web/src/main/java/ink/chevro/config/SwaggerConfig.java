package ink.chevro.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  15:51 2019-08-08
 **/
@Configuration
@EnableSwagger2
@Profile({"dev","prd"})
public class SwaggerConfig {

    @Bean()
    public Docket createApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("blog")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(true)
                .enableUrlTemplating(true)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                // 除了error-handle-controller不监控
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(blogApiInfo());
    }

    private ApiInfo blogApiInfo() {
        return new ApiInfoBuilder()
                .title("Blog Swagger API Doc.")
                .version("1.0.0")
                .termsOfServiceUrl("API TERMS URL")
                .build();
    }
}
