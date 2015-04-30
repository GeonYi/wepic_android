package com.momori.wepic.controller.user;

import android.test.suitebuilder.annotation.SmallTest;

import com.momori.wepic.controller.post.UserController;
import com.momori.wepic.model.response.ResCommonModel;
import com.momori.wepic.model.UserModel;
import com.momori.wepic.model.response.ResLogInModel;

import junit.framework.TestCase;

public class UserRegTest extends TestCase {
    @Override
    protected void setUp() throws Exception {
        System.out.println("setUp");
        super.setUp();
    }

    @SmallTest
    public void testAdd(){

        UserModel user = new UserModel("gggg@gmail.com", "00001111");
        UserController usr = new UserController(user);
        ResLogInModel tmp = usr.loginUser();

        System.out.println("tmp : " + tmp.getResult());
        System.out.println("tmp : " + tmp.getMsg());
    }

    @Override
    protected void tearDown() throws Exception {
        System.out.println("tearDown");
        super.tearDown();
    }
}