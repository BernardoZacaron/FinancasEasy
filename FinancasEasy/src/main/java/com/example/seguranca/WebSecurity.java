/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Aluno
 */
@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{

    @Override
    public void configure(org.springframework.security.config.annotation.web.builders.WebSecurity web) throws Exception {
         web.ignoring().antMatchers("/h2/**");
    }

    
    
    //Autorização
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/financas/**").hasRole("USER")
                .antMatchers("/registrarUsuario", "/saveUsuario").permitAll()
                .antMatchers("/estilo/**","/js/**","/img/**","/h2","/h2/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").permitAll()
                .and().logout().permitAll();
    }

    @Bean
    public PasswordEncoder password(){
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    UserDetailsService service;
    
    //Autenticação
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(password());
    }
    
    
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin")
//                .password( password().encode("123") )
//                .authorities("ROLE_USER","ROLE_ADMIN")
//                .and().withUser("zaca")
//                .password( password().encode("123") )
//                .authorities("ROLE_USER")
//                .and().withUser("bob")
//                .password( password().encode("123") )
//                .authorities("ROLE_USER");
//    }

       
}
