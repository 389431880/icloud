package com.project.icloud.rest.interfaces;

import com.project.icloud.dao.param.AdminUserEditParam;
import com.project.icloud.dao.param.StudentEditParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: HZB
 * @Description:
 * @Date: Created in 17:16 2019/8/27
 */

@FeignClient(value = "service-student")
public interface StudentServiceHi {

    @PostMapping(value = "/api/student/editStudent")
    String edit(@RequestBody StudentEditParam studentEditParam);

}
