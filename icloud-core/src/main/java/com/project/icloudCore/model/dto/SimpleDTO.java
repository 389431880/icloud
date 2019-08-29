package com.project.icloudCore.model.dto;

import com.project.icloudCore.constant.CodeEnum;
import com.project.icloudCore.constant.ICodeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 通用VO（不带分页）
 *
 * @param <T> 实际数据类型
 */
@Data
//@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class SimpleDTO<T> extends RestDTO {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7281269010610224891L;

    /**
     * 构造器
     *
     * @param codeEnum code
     */
    public SimpleDTO(ICodeEnum codeEnum) {
        super(codeEnum);
        this.setData(null);
    }
    

    /**
     * 构造器
     *
     * @param data 返回的数据
     */
    public SimpleDTO(T data) {
        super(CodeEnum.SUCCESS);
        this.setData(data);
    }

    /**
     * 返回的数据
     */
    @ApiModelProperty(value = "返回的响应数据", required = true)
    T data;
}
