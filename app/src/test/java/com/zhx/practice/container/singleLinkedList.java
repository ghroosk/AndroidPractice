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
        linkedList.addNode(11);
        linkedList.addNode(435);
        System.out.println("linkedList.size(): " + linkedList.size());
        linkedList.insert(2, 22);
        linkedList.insert(2, 22);
        linkedList.insert(5, 55);
        linkedList.insert(18, 18);
        linkedList.insert(-1, -1);
        System.out.println("linkedList.size(): " + linkedList.size());
        linkedList.delete(5);
        linkedList.delete(18);
        linkedList.delete(-1);
        System.out.println("linkedList.size(): " + linkedList.size());
        linkedList.sort();
        System.out.println("linkedList.size(): " + linkedList.size());
        linkedList.deleteDuplicate();
        System.out.println("linkedList.size(): " + linkedList.size());
        Node node = linkedList.getMidNode();
        System.out.println("linkedList.mid data: " + node.data);
    }


    /**
     * 单向链表 : 一个节点指向下一个节点
     */
    private class LinkedList {

        private Node head;

        private int length;

        private Node getHead() {
            return head;
        }

        /**
         * 添加元素
         */
        private void addNode(int data) {
            Node newNode = new Node(data);
            addNode(newNode);
        }

        private void addNode(Node node) {
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
        private int size() {
            int size = 0;
            // 遍历链表,这里的头节点是有数据的，巨大多数情况下头节点不显示数据；
            Node temp = head;
            while (temp != null) {
                size++;
                System.out.println("data ：" + temp.data);
                temp = temp.next;
            }
            return size;
        }

        /**
         * 插入节点；
         */
        private void insert(int index, int data) {
            if (index < 0 || index > length) {
                System.out.println("insert position is illegal");
                return;
            }

            int curPosition = 0;
            Node insertNode = new Node(data);
            Node temp = head;

            while (temp.next != null) {
                if (curPosition == (index - 1)) {
                    insertNode.next = temp.next;
                    temp.next = insertNode;
                    length++;
                    return;
                }
                temp = temp.next;
                curPosition++;
            }
        }

        /**
         * 删除节点；
         */
        private void delete(int index) {
            if (index < 0 || index > length) {
                System.out.println("delete position is illegal");
                return;
            }
            int curPosition = 0;
            Node temp = head;
            while (temp.next != null) {
                if (curPosition == index - 1) {
                    temp.next = temp.next.next;
                    length--;
                    return;
                }
                temp = temp.next;
                curPosition++;
            }
        }

        /**
         * 对链表进行冒泡排序
         */
        private void sort() {
            System.out.println("begin sort");
            Node currentNode;
            Node nextNode;

//            // 没有长度的实现方法
//            for (currentNode = head; currentNode.next != null; currentNode = currentNode.next) {
//                for (nextNode = head; nextNode.next != null; nextNode = nextNode.next) {
//                    if (nextNode.data > nextNode.next.data) {
//                        int temp = nextNode.data;
//                        nextNode.data = nextNode.next.data;
//                        nextNode.next.data = temp;
//                    }
//                }
//            }

            // 有长度的优化算法
            boolean isChange;
            currentNode = head;
            nextNode = head;
            for (int i = 0; i <= length; i++) {
                isChange = false;
                for (int j = 0; j < length - i; j++) {
                    if (nextNode.next != null) {
                        if (nextNode.data > nextNode.next.data) {
                            int temp = nextNode.data;
                            nextNode.data = nextNode.next.data;
                            nextNode.next.data = temp;
                            isChange = true;
                        }
                        nextNode = nextNode.next;
                    }
                }
                nextNode = head;
                currentNode = currentNode.next;
                //如果比较完一趟没有发生置换，那么说明已经排好序了，不需要再执行下去了
                if (!isChange) {
                    break;
                }
            }

        }

        /**
         * 删除链表内的重复数据
         */
        private void deleteDuplicate() {
            System.out.println("begin deleteDuplicate");
            Node temp = head;
            Node nextNode = temp.next;

            while (temp.next != null) {
                while (nextNode.next != null) {
                    if (nextNode.next.data == nextNode.data) {
                        //将下一个节点删除(当前节点指向下下个节点)
                        nextNode.next = nextNode.next.next;
                    } else {
                        //继续下一个
                        nextNode = nextNode.next;
                    }
                }
                //下一轮比较
                temp = temp.next;
            }
        }

        /**
         * 查询链表中的中间元素
         */
        private Node getMidNode() {
            System.out.println("begin getMidNode");
            Node p1 = head;
            Node p2 = head;

            // 一个走一步，一个走两步，直到为null，走一步的到达的就是中间节点
            while(p2 != null && p2.next != null && p2.next.next != null) {
                p1 = p1.next;
                p2 = p2.next.next;
            }
            return p1;
        }

//        /**
//         * 实现链表的反转
//         *
//         * @param node 链表的头节点
//         */
//        private Node reverseLinkList(Node node) {
//            Node prev ;
//            if (node == null || node.next == null) {
//                prev = node;
//            } else {
//                Node tmp = reverseLinkList(node.next);
//                node.next.next = node;
//                node.next = null;
//                prev = tmp;
//            }
//            return prev;
//        }


    }

    private class Node {

        // 数据域
        private int data;

        // 指针域
        private Node next;

        private Node(int data) {
            this.data = data;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

}
