package com.hqinjun.myboot.config;

import com.hqinjun.myboot.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource source;

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()                    //  定义当需要用户登录时候，转到的登录页面。
//                .and()
//                .authorizeRequests()        // 定义哪些URL需要被保护、哪些不需要被保护
//                .anyRequest()               // 任何请求,登录后可以访问
//                .authenticated();
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()                    //  定义当需要用户登录时候，转到的登录页面。
                .loginPage("/login.html")           // 设置登录页面
                .loginProcessingUrl("/user/login")  // 自定义的登录接口
                .and()
                .authorizeRequests()        // 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/login.html").permitAll()     // 设置所有人都可以访问登录页面
                .antMatchers("/v2/api-docs",
                        "/definitions/**",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/swagger-resources/configuration/ui",
                        "/swagge‌​r-ui.html").permitAll()
                .anyRequest()               // 任何请求,登录后可以访问
                .authenticated()
                .and().rememberMe().tokenRepository(tokenRepository(source)).tokenValiditySeconds(360)
                .and()
                .csrf().disable();          // 关闭csrf防护
    }

    @Bean
        //UserDetailsService是一个接口
        //意思是@Bean明确的指示了一种方法，什么方法呢----产生一个bean的方法，并且将bean实例交给spring容器管理
    UserDetailsService customUserService(){
        //向spring容器直接植入了一个Service
        return new UserServiceImpl();
    }
    //定制了一个configuration
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {//认证管理构建器
        auth.userDetailsService(customUserService()).passwordEncoder(passwordEncoder());//passwordEncoder(new MyPasswordEncoder()); //user Details Service验证
    }


    @Bean(name = "tokenRepository")
    public PersistentTokenRepository tokenRepository(DataSource dataSource) {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
         //tokenRepository.setCreateTableOnStartup(true); // 第一次启动时可使用此功能自动创建表，第二次要关闭，否则表已存在会启动报错
        return tokenRepository;
    }


    // 处理密码加密解密逻辑
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
