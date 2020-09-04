package com.zben.data.sort;

import java.util.Arrays;

/**
 * @DESC:插入排序
 * 思想：
 *  把n个待排序的元素看成一个有序表和一个无序表，开始时有序表中只包含一个元素，无序表中包含n-1个元素
 *  排序过程中每次从无序表中取出第一个元素，把它的排序码依次有序进行比较，将它插入有序表中的适当位置
 * @author: zhouben
 * @date: 2020/9/4 0004 15:50
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); //生成[0-8000000)
        }

        long start = System.currentTimeMillis();
        sort(arr);
        System.out.println(System.currentTimeMillis() - start);
    }

    private static void sort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i]; //待插入的数
            int insertIndex = i - 1; //待插入的下标

            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertVal;
        }

    /*    //第一轮
        int insertVal = arr[1]; //待插入的数
        int insertIndex = 1 - 1; //待插入的下标
        //1. insertIndex >= 0 保证在给insertVal找插入位置，不越界
        //2. insertVal < arr[insertIndex]待插入的数，还没有找到插入位置
        //3. 就需要将arr[insertIndex]后移
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex + 1] = insertVal;
        System.out.println("第一轮结果~");
        System.out.println(Arrays.toString(arr));

        //第二轮
        insertVal = arr[2]; //待插入的数
        insertIndex = 2 - 1; //待插入的下标

        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex + 1] = insertVal;
        System.out.println("第二轮结果~");
        System.out.println(Arrays.toString(arr));

        //第三轮
        insertVal = arr[3]; //待插入的数
        insertIndex = 3 - 1; //待插入的下标

        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex + 1] = insertVal;
        System.out.println("第三轮结果~");
        System.out.println(Arrays.toString(arr));*/
    }
}
