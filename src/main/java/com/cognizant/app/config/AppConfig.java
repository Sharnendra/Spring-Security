package com.cognizant.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class AppConfig extends WebSecurityConfigurerAdapter{
	

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
    	
    	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        
    	authenticationMgr.inMemoryAuthentication().withUser("adminuser").password(encoder.encode("123"))
            .authorities("ROLE_USER", "ROLE_ADMIN")
            .and()
            .withUser("user").password(encoder.encode("123"))
            .authorities("ROLE_USER")
	    	.and()
	        .withUser("admin").password(encoder.encode("123"))
	        .authorities("ROLE_ADMIN");
    }
    
/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/processor")
	    	.hasRole("USER")
	    .antMatchers("/welcome")
        	.hasAnyRole("USER", "ADMIN")
        .antMatchers("/getEmployees")
        	.hasAnyRole("USER", "ADMIN")
        .antMatchers("/addNewEmployee")
        	.hasRole("ADMIN")
        	.anyRequest()
        	.authenticated()
    	.and().formLogin().defaultSuccessUrl("/welcome").permitAll()
    	.and().logout().permitAll();
    }*/
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        	.anyRequest()
        	.permitAll();
        
        http.csrf().disable();
    }
}
