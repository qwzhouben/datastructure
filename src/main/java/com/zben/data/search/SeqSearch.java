package com.zben.data.search;

/**
 * @DESC:线性查找：可以是有序，也可以是无序
 * @author: zhouben
 * @date: 2020/9/8 0008 15:14
 */
public class SeqSearch {

    public static void main(String[] args) {
        int[] arr = {1, 7, 11, 34, -4, 83};
        int i = seqSearch(arr, -4);
        if (i == -1) {
            System.out.println("没有找到~");
        } else {
            System.out.println("找到了 下标=" + i);
        }
    }

    /**
     * 线性查找
     *
     * @param arr
     * @param value
     * @return
     */
    public static int seqSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }

}
