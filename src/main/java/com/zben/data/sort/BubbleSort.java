package com.zben.data.sort;

import java.util.Arrays;

/**
 * @DESC:冒泡排序 结论：
 * 1. 一共进行数组的大小-1次大的循环
 * 2. 每一趟排序的次数在逐渐减少
 * 3. 如果我们发现在某趟排序中，没有发生一次交换，可以提前结束冒泡排序。 （优化）
 * @author: zhouben
 * @date: 2020/9/2 0002 11:34
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); //生成[0-8000000)
        }
        long start = System.currentTimeMillis();
        sort(arr);
        System.out.println((System.currentTimeMillis() - start) / 1000);

        /**
         * 冒泡排序时间复杂度O(n^2)
         */



  /*      //第一次排序
        int temp;
        for (int j = 0; j < arr.length - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
        System.out.println("第一次排序的结果：" + Arrays.toString(arr));

        //第二次排序
        for (int j = 0; j < arr.length - 1 - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
        System.out.println("第二次排序的结果：" + Arrays.toString(arr));

        //第三次排序
        for (int j = 0; j < arr.length - 1 - 2; j++) {
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
        System.out.println("第三次排序的结果：" + Arrays.toString(arr));

        //第四次排序
        for (int j = 0; j < arr.length - 1 - 3; j++) {
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
        System.out.println("第四次排序的结果：" + Arrays.toString(arr));
    }*/
    }

    private static void sort(int[] arr) {
        int temp_;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp_ = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp_;
                }
            }
            if (!flag) {
                break;
            } else {
                flag = false;
            }
            //System.out.println("第" + (i + 1) + "次排序的结果：" + Arrays.toString(arr));
        }
    }
}
