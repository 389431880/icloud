package com.project.serviceStudent.rest.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.project.icloudCore.constant.CodeEnum;
import com.project.icloudCore.model.dto.SimpleDTO;
import com.project.serviceStudent.dao.constant.SystemConstant;
import com.project.serviceStudent.dao.domain.TEhStudent;
import com.project.serviceStudent.dao.param.StudentEditParam;
import com.project.serviceStudent.rest.interfaces.StudentController;
import com.project.serviceStudent.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = SystemConstant.BASE_REQUEST_MAPPING)
public class StudentControllerImpl implements StudentController {

    /**
     * 学员Service
     */
    @Autowired
    private StudentService studentService;


    @Override
    @LcnTransaction
    @Transactional
    @PostMapping(value = PATH_EDIT_STUDENT)
    public SimpleDTO editStudent(@RequestBody StudentEditParam studentEditParam) {
        SimpleDTO simpleDTO;

        studentEditParam.setId(1);

        TEhStudent tEhStudent = new TEhStudent();
        BeanUtils.copyProperties(studentEditParam, tEhStudent);
        boolean result = studentService.updateById(tEhStudent);
        if (!result) {
            //回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            simpleDTO = new SimpleDTO<>(CodeEnum.UPDATE_FAILED);
        } else {
            simpleDTO = new SimpleDTO<>(CodeEnum.SUCCESS);
        }

        return simpleDTO;
    }
}
