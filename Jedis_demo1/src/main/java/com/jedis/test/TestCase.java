package com.jedis.test;

import com.jedis.utils.JedisUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;

import java.util.*;

/**
 * Redis常用方法测试类
 * Created by Administrator on 2015/7/19 0019.
 */
public class TestCase {

    /**
     * 在不同的线程中使用相同的Jedis实例会发生奇怪的错误。但是创建太多的实现也不好因为这意味着会建立很多sokcet连接，
     * 也会导致奇怪的错误发生。单一Jedis实例不是线程安全的。为了避免这些问题，可以使用JedisPool,
     * JedisPool是一个线程安全的网络连接池。可以用JedisPool创建一些可靠Jedis实例，可以从池中拿到Jedis的实例。
     * 这种方式可以解决那些问题并且会实现高效的性能
     */

    private JedisUtil jedisUtil;

    @Test
    public void Hello() {

        System.out.println("Test:Hello");
        jedisUtil = JedisUtil.getInstance();
        Jedis jedis = jedisUtil.getJedis("127.0.0.1", 6379);

        try {
            // 向key-->name中放入了value-->qingrong
            jedis.set("name", "qingrong");
            String ss = jedis.get("name");
            System.out.println("1:" + ss);

            // 很直观，类似map 将新的值 append到已经有的value之后
            jedis.append("name", "xiaoqin");
            ss = jedis.get("name");
            System.out.println("2:" + ss);

            // 直接覆盖原来的数据
            jedis.set("name", "xiaoqin");
            System.out.println("3:" + jedis.get("name"));

            // 删除key对应的记录
            jedis.del("name");
            System.out.println("4:" + jedis.get("name"));// 执行结果：null

            /**
             * mset相当于
             * jedis.set("name","minxr");
             * jedis.set("jarorwar","aaa");
             */
            jedis.mset("name1", "qringrong", "name2", "xiaoqin");
            System.out.println("5:" + jedis.mget("name1", "name2"));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisUtil.closeJedis(jedis, "127.0.0.1", 6379);
        }

    }

    @Test
    public void testKey() {

        System.out.println("Test:testKey");
        jedisUtil = JedisUtil.getInstance();
        Jedis jedis = jedisUtil.getJedis("127.0.0.1", 6379);

        //新加入一个key
        jedis.set("testKey_1", "testKey_1_value");

        // 判断key否存在
        System.out.println("1:" + jedis.exists("testKey_1"));
        System.out.println("2:" + jedis.get("testKey_1"));

        /**清空Redis缓存中的所有数据*/
        System.out.println("3:" + jedis.flushDB());

        // 判断key否存在
        System.out.println("4:" + jedis.exists("testKey_1"));
        System.out.println("5:" + jedis.get("testKey_1"));
    }

    @Test
    public void testString() {

        System.out.println("Test:testString");
        jedisUtil = JedisUtil.getInstance();
        Jedis jedis = jedisUtil.getJedis("127.0.0.1", 6379);

        try {

            // 清空数据
            System.out.println("flushDB:" + jedis.flushDB());

            // 存储数据
            jedis.set("testString_1", "testString_1_value1");
            System.out.println("1:" + jedis.get("testString_1"));

            // 若key不存在，则存储
            //jedis.del("testString_1");//删除testString_1
            jedis.setnx("testString_1", "testString_1_value2");
            System.out.println("2:" + jedis.get("testString_1"));

            // 覆盖数据
            jedis.set("testString_1", "testString_1_value3");
            System.out.println("3:" + jedis.get("testString_1"));

            // 追加数据
            jedis.append("testString_1", "testString_1_value4");
            System.out.println("4:" + jedis.get("testString_1"));

            // 设置key的有效期，并存储数据
            jedis.setex("testString_1", 2, "testString_1_value5");
            System.out.println("5:" + jedis.get("testString_1"));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            System.out.println("6:" + jedis.get("testString_1"));

            // 获取并返回原来数据，然后更新数据
            jedis.set("testString_1", "testString_1 update");
            System.out.println("7:" + jedis.getSet("testString_1", "testString_1 modify"));
            System.out.println("8:" + jedis.get("testString_1"));

            // 截取value的值
            System.out.println("9:" + jedis.getrange("testString_1", 1, 3));

            //批量插入数据
            System.out.println("10:" + jedis.mset("mset1", "mvalue1", "mset2", "mvalue2",
                    "mset3", "mvalue3", "mset4", "mvalue4"));
            System.out.println("11:" + jedis.mget("mset1", "mset2", "mset3", "mset4"));

            //批量删除数据
            System.out.println("12:" + jedis.del(new String[]{"mset1", "mset2", "mset3"}));
            System.out.println("13:" + jedis.mget("mset1", "mset2", "mset3", "mset4"));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisUtil.closeJedis(jedis, "127.0.0.1", 6379);
        }
    }


