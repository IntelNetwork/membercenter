package org.smartwork.comm.enums;

import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.ResultEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 项目状态枚举
 */
public enum ProjectStateEnum {
    NOT_YET_BEGUN("0", "未开始"),
    Have_in_hand("1", "进行中"),
    Completed("2", "已完成"),
    Delay("3", "延期"),
    ;

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
    public static List<ResultEnum> resultEnums() {
        return Arrays.asList(ProjectStateEnum.values())
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
    public static boolean existsCode(Object code) {
        return Arrays.asList(ProjectStateEnum.values()).stream()
                .filter(mchType -> ConvertUtils.isNotEmpty(code) && mchType.getCode().equals(code))
                .count() >= 1;
    }

    /***
     *
     * 构造函数:
     * @param code
     * @param name
     */
    ProjectStateEnum(String code, String name) {
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
