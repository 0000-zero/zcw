package com.at.zcw.mqtest.woker;

import com.at.zcw.mqtest.factory.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zero
 * @create 2020-12-04 13:09
 */
public class Recv1 {

    private final static String QUEUE_NAME = "WORK_QUEUE";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        //在队列声明之前设置，当前消费者每次只能处理一个请求，在请求被ack后mq再向当前消费者发送下一个消息
        channel.basicQos(1);


        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("Recv1 ==> " + msg);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        channel.basicConsume(QUEUE_NAME,false,consumer);

//        channel.close();
//        connection.close();

    }

}
