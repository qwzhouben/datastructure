package com.zben.data.huffmancode;

import java.io.*;
import java.util.*;

/**
 * @DESC:赫夫曼编码 1. i like like like java do you like a java
 * 2. d:1 y:1 u:1 j:2 o:2 l:4.....
 * 3. 按照上面字符出现的次数构建一颗赫夫曼树，次数作为权值
 * @author: zhouben
 * @date: 2020/9/15 0015 17:32
 */
public class HuffmanCode {

    public static void main(String[] args) {
        /*String srcFile = "d://1.jpg";
        String desFile = "d://1.zip";
        zipFile(srcFile, desFile);*/
        String desFile = "d://2.jpg";
        String srcFile = "d://1.zip";
        unzipFile(srcFile, desFile);
 /*       String content = "i like like like java do you like a java";
        byte[] strBytes = content.getBytes();*/
        /*List<Node> nodes = getNodes(strBytes);
        System.out.println("nodes=" + nodes);
        //创建赫夫曼树
        Node root = createHuffmanTree(nodes);
        //前序遍历
        preList(root);

        //测试 生成赫夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(root);
        System.out.println("赫夫曼编码=" + huffmanCodes);

        byte[] zip = zip(content.getBytes(), huffmanCodes);*/
        /*byte[] zip = huffmanZip(strBytes);
        System.out.println("压缩后=" + Arrays.toString(zip));

        byte[] decode = decode(huffmanCodes, zip);
        System.out.println("decode = " + new String(decode));*/
    }

    //保存所有节点的赫夫曼编码
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    //编码拼接
    static StringBuilder builder = new StringBuilder();


    /**
     * 压缩文件
     *
     * @param srcFile
     * @param desFile
     */
    public static void zipFile(String srcFile, String desFile) {
        OutputStream os = null;
        ObjectOutputStream oos = null;
        InputStream is = null;

        try {
            //创建文件输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件一样大小的字节数组
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //直接对源文件进行压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建输出流
            os = new FileOutputStream(desFile);
            //创建对象输出流
            oos = new ObjectOutputStream(os);
            //把赫夫曼编码压缩后的字节数组写入文件
            oos.writeObject(huffmanBytes);
            //把赫夫曼编码表写入文件，为了恢复压缩文件用
            oos.writeObject(huffmanCodes);
            System.out.println("压缩成功~");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (oos != null) {

                    oos.close();
                }
                if (os != null) {
                    os.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 解压文件
     * @param srcFile
     * @param dstFile
     */
    public static void unzipFile(String srcFile, String dstFile) {
        OutputStream os = null;
        ObjectInputStream ois = null;
        InputStream is = null;
        try {
            //创建文件输入流
            is = new FileInputStream(srcFile);
            //创建一个和is相关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte[]
            byte[] b = (byte[]) ois.readObject();
            //读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            byte[] bytes = decode(huffmanCodes, b);
            //创建文件输出流
            os = new FileOutputStream(dstFile);
            os.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (os != null) {
                    os.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * @param flag 是否需要补高位
     * @param b    需要转换成字符串的byte
     * @return
     */
    public static String byteToString(boolean flag, Byte b) {
        int temp = b;   //byte转换成了int
        //如果是正数，还需要补高位
        if (flag) {
            temp |= 256;    //按位与 256 = 1 0000 0000 | 0000 0001 => 1 0000 0001
        }
        String str = Integer.toBinaryString(temp);  //返回的是temp对应二进制的补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /**
     * @param huffmanCodes 赫夫曼编码表
     * @param huffmanBytes 赫夫曼得到的字节数组 [-88, -90, ...]
     * @return 原来的字符串对应的数组
     */
    public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //1. 先得到 huffmanBytes 对应的二进制字符串 10101000101111111100100010111...
        boolean flag = true;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            if (i == huffmanBytes.length - 1) {
                flag = false;
            }
            builder.append(byteToString(flag, huffmanBytes[i]));
        }
        System.out.println(builder.toString());
        //把赫夫曼编码表进行调换 a=>100 转换成 100=>a
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        //创建集合 存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < builder.length(); ) {
            int count = 1;
            while (true) {
                String key = builder.substring(i, i + count);
                Byte b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    list.add(b);
                    break;
                }
            }
            i = i + count;
        }

        //把list放入byte[]
        byte[] b = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            b[i] = list.get(i);
        }
        return b;
    }


    /**
     * @param bytes 需要压缩字符串的字节数组
     * @return 返回赫夫曼编码后的字节数组
     */
    public static byte[] huffmanZip(byte[] bytes) {
        //计算字符出现的次数
        List<Node> nodes = getNodes(bytes);
        //创建赫夫曼树
        Node root = createHuffmanTree(nodes);
        //获得赫夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(root);
        return zip(bytes, huffmanCodes);
    }


    /**
     * 编写方法，将字符串对应的byte[]，通过生成的赫夫曼编码，返回一个赫夫曼编码压缩后的byte[]
     *
     * @param bytes        这是原始的字符串对应的byte[]
     * @param huffmanCodes 生成的赫夫曼编码code
     *                     举例：String content = "i like like like java do you like a java"; bytes = content.getBytes()
     *                     返回的字符串 1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100
     *                     =>对应的byte[] huffmanCodeBytes, 即8位对应一个byte 放入huffmanCodeBytes
     *                     huffmanCodeByte[0] = 10101000(补码) => 【推导 10101000 - 1 = 10100111(反码)】 => 11011000(反码取反=原码)
     *                     huffmanCodeBytes[0] = -88
     */
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //利用赫夫曼编码， 把原始的bytes 转成赫夫曼对应的字符串
        StringBuilder builder = new StringBuilder();

        for (byte b : bytes) {
            builder.append(huffmanCodes.get(b));
        }
        //把对应的字符串 按8位一组存到新的byte[]
        int len;
        //计算新的byte长度
        if (builder.length() % 8 == 0) {
            len = builder.length() / 8;
        } else {
            len = builder.length() / 8 + 1;
        }
        //存储压缩后的数组
        byte[] zipBytes = new byte[len];
        int index = 0;  // 压缩后byte[]的下标
        for (int i = 0; i < builder.length(); i += 8) {
            String substring;
            if (i + 8 > builder.length()) {
                substring = builder.substring(i);
            } else {
                substring = builder.substring(i, i + 8);
            }
            //转成二进制
            zipBytes[index] = (byte) Integer.parseInt(substring, 2);
            index++;
        }
        return zipBytes;
    }

    /**
     * 重载getCodes
     *
     * @param root
     * @return
     */
    public static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        //向左递归
        getCodes(root.left, "0", builder);
        //向右递归
        getCodes(root.right, "1", builder);
        return huffmanCodes;
    }

