package com.zben.data.recursion;

/**
 * @DESC:迷宫问题
 * @author: zhouben
 * @date: 2020/9/1 0001 16:51
 */
public class MiGong {

    public static void main(String[] args) {
        int[][] arr = new int[8][7];
        //第一行和第七行为墙
        for (int i = 0; i < 7; i++) {
            arr[0][i] = 1;
            arr[7][i] = 1;
        }
        //第一列和第六列为墙
        for (int i = 0; i < 7; i++) {
            arr[i][0] = 1;
            arr[i][6] = 1;
        }
        arr[3][1] = 1;
        arr[3][2] = 1;
        System.out.println("迷宫地图~");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

        setWay(arr, 1, 1);

        System.out.println("行走后的迷宫地图~");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

    }

    /**
     * 使用递归回溯来给小球找路
     * 1、map表示地图
     * 2、i，j表示从地图的哪个位置开始出发（1,1）
     * 3、如果小球能到map[6][5]位置，则说明通路找到
     * 4、约定：当map[i][j]为0表示该点没有走过，当为1表示墙；2表示通路可以走；3表示该点已经走过，但是不通
     * 5、在走迷宫时，需要确定一个策略  下->右->上->左，如果该点不通，再回溯
     * @param map
     * @param i
     * @param j
     * @return 如果找到通路，就返回true，否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            //找到终点
            return true;
        } else {
            if (map[i][j] == 0) { //没有走过
                map[i][j] = 2;
                //使用策略 下->右->上->左
                if (setWay(map, i+1, j)) {
                    return true;
                } else if (setWay(map, i, j+1)) {
                    return true;
                } else if (setWay(map, i-1, j)) {
                    return true;
                } else if (setWay(map, i, j-1)) {
                    return true;
                } else { //说明是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }

}
