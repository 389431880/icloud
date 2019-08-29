package com.project.icloud.dao.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "db驾校端后台管理人员信息")
public class DBTEhAdminUserDTO {

    /**
     * id
     */
    @ApiModelProperty(value = "id", name = "id", example = "1")
    private Integer id;

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

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称", name = "roleName", example = "1")
    private String roleName;

    /**
     * 驾校代码
     */
    @ApiModelProperty(value = "驾校代码", name = "schoolCode", example = "驾校代码")
    private String schoolCode;

}
