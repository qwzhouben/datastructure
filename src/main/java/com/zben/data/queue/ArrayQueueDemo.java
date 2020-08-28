package com.zben.data.queue;

import java.util.Scanner;

/**
 * @DESC:队列
 * @author: zhouben
 * @date: 2020/8/18 0018 11:36
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        boolean loop  = true;
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列获取数据");
            System.out.println("h(head): 显示队列第一个数据");
            System.out.println("e(exit): 退出");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    int j = queue.getQueue();
                    System.out.println(j);
                    break;
                case 'h':
                    int i = queue.headQueue();
                    System.out.println(i);
                    break;
                case 'e':
                    loop = false;
                    System.out.println("退出~");
                    break;
                default:
                    break;
            }
        }
        System.out.println("退出~");
    }

    /**
     * 使用数组模拟队列
     */
    static class ArrayQueue {
        private int maxSize; //数组最大容量
        private int front; //队列头
        private int rear;  //队列尾
        private int[] arr; //用于存放数据，模拟队列

        /**
         * 构造器
         * @param maxSize
         */
        public ArrayQueue(int maxSize) {
            this.maxSize = maxSize;
            this.front = -1;    //指向队列头， 前一个位置
            this.rear = -1;     //指向队列尾 ，队列最后一个数据
            this.arr = new int[maxSize];
        }

        /**
         * 判断队列是否已满
         * @return
         */
        public boolean isFull() {
            return rear == maxSize - 1;
        }

        /**
         * 判断队列是否空
         * @return
         */
        public boolean isEmpty() {
            return front == rear;
        }

        /**
         * 添加数据到队列
         * @param n
         */
        public void addQueue(int n) {
            if (isFull()) {
                System.out.println("队列已满， 不能添加~");
                return;
            }
            rear++; //让rear后移
            arr[rear] = n;
        }

        /**
         * 获取队列值
         * @return
         */
        public int getQueue() {
            if (isEmpty()) {
                throw new RuntimeException("队列为空，获取不到~");
            }
            front++;    //让front后移
            return arr[front];
        }

        /**
         * 展示队列
         */
        public void showQueue() {
            if (isEmpty()) {
                System.out.println("队列为空~");
            }
            //遍历
            for (int i = 0; i < arr.length; i++) {
                System.out.printf("arr[%d]=%d\t", i, arr[i]);
            }
        }

        /**
         * 显示队列头数据
         * @return
         */
        public int headQueue() {
            if (isEmpty()) {
                throw new RuntimeException("队列为空，获取不到~");
            }
            return arr[front + 1];
        }
    }


}
