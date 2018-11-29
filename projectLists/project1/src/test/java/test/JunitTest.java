package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath*:spring-mvc.xml", "classpath*:spring-mybatis.xml" }) 
public class JunitTest {
	
	@Test
	public void test01() {
		System.out.println("hello world!");
	}

}
