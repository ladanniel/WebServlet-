package base.web;
/**
 * 前端控制器
 */
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import base.common.Handler;
import base.common.HandlerMapping;


public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  HandlerMapping hMapping;

	@Override
	/**
	 * 初始化方法，读取配置文件（config.xml）,获得处理器的类名
	 * 然后利用java反射将处理器实例化，然后将处理器实例交给HandlerMapping来处理
	 * 注：
	 * HandlerMapping :即映射处理器，利用java反射读取@RequestMapping中 的路径信息，建立请求路径与处理器方法的对应关系.
	 * 配置文件config.xml
	 */
	public void init() throws ServletException {
		//利用dom4j解析config.xml配置文件，主要目的是读取处理器类名 ，需要优先启动 (在web.xml中配置)<load-on-startup>

		try {
			SAXReader reader = new SAXReader();
			InputStream in = getClass().getClassLoader().getResourceAsStream("config.xml");
			Document doc = reader.read(in);
			//找到根元素
			Element root = doc.getRootElement();
			//找到根元素下的所有子元素
			List<Element> eles =  root.elements();
			List beans = new ArrayList();//接收实例化后的bean,
			//遍历所有子元素
			for(Element ele:eles){
				String className = ele.attributeValue("class"); //xml文件中的class
				System.out.println("className:"+className);
				//将处理器实例化
				Object bean = Class.forName(className).newInstance();
				//将处理器实例，并放到一个集合里面，方便管理
				beans.add(bean);
				//将处理器实例交给HandlerMapping来处理
				hMapping = new HandlerMapping();
				hMapping.process(beans);
			}
			System.out.println("beans:"+beans);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}		
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//获得请求资源路径
		String uri = request.getRequestURI();
		//截取请求资源路径的一部分(应用名)
		/**
		 * web.servlet.MVC/hello.do
		 * 应用名：/hello.do
		 */
		String contextPath = request.getContextPath();
		String path = uri.substring(contextPath.length());
		System.out.println("path::"+path);
		/**
		 * 依据请求路径，找到对应的处理器来处理
		 */
		Handler handler = hMapping.getHandler(path);
		Method mh = handler.getMh();
		Object obj = handler.getObj();
		//rv处理器方法的返回值
		Object rv = null;
		try {
			//调用处理器的方法
			/**
			 * 利用java反射，查看处理器的方法，是否带参数，如果带有参数，则给相应的参数赋值
			 */
			//获得方法的参数类型
			Class [] types = mh.getParameterTypes();
			if (types.length>0) {
				//执行带有参数的方法
				Object [] args = new Object[types.length];
				for (int i = 0; i < types.length; i++) {
					if (types[i]==HttpServletRequest.class) {
						//如果是请求对象
						args[i] = request;
						//如果是相应对象
					}
					if (types[i]==HttpServletResponse.class) {
						args[i] = response;
					}
				}
				rv=mh.invoke(obj,args);
			}else {
				//执行不带参数的方法
				rv=mh.invoke(obj);
				//获得视图名
				
			}
			String viewName = rv.toString();
			
			/**
			 * 检查视图名。若果是以redirect：开头，则重定向，否则转发
			 */
			if (viewName.startsWith("redirect:")) {
				//重定向
				//将视图名转发成重定向地址
				String redirectPath = contextPath +"/"+viewName.substring("redirect:".length());
				response.sendRedirect(redirectPath);
			}else {
				//转发
				/**
				 * 将返回值（即：视图名）转换成jsp地址
				 */
				String jspPath = "/WEB-INF/"+viewName+".jsp";
				//
				request.getRequestDispatcher(jspPath).forward(request, response);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		} 



	}
}
