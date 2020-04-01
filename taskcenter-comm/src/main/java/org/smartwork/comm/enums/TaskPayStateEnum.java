package org.smartwork.comm.enums;

import com.google.common.collect.Maps;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.ResultEnum;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 任务订单支付状态枚举
 */
public enum TaskPayStateEnum {

    UN_PAY("0", "未支付"),
    PAID("1", "已支付"),
    PAY_DEBUFF("2", "支付异常");

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
        return Arrays.asList(TaskPayStateEnum.values())
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
        return Arrays.asList(TaskPayStateEnum.values()).stream()
                .filter(mchType -> ConvertUtils.isNotEmpty(code)&&mchType.getCode().equals(code))
                .count() >=  1;
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
