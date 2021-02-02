package com.itheima.oauth.oauth.config;

import com.itheima.oauth.oauth.handler.MyAuthenticationSuccessHandler;
import com.webcommon.config.WebSecurityCorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import javax.sql.DataSource;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * 认证方式
     */
    @Autowired
    private UserDetailsService userDetailService;
    /**
     * 跨域拦截器
     */
    @Autowired
    private WebSecurityCorsFilter webSecurityCorsFilter;
    /**
     * 登录成功处理器
     */
    @Autowired
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;


    /**
     * 注册认证管理器
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



    /**
     * 配置客户端认证方式
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }

    /**
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
    }

    /**
     * 配置url访问安全策略
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //配置访问策略
        http
                .csrf()
                .disable()
                .authorizeRequests().antMatchers("/oauth/**").permitAll()
//                .and()
//                .httpBasic()
                .and()
                .formLogin()
                .loginProcessingUrl("/login").permitAll()
                .successHandler(authenticationSuccessHandler)
                .and()
                .addFilterBefore(webSecurityCorsFilter, ChannelProcessingFilter.class)
                .authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .permitAll();
    }



}
