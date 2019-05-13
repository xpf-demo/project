package utils;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.util.constant.MsgCode;
import com.util.constant.MsgConstant;

public class ResultUtil {
	
	/**
	 * 获取返回结果的模板
	 * @param e
	 * @return
	 * @date 2016年6月8日
	 * @author liubo04
	 */
	/*public static JSONObject getResJson(BusinessException e) {
		return getResJson(e.getMsg(), e.getCode());
	}*/
	
	/**
	 * 获取返回结果的模板
	 * @param msg 传null将返回默认 系统异常
	 * @param code 
	 * @return
	 * @date 2016年6月8日
	 * @author liubo04
	 */
	public static JSONObject getResJson(String msg, String code, Object... objects) {
		JSONObject res = new JSONObject();
		msg = msg == null ? MsgCode.SYS_ERR_MSG : msg;
		if(objects != null && objects.length > 0){
			msg = String.format(msg, objects);
		}
		res.put(MsgConstant.MSG_CONTENT_NAME, msg);
		res.put(MsgConstant.MSG_CODE_NAME, code == null ? MsgCode.SYS_ERR_CODE : code);
		return res;
	}

	/**
	 * 获取默认的 返回结果的模板  系统异常
	 * @return
	 * @date 2016年6月8日
	 * @author liubo04
	 */
	public static JSONObject getResJson() {
		return getResJson(null, null);
	}

	/**
	 * 获取成功时 返回结果的模板 
	 * @return
	 * @date 2016年6月8日
	 * @author liubo04
	 */
	public static JSONObject getSuccessResJson() {
		return getResJson(MsgCode.SUCCESS_MSG, MsgCode.SUCCESS_CODE);
	}
	
	/**
	 * 获取成功时 返回结果的模板 
	 * @return
	 * @date 2016年6月8日
	 * @author liubo04
	 */
	public static JSONObject getSuccessResJson(String msg) {
		return StringUtils.isBlank(msg) ? getSuccessResJson() : getResJson(String.format("%s，提示：%s", MsgCode.SUCCESS_MSG, msg), MsgCode.SUCCESS_CODE);
	}
	
	/**
	 * 获取成功时 返回结果的模板 
	 * @return
	 * @date 2016年8月17日
	 * @author liubo04
	 */
	public static JSONObject getSuccessResJson(String name, Object obj) {
		JSONObject json = getResJson(MsgCode.SUCCESS_MSG, MsgCode.SUCCESS_CODE);
		json.put(name, obj);
		return json;
	}
	

	/**
	 * 获取成功时 返回结果的模板 
	 * @return
	 * @date 2016年8月17日
	 * @author liubo04
	 */
	public static JSONObject getSuccessResJson(Object obj) {
		JSONObject json = getResJson(MsgCode.SUCCESS_MSG, MsgCode.SUCCESS_CODE);
		json.put("data", obj);
		return json;
	}
	
	
	/**
	 * 获取成功时 返回结果的模板 
	 * @return
	 * @date 2016年8月17日
	 * @author liubo04
	 */
	public static JSONObject getSuccessResJson(List<?> obj) {
		JSONObject json = getResJson(MsgCode.SUCCESS_MSG, MsgCode.SUCCESS_CODE);
		json.put("data", obj);
		return json;
	}
	
	/**
	 * 获取成功时 返回结果的模板 
	 * @return
	 * @date 2016年8月17日
	 * @author liubo04
	 */
	/*public static JSONObject getSuccessResJson(PageRes<?> obj) {
		JSONObject json = getResJson(MsgCode.SUCCESS_MSG, MsgCode.SUCCESS_CODE);
		json.put("data", obj.getResultList());
		json.put("totalSize", obj.getTotalSize());
		json.put("totalPage", obj.getTotalPage());
		json.put("pageIndex", obj.getCurPage());
		json.put("pageSize", obj.getPageSize());
		return json;
	}*/
	
	/**
	 * 获取成功时 返回结果的模板 
	 * @return
	 * @date 2016年8月17日
	 * @author liubo04
	 */
	/*public static JSONObject getSuccessResJson(PageRet<?> obj) {
		JSONObject json = getResJson(MsgCode.SUCCESS_MSG, MsgCode.SUCCESS_CODE);
		json.put("data", obj.getResultList());
		json.put("totalSize", obj.getTotalSize());
		json.put("totalPage", obj.getTotalPage());
		json.put("pageIndex", obj.getCurPage());
		json.put("pageSize", obj.getPageSize());
		return json;
	}
	*/
	/**
	 * 获取失败时 返回结果的模板 
	 * @return
	 * @date 2016年6月8日
	 * @author liubo04
	 */
	public static JSONObject getFailResJson() {
		return getFailResJson(null);
	}
	
	/**
	 * 获取参数错误时 返回结果的模板 
	 * @return
	 * @date 2016年6月8日
	 * @author liubo04
	 */
	public static JSONObject getParamErrResJson() {
		return getParamErrResJson(null);
	}
	
	/**
	 * 获取参数错误时 返回结果的模板 
	 * @return
	 * @date 2016年6月8日
	 * @author liubo04
	 */
	public static JSONObject getParamErrResJson(String msg) {
		if(StringUtils.isBlank(msg)){
			return getResJson(MsgCode.ERR_PARAMETER_MSG, MsgCode.ERR_PARAMETER_CODE);
		}
		return getResJson("失败，"+MsgCode.ERR_PARAMETER_MSG, MsgCode.ERR_PARAMETER_CODE, msg);
	}
	
	/**
	 * 获取失败时 返回结果的模板 
	 * @return
	 * @date 2016年6月8日
	 * @author liubo04
	 */
	public static JSONObject getFailResJson(String msg) {
		if(StringUtils.isBlank(msg)){
			return getResJson(MsgCode.FAIL_MSG, MsgCode.FAIL_CODE);
		}
		return getResJson(String.format("%s，提示：%s", MsgCode.FAIL_MSG, msg), MsgCode.FAIL_CODE);
	}
	
	/**
	 * 获取失败时 返回结果的模板 
	 * @return
	 * @date 2016年6月8日
	 * @author liubo04
	 */
	public static JSONObject getFailResJson(String msg, String code) {
		if(StringUtils.isBlank(msg)){
			msg = MsgCode.FAIL_MSG;
		}
		if(StringUtils.isBlank(code)){
			code = MsgCode.FAIL_CODE;
		}
		return getResJson(String.format("%s，提示：%s", MsgCode.FAIL_MSG, msg), code);
	}

}
