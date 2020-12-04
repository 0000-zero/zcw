package com.at.zcw.mqtest.direct;

import com.at.zcw.mqtest.factory.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zero
 * @create 2020-12-04 13:56
 */
public class Send {

    private final static String EXCHANGE_NAME = "direct_exchange_test";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME,"direct");

        String msg = "direct_exchange_test ... direct";
        channel.basicPublish(EXCHANGE_NAME,"insert",null,msg.getBytes());
        System.out.println("发送成功");

        channel.close();
        connection.close();


    }

}
