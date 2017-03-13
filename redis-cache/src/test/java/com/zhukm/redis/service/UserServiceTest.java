package com.zhukm.redis.service;


import com.zhukm.redis.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by king on 2017/3/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    JedisConnectionFactory jedisConnectionFactory;
    @Test
    public void findUser() throws Exception {
        User user = userService.findUser(1L);
        System.out.println(user.toString());
    }

    @Test
    public void findUserList() throws Exception {
        List<User> userList = userService.findUserList();
        System.out.printf(userList.toString());
    }

    @Test
    public void findUserById() throws Exception {
        User user = userService.findUserById(1L);
        System.out.printf(user.toString());
    }

    @Test
    public void delUserById() throws Exception {
        int i = userService.delUserById(1L);
        System.out.printf(i + "");
    }
}