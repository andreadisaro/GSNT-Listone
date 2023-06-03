package it.giochisulnostrotavolo.listone.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import it.giochisulnostrotavolo.listone.security.UserDetailsServiceImpl;
import it.giochisulnostrotavolo.listone.security.jwt.AuthEntryPointJwt;
import it.giochisulnostrotavolo.listone.security.jwt.AuthTokenFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableCaching
@ComponentScan("it.giochisulnostrotavolo.listone.configuration")
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2Y, 10);
	}
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .httpBasic()
        .and()
            .authorizeRequests()
                .antMatchers("/public/**").permitAll() // allow every URI, that begins with '/public/'
                .antMatchers("/auth/**").authenticated()
                .anyRequest().permitAll()// allow all other requests
        .and()
            .csrf().disable() // disable cross site request forgery, as we don't use cookies - otherwise ALL PUT, POST, DELETE will get HTTP 403!
        	.cors()
        .and()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler);

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
