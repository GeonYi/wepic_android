package com.momori.wepic.model.response;

/**
 * Created by sec on 2015-04-12.
 */
public class ResLogInModel {
    private String result    ;
    private String msg       ;
    private String user_id   ;

    public ResLogInModel(String result, String msg, String userId) {
        this.result = result;
        this.msg = msg;
        this.user_id = userId;
    }

    public String getResult() { return result; }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserId() { return user_id; }

    @Override
    public String toString() {
        return "ResLogInModel{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
