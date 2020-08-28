package com.zben.data.stack;

/**
 * @DESC:计算器
 * @author: zhouben
 * @date: 2020/8/28 0028 16:05
 */
public class Calculator {

    public static void main(String[] args) {
        /**
         * 一个数栈，一个符号栈
         * 1.如果第一个数栈是空，直接入栈
         * 2.1 如果是符号 如果符号栈是空，直接入栈
         * 2.2 如果符号栈不是空， 判断符号的优先级 优先级低的 从数栈pop出两个， 从符号栈
         * pop出一个进行计算 得到结果push到数栈， 符号push到符号栈
         * 3. 分别pop进行计算
         */
        String expression = "70+20*6-3";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        int index = 0; // 用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        char ch = ' ';
        int res = 0;
        String keepNum = "";

        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);
            //判断是否是符号
            if (operStack.isOper(ch)) {
                if (operStack.isEmpty()) { // 如果符号栈是空 直接入栈
                    operStack.push(ch);
                } else {
                    if (operStack.priority(ch) <= operStack.priority(operStack.peer())) {
                        //优先级小于等于符号栈栈顶的运算符
                        //弹出数栈的两个数，弹出符号栈的符号， 进行运算
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = operStack.cal(num1, num2, oper);
                        //把结果入栈数栈， 运算符入栈符号栈
                        operStack.push(ch);
                        numStack.push(res);
                    } else {
                        //否则，直接入栈
                        operStack.push(ch);
                    }
                }
            } else { //如果是数  直接入栈
                //处理多位数
                keepNum += ch;

                //如果ch是最后一位数了，直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {

                    //还需要判断下一位是数字还是运算符
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果是运算符  直接入数栈
                        numStack.push(Integer.parseInt(keepNum));
                        //重要的是把keepNum情况
                        keepNum = "";
                    }
                }
            }
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        //当表达式扫描完毕， 直接计算
        while (true) {
            //如果符号栈为空，则计算到了最后的结果
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = operStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        System.out.printf("%s = %d", expression, numStack.pop());
    }

}

class ArrayStack2 {
    private int maxSize;    //栈最大值
    private int[] stack;    //数组模拟栈
    private int top = -1;   //栈顶默认-1

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    /**
     * 栈是否已满
     *
     * @return
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 栈是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 查看栈顶
     *
     * @return
     */
    public int peer() {
        return stack[top];
    }

    /**
     * 判断是不是运算符
     *
     * @param val
     * @return
     */
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    /**
     * 判断优先级， 数字越大，优先级越高
     *
     * @param oper
     * @return
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        }
        return -1;
    }

    /**
     * 计算
     *
     * @param num1 先弹出的数
     * @param num2 后弹出的数
     * @param oper
     * @return
     */
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

    /**
     * 入栈
     *
     * @param value
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈已满, 无法添加~");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈
     *
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
