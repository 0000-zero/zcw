package com.at.zcw.estest;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class EsTestApplicationTests {

    private static RestHighLevelClient restHighLevelClient;

    @BeforeAll
    public static void init(){
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("192.168.44.103",9200));
        restHighLevelClient = new RestHighLevelClient(restClientBuilder);
    }

    @AfterAll
    public static void destroy() throws IOException {
        restHighLevelClient.close();
    }

    @Test
    void createAsync(){

        Guli guli = new Guli();
        guli.setId(9999);
        guli.setTitle("一个小区");
        guli.setPrice(9980);

        String s = JSON.toJSONString(guli);

        IndexRequest index = new IndexRequest("guli").id(guli.getId().toString()).source(s,XContentType.JSON);
        Cancellable cancellable = restHighLevelClient.indexAsync(index, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {
                System.out.println("成功回调："+indexResponse);
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("失败回调："+e.getMessage());
            }
        });

        try { Thread.sleep(3000); } catch (InterruptedException e) {e.printStackTrace();}

    }

    @Test
    void create() throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("id", 2003);
        data.put("title", "通波小区 二室一厅");
        data.put("price", 4000);

        IndexRequest index = new IndexRequest("guli").source(data);
        IndexResponse response = restHighLevelClient.index(index, RequestOptions.DEFAULT);

        System.out.println("id -> " + response.getId());
        System.out.println("version -> " + response.getVersion());
        System.out.println("result -> " + response.getResult());;

    }

    @Data
    class Guli{
        Integer id;
        String title;
        Integer price;
    }


    @Test
    void contextLoads() throws IOException {

        System.out.println("restHighLevelClient:"+restHighLevelClient);

    }

}
