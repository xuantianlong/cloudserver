package com.webcommon.utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseUtils {
    public static void out(HttpServletResponse response, Object data){
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");//设置字符集为'UTF-8'
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(JSON.toJSONString(data));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            out.flush();
            out.close();
        }
    }
}