    @Test
    public void testList() {

        System.out.println("Test:testList");
        jedisUtil = JedisUtil.getInstance();
        Jedis jedis = jedisUtil.getJedis("127.0.0.1", 6379);

        try {
            // 清空数据
            System.out.println("flushDB:" + jedis.flushDB());

            //添加数据
            jedis.rpush("messages", "testList_1_value1");
            jedis.rpush("messages", "testList_1_value2");
            jedis.rpush("messages", "testList_1_value3");

            // 再取出所有数据jedis.lrange是按范围取出，
            // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
            List<String> values = jedis.lrange("messages", 0, -1);
            System.out.println("1:" + values);

            // 清空数据
            System.out.println("flushDB:" + jedis.flushDB());

            // 添加数据
            jedis.lpush("lists", "testList_2_value1");
            jedis.lpush("lists", "testList_2_value2");
            jedis.lpush("lists", "testList_2_value3");

            // 数组长度
            System.out.println("2:" + jedis.llen("lists"));
            // 排序
//            System.out.println("3:" + jedis.sort("lists"));
            // 字串
            System.out.println("4:" + jedis.lrange("lists", 0, -1));

            // 修改列表中单个值
            jedis.lset("lists", 0, "testList_2_value4");
            System.out.println("5:" + jedis.lrange("lists", 0, -1));

            // 获取列表指定下标的值
            System.out.println("6:" + jedis.lindex("lists", 1));

            // 删除列表指定下标的值
            System.out.println("7:" + jedis.lrem("lists", 1, "testList_2_value2"));
            System.out.println("8:" + jedis.lrange("lists", 0, -1));

            // 删除区间以外的数据
            System.out.println("9:" + jedis.ltrim("lists", 0, 0));
            System.out.println("10:" + jedis.lrange("lists", 0, -1));

            // 列表出栈
            System.out.println("11:" + jedis.lpop("lists"));
            // 整个列表值
            System.out.println("12:" + jedis.lrange("lists", 0, -1));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisUtil.closeJedis(jedis, "127.0.0.1", 6379);
        }
    }


    @Test
    public void testSet() {
        System.out.println("Test:testSet");
        jedisUtil = JedisUtil.getInstance();
        Jedis jedis = jedisUtil.getJedis("127.0.0.1", 6379);

        try {
            // 清空数据
            System.out.println("flushDB:" + jedis.flushDB());

            // 添加数据
            jedis.sadd("sets", "HashSet");
            jedis.sadd("sets", "SortedSet");
            jedis.sadd("sets", "TreeSet");

            // 判断value是否在列表中
            System.out.println("1:" + jedis.sismember("sets", "TreeSet"));

            // 整个列表值
            System.out.println("2:" + jedis.smembers("sets"));

            // 删除指定元素
            System.out.println("3:" + jedis.srem("sets", "SortedSet"));
            System.out.println("4:" + jedis.smembers("sets"));

            // 出栈
            System.out.println("5:" + jedis.spop("sets"));
            System.out.println("6:" + jedis.smembers("sets"));

            //
            jedis.sadd("sets1", "HashSet1");
            jedis.sadd("sets1", "SortedSet1");
            jedis.sadd("sets1", "TreeSet");
            jedis.sadd("sets2", "HashSet2");
            jedis.sadd("sets2", "SortedSet1");
            jedis.sadd("sets2", "TreeSet1");
            // 交集
            System.out.println("7:" + jedis.sinter("sets1", "sets2"));
            // 并集
            System.out.println("8:" + jedis.sunion("sets1", "sets2"));
            // 差集
            System.out.println("9:" + jedis.sdiff("sets1", "sets2"));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisUtil.closeJedis(jedis, "127.0.0.1", 6379);
        }
    }


