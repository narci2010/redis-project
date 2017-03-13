package com.zhukm.redis.service;

import com.zhukm.redis.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by king on 2017/3/12.
 */
@Service
public class UserService {
    @Cacheable(value = "userCache", keyGenerator = "keyGenerator")
    public User findUser(Long id) {
        System.out.println("db find user");
        return new User(1L, "zhu", "kangmin");
    }

    @Cacheable(value = "userListCache", keyGenerator = "keyGenerator")
    public List<User> findUserList(){
        System.out.println("find user list");
        List<User> list = new ArrayList<User>();
        list.add(new User(1L,"zhu", "kangmin"));
        list.add(new User(2L, "zou", "min"));
        return list;
    }

    @Cacheable(value = "userCacheById", key = "'key:'+#id.toString()")
    public User findUserById(Long id){
        System.out.println("find user from cache");
        return new User(id, "newName", "newLastName");
    }

    @Cacheable(value = "userCacheById", key = "'key:'+#id.toString()")
    public int delUserById(Long id){
        System.out.printf("delete user");
        return 1;
    }
}
