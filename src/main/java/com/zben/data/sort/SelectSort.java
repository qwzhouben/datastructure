package com.zben.data.sort;

import java.util.Arrays;

/**
 * @DESC:选择排序：结论
 * 1. 选择排序一共有数组大小-1轮排序
 * 2. 每一轮排序，又是一个循环
 * 思想：
 *  第一次从arr[0]~arr[n-1]中选取最小值，与arr[0]交换， 第二次从arr[1]~arr[n-1]中选最小值，
 *  与arr[1]交换，与此类推，得到一个按排序码从小到大的有序序列
 * @author: zhouben
 * @date: 2020/9/4 0004 11:31
 */
public class SelectSort {

    public static void main(String[] args) {

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); //生成[0-8000000)
        }

        long start = System.currentTimeMillis();
        sort(arr);
        System.out.println((System.currentTimeMillis() - start));

    /*    int[] arr = {101, 24, 119, 1};
        System.out.println("排序前~");
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后~");
        System.out.println(Arrays.toString(arr));*/
    }

    private static void sort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            int minIndex = i; //假定最小值的index
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    minIndex = j;
                    min = arr[minIndex];
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }

      /*  //第一轮
        int minIndex = 0; //假定最小值的index
        int min = arr[0];
        for (int j = 0 + 1; j < arr.length; j++) {
            if (arr[j] < min) {
                minIndex = j;
                min = arr[minIndex];
            }
        }

        arr[minIndex] = arr[0];
        arr[0] = min;

        System.out.println("第一轮排序后~");
        System.out.println(Arrays.toString(arr));

        //第二轮
        minIndex = 1; //假定最小值的index
         min = arr[1];
        for (int j = 1 + 1; j < arr.length; j++) {
            if (arr[j] < min) {
                minIndex = j;
                min = arr[minIndex];
            }
        }

        if (minIndex != 1) {
            arr[minIndex] = arr[1];
            arr[1] = min;
        }

        System.out.println("第二轮排序后~");
        System.out.println(Arrays.toString(arr));

        //第三轮
        minIndex = 2; //假定最小值的index
        min = arr[2];
        for (int j = 2 + 1; j < arr.length; j++) {
            if (arr[j] < min) {
                minIndex = j;
                min = arr[minIndex];
            }
        }

        if (minIndex != 2) {
            arr[minIndex] = arr[2];
            arr[2] = min;
        }

        System.out.println("第三轮排序后~");
        System.out.println(Arrays.toString(arr));*/
    }

}
