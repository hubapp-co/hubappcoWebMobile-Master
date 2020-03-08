package in.co.hubapp.config;

import in.co.hubapp.service.UserService;
import in.co.hubapp.web.LoggingAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
 

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
	 @Autowired
	    private UserService userService;
	 
    @Autowired
    private DataSource dataSource;
    @Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
	

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable();
      
		/*
		 * http.authorizeRequests().and() //
		 * .rememberMe().tokenRepository(this.persistentTokenRepository()) //
		 * .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
		 */    	
    	http
                .authorizeRequests()
                    .antMatchers(
                            "/",
                            "/home",
                            "/about_us",
                            "/contact_us",
                            "/js/**",
                            "/css/**",
                            "/images/**",
                            "/php/**",
                            "/rs-plugin/**",
                            "/video/**",
                            "/fonts/**",
                            "/webjars/**",
                            "/user/**",
                            "/registration**",
                            "/api/**").permitAll()
				/* .antMatchers("/user/**").hasRole("USER") */
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                .logout()
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
		/*
		 * .and() .exceptionHandling() .accessDeniedHandler(accessDeniedHandler)
		 */;
    }

	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { auth.inMemoryAuthentication()
	 * .withUser("user").password("password").roles("USER") .and()
	 * .withUser("manager").password("password").roles("MANAGER"); }
	 */
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
 

}