    /**
     * 得到传入的node节点的所有子节点和孙子结点的赫夫曼编码，并放入map集合中
     *
     * @param node    需要处理的节点
     * @param code    向左递归：0， 向右递归：1
     * @param builder 拼接字符串
     */
    public static void getCodes(Node node, String code, StringBuilder builder) {
        StringBuilder builder_ = new StringBuilder(builder);
        //将code拼接
        builder_.append(code);
        if (node != null) { //node == null不处理
            if (node.data == null) {    //说明是非叶子节点
                //向左递归
                getCodes(node.left, "0", builder_);
                //向右递归
                getCodes(node.right, "1", builder_);
            } else {
                huffmanCodes.put(node.data, builder_.toString());
            }
        }

    }

    /**
     * 计算每个字符出现的次数
     *
     * @param strBytes
     * @return
     */
    public static List<Node> getNodes(byte[] strBytes) {
        List<Node> nodes = new ArrayList<>();
        Map<Byte, Integer> map = new HashMap<>();
        //遍历byte， 获取每个字符出现的次数
        for (byte b : strBytes) {
            Integer count = map.get(b);
            if (map.containsKey(b)) {
                map.put(b, count + 1);
            } else {
                map.put(b, 1);
            }
        }
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    /**
     * 创建赫夫曼树
     *
     * @param nodes
     * @return
     */
    public static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //1. 从小到大进行排序
            Collections.sort(nodes);

            //2. 取出根节点权值最小的两棵二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            //3. 组成一颗新的二叉树，该新的二叉树的根节点的权值是前面两棵二叉树根节点权值的和
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            //再将这颗新的二叉树，以根节点的权值大小，再次排序
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    /**
     * 前序遍历
     */
    public static void preList(Node root) {
        if (root == null) {
            System.out.println("空树，不能遍历");
            return;
        }
        root.preList();
    }
}

class Node implements Comparable<Node> {

    Byte data;     //字符
    int weight; //权值（字符出现的次数）
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
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
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}
