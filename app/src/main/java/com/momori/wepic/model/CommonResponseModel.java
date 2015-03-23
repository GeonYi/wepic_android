package com.momori.wepic.model;

/**
 * Created by sec on 2015-03-21.
 */
public class CommonResponseModel {
    private String result   ;
    private String msg      ;

    public CommonResponseModel(String result, String msg) {
        this.result = result;
        this.msg = msg;
    }

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


}
