package cn.zhengsigen.test.demo.testdemo;

import lombok.Data;

import java.io.RandomAccessFile;

@Data
public class ThreadRun implements Runnable {
    /**
     * 文件切割份数
     */
    private int count;
    /**
     * 线程ID
     */
    private int threadId;
    /**
     * 源文件
     */
    private RandomAccessFile raf;
    /**
     * 合并文件地址
     */
    private RandomAccessFile rafAll;

    @Override
    public void run() {
        try {
            RandomAccessFile tempRaf = new RandomAccessFile("src/main/resources/切割存放文件夹/切割文件" + threadId + ".wmv", "rw");
            byte[] b = new byte[(int) raf.length() / count];
            if (threadId == count) b = new byte[(int) (raf.length() - b.length * (count - 1))];
            raf.read(b);
            rafAll.write(b);
            tempRaf.write(b);
            tempRaf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
