package cn.zhengsigen.test.demo.testdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.spi.DateFormatProvider;
import java.util.*;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExceptionHandle {
    /*
        1.2. 异常处理
    自定义一个异常
    列表20个有意义的异常将异常复现, 如不能复制则说明原因(不限基础)
     */

    //抛出自定义异常
    public void customException() {
        throw new ParameterException();
    }

    //算术异常ArithmeticException
    public void exception1() {
        Integer number = 10;
        Integer divisor = 0;
        //除数不能为0
        //number = number /divisor;
        if (divisor != 0) {
            number = number / divisor;
        }
    }

    //数组下标越界ArrayIndexOutOfBoundsException
    public void exception2() {
        Integer[] temp = new Integer[3];
        Integer index = 10;
        //下标越界
        // System.out.println(temp[index]);
        if (temp.length - 1 > index) {
            System.out.println(temp[index]);
        }
    }

    //字符串转换为数字异常：NumberFormatException
    public void exception3() {
        String str = "12a";
        //转换异常
        //Integer num = Integer.valueOf(str);
        //先使用正则表达式判断是否为纯数字
        if (str.matches("^[0-9]*$")) {
            System.out.println(Integer.valueOf(str));
        }
    }

    //下标越界异常：IndexOutOfBoundsException
    public void exception4() {
        List<Integer> lists = new ArrayList<>();
        //复现
        //System.out.println(lists.get(10));
        Integer index = 10;
        if (lists.size() - 1 > index) {
            System.out.println(lists.get(index));
        }
    }

    //编译时异常，springboot测试用例没有找到含有@Test注解的方法是抛出
    //java.lang.Exception: No runnable methods
    public void exception5() {
        //至少需要一个方法有@Test注解
        //@Test
//        public void exception5(){
//
//        }
    }

    //NullPointException空指针异常
    public void excepiton6() {
        String str = null;
        //复现
        //sSystem.out.println(str.length());
        if (str != null) {
            System.out.println(str);
        }
    }

    //NegativeArrayException数组负下标
    public void exception7() {
        Integer length = -1;
        //Integer[] msg = new Integer[length];
        if (length > 0) {
            Integer[] msg = new Integer[length];
            System.out.println(msg);
        }
    }

    //InputMismatchException输入的类型不匹配
    public void exception8() {
        Scanner src = new Scanner(System.in);
//        System.out.println("请输入一个数字");
//        //不是数字类型抛出异常InputMismatchException
//        int temp = src.nextInt();
        while (true) {
            try {
                System.out.println("请输入一个数字");
                int temp = src.nextInt();
                System.out.println(temp);
                break;
            } catch (Exception e) {
                src = new Scanner(System.in);
            }
        }
    }

    //ClassNotFoundException找不到类型异常
    public void exception9() throws NoSuchMethodException {
        //复现 Class<?> test1 = Class.forName("T");
        try {
            Class<?> test1 = Class.forName("T");
        } catch (Exception e) {
            System.out.println("找不到当前类");
        }
    }

    //NoSuchMethodException找不到方法
    public void exception10() throws ClassNotFoundException {
        Class<?> test1 = Class.forName("T");
        // 复现 test1.getMethod("show");
        try {
            test1.getMethod("show");
        } catch (Exception e) {
            System.out.println("找不到方法show");
        }
    }

    //FileNotFoundException找不到文件
    public void exception11() {
        File file = new File("work.txt");
        //复现System.out.println(file.getAbsoluteFile());
        if (!file.exists()) {
            file.getParentFile().mkdir();
        }
    }

    //ClassCastException类型转换异常
    public void exception12() {
//      复现  Object o = new Integer(1);
//        System.out.println((String) o);
        Object o = new Integer(1);
        if (o instanceof String) {
            System.out.println((String) o);
        }
    }

    //ConcurrentModificationException并行修改异常
    public void exception13() {
        List lists = new ArrayList();
        lists.add(1);
        lists.add(1);
        lists.add(2);
//        for (Object list : lists) {
//            if(list.equals(1)){
//                lists.remove(1);
//            }
//        }
        Iterator<Integer> it = lists.iterator();
        while (it.hasNext()) {
            Integer x = it.next();
            if (x.equals(1)) {
                it.remove();
            }
        }
    }

    //ParseException转换异常
    public void exception14() {
        String time = "2011-11-11";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            simpleDateFormat.parse(time);
        } catch (Exception e) {
            System.out.println("格式不符合");
        }
    }

    //IllegalArgumentException非法参数异常
    public void exception15() {
        String time = "2011-11-11";
        // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("qqq");
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("qqq");
            simpleDateFormat.parse(time);
        } catch (Exception e) {
            System.out.println("参数异常");
        }
    }

    //StringIndexOutOfBoundsException字符串下标越界
    public void exception16() {
        String str = "123123asdasd";
        Integer index = 100;
        //System.out.println(str.substring(index));
        if (str.length() - 1 > index) {
            System.out.println(str.substring(index));
        }
    }

    //IOException流异常
    public void excetion17() throws FileNotFoundException {
        File file = new File("src/main/resources/test.txt");
        FileInputStream fis = new FileInputStream(file);
        int value = 0;
//        while ((value = fis.read()) == -1) {
//            System.out.print((char) value);
//        }
        try {
            while ((value = fis.read()) != -1) {
                System.out.print((char) value);
            }
        } catch (IOException e) {
            System.out.println("io异常");
        }
    }

    //StreamCorruptedException流损坏异常
    public void exception18() throws IOException, ClassNotFoundException {
//        FileInputStream fis = new FileInputStream("src/main/resources/test.txt");
//        ObjectInputStream ois = new ObjectInputStream(fis);
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/test.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            System.out.println("流被损坏");
        }
    }

    //ArrayStoreException向数组中存放与声明类型不兼容对象异常
    public void exception19() {
        Object x[] = new String[3];
//        x[0] = new Integer(0);
        if (x instanceof Integer[]) {
            x[0] = new Integer(0);
        }
    }

    //UnsupportedOperationException不支持的操作异常
    public void exception20() {
        //定义一个字符长度为5的字符串
        String[] strings = new String[5];
        strings[0] = "a";
        strings[1] = "b";
        strings[2] = "c";
        strings[3] = "d";
        strings[4] = "e";
//        List<String> list = Arrays.asList(strings);
//  复现  System.out.println("list<String>:"+list.toString());
//        list.add("f");
        List<String> list = new ArrayList(Arrays.asList(strings));
        System.out.println("list<String>:" + list.toString());
        list.add("f");
    }
}
