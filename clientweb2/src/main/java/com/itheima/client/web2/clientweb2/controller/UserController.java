package com.itheima.client.web2.clientweb2.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/test")
@Controller
public class UserController {

    @RequestMapping("/test")
    @ResponseBody
    public String user() {
        return "test";
    }
}
