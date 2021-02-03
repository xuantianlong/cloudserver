package com.itheima.client.web2.clientweb2.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.client.web2.clientweb2.fegin.RemoteUserService;
import com.webcommon.domain.User;
import com.webcommon.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/test")
@Controller
public class UserController {

    @Autowired
    private RemoteUserService remoteUserService;

    @RequestMapping("/test")
    @ResponseBody
    public String user() {
        User user = new User();
        String aa = remoteUserService.aa();
        System.out.println(aa);
        AjaxResult ajaxResult = remoteUserService.list(user);
        System.out.println(ajaxResult);
        return "test";
    }
}
