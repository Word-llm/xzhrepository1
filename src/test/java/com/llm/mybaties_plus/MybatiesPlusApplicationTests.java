package com.llm.mybaties_plus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.llm.mybaties_plus.dao.UserDao;
import com.llm.mybaties_plus.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatiesPlusApplicationTests {


    // 继承了BaseMapper，所有的方法都来自己父类
    // 我们也可以编写自己的扩展方法！
    @Autowired
    private UserDao userDao;


    @Test
    void contextLoads() {
        // 参数是一个 Wrapper ，条件构造器，这里我们先不用 null
        // 查询全部用户
        List<User> users = userDao.selectList(null);
        users.forEach(System.out::println);    }


    // 测试插入
    @Test
    public void testInsert() {
        User user = new User();
        user.setName("狂神说Java");
        user.setAge(3);
        user.setEmail("24736743@qq.com");
        //此时并没有给id赋值，并且数据库并没有设置id自增所以报错，id没有默认值
        //解决方案在User类的id上加上注解@TableId(type = IdType.ID_WORKER)表示id默认值为全局唯一id
        int result = userDao.insert(user);// 帮我们自动生成id
        System.out.println(result); // 受影响的行数
        System.out.println(user); // 发现，id会自动回填
    }

    // 测试更新
    @Test
    public void testUpdate() {
        User user = new User();
        // 通过条件自动拼接动态sql
        user.setId(1279372442983444488L);
        user.setName("关注公众号：狂神说");
        user.setAge(18); // 注意：updateById 但是参数是一个 对象！
        int i = userDao.updateById(user);
        System.out.println(i);
    }


    // 测试乐观锁成功！
    @Test
    public void testOptimisticLocker() {
        // 1、查询用户信息
        User user = userDao.selectById(1L);
        System.out.println("user ================= " + user);
        // 2、修改用户信息
        user.setName("kuangshen");
        user.setEmail("24736743@qq.com");
        // 3、执行更新操作
        userDao.updateById(user);
    }

    // 测试乐观锁失败！多线程下
    @Test
    public void testOptimisticLocker2() {
        // 线程 1
        User user = userDao.selectById(1L);
        user.setName("kuangshen3333");
        user.setEmail("24736743@qq.com");
        // 模拟另外一个线程执行了插队操作
        User user2 = userDao.selectById(1L);
        user2.setName("kuangshen4444");
        user2.setEmail("24736743@qq.com");
        userDao.updateById(user2);
        // 自旋锁来多次尝试提交！
        userDao.updateById(user); // 如果没有乐观锁就会覆盖插队线程的值！
    }


    // 测试批量查询！
    @Test
    public void testSelectByBatchId() {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        List<User> users = userDao.selectBatchIds(integers);
        users.forEach(System.out::println);
    }

    // 按条件查询之一使用map操作
    @Test
    public void testSelectByBatchIds() {
        HashMap<String, Object> map = new HashMap<>();
        // 自定义要查询
        map.put("name", "狂神说Java");
        map.put("age", 3);
        List<User> users = userDao.selectByMap(map);
        users.forEach(System.out::println);
    }


    // 测试分页查询
    @Test
    public void testPage() {
        // 参数一：当前页
        // 参数二：页面大小
        // 使用了分页插件之后，所有的分页操作也变得简单的！
        Page<User> page = new Page<>(1, 5);
        userDao.selectPage(page, null);
        page.getRecords().forEach(System.out::println);
//        System.out.println(page.getTotal());
    }


    // 测试删除
//    @Test
//    public void testDeleteById() {
//        userDao.deleteById(1);
//    }
}