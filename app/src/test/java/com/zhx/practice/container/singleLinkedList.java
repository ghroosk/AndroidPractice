package com.zhx.practice.container;

import org.junit.Test;

/**
 * 单链表定义及相关操作
 * <p>
 * 数组：连续存储线性结构，元素类型相同，大小相等.<br>
 * 优点：存取速度快.<br>
 * 缺点：<br>
 * 1.事先必须知道数组的长度.<br>
 * 2.插入删除元素很慢，插入删除元素的效率很低.（由位置决定效率）<br>
 * 3.空间通常是有限制的，需要大块连续的内存块.<br>
 * <p>
 * 链表：是离散存储线性结构，n个节点离散分配，彼此通过指针相连，每个节点只有一个前驱节点，<br>
 * 每个节点只有一个后续节点，首节点没有前驱节点，尾节点没有后续节点.
 * 优点：空间没有限制，插入删除元素很快.<br>
 * 缺点：存取速度很慢.<br>
 * <p>
 * 链表又分了好几类：<br>
 * 单向链表 : 一个节点指向下一个节点<br>
 * 双向链表 : 一个节点有两个指针域<br>
 * 循环链表: 能通过任何一个节点找到其他所有的节点，将两种(双向/单向)链表的最后一个结点指向第一个结点从而实现循环<br>
 * <p>
 * Created by zhx on 2018/4/26.
 */
public class singleLinkedList {

    @Test
    public void testSingleLinkedList() {
        LinkedList linkedList = new LinkedList();
        linkedList.addNode(10);
        linkedList.addNode(11);
        linkedList.addNode(23);
        linkedList.addNode(435);
        System.out.println(linkedList.length);
        System.out.println(linkedList.size());
        linkedList.insert(2, 22);
        linkedList.insert(2, 22);
        linkedList.insert(5, 55);
        linkedList.insert(18, 18);
        linkedList.insert(-1, -1);
        System.out.println(linkedList.length);
        System.out.println(linkedList.size());
    }


    /**
     * 单向链表 : 一个节点指向下一个节点
     */
    private class LinkedList {

        private Node head;

        public int length;

        /**
         * 添加元素
         */
        public void addNode(int data) {
            Node newNode = new Node(data);
            addNode(newNode);
        }

        public void addNode(Node node) {
            if (head == null) {
                head = node;
                length++;
                return;
            }

            // 临时节点，用于寻找到尾节点.
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = node;
            length++;
        }

        /**
         * 遍历链表,获取链表长度；
         */
        public int size() {
            int size = 0;
            // 遍历链表
            Node temp = head.next;
            while (temp != null) {
                size++;
                System.out.println("data ：" + temp.data);
                temp = temp.next;
            }
            return size;
        }

        /**
         * 插入数据；
         */
        public void insert(int index, int data) {
            if (index < 0 || index > length) {
                System.out.println("insert position is illegal");
                return;
            }

            int curPosition = 0;
            Node insertNode = new Node(data);
            Node temp = head;

            while (temp.next != null) {
                if (curPosition != (index - 1)) {
                    insertNode.next = temp.next;
                    temp.next = insertNode;
                    length++;
                    return;
                }
                temp = temp.next;
                curPosition++;
            }
        }
    }

    private class Node {

        // 数据域
        public int data;

        // 指针域
        public Node next;

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

}
