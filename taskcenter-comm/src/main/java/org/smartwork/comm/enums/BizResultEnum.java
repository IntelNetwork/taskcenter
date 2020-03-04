package org.smartwork.comm.enums;

/***
 * BizResultEnum概要说明：业务系统错误代码
 * @author niehy(Frunk)
 */
public enum BizResultEnum {
    /***
     * 005-任务管理
     * 功能暂无-表示通用异常
     * 001-为空判断
     */

    EMPTY("005001000", "参数为空", "%s参数为空"),
    ENTITY_EMPTY("005002000", "对象为空", ""),

    TASK_CODE_EXISTS("005001001","任务类型编码已存在","%s对应任务类型编码已存在"),
    TASK_RECORD_EXISTS("005001002","已存在竞标人","%s对应竞标人已存在")


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
    BizResultEnum(String bizCode, String bizMessage, String bizFormateMessage) {
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
