package com.happy.sky.arithmetic;

import java.util.*;

/**
 * @name: RealCaseDemo <tb>
 * @title:  17 | 真题案例（二）：数据结构训练  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/7 15:41 <tb>
 */
public class DataStructureCase {
    private int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    private static List<Node> nodeList = null;

    public static void main(String[] args) {
        // 例题 1：反转字符串中的单词
        String ss = "This is a   good example";
        String[] s = ss.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i=s.length-1; i >= 0;i--){
            sb = sb.append(s[i] +" ") ;
        }
        System.out.println(sb);
        System.out.println(sb.toString().replaceAll("   "," "));
        System.out.println(reverseWords(ss));

        // 例题 2：树的层序遍历
       int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        List<Node> nodeList = null;
        nodeList = createBinTree(array,nodeList);
        // nodeList中第0个索引处的值即为根节点
        Node root = nodeList.get(0);
        levelTraverse(root);

        // 例题 3：查找数据流中的中位数
        DataStructureCase t = new DataStructureCase();
        t.Insert(1);
        t.Insert(2);
        t.Insert(0);
        t.Insert(20);
        t.Insert(10);
        t.Insert(22);

    }

    /**
     * @title: 例题 1：反转字符串中的单词 <tb>
     * @params: a  <tb
     */
    private static String reverseWords(String s) {
        Stack stack=new Stack();
        String temp = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                temp += s.charAt(i);
            }
            else if (temp != ""){
                stack.push(temp);
                temp = "";
            }
            else{
                continue;
            }
        }
        if (temp != ""){
            stack.push(temp);
        }
        String result = "";
        while (!stack.empty()){
            result += stack.pop() + " ";
        }
        return result.substring(0,result.length()-1);
    }

    /**
     * @title: 例题 2：树的层序遍历 <tb>
     * @params: root  <tb
     */
    public static void levelTraverse(Node root) {
        LinkedList<Node> queue = new LinkedList<Node>();
        Node current = null;
        queue.offer(root); // 根结点入队
        while (!queue.isEmpty()) {
            current = queue.poll(); // 出队队头元素
            System.out.print(current.data);
            // 左子树不为空，入队
            if (current.leftChild != null)
                queue.offer(current.leftChild);
            // 右子树不为空，入队
            if (current.rightChild != null)
                queue.offer(current.rightChild);
        }
    }

    private static class Node {
        Node leftChild;
        Node rightChild;
        int data;

        Node(int newData) {
            leftChild = null;
            rightChild = null;
            data = newData;
        }
    }
    public static List<Node> createBinTree(int[] array, List<Node> nodeList) {
        nodeList = new LinkedList<Node>();
        // 将一个数组的值依次转换为Node节点
        for (int nodeIndex = 0; nodeIndex < array.length; nodeIndex++) {
            nodeList.add(new Node(array[nodeIndex]));
        }
        // 对前lastParentIndex-1个父节点按照父节点与孩子节点的数字关系建立二叉树
        for (int parentIndex = 0; parentIndex < array.length / 2 - 1; parentIndex++) {
            // 左孩子
            nodeList.get(parentIndex).leftChild= nodeList
                    .get(parentIndex * 2 + 1);
            // 右孩子
            nodeList.get(parentIndex).rightChild = nodeList
                    .get(parentIndex * 2 + 2);
        }
        // 最后一个父节点:因为最后一个父节点可能没有右孩子，所以单独拿出来处理
        int lastParentIndex = array.length / 2 - 1;
        // 左孩子
        nodeList.get(lastParentIndex).leftChild = nodeList
                .get(lastParentIndex * 2 + 1);
        // 右孩子,如果数组的长度为奇数才建立右孩子
        if (array.length % 2 == 1) {
            nodeList.get(lastParentIndex).rightChild = nodeList
                    .get(lastParentIndex * 2 + 2);
        }
        return nodeList;
    }

    /**
     * @title: 例题 3：查找数据流中的中位数 <tb>
     * @params: root  <tb
     */
    int count = 0;
    static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    });
    public void Insert(Integer num) {
        if (count % 2 == 0) {
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        } else {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        }
        count++;
        System.out.println(DataStructureCase.GetMedian());
    }
    public static int GetMedian() {
        return maxHeap.peek();
    }

}
