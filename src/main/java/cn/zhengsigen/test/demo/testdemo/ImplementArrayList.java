package cn.zhengsigen.test.demo.testdemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 使用集合
 */
public class ImplementArrayList implements DSList {
    List<Object> lists = new ArrayList<>();

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
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = lists.size(); i >= 0; i--) {
            if (lists.get(i).equals(o)) {
                return i;
            }
        }
        return -1;
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
        if (lists.contains(object)) {
            return true;
        }
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
