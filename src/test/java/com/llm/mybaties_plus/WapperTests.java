package com.llm.mybaties_plus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.llm.mybaties_plus.dao.UserDao;
import com.llm.mybaties_plus.domain.User;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class WapperTests {


    // 继承了BaseMapper，所有的方法都来自己父类
    // 我们也可以编写自己的扩展方法！
    @Autowired
    private UserDao userDao;

    @Test
    void contextLoads() {
        // 查询name不为空的用户，并且邮箱不为空的用户，年龄大于等于12
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("name").isNotNull("email").ge("age", 12);
        userDao.selectList(wrapper).forEach(System.out::println); // 和我们刚才学习 的map对比一下
    }

    @Test
    void test2() {
        // 查询名字狂神说
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "狂神说");
        User user = userDao.selectOne(wrapper); // 查询一个数据，出现多个结果使用List 或者 Map System.out.println(user);
    }

    @Test
    void test3() {
        // 查询年龄在 20 ~ 30 岁之间的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age", 20, 30); // 区间
        Integer count = userDao.selectCount(wrapper);// 查询结果数
        System.out.println(count);
    }

    // 模糊查询
    @Test
    void test4() {
        // 查询年龄在 20 ~ 30 岁之间的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>(); // 左和右 t%
        wrapper.notLike("name", "e").likeRight("email", "t");
        List<Map<String, Object>> maps = userDao.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    //测试六
    @Test
    void test6() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 通过id进行排序 Asc升序排序  desc降序排序
        wrapper.orderByDesc("id");
        List<User> users = userDao.selectList(wrapper);
        users.forEach(System.out::println);
    }
}