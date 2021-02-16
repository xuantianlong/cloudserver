package com.feginapi.factory;


import com.feginapi.domain.User;
import com.feginapi.service.fegin.RemoteUserService;
import com.feginapi.utils.AjaxResult;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class RemoteUserServiceFallbackFactory implements FallbackFactory<RemoteUserService> {

    private static final Logger log = LoggerFactory.getLogger(RemoteUserServiceFallbackFactory.class);

    @Override
    public RemoteUserService create(Throwable throwable) {
        return new RemoteUserService(){
            @Override
            public AjaxResult list(User user) {
                return null;
            }

            @Override
            public String aa() {
                return null;
            }
        };
    }
}
