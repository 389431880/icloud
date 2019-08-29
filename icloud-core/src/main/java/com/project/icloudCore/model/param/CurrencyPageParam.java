package com.project.icloudCore.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: HZB
 * @Description:
 * @Date: Created in 15:40 2019/8/27
 */
@Data
public class CurrencyPageParam {

    /**
     * pageIndex
     */
    @ApiModelProperty(value = "pageIndex", name = "pageIndex", example = "1")
    private Integer pageIndex = 1;

    /**
     * pageSize
     */
    @ApiModelProperty(value = "pageSize", name = "pageSize", example = "1")
    private Integer pageSize = 10;

}
