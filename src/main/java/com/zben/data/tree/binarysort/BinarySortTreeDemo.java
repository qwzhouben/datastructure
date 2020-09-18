package com.zben.data.tree.binarysort;

/**
 * @DESC:
 * @author: zhouben
 * @date: 2020/9/17 0017 15:47
 */
public class BinarySortTreeDemo {

    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree sortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            sortTree.add(new Node(arr[i]));
        }

        System.out.println("二叉排序树 中序遍历");
        sortTree.infixList();
        System.out.println();
        //  sortTree.delNode(2);
        //  sortTree.delNode(1);
        //  sortTree.delNode(12);
        //  sortTree.delNode(1);
        sortTree.delNode(7);
        sortTree.infixList();
    }

}

/**
 * 二叉排序树
 */
class BinarySortTree {
    private Node root;

    /**
     * 添加节点
     *
     * @param node
     */
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 查找要删除的节点
     *
     * @param value
     * @return
     */
    public Node search(int value) {
        if (root == null) {
            return null;
        }
        return root.search(value);
    }

    /**
     * 查找要删除节点的父节点
     *
     * @param value
     * @return
     */
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        }
        return root.searchParent(value);
    }

    /**
     * @param node 以node为根节点， 向右子树查找最小的节点
     * @return 返回最小节点的值
     */
    public int delRightTreeMin(Node node) {
        Node temp = node;
        while (temp.left != null) {
            temp = temp.left;
        }
        //temp就是最小的节点
        //删除最小的节点，并将value返回
        delNode(temp.value);
        return temp.value;
    }

    /**
     * 删除节点 思路：
     * 第一种情况：删除叶子节点
     * 1、需要先去找到要删除的节点 targetNode
     * 2、找到targetNode的父节点 parent
     * 3、确定targetNode是parent的左子节点还是右子节点
     * 4、根据前面的情况来对应删除
     * <p>
     * 第二种情况：删除只有一颗子树的节点
     * 1、需要先去找到要删除的节点 targetNode
     * 2、找到targetNode的父节点 parent
     * 3、确定targetNode的子节点是左子节点还是右子节点
     * 4、targetNode是parent的左子节点还是右子节点
     * 5、如果targetNode有左子节点
     * <p>
     * 第三种情况：删除有两棵子树的节点
     * 1、需要先去找到要删除的节点 targetNode
     * 2、找到targetNode的父节点 parent
     * 3、从targetNode的右子树找到最小的节点
     * 4、用一个临时变量，将最小节点的值保存 temp = 11
     * 5、删除该最小节点 并 targetNode.value = temp
     *
     * @param value
     * @return
     */
    public void delNode(int value) {
        if (root == null) {
            System.out.println("空树~");
            return;
        } else {
            //查询当前节点
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            Node parent = searchParent(value);
            //1. 如果当前节点的左子节点和右子节点都为空 说明是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                // 如果当前节点是父节点的左子节点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                //2. 如果当前节点的左右子节点都不为空
                int minValue = delRightTreeMin(targetNode);
                targetNode.value = minValue;
            } else {
                //3. 如果当前节点只有一个子树
                if (targetNode.left != null) {//如果是左子树不为空
                    if (parent != null) {
                        if (parent.left.value == targetNode.value) { //如果targetNode是parent的左子节点
                            //如果targetNode的左子树不为空
                            parent.left = targetNode.left;
                        } else {
                            parent.right = targetNode.left;

                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {
                    if (parent != null) {
                        if (parent.right.value == targetNode.value) { //如果targetNode是parent的左子节点
                            //如果targetNode的左子树不为空
                            parent.left = targetNode.left;
                        } else {
                            parent.left = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                }
            }
        }

    }

}

    /**
     * 中序遍历
     */
    public void infixList() {
        if (root == null) {
            System.out.println("二叉排序树为空~");
        } else {
            root.infixList();
        }
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 查询value值的父节点
     *
     * @param value
     * @return
     */
    public Node searchParent(int value) {
        //如果当前节点就是要删除的节点的父节点 返回
        if ((this.left != null && this.left.value == value)
                || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找的值小于当前节点，并且当前节点的左子树不为空
            if (this.left != null && value < this.value) {
                return this.left.searchParent(value);
            } else if (this.right != null && value >= this.value) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }

    /**
     * 查询当前节点
     *
     * @param value
     * @return
     */
    public Node search(int value) {
        if (this.value == value) {
            return this;
        } else if (this.left != null && value < this.value) {
            //查找的节点小于当前节点  向左递归
            return this.left.search(value);
        } else if (this.right != null && value >= this.value) {
            return this.right.search(value);
        } else {
            return null;
        }
    }

    /**
     * 添加节点
     *
     * @param node
     */
    public void add(Node node) {
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void infixList() {
        if (this.left != null) {
            this.left.infixList();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixList();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}