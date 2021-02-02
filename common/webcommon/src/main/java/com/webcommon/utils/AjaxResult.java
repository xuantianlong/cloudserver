package com.webcommon.utils;

public class AjaxResult {

    private String code;

    private String msg;

    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static AjaxResult toReponseEntry(String msg,Object data,String httpStatus){
        AjaxResult ajaxResult = AjaxResult.getInstance();
        ajaxResult.setCode(httpStatus);
        ajaxResult.setMsg(msg);
        ajaxResult.setData(data);
        return ajaxResult;
    }

    private static AjaxResult  getInstance(){
        return AjaxResultPrivate.ajaxResult;
    }

    private static class AjaxResultPrivate{
        private  static AjaxResult ajaxResult = new AjaxResult();
    }
}
