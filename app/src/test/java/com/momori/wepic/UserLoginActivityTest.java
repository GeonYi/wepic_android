package com.momori.wepic;

import android.test.suitebuilder.annotation.SmallTest;
import junit.framework.TestCase;

public class UserLoginActivityTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        System.out.println("setUp!!");
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        System.out.println("tearDown");
        super.tearDown();
    }

    @SmallTest
    public void testAdd(){
        assertEquals(null, null);
        System.out.println("Test Case");
    }
}
