package cn.zhengsigen.test.demo.testdemo;

import org.omg.PortableInterceptor.INACTIVE;
import org.omg.PortableInterceptor.NON_EXISTENT;

/**
 * 单项链表
 */
public class ImplementLinked {
    //根节点
    private Node head;
    private Integer size = 0;

    public boolean add(Object object) {
        Node node = new Node();
        node.setData(object);
        if (head != null) {
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(node);
        } else {
            head = node;
        }
        size++;
        return false;
    }

    public boolean add(int index, Object object) {
        if (index < 0 || index > size) {
            System.out.println("插入位置不合法");
            return false;
        }
        int length = 0;
        Node temp = head;
        Node node = new Node();
        node.setData(object);
        while (temp != null) {
            if (index == length) {
                node.setNext(temp.getNext());
                temp.setNext(node);
                size++;
                return true;
            }
            temp = temp.getNext();
            length++;
        }
        return false;
    }

    public boolean addAll(Node node) {
        Node temp = head;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        temp.setNext(node);
        while (node != null) {
            node = node.getNext();
            size++;
        }
        return true;
    }

    public int indexOf(Object o) {
        Node temp = head;
        Integer i = 0;
        while (temp != null) {
            if (temp.getData().equals(o)) {
                return i;
            }
            i++;
            temp = temp.getNext();
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        Node temp = head;
        Integer i = 0;
        while (temp != null) {
            if (temp.getData().equals(o)) {
                return i;
            }
            temp = temp.getNext();
            i++;
        }
        return -1;
    }

    public Object remove(int index) {
        Node temp = head;
        Integer check = 0;
        while (temp != null) {
            if (++check == index) {
                temp.setNext(temp.getNext().getNext());
                size--;
            }
            temp = temp.getNext();
        }
        return null;
    }

    public boolean remove(Object object) {
        Node temp = head;
        while (temp != null) {
            if (temp.getData().equals(object)) {
                temp.setNext(temp.getNext().getNext());
                size--;
            }
            temp = temp.getNext();
        }
        return false;
    }

    public boolean set(int index, Object object) {
        Node node = new Node();
        node.setData(object);
        Node temp = head;
        Integer i = 0;
        while (temp != null) {
            if (i == index) {
                temp.setNext(node);
            }
            i++;
            temp = temp.getNext();
        }
        head = temp;
        return false;
    }

    public boolean contains(Object object) {
        Node temp = head;
        while (temp != null) {
            if (temp.getData().equals(object)) {
                return true;
            }
        }
        return false;
    }

    public Object get(int index) {
        Node temp = head;
        Integer i = 0;
        while (temp != null) {
            if (i == index) {
                return temp.getData();
            }
            i++;
            temp = temp.getNext();
        }
        return null;
    }

    public int size() {
        Node temp = head;
        Integer i = 0;
        while (temp != null) {
            i++;
        }
        return i;
    }

    public Object[] subList(int fromIndex, int endIndex) {
        Object[] newArr = new Object[size];
        Integer index = 0;
        Node temp = head;
        Integer i = 0;
        while (temp != null) {
            if (i >= fromIndex && i <= endIndex) {
                newArr[index++] = temp.getData();
            }
            i++;
        }
        return newArr;
    }

    public Object[] toArray() {
        Object[] newArr = new Object[size];
        Integer index = 0;
        Node temp = head;
        while (temp != null) {
            newArr[index++] = temp.getData();
        }
        return newArr;
    }

    public void lists() {
        System.out.println(size);
        System.out.println(head);
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.getData());
            temp = temp.getNext();
        }
    }
}
