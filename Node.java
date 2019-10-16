package cn.zhengsigen.test.demo.testdemo;

public class Node { //链表节点
    private Object nodeData; //节点数据
    private Node preNode; //前驱节点的引用
    private Node nextNode; //后继节点的引用

    public Object getNodeData() {
        return nodeData;
    }

    public void setNodeData(Object nodeData) {
        this.nodeData = nodeData;
    }

    public Node getPreNode() {
        return preNode;
    }

    public void setPreNode(Node preNode) {
        this.preNode = preNode;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public Node() {
    }

    public Node(Object nodeData) {
        this.nodeData = nodeData;
    }
}
