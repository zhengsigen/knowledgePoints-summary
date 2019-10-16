package cn.zhengsigen.test.demo.testdemo;

public class ImplementDoubleLinked implements DSList {
    private int size = 0; //链表的初始长度
    private Node head; //链表的头指针,指向链表头部
    private Node last; //链表的尾指针,指向链表尾部

    @Override
    public boolean add(Object object) {
        Node newNode = new Node(object); //将节点数据实例化为一个节点对象
        if (size == 0) { //当前链表为空链表
            head = newNode;
            last = newNode;
        } else {
            newNode.setPreNode(last);
            last.setNextNode(newNode);
            last = newNode;
        }
        size++;
        return true;
    }

    @Override
    public boolean add(int index, Object object) {
        Node node = new Node(object);
        Node temp = head;
        Integer check = 0;
        while (temp != null) {
            if (check == index) {
                node.setPreNode(temp.getPreNode());
                node.setNextNode(temp);
                temp.getPreNode().setNextNode(node);
                temp.setPreNode(node);
                size++;
                return true;
            }
            check++;
            temp = temp.getNextNode();
        }
        return false;
    }

    @Override
    public boolean addAll(Object[] object) {
        return true;
    }

    @Override
    public int indexOf(Object o) {
        Node temp = head;
        Integer index = 0;
        while (temp != null) {
            if (temp.getNodeData().equals(o)) {
                return index;
            }
            temp = temp.getNextNode();
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node temp = last;
        Integer index = size - 1;
        while (temp != null) {
            if (temp.getNodeData().equals(o)) {
                return index;
            }
            index--;
            temp = temp.getPreNode();
        }
        return 0;
    }

    @Override
    public Object remove(int index) {
        Node temp = head;
        Integer check = 0;
        while (temp != null) {
            if (check == index) {
                temp.getPreNode().setNextNode(temp.getNextNode());
                temp.getNextNode().setPreNode(temp.getPreNode());
                size--;
                return temp.getNodeData();
            }
            check++;
            temp = temp.getNextNode();
        }
        return null;
    }

    @Override
    public boolean remove(Object object) {
        Node temp = head;
        while (temp != null) {
            if (temp.getNodeData().equals(object)) {
                temp.getPreNode().setNextNode(temp.getNextNode());
                temp.getNextNode().setPreNode(temp.getPreNode());
                size--;
                return true;
            }
            temp = temp.getNextNode();
        }
        return false;
    }

    @Override
    public boolean set(int index, Object object) {
        Node node = new Node(object);
        Node temp = head;
        Integer check = 0;
        while (temp != null) {
            if (check == index) {
                node.setNextNode(temp.getNextNode());
                node.setPreNode(temp.getPreNode());
                temp.getPreNode().setNextNode(node);
                return true;
            }
            check++;
            temp = temp.getNextNode();
        }
        return false;
    }

    @Override

    public boolean contains(Object object) {
        Node temp = head;
        while (temp != null) {
            if (temp.getNodeData().equals(object)) {
                return true;
            }
            temp = temp.getNextNode();
        }
        return false;
    }

    @Override
    public Object get(int index) {
        Node temp = head;
        Integer check = 0;
        while (temp != null) {
            if (check == index) {
                return temp.getNodeData();
            }
            check++;
            temp = temp.getNextNode();
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] subList(int fromIndex, int endIndex) {
        Object[] arr = new Object[size];
        Integer inde = 0;
        Node temp = head;
        Integer index = 0;
        while (temp != null) {
            if (index >= fromIndex && index < endIndex) {
                arr[inde++] = temp.getNodeData();
            }
            index++;
            temp = temp.getNextNode();
        }
        Object[] newArr = new Object[inde];
        for (int i = 0; i < inde; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    @Override
    public Object[] toArray() {
        Node temp = head;
        Object[] newArr = new Object[size];
        Integer index = 0;
        while (temp != null) {
            newArr[index++] = temp.getNodeData();
            temp = temp.getNextNode();
        }
        return newArr;
    }
}
