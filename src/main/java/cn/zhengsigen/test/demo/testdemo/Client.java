package cn.zhengsigen.test.demo.testdemo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException {
        // 建立连接
        final Socket socket = new Socket("192.168.6.182", 8888);

        // 在新线程中运行
        new Thread() {
            public void run() {
                try {
                    // 一直读
                    InputStream is = socket.getInputStream();
                    byte[] bytes = new byte[1024];
                    int lenght = -1;
                    // 读取其他客户端的发送的消息
                    while ((lenght = is.read(bytes)) != -1) {
                        System.out.println(new String(bytes, 0, lenght));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        // 一直发送
        OutputStream os = socket.getOutputStream();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String msg = scanner.nextLine();
            os.write(msg.getBytes("UTF-8"));
        }
    }
}
