package org.smartwork.comm.enums;

import com.google.common.collect.Maps;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.ResultEnum;

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
     *
     * @return
     */
    public static List<ResultEnum> resultEnums(){
        return Arrays.asList(TaskHitstateEnum.values())
                .stream().map(mchType -> ResultEnum.ResultEnumBuild
                        .build()
                        .setCode(mchType.getCode())
                        .setName(mchType.getName())).collect(Collectors.toList());
    }

    /***
     *   判断是否存在
     * @param code
     * @return
     */
    public static boolean existsCode(Object code){
        return Arrays.asList(TaskHitstateEnum.values()).stream()
                .filter(mchType -> ConvertUtils.isNotEmpty(code)&&mchType.getCode().equals(code))
                .count() >=  1;
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
