package com.project.icloud.rest.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.project.icloud.dao.constant.SystemConstant;
import com.project.icloud.dao.domain.TEhAdminUser;
import com.project.icloud.dao.dto.AdminUserDetailDTO;
import com.project.icloud.dao.dto.DBTEhAdminUserDTO;
import com.project.icloud.dao.param.AdminUserEditParam;
import com.project.icloud.dao.param.AdminUserSearchParam;
import com.project.icloud.rest.interfaces.AdminUserController;
import com.project.icloud.service.AdminUserService;
import com.project.icloudCore.constant.CodeEnum;
import com.project.icloudCore.model.dto.SimpleDTO;
import com.project.icloudCore.model.param.CurrencyParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping(value = SystemConstant.BASE_REQUEST_MAPPING)
public class AdminUserControllerImpl implements AdminUserController {

    /**
     * 用户Service
     */
    @Autowired
    private AdminUserService adminUserService;


    @Override
    @GetMapping(value = PATH_GET_ADMIN_BY_PAGE)
    public SimpleDTO<Page<DBTEhAdminUserDTO>> getAdminUserPage(AdminUserSearchParam adminUserSearchParam) {
        SimpleDTO<Page<DBTEhAdminUserDTO>> simpleDTO;
        if (ObjectUtils.isEmpty(adminUserSearchParam)) {
            adminUserSearchParam = new AdminUserSearchParam();
        }

        Page<DBTEhAdminUserDTO> page = adminUserService.getAdminUserByPage(adminUserSearchParam);
        if (CollectionUtils.isEmpty(page.getRecords())) {
            simpleDTO = new SimpleDTO<>(CodeEnum.DATA_NOT_FOUND);
            return simpleDTO;
        }

        simpleDTO = new SimpleDTO<>(CodeEnum.SUCCESS);
        simpleDTO.setData(page);

        return simpleDTO;
    }

    @Override
    @GetMapping(value = PATH_GET_ADMIN_DETAIL)
    public SimpleDTO<AdminUserDetailDTO> getAdminUserDetail(CurrencyParam currencyParam) {
        SimpleDTO<AdminUserDetailDTO> simpleDTO;
        if (ObjectUtils.isEmpty(currencyParam.getId())) {
            simpleDTO = new SimpleDTO<>(CodeEnum.PARAMS_ERROR);
            return simpleDTO;
        }

        TEhAdminUser user = adminUserService.getAdminUserByUserName(null, currencyParam.getId());
        if (ObjectUtils.isEmpty(user)) {
            simpleDTO = new SimpleDTO<>(CodeEnum.DATA_NOT_FOUND);
            return simpleDTO;
        }

        AdminUserDetailDTO adminUserDTO = new AdminUserDetailDTO();
        BeanUtils.copyProperties(user, adminUserDTO);
        simpleDTO = new SimpleDTO<>(CodeEnum.SUCCESS);
        simpleDTO.setData(adminUserDTO);

        return simpleDTO;
    }

    @Override
    @Transactional
    @LcnTransaction
    @PostMapping(value = PATH_EDIT_ADMIN_USER)
    public SimpleDTO editAdminUser(@RequestBody AdminUserEditParam adminUserEditParam) {
        SimpleDTO simpleDTO;
        if (ObjectUtils.isEmpty(adminUserEditParam.getId())) {
            simpleDTO = new SimpleDTO<>(CodeEnum.PARAMS_ERROR);
            return simpleDTO;
        }

        TEhAdminUser adminUser = new TEhAdminUser();

        BeanUtils.copyProperties(adminUserEditParam, adminUser);
        adminUser.setUpdateTime(new Date());
        adminUser.setUpdater(1);

        boolean flag = adminUserService.updateById(adminUser);
        if (flag) {
            simpleDTO = new SimpleDTO<>(CodeEnum.SUCCESS);
        } else {
            //回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            simpleDTO = new SimpleDTO<>(CodeEnum.UPDATE_FAILED);
        }

        return simpleDTO;
    }

}
