package com.zben.data.sort;

import java.util.Arrays;

/**
 * @DESC:希尔排序
 * 思想：
 *  把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少
 *  每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组 算法便终止
 * @author: zhouben
 * @date: 2020/9/4 0004 17:12
 */
public class ShellSort {

    public static void main(String[] args) {
        //int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        //shellSort(arr);
        //shellSort2(arr);
        //System.out.println(Arrays.toString(arr));
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); //生成[0-8000000)
        }

        long start = System.currentTimeMillis();
        //shellSort(arr);
        shellSort2(arr);
        System.out.println((System.currentTimeMillis() - start));
    }

    /**
     * 希尔排序 优化  移位法
     *
     * @param arr
     */
    private static void shellSort2(int[] arr) {

        //整理
        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {
            //从第gap个元素 逐步插入
            for (int i = gap; i < arr.length; i++) {
                int j = i; //待插入的下标
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j - gap];
                        j = j - gap;
                    }
                    //当退出后，就给temp找到了插入位置
                    arr[j] = temp;
                }
            }
        }
    }

    /**
     * 希尔排序 交换法
     *
     * @param arr
     */
    private static void shellSort(int[] arr) {

        //整理
        int temp = 0;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有数据， 步长是5
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            //System.out.println("第" + (count++) + "轮排序后:" + Arrays.toString(arr));
        }


 /*       //第1轮 将数据分为10/2=5组
        for (int i = 5; i < arr.length; i++) {
            //遍历各组中所有数据， 步长是5
            for (int j = i - 5; j >= 0; j -= 5) {
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("第1轮排序后:" + Arrays.toString(arr));

        //第2轮 将数据分为5/2=2组
        for (int i = 2; i < arr.length; i++) {
            //遍历各组中所有数据， 步长是2
            for (int j = i - 2; j >= 0; j -= 2) {
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("第2轮排序后:" + Arrays.toString(arr));

        //第3轮 将数据分为2/2=1组
        for (int i = 1; i < arr.length; i++) {
            //遍历各组中所有数据， 步长是2
            for (int j = i - 1; j >= 0; j -= 1) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("第3轮排序后:" + Arrays.toString(arr));*/

    }


}
