package com.example.demo.model;

public class Result {

    private String msg;
    private boolean flag;
    private Object obj;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Result() {
    }

    public Result(String msg, boolean flag, Object obj) {
        this.msg = msg;
        this.flag = flag;
        this.obj = obj;
    }

    public static Result error(String msg, Object obj){
        return new Result(msg, false, obj);
    }

    public static Result success(String msg, Object obj){
        return new Result(msg, true, obj);
    }
}
