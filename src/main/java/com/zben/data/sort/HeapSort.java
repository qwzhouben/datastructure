package com.zben.data.sort;

import java.util.Arrays;

/**
 * @DESC:堆排序
 * @author: zhouben
 * @date: 2020/9/14 0014 17:20
 */
public class HeapSort {

    public static void main(String[] args) {
       /* int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);*/
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000); //生成[0-8000000)
        }

        long start = System.currentTimeMillis();
        //shellSort(arr);
        heapSort(arr);
        System.out.println((System.currentTimeMillis() - start));

    }

    public static void heapSort(int[] arr) {
        /*adjustHeap(arr, 1, arr.length);
        System.out.println("第一次：" + Arrays.toString(arr));
        adjustHeap(arr, 0, arr.length);
        System.out.println("第二次：" + Arrays.toString(arr));
        */
        int temp = 0;
        //将无序序列构建成一个堆，根据升降需求选择大顶堆或小顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        //2. 将堆顶元素与末尾元素交换，将最大元素沉到数组末端
        //3. 重新调整结构，使其满足堆定义，然后继续交换堆顶元素和当前末尾元素
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
        //System.out.println("数组=" + Arrays.toString(arr));
    }

    /**
     * 完成将以 i 对应的非叶子节点的树调整成大顶堆
     *
     * @param arr    将要调整的数组
     * @param i      表示非叶子节点的索引
     * @param length 表示多少个元素要调整， length逐渐减小
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        //先取出当前元素的值，保存临时变量
        int temp = arr[i];
        //说明 k = 2*i + 1是i节点的左子节点
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            //说明左子节点的值小于右子节点的值
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;    //指向右子节点
            }
            //如果子节点大于父节点
            if (arr[k] > temp) {
                //把较大的值付给当前节点
                arr[i] = arr[k];
                i = k;  //！！！指向k，继续循环比较
            } else {
                break;
            }
        }
        //当for循环结束后，我们已经将以i为父节点的树的最大值，放在了最顶层（局部）
        arr[i] = temp;
    }
}
