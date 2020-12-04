package com.at.zcw.mqtest.fanout;

import com.at.zcw.mqtest.factory.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zero
 * @create 2020-12-04 13:42
 */
public class Recv1 {

    private final static String QUEUE_NAME = "fanout_exchange_queue_1";

    private final static String EXCHANGE_NAME = "FANOUT_EXCHANGE_TEST";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.basicQos(1);

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //绑定交换机 (String queue, String exchange, String routingKey)
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                System.out.println("consumerTag:"+consumerTag+",properties："+properties+",envelope:"+envelope);

                String msg = new String(body);
                System.out.println("Recv1 。。。 ==》 " + msg);

//                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };

        channel.basicConsume(QUEUE_NAME,true,consumer);


    }


}
