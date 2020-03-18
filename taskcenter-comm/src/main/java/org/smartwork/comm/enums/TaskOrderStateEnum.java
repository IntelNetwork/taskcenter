package org.smartwork.comm.enums;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 任务订单状态枚举
 */
public enum TaskOrderStateEnum {

    FUND_TRUSTEESHIP("0", "资金托管"),
    UN_MANAGED("1", "未托管"),
    START_WORK("2", "开始工作"),
    START_CHECK("3", "开始验收"),
    DEFINE_CHECK("4", "确认验收"),
    DEBUFF("99", "异常状态");
    /***编码
     */
    private String code;

    /***名称
     */
    private String name;


    /***
     * existsTaskOrderStateEnum方法慨述:
     * @param code
     * @return boolean
     * @创建人 huanghy
     * @创建时间 2019年12月7日 上午11:19:13
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public static boolean existsTaskOrderStateEnum(String code) {
        return Arrays.asList(TaskOrderStateEnum.values()).stream()
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
    public static List<Map<String, String>> receTaskOrderStateEnum() {
        return Arrays.asList(TaskOrderStateEnum.values()).stream().map(tEnum -> {
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
    TaskOrderStateEnum(String code, String name) {
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
