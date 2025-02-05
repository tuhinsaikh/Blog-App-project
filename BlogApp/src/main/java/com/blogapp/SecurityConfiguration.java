//package com.blogapp;
//
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.security.SecurityRequirement;
//import io.swagger.v3.oas.models.security.SecurityScheme;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//			.withUser("user")
//			.password("user")
//			.roles("USER");
//	}
//
//	@Bean
//	public OpenAPI customOpenAPI() {
//		return new OpenAPI()
//				.addSecurityItem(new SecurityRequirement().addList("basicAuth"))
//				.components(new Components()
//						.addSecuritySchemes("basicAuth",
//								new SecurityScheme()
//										.type(SecurityScheme.Type.HTTP)
//										.scheme("basic")));
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//				.authorizeRequests()
//				.antMatchers("/swagger-ui/**", "/v2/api-docs", "/swagger-resources/**").permitAll() // Allow Swagger
//				.anyRequest().authenticated() // Require authentication for all other APIs
//				.and()
//				.httpBasic() // Enable Basic Authentication
//				.and()
//				.csrf().disable(); // Disable CSRF for APIs
//	}
//
//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
//
//}
