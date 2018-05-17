package com.zhx.practice.design_patterns.six_principles;

import org.junit.Test;

/**
 * 开闭原则OCP
 * Created by zhx on 2018/5/16.
 */

public class OCP {

    @Test
    public void ocpTest() {
//        Animal animal = new Animal();
//        animal.move("terrestrial", "牛");
//        animal.move("terrestrial", "羊");
//        animal.move("aquatic", "鱼");

        Animal animalTerrestrial = new Terrestrial("牛");
        animalTerrestrial.movement();
        Animal animalAquatic = new Aquatic("鱼");
        animalAquatic.movement();
        Animal animalCelestial = new Celestial("鸟");
        animalCelestial.movement();
    }
//
//    private class Animal {
//
//        // 感觉很少会有人这样写逻辑，这里只是做个经典的示范
//        private void move(String type, String animal) {
//            if (type.equals("terrestrial")) {
//                Terrestrial terrestrial = new Terrestrial(animal);
//                terrestrial.move();
//            } else if (type.equals("aquatic")) {
//                Aquatic aquatic = new Aquatic(animal);
//                aquatic.move();
//            }
//
//        }
//    }

    // 这里是通过抽象类的方法实现，接口也可以实现
    // 使用接口还是抽象类请根据实际情况来选择
    private abstract class Animal {
        abstract void move();

        private void movement() {
            move();
        }
    }

//    private interface Movement {
//        void move();
//    }

    private class Terrestrial extends Animal {

        private String mTerrestrial;

        Terrestrial(String animal) {
            mTerrestrial = animal;
        }

        @Override
        public void move() {
            System.out.println(mTerrestrial + "奔跑");
        }
    }

    private class Aquatic extends Animal {

        private String mAquatic;

        Aquatic(String animal) {
            mAquatic = animal;
        }

        @Override
        public void move() {
            System.out.println(mAquatic + "在水里游");
        }
    }

    private class Celestial extends Animal{

        private String mCelestial;

        Celestial(String animal) {
            mCelestial = animal;
        }

        @Override
        public void move() {
            System.out.println(mCelestial + "在天空飞");
        }
    }

}
