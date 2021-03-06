package org.gentten.codegeneratorweb.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.gentten.codegeneratorweb.domain.entity.CodeTemplateGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * 代码模板组mapper
 * <p>
 * 1、继承BaseMapper{@link BaseMapper}直接或者curd功能  泛型指定对应的实体
 * 2、@Mapper 如mybatis
 * 3、当基础功能不够用，自己写mapper.xml 或者基于注解 和mybatis 一样
 *
 * @author : code-generator
 * @date : Tue Sep 10 11:53:05 CST 2019
 */
@Mapper
public interface CodeModuleGroupMapper extends BaseMapper<CodeTemplateGroup> {

    @Results(id = "test", value = {
            @Result(column = "xxx", property = "xx"),
            @Result(column = "xx", property = "xx"),
            @Result(column = "xx", property = "xxx"),
    })
    void test();
}