    @Test
    public void testSortedSet() {

        System.out.println("Test:testSortedSet");
        jedisUtil = JedisUtil.getInstance();
        Jedis jedis = jedisUtil.getJedis("127.0.0.1", 6379);

        try {

            // 清空数据
            System.out.println("flushDB:" + jedis.flushDB());

            jedis.zadd("hackers", 1940, "Alan Kay");
            jedis.zadd("hackers", 1953, "Richard Stallman");
            jedis.zadd("hackers", 1965, "Yukihiro Matsumoto");
            jedis.zadd("hackers", 1916, "Claude Shannon");
            jedis.zadd("hackers", 1969, "Linus Torvalds");
            jedis.zadd("hackers", 1912, "Alan Turing");

            //顺序取出
            Set<String> setValues = jedis.zrange("hackers", 0, -1);
            System.out.println("1:" + setValues);

            //倒序取出
            Set<String> setValues2 = jedis.zrevrange("hackers", 0, -1);
            System.out.println("2:" + setValues2);

            // 清空数据
            System.out.println("flushDB:" + jedis.flushDB());

            // 添加数据
            jedis.zadd("zset", 10.1, "hello");
            jedis.zadd("zset", 10.0, ":");
            jedis.zadd("zset", 9.0, "zset");
            jedis.zadd("zset", 11.0, "zset!");

            // 元素个数
            System.out.println("3:" + jedis.zcard("zset"));

            // 元素下标
            System.out.println("4:" + jedis.zscore("zset", "zset"));

            // 集合子集
            System.out.println("5:" + jedis.zrange("zset", 0, 2));

            // 删除元素
            System.out.println("6:" + jedis.zrem("zset", "zset"));
            System.out.println("7:" + jedis.zcount("zset", 9.5, 10.5));

            // 整个集合值
            System.out.println("8:" + jedis.zrange("zset", 0, -1));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisUtil.closeJedis(jedis, "127.0.0.1", 6379);
        }
    }


    @Test
    public void testHsh() {

        System.out.println("Test:testSortedSet");
        jedisUtil = JedisUtil.getInstance();
        Jedis jedis = jedisUtil.getJedis("127.0.0.1", 6379);

        try {

            // 清空数据
            System.out.println("flushDB:" + jedis.flushDB());

            Map<String, String> pairs = new HashMap<String, String>();
            pairs.put("name", "Akshi");
            pairs.put("age", "2");
            pairs.put("sex", "Female");

            //添加Map
            jedis.hmset("kid", pairs);

            // 结果是个泛型的LIST
            List<String> name = jedis.hmget("kid", "name");
            System.out.println("1:" + name);

            // 为key中的域 field 的值加上增量 increment
            System.out.println("2:" + jedis.hincrBy("kid", "age", 20));

            //删除map中的某个键值
            jedis.hdel("kid", "age");
            System.out.println("3:" + jedis.hmget("kid", "age")); // 因为删除了，所以返回的是null

            // 返回key为user的键中存放的值的个数
            System.out.println("4:" + jedis.hlen("kid"));

            // 是否存在key为kid的记录
            System.out.println("5:" + jedis.exists("kid"));

            // 判断某个值是否存在
            System.out.println("6:" + jedis.hexists("kid", "sex"));

            // 返回map对象中的所有key
            System.out.println("7:" + jedis.hkeys("kid"));

            // 返回map对象中的所有value
            System.out.println("8:" + jedis.hvals("kid"));

            //返回Map中指定的key对应的value
            List<String> values = jedis.hmget("kid", new String[]{"name", "age", "sex"});
            System.out.println("9:" + values);
            System.out.println("10:" + jedis.hmget("hashs", "name", "age", "sex"));

            //返回Map中的键值对，json格式,{sex=Female, name=Akshi}
            pairs = jedis.hgetAll("kid");
            System.out.println("11:" + pairs);

            Iterator<String> iter = jedis.hkeys("kid").iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                System.out.println(key + ":" + jedis.hmget("kid", key));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisUtil.closeJedis(jedis, "127.0.0.1", 6379);
        }
    }


