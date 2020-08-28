package com.zben.data.linkedlist;

import java.util.Stack;

/**
 * @DESC:
 * @author: zhouben
 * @date: 2020/8/22 0022 15:18
 */
public class LinkedListDemo {

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "吴用", "智多星");
        HeroNode heroNode3 = new HeroNode(3, "林冲", "豹子头");
        /*singleLinkedList.add(heroNode1);
        singleLinkedList.add(heroNode2);
        singleLinkedList.add(heroNode3);*/

        singleLinkedList.addByOrder(heroNode2);
        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.addByOrder(heroNode3);

        singleLinkedList.show();

        System.out.println("倒叙打印单链表");
        reverseListPrint(singleLinkedList.getHead());

        System.out.println("反转~");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.show();

        //修改节点
        HeroNode newNode = new HeroNode(2, "吴2用", "智多星2");
        singleLinkedList.update(newNode);
        System.out.println("修改后~~");
        singleLinkedList.show();

        //singleLinkedList.del(3);
        //singleLinkedList.del(1);
        System.out.println("删除后~~");
        singleLinkedList.show();

        //测试一下  链表的长度
        System.out.println(getLength(singleLinkedList.getHead()));

        //测试一下  倒数第K个节点
        HeroNode lastIndexNode = getLastIndexNode(singleLinkedList.getHead(), 4);
        System.out.println(lastIndexNode);
    }

    /**
     * 获取链表的长度
     * @return
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        HeroNode temp = head;
        int length = 0;
        while (temp.next != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 获取倒数第index个节点（新浪面试题）
     * @param head
     * @param index
     * @return
     */
    public static HeroNode getLastIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }
        //1. 先获取链表的长度
        int size = getLength(head);
        if (index <= 0 || index > size) {
            return null;
        }
        HeroNode temp = head.next;
        //2. 遍历，size - index
        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 反转链表（腾讯面试题）
     * @param head
     */
    public static void reverseList(HeroNode head) {
        if (head.next == null || head.next.next == null) {
            return; //如果链表为空或者链表只有一个节点
        }
        HeroNode reverseHead = new HeroNode(0,"","");
        HeroNode next ;            //当前节点的下一个节点
        HeroNode cur = head.next;        //辅助变量， 便于遍历指针
        while (cur != null) {
            next = cur.next;            //记录下一个节点
            cur.next = reverseHead.next;    //将cur的下一个节点指向新的链表最前端
            reverseHead.next = cur;    //将cur连接到新的链表上
            cur = next;
        }
        head.next = reverseHead.next;
    }

    /**
     * 倒叙打印单链表
     *  不用反转（会破坏原有的结构）
     *  使用stack，先进后出
     * @param head
     */
    public static void reverseListPrint(HeroNode head) {
        if (head.next == null) {
            return;
        }
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}

class SingleLinkedList {
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点  不考虑排序  直接添加到最后
     * @param node
     */
    public void add(HeroNode node) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
    }

    /**
     * 添加节点  排序
     * @param node
     */
    public void addByOrder(HeroNode node) {
        HeroNode temp = head;
        boolean flag = false; //是否链表中有该节点 标志位
        while (true) {
            if (temp.next == null) {
                break;
            } else if (temp.next.no > node.no) {
                break;
            } else if (temp.next.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("链表中已有该节点 %d, 不能再添加\n", node.no);
            return;
        } else {
            node.next = temp.next;
            temp.next = node;
        }
    }

    /**
     * 根据no修改节点
     * @param node
     */
    public void update(HeroNode node) {
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break; // 到了链表的最后  没有找到
            }
            if (temp.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = node.name;
            temp.nickname = node.nickname;
        } else {
            System.out.printf("没有找到该节点 %d\n", node.no);
        }
    }

    /**
     * 删除节点
     * 找到no的前一个节点
     * @param no
     */
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                System.out.println("链表为空~");
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("没有找到该节点 %d\n", no);
        }
    }

    /**
     * 展示节点
     */
    public void show() {
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
