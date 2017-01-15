package com.prashast.rest;


import com.prashast.dto.User;
import com.prashast.service.SecuredRestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/secured")
public class SecuredRest {

    @Autowired
    SecuredRestService securedRestService;

    static Logger log = Logger.getLogger(SecuredRest.class.getName());

    @RequestMapping(value = "/getUsersByLastName/{lastName}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> getUser(@PathVariable("lastName") String lastName){
        /*
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(username!= null){
            User user = securedRestService.getUser(username);
            System.out.println(user.toString());
            return user;
        }else {
            return null;
        }
        */
        log.info("last name parameter received: "+ lastName);
        return securedRestService.findUsersByLastName(lastName);
    }

    @RequestMapping("/hello")
    public ModelAndView helloUser(@RequestParam("name") String name){
        ModelAndView modelAndView = new ModelAndView("hello");
        modelAndView.addObject("name",name);
        return modelAndView;
    }
}
