package com.zben.data.tree;

/**
 * @DESC:顺序存储二叉树 ### 10.2.1 顺序存储二叉树的概念
 * - 从数据存储来看，数组存储方式和数的存储方式可以相互转换，即**数组可以转换成树，树也可以转换成数组**
 * - 顺序存储二叉树的特点
 * 1. 顺序二叉树通常只考虑完全二叉树
 * 2. 第n个元素的左子节点为 2*n + 1
 * 3. 第n个元素的右子节点为 2*n + 2
 * 4. 第n个元素的父节点为   (n-1)/2
 * @author: zhouben
 * @date: 2020/9/14 0014 10:20
 */
public class ArrBinaryTreeDemo {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preList();    //1,2,4,5,3,6,7
        System.out.println();
        arrBinaryTree.infixList();  //4,2,5,1,6,3,7
        System.out.println();
        arrBinaryTree.postList();   //4,5,2,6,7,3,1
    }

}

class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preList() {
        this.preList(0);
    }

    public void infixList() {
        this.infixList(0);
    }

    public void postList() {
        this.postList(0);
    }

    /**
     * 前序遍历
     *
     * @param index 数组的下标
     */
    public void preList(int index) {
        if (this.arr == null || this.arr.length == 0) {
            System.out.println("数组为空 不能遍历");
            return;
        }
        System.out.print(arr[index] + ", ");
        //向左递归
        if ((2 * index + 1) < arr.length) {
            preList(2 * index + 1);
        }
        //向右递归
        if ((2 * index + 2) < arr.length) {
            preList(2 * index + 2);
        }
    }

    /**
     * 中序遍历
     *
     * @param index 数组的下标
     */
    public void infixList(int index) {
        if (this.arr == null || this.arr.length == 0) {
            System.out.println("数组为空 不能遍历");
            return;
        }
        //向左递归
        if ((2 * index + 1) < arr.length) {
            infixList(2 * index + 1);
        }
        System.out.print(arr[index] + ", ");

        //向右递归
        if ((2 * index + 2) < arr.length) {
            infixList(2 * index + 2);
        }
    }

    /**
     * 后序遍历
     *
     * @param index 数组的下标
     */
    public void postList(int index) {
        if (this.arr == null || this.arr.length == 0) {
            System.out.println("数组为空 不能遍历");
            return;
        }
        //向左递归
        if ((2 * index + 1) < arr.length) {
            postList(2 * index + 1);
        }
        //向右递归
        if ((2 * index + 2) < arr.length) {
            postList(2 * index + 2);
        }
        System.out.print(arr[index] + ", ");
    }
}