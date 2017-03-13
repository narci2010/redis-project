package com.zhukm.redis.test;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by king on 2017/3/11.
 */
public class CrudTest {
    private Jedis jedis;

    @Before
    public void setup(){
        //连接redis服务器
        jedis = new Jedis("192.168.204.129", 6379);
        //权限认证
        jedis.auth("ri1yvi2txgt6");
    }

    @Test
    public void testString(){
        //添加数据
        jedis.set("test", "zhukm");
        System.out.println(jedis.get("test"));

        jedis.append("test", " is smart");
        System.out.println(jedis.get("test"));

        //删除key
        jedis.del("test");
        System.out.println(jedis.get("test"));

        //设置多个值
        jedis.mset("name", "zhukm", "age", "25", "qq", "104992110029");
        System.out.println(jedis.get("name") + jedis.get("qq"));

        jedis.incr("age");
        System.out.println(jedis.get("age"));
    }

    @Test
    public void testMap(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("map_name", "zhukm");
        map.put("map_age", "29");
        jedis.hmset("user", map);
        //返回是一个List，参数是key ,feild
        System.out.printf(jedis.hmget("user", "map_name", "map_age").toString());

        //删除map中一个key
        jedis.hdel("user", "map_name");
        System.out.println(jedis.hmget("user", "map_name").toString());

        //返回map size
        System.out.println(jedis.hlen("user"));

        //返回map中所有key
        System.out.println(jedis.hkeys("user"));

        //返回map中所有value
        System.out.println(jedis.hvals("user"));
    }

    @Test
    public void testList(){
        jedis.del("list_test");
        System.out.println(jedis.lrange("list", 0, -1));
        jedis.lpush("list_test", "spring");
        jedis.lpush("list_test", "jpa");
        jedis.lpush("list_test", "vue");
        System.out.println(jedis.lrange("list_test", 0, -1));

        jedis.del("list_test");

        jedis.rpush("list_test", "jpa");
        jedis.rpush("list_test", "spring");
        jedis.rpush("list_test", "vue");
        System.out.println(jedis.lrange("list_test", 0, -1));
        System.out.println(jedis.lpop("list_test"));
        System.out.println(jedis.lrange("list_test", 0, -1));
    }

    @Test
    public void testSet(){
        jedis.sadd("set_test", "spring");
        jedis.sadd("set_test", "jpa");
        jedis.sadd("set_test", "vue");
        jedis.sadd("set_test", "jpa");

        System.out.println(jedis.smembers("set_test"));
        //删除jpa
        jedis.srem("set_test", "jpa");
        System.out.println(jedis.smembers("set_test"));
        //判断jpa是否为set_test中的元素
        System.out.println(jedis.sismember("set_test", "jpa"));
        System.out.println(jedis.srandmember("set_test"));
        System.out.println(jedis.scard("set_test"));
    }

    @Test
    public void testPool(){
        Jedis redisFromPool = ReditUtil.getJedis();
        redisFromPool.set("pool_key", "pool_value");
        System.out.println(redisFromPool.get("pool_key"));
        ReditUtil.returnResource(redisFromPool);
    }
}
