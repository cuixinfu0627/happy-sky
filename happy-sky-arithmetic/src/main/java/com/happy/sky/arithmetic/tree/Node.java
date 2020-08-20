package com.happy.sky.arithmetic.tree;

/**
 * @name: Node <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/7 16:29 <tb>
 */
public class Node {
   int data;        //节点的值
   Node node;        //此节点，数据类型为Node
   Node leftChild;        //此节点的左子节点，数据类型为Node
   Node rightChild;       //此节点的右子节点，数据类型为Node

    public Node(int data, Node node, Node leftChild, Node rightChild) {
        this.data = data;
        this.node = node;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public Node(int newData) {
        leftChild = null;
        rightChild = null;
        data = newData;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", node=" + node +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                '}';
    }
}
