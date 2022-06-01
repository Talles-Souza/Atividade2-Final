package com.residencia.academia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
	
	@Bean
	  public OpenAPI springShopOpenAPI() {
	      return new OpenAPI()
	              .info(new Info().title("Academia")
	              .description("Uma aplicação para gerenciar a sua academia!")
	              .version("4.14.1.RELEASE")
	              .license(new License().name("Apache 2.0").url("http://springdoc.org")))
	              .externalDocs(new ExternalDocumentation()
	              .description("SpringAcademia Documentation")
	              .url("https://github.com/Talles-Souza/API-Atividade2-Grupo"));
	  }
//	private ApiInfo apiInfo() {
//		ApiInfo apiInfo = new ApiInfoBuilder()
//		.title ("API de Teste")
//		.description ("Essa é uma api desenvolvida para testes")
//		.license ("Apache License Version 2.0")
//		.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
//		.termsOfServiceUrl("/service.html")
//		.version("1.0.0")
//		.contact(new Contact("Serratec","www.serratec.org.br", "teste@gmail.com"))
//		.build();
//		return apiInfo;
//	}
}
