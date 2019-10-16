package cn.zhengsigen.test.demo.testdemo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 1.6. 基于网络实现群聊、私聊
 * 私聊：@ip或@昵称，只需要发送指定用户消息
 * 消息发送格式：@192.168.0.0 在
 * 消息反馈格式:  反馈消息 201x-xx-xx xx:xx:xx  192.168.0.0  发送成功或发送失败(下线)
 * 消息接收格式：私聊消息 201x-xx-xx xx:xx:xx  192.168.0.0  \n 在
 * 群聊：向所有在线的用户全部发送消息（自己发消息所有人接收，自己不收接收）
 * 消息发送格式：在
 * 消息接收格式：群聊消息 201x-xx-xx xx:xx:xx  192.168.0.0  \n 在
 * 通知：用户上下线需要通知所有用户
 * 消息接收格式：消息通知 201x-xx-xx xx:xx:xx   xx下线
 * 消息接收格式：消息通知 201x-xx-xx xx:xx:xx   xx上线
 */
/*
    服务器端：
    1、开启服务器。
    2、阻塞（循环）等待用户连接，如果有用户连接成功，创建新线程，while循环重新阻塞登录用户
    3、新线程不断读取用户发送的信息，根据正则表达式判断为私聊/群聊，处理发送
    客户端：
    1、连接服务器
    2、开启新线程，读取服务器发送的信息
    3、发送信息
 */
public class Server {

    public static void main(String[] args) throws IOException {
        new Server().init();
    }

    // 所有的客户端
    private List<Socket> clients = new ArrayList<>();
    SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM-dd");

    public void init() throws IOException {
        // 建立服务器
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务器启动");
        while (!Thread.currentThread().isInterrupted()) {
            Socket client = serverSocket.accept(); // 等待请求 - 只运行一次
            // 用户上线
            clients.add(client);
            String name = client.getInetAddress().getHostAddress() + ":" + client.getPort();
            broadcast(name + " 上线了");
            new Thread() {
                @Override
                public void run() {
                    try {
                        // 边读边写
                        InputStream is = client.getInputStream();
                        byte[] bytes = new byte[1024];
                        int lenght = -1;
                        // 读取客户端的发送的消息
                        while ((lenght = is.read(bytes)) != -1) { // 阻塞
                            String msg = new String(bytes, 0, lenght);
                            Pattern compile = Pattern.compile("^@([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})\\s+.*$");
                            Matcher matcher = compile.matcher(msg);
                            // 私聊
                            if (matcher.find()) {
                                String ip = matcher.group(1);
                                Boolean bool = false;
                                for (Socket socket : clients) {
                                    //向指定用户发送私聊
                                    if (socket.getInetAddress().getHostAddress().equals(ip)) {
                                        bool = true;
                                        try {
                                            OutputStream os = socket.getOutputStream();
                                            os.write(msg.getBytes("UTF-8"));
                                        } catch (Exception e) {
                                            // 用户下线
                                            clients.remove(socket);
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                if (!bool) {
                                    client.getOutputStream().write("用户不在线".getBytes("UTF-8"));
                                }
                            } else {
                                // 群聊
                                String name = client.getInetAddress().getHostAddress() + ":" + client.getPort();
                                msg = name + "\n" + msg;
                                broadcast(msg);
                            }
                        }
                    } catch (Exception e) {
                        // 用户下线
                        broadcast(dsf.format(new Date()) + " " + name + " 下线了");
                        clients.remove(client);
                    }
                }
            }.start();
        }
    }

    /**
     * 广播
     */
    public void broadcast(String msg) {
        msg = dsf.format(new Date()) + " " + msg;
        // 向客户端发送消息
        for (Socket socket : clients) {
            try {
                OutputStream os = socket.getOutputStream();
                os.write(msg.getBytes("UTF-8"));
            } catch (Exception e) {
            }
        }
    }
}
