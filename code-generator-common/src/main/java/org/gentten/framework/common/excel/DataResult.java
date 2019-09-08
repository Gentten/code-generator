package org.gentten.framework.common.excel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * 结果集
 *
 * @author : duanzhiqiang
 * @date : 2019-08-03 15:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataResult {
    /**
     * 数量量
     */
    private Long total = 0L;
    /**
     * 一行的列名
     */
    private String[] columns;
    /**
     * 真正的数据
     */
    private List<String[]> data;

    private Boolean isIncludeColumns;

    public DataResult(List<String[]> data, Boolean isIncludeColumns) {

        this.isIncludeColumns = isIncludeColumns;
        if (isIncludeColumns && CollectionUtils.isNotEmpty(data)) {
            this.columns = data.get(0);
            data.remove(0);
        }
        this.total = (long) data.size();
        this.data = data;
    }
}
