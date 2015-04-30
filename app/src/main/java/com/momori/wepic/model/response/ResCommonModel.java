package com.momori.wepic.model.response;

/**
 * Created by sec on 2015-03-21.
 */
public class ResCommonModel {
    private String result   ;
    private String msg      ;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResCommonModel{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
