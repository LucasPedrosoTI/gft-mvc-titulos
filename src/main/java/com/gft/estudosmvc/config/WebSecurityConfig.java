package com.gft.estudosmvc.config;

import com.gft.estudosmvc.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Autowired
  UsuarioService usuarioService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests().antMatchers("/cadastrar").permitAll().antMatchers("/usuarios/novo")
        .permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
        .usernameParameter("email").passwordParameter("senha").defaultSuccessUrl("/titulos", true)
        .failureUrl("/login?erro=true").and().logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout=true");
  }

}
