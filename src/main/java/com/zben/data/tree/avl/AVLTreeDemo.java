package com.zben.data.tree.avl;

/**
 * @DESC:平衡二叉树
 * @author: zhouben
 * @date: 2020/9/18 0018 11:26
 */
public class AVLTreeDemo {

    public static void main(String[] args) {
        //int[] arr = {4, 3, 6, 5, 7, 8};
        //int[] arr = {10, 12, 8, 9, 7, 6};
        int[] arr = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        System.out.println("中序遍历~");
        avlTree.infixList();

        System.out.println("树的高度" + avlTree.getRoot().height());
        System.out.println("左子树的高度" + avlTree.getRoot().leftHeight());
        System.out.println("右子树的高度" + avlTree.getRoot().rightHeight());
        System.out.println("root=" + avlTree.getRoot());
        System.out.println("root.left=" + avlTree.getRoot().left);
        System.out.println("root.right=" + avlTree.getRoot().right);
    }

}

class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

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
     * 计算左子树的高度
     *
     * @return
     */
    public int leftHeight() {
        if (this.left == null) {
            return 0;
        }
        return this.left.height();
    }

    /**
     * 计算左子树的高度
     *
     * @return
     */
    public int rightHeight() {
        if (this.right == null) {
            return 0;
        }
        return this.right.height();
    }

    /**
     * 计算已当前节点为根的高度
     *
     * @return
     */
    public int height() {
        return Math.max(this.left == null ? 0 : this.left.height(), this.right == null ? 0 : this.right.height()) + 1;
    }

    /**
     * 左旋转方法
     * 1. 创建一个新的节点 newNode， 值等于当前节点的值
     * 2. 把新的节点的左子树设置当前节点的左子树 newNode.left = left
     * 3. 把新节点的右子树设置为当前节点的右子树的左子树 newNode.right = right.left
     * 4. 把当前节点的值换为右子节点的值   value = right.value
     * 5. 把当前节点的右子树设置成右子树的右子树   right=right.right
     * 6. 把当前节点的左子树设置为新节点   left=newLeft
     */
    public void leftRotate() {
        //创建一个新的节点 newNode， 值等于当前节点的值
        Node newNode = new Node(value);
        //把新的节点的左子树设置当前节点的左子树
        newNode.left = left;
        //把新节点的右子树设置为当前节点的右子树的左子树
        newNode.right = right.left;
        //把当前节点的值换为右子节点的值
        value = right.value;
        //把当前节点的右子树设置成右子树的右子树
        right = right.right;
        //把当前节点的左子树设置为新节点
        left = newNode;
    }

    /**
     * 右旋转方法
     * 1、创建第一个新的节点newNode，值等于当前根节点的值
     * 2、把新节点的右子树设置成当前节点的右子树 newNode.right = right
     * 3、把新节点的左子树设置为当前节点的左子树的右子树  newNode.left = left.right
     * 4、把当前节点的值换为左子节点的值    value = left.value
     * 5、把当前节点的左子树设置成左子树的左子树    left = left.left
     * 6、把当前节点的右子树设置为新节点 right = newNode
     */
    public void rightRotate() {
        //创建第一个新的节点newNode，值等于当前根节点的值
        Node newNode = new Node(value);
        //把新节点的右子树设置成当前节点的右子树
        newNode.right = right;
        //把新节点的左子树设置为当前节点的左子树的右子树
        newNode.left = left.right;
        //把当前节点的值换为左子节点的值
        value = left.value;
        //把当前节点的左子树设置成左子树的左子树
        left = left.left;
        //把当前节点的右子树设置为新节点
        right = newNode;
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

        //如果右子树的高度-左子树的高度 大于1 左旋
        if (rightHeight() - leftHeight() > 1) {
            //如果它的右子树的左子树高度大于它的右子树的右子树高度
            if (right.leftHeight() > right.rightHeight()) {
                //先对当前节点的右节点进行右旋转
                right.rightRotate();
                //再对当前节点进行左旋转操作
                leftRotate();
            } else {
                leftRotate();
            }
            return;
        }

        //如果左子树的高度-右子树的高度 大于1 右旋
        if (leftHeight() - rightHeight() > 1) {
            //如果它的左子树的右子树高度大于它的左子树的左子树高度
            if (left.rightHeight() > left.leftHeight()) {
                //先对当前节点的左节点进行左旋转
                left.leftRotate();
                //再对当前节点进行右旋转操作
                rightRotate();
            } else {
                rightRotate();
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
