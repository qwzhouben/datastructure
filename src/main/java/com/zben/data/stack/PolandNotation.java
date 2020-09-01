package com.zben.data.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @DESC:逆波兰表达式（后缀表达式）
 * @author: zhouben
 * @date: 2020/8/30 0030 10:54
 */
public class PolandNotation {

    public static void main(String[] args) {

        //例如 中缀表达式："1+((2+3)*4)-5" --> 后缀表达式： "1 2 3 + 4 * + 5 -"
        String inExp = "1+((2+3)*4)-5";
        List<String> inFixExpList = strToList(inExp);
        System.out.println("中缀表达式：" + inFixExpList);
        //中缀表达式转换成后缀表达式
        List<String> suffixExpList = inFixToSuffixExp(inFixExpList);
        System.out.println("后缀表达式：" + suffixExpList);
        //计算
        int res = cal(suffixExpList);
        System.out.println("计算结果为：" + res);

        /**
         * 后缀表达式步骤
         * 1. 从左至右扫描，将3和4压入堆栈
         * 2. 遇到+运算符，因此弹出3和4（4为栈顶元素，3位次顶元素），计算出3+4的值，得7，将7入栈
         * 3. 将5入栈
         * 4. 接下来是*运算符，因此弹出5和7，计算出7*5=35，将35入栈
         * 5. 将6入栈
         * 6. 最后是-运算符，计算出35-6的值，由此的出最终结果
         * @param args
         */
        //(3+4)*5-6
/*        String suffixExp = "3 4 + 5 * 6 -";
        String[] split = suffixExp.split(" ");
        List<String> list = new ArrayList<>();
        for (String s : split) {
            list.add(s);
        }
        System.out.println("list:" + list);

        int res = cal(list);
        System.out.println(res);*/
    }

    /**
     * 中缀表达式转换成后缀表达式
     * 1. 初始化两个栈：运算符栈s1和储存中间结果的栈s2
     * 2. 从左至右扫描中缀表达式
     * 3. 遇到操作数时，将其压入s2
     * 4. 遇到运算符时，比较其与s1栈顶运算符的优先级：
     * 4.1. 如果s1为空，或栈顶运算符为左括号“(”，则直接将运算符入s1栈
     * 4.2. 否则，若优先级比栈顶运算符的高，也将运算符压入s1；
     * 4.3. 否则，将s1栈顶的运算符弹出并压入到s2中，再次转到4.1 与s1中新的栈顶运算符相比较
     * 5. 遇到括号时：
     * 5.1. 如果是左括号“(”，则直接压入s1
     * 5.2. 如果是“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
     * 6. 重复步骤2至5，直到表达式的最右边
     * 7. 将s1中剩余的运算符依次弹出并压入s2
     * 8. 依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
     */
    private static List<String> inFixToSuffixExp(List<String> inFixExpList) {
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>(); // 因为需要把s2倒序， 用list方便

        for (String str : inFixExpList) {
            if (str.matches("\\d+")) { //如果是操作数，直接压入s2
                s2.add(str);
            } else if ("(".equals(str)) {   //如果是"(",直接压入s1
                s1.push(str);
            } else if (")".equals(str)) {   //如果是")", 则依次弹出s1栈顶的运算符，并压入s2，遇到左括号为止，一对括号丢弃
                while (!"(".equals(s1.peek())) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {                        //遇到运算符
                //
                while (s1.size() > 0 && Opearator.getPriority(str) <= Opearator.getPriority(s1.peek())) {
                    s2.add(s1.pop());
                }
                s1.push(str);
            }
        }
        //将剩下的压入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    /**
     * 将中缀表达式字符串转成list
     *
     * @param exp
     * @return
     */
    public static List<String> strToList(String exp) {
        int index = 0; //指针 便于遍历字符串
        List<String> list = new ArrayList<>();
        char c;
        String str = "";
        do {
            //如果不是数字,直接加入list
            if ((c = exp.charAt(index)) > 57 || (c = exp.charAt(index)) < 48) {
                list.add("" + c);
                index++;
            } else {
                str = "";
                //如果是数字 就需要考虑多位数
                while (index < exp.length() && (c = exp.charAt(index)) >= 48 && (c = exp.charAt(index)) <= 57) {
                    str += c;
                    index++;
                }
                list.add(str);
            }

        } while (index < exp.length());
        return list;
    }

    private static int cal(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String item : list) {
            if (item.matches("\\d+")) {
                stack.push(item);
                continue;
            }
            int res = 0;
            int num2 = Integer.parseInt(stack.pop());
            int num1 = Integer.parseInt(stack.pop());
            if ("+".equals(item)) {
                res = num1 + num2;
            } else if ("-".equals(item)) {
                res = num1 - num2;
            } else if ("*".equals(item)) {
                res = num1 * num2;
            } else if ("/".equals(item)) {
                res = num1 / num2;
            } else {
                throw new RuntimeException("运算符有误~");
            }
            stack.push("" + res);
        }
        return Integer.parseInt(stack.pop());
    }


}

class Opearator {

    public static int ADD = 1;
    public static int SUB = 1;
    public static int MUL = 2;
    public static int DIV = 2;

    public static int getPriority(String oper) {
        int res = 0;
        switch (oper) {
            case "+":
                res= ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
            default:
                System.out.println("运算符不合法~");
                break;
        }
        return res;
    }
}