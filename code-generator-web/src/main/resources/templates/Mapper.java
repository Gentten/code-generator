package [(${model.packageName})].[# th:if="${model.moduleName}"][(${model.moduleName})].[/][(${module.packageName})];

import [(${model.packageName})].[# th:if="${model.moduleName}"][(${model.moduleName})].[/]domain.entity.[(${model.className})];
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * [(${model.comment})]mapper
 * <p>
 * 1、继承BaseMapper{@link BaseMapper}直接或者curd功能  泛型指定对应的实体
 * 2、@Mapper 如mybatis
 * 3、当基础功能不够用，自己写mapper.xml 或者基于注解 和mybatis 一样
 *
 * @author : [(${model.operatorName})]
 * @date : [(${model.createTime})]
 */
@Mapper
public interface [(${model.className})]Mapper extends BaseMapper<[(${model.className})]> {

}
