package cn.zhengsigen.test.demo.testdemo;

import org.omg.CORBA.DATA_CONVERSION;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class CalendarList {
    /*
    1.3. 日历输出
    请在控制台输出: 指定日期的日历, 并在指定日期所在日右侧上添加*
    2016年11月份的日历, 并在10号右侧上添加*, 第一行右侧显示返回今天(以代码运行时间为准)
     */
    Scanner sc = new Scanner(System.in);
    String[] weekNums = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public void test() throws ParseException {
        System.out.println("请输入一个日期(例: 2010-10-06)");
        String date = sc.nextLine();
        Date parse = null;
        try {
            parse = sdf.parse(date);
        } catch (Exception e) {
            System.out.println("日期格式错误");
        }
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(parse);
        int day = cal.get(java.util.Calendar.DAY_OF_MONTH);
        int weekDay = cal.get(java.util.Calendar.DAY_OF_WEEK);
        java.util.Calendar today = java.util.Calendar.getInstance();
        today.setTime(new Date());
        int last = cal.getActualMaximum(cal.DAY_OF_MONTH);
        System.out.print("当前日历：" + date + " " + weekNums[weekDay - 1]);
        System.out.println("  返回当天：" + sdf.format(new Date()) + " " + weekNums[today.get(java.util.Calendar.DAY_OF_WEEK) - 1]);
        System.out.println("一  二  三  四  五  六  日");
        cal.set(java.util.Calendar.DAY_OF_MONTH, 1);
        //一个月的第一天是星期几
        int start = cal.get(java.util.Calendar.DAY_OF_WEEK) - 1;
        for (int i = 1; i < start; i++) {
            System.out.print("  " + " " + "\t");
        }
        for (int i = 1; i <= last; i++) {
            if (i == day) {
                System.out.print(i + "*" + "\t");
            } else {
                System.out.print(i + "\t");
            }
            if ((i - 1 + start) % 7 == 0) {
                System.out.println();
            }
        }
        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.print("当前日历：" + date + " " + weekNums[weekDay - 1]);
        System.out.println("  返回当天：" + sdf.format(new Date()) + " " + weekNums[today.get(java.util.Calendar.DAY_OF_WEEK) - 1]);
        System.out.println("日  一  二  三  四  五  六 ");
        cal.set(java.util.Calendar.DAY_OF_MONTH, 1);
        int start2 = cal.get(java.util.Calendar.DAY_OF_WEEK);
        for (int i = 1; i < start2; i++) {
            System.out.print("  " + " " + "\t");
        }
        for (int i = 1; i <= last; i++) {
            if (i == day) {
                System.out.print(i + "*" + "\t");
            } else {
                System.out.print(i + "\t");
            }
            if ((i - 1 + start2) % 7 == 0) {
                System.out.println();
            }
        }
    }
}
