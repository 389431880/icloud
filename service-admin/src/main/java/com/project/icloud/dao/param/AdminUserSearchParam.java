package com.project.icloud.dao.param;

import com.project.icloudCore.model.param.CurrencyPageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "查询后台管理人员条件")
public class AdminUserSearchParam extends CurrencyPageParam {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "username", example = "admin")
    private String username;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", name = "name", example = "张三")
    private String name;

    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id", name = "roleId", example = "1")
    private Integer roleId;

}
