package in.co.hubapp.config;

import in.co.hubapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

 

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
	 @Autowired
	 private UserService userService;
	 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable();
      
		http
                .authorizeRequests()
                    .antMatchers(
                            "/",
                            "/home",
                            "/about_us",
                            "/services",
                            "/subscription",
                            "/contact_us",
                            "/js/**",
                            "/css/**",
                            "/images/**",
                            "/php/**",
                            "/rs-plugin/**",
                            "/video/**",
                            "/fonts/**",
                            "/webjars/**",
						/* "/user/**", */
                            "/registration**",
                            "/swagger**",
                            "/api/**",
                            "/v2/**",
                            "/posts/**",
                            "/childCategory/**",
                            "/childSubCategory/**").permitAll()
                    .antMatchers("user/**").hasAnyRole("USER")
                    .antMatchers("user/postweb").hasAnyRole("USER")
                    .antMatchers("user/event").hasAnyRole("USER")
                    .antMatchers("user/profile").hasAnyRole("USER")
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/user")
                    .permitAll()
                .and()
                .logout()
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login?logout")
                    .permitAll();		
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
    
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