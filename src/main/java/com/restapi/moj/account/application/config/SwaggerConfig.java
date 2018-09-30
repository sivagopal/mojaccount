package com.restapi.moj.account.application.config;

import com.restapi.moj.account.application.controller.AccountController;
import com.restapi.moj.account.application.data.Account;
import io.swagger.models.Model;
import io.swagger.models.ModelImpl;
import io.swagger.models.properties.Property;
import io.swagger.models.properties.RefProperty;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.mappers.ModelMapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@EnableSwagger2
@PropertySource("classpath:swagger.properties")
@ComponentScan(basePackageClasses = AccountController.class)
@Configuration
public class SwaggerConfig implements BeanDefinitionRegistryPostProcessor {
    private static final String SWAGGER_API_VERSION="1.0";
    private static final String SWAGGER_API_LICENSE="General public licence";
    private static final String title="Accounts REST API";
    private static final String description="Rest API for accounts CRUD operations";

    private ApiInfo accountAPI() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .license(SWAGGER_API_LICENSE)
                .version(SWAGGER_API_VERSION)
                .build();
    }

    @Bean
    public Docket accountsDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(accountAPI())
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/rest.*"))
                .build();
    }

    public static class CustomModelMapperImpl extends ModelMapper {

        @Override
        public Map<String, Model> mapModels(Map<String, springfox.documentation.schema.Model> from) {
            Map<String, Model> mapModels = super.mapModels(from);
            Model model = mapModels.get("Account");
            Map<String, Property> accountProperty = model.getProperties();
            accountProperty.remove("errorMessage");
            accountProperty.remove("id");
            model.setProperties(accountProperty);

            mapModels.put("JsonNode", model);
            return mapModels;
        }

    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionRegistry.getBeanDefinition("modelMapperImpl");
        beanDefinition.setBeanClassName(CustomModelMapperImpl.class.getCanonicalName());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
