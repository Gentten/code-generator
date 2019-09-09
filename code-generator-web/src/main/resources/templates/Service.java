package [(${model.packageName})].[# th:if="${model.moduleName}"][(${model.moduleName})].[/]service;

import [(${model.packageName})].[# th:if="${model.moduleName}"][(${model.moduleName})].[/]domain.entity.[(${model.className})];
import [(${model.packageName})].[# th:if="${model.moduleName}"][(${model.moduleName})].[/]domain.form.query.[(${model.className})]Query;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
 * [(${model.comment})]服务
 * 1、继承IService接口{@link IService}直接获得curd方法不用自己去声明  泛型指定对应的实体
 * 2、当IService不满足业务需求时，自己自定义方法
 *
 * @author : [(${model.operatorName})]
 * @date : [(${model.createTime})]
 */
public interface [(${model.className})]Service extends IService<[(${model.className})]> {
    /**
     * [(${model.comment})]查询
     *
     * @param query 查询条件
     * @return 查询结果
     */
    PageInfo<[(${model.className})]> search([(${model.className})]Query query);

}