    @Test
    public void testOther() {

        System.out.println("Test:testSortedSet");
        jedisUtil = JedisUtil.getInstance();
        Jedis jedis = jedisUtil.getJedis("127.0.0.1", 6379);

        try {

            // 清空数据
            System.out.println("flushDB:" + jedis.flushDB());

            jedis.set("testOther_1", "testOther_1_value");
            jedis.set("testOther_2", "testOther_2_value");
            jedis.set("testOther_3", "testOther_3_value");
            jedis.set("testOther_4", "testOther_4_value");

            // keys中传入的可以用通配符

            // 返回当前库中所有的key
            System.out.println(jedis.keys("*"));

            // 返回的
            System.out.println(jedis.keys("*_2"));

            // 删除key为sanmdde的对象 删除成功返回1,删除失败（或者不存在）返回 0
            System.out.println(jedis.del("testOther_3"));
            System.out.println(jedis.keys("*"));

            // 返回给定key的有效时间，如果是-1则表示永远有效
            System.out.println(jedis.ttl("testOther_1"));

            // 通过此方法，可以指定key的存活（有效时间） 时间为秒
            jedis.setex("testOther_4", 10, "min");
            System.out.println(jedis.ttl("testOther_4"));
            Thread.sleep(5000);// 睡眠5秒后，剩余时间将为<=5
            System.out.println(jedis.ttl("testOther_4"));

            //重命名某个key
            System.out.println(jedis.rename("testOther_2", "testOther_5"));
            System.out.println(jedis.get("testOther_2"));// 因为移除，返回为null
            System.out.println(jedis.get("testOther_5")); // 因为将timekey 重命名为time

            // 所以可以取得值 min
            // jedis 排序
            // 注意，此处的rpush和lpush是List的操作。是一个双向链表（但从表现来看的）
            //rpush从尾插入，lpush从头插入
            jedis.del("a");// 先清除数据，再加入数据进行测试
            jedis.rpush("a", "1");
            jedis.lpush("a", "6");
            jedis.rpush("a", "3");
            jedis.lpush("a", "9");
            System.out.println(jedis.lrange("a", 0, -1));// [9, 6, 1, 3]
            System.out.println(jedis.sort("a"));//[1, 3, 6, 9]
            //排序后并不保存
            System.out.println(jedis.lrange("a", 0, -1));// [9, 6, 1, 3]

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisUtil.closeJedis(jedis, "127.0.0.1", 6379);
        }

    }


    @Test
    public void testUnUsePipeline() {

        System.out.println("Test:testUnUsePipeline");
        jedisUtil = JedisUtil.getInstance();
        Jedis jedis = jedisUtil.getJedis("127.0.0.1", 6379);

        try {

            long start = new Date().getTime();

            for (int i = 0; i < 10000; i++) {
                jedis.set("age1" + i, i + "");
                jedis.get("age1" + i);// 每个操作都发送请求给redis-server
            }
            long end = new Date().getTime();

            System.out.println("unuse pipeline cost:" + (end - start) + "ms");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisUtil.closeJedis(jedis, "127.0.0.1", 6379);
        }
    }


