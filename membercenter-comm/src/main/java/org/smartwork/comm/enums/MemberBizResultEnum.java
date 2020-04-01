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
    ENTITY_EMPTY("008002000", "对象为空", ""),
    USER_ADMIN("008002000", "当前用户是管理员", ""),
    USER_UN_ADMIN("008002000", "当前用户不是管理员", ""),
    /*****公司001(中间三位)****/
    NO_DELECT_POST("008001001", "该岗位下存在员工，无法删除", "%该岗位下存在员工，无法删除"),
    CM_ADMINFLAG_NO_EXISTS("008001002", "该管理员标识不存在", "%该管理员标识不存在"),
    NO_PERMISSION_TO_CM("008002003", "你无法修改公司信息", "%你无法修改公司信息"),
    NO_PERMISSION_ADD_USER("008002004", "你无法添加员工", "%你无法修改公司信息"),
    NO_PERMISSION_UPDATE_CM("008002005", "你无法变更岗位信息", "%你无法变更岗位信息"),
    COMPANY_NOT_CHECK_STATE("005001006","该公司不在被审核状态","%s该公司不在被审核状态"),
    NOT_CHECK_STATE("005001007","审核状态不存在","%s审核状态不存在"),
    /*****团队002(中间三位)****/
    NO_PERMISSION_TO_MODIFY("008002001", "您没有权限进行此操作", "%您没有权限进行此操作"),
    TEAM_NOT_CHECK_STATE("005001002","该团队不在被审核状态","%s该团队不在被审核状态"),
    /*****项目任务003(中间三位)****/
    PRO_TASK_PRO_ID_NOT_UPDATE("008003001", "项目ID不能修改", "%s项目ID不能修改"),
    /*****公司中岗位004(中间三位)****/
    COMPANY_POST_EXEITS("008004001", "本公司已存在该岗位", "%s本公司已存在该岗位"),
    /*****会员等级005(中间三位)****/
    MEMBER_LEVEL_ORDERS("008005001", "会员等级已经存在", "%s会员等级已经存在"),
    MEMBER_DEAD_UNIT("008005002", "期限单位不存在", "%s期限单位不存在"),
    ACTIVITY_STATE("008005003", "等级已启用", "%s等级已启用"),
    TAKE_EFFECT("008005004", "等级再申请中", "%s等级再申请中"),
    APPLY_TAKE_EFFECT("008005005", "等级已申请", "%s等级已申请"),
    LEVEL_CHANGE("008005006", "请选择等级变更申请", "%s请选择等级变更申请"),
    DID_NOT_ORDER("008005007", "您还未订购会员等级权益", "%s:未订购会员等级权益"),
    MEMBER_LEVEL_EXP_TIME("008005008", "您的会员等级即将到期，请直接升级", "%s:会员等级即将到期，请直接升级"),
    MEMBER_LEVEL_LOW("008005009", "会员等级低于当前订购会员等级", "%s:会员等级低于当前订购会员等级"),
    /*****工作计划006(中间三位)****/
    WORK_PLAN_ASSESS("008006001", "该任务已被评估,无法修改", "%s该任务已被评估,无法修改"),
    /***结算申请审核007**/
    REVIEW_STATUS_NOT_EXISTS("008007001", "审核状态不存在", "%s审核状态不存在"),
    REVIEW_STATUS_EXISTS("008007002", "申请已审核", "%s申请已审核"),
    PAY_CRED_NOT_EXISTS("008007003", "线下支付需上传支付凭证", "%s线下支付需上传支付凭证"),
    NOT_AVAILABLE_BALANCE("008007004", "暂无可用余额", "%s暂无可用余额"),
    ZERO_AMOUNT("008007005", "结算金额不能为零/负数", "%s结算金额不能为零/负数"),
    DEF_AVAILABLE_BALANCE("008007005", "可用余额不足", "%s可用余额不足"),
    SETTL_TYPE("008007006", "结算方式不存在", "%s结算方式不存在"),
    ACCOUNT("008007007", "结算账号为空", "%s结算账号为空"),
    ACCOUNT_NAME("008007008", "账号名称为空", "%s账号名称为空"),
    AUDIT_WAY("008007009", "审核时结算方式只允许修改为线下支付", "%s审核时结算方式只允许修改为线下支付"),
    NOT_AUDIT("008007010", "申请还未审核", "%s申请还未审核"),
    SETTL_STATUS("008007011", "申请还未执行/或已执行完成", "%s申请还未执行/或已执行完成"),
    /***项目008**/
    DATA_TYPE_NO_EXIST("008008001", "项目数据类型不存在", "%s项目数据类型不存在");

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
