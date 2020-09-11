package com.zben.data.search;

import java.util.Arrays;

/**
 * @DESC:插值查找 有序数组
 * 对于数据量较大，关键字分布比较均匀的查找表来说，采用插值查找，速度较快
 * 关键字分布不均匀的情况下，该方法不一定比折半查找要好
 * 分布不均匀意思是跳跃性大
 * left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left])
 * @author: zhouben
 * @date: 2020/9/8 0008 16:50
 */
public class InsertSearch {

    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 1; i <= 100; i++) {
            arr[i - 1] = i;
        }
        int index = insertSearch(arr, 0, arr.length - 1, 66);
        System.out.println("index=" + index);
    }

    /**
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static int insertSearch(int[] arr, int left, int right, int findVal) {
        System.out.println("插值查找");
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {
            return insertSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return insertSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

}
