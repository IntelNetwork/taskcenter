package org.smartwork.comm.enums;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description 竞标记录状态
 * @author xfx
 * @date 2020/3/4 10:07
 */
public enum TaskHitstateEnum {

    HITSTATE_NO("0", "未中标"),
    HITSTATE("1", "已中标"),
    CHECK("2","选标中");

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
    TaskHitstateEnum(String code, String name) {
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
