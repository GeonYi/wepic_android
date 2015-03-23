package com.momori.wepic.common;

/**
 * Created by sec on 2015-03-21.
 */
public class Func {

    /** post request 결과가 성공인지 아닌지 체크 */
    public static boolean isPostSucc(String result){
        if(result.equals(Const.REQUEST_REUSLT_SUCCESS) == true){
            return true;
        }
        return false;
    }
}
