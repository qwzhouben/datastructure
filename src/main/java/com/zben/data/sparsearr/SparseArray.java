package com.zben.data.sparsearr;

/**
 * @DESC:稀疏数组
 * @author: zhouben
 * @date: 2020/8/17 0017 17:09
 */
public class SparseArray {

    public static void main(String[] args) {
        //1. 原始二维数组转换为稀疏数组
        int[][] array = new int[11][11];
        array[1][2] = 1;  //1代表黑子， 2代表白子
        array[2][3] = 2;
        array[3][4] = 1;

        int sum = 0;
        for (int[] row : array) {
            for (int data : row) {
                System.out.printf("%d\t", data);
                if (data != 0) {
                    sum++;
                }
            }
            System.out.println();
        }

        //获取原始二维数组里的有效数据
        System.out.println("sum:"+sum);

        //创建稀疏数组
        int[][] sparseArr = new int[sum+1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        int count= 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (array[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = array[i][j];
                }
            }
        }

        //遍历稀疏数组
        for (int[] row : sparseArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //2. 稀疏数组转换成二维数组
        System.out.println("稀疏数组转换成二维数组~~~");
        int array2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];

        for (int i = 1; i < sparseArr.length; i++) {
            array2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        for (int[] row : array2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
