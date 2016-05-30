package com.spicytown.configuration;

import com.spicytown.component.CustomSuccessHandler;
import com.spicytown.servlet.ImageServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Created by cheyikung on 5/8/16.
 */
@Configuration
public class AppConfig {

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        CustomSuccessHandler handler = new CustomSuccessHandler();
        return handler;
    }

    @Bean(name = "SessionFactory")
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean () {
        return new ServletRegistrationBean( new ImageServlet(), "/images/*" );
    }
}
