package com.zben.data.tree.threaded;

/**
 * @DESC:线索化二叉树 1. n个节点的二叉链表中含有n+1 【公式 2n-(n-1)=n+1】个空指针域。利用二叉链表的空指针域，存放指向该节点在某种
 * 遍历次序下的前驱和后继节点的指针 (这种附加的指针称为"线索")
 * 2. 这种加上了线索的二叉链表称为线索链表，相应的二叉树称为线索二叉树（Threaded BinaryTree）。根据线索性质不同
 * ，线索二叉树可分为前序二叉树、中序二叉树和后序二叉树
 * 3. 一个节点的前一个节点，称为前驱节点
 * 4. 一个节点的后一个节点，称为后继节点
 * @author: zhouben
 * @date: 2020/9/14 0014 11:36
 */
public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {

    }

}

class BinaryTree {
    private HeroNode root;

    //为了实现线索化，需要创建指向当前节点的前驱节点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    /**
     * 对二叉树进行线索化
     *
     * @param node 需要线索化的节点
     */
    public void threadedNodes(HeroNode node) {
        if (node == null) {
            //节点为空 不能线索化
            return;
        }

        //先线索化左子树
        threadedNodes(node.getLeft());
        //线索化
        if (node.getLeft() == null) {
            //让当前节点的左指针指向前驱
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && node.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        //每处理一个节点后，让当前节点是下个节点的前驱节点
        pre = node;
        //线索化右子树
        threadedNodes(node.getRight());
    }

    public void preList() {
        if (root != null) {
            root.preList();
        } else {
            System.out.println("二叉树为空");
        }
    }

    public void infixList() {
        if (root != null) {
            root.infixList();
        } else {
            System.out.println("二叉树为空");
        }
    }

    public void postList() {
        if (root != null) {
            root.postList();
        } else {
            System.out.println("二叉树为空");
        }
    }

    public void preFindById(int no) {
        if (root != null) {
            HeroNode heroNode = root.preFindById(no);
            if (heroNode == null) {
                System.out.printf("没有找到编号为%d的英雄", no);
            } else {
                System.out.printf("找到了 name=%s", heroNode.getName());
            }
        }
    }

    public void infixFindById(int no) {
        if (root != null) {
            HeroNode heroNode = root.infixFindById(no);
            if (heroNode == null) {
                System.out.printf("没有找到编号为%d的英雄", no);
            } else {
                System.out.printf("找到了 name=%s", heroNode.getName());
            }
        }
    }

    public void postFindById(int no) {
        if (root != null) {
            HeroNode heroNode = root.postFindById(no);
            if (heroNode == null) {
                System.out.printf("没有找到编号为%d的英雄", no);
            } else {
                System.out.printf("找到了 name=%s", heroNode.getName());
            }
        }
    }

    /**
     * 删除节点
     *
     * @param no
     */
    public void delNode(int no) {
        if (root == null) {
            System.out.println("root不能为空");
        } else {
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delNode(no);
            }
        }
    }
}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    private int leftType;   //0:表示指向左子树， 1：表示指向前驱节点
    private int rightType;  //0:表示指向右子树， 1：表示指向后继节点

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    /**
     * 前序查找
     *
     * @param no
     * @return
     */
    public HeroNode preFindById(int no) {
        System.out.println("前序查找");
        if (this.no == no) {
            return this;
        }
        //向左遍历
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preFindById(no);
            if (resNode != null) {
                return resNode;
            }
        }
        //向右遍历
        if (this.right != null) {
            resNode = this.right.preFindById(no);
            if (resNode != null) {
                return resNode;
            }
        }
        return resNode;
    }

    /**
     * 中序查找
     *
     * @param no
     * @return
     */
    public HeroNode infixFindById(int no) {
        //向左遍历
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixFindById(no);
            if (resNode != null) {
                return resNode;
            }
        }
        System.out.println("中序查找");
        if (this.no == no) {
            return this;
        }
        //向右遍历
        if (this.right != null) {
            resNode = this.right.infixFindById(no);
            if (resNode != null) {
                return resNode;
            }
        }
        return resNode;
    }

    /**
     * 后序查找
     *
     * @param no
     * @return
     */
    public HeroNode postFindById(int no) {
        //向左遍历
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postFindById(no);
            if (resNode != null) {
                return resNode;
            }
        }
        //向右遍历
        if (this.right != null) {
            resNode = this.right.postFindById(no);
            if (resNode != null) {
                return resNode;
            }
        }
        System.out.println("后序查找");
        if (this.no == no) {
            return this;
        }
        return resNode;
    }

    /**
     * 前序遍历
     */
    public void preList() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preList();
        }
        if (this.right != null) {
            this.right.preList();
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

    /**
     * 后序遍历
     */
    public void postList() {
        if (this.left != null) {
            this.left.postList();
        }
        if (this.right != null) {
            this.right.postList();
        }
        System.out.println(this);
    }

    /**
     * 删除节点
     * 1. 因为我们的二叉树是单向的，所有我们是判断当前节点的子节点是否需要删除节点
     * 2. 如果当前节点的左子节点不为空，并且左子节点就是要删除的节点，就将this.left=null,并且返回
     * 3. 如果当前节点的右子节点不为空，并且右子节点就是要删除的节点，将this.right=null,并且返回
     * 4. 如果第2和第3步没有删除节点，那么我们就需要向左子树进行递归删除
     * 5. 如果第4步也没有删除节点，则应当向右子树进行递归删除
     *
     * @param no
     */
    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }
}
