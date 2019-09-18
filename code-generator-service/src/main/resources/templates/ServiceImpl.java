package [(${model.packageName})].[# th:if="${model.moduleName}"][(${model.moduleName})].[/][(${module.packageName})];

import [(${model.packageName})].[# th:if="${model.moduleName}"][(${model.moduleName})].[/]domain.entity.[(${model.className})];
import [(${model.packageName})].[# th:if="${model.moduleName}"][(${model.moduleName})].[/]domain.form.query.[(${model.className})]Query;
import [(${model.packageName})].[# th:if="${model.moduleName}"][(${model.moduleName})].[/]mapper.[(${model.className})]Mapper;
import [(${model.packageName})].[# th:if="${model.moduleName}"][(${model.moduleName})].[/]service.[(${model.className})]Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

/**
 * [(${model.comment})]([(${model.className})])服务实现
 * 1、继承ServiceImpl接口{@link ServiceImpl}便可实现继承curd功能即AR模式  泛型指定对应的mapper和实体
 * 2、实现自己定义的方法
 * 3、父类中已经将此实体的对应的mapper已经注入了，不需要再次注入。当然你可以注入其他mapper
 *
 * @author : [(${model.operatorName})]
 * @date : [(${model.createTime})]
 */
@Service
public class [(${model.className})]ServiceImpl extends ServiceImpl<[(${model.className})]Mapper, [(${model.className})]> implements [(${model.className})]Service {

    @Override
    public PageInfo<[(${model.className})]> search([(${model.className})]Query query) {
        //不分页则1到99999
        return PageHelper.startPage(query.getListMode() ? 1 : query.getPageNum(), query.getListMode() ? 999999 : query.getPageSize())
                .doSelectPageInfo(() -> list(query.buildWrapper()));
    }

}