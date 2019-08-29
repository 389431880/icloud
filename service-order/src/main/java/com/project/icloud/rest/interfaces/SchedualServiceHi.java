package com.project.icloud.rest.interfaces;

import com.project.icloud.dao.param.AdminUserEditParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: HZB
 * @Description:
 * @Date: Created in 17:16 2019/8/27
 */

@FeignClient(value = "service-admin")
public interface SchedualServiceHi {

    @RequestMapping(value = "/api/admin/adminUser/getAdminUserPage", method = RequestMethod.GET)
    String sayHiFromClientOne();

    @PostMapping(value = "/api/admin/adminUser/editAdminUser")
    String edit(@RequestBody AdminUserEditParam adminUserEditParam);

}
