package web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by ivan on 02.06.2017.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").anonymous()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
              //  .antMatchers("/users/*").permitAll()
                .antMatchers("/logout").authenticated()
                .antMatchers("/").hasAnyRole("ADMIN", "STUDENT")
                .antMatchers("/admin/users/*").hasRole("ADMIN")
                .antMatchers("/admin/users/**").hasRole("ADMIN")
                .antMatchers("/student/**").hasRole("STUDENT")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf()
                .disable();


    }




}
