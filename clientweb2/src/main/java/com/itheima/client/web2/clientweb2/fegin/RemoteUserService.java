package com.itheima.client.web2.clientweb2.fegin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.webcommon.domain.User;
import com.webcommon.utils.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "clientweb")
public interface RemoteUserService {

    @RequestMapping("/user/list")
    AjaxResult list(@RequestBody User user);

    @RequestMapping("/user/aa")
    String aa();
}
