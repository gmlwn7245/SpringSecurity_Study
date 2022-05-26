package com.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
	// Spring 5.7 부터 WebSecurityConfigurerAdapter 사용 X..

	// 회원가입시 비밀번호 암호화에 사용할 Encoder 빈 등록
	@Bean
	public BCryptPasswordEncoder encodePassword() {
		return new BCryptPasswordEncoder();
	}

	// configure 부분
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeHttpRequests();

		http.authorizeHttpRequests().antMatchers("/","/auth/**").permitAll() 
				.antMatchers("/admin").hasRole("ADMIN")
				.anyRequest().authenticated(); // 그 외 모든 요청은 인증 필요
		
		return http.build();
	}
	  @Bean
      public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
          return authenticationConfiguration.getAuthenticationManager();
      }
  
	
}
