package com.project.serviceStudent.dao.domain;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_eh_student")
public class TEhStudent {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号码
     */
    private String idcard;

    /**
     * 头像图片
     */
    @Column(name = "image_url")
    private String imageUrl;

    /**
     * 性别1男2女
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 出生
     */
    private Date birthday;

    /**
     * 用户手机号码
     */
    private String mobile;

    /**
     * 微信号
     */
    @Column(name = "weixin_no")
    private String weixinNo;

    /**
     * QQ号
     */
    private String qq;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 学员状态plan预报名，formal正式
     */
    private String status;

    /**
     * 学员类型vip 会员 ordinary普通
     */
    private String type;

    /**
     * 驾校代码
     */
    @Column(name = "school_code")
    private String schoolCode;

    /**
     * 创建人
     */
    private Integer creater;

    /**
     * 更新人
     */
    private Integer updater;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 是否可用1是0否
     */
    private Boolean enabled;

    /**
     * 删除标示1删除0未删除
     */
    private Boolean deleted;

    /**
     * 备注
     */
    private String remark;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取身份证号码
     *
     * @return idcard - 身份证号码
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     * 设置身份证号码
     *
     * @param idcard 身份证号码
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    /**
     * 获取头像图片
     *
     * @return image_url - 头像图片
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 设置头像图片
     *
     * @param imageUrl 头像图片
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 获取性别1男2女
     *
     * @return sex - 性别1男2女
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别1男2女
     *
     * @param sex 性别1男2女
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取出生
     *
     * @return birthday - 出生
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置出生
     *
     * @param birthday 出生
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取用户手机号码
     *
     * @return mobile - 用户手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置用户手机号码
     *
     * @param mobile 用户手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取微信号
     *
     * @return weixin_no - 微信号
     */
    public String getWeixinNo() {
        return weixinNo;
    }

    /**
     * 设置微信号
     *
     * @param weixinNo 微信号
     */
    public void setWeixinNo(String weixinNo) {
        this.weixinNo = weixinNo;
    }

    /**
     * 获取QQ号
     *
     * @return qq - QQ号
     */
    public String getQq() {
        return qq;
    }

    /**
     * 设置QQ号
     *
     * @param qq QQ号
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取学员状态plan预报名，formal正式
     *
     * @return status - 学员状态plan预报名，formal正式
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置学员状态plan预报名，formal正式
     *
     * @param status 学员状态plan预报名，formal正式
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取驾校代码
     *
     * @return school_code - 驾校代码
     */
    public String getSchoolCode() {
        return schoolCode;
    }

    /**
     * 设置驾校代码
     *
     * @param schoolCode 驾校代码
     */
    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    /**
     * 获取创建人
     *
     * @return creater - 创建人
     */
    public Integer getCreater() {
        return creater;
    }

    /**
     * 设置创建人
     *
     * @param creater 创建人
     */
    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    /**
     * 获取更新人
     *
     * @return updater - 更新人
     */
    public Integer getUpdater() {
        return updater;
    }

    /**
     * 设置更新人
     *
     * @param updater 更新人
     */
    public void setUpdater(Integer updater) {
        this.updater = updater;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取是否可用1是0否
     *
     * @return enabled - 是否可用1是0否
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置是否可用1是0否
     *
     * @param enabled 是否可用1是0否
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取删除标示1删除0未删除
     *
     * @return deleted - 删除标示1删除0未删除
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * 设置删除标示1删除0未删除
     *
     * @param deleted 删除标示1删除0未删除
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     *  获取类型
     * @return type - 类型
     */
    public String getType() {
        return type;
    }

    /**
     *  设置类型
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type;
    }
}