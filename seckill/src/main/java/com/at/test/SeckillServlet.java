package com.at.test;

import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zero
 * @create 2020-11-28 19:27
 */
public class SeckillServlet extends HttpServlet {

    static String secKillScript = "local userid=KEYS[1];\r\n"
            + "local prodid=KEYS[2];\r\n"
            + "local qtkey='sk:'..prodid..\":qt\";\r\n"
            + "local usersKey='sk:'..prodid..\":usr\";\r\n"
            + "local userExists=redis.call(\"sismember\",usersKey,userid);\r\n"
            + "if tonumber(userExists)==1 then \r\n"
            + "   return 2;\r\n"
            + "end\r\n"
            + "local num= redis.call(\"get\" ,qtkey);\r\n"
            + "if tonumber(num)<=0 then \r\n"
            + "   return 0;\r\n"
            + "else \r\n"
            + "   redis.call(\"decr\",qtkey);\r\n"
            + "   redis.call(\"sadd\",usersKey,userid);\r\n"
            + "end\r\n"
            + "return 1";

    static String secKillScript2 = "local userExists=redis.call(\"sismember\",\"{sk}:0101:usr\",userid);\r\n"
            + " return 1";

    public static final String REDIS_HOST = "192.168.44.103";
    public static final int REDIS_PORT = 6379;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //1、获取请求参数：  商品id ， 用户id(随机生成)
        String pid = req.getParameter("pid");
        String uid = (int)(10000*Math.random())+"";
        System.out.println("uid = " + uid+" , pid = "+ pid);

        Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT);

        String sha1 = jedis.scriptLoad(secKillScript);

        Object result = jedis.evalsha(sha1, 2, uid, pid);//返回值脚本执行时return的内容
        long code = (long)result;
        if(code == 1){
            resp.getWriter().write("200");
        }else if(code == 0){
            resp.getWriter().write("10003");
        }else if(code == 2 ){
            resp.getWriter().write("10001");
        }
        jedis.close();



//
//        //拼接  库存key和 秒杀商品用户列表的key
//        String qtKey = "sk:"+ pid + ":qt";
//        String usrsKey = "sk:" + pid +":usr";
//        //2、判断用户是否重复秒杀[用户会存在redis中  set]
//        Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT);
//        Boolean sismember = jedis.sismember(usrsKey, uid);
//        if(sismember){
//            //用户重复秒杀
//            System.err.println("userid为："+uid +" 重复秒杀了....");
//            resp.getWriter().write("10001");
//            return;//结束当前方法
//        }
//
//        //添加乐观锁
//        jedis.watch(qtKey);
//
//        //3、判断库存是否足够
//        String qtStr = jedis.get(qtKey);
//        if(qtStr==null || qtStr.length()==0){
//            //库存值没有获取到，秒杀还未开始
//            System.err.println("userid为："+uid +" 秒杀尚未开始....");
//            resp.getWriter().write("10002");
//            return;//结束当前方法
//        }
//        int qt = Integer.parseInt(qtStr);
//        if(qt<=0){
//            //库存不足
//            System.err.println("userid为："+uid +" 库存不足....  qt: "+qt);
//            resp.getWriter().write("10003");
//            return;//结束当前方法
//        }
//
//        //添加队列
//        Transaction transaction = jedis.multi();
//
//        //4、秒杀
//        //将用户添加到秒杀成功的set中
//        transaction.sadd(usrsKey , uid);
//        //减库存
//        transaction.decr(qtKey);
//
//        //执行队列
//        transaction.exec();
//
//        //响应
//        System.out.println(uid+  ": 秒杀成功..");
//        resp.getWriter().write("200");


    }

}
