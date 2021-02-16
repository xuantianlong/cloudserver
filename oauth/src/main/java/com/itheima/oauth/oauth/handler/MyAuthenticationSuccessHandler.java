package com.itheima.oauth.oauth.handler;

import com.feginapi.domain.User;
import com.feginapi.utils.AjaxResult;
import com.feginapi.utils.HttpStatus;
import com.feginapi.utils.ResponseUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
    /**
     * 如果不是前后端分类执行此方法
     * @param httpServletRequest
     * @param httpServletResponse
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        User user = ((User) authentication.getPrincipal());
        ResponseUtils.out(httpServletResponse, AjaxResult.toReponseEntry("登录成功",user, HttpStatus.HTTP_OK));
    }
}
