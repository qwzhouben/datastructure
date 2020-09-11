package com.zben.data.hashtab;

import java.util.Scanner;

/**
 * @DESC:散列
 * @author: zhouben
 * @date: 2020/9/11 0011 16:02
 */
public class HashTabDemo {

    public static void main(String[] args) {
        HashTab hashTab = new HashTab(8);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("add :  添加");
            System.out.println("list:  展示");
            System.out.println("find:  查找");
            System.out.println("del:   删除");
            System.out.println("exit:  退出");
            String key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int i = scanner.nextInt();
                    System.out.println("输入名字");
                    String next = scanner.next();
                    Emp emp = new Emp(i, next);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("输入要查找的雇员id");
                    int j = scanner.nextInt();
                    hashTab.findById(j);
                    break;
                case "del":
                    System.out.println("输入要删除的雇员id");
                    int id = scanner.nextInt();
                    hashTab.delById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }

    }

}

class HashTab {
    private int maxSize;
    private EmpLinkedList[] empLinkedLists;

    public HashTab(int maxSize) {
        this.maxSize = maxSize;
        this.empLinkedLists = new EmpLinkedList[maxSize];
        for (int i = 0; i < maxSize; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    /**
     * 添加雇员
     *
     * @param emp
     */
    public void add(Emp emp) {
        int index = index(emp.id);
        empLinkedLists[index].add(emp);
    }

    /**
     * 展示
     */
    public void list() {
        for (int i = 0; i < empLinkedLists.length; i++) {
            EmpLinkedList empLinkedList = empLinkedLists[i];
            empLinkedList.list(i);
        }
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    public void findById(int id) {
        int index = index(id);
        Emp emp = empLinkedLists[index].findById(id);
        if (emp == null) {
            System.out.println("没有找到！");
            return;
        }
        System.out.println("在第" + (index + 1) + "个链表中找到了\t id=" + id + "name=" + emp.name);
    }

    /**
     * 根据id删除
     * @param id
     */
    public void delById(int id) {
        int index = index(id);
        empLinkedLists[index].delById(id);
    }

    /**
     * 计算散列值
     *
     * @param id
     * @return
     */
    public int index(int id) {
        return id % maxSize;
    }
}

/**
 * 表示链表
 */
class EmpLinkedList {
    private Emp head;

    /**
     * 添加雇员
     *
     * @param emp
     */
    public void add(Emp emp) {
        //如果是添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    /**
     * 展示链表
     */
    public void list(int no) {
        if (head == null) {
            System.out.println("第 " + (no + 1) + " 个链表为空!");
            return;
        }
        Emp curEmp = head;
        while (true) {
            System.out.print("第" + (no + 1) + "个链表\t");
            System.out.printf("id=>%d, name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    public Emp findById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                break;
            }
            //找到最后也没有找到 退出
            if (curEmp.next == null) {
                curEmp = null;
                break;
            }

            curEmp = curEmp.next;
        }
        return curEmp;
    }

    /**
     * 根据id删除
     * @param id
     */
    public void delById(int id) {
        if (head == null) {
            System.out.println("链表为空");
        }
        Emp curEmp = head;
        if (curEmp.id == id) {
            head = curEmp.next;
            return;
        }
        while (true) {
            if (curEmp.next == null) {
                System.out.println("不存在该id");
                break;
            }
            if (curEmp.next.id == id) {
                curEmp.next = curEmp.next.next;
                break;
            }
            curEmp = curEmp.next;
        }
    }

    public Emp getHead() {
        return head;
    }

    public void setHead(Emp head) {
        this.head = head;
    }
}

/**
 * 雇员
 */
class Emp {

    public int id;
    public String name;
    public Emp next; //默认是null

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}