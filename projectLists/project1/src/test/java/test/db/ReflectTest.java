package test.db;


import java.lang.reflect.Field;
import java.util.Arrays;

import com.cn.entity.PersonEntity;


public class ReflectTest {
	public static void main(String[] args) {
		String[] strs = getParams(PersonEntity.class);
		System.out.println(Arrays.toString(strs));
	}
	
	/**
	 * 反射获取类里面所有的字段
	 * @param clazz
	 * @return
	 */
	public static String[] getParams(Class<?> clazz){
		Field[] fields = clazz.getDeclaredFields();
		String[] strs = new String[fields.length];
		for(int i=0;i<fields.length;i++){
			strs[i] = fields[i].getName();
		}
		return strs;
	}

}
