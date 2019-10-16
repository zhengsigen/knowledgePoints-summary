package cn.zhengsigen.test.demo.testdemo;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class DeleteAndCopy {
    /*
    1.5. 删除/复制一个文件(夹)
        删除: 自己写代码, 引用工具类除外
        如果不存在,取消操作
        如果文件直接删除
        如果文件夹递归删除所有
        复制: 自己写代码, 引用工具类除外
        如果文件直接复制, 如果复制后的路径是文件则放弃, 如果复制后的路径是文件夹(不存在创建)直接复制(如果目录有同名文件, 提示是否覆盖-取消)
        如果文件夹则按目录结构递归进行复制, 1.复制后的路径为文件则取消. 2.复制后的路径文件夹为空直接复制 3.复制后的路径文件夹里面有空取消
     */
    public void delFile(File f) {
        File[] b = f.listFiles();
        for (int i = 0; i < b.length; i++) {
            if (b[i].isFile()) {
                b[i].delete();
            } else {
                delFile(b[i]);
            }
        }
        f.delete();
    }

    public void copyFiles() throws IOException {
        File destDir = new File("src/main/resources/1.html");
        File source = new File("src/main/resources/字体/1.html");
        //如果源是文件
        if (source.isFile()) {
            //如果目的地存在
            if (destDir.exists()) {
                //如果目的地是文件夹，直接复制进去
                if (destDir.isDirectory()) {
                    File file = new File(destDir.getPath() + "/" + source.getName());
                    Files.copy(source.toPath(), file.toPath());
                } else {
                    //如果目的地是文件
                    System.out.println("文件已经存在，是否覆盖？\n1、覆盖 2、取消");
                    Scanner sc = new Scanner(System.in);
                    if (sc.nextInt() != 1) {
                        return;
                    }
                    destDir.delete();
                    Files.copy(source.toPath(), destDir.toPath());
                }
                //如果目的地不存在
            } else {
                Files.copy(source.toPath(), destDir.toPath());
            }
        }
        //如果源是目录
        else {
            //如果目的地存在
            if (destDir.exists()) {
                System.out.println("存储路径已经存在，是否覆盖？\n1、覆盖 2、取消");
                Scanner sc = new Scanner(System.in);
                if (sc.nextInt() != 1) {
                    return;
                }
                delFile(destDir);
            }
            destDir.mkdirs();
            copyFile(source, destDir);
        }
    }

    //递归复制
    public void copyFile(File fileInTo, File fileOutTo) throws IOException {
        File[] listFiles = fileInTo.listFiles();
        for (File file : listFiles) {
            if (file.isDirectory()) {
                File file2 = new File(fileOutTo.getPath() + "/" + file.getName());
                file2.mkdirs();
                copyFile(file, file2);
            } else {
                Files.copy(file.toPath(), new File(fileOutTo.getPath() + "/" + file.getName()).toPath());
            }
        }
    }

}
