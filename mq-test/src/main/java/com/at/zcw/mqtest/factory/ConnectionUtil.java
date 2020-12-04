package com.at.zcw.mqtest.factory;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zero
 * @create 2020-12-04 12:32
 */
public class ConnectionUtil {

    public static Connection getConnection() throws IOException, TimeoutException {
        //获取连接工厂
        ConnectionFactory factory  = new ConnectionFactory();
        //设置服务地址 端口 账号密码虚拟机等信息


        factory.setHost("192.168.44.103");
        factory.setPort(5672);

        factory.setUsername("test");
        factory.setPassword("test");
        factory.setVirtualHost("/test");

        //获取连接
        Connection connection = factory.newConnection();
        return connection;

    }

//    public static void main(String[] args) {
//        try {
//            Connection connection = getConnection();
//            System.out.println("conn:"+connection);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//        }
//    }

}
