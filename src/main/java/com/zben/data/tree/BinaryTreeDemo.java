package com.zben.data.tree;

/**
 * @DESC:二叉树
 * ### 10.1.3二叉树的概念
- 树有很多种，每个节点 **最多只能有两个子节点** 的一种形式称为二叉树
- 二叉树的子节点分为左节点和右节点
- 如果该二叉树的所有**叶子节点**都在最后一层，并且节点总数=2^n - 1， n为层数，则称为满二叉树
- 如果该二叉树的所有**叶子节点**都在**最后一层**或者**倒数第二层**，而且最后一层的叶子节点在左边连续
，倒数第二层的叶子节点在右边连续，称为完全二叉树

### 10.1.4 二叉树遍历的说明
- 前序遍历：先输出父节点，再遍历左子树和右子树
- 中序遍历：先遍历左子树，再输出父节点，再遍历右子树
- 后序遍历：先遍历左子树，再遍历右子树，最后输出父节点
 * @author: zhouben
 * @date: 2020/9/12 0012 15:27
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node1 = new HeroNode(2, "吴用");
        HeroNode node2 = new HeroNode(3, "卢俊义");
        HeroNode node3 = new HeroNode(4, "林冲");
        HeroNode node4 = new HeroNode(5, "关胜");
        root.setLeft(node1);
        root.setRight(node2);
        node2.setLeft(node4);
        node2.setRight(node3);
        binaryTree.setRoot(root);

        System.out.println("前序遍历");
        binaryTree.preList();

      /*  System.out.println("中序遍历");
        binaryTree.infixList();

        System.out.println("后序遍历");
        binaryTree.postList();*/

        // binaryTree.preFindById(5); //查询4次
        // binaryTree.infixFindById(5);//查询3次
        //binaryTree.postFindById(5);//查询2次

        binaryTree.delNode(5);
        System.out.println("删除后");
        binaryTree.preList();

    }

}

class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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
}

