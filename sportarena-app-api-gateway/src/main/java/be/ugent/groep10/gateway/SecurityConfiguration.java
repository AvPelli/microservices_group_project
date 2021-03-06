package be.ugent.groep10.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {
	
	@Bean  
    GrantedAuthorityDefaults grantedAuthorityDefaults() { 
        return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix  
    }  

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    	System.out.println("\n----------------securityWebFilterChain----------------\n");
        // @formatter:off
//        http
//            .authorizeExchange()
//                .anyExchange().authenticated()
//                .and()
//            .oauth2Login()
//                .and()
//            .oauth2ResourceServer()
//                .jwt();
        http
        	.csrf().disable()
            .authorizeExchange()
//            	.anyExchange().permitAll()
            	.pathMatchers("/", "/authorization/**", "/games/**","/tickets/**","/arena/**").permitAll()
            	.pathMatchers("/member/**").hasAuthority(Roles.MEMBER)
            	.pathMatchers("/betting/**","/wallets/**","/gambling/**").hasAuthority(Roles.MEMBER)
            	.pathMatchers("/catering/**").hasAuthority(Roles.CATERINGSERVICE)
				.pathMatchers("/staff/**").hasAnyAuthority(Roles.STAFF)
            	.pathMatchers(HttpMethod.POST,"/authorization/register").permitAll()
            	.anyExchange().authenticated()
            	;
        return http.build();
        // @formatter:on
    }
}