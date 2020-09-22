package com.panshi.security03.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/***
 * @Auther: guo
 * @Date: 15:06 2020/9/20
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //Spring Security默认是禁用注解的，要想开启注解，需要在继承WebSecurityConfigurerAdapter的类上加@EnableGlobalMethodSecurity注解，来判断用户对某个控制层的方法是否具有访问权限
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService userDetailsService;

//    // 最后手动指定加密方式,这个原本默认的全局配置config() 可以不需要了
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
////            @Override
////            public String encode(CharSequence charSequence) {
////                return charSequence.toString();
////            }
////
////            @Override
////            public boolean matches(CharSequence charSequence, String s) {
////                return s.equals(charSequence.toString());
////            }
////        });
//        // 如果你想要将密码加密，可以修改 configure() 方法如下：
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(new BCryptPasswordEncoder());
//    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers("/css/**","/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 如果有允许匿名的url，填在下面
//                .antMatchers().permitAll()
                // 添加用户
                .antMatchers("/insert**").permitAll()
                .antMatchers("/add").permitAll()
                .anyRequest().authenticated()
                .and()
                // 设置登陆页
                .formLogin().loginPage("/login")
                // 设置登陆成功页
                .defaultSuccessUrl("/").permitAll()
                // 登录失败url
                .failureUrl("/login/error")
                // 自定义登陆用户名和密码参数，默认为username和password
//                .usernameParameter("username")
//                .passwordParameter("password")
                .and()
                .logout().permitAll()
                //自动登录 会自动保存一个名为remember-me的cookie,可以自定义该cookie的名字如: rememberMe
                .and().rememberMe().rememberMeParameter("rememberMe")
                        // 用户信息保存到数据库
                        .tokenRepository(persistentTokenRepository())
                        // 有效时间：单位s
                        .tokenValiditySeconds(60)
                        .userDetailsService(userDetailsService);

        // 关闭CSRF跨域
        http.csrf().disable();
    }

    @Autowired
    private DataSource dataSource;

    /**
     * 在客户端的 Cookie 中，仅保存一个无意义的加密串（与用户名、密码等敏感数据无关），
     * 然后在数据库中保存该加密串-用户信息的对应关系，自动登录时，用 Cookie 中的加密串，到数据库中验证，
     * 如果通过，自动登录才算通过。
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 如果token表不存在，使用下面语句可以初始化该表；若存在，请注释掉这条语句，否则会报错。
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

//      至此 Spring security 完成了异常处理，总结一下流程：
//
//            –> AbstractAuthenticationProcessingFilter.doFilter()
//
//            –> AbstractAuthenticationProcessingFilter.unsuccessfulAuthentication()
//
//            –> SimpleUrlAuthenticationFailureHandler.onAuthenticationFailure()
//
//            –> SimpleUrlAuthenticationFailureHandler.saveException()


    // 本来用户名输入错误,应该根据自定义CustomUserDetailsService中,抛出UserNotFoundException,
    // 但是hideUserNotFoundExceptions默认为true,这就是导致 UserNotFoundException 无法抛出的原因。
    @Bean
    public DaoAuthenticationProvider authenticationProvider() throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
//   // 这是默认用户名 密码不加密的方式验证  如 lisi 123 密码未加密保存
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence charSequence) {
//                return charSequence.toString();
//            }
//
//            @Override
//            public boolean matches(CharSequence charSequence, String s) {
//                return s.equals(charSequence.toString());
//            }
//        };
//    }


    // 密码加密  用户在注册的时候,对密码进行加密保存
    @Bean
    public PasswordEncoder passwordEncoder() throws Exception {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return new BCryptPasswordEncoder().encode(charSequence.toString());
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                // charSequence 用户输入的密码  , s 是从数据库查到的数据

                return BCrypt.checkpw(charSequence.toString(),s);
            }
        };
    }
}
