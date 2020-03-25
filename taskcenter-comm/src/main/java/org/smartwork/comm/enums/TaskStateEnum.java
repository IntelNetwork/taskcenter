package org.smartwork.comm.enums;

import com.google.common.collect.Maps;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.ResultEnum;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 任务状态枚举
 */
public enum TaskStateEnum {

    CHECK("0", "待审核"),
    RELEASE("1", "竞标中"),
    TRUST_REWARD("2", "托管赏金"),
    START_UP("3", "开始工作"),
    SUBMIT_ACCEPTANCE("4", "提交验收"),
    CONFIRMATION_ACCEPTANCE("5", "确认验收"),
    PAYMENT_GRATUITY("6", "支付赏金"),
    CHECK_NULL("7", "审核未通过"),
    TASK_OVERDUE("99", "任务过期");

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
