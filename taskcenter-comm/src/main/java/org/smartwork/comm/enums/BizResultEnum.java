package org.smartwork.comm.enums;

/***
 * BizResultEnum概要说明：业务系统错误代码
 * @author niehy(Frunk)
 */
public enum BizResultEnum {
    /***
     * 007-系统管理
     * 功能暂无-表示通用异常
     * 001-为空判断
     */

    EMPTY("007001000", "参数为空", "%s参数为空"),
    ENTITY_EMPTY("007002000", "对象为空", ""),

    /****导航分组001****/
    NAVIGATION_GROUP_NAME_EXISTS("007001001", "导航分组名称已经存在", "%s对应导航分组名称已经存在"),
    NAVIGATION_OTHER_NAVIGATION_UNDER_GROUP("007001002", "导航分组下存在其他导航", "%s对应导航分组下存在其他导航"),

    /****导航002****/
    NAVIGATION_NAME_EXISTS("007002001", "已存在相同名称", "%s对应已存在相同名称"),

    /****楼层003****/
    FLOOR_NAME_EXISTS("007003001", "此楼层名称已存在", "%s对应此楼层名称已存在"),

    /****广告位004****/
    AD_POSITION_NAME_EXISTS("007004001", "此广告位名称已存在", "%s对应此广告位名称已存在"),
    AD_POSITION_EXISTS_OTHER("007004002", "此广告位下存在广告", "%s对应此广告位下存在广告"),

    /****广告附件005****/
    AD_ATTACH_NAME("007005001", "广告附件名称必须存在", "%s对应广告附件名称必须存在"),
    AD_ATTACH_BINDING("007005001", "广告附件需要绑定广告", "%s对应广告附件需要绑定广告"),


    /***
     * 008-新闻/消息
     * 功能暂无-表示通用异常
     * 001-为空判断
     */

    /****新闻001****/
    NEWS_NOT_RELEASED("008001001", "此新闻并未发布", "%s对应此新闻并未发布"),
    NEWS_EXIST_EXAMINE("008001002", "此新闻已通过审核", "%s对应此新闻已通过审核"),
    NEWS_NOT_EXIST_EXAMINE("008001003", "此新闻还未通过审核", "%s对应此新闻还未通过审核"),
    NEWS_NAME_EMPTY("008001004", "新闻标题为空", "%s新闻标题为空"),
    NEWS_CONTENT_EMPTY("008001005", "新闻内容为空", "%新闻内容为空"),
    NEWS_TYPE_EMPTY("008001006", "新闻类型为空", "%s新闻类型为空"),

    /****新闻分类002****/
    NEWS_TYPE_NAME_EXIST("008002001", "此新闻分类名称已存在", "%s对应此新闻分类名称已存在"),
    NEWS_TYPE_OTHER_EXIST_EXIST("008002002", "新闻分类下存在新闻", "%s对应新闻分类下存在新闻"),

    /****新闻附件003****/
    NEWS_ATTACH_CN_NAME("008003001", "新闻附件要有文件名称", "%s对应新闻附件要有文件名称"),
    NEWS_ATTACH_BINDING("008003002", "新闻附件必须绑定新闻", "%s对应新闻附件必须绑定新闻"),
    NEWS_ATTACH_NULL("008003003", "新闻附件未上传", "%s对应新闻附件未上传"),

    /****消息模板004****/
    MSG_TEMP_TYPE_NULL("008004001", "消息模板类型不能为空", "%s对应消息模板类型不能为空"),

    /****消息005****/
    MSG_YES("008005001", "此消息为已读消息", "%s对应此消息为已读消息"),
    MSG_NO("008005001", "此消息为未读消息", "%s对应此消息为未读消息"),

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
