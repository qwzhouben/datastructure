package com.zben.data.linkedlist;

/**
 * @DESC: 双向链表
 * @author: zhouben
 * @date: 2020/8/27 0027 10:08
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        HeroNode2 heroNode1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 heroNode2 = new HeroNode2(2, "吴用", "智多星");
        HeroNode2 heroNode3 = new HeroNode2(3, "林冲", "豹子头");
        doubleLinkedList.add(heroNode1);
        doubleLinkedList.add(heroNode2);
        doubleLinkedList.add(heroNode3);
        System.out.println("显示双向链表");
        doubleLinkedList.show();

        System.out.println("按顺序添加`");
        HeroNode2 heroNode7 = new HeroNode2(7, "鲁智深", "花和尚");
        HeroNode2 heroNode4 = new HeroNode2(4, "卢俊义", "云麒麟");
        doubleLinkedList.addByOrder(heroNode7);
        doubleLinkedList.addByOrder(heroNode4);
        doubleLinkedList.show();

        System.out.println("修改~");
        HeroNode2 newHeroNode = new HeroNode2(3, "卢俊义", "云麒麟");
        doubleLinkedList.update(newHeroNode);
        doubleLinkedList.show();

        System.out.println("删除~");
        doubleLinkedList.del(3);
        doubleLinkedList.show();
    }
}

class DoubleLinkedList {

    public HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    /**
     * 添加节点  不考虑排序  直接添加到最后
     * @param node
     */
    public void add(HeroNode2 node) {
        HeroNode2 temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;
    }

    /**
     * 添加节点  排序
     * @param node
     */
    public void addByOrder(HeroNode2 node) {
        HeroNode2 temp = head;
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
            if (temp.next != null) {
                node.next = temp.next;
                temp.next.pre = node;
            }
            node.pre = temp;
            temp.next = node;
        }
    }

    /**
     * 根据no修改节点
     * @param node
     */
    public void update(HeroNode2 node) {
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        HeroNode2 temp = head.next;
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
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                System.out.println("链表为空~");
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
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
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

}

class HeroNode2 {

    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
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