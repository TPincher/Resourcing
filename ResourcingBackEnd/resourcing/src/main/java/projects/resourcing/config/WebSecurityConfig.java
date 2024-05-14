package projects.resourcing.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import projects.resourcing.credentials.CredentialsService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	private CredentialsService credentialsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
			.authorizeHttpRequests(registry -> {
			registry.requestMatchers("/credentials/**").permitAll();
			registry.requestMatchers("/jobs/**").permitAll();
			registry.requestMatchers("/temps/**").permitAll();
			registry.requestMatchers("/admin/**").hasRole("ADMIN");
			registry.anyRequest().authenticated();
			})
		.formLogin(formLogin -> formLogin.permitAll())
	        .csrf().disable() // Disable CSRF protection for simplicity
		.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return credentialsService;
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
