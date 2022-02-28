package com.ebook.book.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity
@Configuration 
public class SecurityConfiger extends WebSecurityConfigurerAdapter {

    // private static final String[] AUTH_WHITELIST = {
    //             "/swagger-resources/**",
    //             "/swagger-ui.html",
    //             "/v2/api-docs",
    //             "/webjars/**"
    //    };
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and()
         .csrf().disable()
         .authorizeRequests().antMatchers("/api/ebook/**").authenticated()
            .and()
            .httpBasic();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		        
                auth.inMemoryAuthentication()
				.withUser("divyesh")
				.password(passwordEncoder().encode("divu@2108"))
				.roles("ADMIN");
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
    // @Override
    // public void configure(final WebSecurity web) throws Exception {
    //     web.ignoring().antMatchers("/v2/api-docs",
    //         "/configuration/ui",
    //         "/swagger-resources/**",
    //         "/webjars/**",
    //         "/configuration/security",
    //         "/swagger-ui.html");
    // }
	
	
 
}