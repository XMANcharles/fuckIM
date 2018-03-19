/*
 * Copyright (C), 1995-2018, 没钱有限公司
 * FileName: SecurityConfig
 * Author:   Neo Geng
 * Date:     2018/1/28 15:56
 * Description: SecurityConfig
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.honeybadger.im.fuck.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 〈SecurityConfig><br>
 * <a href="https://www.cnblogs.com/softidea/p/5991897.html">https://www.cnblogs.com/softidea/p/5991897.html</a>
 * <p>{@link EnableWebSecurity 禁用Boot的默认Security配置，配合@Configuration启用自定义配置（需要扩展WebSecurityConfigurerAdapter）}
 * <p>{@link EnableGlobalMethodSecurity#prePostEnabled()}  启用Security注解，例如最常用的@PreAuthorize
 * <p><a href="https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-security">SpringBoot-Security文档</a></p>
 * @author Neo Geng
 * Date 2018/1/28
 * @since 1.0.0
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier(value = "myUserDetailsServiceImpl")
    @Autowired
    private UserDetailsService detailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //配置许可的URL&&除此之外全部认证
                .mvcMatchers("/","/registers").permitAll().anyRequest().authenticated()
                //启动FormLogin
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll()
                //配置logout
                .and().logout().logoutUrl("/logout")
                //配置Session
                .and().sessionManagement().maximumSessions(1).expiredUrl("/expired").and()
                //配置异常处理-访问拒接页面（已认证授权、权限不足）
                .and().exceptionHandling()//.accessDeniedPage("/accessDenied")
                //X-Frame-Options-同源策略
                .and().headers().frameOptions().sameOrigin();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // Web层面的配置，一般用来配置无需安全检查的路径
        //但是SpringBoot会默认配置常用的(/css/**, /js/**, /images/**, /webjars/** and **/favicon.ico).如果不够用，可以自定义，确实不够用了现在~~~
        web.ignoring().mvcMatchers("/webjars/**","/js/**", "/css/**", "/images/**", "/**/favicon.ico","/layui/**");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //身份验证配置，用于注入自定义身份验证Bean和密码校验规则
        //在大多数情况下， BCrypt 是一个好的选择，除非你有一个遗留系统强制你使用其他的算法。
        auth.userDetailsService(detailsService).passwordEncoder(new BCryptPasswordEncoder());
    }


}
