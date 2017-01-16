package com.prashast.config;


import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;

public class WebAppInitializer implements WebApplicationInitializer {

    /*
    download servlet-api 3.1.0 jar from https://jar-download.com/download/javax.servlet-api-jar-3.1.0-download-with-dependencies.php
     */

    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebMvcConfig.class);
        context.setServletContext(servletContext);

        servletContext.addListener(new ContextLoaderListener(context));
        servletContext.setInitParameter("contextInitializerClasses","com.prashast.config.SpringContextInitializer");

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/rest/*");
    }
}
