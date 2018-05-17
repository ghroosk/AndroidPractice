package com.zhx.practice.design_patterns.six_principles;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 接口隔离原则
 * Created by zhx on 2018/5/17.
 */

public class ISP {

    @Test
    public void ispTest() {
        Tenant tenant = new Tenant(18, 2720);
        Mediator mediator = new Mediator();
        tenant.rentRoom(mediator);
    }

    private class Room {

        private int mPrice;
        private int mArea;

        Room(int price, int area) {
            mPrice = price;
            mArea = area;
        }

        @Override
        public String toString() {
            return "当前房子" +
                    "房价： " + mPrice +
                    ", 房子面积： " + mArea +
                    '}';
        }
    }

    // 中介
    private class Mediator {

        private int mDiffPrice = 10;
        private int mDiffArea = 100;
        List<Room> mRooms = new ArrayList<Room>();

        Mediator() {
            for (int i = 0; i < 5; i++) {
                mRooms.add(new Room(15 + i, (15 + i) * 150));
            }
        }

        //
//        private List<Room> getRooms() {
//            return mRooms;
//        }
        private Room rent(int price, int area) {
            for (Room room : mRooms) {
                if (isSuitable(room, price, area)) {
                    System.out.println("租到房子了，" + room.toString());
                    return room;
                }
            }
            return new Room(0, 0);
        }

        private boolean isSuitable(Room room, int price, int area) {
            return Math.abs(room.mPrice - price) < mDiffPrice
                    && Math.abs(room.mArea - area) < mDiffArea;
        }

    }

    // 租客
    private class Tenant {

        private int mRoomPrice;
        private int mRoomArea;
//        private int mDiffPrice = 10;
//        private int mDiffArea = 100;

        Tenant(int price, int area) {
            mRoomPrice = price;
            mRoomArea = area;
        }

        private void rentRoom(Mediator mediator) {
//            List<Room> rooms = mediator.getRooms();
//            for (Room room : rooms) {
//                if (isSuitable(room)) {
//                    System.out.println("租到房子了，" + room.toString());
//                    break;
//                }
//            }
            mediator.rent(mRoomPrice, mRoomArea);
        }

//        private boolean isSuitable(Room room) {
//            return Math.abs(room.mPrice - mRoomPrice) < mDiffPrice
//                    && Math.abs(room.mArea - mRoomArea) < mDiffArea;
//        }
    }


}
