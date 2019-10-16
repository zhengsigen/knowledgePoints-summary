package cn.zhengsigen.test.demo.testdemo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;

public class FileCuttingAndMerging {
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
        int count = 10;
        //切割
//        RandomAccessFile raf = new RandomAccessFile("src/main/resources/2018-12-3.wmv", "r");
//        for (int i = 1; i <= count; i++) {
//            Long readLength = raf.length() / count;
//            ThreadRun t = new ThreadRun();
//            t.setStart((i - 1) * readLength);
//            if (i == count) {
//                readLength = raf.length() - readLength * (count - 1);
//            }
//            t.setThreadId(i);
//            t.setRedLength(readLength);
//            System.out.println(t);
//            Thread thread = new Thread(t);
//            thread.start();
//        }

        //合并
        for (int i = 1; i <= count; i++) {
            RandomAccessFile length = new RandomAccessFile("src/main/resources/切割存放文件夹/切割文件" + 1 + ".wmv", "rw");
            RandomAccessFile raf = new RandomAccessFile("src/main/resources/切割存放文件夹/切割文件" + i + ".wmv", "rw");
            ThreadMergeRun t = new ThreadMergeRun();
            t.setThreadId(i);
            t.setStart(length.length() * (i - 1));
            t.setRedLength(raf.length());
            Thread thread = new Thread(t);
            thread.start();
        }
    }
}
