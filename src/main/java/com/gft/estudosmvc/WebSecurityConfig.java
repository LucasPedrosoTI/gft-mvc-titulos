package com.gft.estudosmvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    // http.authorizeRequests().antMatchers("/usuarios/cadastrar").permitAll().anyRequest().authenticated().and()
    // .formLogin().loginPage("/usuarios/login").permitAll().failureUrl("/usuarios/login?erro=true").and().logout()
    // .permitAll().logoutSuccessUrl("/usuarios/login?logout");
  }

}
