package com.at.zcw.mqtest.simple;

import com.at.zcw.mqtest.factory.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zero
 * @create 2020-12-04 12:41
 */
public class Send {

    public static final String QUEUE_NAME="SIMPLE_QUEUE";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接通道
        Connection conn = ConnectionUtil.getConnection();

        //从连接中创建通道
        Channel channel = conn.createChannel();

        //在通道中声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //发送消息
        String msg  = "hello world!!!";
        channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());

        System.out.println("发送成功。。。。。。");

        //关闭连接
        channel.close();
        conn.close();


    }

}
