package com.zhx.practice.design_patterns.six_principles;

import org.junit.Test;

/**
 * 依赖倒置原则
 * Created by zhx on 2018/5/16.
 */

public class DIP {

    @Test
    public void dipTest() {
//        Writer writer = new Writer();
//        Book book = new Book();
//        Newspaper newspaper = new Newspaper();
//        writer.read(book);
//        writer.read(newspaper);
        Writer writer = new Writer();
        writer.read(new Book());
        writer.read(new Newspaper());
    }


    private class Book implements IReader{

        @Override
        public String getContent() {
            return "读书";
        }
    }

    private class Newspaper implements IReader{

        @Override
        public String getContent() {
            return "读报纸";
        }
    }

    private class Writer {

        private void read(IReader reader) {
            System.out.println("作家" + reader.getContent());
        }

//        private void read(Book book) {
//            System.out.println("作家" + book.getContent());
//        }
//
//        private void read(Newspaper newspaper) {
//            System.out.println("作家" + newspaper.getContent());
//        }
    }


    private interface IReader {
        String getContent();
    }


}
