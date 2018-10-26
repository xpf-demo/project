package demo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonDemo {
	
	private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
		
	};
	
	public static void main(String[] args) {
		String text = "{\r\n" + 
				"	\"log_id\": 73473737,\r\n" + 
				"	\"result_num\": 1,\r\n" + 
				"	\"result\": [{\r\n" + 
				"		\"group_id\": \"test1\",\r\n" + 
				"		\"uid\": \"u333333\",\r\n" + 
				"		\"user_info\": \"Test User\",\r\n" + 
				"		\"scores\": [\r\n" + 
				"			99.3,\r\n" + 
				"			83.4\r\n" + 
				"		]\r\n" + 
				"	}]\r\n" + 
				"}";
		
		System.out.println(text);
		JSONObject jsonObject = JSONObject.parseObject(text);
		System.out.println(jsonObject);
		JSONArray array = jsonObject.getJSONArray("result");
		System.out.println(array);
		
		for(int i = 0; i<array.size(); i++) {
			JSONObject json = array.getJSONObject(i);
			System.out.println(json);
			System.out.println(json.getJSONArray("scores"));
		}
		
		System.out.println("--------------------------------------------");
		
		
		short s1 = 1;
		s1 += 1;
		System.out.println(s1);
		System.out.println(Byte.MAX_VALUE);
		System.out.println(Short.MAX_VALUE);
		System.out.println("--------------------------------------------");
		System.out.println(df.get().format(new Date()));
		
//		Calendar cal = Calendar.getInstance();
		
		
	}
}
