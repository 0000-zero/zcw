package com.at.zcw.mqtest.springamqp2.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author zero
 * @create 2020-12-04 19:21
 */
@Component
@RabbitListener(queues = "test_queue_confirm")
public class MqListener {


    @RabbitHandler
    public void listen(Message message, Channel channel,String msg) throws IOException {

//        channel.basicQos(1);

        try{
            System.out.println("接收到消息 ==》："+msg);

//            int i = 9/0;

            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }catch(Exception e){
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
        }

    }

}
