package com.zhx.practice.design_patterns.six_principles;

import org.junit.Test;

/**
 * 里氏替换原则
 * Created by zhx on 2018/5/16.
 */

public class LSP {

    @Test
    public void lspTest() {

        A a = new A();
        System.out.println("100 + 50 = " + a.add(100, 50));


         B b = new B();
        System.out.println("100 + 50 = " + b.add(100, 50));
        System.out.println("100 + 50 + 100 = " + b.minus(100, 50));

    }


    private class A {
        public int add(int a, int b) {
            return a + b;
        }
    }

    private class B extends A {

        public int add(int a, int b) {
            // a 不再是加上 b
            return a - b;
        }

        private int minus(int a, int b) {
            return add(a , b) + 100;
        }

    }


}
