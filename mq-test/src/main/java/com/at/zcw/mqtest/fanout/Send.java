package com.at.zcw.mqtest.fanout;

import com.at.zcw.mqtest.factory.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zero
 * @create 2020-12-04 13:36
 */
public class Send {

    private final static String EXCHANGE_NAME = "FANOUT_EXCHANGE_TEST";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //声明交换机，交换机名，交换机类型
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        String msg = "exchange .... fanout";
        channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes());

        System.out.println("发送成功！！！");

        channel.close();
        connection.close();


    }


}
