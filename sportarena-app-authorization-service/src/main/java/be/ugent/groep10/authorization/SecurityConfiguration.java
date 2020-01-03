package be.ugent.groep10.authorization;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Bean  
    GrantedAuthorityDefaults grantedAuthorityDefaults() { 
        return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix  
    }  
	
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .authorizeRequests()  
        .antMatchers("/","/authorization/**","/authorizationn/**").permitAll()
        .antMatchers(HttpMethod.POST,"/authorization/register").permitAll()
        .anyRequest().authenticated()
        .and()
        .oauth2Login();
    }
}