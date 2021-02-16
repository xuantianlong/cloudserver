package com.feginapi.service.fegin;

import com.feginapi.domain.User;
import com.feginapi.factory.RemoteUserServiceFallbackFactory;
import com.feginapi.utils.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(contextId = "RemoteUserService", value = "clientweb",fallbackFactory = RemoteUserServiceFallbackFactory.class)
public interface RemoteUserService {

    @RequestMapping("/user/list")
    AjaxResult list(@RequestBody User user);

    @RequestMapping("/user/aa")
    String aa();
}
