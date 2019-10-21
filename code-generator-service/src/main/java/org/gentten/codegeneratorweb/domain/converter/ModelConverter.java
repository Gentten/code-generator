package org.gentten.codegeneratorweb.domain.converter;

import org.gentten.codegeneratorweb.domain.entity.Model;
import org.gentten.codegeneratorweb.domain.form.edit.ModelEditForm;
import org.mapstruct.Mapper;

/**
 * Model转化类
 *
 * @author : duanzhiqiang
 * @date : 2019-10-21 10:03
 */
@Mapper(componentModel = "spring")
public interface ModelConverter {


    /**
     * 表单转化为实体
     *
     * @param form 表单
     * @return 实体
     */
    Model form2Entity(ModelEditForm form);

}
