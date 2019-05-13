package utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;


public class CollectionUtil extends CollectionUtils{

	private final static Logger logger = LoggerFactory.getLogger(CollectionUtil.class);
	
	private final static int defaultBatchNum = 500;
	
	/**
	 * java对象转map对象
	 * @param object
	 * @param fields
	 * @return
	 * @date 2016年12月30日
	 * @author liubo04
	 */
	public static Map<String, Object> java2Map(Object object, String... fields){
		if(object == null){
			return null;
		}
		if(fields == null || fields.length < 1){
			logger.error("fields为空");
			throw new RuntimeException("转换mapList失败，没有指定字段");
		}
		String[][] fieldArr = splitFields(fields);
		Method[] methods = BeanUtils.getGetterMethods(object.getClass(), fieldArr[0]);
		return java2Map(object, methods, fieldArr[1]);
	}
	
	/**
	 * 将java对象list转换为map对象list，需要的字段自己指定
	 * @param list
	 * @param fields
	 * @return
	 * @date 2016年10月14日
	 * @author liubo04
	 */
	public static List<Map<String, Object>> javaList2MapList(List<?> list, String ... fields){
		if(list == null){
			return null;
		}
		if(list.size() < 1){
			return Collections.emptyList();
		}
		if(fields == null || fields.length < 1){
			logger.error("fields为空");
			throw new RuntimeException("转换mapList失败，没有指定字段");
		}
		String[][] fieldArr = splitFields(fields);
		List<Map<String, Object>> mapList = new ArrayList<>(list.size());
		Iterator<?> it = list.iterator();
		Object object = it.next();
		Method[] methods = BeanUtils.getGetterMethods(object.getClass(), fieldArr[0]);
		mapList.add(java2Map(object, methods, fieldArr[1]));
		while(it.hasNext()) {
			mapList.add(java2Map(it.next(), methods, fieldArr[1]));
		}
		return mapList;
	}

	/**
	 * 分隔字段
	 * @param fields
	 * @return
	 * @date 2016年12月30日
	 * @author liubo04
	 */
	private static String[][] splitFields(String[] fields) {
		String[] _fss = null;
		String[] fds = new String[fields.length];
		String[] keys = new String[fields.length];
		for (int i = 0;i<fields.length;i++) {
			if(fields[i].indexOf("=>") != -1 && (_fss = fields[i].split("=>")).length > 1){
				fds[i] =_fss[0];
				keys[i] =_fss[1];
			}else{
				fds[i]=fields[i];
				keys[i]=fields[i];
			}
		}
		_fss = null;
		return new String[][]{fds, keys};
	}
	
	/**
	 * java对象转map对象
	 * @param object
	 * @param mds
	 * @param fields
	 * @return
	 * @date 2016年10月14日
	 * @author liubo04
	 */
	private static Map<String, Object> java2Map(Object object, Method[] mds, String[] fields) {
		if(object == null){
			return null;
		}
		Map<String, Object> map = new HashMap<>();
		try {
			for (int i = 0; i < fields.length; i++) {
				map.put(fields[i], mds[i].invoke(object));
			}
		} catch (Exception e) {
			logger.error("java对象转map出现异常");
			throw new RuntimeException("java对象转map出现异常");
		}
		return map;
	}
	
	/**
	 * 将Map list对象转换为指定的Java对象list
	 * @param list
	 * @param clazz
	 * @return
	 * @date 2016年9月26日
	 * @author liubo04
	 */
	public static <T> List<T> objectList2javaList(List<? extends Object> list, Class<T> clazz) {
		if(list != null){
			List<T> _list = new ArrayList<>();
			for (Object obj : list) {
				_list.add(JSONObject.toJavaObject(JSONObject.parseObject(JSONObject.toJSONString(obj)), clazz));
			}
			return _list;
		}
		return Collections.emptyList();
	}
	
	/**
	 * @Description 将list分成若干批数据,每批按照默认的大小
	 * @param list 
	 * @return
	 * @date 2016年9月8日
	 * @author liubo04
	 */
	public static <T> List<List<T>> list2BatchList(List<T> list){
		return list2BatchList(list, 0);
	}
	
