package com.project.icloud.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.project.icloud.dao.domain.TEhAdminUser;
import com.project.icloud.dao.dto.DBTEhAdminUserDTO;
import com.project.icloud.dao.mapper.TEhAdminUserMapper;
import com.project.icloud.dao.param.AdminUserSearchParam;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminUserService extends ServiceImpl<TEhAdminUserMapper, TEhAdminUser> {

    /**
     * 用户Mapper
     */
    @Resource
    private TEhAdminUserMapper tEhAdminUserMapper;

    /**
     * 根据用户名、id查询用户信息
     * @param userName 用户名
     * @param id 用户id
     * @return TEhAdminUser
     */
    public TEhAdminUser getAdminUserByUserName(String userName, Integer id) {
        EntityWrapper<TEhAdminUser> wrapper = new EntityWrapper<>();
        wrapper.eq("enabled", 1);
        wrapper.eq("deleted", 0);

        if (!StringUtils.isEmpty(userName)) {
            wrapper.eq("username", userName);
        }

        if (!ObjectUtils.isEmpty(id)) {
            wrapper.eq("id", id);
        }

        return selectOne(wrapper);
    }

    /**
     * 根据角色id查询角色下的管理员
     * @param roleId 角色id
     * @return List<TEhAdminUser>
     */
    public List<TEhAdminUser> getAdminUserByRole(Integer roleId) {
        EntityWrapper<TEhAdminUser> wrapper = new EntityWrapper<>();

        wrapper.eq("enabled", 1);
        wrapper.eq("deleted", 0);

        if (!ObjectUtils.isEmpty(roleId)) {
            wrapper.eq("role_id", roleId);
        }

        return selectList(wrapper);
    }

    /**
     * 条件查询驾校端用户人员分页
     * @param adminUserSearchParam 查询条件
     * @return Page<DBTEhAdminUserDTO>
     */
    public Page<DBTEhAdminUserDTO> getAdminUserByPage(AdminUserSearchParam adminUserSearchParam) {
        Page<DBTEhAdminUserDTO> page = new Page<>(adminUserSearchParam.getPageIndex(), adminUserSearchParam.getPageSize());

        return page.setRecords(tEhAdminUserMapper.getAdminUserByPage(page, adminUserSearchParam));
    }

}
