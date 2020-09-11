package com.zben.data.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @DESC:二分查找法：数组必须是有序的
 * @author: zhouben
 * @date: 2020/9/8 0008 15:39
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 6, 12, 34, 56, 56, 123, 678};
        int findVal = 56;
        List<Integer> integerList = binarySearch2(arr, 0, arr.length - 1, findVal);
        if (integerList.size() == 0) {
            System.out.println("没有找到~");
        } else {
            System.out.println("找到了，下标是" + integerList);
        }
    }

    /**
     * 数组中的数不重复
     *
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        //当left > right，说明遍历了整个数组也没有找到
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    /**
     * 数组中的数可能有重复
     *
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        //当left > right，说明遍历了整个数组也没有找到
        if (left > right) {
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            //查找左边是不是还有
            List<Integer> indexList = new ArrayList<>();
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                indexList.add(temp);
                temp -= 1;
            }
            indexList.add(mid);
            //查找右边是不是还有
            temp = mid + 1;
            while (true) {
                if (temp < arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                indexList.add(temp);
                temp += 1;
            }
            return indexList;
        }
    }

}
