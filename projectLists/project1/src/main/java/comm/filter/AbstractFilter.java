package comm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cn.service.BuildingService;
import com.cn.service.PersonService;
/**
 * @author xiepanfeng
 * 新建一个过滤器
 */
public class AbstractFilter implements Filter{
	
	protected ServletContext context;
	protected ApplicationContext ctx;
	protected PersonService personService;
	protected BuildingService buildingService;
	
	@Override
	public void destroy() {
		System.out.println("销毁.....GG");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		System.out.println("开始继续.....go on!!!");
	}

	/**
	 * 初始化需要的东东
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("开始初始化全局变量.....");
		context = filterConfig.getServletContext();
		ctx = WebApplicationContextUtils.getWebApplicationContext(context);
		personService  = ctx.getBean(PersonService.class);
		buildingService = ctx.getBean(BuildingService.class);
		System.out.println("persionService:"+personService);
		System.out.println("buildingService:"+buildingService);
	}

}
