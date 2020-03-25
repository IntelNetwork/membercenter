package org.smartwork.comm.enums;

import com.google.common.collect.Maps;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.ResultEnum;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/***
 * AdminFlagEnum概要说明：管理员标识
 * @author Huanghy
 */
public enum CmAdminFlagEnum {

	
	SUPER_ADMIN("0","普通员工"),
	ORDINARY("1","管理员");
	
	private String code;
	private String name;



	/***
	 *
	 * @return
	 */
	public static List<ResultEnum> resultEnums(){
		return Arrays.asList(CmAdminFlagEnum.values())
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
		return Arrays.asList(CmAdminFlagEnum.values()).stream()
				.filter(mchType -> ConvertUtils.isNotEmpty(code)&&mchType.getCode().equals(code))
				.count() >=  1;
	}

	/***
	 * 
	 * 构造函数:
	 * @param code
	 * @param name
	 */
	CmAdminFlagEnum(String code, String name){
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
	 * @return name 
	 */
	public String getName() {
		return name;
	}
}
