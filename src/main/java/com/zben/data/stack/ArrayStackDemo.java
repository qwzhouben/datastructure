package com.zben.data.stack;

import java.util.Scanner;

/**
 * @DESC:数组模拟栈
 * @author: zhouben
 * @date: 2020/8/28 0028 10:25
 */
public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(3);
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        String key = "";
        while (loop) {
            System.out.println("s: show显示栈~");
            System.out.println("p: push添加栈~");
            System.out.println("o: pop弹出栈~");
            System.out.println("e: exit退出~");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "s":
                    stack.show();
                    break;
                case "p":
                    System.out.println("请输入~");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "o":
                    int pop = 0;
                    try {
                        pop = stack.pop();
                        System.out.printf("出栈的数据是%d\n", pop);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "e":
                    System.out.println("程序退出~");
                    loop = false;
                    break;
                default:
                    break;
            }
        }

    }

}

class ArrayStack {
    private int maxSize;    //栈最大值
    private int[] stack;    //数组模拟栈
    private int top = -1;   //栈顶默认-1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    /**
     * 栈是否已满
     * @return
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 栈是否为空
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     * @param value
     */
    public void push(int value) {
        if (isFull()){
            System.out.println("栈已满, 无法添加~");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空~");
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 遍历
     */
    public void show() {
        if (isEmpty()) {
            System.out.println("栈为空~");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d \n", i, stack[i]);
        }
    }
}