    /**
     * 时间复杂度：
     * O(N+M*log(M))， N 为要排序的列表或集合内的元素数量， M 为要返回的元素数量。
     * 如果只是使用 SORT 命令的 GET 选项获取数据而没有进行排序，时间复杂度 O(N)。
     */
    @Test
    public void testSort1() {

        System.out.println("Test:testUnUsePipeline");
        jedisUtil = JedisUtil.getInstance();
        Jedis jedis = jedisUtil.getJedis("127.0.0.1", 6379);

        try {
            // 排序默认以数字作为对象，值被解释为双精度浮点数，然后进行比较
            // 一般SORT用法 最简单的SORT使用方法是SORT key。
            jedis.lpush("mylist", "1");
            jedis.lpush("mylist", "4");
            jedis.lpush("mylist", "6");
            jedis.lpush("mylist", "3");
            jedis.lpush("mylist", "0");
            // List<String> list = jedis.sort("sort");// 默认是升序
            SortingParams sortingParameters = new SortingParams();
            sortingParameters.desc();
            // sortingParameters.alpha();//当数据集中保存的是字符串值时，你可以用 ALPHA
            // 修饰符(modifier)进行排序。
            sortingParameters.limit(0, 2);// 可用于分页查询
            List<String> list = jedis.sort("mylist", sortingParameters);// 默认是升序
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }

            sortingParameters.limit(2, 6);// 可用于分页查询
            list = jedis.sort("mylist", sortingParameters);
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
            // 清空数据
            System.out.println("flushDB:" + jedis.flushDB());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisUtil.closeJedis(jedis, "127.0.0.1", 6379);
        }
    }


    @Test
    public void testSort2() {

        System.out.println("Test:testSort2");
        jedisUtil = JedisUtil.getInstance();
        Jedis jedis = jedisUtil.getJedis("127.0.0.1", 6379);

        try {

            // 清空数据
            System.out.println("flushDB:" + jedis.flushDB());

            jedis.lpush("userlist", "33");
            jedis.lpush("userlist", "22");
            jedis.lpush("userlist", "55");
            jedis.lpush("userlist", "11");

            jedis.hset("user:66", "name", "66");
            jedis.hset("user:55", "name", "55");
            jedis.hset("user:33", "name", "33");
            jedis.hset("user:22", "name", "79");
            jedis.hset("user:11", "name", "24");
            jedis.hset("user:11", "add", "beijing");
            jedis.hset("user:22", "add", "shanghai");
            jedis.hset("user:33", "add", "guangzhou");
            jedis.hset("user:55", "add", "chongqing");
            jedis.hset("user:66", "add", "xi'an");

            SortingParams sortingParameters = new SortingParams();
            // 符号 "->" 用于分割哈希表的键名(key name)和索引域(hash field)，格式为 "key->field" 。
            sortingParameters.get("#");
            sortingParameters.get("user:*->name");
            sortingParameters.get("user:*->add");
//      sortingParameters.by("user:*->name");
            List<String> result = jedis.sort("userlist", sortingParameters);
            for (String item : result) {
                System.out.println("item...." + item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisUtil.closeJedis(jedis, "127.0.0.1", 6379);
        }
    }


    /**
     * sort set
     * SET结合String的排序
     */
    @Test
    public void testSort3() {

        System.out.println("Test:testSort2");
        jedisUtil = JedisUtil.getInstance();
        Jedis jedis = jedisUtil.getJedis("127.0.0.1", 6379);

        try {

            // 清空数据
            System.out.println("flushDB:" + jedis.flushDB());

            jedis.sadd("tom:friend:list", "123"); // tom的好友列表
            jedis.sadd("tom:friend:list", "456");
            jedis.sadd("tom:friend:list", "789");
            jedis.sadd("tom:friend:list", "101");

            jedis.set("score:uid:123", "1000"); // 好友对应的成绩
            jedis.set("score:uid:456", "6000");
            jedis.set("score:uid:789", "100");
            jedis.set("score:uid:101", "5999");

            jedis.set("uid:123", "{'uid':123,'name':'lucy'}"); // 好友的详细信息
            jedis.set("uid:456", "{'uid':456,'name':'jack'}");
            jedis.set("uid:789", "{'uid':789,'name':'jay'}");
            jedis.set("uid:101", "{'uid':101,'name':'jolin'}");

            SortingParams sortingParameters = new SortingParams();

            sortingParameters.desc();
            // sortingParameters.limit(0, 2);
            // 注意GET操作是有序的，GET user_name_* GET user_password_* 和 GET user_password_* GET user_name_*返回的结果位置不同
            // GET 还有一个特殊的规则—— "GET #"，用于获取被排序对象(我们这里的例子是 user_id )的当前元素。
//            sortingParameters.get("#");
            sortingParameters.get("uid:*");
//            sortingParameters.get("score:uid:*");
            sortingParameters.by("score:uid:*");
            // 对应的redis 命令是./redis-cli sort tom:friend:list by score:uid:* get # get uid:* get score:uid:*
            List<String> result = jedis.sort("tom:friend:list", sortingParameters);
            for (String item : result) {
                System.out.println("item..." + item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisUtil.closeJedis(jedis, "127.0.0.1", 6379);
        }

    }


    /**
     * 保存排序结果 默认情况下， SORT 操作只是简单地返回排序结果，如果你希望保存排序结果，可以给 STORE 选项指定一个 key
     * 作为参数，排序结果将以列表的形式被保存到这个 key 上。(若指定 key 已存在，则覆盖。)  一个有趣的用法是将 SORT
     * 结果保存，用 EXPIRE 为结果集设置生存时间，这样结果集就成了 SORT 操作的一个缓存。 这样就不必频繁地调用 SORT
     * 操作了，只有当结果集过期时，才需要再调用一次 SORT 操作。
     * 有时候为了正确实现这一用法，你可能需要加锁以避免多个客户端同时进行缓存重建(也就是多个客户端，同一时间进行 SORT
     * 操作，并保存为结果集)，具体参见 SETNX 命令。
     */
    @Test
    public void testSort5() {

        System.out.println("Test:testSort2");
        jedisUtil = JedisUtil.getInstance();
        Jedis jedis = jedisUtil.getJedis("127.0.0.1", 6379);

        try {

            // 清空数据
            System.out.println("flushDB:" + jedis.flushDB());

            // 排序默认以数字作为对象，值被解释为双精度浮点数，然后进行比较
            // 一般SORT用法 最简单的SORT使用方法是SORT key。
            jedis.lpush("mylist", "1");
            jedis.lpush("mylist", "4");
            jedis.lpush("mylist", "6");
            jedis.lpush("mylist", "3");
            jedis.lpush("mylist", "0");
            // List<String> list = redis.sort("sort");// 默认是升序
            SortingParams sortingParameters = new SortingParams();
            sortingParameters.desc();
            // sortingParameters.alpha();//当数据集中保存的是字符串值时，你可以用 ALPHA
            // 修饰符(modifier)进行排序。
            // sortingParameters.limit(0, 2);//可用于分页查询

            // 没有使用 STORE 参数，返回列表形式的排序结果. 使用 STORE 参数，返回排序结果的元素数量。
            // 排序后指定排序结果到一个KEY中，这里讲结果覆盖原来的KEY
            jedis.sort("mylist", sortingParameters, "mylist");

            List<String> list = jedis.lrange("mylist", 0, -1);
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }

            jedis.sadd("tom:friend:list", "123"); // tom的好友列表
            jedis.sadd("tom:friend:list", "456");
            jedis.sadd("tom:friend:list", "789");
            jedis.sadd("tom:friend:list", "101");

            jedis.set("score:uid:123", "1000"); // 好友对应的成绩
            jedis.set("score:uid:456", "6000");
            jedis.set("score:uid:789", "100");
            jedis.set("score:uid:101", "5999");

            jedis.set("uid:123", "{'uid':123,'name':'lucy'}"); // 好友的详细信息
            jedis.set("uid:456", "{'uid':456,'name':'jack'}");
            jedis.set("uid:789", "{'uid':789,'name':'jay'}");
            jedis.set("uid:101", "{'uid':101,'name':'jolin'}");

            sortingParameters = new SortingParams();
            // sortingParameters.desc();
            // GET 还有一个特殊的规则—— "GET #"，用于获取被排序对象(我们这里的例子是 user_id )的当前元素。
            sortingParameters.get("#");
            sortingParameters.by("score:uid:*");
            jedis.sort("tom:friend:list", sortingParameters, "tom:friend:list");
            List<String> result = jedis.lrange("tom:friend:list", 0, -1);
            for (String item : result) {
                System.out.println("item..." + item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisUtil.closeJedis(jedis, "127.0.0.1", 6379);
        }
    }


    @Test
    public void testDB() {

        System.out.println("Test:testSort2");
        jedisUtil = JedisUtil.getInstance();
        Jedis jedis = jedisUtil.getJedis("127.0.0.1", 6379);

        try {

            // 清空数据
            System.out.println("flushDB:" + jedis.flushDB());

            jedis.sadd("tom:friend:list", "123"); // tom的好友列表
            jedis.sadd("tom:friend:list", "456");
            jedis.sadd("tom:friend:list", "789");
            jedis.sadd("tom:friend:list", "101");

            jedis.set("score:uid:123", "1000"); // 好友对应的成绩
            jedis.set("score:uid:456", "6000");
            jedis.set("score:uid:789", "100");
            jedis.set("score:uid:101", "5999");

            jedis.set("uid:123", "{'uid':123,'name':'lucy'}"); // 好友的详细信息
            jedis.set("uid:456", "{'uid':456,'name':'jack'}");
            jedis.set("uid:789", "{'uid':789,'name':'jay'}");
            jedis.set("uid:101", "{'uid':101,'name':'jolin'}");

            // 通过索引选择数据库，默认连接的数据库所有是0,默认数据库数是16个。返回1表示成功，0失败
            System.out.println(jedis.select(0));

            // dbsize 返回当前数据库的key数量
            System.out.println(jedis.dbSize());

            // 返回匹配指定模式的所有key
            System.out.println(jedis.keys("*"));

            //随机获取一个key值,例 uid:789
            System.out.println(jedis.randomKey());

            // 删除当前数据库中所有key,此方法不会失败。慎用
            jedis.flushDB();

            // 删除所有数据库中的所有key，此方法不会失败。更加慎用
            jedis.flushAll();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisUtil.closeJedis(jedis, "127.0.0.1", 6379);
        }
    }

    @Test
    public void testMget() {

        System.out.println("Test:testSort2");
        jedisUtil = JedisUtil.getInstance();
        Jedis jedis = jedisUtil.getJedis("127.0.0.1", 6379);

        try {

            // 清空数据
            System.out.println("flushDB:" + jedis.flushDB());

            jedis.rpush("ids", "aa");
            jedis.rpush("ids", "bb");
            jedis.rpush("ids", "cc");

            List<String> ids = jedis.lrange("ids", 0, -1);
            System.out.println(ids);

            jedis.set("aa", "{'name':'zhoujie','age':20}");
            jedis.set("bb", "{'name':'yilin','age':28}");
            jedis.set("cc", "{'name':'lucy','age':21}");
            List<String> list = jedis.mget(ids.toArray(new String[ids.size()]));
            System.out.println(list);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisUtil.closeJedis(jedis, "127.0.0.1", 6379);
        }
    }

    /**
     * 可以利用lrange对list进行分页操作
     */
    @Test
    public void queryPageBy() {

        System.out.println("Test:testSort2");
        jedisUtil = JedisUtil.getInstance();
        Jedis jedis = jedisUtil.getJedis("127.0.0.1", 6379);

        try {

            // 清空数据
            System.out.println("flushDB:" + jedis.flushDB());

            int pageNo = 6;
            int pageSize = 6;
            int temp = 0;

            for (int i = 1; i <= 30; i++) {
                jedis.rpush("a", i + "");
            }

            for (int i = 0; i < pageNo; i++) {

                System.out.println("temp=" + temp);

                int start = pageSize * temp;// 因为redis中list元素位置基数是0
                int end = pageSize * temp + pageSize - 1;

                List<String> results = jedis.lrange("a", start, end);// 从start算起，start算一个元素，到结束那个元素
                for (String str : results) {
                    System.out.println(str);
                }
                temp++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisUtil.closeJedis(jedis, "127.0.0.1", 6379);
        }
    }

    /**
     * [向Redis list压入ID而不是实际的数据]
     * 在上面的例子里 ，我们将“对象”（此例中是简单消息）直接压入Redis list，但通常不应这么做，
     * 由于对象可能被多次引用：例如在一个list中维护其时间顺序，在一个集合中保存它的类别，只要有必要，它还会出现在其他list中，等等。
     * 让我们回到reddit.com的例子，将用户提交的链接（新闻）添加到list中，有更可靠的方法如下所示：
     * 我们自增一个key，很容易得到一个独一无二的自增ID，然后通过此ID创建对象–为对象的每个字段设置一个key。最后将新对象的ID压入submitted.news list。
     * 这只是牛刀小试。在命令参考文档中可以读到所有和list有关的命令。你可以删除元素，旋转list，根据索引获取和设置元素，当然也可以用LLEN得到list的长度。
     */
    @Test
    public void testListStrUsage() {

        System.out.println("Test:testSort2");
        jedisUtil = JedisUtil.getInstance();
        Jedis jedis = jedisUtil.getJedis("127.0.0.1", 6379);

        try {

            // 清空数据
            System.out.println("flushDB:" + jedis.flushDB());

            String title = "太阳能是绿色能源";
            String url = "http://javacreazyer.iteye.com";
            long adInfoId = jedis.incr("ad:adinfo:next.id");

            jedis.set("ad:adinfo:" + adInfoId + ":title", title);
            jedis.set("ad:adinfo:" + adInfoId + ":url", url);
            jedis.lpush("ad:adinfo", String.valueOf(adInfoId));

            String resultTitle = jedis.get("ad:adinfo:" + adInfoId + ":title");
            String resultUrl = jedis.get("ad:adinfo:" + adInfoId + ":url");
            List<String> ids = jedis.lrange("ad:adinfo", 0, -1);
            System.out.println("" + resultTitle);
            System.out.println(resultUrl);
            System.out.println(ids);

            /**
             * dbsize返回的是所有key的数目，包括已经过期的， 而redis-cli keys "*"查询得到的是有效的key数目
             */
            System.out.println(jedis.dbSize());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisUtil.closeJedis(jedis, "127.0.0.1", 6379);
        }
    }


    /**
     * 下面是一个简单的方案：对每个想加标签的对象，用一个标签ID集合与之关联，并且对每个已有的标签，一组对象ID与之关联。 例如假设我们的新闻ID
     * 1000被加了三个标签tag 1,2,5和77，就可以设置下面两个集合：
     * 要获取一个对象的所有标签，如此简单：
     * 而有些看上去并不简单的操作仍然能使用相应的Redis命令轻松实现。例如我们也许想获得一份同时拥有标签1, 2,5和77的对象列表。
     * 这可以用SINTER命令来做，他可以在不同集合之间取出交集。因此为达目的我们只需：
     * 在命令参考文档中可以找到和集合相关的其他命令，令人感兴趣的一抓一大把。一定要留意SORT命令，Redis集合和list都是可排序的。
     */
    @Test
    public void testSetUsage() {

        System.out.println("Test:testSort2");
        jedisUtil = JedisUtil.getInstance();
        Jedis jedis = jedisUtil.getJedis("127.0.0.1", 6379);

        try {

            // 清空数据
            System.out.println("flushDB:" + jedis.flushDB());

            jedis.sadd("zhongsou:news:1000:tags", "1");
            jedis.sadd("zhongsou:news:1000:tags", "2");
            jedis.sadd("zhongsou:news:1000:tags", "5");
            jedis.sadd("zhongsou:news:1000:tags", "77");

            jedis.sadd("zhongsou:news:2000:tags", "1");
            jedis.sadd("zhongsou:news:2000:tags", "2");
            jedis.sadd("zhongsou:news:2000:tags", "5");
            jedis.sadd("zhongsou:news:2000:tags", "77");

            jedis.sadd("zhongsou:news:3000:tags", "2");
            jedis.sadd("zhongsou:news:3000:tags", "5");

            jedis.sadd("zhongsou:news:4000:tags", "77");

            jedis.sadd("zhongsou:news:5000:tags", "1");

            jedis.sadd("zhongsou:news:6000:tags", "5");

            /**取参数对应结果集中的交集*/
            Set<String> sets1 = jedis.sinter("zhongsou:news:1000:tags",
                    "zhongsou:news:2000:tags", "zhongsou:news:3000:tags");
            System.out.println(sets1);

            jedis.sadd("zhongsou:tag:1:objects", 1000 + "");
            jedis.sadd("zhongsou:tag:1:objects", 2000 + "");

            jedis.sadd("zhongsou:tag:2:objects", 1000 + "");
            jedis.sadd("zhongsou:tag:2:objects", 2000 + "");

            jedis.sadd("zhongsou:tag:5:objects", 1000 + "");
            jedis.sadd("zhongsou:tag:5:objects", 2000 + "");

            jedis.sadd("zhongsou:tag:77:objects", 1000 + "");
            jedis.sadd("zhongsou:tag:77:objects", 2000 + "");

            Set<String> sets2 = jedis.sinter("zhongsou:tag:1:objects",
                    "zhongsou:tag:2:objects", "zhongsou:tag:5:objects",
                    "zhongsou:tag:77:objects");
            System.out.println(sets2);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisUtil.closeJedis(jedis, "127.0.0.1", 6379);
        }
    }


    @Test
    public void testSortedSetUsage() {

        System.out.println("Test:testSort2");
        jedisUtil = JedisUtil.getInstance();
        Jedis jedis = jedisUtil.getJedis("127.0.0.1", 6379);

        try {
            // 清空数据
            System.out.println("flushDB:" + jedis.flushDB());

            jedis.zadd("zhongsou:hackers", 1940, "Alan Kay");
            jedis.zadd("zhongsou:hackers", 1953, "Richard Stallman");
            jedis.zadd("zhongsou:hackers", 1943, "Jay");
            jedis.zadd("zhongsou:hackers", 1920, "Jellon");
            jedis.zadd("zhongsou:hackers", 1965, "Yukihiro Matsumoto");
            jedis.zadd("zhongsou:hackers", 1916, "Claude Shannon");
            jedis.zadd("zhongsou:hackers", 1969, "Linus Torvalds");
            jedis.zadd("zhongsou:hackers", 1912, "Alan Turing");

            //升序排列
            Set<String> hackers = jedis.zrange("zhongsou:hackers", 0, -1);
            System.out.println(hackers);

            //降序排列
            Set<String> hackers2 = jedis.zrevrange("zhongsou:hackers", 0, -1);
            System.out.println(hackers2);

            // 区间操作,我们请求Redis返回score介于负无穷到1920年之间的元素（两个极值也包含了）。
            Set<String> hackers3 = jedis.zrangeByScore("zhongsou:hackers", "-inf", "1920");
            System.out.println(hackers3);

            // ZREMRANGEBYSCORE 这个名字虽然不算好，但他却非常有用，还会返回已删除的元素数量。
            long num = jedis.zremrangeByScore("zhongsou:hackers", "-inf", "1920");
            System.out.println(num);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisUtil.closeJedis(jedis, "127.0.0.1", 6379);
        }
    }
}
