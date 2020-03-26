package org.smartwork.comm.enums;

import com.google.common.collect.Maps;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.ResultEnum;

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
     *
     * @return
     */
    public static List<ResultEnum> resultEnums(){
        return Arrays.asList(TaskOrderStateEnum.values())
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
        return Arrays.asList(TaskOrderStateEnum.values()).stream()
                .filter(mchType -> ConvertUtils.isNotEmpty(code)&&mchType.getCode().equals(code))
                .count() >=  1;
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
