package com.project.icloud.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.project.icloud.dao.domain.TEhAdminUser;
import com.project.icloud.dao.dto.DBTEhAdminUserDTO;
import com.project.icloud.dao.param.AdminUserSearchParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface TEhAdminUserMapper extends BaseMapper<TEhAdminUser> {

    /**
     * 查询系统用户人员分页
     * @param rowBounds 分页器
     * @param adminUserSearchParam 查询条件
     * @return Page<DBTEhAdminUserDTO>
     */
    @Select({"<script>"
            + "SELECT eau.`id`,eau.`username`,eau.`name`, "
            + "eau.`school_code`,ero.`name` AS roleName "
            + "FROM t_eh_admin_user eau "
            + "LEFT JOIN t_eh_role ero ON eau.`role_id`=ero.`id` "
            + "WHERE eau.`deleted` = 0 AND eau.`enabled` = 1 "
            + "AND (eau.`deleted` = 0 OR eau.`deleted` IS NULL) "
            + "AND (eau.`enabled` = 1 OR eau.`enabled` IS NULL) "
            + "<when test='username != null'>AND eau.`username`=#{username} </when> "
            + "<when test='name != null'>AND eau.`name` LIKE CONCAT('%', #{name}, '%') </when> "
            + "<when test='roleId != null'>AND eau.`role_id`=#{roleId} </when> "
            + "</script>"})
    List<DBTEhAdminUserDTO> getAdminUserByPage(RowBounds rowBounds, AdminUserSearchParam adminUserSearchParam);

}