package com.llm.mybaties_plus.dao;

/**
 * @author 木头
 * @date 2020/7/3 19:48
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.llm.mybaties_plus.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


// 在对应的Mapper(Dao)上面继承基本的类 BaseMapper
//@Repository需要在Spring中配置扫描地址，然后生成Dao层的Bean才能被注入到Service层中。
@Mapper//不需要配置扫描地址，通过xml里面的namespace里面的接口地址，生成了Bean后注入到Service层中。
@Repository // 代表持久层
public interface UserDao extends BaseMapper<User> {
    // 所有的CRUD操作都已经编写完成了
    // 你不需要像以前的配置一大堆文件了！
}
