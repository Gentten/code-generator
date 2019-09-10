package org.gentten.codegeneratorweb.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.gentten.codegeneratorweb.domain.entity.CodeModule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 代码模板mapper
 * <p>
 * 1、继承BaseMapper{@link BaseMapper}直接或者curd功能  泛型指定对应的实体
 * 2、@Mapper 如mybatis
 * 3、当基础功能不够用，自己写mapper.xml 或者基于注解 和mybatis 一样
 *
 * @author : code-generator
 * @date : Tue Sep 10 11:54:28 CST 2019
 */
@Mapper
public interface CodeModuleMapper extends BaseMapper<CodeModule> {
    /**
     * 按模板组获取 groupId
     *
     * @param groupId 模板组id
     * @return 模板
     */
    @Select("select cm.* from code_module cm join  module_group_relation mgr on cm.id  = mgr.module_id where mgr.group_id=#{groupId}")
    List<CodeModule> getByGroupId(@Param("groupId") String groupId);

}
