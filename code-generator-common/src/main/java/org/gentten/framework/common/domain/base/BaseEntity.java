package org.gentten.framework.common.domain.base;

import org.gentten.framework.common.json.LongJsonDeserializer;
import org.gentten.framework.common.json.LongJsonSerializer;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类 继承Model
 * ActiveRecord 模式 CRUD 需要原生mapper继承BaseMapper
 *
 * @author : duanzhiqiang
 * @date : 2019-07-30 14:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BaseDto对象", description = "基础实体")
public class BaseEntity<T extends Model> extends Model {
    private static final long serialVersionUID = -3087658965883908267L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "操作时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 指定主键 ,继承 Model 类且实现主键指定方法 即可让实体开启 AR(ActiveRecord) 之旅
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
