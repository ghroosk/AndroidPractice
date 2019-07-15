package com.zhx.practice;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    public void test1() throws Exception {
        throw new Exception("test");
    }

    @Test
    public void test2() throws Exception {
        test1();
    }

    @Test
    public void test3(){
        try{
            test2();
        } catch(Exception e) {
            System.out.print("catch throw exception");
        }
//        isCorrect2("23");
    }

    @Test
    public void isCorrect2(){
//        String msg = "we";
//        if ("".equals(msg)) {
//            throw new NullPointerException("msg is empty string");
//        }
        try {
            test1();
        } catch (Exception e) {
            System.out.print("catch");
            e.printStackTrace();
        }
    }

}