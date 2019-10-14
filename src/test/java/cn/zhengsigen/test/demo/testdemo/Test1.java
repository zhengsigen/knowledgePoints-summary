package cn.zhengsigen.test.demo.testdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test1 {
    Integer[] nums = {1, 2, 33, 3, 3, 3, 4, 62, 124, 12, 56, 1, 23, 45, 1, 2, 41, 2, 33, 55, 1, 2, 35, 23, 4, 54, 7, 1, 3, 34, 42, 3, 32, 32, 3, 3, 3, 3, 5};
    Integer[] numsCopy = {3, 3, 5, 1, 5, 6, 1, 33, 5, 5, 134, 12, 412, 3, 3, 64, 634, 2134, 1234, 12, 34, 1234, 12, 4};
    /*
    计算要求: 分别使用数组/集合框架实现以下要求
    1 统计两个数组中共同出现的数字 以及 求共同出现次数最多的数字是哪个有几次?
    2 进行去重:数字不能重复
    3 对以上数组进行排序与去重: 升序或降序
    4 统计出现次数最多的数: 比如: 3,78(出现次数相同一起列出), 出现次数:9.
    5 输出每个数字出现的数字: (资料出现的次数从大到小)
    数字:3 - 出现次数:9
    数字:2 - 出现次数:9
    数字:1 - 出现次数:9
     */
    //1统计两个数组中共同出现的数字 以及 求共同出现次数最多的数字是哪个有几次?
    public void aggregate1() {
        List<Integer> integers = new ArrayList(Arrays.asList(nums));
        List<Integer> integersCopy = new ArrayList(Arrays.asList(numsCopy));
        Map<Integer, Integer> map = new HashMap<>();
        //获取共同出现的数字
        for (Integer integer : integersCopy) {
            if (integers.contains(integer)) {
                if (map.containsKey(integer)) {
                    map.put(integer, map.get(integer) + 1);
                } else {
                    map.put(integer, 1);
                }
            }
        }
        //最多次数
        Integer maxInteger = 0;
        Map<Integer, Integer> max = new HashMap<>();
        Iterator<Map.Entry<Integer, Integer>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Integer, Integer> entry = entries.next();
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (value > maxInteger) {
                maxInteger = value;
                max.clear();
                max.put(key, value);
            } else if (value == maxInteger) {
                max.put(key, value);
            }
        }
        Iterator<Map.Entry<Integer, Integer>> msg = max.entrySet().iterator();
        while (msg.hasNext()) {
            Map.Entry<Integer, Integer> entry = msg.next();
            System.out.println("共同出现次数最多的数字是：" + entry.getKey() + "，次数为" + entry.getValue());
        }
        Set common = new HashSet<>();
        //取交集
        integers.retainAll(integersCopy);
        //去重
        common.addAll(integers);
        System.out.println("两个数组中共同出现的数字：" + common);
    }
    //2进行去重:数字不能重复
    public void aggregate2() {
        Set common = new HashSet<>();
        common.addAll(Arrays.asList(numsCopy));
        System.out.println(common);
    }
    //3对以上数组进行排序与去重: 升序或降序
    public void aggregate3() {
        TreeSet<Integer> tSet = new TreeSet<>();
        tSet.addAll(Arrays.asList(numsCopy));
        System.out.println(tSet);
    }
    //4统计出现次数最多的数: 比如: 3,78(出现次数相同一起列出), 出现次数:9.
    public void aggregate4() {
        List<Integer> integers = new ArrayList(Arrays.asList(nums));
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer integer : integers) {
            if (map.containsKey(integer)) {
                map.put(integer, map.get(integer) + 1);
            } else {
                map.put(integer, 1);
            }
        }
        //最多次数
        Integer maxInteger = 0;
        Map<Integer, Integer> max = new HashMap<>();
        Iterator<Map.Entry<Integer, Integer>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Integer, Integer> entry = entries.next();
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (value > maxInteger) {
                maxInteger = value;
                max.clear();
                max.put(key, value);
                //判断是否有出现次数一致的数字
            } else if (value == maxInteger) {
                max.put(key, value);
            }
        }
        Iterator<Map.Entry<Integer, Integer>> msg = max.entrySet().iterator();
        while (msg.hasNext()) {
            Map.Entry<Integer, Integer> entry = msg.next();
            System.out.println("出现次数最多的数字是：" + entry.getKey() + "，次数为" + entry.getValue());
        }
    }
    //5输出每个数字出现的数字: (资料出现的次数从大到小)
    public void aggregate5() {
        List<Integer> integers = new ArrayList(Arrays.asList(nums));
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer integer : integers) {
            if (map.containsKey(integer)) {
                map.put(integer, map.get(integer) + 1);
            } else {
                map.put(integer, 1);
            }
        }
        //将map.entrySet()转换成list
        List<Map.Entry<Integer, Integer>> list_Data = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());
        //自定义排序规则
        Collections.sort(list_Data, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                //o1 to o2升序   o2 to o1降序
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        System.out.println(list_Data);
    }
    /*---------------------------------------------------------------------------------------------------------------------*/
    //1统计两个数组中共同出现的数字 以及 求共同出现次数最多的数字是哪个有几次?
    public void array1() {
        Integer temp[] = new Integer[nums.length];
        Integer index = 0;
        Integer counts[][] = new Integer[nums.length][2];
        Integer countIndex = 0;
        for (Integer num : nums) {
            boolean bool = false;
            Integer count = 0;
            //共同出现的数字
            for (Integer integer : numsCopy) {
                if (num.equals(integer)) {
                    bool = true;
                    count++;
                }
            }
            //不存在新数组
            if (bool) {
                boolean b = false;
                for (Integer integer : temp) {
                    if (num.equals(integer)) {
                        b = true;
                    }
                }
                if (!b) {
                    System.out.println("两个数组中共同出现的数字：" + num);
                    temp[index++] = num;
                    Integer[] msg = {num, count};
                    counts[countIndex++] = msg;
                }
            }
        }
        Integer msgArr[][] = new Integer[countIndex][2];
        Integer msgIndex = 0;
        Integer max = 0;
        for (int x = 0; x < countIndex; x++) {
            if (counts[x][1] > max) {
                max = counts[x][1];
            }
        }
        for (int x = 0; x < countIndex; x++) {
            if (counts[x][1] == max) {
                msgArr[msgIndex++] = counts[x];
            }
        }
        for (int x = 0; x < msgIndex; x++) {
            System.out.println("共同出现次数最多的数字是：" + msgArr[x][0] + "，次数为" + msgArr[x][1]);
        }
    }
    //2进行去重:数字不能重复
    public void array2() {
        Integer temp[] = new Integer[nums.length];
        Integer index = 0;
        for (Integer num : nums) {
            boolean bool = true;
            for (int i = 0; i < index; i++) {
                if (num.equals(temp[i])) {
                    bool = false;
                    break;
                }
            }
            if (bool) {
                temp[index++] = num;
            }
        }
    }
    //3对以上数组进行排序与去重: 升序或降序
    public void array3() {
        Integer temp[] = new Integer[nums.length];
        Integer index = 0;
        for (Integer num : nums) {
            boolean bool = true;
            for (int i = 0; i < index; i++) {
                if (num.equals(temp[i])) {
                    bool = false;
                    break;
                }
            }
            if (bool) {
                temp[index++] = num;
            }
        }
        Integer msg;
        for (int i = 0; i < index; i++) {
            for (int j = i + 1; j < index; j++) {
                if (temp[i] > temp[j]) {
                    msg = temp[i];
                    temp[i] = temp[j];
                    temp[j] = msg;
                }
            }
        }
    }
    //4统计出现次数最多的数: 比如: 3,78(出现次数相同一起列出), 出现次数:9.
    public void array4() {
        Integer counts[][] = new Integer[nums.length][2];
        Integer countIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            Integer count = 1;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }
            Integer[] msg = {nums[i], count};
            counts[countIndex++] = msg;
        }

        Integer msgArr[][] = new Integer[countIndex][2];
        Integer msgIndex = 0;
        Integer max = 0;
        for (int x = 0; x < countIndex; x++) {
            if (counts[x][1] > max) {
                max = counts[x][1];
            }
        }
        for (int x = 0; x < countIndex; x++) {
            if (counts[x][1] == max) {
                msgArr[msgIndex++] = counts[x];
            }
        }
        for (int x = 0; x < msgIndex; x++) {
            System.out.println("共同出现次数最多的数字是：" + msgArr[x][0] + "，次数为" + msgArr[x][1]);
        }
    }
    //5输出每个数字出现的数字: (资料出现的次数从大到小)
    public void array5() {
        Integer counts[][] = new Integer[nums.length][2];
        Integer countIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            Integer count = 1;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }
            boolean bool = true;
            for (int k = 0; k < countIndex; k++) {
                if (counts[k][0] == nums[i]) {
                    bool = false;
                }
            }
            if (bool) {
                Integer[] msg = {nums[i], count};
                counts[countIndex++] = msg;
            }
            Integer[] tempArr;
            for (int q = 0; q < countIndex; q++) {
                for (int j = q + 1; j < countIndex; j++) {
                    if (counts[q][1] < counts[j][1]) {
                        tempArr = counts[q];
                        counts[q] = counts[j];
                        counts[j] = tempArr;
                    }
                }
            }
        }
        for (int x = 0; x < countIndex; x++) {
            System.out.println("数字：" + counts[x][0] + "，次数：" + counts[x][1]);
        }
    }
}
