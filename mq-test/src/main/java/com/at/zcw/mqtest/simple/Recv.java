package com.at.zcw.mqtest.simple;

import com.at.zcw.mqtest.factory.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zero
 * @create 2020-12-04 12:53
 */
public class Recv {

    public static final String QUEUE_NAME="SIMPLE_QUEUE";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection conn = ConnectionUtil.getConnection();

        Channel channel = conn.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                String msg = new String(body);
                System.out.println("receive msg : " + msg);

                channel.basicAck(envelope.getDeliveryTag(),false);

            }
        };

        //监听队列
        channel.basicConsume(QUEUE_NAME,false,consumer);


    }

}
