package com.zben.data.sort;

import java.util.Arrays;

/**
 * @DESC:基数排序 思想：
 * 将所有待比较数值统一为同样的数位长度，数位较短的数前面补零。然后从最低位开始
 * 一次、、依次进行一次排序，这样从最低位排序一直到最高位排序完成后，数列就变成一个有序序列
 * @author: zhouben
 * @date: 2020/9/8 0008 11:01
 */
public class RadixSort {

    public static void main(String[] args) {
        /*int[] arr = {53, 3, 543, 748, 14, 214};
        radixSort(arr);*/

        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000); //生成[0-8000000)
        }
        //System.out.println(Arrays.toString(arr));
        long start = System.currentTimeMillis();
        radixSort(arr);
        System.out.println((System.currentTimeMillis() - start));
        //System.out.println(Arrays.toString(arr));
    }

    public static void radixSort(int[] arr) {

        //整合
        //取出最大值
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //计算最大值的位数
        int maxLength = (max + "").length();

        //定义一个二维数组，表示10个桶，每个桶就是一个一位数组
        /**
         * 1. 二维数组包含10个一位数组
         * 2. 为了防止在放入数的时候，数组溢出，则每个一位数组，大小定arr.length
         * 3. 基数排序是使用空间换时间的经典算法
         */
        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组来记录
        //比如：bucketEleCounts[0]，记录的就是bucket[0]桶存放数据的个数
        int[] bucketEleCounts = new int[10];

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //第1轮， 计算个位
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素对应位数的值
                int digitOfEle = arr[j] / n % 10;
                //放入对应的桶
                bucket[digitOfEle][bucketEleCounts[digitOfEle]] = arr[j];
                bucketEleCounts[digitOfEle]++;
            }
            int index = 0;
            //放入到原始数组中
            for (int k = 0; k < bucketEleCounts.length; k++) {
                //如果桶中有数据， 放入到原始数组中
                if (bucketEleCounts[k] != 0) {
                    for (int l = 0; l < bucketEleCounts[k]; l++) {
                        //取出元素放入arr
                        arr[index++] = bucket[k][l];
                    }
                }
                //每轮处理需要将bucketEleCounts=0
                bucketEleCounts[k] = 0;
            }

            //System.out.println(Arrays.toString(arr));
        }


    /*    //第1轮， 计算个位
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素对应位数的值
            int digitOfEle = arr[j] / 1 % 10;
            //放入对应的桶
            bucket[digitOfEle][bucketEleCounts[digitOfEle]] = arr[j];
            bucketEleCounts[digitOfEle]++;
        }
        int index = 0;
        //放入到原始数组中
        for (int k = 0; k < bucketEleCounts.length; k++) {
            //如果桶中有数据， 放入到原始数组中
            if (bucketEleCounts[k] != 0) {
                for (int l = 0; l < bucketEleCounts[k]; l++) {
                    //取出元素放入arr
                    arr[index++] = bucket[k][l];
                }
            }
            //每轮处理需要将bucketEleCounts=0
            bucketEleCounts[k] = 0;
        }

        System.out.println(Arrays.toString(arr));


        //第2轮， 计算十位
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素对应位数的值
            int digitOfEle = arr[j] / 10 % 10;
            //放入对应的桶
            bucket[digitOfEle][bucketEleCounts[digitOfEle]] = arr[j];
            bucketEleCounts[digitOfEle]++;
        }
        index = 0;
        //放入到原始数组中
        for (int k = 0; k < bucketEleCounts.length; k++) {
            //如果桶中有数据， 放入到原始数组中
            if (bucketEleCounts[k] != 0) {
                for (int l = 0; l < bucketEleCounts[k]; l++) {
                    //取出元素放入arr
                    arr[index++] = bucket[k][l];
                }
            }
            //每轮处理需要将bucketEleCounts=0
            bucketEleCounts[k] = 0;
        }

        System.out.println(Arrays.toString(arr));

        //第3轮， 计算百位
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素对应位数的值
            int digitOfEle = arr[j] / 100 % 10;
            //放入对应的桶
            bucket[digitOfEle][bucketEleCounts[digitOfEle]] = arr[j];
            bucketEleCounts[digitOfEle]++;
        }
        index = 0;
        //放入到原始数组中
        for (int k = 0; k < bucketEleCounts.length; k++) {
            //如果桶中有数据， 放入到原始数组中
            if (bucketEleCounts[k] != 0) {
                for (int l = 0; l < bucketEleCounts[k]; l++) {
                    //取出元素放入arr
                    arr[index++] = bucket[k][l];
                }
            }
            //每轮处理需要将bucketEleCounts=0
            bucketEleCounts[k] = 0;
        }

        System.out.println(Arrays.toString(arr));

*/
    }

}
