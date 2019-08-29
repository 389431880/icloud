package com.project.icloud.dao.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "驾校端后台管理人员信息")
public class AdminUserDetailDTO {

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
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "password", example = "123456")
    private String password;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", name = "name", example = "张三")
    private String name;

    /**
     * 头像图片
     */
    @ApiModelProperty(value = "头像图片", name = "image_url", example = "https://www.baidu.com/img/bd_logo1.png?where=super")
    private String imageUrl;

    /**
     * 用户手机号码
     */
    @ApiModelProperty(value = "用户手机号码", name = "mobile", example = "13866666666")
    private String mobile;

    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id", name = "roleId", example = "1")
    private Integer roleId;

    /**
     * 驾校代码
     */
    @ApiModelProperty(value = "驾校代码", name = "schoolCode", example = "驾校代码", hidden = true)
    private String schoolCode;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", name = "creater", example = "1")
    private Integer creater;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人", name = "updater", example = "1")
    private Integer updater;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createTime", example = "2018:01:15 09:39:00")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", name = "updateTime", example = "2018:01:15 09:39:00")
    private Date updateTime;

    /**
     * 是否可用1是0否
     */
    @ApiModelProperty(value = "是否可用1是0否", name = "enabled", example = "true")
    private Boolean enabled;

    /**
     * 删除标示1删除0未删除
     */
    @ApiModelProperty(value = "删除标示1删除0未删除", name = "deleted", example = "false")
    private Boolean deleted;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark", example = "暂无备注")
    private String remark;

}
