package org.smartwork.comm.enums;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 任务状态枚举
 */
public enum TaskPayStateEnum {

    UN_PAY("1", "未支付"),
    PAID("2", "已支付"),
    PAY_DEBUFF("3", "支付异常");

    /***编码
     */
    private String code;

    /***名称
     */
    private String name;


    /***
     * existsTaskPayStateEnum方法慨述:
     * @param code
     * @return boolean
     * @创建人 huanghy
     * @创建时间 2019年12月7日 上午11:19:13
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public static boolean existsTaskPayStateEnum(String code) {
        return Arrays.asList(TaskPayStateEnum.values()).stream()
                .filter(tEnum -> tEnum.getCode().equals(code)).count() > 0;
    }


    /***
     * receTaskPayStateEnum方法慨述:
     * @return List<Map<String,String>>
     * @创建人 huanghy
     * @创建时间 2019年12月7日 上午11:22:07
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public static List<Map<String, String>> receTaskPayStateEnum() {
        return Arrays.asList(TaskPayStateEnum.values()).stream().map(tEnum -> {
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
    TaskPayStateEnum(String code, String name) {
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
