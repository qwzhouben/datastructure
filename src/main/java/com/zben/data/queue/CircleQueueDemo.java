package com.zben.data.queue;

/**
 * @DESC:环形队列
 * @author: zhouben
 * @date: 2020/8/18 0018 17:18
 */
public class CircleQueueDemo {

    public static void main(String[] args) {

    }
}

class CircleQueue {

    private int maxSize; //数组最大容量
    private int front; //队列头 指向第一个元素
    private int rear;  //队列尾 指向最后一个元素后一个位置， 因为希望空一个空间做约定
    private int[] arr; //用于存放数据，模拟队列

    public CircleQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
    }
    /**
     * 判断队列是否已满
     * @return
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 判断队列是否空
     * @return
     */
    public boolean isEmpty() {
        return rear == front;
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
        arr[rear] = n;
        //将rear后移，必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    /**
     * 获取队列值
     * @return
     */
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，获取不到~");
        }
        //1. 先把front的值保存
        int temp = arr[front]; //
        return temp;
    }
}
