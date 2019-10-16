//package cn.zhengsigen.test.demo.testdemo;
//
//import org.omg.PortableInterceptor.INACTIVE;
//import org.omg.PortableInterceptor.NON_EXISTENT;
//
//import java.util.Stack;
//
///**
// * 单项链表
// */
//public class ImplementLinked implements DSList {
//    //根节点
//    private Node head;
//    private Integer size = 0;
//
//    public boolean add(Object object) {
//        Node node = new Node();
//        node.setData(object);
//        if (head != null) {
//            Node temp = head;
//            while (temp.getNext() != null) {
//                temp = temp.getNext();
//            }
//            temp.setNext(node);
//        } else {
//            head = node;
//        }
//        size++;
//        return false;
//    }
//
//    public boolean add(int index, Object object) {
//        int length = 1;
//        Node temp = head;
//        Node node = new Node();
//        node.setData(object);
//        while (temp != null) {
//            if (index == length) {
//                node.setNext(temp.getNext());
//                temp.setNext(node);
//                size++;
//                return true;
//            }
//            length++;
//            temp = temp.getNext();
//        }
//        return false;
//    }
//
//    @Override
//    public boolean addAll(Object[] object) {
//        return false;
//    }
//
//    public boolean addAll(Node node) {
//        Node temp = head;
//        while (temp.getNext() != null) {
//            temp = temp.getNext();
//        }
//        temp.setNext(node);
//        while (node != null) {
//            node = node.getNext();
//            size++;
//        }
//        return true;
//    }
//
//    public int indexOf(Object o) {
//        Node temp = head;
//        Integer i = 0;
//        while (temp != null) {
//            if (temp.getData().equals(o)) {
//                return i;
//            }
//            i++;
//            temp = temp.getNext();
//        }
//        return -1;
//    }
//
//    public int lastIndexOf(Object o) {
//        Stack<Node> stack = new Stack<>();
//        Node cur = head;
//        //将链表的所有节点压入
//        // 栈中
//        while (cur != null) {
//            stack.push(cur);
//            cur = cur.getNext();//这样就可以压入下一个节点
//        }
//        //将栈中的节点获取
//        while (stack.size() > 0) {
//            Node temp = stack.pop();
//            Node pop = temp;
//            int del = 0;
//            //查看目标节点长度
//            while (temp != null) {
//                temp = temp.getNext();
//                del++;
//            }
//            //初始化地址由总长度减去目标节点长度
//            int index = size - del;
//            while (pop != null) {
//                if (pop.getData().equals(o)) {
//                    return index;
//                }
//                pop = pop.getNext();
//                index--;
//            }
//        }
//        return -1;
//    }
//
//    public Object remove(int index) {
//        Node temp = head;
//        Node nextTemp = temp.getNext();
//        int check = 0;
//        while (nextTemp != null) {
//            if (index == check) {
//                Object o = nextTemp.getData();
//                temp.setNext(nextTemp.getNext());
//                size--;
//                return o;
//            }
//            check++;
//            temp = nextTemp;
//            nextTemp = nextTemp.getNext();
//        }
//        return null;
//    }
//
//    public boolean remove(Object object) {
//        Node temp = head;
//        Node nextTemp = temp.getNext();
//        while (nextTemp != null) {
//            if (object.equals(nextTemp.getData())) {
//                temp.setNext(nextTemp.getNext());
//                return true;
//            }
//            temp = nextTemp;
//            nextTemp = nextTemp.getNext();
//        }
//        return false;
//    }
//
//    public boolean set(int index, Object object) {
//        Node node = new Node();
//        node.setData(object);
//        Node temp = head;
//        Node nextTemp = temp.getNext();
//        Integer i = 1;
//        while (nextTemp != null) {
//            if (i == index) {
//                temp.setNext(node);
//                node.setNext(nextTemp.getNext());
//            }
//            temp = nextTemp;
//            nextTemp = nextTemp.getNext();
//            i++;
//        }
//        return false;
//    }
//
//    public boolean contains(Object object) {
//        Node temp = head;
//        while (temp != null) {
//            if (temp.getData().equals(object)) {
//                return true;
//            }
//            temp = temp.getNext();
//        }
//        return false;
//    }
//
//    public Object get(int index) {
//        Node temp = head;
//        Integer i = 0;
//        while (temp != null) {
//            if (i == index) {
//                return temp.getData();
//            }
//            i++;
//            temp = temp.getNext();
//        }
//        return null;
//    }
//
//    public int size() {
//        Node temp = head;
//        Integer i = 0;
//        while (temp != null) {
//            i++;
//            temp = temp.getNext();
//        }
//        return i;
//    }
//
//    public Object[] subList(int fromIndex, int endIndex) {
//        Object[] newArr = new Object[size];
//        Integer index = 0;
//        Node temp = head;
//        Integer i = 0;
//        while (temp != null) {
//            if (i >= fromIndex && i < endIndex) {
//                newArr[index++] = temp.getData();
//            }
//            i++;
//            temp = temp.getNext();
//        }
//        Object[] tempArr = new Object[index];
//        for (int k = 0; k < index; k++) {
//            tempArr[k] = newArr[k];
//        }
//        return tempArr;
//    }
//
//    public Object[] toArray() {
//        Object[] newArr = new Object[size];
//        Integer index = 0;
//        Node temp = head;
//        while (temp != null) {
//            newArr[index++] = temp.getData();
//            temp = temp.getNext();
//        }
//        return newArr;
//    }
//
//}
