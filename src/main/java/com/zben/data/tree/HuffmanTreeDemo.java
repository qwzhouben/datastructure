package com.zben.data.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @DESC:赫夫曼树 构建赫夫曼树的步骤
 * 1. 从小到大进行排序，将每一个数据，每一个数据就是一个节点，每一个节点可以看成一颗最简单的二叉树
 * 2. 取出根节点权值最小的两棵二叉树
 * 3. 组成一颗新的二叉树，该新的二叉树的根节点的权值是前面两棵二叉树根节点权值的和
 * 4. 再将这颗新的二叉树，以根节点的权值大小，再次排序，不断重复1,2,3,4发的步骤，知道数列中，所有
 * 的数据都被处理，就得到一颗赫夫曼树
 * @author: zhouben
 * @date: 2020/9/15 0015 15:20
 */
public class HuffmanTreeDemo {

    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node huffmanTree = createHuffmanTree(arr);
        preList(huffmanTree);
    }

    /**
     * 构建一颗赫夫曼树
     *
     * @param arr
     */
    public static Node createHuffmanTree(int[] arr) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            nodes.add(new Node(arr[i]));
        }

        while (nodes.size() > 1) {
            //1. 从小到大进行排序
            Collections.sort(nodes);
            System.out.println("排序后 arr=" + nodes);

            //2. 取出根节点权值最小的两棵二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            //3. 组成一颗新的二叉树，该新的二叉树的根节点的权值是前面两棵二叉树根节点权值的和
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            //再将这颗新的二叉树，以根节点的权值大小，再次排序
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    public static void preList(Node root) {
        if (root == null) {
            System.out.println("空数， 不能遍历");
            return;
        }
        root.preList();
    }

}

class Node implements Comparable<Node> {
    int value;  //权值
    Node left;  //左节点
    Node right; //右节点

    public Node(int value) {
        this.value = value;
    }

    public void preList() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preList();
        }
        if (this.right != null) {
            this.right.preList();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //表示从小到大排序
        return this.value - o.value;
    }
}
