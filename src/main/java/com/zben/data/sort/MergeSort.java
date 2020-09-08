package com.zben.data.sort;

import java.util.Arrays;

/**
 * @DESC:归并排序 思想：
 * 采用经典的分治策略
 * @author: zhouben
 * @date: 2020/9/7 0007 17:44
 */
public class MergeSort {

    public static void main(String[] args) {
       /* int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length -1, temp);
        System.out.println(Arrays.toString(arr));*/
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000); //生成[0-8000000)
        }
        int[] temp = new int[arr.length];
        //System.out.println(Arrays.toString(arr));
        long start = System.currentTimeMillis();
        mergeSort(arr, 0, arr.length -1, temp);
        System.out.println((System.currentTimeMillis() - start));
    }

    /**
     * 分 + 治
     *
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;  //中间索引
            //向左递归
            mergeSort(arr, left, mid, temp);
            //向右递归
            mergeSort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并
     *
     * @param arr   原始数组
     * @param left  左边有序序列初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  临时中转数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;       //初始化i，左边有序序列初始索引
        int j = mid + 1;    //初始化j，右边有序序列初始索引
        int t = 0;          //临时数组的当前索引

        //1. 把左右两边有序序列填充到中转数组
        //直到左右两边有一边遍历完
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        //2. 剩余的填充到中转数组
        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }

        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }

        //3. 将所有元素拷贝到arr
        //第一次合并 tempLeft=0, right=1
        //第二次合并 tempLeft=2, right=3
        //第三次合并 tempLeft=0, right=3
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
