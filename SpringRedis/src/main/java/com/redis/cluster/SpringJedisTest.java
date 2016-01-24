package com.redis.cluster;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/1/24 0024.
 */
public class SpringJedisTest {

    private JedisCluster jedisCluster;

    @Before
    public void init(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
        jedisCluster = applicationContext.getBean(JedisCluster.class);
    }

    /**
     * 获取集群中所有节点
     * */
    @Test
    public void test1(){
        Map<String,JedisPool> clusters = jedisCluster.getClusterNodes();
        System.out.println(clusters);
    }

    /**
     * 通过set 方法，向集群中添加一个String键值对
     * */
    @Test
    public void testSet(){
        String key = "testSet_hello";
        String value = "hello world";
        jedisCluster.set(key, value);
        System.out.println(jedisCluster.get(key));
    }

    /**
     * 通过del 方法，从集群中删除一个键值对
     * */
    @Test
    public void testDel(){
        String key = "testSet_hello";
        System.out.println(jedisCluster.get(key));
        jedisCluster.del(key);
        System.out.println(jedisCluster.get(key));
    }

    /**
     * 通过 hset方法，添加 set
     * */
    @Test
    public void testHmap(){
        //map 对应的主键
        String mapKey = "testHset";
        //map内部键值对
        String key1 = "key1";
        String value1 = "value1";
        String key2 = "key2";
        String value2 = "value2";
        String key3 = "key3";
        String value3 = "value3";
        String key4 = "key4";
        String value4 = "value4";
        //通过hset方法添加 map 中的一个元素
        jedisCluster.hset(mapKey,key1,value1);
        //通过hmset 方法添加整个map
        Map<String,String> map = new HashMap<String, String>();
        map.put(key2,value2);
        map.put(key3, value3);
        map.put(key4, value4);
        jedisCluster.hmset(mapKey, map);
        //通过hexists方法判断set中的一个元素是否存在
        System.out.println(jedisCluster.hexists(mapKey, key1));
        //通过hget方法获取 map 中的一个元素
        System.out.println(jedisCluster.hget(mapKey, key1));
        //通过hdel方法删除 map 中的元素
        System.out.println(jedisCluster.hdel(mapKey, key1));
        //通过hkeys方法获取 map 中的所有主键
        Set<String> set = jedisCluster.hkeys(mapKey);
        System.out.println(set);
        //通过 hgetAll 方法获取 map
        Map<String,String> allValue = jedisCluster.hgetAll(mapKey);
        System.out.println(allValue);
    }

    @Test
    public void testList(){

    }
}
