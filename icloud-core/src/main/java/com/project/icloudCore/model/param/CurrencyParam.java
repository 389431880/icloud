package com.project.icloudCore.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "通用Param")
public class CurrencyParam {

    /**
     * id
     */
    @ApiModelProperty(value = "id", name = "目标对象id", example = "1")
    private Integer id;

}
