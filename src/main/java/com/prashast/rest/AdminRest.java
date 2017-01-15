package com.prashast.rest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/admin")
public class AdminRest {


    @RequestMapping(produces = {MediaType.TEXT_PLAIN_VALUE}, method = RequestMethod.GET)
    @ResponseBody
    public String getAdminContent(){
        return "hey, you've reached admin page";
    }
}
