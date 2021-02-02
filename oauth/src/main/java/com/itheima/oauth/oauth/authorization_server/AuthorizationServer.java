package com.itheima.oauth.oauth.authorization_server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

/**
 * 配置授权服务器
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer  extends AuthorizationServerConfigurerAdapter{
    /**
     * 数据源
     */
    @Autowired
    private DataSource dataSource;
    /**
     * token生成方式及存储规则
     */
    @Autowired
    private TokenStore tokenStore;
    /**
     * 授权认证器
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    /**
     * 认证方式
     */
    @Autowired
    private UserDetailsService userDetailsService;
    /**
     *  令牌生成策略服务
     */
    @Autowired
    private AuthorizationServerTokenServices tokenServices;
    /**
     * 密码加密器
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 设置token令牌服务
     * @return
     */
    @Bean
    public AuthorizationServerTokenServices tokenServices(){
        DefaultTokenServices services = new DefaultTokenServices();
        services.setSupportRefreshToken(true);   //是否支持刷新
        services.setTokenStore(tokenStore);      //token 存储的位置
        services.setAccessTokenValiditySeconds(60 * 60 * 2);   //token的有效期
        services.setRefreshTokenValiditySeconds(60 * 60 * 24 * 3);   // 刷新token的有效期
        return services;
    }

    /**
     * 开启token验证
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security .tokenKeyAccess("permitAll()"); //url:/oauth/token_key,exposes public key for token verification if using JWT tokens
        security.checkTokenAccess("isAuthenticated()");
        security.allowFormAuthenticationForClients();
        security.passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    /**
     * 配置客户端信息从数据库访问和加密模式
     * @return
     */
    @Bean
    public ClientDetailsService clientDetails() {
        JdbcClientDetailsService jdbcClientDetailsService=new JdbcClientDetailsService(dataSource);
        jdbcClientDetailsService.setPasswordEncoder(passwordEncoder);
        return jdbcClientDetailsService;
    }

    /**
     * 拦截客户端信息
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetails());
    }

    /**
     * 设置token存储规则
     * 设置token生成方式
     * 设置token令牌服务
     * 设置刷新token验证方式
     * 设置url访问权限
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore);
        endpoints.authenticationManager(authenticationManager);
        endpoints.accessTokenConverter(jwtTokenConverter());
        endpoints.tokenServices(tokenServices);
        endpoints.userDetailsService(userDetailsService);
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }
    /**
     * token转换器(jwt)
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        return jwtAccessTokenConverter;
    }

    @Bean
    public TokenStore tokenStore(@Qualifier("dataSource") DataSource dataSource){
        TokenStore tokenStore = new JdbcTokenStore(dataSource);
        return tokenStore;
    }
}
