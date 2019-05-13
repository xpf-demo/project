package com.util.constant;

/**
 * @Description 
 * @date 2016年7月23日
 * @author liubo04
 */
public final class MsgCode {

	public final static String SYS_ERR_MSG = "系统异常";
	public final static String SYS_ERR_CODE = "4999";
	
	public final static String CONFIG_NOT_EXIST_MSG = "配置项不存在，%s";
	public final static String CONFIG_NOT_EXIST_CODE = "002";
	
	public final static String CONFIG_TYPE_CONVERT_MSG = "配置项转换异常，%s";
	public final static String CONFIG_TYPE_CONVERT_CODE = "003";

	public final static String SUCCESS_MSG = "成功";
	public final static String SUCCESS_CODE = "0";

	public final static String FAIL_MSG = "失败";
	public final static String FAIL_CODE = "4999";

	public final static String TOKEN_ERR_MSG = "accessToken错误";
	public final static String TOKEN_ERR_CODE = "102";

	public final static String SIGN_ERR_MSG = "签名错误";
	public final static String SIGN_ERR_CODE = "4001";

	public final static String MCH_ERR_MSG = "mchId或者口令错误";
	public final static String MCH_ERR_CODE = "104";

	public final static String MCHID_TOKEN_ERR_MSG = "mchId或accessToken参数错误";
	public final static String MCHID_TOKEN_ERR_CODE = "105";

	public static final String KEY_ERR_MSG = "请先获取key值";
	public static final String KEY_ERR_CODE = "108";

	public static final String JSON_ERR_MSG = "json格式错误";
	public static final String JSON_ERR_CODE = "4006";
	
	public static final String INCORRECT_SN_MSG = "错误的序列号或序列号已过期";
	public static final String INCORRECT_SN_CODE = "110";
	
	public static final String EXIST_PROJECT_MSG = "项目名称已存在";
	public static final String EXIST_PROJECT_CODE = "111";
	
	public static final String EXIST_PARKING_MSG = "停车场名称已存在";
	public static final String EXIST_PARKING_CODE = "112";
	
	public static final String ERR_PROJECT_MSG = "不存在的项目编码";
	public static final String ERR_PROJECT_CODE = "113";
	
	public static final String ERR_PARKINGID_MSG = "不存在的项目编码";
	public static final String ERR_PARKINGID_CODE = "5050";
	
	public static final String INACTIVE_PROJECT_MSG = "项目处于不可用状态";
	public static final String INACTIVE_PROJECT_CODE = "114";
	
	public static final String ERR_PARAMETER_MSG = "数据参数错误：%s";
	public static final String ERR_PARAMETER_CODE = "4004";
	
	public static final String OUT_OF_LIMIT_MSG = "图片超过最大限制：%sM";
	public static final String OUT_OF_LIMIT_CODE = "116";
	
	public static final String NONSUPPORT_FILE_TYPE_MSG = "不支持的文件类型";
	public static final String NONSUPPORT_FILE_TYPE_CODE = "117";
	
	public static final String UPLOAD_ERR_MSG = "上传文件出错";
	public static final String UPLOAD_ERR_CODE = "118";
	
	public static final String MISS_PARAMETER_MSG = "参数缺失";
	public static final String MISS_PARAMETER_CODE = "119";
	
	public static final String MISS_TIMESTAMP_MSG = "时间戳缺失";
	public static final String MISS_TIMESTAMP_CODE = "120";
	
	public static final String INCORRECT_TIMESTAMP_MSG = "错误的时间戳";
	public static final String INCORRECT_TIMESTAMP_CODE = "121";
	
	public static final String OUT_OF_DATE_SIGN_MSG = "签名已过期，服务器当前时间：%s";
	public static final String OUT_OF_DATE_SIGN_CODE = "4003";
	
	public static final String NONSUPPORT_CHARSET_MSG = "不支持的字符编码";
	public static final String NONSUPPORT_CHARSET_CODE = "123";
	
	public static final String MISS_PARAMETER_NAME_OR_VALUE_MSG = "参数名称或者参数值缺失";
	public static final String MISS_PARAMETER_NAME_OR_VALUE_CODE = "124";
	
	public static final String ERR_PARKING_MSG = "停车场Id错误";
	public static final String ERR_PARKING_CODE = "4108";
	
	public static final String INCORRECT_CITY_ID_MSG = "不存在的城市编码";
	public static final String INCORRECT_CITY_ID_CODE = "126";
	
	public static final String ERR_REQUEST_URL_MSG = "错误的请求链接";
	public static final String ERR_REQUEST_URL_CODE = "127";
	
	public static final String NO_AUDIT_PROJECT_MSG = "没有待审核的项目";
	public static final String NO_AUDIT_PROJECT_CODE = "128";
	
	public static final String NOT_ALLOWED_PROJECT_STATE_MSG = "不允许的项目状态";
	public static final String NOT_ALLOWED_PROJECT_STATE_CODE = "129";
	
	public final static String TOKEN_OUT_OF_TIME_MSG = "accessToken过期";
	public final static String TOKEN_OUT_OF_TIME_CODE = "130";
	
	public final static String ERR_PARAMTER_MSG = "参数错误";
	public final static String ERR_PARAMTER_CODE = "4004";
	
	public final static String RECORD_EXIST_MSG = "该记录已存在，%s";
	public final static String RECORD_EXIST_CODE = "4132";
	
	public final static String RECORD_NOT_EXIST_MSG = "该记录不存在，%s";
	public final static String RECORD_NOT_EXIST_CODE = "4133";
	
	public final static String MCHID_ACESS_ERR_MSG = "mchId无车场权限";
	public final static String MCHID_ACESS_ERR_CODE = "4002";
	
	public final static String MCHID_PARKING_ERR_MSG = "车场已被禁用";
	public final static String MCHID_PARKING_ERR_CODE = "4007";
	
	public final static String SIGN_PARAM_ERROR_MSG = "鉴权参数错误";
	public final static String SIGN_PARAM_ERROR_CODE = "4005";
	
	//住这儿错误编码及错误说明
	public final static String ZER_URL_ERROR_MSG = "url错误，请修改url";
	public final static String ZER_URL_ERROR_CODE = "4001";
	
	public final static String ZER_SIGN_ERROR_MSG = "签名为空，请传入签名";
	public final static String ZER_SIGN_ERROR_CODE = "4002";
	
	public final static String ZER_PARAM_ERROR_MSG = "参数为空，请传入参数";
	public final static String ZER_PARAM_ERROR_CODE = "4003";
	
	public final static String ZER_SIGN_COMPARE_ERROR_MSG = "签名比对错误,请重新生成签名";
	public final static String ZER_SIGN_COMPARE_ERROR_CODE = "4004";
	
	public final static String ZER_JSON_STYLE_ERROR_MSG = "Json格式错误";
	public final static String ZER_JSON_STYLE_ERROR_CODE = "4005";
	
	public final static String ZER_NOKNOW_ERROR_MSG = "未知错误";
	public final static String ZER_NOKNOW_ERROR_CODE = "4999";
	
	
	
}