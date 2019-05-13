package utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanUtils{
	
	private final static Logger logger = LoggerFactory.getLogger(BeanUtils.class);
	
	private final static String GETTER_PREFIX = "get";
	private final static String SETTER_PREFIX = "set";
	
	/*public static void copyProperties(Object dest, Object orig){
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(dest, orig);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException("转换对象失败");
		}
	}
	
	public static Object getValueByName(Object bean, String name) throws Exception{
		return BeanUtilsBean.getInstance().getPropertyUtils().getNestedProperty(bean, name);
	}*/
	
	/**
	 * 根据字段批量获取getter方法
	 * @param clazz
	 * @param field
	 * @return
	 * @date 2016年10月14日
	 * @author liubo04
	 */
	public static Method[] getGetterMethods(Class<?> clazz, String[] fields){
		if(clazz == null || fields== null || fields.length < 1){
			throw new RuntimeException("类和字段不能为空");
		}
		Method[] mds = new Method[fields.length];
		for (int i = 0; i< fields.length; i++) {
			mds[i] = getGetterMethod(clazz, fields[i]);
		}
		return mds;
	}
	
	/**
	 * 根据字段批量获取setter方法
	 * @param clazz
	 * @param field
	 * @return
	 * @date 2016年10月14日
	 * @author liubo04
	 */
	public static Method[] getSetterMethods(Class<?> clazz, String[] fields){
		if(clazz == null || fields== null || fields.length < 1){
			throw new RuntimeException("类和字段不能为空");
		}
		Method[] mds = new Method[fields.length];
		for (int i = 0; i< fields.length; i++) {
			mds[i] = getSetterMethod(clazz, fields[i]);
		}
		return mds;
	}
	
	/**
	 * 根据字段获取getter方法
	 * @param clazz
	 * @param field
	 * @return
	 * @date 2016年10月14日
	 * @author liubo04
	 */
	public static Method getGetterMethod(Class<?> clazz, String field){
		try {
			return clazz.getMethod(getGetterName(field));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException("获取类方法出错");
		}
	}
	
	/**
	 * 根据字段获取setter方法
	 * @param clazz
	 * @param field
	 * @return
	 * @date 2016年10月14日
	 * @author liubo04
	 */
	public static Method getSetterMethod(Class<?> clazz, String field){
		try {
			return clazz.getMethod(getSetterName(field));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException("获取类方法出错");
		}
	}
	
	/**
	 * 根据前缀和字段获取对应的方法
	 * @param prefix
	 * @param fieldName
	 * @return
	 * @date 2016年10月14日
	 * @author liubo04
	 */
	public static String getMethodName(String prefix, String fieldName){
		if(StringUtils.isBlank(prefix)){
			return String.valueOf(fieldName.charAt(0)).toUpperCase() + fieldName.substring(1);
		}
		return prefix + String.valueOf(fieldName.charAt(0)).toUpperCase() + fieldName.substring(1);
	}
	
	/**
	 * 根据字段获取对应的getter方法
	 * @param fieldName
	 * @return
	 * @date 2016年10月14日
	 * @author liubo04
	 */
	public static String getGetterName(String fieldName){
		return getMethodName(GETTER_PREFIX, fieldName);
	}
	
	/**
	 * 根据字段获取对应的setter方法
	 * @param fieldName
	 * @return
	 * @date 2016年10月14日
	 * @author liubo04
	 */
	public static String getSetterName(String fieldName){
		return getMethodName(SETTER_PREFIX, fieldName);
	}
	
	
	/**
	 * 获取指定类的所有字段，包括从父类继承过来的（包括静态字段和其他字段）
	 * 
	 * @param clazz
	 * @return
	 * @date 2017年4月10日
	 * @author liubo04
	 */
	private static List<Field> getFields(Class<?> clazz) {
		if ("Object".equals(clazz.getSimpleName())) {
			return Collections.emptyList();
		}
		List<Field> fields = new ArrayList<>();
		fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
		fields.addAll(getFields(clazz.getSuperclass()));
		return fields;
	}

	/**
	 * 获取指定类的所有业务字段，包括从父类继承过来的
	 * 
	 * @param clazz
	 * @return
	 * @date 2017年4月10日
	 * @author liubo04
	 */
	public static List<Field> getBusinessFields(Class<?> clazz) {
		List<Field> _fds = getFields(clazz);
		List<Field> fds = new ArrayList<>();
		for (Field field : _fds) {
			int mdf = field.getModifiers();
			if (mdf == 0 || mdf == 1 || mdf == 2 || mdf == 4) {// 默认,public,private,protected
				fds.add(field);
			}
		}
		return fds;
	}

	public static void setValue(Object bean, String fieldName, Object value) {
		Boolean flag = null;
		Field field = null;
		try {
			field = bean.getClass().getDeclaredField(fieldName);
			flag = field.isAccessible();
			field.setAccessible(true);
			field.set(bean, value);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}finally {
			if(flag != null && field!= null){
				field.setAccessible(flag);
			}
		}
		
	}
	
	public static Object getValue(Object bean, String fieldName) {
		boolean flag = false;
		Field field = null;
		try {
			field = bean.getClass().getDeclaredField(fieldName);
			flag = field.isAccessible();
			field.setAccessible(true);
			return field.get(bean);
		} catch (Exception e) {
			return null;
		} finally {
			field.setAccessible(flag);
		}
	}
}
