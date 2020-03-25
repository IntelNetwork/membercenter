package org.smartwork.comm.enums;

/***
 * MemberBizResultEnum概要说明：业务系统错误代码
 * @author niehy(Frunk)
 */
public enum MemberBizResultEnum {
    /***
     * 008-会员务管理
     * 功能暂无-表示通用异常
     * 001-为空判断
     */
    EMPTY("008001000", "参数为空", "%s参数为空"),
    ENTITY_EMPTY("008001000", "对象为空", ""),
    USER_ADMIN("008002000", "当前用户是管理员", ""),
    USER_UN_ADMIN("008002000", "当前用户不是管理员", ""),
    /*****公司001(中间三位)****/
    NO_DELECT_POST("008001001", "该岗位下存在员工，无法删除", "%该岗位下存在员工，无法删除"),
    CM_ADMINFLAG_NO_EXISTS("008001002", "该管理员标识不存在", "%该管理员标识不存在"),
    NO_PERMISSION_TO_CM("008001003", "你无法修改公司信息", "%你无法修改公司信息"),
    NO_PERMISSION_ADD_USER("008001004", "你无法添加员工", "%你无法修改公司信息"),
    NO_PERMISSION_UPDATE_CM("008001005", "你无法变更岗位信息", "%你无法变更岗位信息"),
    /*****团队002(中间三位)****/
    NO_PERMISSION_TO_MODIFY("008002001", "您没有权限修改管理员", "%您没有权限修改管理员"),
    /*****项目任务003(中间三位)****/
    PRO_TASK_PRO_ID_NOT_UPDATE("008003001", "项目ID不能修改", "%s项目ID不能修改"),
    MEMBER_LEVEL_ORDERS("008003001", "会员等级已经存在", "%s会员等级已经存在"),
    MEMBER_DEAD_UNIT("008003002", "期限单位不存在", "%s期限单位不存在"),
    ACTIVITY_STATE("008003003", "等级已启用", "%s等级已启用"),
    TAKE_EFFECT("008003001", "等级再申请中", "%s等级再申请中"),
    APPLY_TAKE_EFFECT("008003002", "等级已申请", "%s等级已申请"),
    /*****公司中岗位004(中间三位)****/
    COMPANY_POST_EXEITS("008004001", "本公司已存在该岗位", "%s本公司已存在该岗位")





    ;


    private String bizCode;
    /**
     * 错误描述
     ****/
    private String bizMessage;
    /**
     * 带格式错误描述
     ****/
    private String bizFormateMessage;

    /***
     * 构造函数:
     * @param bizCode
     * @param bizMessage
     * @param bizFormateMessage
     */
    MemberBizResultEnum(String bizCode, String bizMessage, String bizFormateMessage) {
        this.bizCode = bizCode;
        this.bizMessage = bizMessage;
        this.bizFormateMessage = bizFormateMessage;
    }

    /**
     * @return bizCode
     */
    public String getBizCode() {
        return bizCode;
    }

    /**
     * @param bizCode 要设置的 bizCode
     */
    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    /**
     * @return bizMessage
     */
    public String getBizMessage() {
        return bizMessage;
    }

    /**
     * @param bizMessage 要设置的 bizMessage
     */
    public void setBizMessage(String bizMessage) {
        this.bizMessage = bizMessage;
    }

    /**
     * @return bizFormateMessage
     */
    public String getBizFormateMessage() {
        return bizFormateMessage;
    }

    /**
     * @param bizFormateMessage 要设置的 bizFormateMessage
     */
    public void setBizFormateMessage(String bizFormateMessage) {
        this.bizFormateMessage = bizFormateMessage;
    }
}
