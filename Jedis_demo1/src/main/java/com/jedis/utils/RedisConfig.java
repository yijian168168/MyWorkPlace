package com.jedis.utils;

/**
 * Redis常量配置类
 * Created by Administrator on 2015/7/19 0019.
 */
public class RedisConfig {

    public static int maxTotal = 20;

    public static int maxIdle = 10;

    public static int maxWaitMillis = 600000;

    public static int timeout = 60000;

    public static int retryNum = 60000;

}
