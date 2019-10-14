package cn.zhengsigen.test.demo.testdemo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
链表集合
 */
public class ImplementDoubleLinked implements DSList {

    LinkedList<Object> lists = new LinkedList<>();

    @Override
    public boolean add(Object object) {
        lists.add(object);
        return false;
    }

    @Override
    public boolean add(int index, Object object) {
        lists.add(index, object);
        return false;
    }

    @Override
    public boolean addAll(Object[] object) {
        lists.addAll(new ArrayList(Arrays.asList(object)));
        return false;
    }

    @Override
    public int indexOf(Object o) {
        return lists.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return lists.lastIndexOf(o);
    }

    @Override
    public Object remove(int index) {
        lists.remove(index);
        return null;
    }

    @Override
    public boolean remove(Object object) {
        lists.remove(object);
        return false;
    }

    @Override
    public boolean set(int index, Object object) {
        lists.set(index, object);
        return false;
    }

    @Override
    public boolean contains(Object object) {
        lists.contains(object);
        return false;
    }

    @Override
    public Object get(int index) {
        return lists.get(index);
    }

    @Override
    public int size() {
        return lists.size();
    }

    @Override
    public Object[] subList(int fromIndex, int endIndex) {
        List<Object> objects = lists.subList(fromIndex, endIndex);
        return objects.toArray();
    }

    @Override
    public Object[] toArray() {
        return lists.toArray();
    }
}
