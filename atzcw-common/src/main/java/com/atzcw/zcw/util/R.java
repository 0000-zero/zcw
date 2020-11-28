package com.atzcw.zcw.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zero
 * @create 2020-11-25 11:03
 * <p>
 * 统一返回数据类
 */
public class R extends HashMap<String, Object> {

    public R() {
        put("code", 0);
        put("msg", "success");
    }

    //错误返回
    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    //正常返回
    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    private <T> T getData(TypeReference<T> reference) {
        Object o = get("data");
        String s = JSON.toJSONString(o);
        T t = JSON.parseObject(s, reference);
        return t;
    }

    //利用 fastjson 将数据逆转为自己先要的数据
    public <T> T getData(String key, TypeReference<T> tTypeReference) {
        Object data = get(key);
        String s = JSON.toJSONString(data);
        T t = JSON.parseObject(s, tTypeReference);
        return t;

    }

    public R setData(Object data) {
        put("data", data);
        return this;
    }


}
