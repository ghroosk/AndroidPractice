package com.zhx.practice.design_patterns.six_principles;

import org.junit.Test;

/**
 * 单一职责原则
 * Created by zhx on 2018/5/15.
 */

public class SRP {

    @Test
    public void SrpTest() {
        Animal animal = new Animal();
        animal.move("牛");
        animal.move("羊");
        animal.move("鱼");

//        Terrestrial terrestrial = new Terrestrial();
//        terrestrial.move("牛");
//        terrestrial.move("羊");
//
//        Aquatic aquatic = new Aquatic();
//        aquatic.move("鱼");


    }

    private class Animal {

        private void move(String animal) {
            System.out.println(animal + "奔跑");
        }

//        private void move2(String animal) {
//            System.out.println(animal + "在水里游");
//        }

//        // 极差的拓展方式
//        private void move(String animal) {
//            if ("鱼".equals(animal)) {
//                System.out.println(animal + "奔跑");
//            } else {
//                System.out.println(animal + "在水里游");
//            }
//        }


    }

    /**
     * 单一职责原则 实现
     */

    private class Terrestrial {

        private void move(String animal) {
            System.out.println(animal + "奔跑");
        }
    }

    private class Aquatic {

        private void move(String animal) {
            System.out.println(animal + "在水里游");
        }
    }


}
