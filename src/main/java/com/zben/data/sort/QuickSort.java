package com.zben.data.sort;

import java.util.Arrays;

/**
 * @DESC:快速排序 思想：
 * 通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比、
 * 另一部分的所有数据要小，然后再按此方法对这两部分数据分别进行快速排序，
 * 整个排序过程可以递归进行
 * @author: zhouben
 * @date: 2020/9/5 0005 10:51
 */
public class QuickSort {

    public static void main(String[] args) {
     /*   int[] arr = {-9, 78, 0, 23, -567, 70, 0, 54, 90,-12, -54,1};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));*/

        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000); //生成[0-8000000)
        }
        //System.out.println(Arrays.toString(arr));
        long start = System.currentTimeMillis();
        quickSort(arr, 0, arr.length -1);
        System.out.println((System.currentTimeMillis() - start));
        //System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left; //左下标
        int r = right;//右下标
        int pivot = arr[(left + right) / 2]; //中轴
        int temp = 0;
        while (l < r) {
            //如果左边的大于等于中轴，退出
            while (arr[l] < pivot) {
                l++;
            }
            //如果右边的小于等于中轴值，退出
            while (arr[r] > pivot) {
                r--;
            }

            if (l >= r) {
                break;
            }

            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完，发现arr[l]==pivot, r--
            if (arr[l] == pivot) {
                r--;
            }
            if (arr[r] == pivot) {
                l++;
            }
        }

        //向左递归
        if (l == r) {
            l++;
            r--;
        }
        if (left < r) {
            quickSort(arr, left, r);
        }

        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }

    }

}
