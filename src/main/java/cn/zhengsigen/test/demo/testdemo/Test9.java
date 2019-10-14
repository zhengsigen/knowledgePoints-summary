package cn.zhengsigen.test.demo.testdemo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;

public class Test9 {
    /**
     * 2.2. 文件切割与合并
     * 请将一个100M以上的文件分10个线程同时分割与合并, 使用MD5验证过程与结果的正确
     * 分割:
     * 每个线程(任务)即一个任务: 文件不同的部分
     * 并发运行: 同时读一个文件
     * 任务切割: 分割不同的部分的区间
     * 分割:
     * 每个线程(任务)即一个任务: 文件不同的部分
     * 并发运行: 同时写一个文件
     * 任务切割: 合并不同的部分的区间到一文件
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        RandomAccessFile raf = new RandomAccessFile("src/main/resources/2018.08.30.wmv", "r");
        RandomAccessFile rafAll = new RandomAccessFile("src/main/resources/切割存放文件夹/合并-2018.08.30.wmv", "rw");
        int count = 10;
        for (int i = 0; i < count; i++) {
            Thread.sleep(50);
            ThreadRun t = new ThreadRun();
            t.setCount(count);
            t.setThreadId(i + 1);
            t.setRaf(raf);
            t.setRafAll(rafAll);
            Thread thread = new Thread(t);
            thread.start();
        }
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println("切割合并完成" + rafAll.length());
                    raf.close();
                    rafAll.close();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
