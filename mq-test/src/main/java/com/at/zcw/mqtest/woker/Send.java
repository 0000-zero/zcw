package com.at.zcw.mqtest.woker;

import com.at.zcw.mqtest.factory.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zero
 * @create 2020-12-04 13:05
 */
public class Send {

    private final static String QUEUE_NAME = "WORK_QUEUE";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        for (int i = 0; i < 400; i++) {
            String msg = "tesk .... "  + i;
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            System.out.println(i+" ==> 消息发送成功");
//            try { Thread.sleep(300); } catch (InterruptedException e) {e.printStackTrace();}
        }

        channel.close();
        connection.close();


    }
}
