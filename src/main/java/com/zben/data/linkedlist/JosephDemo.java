package com.zben.data.linkedlist;

/**
 * @DESC:约瑟夫问题 （1.2...n）个小孩， 从k开始数，数到m就出来，求出来的顺序
 * @author: zhouben
 * @date: 2020/8/27 0027 15:46
 */
public class JosephDemo {

    public static void main(String[] args) {
        CircleSingleLinkedList circle = new CircleSingleLinkedList();
        circle.add(5);
        System.out.println("打印链表~");
        circle.show();

        System.out.println("出圈顺序~");
        circle.countByBoy(1, 3, 5);
    }

}

class CircleSingleLinkedList {
    //定义第一个小孩
    private Boy first;

    /**
     * 添加num个小孩
     *
     * @param num
     */
    public void add(int num) {
        if (num < 1) {
            System.out.println("添加的小孩必须大于1~");
            return;
        }
        Boy curBoy = null; //辅助变量
        for (int i = 1; i <= num; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                curBoy = first;
                curBoy.setNext(first); //建立环形
                continue;
            }
            curBoy.setNext(boy);
            boy.setNext(first);
            curBoy = boy;
        }
    }

    /**
     * 约瑟夫问题（1.2...n）个小孩， 从k开始数，数到m下就出来，求出来的顺序
     *
     * @param k
     * @param m
     * @param n
     */
    public void countByBoy(int k, int m, int n) {
        if (first == null || k < 1 || m > n) {
            System.out.println("输入的参数不正确~");
            return;
        }
        Boy helper = first;
        // helper指向最后一个数
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        //从第k个开始数， 移动k-1位
        for (int i = 0; i < k - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //数到m下就出圈， 移动m-1位, 直到只有一个节点就退出循环
        while (true) {
            if (first == helper) {
                break;
            }
            for (int i = 0; i < m - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("出圈的小孩是%d \n", first.getNo());
            //将first指向下一个
            first =first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后出圈的小孩是%d \n", first.getNo());

    }

    /**
     * 显示环形链表
     */
    public void show() {
        if (first == null) {
            System.out.println("链表为空~");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.printf("当前小孩的序号：%d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext();
        }
    }
}

class Boy {

    private int no;     //小孩的编号
    private Boy next;   //下一个小孩

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}