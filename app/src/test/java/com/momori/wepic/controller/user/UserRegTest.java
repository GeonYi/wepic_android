package com.momori.wepic.controller.user;

import android.test.suitebuilder.annotation.SmallTest;

import com.momori.wepic.model.CommonResponseModel;
import com.momori.wepic.model.UserModel;

import junit.framework.TestCase;

public class UserRegTest extends TestCase {
    @Override
    protected void setUp() throws Exception {
        System.out.println("setUp");
        super.setUp();
    }

    @SmallTest
    public void testAdd(){

//        UserModel user = new UserModel("gggg@gmail.com", "00001111", "ggggoooo", "01000001111", "android");
//        UserController usr = new UserController(user);
//        CommonResponseModel tmp = usr.registUser();
//
//        System.out.println("tmp : " + tmp.getResult());
//        System.out.println("tmp : " + tmp.getMsg());

        /////////////////////////////////////////////////////////////////////

        UserModel user = new UserModel("gggg@gmail.com", "00001111");
        UserController usr = new UserController(user);
        CommonResponseModel tmp = usr.loginUser();

        System.out.println("tmp : " + tmp.getResult());
        System.out.println("tmp : " + tmp.getMsg());
    }

    @Override
    protected void tearDown() throws Exception {
        System.out.println("tearDown");
        super.tearDown();
    }
}