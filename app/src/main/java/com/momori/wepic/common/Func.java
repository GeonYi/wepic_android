package com.momori.wepic.common;

import android.util.Log;

import com.momori.wepic.model.response.ResCommonModel;

/**
 * Created by sec on 2015-03-21.
 */
public class Func {

    /** post request 결과가 성공인지 아닌지 체크 */
    public static boolean isPostSucc(ResCommonModel res){
        if(Const.REQUEST_REUSLT_SUCCESS.equals(res.getResult())){
            Log.d(res.getResult(), res.getMsg());
            return true;
        }else{
            Log.e(res.getResult(), res.getMsg());
            return false;
        }
    }

    /** post request 결과가 성공인지 아닌지 체크 */
    public static boolean isPostSucc(String result){
        if(result.equals(Const.REQUEST_REUSLT_SUCCESS) == true){
            return true;
        }
        return false;
    }

    /** check email format */
    /*
    public static boolean checkEmailFormat(String email) {
        boolean emailCheckResult = true;

        if(email.length() > Const.EMAIL_LEN){
            emailCheckResult = false ;
        }

        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if(emailCheckResult == true && email.matches(EMAIL_REGEX) == false ){
            emailCheckResult = false ;
        }
        return emailCheckResult;
    }
    */
}
