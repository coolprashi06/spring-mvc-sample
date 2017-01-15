package com.prashast.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.prashast"})
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /*
    To use Spring MVC context configuration for your web application from this class (i.e. JavaConfig) instead of applicationContext.xml; then declare following in web.xml for Dispatcher Servlet:

        <init-param>
            <param-name>contextClass</param-name>
            <param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
        </init-param>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>com.prashast.config.WebMvcConfig</param-value>
        </init-param>

        and also declare following in web-app element:

        <context-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>com.prashast.config.WebMvcConfig</param-value>
        </context-param>

        <context-param>
            <param-name>contextClass</param-name>
            <param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
        </context-param>

        <context-param>
            <param-name>spring.profiles.active</param-name>
            <param-value>test</param-value>
        </context-param>
    */

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

}
