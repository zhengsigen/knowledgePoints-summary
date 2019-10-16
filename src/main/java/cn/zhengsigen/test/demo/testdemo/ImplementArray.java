package cn.zhengsigen.test.demo.testdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用数组
 */
public class ImplementArray implements DSList {
    Object[] arr = new Object[4];
    Integer index = 0;

    @Override
    public boolean add(Object object) {
        dynamicArr(index);
        arr[index++] = object;
        return false;
    }

    @Override
    public boolean add(int index, Object object) {
        Object[] newArray = new Object[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            newArray[i] = arr[i];
        }
        for (int i = newArray.length - 1; i > index; i--) {
            newArray[i] = newArray[i - 1];
        }
        newArray[index] = object;
        arr = newArray;
        dynamicArr(index);
        this.index++;
        return false;
    }

    @Override
    public boolean addAll(Object[] object) {
        List<Object> list = new ArrayList();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        for (int i = 0; i < object.length; i++) {
            list.add(object[i]);
        }
        return false;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = index - 1; i >= 0; i--) {
            if (arr[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Object remove(int index) {
        Object object = null;
        Object[] temp = new Object[arr.length];
        Integer addIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i == index) {
                object = arr[i];
                this.index--;
                continue;
            }
            temp[addIndex++] = arr[i];
        }
        arr = temp;
        return object;
    }

    @Override
    public boolean remove(Object object) {
        Object[] temp = new Object[arr.length];
        Integer addIndex = 0;
        boolean b = false;
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].equals(object)) {
                temp[addIndex++] = arr[i];
            } else {
                b = true;
                index--;
            }
        }
        arr = temp;
        return b;
    }

    @Override
    public boolean set(int index, Object object) {
        for (int i = 0; i < arr.length; i++) {
            if (i == index) {
                arr[i] = object;
                break;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Object object) {
        for (Object o : arr) {
            if (object.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object get(int index) {
        for (int i = 0; i < arr.length; i++) {
            if (i == index) {
                return arr[i];
            }
        }
        return null;
    }

    @Override
    public int size() {
        return index;
    }

    @Override
    public Object[] subList(int fromIndex, int endIndex) {
        Object[] temp = new Object[index];
        Integer index = 0;
        for (int i = 0; i < this.index; i++) {
            if (i >= fromIndex && i < endIndex) {
                temp[index++] = arr[i];
            }
        }
        Object[] objects = new Object[index];
        for (int i = 0; i < index; i++) {
            objects[i] = temp[i];
        }
        return objects;
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[index];
        for (int i = 0; i < index; i++) {
            objects[i] = arr[i];
        }
        return objects;
    }

    public void dynamicArr(Integer index) {
        if (index == arr.length) {
            Object[] newArr = new Object[index * 2];
            for (int i = 0; i < arr.length; i++) {
                newArr[i] = arr[i];
            }
            arr = newArr;
        }
    }
}
