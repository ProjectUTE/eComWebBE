package project.ute.sbjwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import project.ute.sbjwt.service.JwtAuthenticationTokenFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration   {

	@Autowired
	private  JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

	@Bean
	public RestAuthenticationEntryPoint restServicesEntryPoint() {
		return new RestAuthenticationEntryPoint();
	}

	@Bean
	public CustomAccessDeniedHandler customAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// Disable crsf cho đường dẫn /api/**
		http.csrf().ignoringRequestMatchers("/api/**");
		http.authorizeHttpRequests().requestMatchers("/api/login/**").permitAll();
		http.authorizeHttpRequests().requestMatchers("/api/sign-up-account/**").permitAll();
		http.authorizeHttpRequests().requestMatchers("/api/refresh-token/**").permitAll();
		http.authorizeHttpRequests().requestMatchers("/api/load-image/**").permitAll();
		
		http.httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeHttpRequests()
				.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN","USER")
				.requestMatchers(HttpMethod.POST, "/api/**").hasAnyRole("ADMIN","USER")
				.requestMatchers(HttpMethod.PUT, "/api/**").hasAnyRole("ADMIN","USER")
				.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN").and()
				.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
		return http.build();
	}
}