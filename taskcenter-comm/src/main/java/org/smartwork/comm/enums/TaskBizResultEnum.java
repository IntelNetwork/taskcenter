package org.smartwork.comm.enums;

/***
 * BizResultEnum概要说明：业务系统错误代码
 * @author niehy(Frunk)
 */
public enum TaskBizResultEnum {
    /***
     * 005-任务管理
     * 功能暂无-表示通用异常
     * 001-为空判断
     */

    EMPTY("005001000", "参数为空", "%s参数为空"),
    ENTITY_EMPTY("005002000", "对象为空", ""),
    /*****任务001(中间三位)****/
    TASK_CODE_EXISTS("005001001","任务类型编码已存在","%s对应任务类型编码已存在"),
    TASK_STATE_CHECK("005001002","任务无法编辑","%s对应任务无法编辑"),
    AMOUNT_LESS_ZERO("005001003","任务金额不能小于0","%s任务金额不能小于0"),
    TASK_RELEASE("005001004","任务已经发布过,请勿重复发布","%s对应任务已经发布过,请勿重复发布"),
    BIDDING_NUMBER_NOT_("005001005","任务已经有竞标人,无法下架","%s任务已经有竞标人,无法下架"),
    /*****任务竞标002(中间三位)****/
    TASK_RECORD_EXISTS("005002001","已存在竞标人","%s对应竞标人已存在"),
    MEMBERS_NOT_EXIST("005002002","该任务下不存在竞标人","%s对应该任务下不存在竞标人"),
    MEMBERS_SAME_EXIST("005002003","已经竞标过该任务","%s您已经竞标过该任务"),
    MEMBERS_HIT_EXIST("005002004","该任务已经有中标人员","%s该任务已经有中标人员"),
    /*****任务订单003(中间三位)****/
    ORDER_STATUS_ABNORMAL("005003001","操作异常","%s操作异常"),
    PAY_STATUS_NO_EXISTS("005003001","该支付状态不存在","%s对应该支付状态不存在"),
    ;

    /**
     * 错误编码业务系统代码+功能编码+错误代码
     **/
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
    TaskBizResultEnum(String bizCode, String bizMessage, String bizFormateMessage) {
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
