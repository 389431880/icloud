package com.project.serviceStudent.rest.interfaces;

import com.project.icloudCore.model.dto.SimpleDTO;
import com.project.serviceStudent.dao.param.StudentEditParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"student-controller"}, description = "学员信息服务")
public interface StudentController {

    /**
     * 更新学员信息
     */
    String PATH_EDIT_STUDENT = "/editStudent";

    /**
     * 更新学员信息
     * @param studentEditParam 更新学员信息参数
     * @return SimpleDTO
     */
    @ApiOperation(value = "更新学员信息", notes = "更新学员信息", protocols = "http,https", httpMethod = "POST")
    SimpleDTO editStudent(StudentEditParam studentEditParam);

}
