
  package in.co.hubapp.config;
  
  import java.nio.channels.Selector;
  
  import org.springframework.context.annotation.Bean; import
  org.springframework.context.annotation.Configuration; import
  org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
  import
  org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
  
  import io.swagger.models.Path; import
  springfox.documentation.builders.RequestHandlerSelectors; import
  springfox.documentation.spi.DocumentationType; import
  springfox.documentation.spring.web.plugins.ApiSelectorBuilder; import
  springfox.documentation.spring.web.plugins.Docket; import
  springfox.documentation.swagger2.annotations.EnableSwagger2;
  
  @Configuration
  
  @EnableSwagger2
  
  public class SwaggerConfig extends WebMvcConfigurationSupport {
  
  @Bean
  
  public Docket productApi() {
  
  return new Docket(DocumentationType.SWAGGER_2)
  
  .select().apis(RequestHandlerSelectors.basePackage(
  "in.co.hubapp.mobile.controller")) .build();
  
  }
  
  @Override protected void addResourceHandlers(ResourceHandlerRegistry
  registry) { registry.addResourceHandler("swagger-ui.html")
  .addResourceLocations("classpath:/META-INF/resources/");
  registry.addResourceHandler("/webjars/**")
  .addResourceLocations("classpath:/META-INF/resources/webjars/"); }
  
  }
 