	/**
	 * @Description 将list根据固定大小分成若干批数据
	 * @param list 
	 * @param batchNum 每批的数量
	 * @return
	 * @date 2016年9月8日
	 * @author liubo04
	 */
	public static <T> List<List<T>> list2BatchList(List<T> list, int batchNum){
		batchNum = batchNum < 1 ? defaultBatchNum : batchNum;
		List<List<T>> batchList = new ArrayList<>();
		if(list == null || list.size() == 0){
			return batchList;
		}
		List<T> _ls = new ArrayList<>();
		for (T t : list) {
			_ls.add(t);
			if(_ls.size() >= batchNum){
				batchList.add(_ls);
				_ls = new ArrayList<>();
			}			
		}
		if(_ls.size() > 0){
			batchList.add(_ls);
		}
		_ls = null;
		return batchList;
	}
	
	/**
	 * 从一个对象集合里面获取某个字段，并生成对应的list
	 * @param colle 对象的集合
	 * @param clazz 要获取的属性的类型
	 * @return 
	 * @date 2016年6月8日
	 * @author liubo04
	 */
	/*public static <T, P> List<P> getPropertyList(Collection<T> colle, CollectionProperty<T, P> pro) {
		List<P> list = new ArrayList<>();
		for (T t : colle) {
			P p = pro.getProperty(t);
			if (p != null) {
				list.add(p);
			}
		}
		return list;
	}*/
	
	/**
	 * 从一个对象集合里面获取某个字段的Set集合
	 * @param colle 对象的集合
	 * @param clazz 要获取的属性的类型
	 * @return 
	 * @date 2016年6月8日
	 * @author liubo04
	 */
	/*public static <T> List<String> getPropertyList(Collection<T> colle, String field) {
		if(isEmpty(colle)){
			return Collections.emptyList();
		}
		if(StringUtils.isBlank(field)){
			throw new RuntimeException("未指定字段值");
		}
		List<String> list = new ArrayList<>();
		try {
			Iterator<T> it = colle.iterator();
			T t = it.next();
			BeanGetMethodInvoke md = new BeanGetMethodInvoke(t.getClass(), field); 
			list.add(String.valueOf(md.invoke(t)));
			while(it.hasNext()){
				String p = String.valueOf(md.invoke(it.next()));
				if (p != null) {
					list.add(p);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		return list;
	}*/
	
	/**
	 * 从一个对象集合里面获取某个字段的Set集合
	 * @param colle 对象的集合
	 * @param clazz 要获取的属性的类型
	 * @return 
	 * @date 2016年6月8日
	 * @author liubo04
	 */
	/*public static <T, P> Set<P> getPropertySet(Collection<T> colle, CollectionProperty<T, P> pro) {
		Set<P> set = new HashSet<>();
		for (T t : colle) {
			P p = pro.getProperty(t);
			if (p != null) {
				set.add(p);
			}
		}
		return set;
	}
	*/
	/**
	 * 从一个对象集合里面获取某个字段的Set集合
	 * @param colle 对象的集合
	 * @param clazz 要获取的属性的类型
	 * @return 
	 * @date 2016年6月8日
	 * @author liubo04
	 */
	/*public static <T> Set<String> getPropertySet(Collection<T> colle, String field) {
		return new HashSet<>(getPropertyList(colle, field));
	}*/
	
	/**
	 * 从一个对象集合里面根据指定属性获取Map，key为属性值，value为对象
	 * @param colle 对象集合
	 * @param fieldName 属性名称
	 * @return
	 * @date 2016年6月8日
	 * @author liubo04
	 */
	/*public static <T> Map<String, T> getMapByProperty(Collection<T> colle, String fieldName) {
		if (colle == null) {
			return null;
		}
		Map<String, T> map = new HashMap<>((colle.size()+1)/2);
		if (colle.size() == 0) {
			return map;
		}
		Iterator<T> it = colle.iterator();
		T t = it.next();
		try {
			BeanGetMethodInvoke method = new BeanGetMethodInvoke(t.getClass(), fieldName); 
			String val = String.valueOf(method.invoke(t));
			map.put(val, t);
			while (it.hasNext()) {
				t = it.next();
				val = String.valueOf(method.invoke(t));
				map.put(val, t);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		return map;
	}*/

}
