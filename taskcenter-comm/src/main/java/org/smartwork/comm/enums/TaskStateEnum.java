package org.smartwork.comm.enums;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 任务状态枚举
 */
public enum TaskStateEnum {

    UNPUBLISHED("0", "未发布"),
    CHECK("1", "待审核"),
    LOWER_SHELF("2", "已下架"),
    RELEASE("3", "发布（竞标中）"),
    SELECTION_STANDARD("4", "选标中"),
    TRUST_REWARD("5", "托管赏金"),
    START_UP("6", "开始工作"),
    SUBMIT_ACCEPTANCE("7", "提交验收"),
    CONFIRMATION_ACCEPTANCE("8", "确认验收"),
    PAYMENT_GRATUITY("9", "支付赏金"),
    TASK_OVERDUE("99", "任务过期");

    /***编码
     */
    private String code;

    /***名称
     */
    private String name;


    /***
     * existsTaskStateEnum方法慨述:
     * @param code
     * @return boolean
     * @创建人 huanghy
     * @创建时间 2019年12月7日 上午11:19:13
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public static boolean existsTaskStateEnum(String code) {
        return Arrays.asList(TaskStateEnum.values()).stream()
                .filter(tEnum -> tEnum.getCode().equals(code)).count() > 0;
    }


    /***
     * receUserStaus方法慨述:
     * @return List<Map<String,String>>
     * @创建人 huanghy
     * @创建时间 2019年12月7日 上午11:22:07
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public static List<Map<String, String>> receTaskStateEnum() {
        return Arrays.asList(TaskStateEnum.values()).stream().map(tEnum -> {
            Map<String, String> reponseMap = Maps.newHashMap();
            reponseMap.put("code", tEnum.getCode());
            reponseMap.put("name", tEnum.getName());
            return reponseMap;
        }).collect(Collectors.toList());
    }

    /***
     *
     * 构造函数:
     * @param code
     * @param name
     */
    TaskStateEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }


    /**
     * @return code
     */
    public String getCode() {
        return code;
    }


    /**
     * @param code 要设置的 code
     */
    public void setCode(String code) {
        this.code = code;
    }


    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 要设置的 name
     */
    public void setName(String name) {
        this.name = name;
    }
}
