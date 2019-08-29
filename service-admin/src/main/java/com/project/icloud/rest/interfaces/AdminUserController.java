package com.project.icloud.rest.interfaces;

import com.baomidou.mybatisplus.plugins.Page;
import com.project.icloud.dao.dto.AdminUserDetailDTO;
import com.project.icloud.dao.dto.DBTEhAdminUserDTO;
import com.project.icloud.dao.param.AdminUserEditParam;
import com.project.icloud.dao.param.AdminUserSearchParam;
import com.project.icloudCore.model.dto.SimpleDTO;
import com.project.icloudCore.model.param.CurrencyParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"adminUser-controller"}, description = "平台用户管理")
public interface AdminUserController {

    /**
     * 查询用户(分页)
     */
    String PATH_GET_ADMIN_BY_PAGE = "/adminUser/getAdminUserPage";

    /**
     * 查询用户详情
     */
    String PATH_GET_ADMIN_DETAIL = "/adminUser/getAdminUserDetail";

    /**
     * 更新运营平台用户
     */
    String PATH_EDIT_ADMIN_USER = "/adminUser/editAdminUser";



    /**
     * 查询用户(分页)
     * @param adminUserSearchParam 用户查询条件
     * @return SimpleDTO<Page<DBTEhAdminUserDTO>>
     */
    @ApiOperation(value = "查询用户(分页)", notes = "查询用户(分页)", protocols = "http,https", httpMethod = "GET")
    SimpleDTO<Page<DBTEhAdminUserDTO>> getAdminUserPage(AdminUserSearchParam adminUserSearchParam);

    /**
     * 查询用户详情
     * @param currencyParam 用户id
     * @return SimpleDTO
     */
    @ApiOperation(value = "查询用户详情", notes = "查询用户详情", protocols = "http,https", httpMethod = "GET")
    SimpleDTO<AdminUserDetailDTO> getAdminUserDetail(CurrencyParam currencyParam);

    /**
     * 更新运营平台用户
     * @param adminUserEditParam 修改用户参数
     * @return SimpleDTO
     */
    @ApiOperation(value = "更新运营平台用户", notes = "更新运营平台用户", protocols = "http,https", httpMethod = "POST")
    SimpleDTO editAdminUser(AdminUserEditParam adminUserEditParam);

}
