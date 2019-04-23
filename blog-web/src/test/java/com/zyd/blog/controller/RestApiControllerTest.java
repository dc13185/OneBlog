package com.zyd.blog.controller;

import com.zyd.blog.BaseJunitTest;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Api接口单元测试
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/25 14:37
 * @since 1.0
 */
public class RestApiControllerTest extends BaseJunitTest {

    @Test
    public void qq() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(host + "/api/qq/843977358")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                // 打印出执行结果
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    public static void main(String[] args) {
        JedisPoolConfig config=new JedisPoolConfig(); // 连接池的配置对象
        config.setMaxTotal(100); // 设置最大连接数
        config.setMaxIdle(10); // 设置最大空闲连接数

        JedisPool jedisPool=new JedisPool(config,"123.207.146.122",6379);

        Jedis jedis=null;
        try{
            jedis=jedisPool.getResource(); // 获取连接
            jedis.auth("dc1318533144cg.."); // 设置密码
            jedis.set("name", "java知识分享网"); // 设置值
            String value=jedis.get("name"); // 获取值
            jedis.del("name");
            System.out.println(value);

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(jedis!=null){
                jedis.close();
            }
            if(jedisPool!=null){
                jedisPool.close();
            }
        }
    }
    @Test
    public void jedis(){

    }

}