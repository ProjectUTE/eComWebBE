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
		// Disable crsf cho đường dẫn /rest/**
		http.csrf().ignoringRequestMatchers("/api/user/**");
		http.authorizeHttpRequests().requestMatchers("/api/user/login**").permitAll();
		http.authorizeHttpRequests().requestMatchers("/api/size/**").permitAll();
		http.httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeHttpRequests()
				.requestMatchers(HttpMethod.GET, "/api/user/**").hasAnyRole("USER","ADMIN")
				.requestMatchers(HttpMethod.POST, "/api/user/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/api/user/**").hasRole("ADMIN").and()
				.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
		return http.build();
	}
}
