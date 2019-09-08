package org.gentten.framework.common.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * @author : duanzhiqiang
 * @date : 2019-07-05 20:57
 */
public interface IResponseEnum extends IEnum {
    /**
     * 获取code
     *
     * @return 代码
     */
    int getCode();

    /**
     * 说明
     *
     * @return
     */
    String getDesc();
}
