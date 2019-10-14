package cn.zhengsigen.test.demo.testdemo;

import java.io.Serializable;

/**
 * 数组通用功能
 *
 * @author easytop
 */
public interface DSList extends Serializable {
    /**
     * 无限增加
     *
     * @param object
     * @return
     */
    public boolean add(Object object);

    /**
     * 在指定位置添加
     *
     * @param index
     * @param object
     * @return
     */
    public boolean add(int index, Object object);

    /**
     * 向现在数组末尾拼接一个新数组(合并数组)
     *
     * @param object
     * @return
     */
    public boolean addAll(Object[] object);

    /**
     * 从数组开头查找并定位元素所在下标
     *
     * @param o
     * @return
     */
    public int indexOf(Object o);

    /**
     * 从数组结尾查找并定位元素所在下标
     *
     * @param o
     * @return
     */
    public int lastIndexOf(Object o);

    /**
     * 删除指定下标的元素并返回
     *
     * @param index
     * @return
     */
    public Object remove(int index);

    /**
     * 直接删除元素
     *
     * @param object
     * @return
     * @see java.lang.Object.eqauals
     */
    public boolean remove(Object object);

    /**
     * 替换指定下标元素
     *
     * @param index
     * @param object
     * @return
     */
    public boolean set(int index, Object object);

    /**
     * 查找数组中是否有传入的元素
     *
     * @param object
     * @return
     */
    public boolean contains(Object object);

    /**
     * 获取指定下标的元素
     *
     * @param index
     * @return
     */
    public Object get(int index);

    /**
     * 实际存储元素的数量
     *
     * @return
     */
    public int size();

    /**
     * 从fromIndex开始到endIndex
     *
     * @param fromIndex
     * @param endIndex
     * @return
     */
    public Object[] subList(int fromIndex, int endIndex);

    /**
     * 转换成数组
     *
     * @return
     */
    public Object[] toArray();

}
