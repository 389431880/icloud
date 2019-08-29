package com.project.icloud.dao.param;

import com.project.icloudCore.model.param.CurrencyParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName wxy
 * @Description 后台管理人员信息-接口传入参数
 * @Author admin
 * @Date 2019/8/15 9:31
 * @Version 1.0.0
 **/
@Data
@ApiModel(description = "后台管理人员信息")
public class AdminUserEditParam extends CurrencyParam {

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", example = "暂无备注")
    private String remark;

}
