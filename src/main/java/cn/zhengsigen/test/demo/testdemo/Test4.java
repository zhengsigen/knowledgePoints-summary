package cn.zhengsigen.test.demo.testdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Test4 {
    /*
        1.4. 抢红包
    抢红包大致要求:
    最低要求: 每人保底0.01, 最小人数为1
    红包发放完后, 不能再发, 最后所有红包金额之和为发放金额(难点)
    运行效果: 5人发放12.12元
    3.44
    1.61
    1.11
    0.86
    5.10
     */
    Scanner sc = new Scanner(System.in);
    Random random = new Random();

    public void test() {
        Double money;
        Integer number;
        System.out.println("请输入红包金额");
        if (sc.hasNextDouble()) {
            money = sc.nextDouble();
            System.out.println("请输入红包人数");
            if (sc.hasNextInt()) {
                number = sc.nextInt();
                if (money < number * 0.01) {
                    System.out.println("每人至少一分钱");
                } else {
                    List<Double> random = getRandom(money, number);
                    Double con = 0.0;
                    for (Double aDouble : random) {
                        con = con + aDouble;
                        System.out.println(aDouble);
                    }
                    System.out.println("原来红包总金额：" + Math.round(con * 100) / 100.0);
                }
            } else {
                System.out.println("人数应该是一个数字类型");
            }
        } else {
            System.out.println("金额应该是一个数字类型");
        }
    }

    public List<Double> getRandom(Double d, Integer num) {
        List<Double> lists = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            if (i + 1 == num) {
                lists.add(Math.round(d * 100) / 100.0);
                break;
            }
            Double ceil;
            while (true) {
                ceil = getDouble(d, num);
                if (ceil <= 0.01) {
                    ceil = 0.01;
                }
                Double temp1 = (num - (i + 1)) * 0.01;
                if (temp1 <= (Math.round((d - ceil) * 100) / 100.0)) {
                    break;
                }
            }
            d = d - ceil;
            lists.add(ceil);
        }
        return lists;
    }

    public Double getDouble(Double d, Integer num) {
        Double v;
        //人数越多，随机压得越小
        while (true) {
            v = random.nextDouble();
            if (num > 1 && num <= 5) {
                if (v < 2.5 / num) break;
            } else if (num > 5 && num <= 10) {
                if (v < 3.5 / num) break;
            } else if (num > 10 && num <= 20) {
                if (v < 5.0 / num) break;
            } else if (num > 20 && num <= 50) {
                if (v < 6.0 / num) break;
            } else if (v < 0.1) break;
        }
        Double temp = v * d;
        Double ceil = Math.round(temp * 100) / 100.0;
        return ceil;
    }
}
