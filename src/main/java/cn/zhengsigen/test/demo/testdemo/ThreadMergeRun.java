package cn.zhengsigen.test.demo.testdemo;

import lombok.Data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

@Data
public class ThreadMergeRun extends Thread {
    private Integer threadId;
    private long start;
    private long redLength;

    @Override
    public void run() {
        try {
            RandomAccessFile rafAll = new RandomAccessFile("src/main/resources/切割存放文件夹/合并文件.wmv", "rw");
            RandomAccessFile raf = new RandomAccessFile("src/main/resources/切割存放文件夹/切割文件" + threadId + ".wmv", "r");
            rafAll.seek(start);
            byte[] b = new byte[1024 * 1024];
            long length;
            while ((length = raf.read(b)) != -1) {
                if (redLength - length >= 0) {
                    rafAll.write(b, 0, (int) length);
                    redLength -= length;
                } else {
                    rafAll.write(b, 0, (int) redLength);
                    break;
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
