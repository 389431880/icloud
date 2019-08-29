package com.project.icloud.rest.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.project.icloud.dao.param.AdminUserEditParam;
import com.project.icloud.dao.param.StudentEditParam;
import com.project.icloud.rest.interfaces.SchedualServiceHi;
import com.project.icloud.rest.interfaces.StudentServiceHi;
import com.project.icloudCore.constant.CodeEnum;
import com.project.icloudCore.model.dto.SimpleDTO;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: HZB
 * @Description:
 * @Date: Created in 17:15 2019/8/27
 */

@Slf4j
@RestController
public class demo {

    @Autowired
    private SchedualServiceHi schedualServiceHi;

    @Autowired
    private StudentServiceHi studentServiceHi;

    @GetMapping(value = "/hi")
    public SimpleDTO sayHi() {
        SimpleDTO simpleDTO;

        JSONObject jsonObject = JSONObject.fromObject(schedualServiceHi.sayHiFromClientOne());
        if (Integer.parseInt(jsonObject.getString("code")) == 1000) {
            simpleDTO = new SimpleDTO(CodeEnum.SUCCESS);
            simpleDTO.setData(jsonObject.getString("data"));
            return simpleDTO;
        }

        simpleDTO = new SimpleDTO(CodeEnum.DATA_NOT_FOUND);

        return simpleDTO;
    }

    @PostMapping(value = "/edit")
    @LcnTransaction
    @Transactional
    public SimpleDTO edit(@RequestBody AdminUserEditParam adminUserEditParam) {
        SimpleDTO simpleDTO;
        adminUserEditParam.setId(1);
        schedualServiceHi.edit(adminUserEditParam);

        StudentEditParam studentEditParam = new StudentEditParam();
        studentEditParam.setId(1);
        studentEditParam.setRemark(adminUserEditParam.getRemark());
        studentServiceHi.edit(studentEditParam);

        if ("回滚".equals(adminUserEditParam.getRemark())) {
            int i = 100/0;
        }

        simpleDTO = new SimpleDTO(CodeEnum.SUCCESS);
        return simpleDTO;
    }

}
