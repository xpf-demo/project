package test.db;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cn.dao.BuildingDao;
import com.cn.entity.BuildingEntity;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
public class Test {
	
	@Autowired
	private BuildingDao buildingDao;
	
	@org.junit.Test
	public void test01(){
		BuildingEntity entity = buildingDao.findById("0f9ee63b433849faa4233a396997a096");
		System.out.println(entity);
	}

}
