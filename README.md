# spring-mvc-sample

J2EE web application created using Spring MVC with annotations.

##### Add spring-mvc dependency to your project

```
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>4.3.4.RELEASE</version>
    </dependency>

```

##### Configure Spring WebMvc

Create a configuration class extending from **WebMvcConfigurerAdapter** which would define interceptors, resource handlers, cors mappers, filters etc. of your application.
This class should extend **WebMvcConfigurerAdapter** and should be annotated with **@Configuration** and **@EnableWebMvc**.

In this example I created class to configure viewResolver and servlethandler:
```

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

Above code means when view name is passed by controller it would be resolved to viewname.jsp page under /WEB-INF/views folder.
e.g. view name hello would be resolved to hello.jsp under /WEB-INF/views/

```

##### Returning view from controller

To return view from controller you need to have return type set to ModelAndView from the method.
```
e.g.

    @RequestMapping("/hello")
    public ModelAndView helloUser(@RequestParam("name") String name){
        ModelAndView modelAndView = new ModelAndView("hello");
        modelAndView.addObject("name",name);
        return modelAndView;
    }

calling /hello request would show up hello.jsp page and name parameter on hello.jsp page would be dynamically replaced with name passed in this request.
```

#### Configure SpringMvc without web.xml

##### add servlet-api.jar to your project

I cannot find any maven repo containing servlet-api.3.1.0.jar hence downloaded jar from https://jar-download.com/download/javax.servlet-api-jar-3.1.0-download-with-dependencies.php
and added to .m2 repo under desired path and replaced systemPath mentioned below with path on my system:
```    
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>servlet-api</artifactId>
      <version>3.1.0</version>
      <scope>system</scope>
      <systemPath>/Users/prashastsaxena/.m2/repository/javax/servlet/servlet-api/3.1.0/javax.servlet-api-3.1.0.jar</systemPath>
    </dependency>
```


##### Java config version of web.xml
Create a class implementing WebApplicationInitializer interface method onStartup().
This method should define what all needs to happen during application startup i.e. adding filters, listeners, servlets etc.

_Purpose of WebApplicationInitializer is to programmatically configure the servlet context._ 


```
e.g.

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

```
In this example I'm asking it to create WebApplicationContext from class which has WebMvc config defined and then set the servletContext in this web app context class.
Then we define SpringContextLoader Listener which is replacement of:

    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
Then we define init param of this listener as contextInitializerClass which is a replacement of:
    
    <context-param>
        <param-name>spring.profiles.active</param-name>
        <param-value>live</param-value>
    </context-param>

After this we define Spring ServletDispatcher which is replacement of following:

    <servlet>
        <servlet-name>spring-servlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring-servlet.xml</param-value>
        </init-param>
    </servlet>

    <servlet-mapping>
        <servlet-name>spring-servlet</servlet-name>
        <url-pattern>/rest/*</url-pattern>
    </servlet-mapping>

##### Spring ContextInitializer class

This class defines the code that needs to be executed before Spring application context gets created.

_A good use case for using an ApplicationContextInitializer would be to set a Spring environment profile programmatically._

Class should implement interface ApplicationContextInitializer initialize() method:

```
e.g. 
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();
        environment.addActiveProfile("test");
    }
```
in the above example I instructed this code to activate spring profile "test"..


#### References:
* http://stackoverflow.com/questions/22315672/how-to-configure-spring-mvc-with-pure-java-based-configuration
* https://kielczewski.eu/2013/11/spring-mvc-without-web-xml-using-webapplicationinitializer/
* http://codetutr.com/2013/04/09/spring-mvc-easy-rest-based-json-services-with-responsebody/
* http://stackoverflow.com/questions/15530670/trying-to-get-spring-securityconfig-working-with-spring-mvc-and-javaconfig

