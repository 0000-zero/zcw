package com.at.test;

import redis.clients.jedis.Jedis;

/**
 * @author zero
 * @create 2020-11-28 18:57
 */
public class JedisTest {

    public static final String REDIS_HOST = "192.168.44.103";
    public static final int REDIS_PORT = 6379;

    public static void main(String[] args) {

        Jedis jedis = null;
        try {


            jedis = new Jedis(REDIS_HOST, REDIS_PORT);

            String ping = jedis.ping();

            System.out.println("ping:" + ping);
        } finally {
            if (jedis != null) {

                jedis.close();
            }
        }


    }
}
