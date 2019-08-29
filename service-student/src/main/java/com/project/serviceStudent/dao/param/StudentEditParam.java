package com.project.serviceStudent.dao.param;

import com.project.icloudCore.model.param.CurrencyParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "修改学员信息参数")
public class StudentEditParam extends CurrencyParam {

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", example = "备注")
    private String remark;

}
