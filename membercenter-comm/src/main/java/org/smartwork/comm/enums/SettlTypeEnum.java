package org.smartwork.comm.enums;
import org.forbes.comm.vo.ResultEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
/***
 * 结算方式
 */
public enum SettlTypeEnum {


    ALIPAY_TO_ALIPAY("0", "支付宝转支付宝","ALIPAY","TRANS_ACCOUNT_NO_PWD"),
    ALIPAY_TO_BANK_CARD("1", "支付宝转银行卡","ALIPAY","TRANS_BANKCARD_NO_PWD"),
    WECHAT_TO_WECHAT("2", "微信转微信","WX","WECHAT_TO_WECHAT"),
    OFFLINE("3", "线下结算","0","");

    /***编码
     */
    private String code;

    /***名称
     */
    private String name;

    /**结算渠道
     * */
    private String channel;

    /***产品编码
     */
    private String productCode;

    /***
     *
     * @param code
     * @return
     */
    public static boolean existsCode(String code){
        return Arrays.asList(SettlTypeEnum.values()).stream()
                .filter(tEnum -> tEnum.getCode().equals(code)).count() > 0 ;
    }


    /***
     * receEnums方法慨述:
     * @return List<Map<String,String>>
     * @创建人 huanghy
     * @创建时间 2019年12月10日 上午10:18:04
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public static List<ResultEnum> resultEnums(){
        return Arrays.asList(SettlTypeEnum.values()).stream().map(tEnum -> {
            return ResultEnum.ResultEnumBuild.build().setCode(tEnum.getCode()).setName(tEnum.getName());
        }).collect(Collectors.toList());
    }

    /***
     * 获取渠道
     * @param code
     * @return
     */
    public static String receChannel(String code){
       return Arrays.asList(SettlTypeEnum.values()).stream().filter(settlType -> settlType.getCode().equalsIgnoreCase(code))
                .map(settlType->settlType.getChannel()).findFirst().orElse("ALIPAY");
    }


    /***
     * 获取产品编码
     * @param code
     * @return
     */
    public static String receProductCode(String code){
        return Arrays.asList(SettlTypeEnum.values()).stream().filter(settlType -> settlType.getCode().equalsIgnoreCase(code))
                .map(settlType->settlType.getProductCode()).findFirst().orElse("TRANS_ACCOUNT_NO_PWD");
    }

    /***
     *
     * 构造函数:
     * @param code
     * @param name
     */
    SettlTypeEnum(String code, String name,String channel,String productCode) {
        this.code = code;
        this.name = name;
        this.channel = channel;
        this.productCode = productCode;
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

    public String getChannel() {
        return channel;
    }

    public String getProductCode() {
        return productCode;
    }
}
