package org.smartwork.comm.enums;

import org.forbes.comm.vo.ResultEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/***来源数据类型
 */
public enum SourceDataTypeEnum {


    NO_SETTL("0", "任务佣金"),
    IN_SETTL("1", "佣金提现");

    /***编码
     */
    private String code;

    /***名称
     */
    private String name;

    /***
     *
     * @param code
     * @return
     */
    public static boolean existsCode(String code){
        return Arrays.asList(SettlStatusEnum.values()).stream()
                .filter(tEnum -> tEnum.getCode().equals(code)).count() > 0 ;
    }

    /***
     * resultEnums方法慨述:
     * @return List<Map<String,String>>
     * @创建人 huanghy
     * @创建时间 2019年12月10日 上午10:18:04
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public static List<ResultEnum> resultEnums(){
        return Arrays.asList(SourceDataTypeEnum.values()).stream().map(tEnum -> {
            return ResultEnum.ResultEnumBuild.build().setCode(tEnum.getCode()).setName(tEnum.getName());
        }).collect(Collectors.toList());
    }

    /***
     *
     * 构造函数:
     * @param code
     * @param name
     */
    SourceDataTypeEnum(String code, String name) {
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
