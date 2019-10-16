package cn.zhengsigen.test.demo.testdemo;

import lombok.Data;

import java.io.RandomAccessFile;

@Data
public class ThreadRun extends Thread {
    private int threadId;
    private long start;
    private long redLength;

    @Override
    public void run() {
        try {
            RandomAccessFile raf = new RandomAccessFile("src/main/resources/2018-12-3.wmv", "r");
            RandomAccessFile tempRaf = new RandomAccessFile("src/main/resources/切割存放文件夹/切割文件" + threadId + ".wmv", "rw");
            raf.seek(start);
            byte[] b = new byte[1024 * 1024];
            int len;
            while ((len = raf.read(b)) != -1) {
                if (redLength - len >= 0) {
                    tempRaf.write(b, 0, len);
                    redLength -= len;
                } else {
                    tempRaf.write(b, 0, (int) redLength);
                    break;
                }
            }
            tempRaf.close();
            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
