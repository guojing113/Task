package com.model;

/**
 * @Author qmz
 * @Description
 * @Data 2018/7/10$ 14:56$
 **/
public class Result<T> {
    /*错误码*/
    private Integer code;
    /*提示信息*/
    private String msg;
    /*具体的内容*/
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
