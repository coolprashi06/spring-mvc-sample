package com.prashast.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;


public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

    /*
    this class is alternative to following configuration in web.xml:

    <!--
    <filter>
        <filter-name>springSecurityFilterChain</filter-name>
        <filter-class>org.springframework.web.filter.DelegatingFilterProxy
        </filter-class>
    </filter>

    <filter-mapping>
        <filter-name>springSecurityFilterChain</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    -->

     */
}
