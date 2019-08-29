package com.project.icloudCore.constant;

/**
 * rest接口编码，CodeEnum包含编码为通用编码，编码范围从1000~9999
 *
 */
public enum CodeEnum implements ICodeEnum {
    /**
     * 操作成功
     */
    SUCCESS(true, "1000", "操作成功"),
    /**
     * 数据不存在
     */
    DATA_NOT_FOUND(false, "1001", "数据不存在"),
    /**
     * 数据删除失败
     */
    DELETE_FAILED(false, "1002", "数据删除失败"),
    /**
     * 数据新增失败
     */
    INSERT_FAILED(false, "1003", "数据新增失败"),
    /**
     * 数据更新失败
     */
    UPDATE_FAILED(false, "1004", "数据更新失败"),
    /**
     * 数据更新失败
     */
    REPEAT_DATA(false, "1005", "重复数据"),
    /**
     * 接口访问超时
     */
    SOCKET_TIMEOUT(false, "1005", "接口访问超时"),
    /**
     * 加密异常
     */
    ENCRYPT_ERROR(false, "1101", "加密失败"),
    /**
     * 解密异常
     */
    DECRYPT_ERROR(false, "1102", "解密失败"),
    /**
     * 参数异常
     */
    PARAMS_ERROR(false, "1999", "参数异常"),
    /**
     * 未知异常
     */
    UNKNOWN_ERROR(false, "2000", "服务器内部错误"),
    /**
     * 请登录
     */
    UNLOGIN_ERROR(false, "3000", "请登录"),
    /**
     * 帐号或密码不对
     */
    LOGIN_FAIL(false, "3001", "用户名或密码错误"),
    /**
     * 无权限
     */
    NO_AUTH(false, "3002", "无权限"),
    /**
     * 用户信息不完整
     */
    INCOMPLETE_AUTH(false, "3003", "用户信息不完整"),
    /**
     * 岗位下单时间已经截止！
     */
    ORDER_UNPAY(false, "4001", "订单当前状态无法支付！"),
    /**
     * 微信支付失败！
     */
    WXPAY_FAIL(false, "4002", "微信支付失败！"),
    /**
     * 短信发送失败！
     */
    MESSAGE_SEND_FAIL(false, "5001", "短信发送失败！"),
    /**
     * 短信验证超时！
     */
    MESSAGE_OVER_TIME(false, "5002", "短信验证超时！"),
    /**
     * 短信验证码错误！
     */
    MESSAGE_ERROR(false, "5003", "短信验证码错误！"),
    /**
     * 尚未完成报名
     */
    NO_FORMAL_STUDENT(false, "40001", "尚未完成报名，不是正式学员，无法预约!"),
    /**
     * 已经超过时间规定，无法预约!
     */
    RESERVATION_OVERTIME(false, "40002", "已经超过时间规定，无法预约!"),
    /**
     * 已经超过时间规定，无法操作!
     */
    RESERVATION_FULL(false, "40003", "预约已满，无法预约!"),
    /**
     * 您已经预约了该排班的课程！
     */
    RESERVATION_REPEAT(false, "40004", "您已经预约了该排班的课程！"),
    /**
     * 已经超过时间规定，无法取消!
     */
    CANCEL_TIMEOUT(false, "40005", "已经超过时间规定，无法取消!"),
    /**
     * 训练尚未结束，无法评价!
     */
    NO_FINISH(false, "40006", "训练尚未结束，无法评价!");


    /**
     * 成功标示
     */
    private Boolean success;
    /**
     * 响应代码
     */
    private String code;
    /**
     * 响应代码说明
     */
    private String message;

    /**
     * 构造方法
     */
    CodeEnum(Boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    /**
     * 结果
     *
     * @return 操作结果
     */
    @Override
    public Boolean success() {
        return this.success;
    }

    /**
     * 编号
     *
     * @return 操作结果编号
     */
    @Override
    public String code() {
        return this.code;
    }

    /**
     * 说明
     *
     * @return 操作结果说明
     */
    @Override
    public String message() {
        return this.message;
    }
}
