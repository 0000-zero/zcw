package com.at.zcw.mqtest.springamqp2.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author zero
 * @create 2020-12-04 18:47
 */
@Configuration
public class RabbitMQConfig {



    @Bean
    public Queue queue(){

        //String name, boolean durable, boolean exclusive, boolean autoDelete, @Nullable Map<String, Object> arguments) {
          return new  Queue("test_queue_confirm",true,false,false,new HashMap<>());
    }

    @Bean
    public DirectExchange exchange(){
        //String name, boolean durable, boolean autoDelete, Map<String, Object> arguments) {
        return new DirectExchange("test_exchange_confirm",true,false,null);
    }

    @Bean
    public Binding binding(){
        //String destination, Binding.DestinationType destinationType, String exchange, String routingKey, @Nullable Map<String, Object> arguments) {
        return new Binding("test_queue_confirm", Binding.DestinationType.QUEUE,"test_exchange_confirm","confirm",null);
    }

    @Bean
    public Queue queuettl(){

        //String name, boolean durable, boolean exclusive, boolean autoDelete, @Nullable Map<String, Object> arguments) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("x-message-ttl",10000);
        return new  Queue("test_queue_ttl",true,false,false,map);
    }

    @Bean
    public TopicExchange exchangettl(){
        //String name, boolean durable, boolean autoDelete, Map<String, Object> arguments)
        return new TopicExchange("test_exchange_ttl",true,false,new HashMap<>());
    }

    @Bean
    public Binding bindingttl(){
        //String destination, Binding.DestinationType destinationType, String exchange, String routingKey, @Nullable Map<String, Object> arguments) {
        return new Binding("test_queue_ttl", Binding.DestinationType.QUEUE,"test_exchange_ttl","ttl.#",null);
    }


}
