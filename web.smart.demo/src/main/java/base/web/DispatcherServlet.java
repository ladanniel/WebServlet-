package base.web;

import java.io.IOException;
import java.io.InputStream;
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
	//新增一个实例化方法，用于读取web.xml并对值进行初始化
	@Override
	public void init() throws ServletException {

		try {
			SAXReader reader = new SAXReader();
			InputStream ips = getClass().getClassLoader().getResourceAsStream("config.xml");
			Document doc = reader.read(ips);
			Element root = doc.getRootElement();
			List<Element> list = root.elements();
			List<Object> beans = new ArrayList<Object>();
			for (Element eles:list) {
				//遍历根源素，并得到config.xml的根元素属性class对应的value值
				String className = eles.attributeValue("class");
				System.out.println("className:"+className);
				Object bean = Class.forName(className).newInstance();
				beans.add(bean);
				hMapping = new HandlerMapping();
				hMapping.process(beans);
				System.out.println("beans:"+beans);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//1/请求资源路径
		String  uri = request.getRequestURI();
		//1/获取请求路径的应用名
		String contextPath = request.getContextPath();
		//1/截取资源路径的一部分应用名
		String path= uri.substring(contextPath.length());
		System.out.println("path:"+path);
		//2/调用处理逻辑与jsp
		Handler handler = hMapping.getHandler(path);
		//拿到处理逻辑对应的对象与方法
		Method mh =  handler.getMh();
		Object obj  = handler.getObj();
		Object rv = null;//用对象点方法 处理器的方法
		try {
			/**调用处理器的方法
			 * 利用java反射，查看处理器的方法，是否带参数，如果带有参数，则给相应的参数赋值
			 */
			Class [] types = mh.getParameterTypes();//获得参数类型
			if (types.length>0) {//带有参数，对带参数方法进行处理
				Object  [] args = new Object[types.length];//创建一个数组容器，大小跟types大小相等
				for (int i = 0; i < types.length; i++) {
					//参数分为两种，1、页面请求参数request   2、响应response参数
					if (types[i]==HttpServletRequest.class) {
						args[i]=request;
					}
					if (types[i]==HttpServletResponse.class) {
						args[i]=response;
					}
				}
				rv = mh.invoke(obj, args);
			}else{
				rv=mh.invoke(obj);
			}

			String viewPath = rv.toString();
			if (viewPath.startsWith("redirect:")) {
				//重定向，并将视图名转发到重定向地址
				String redirectPath = contextPath+ "/"+viewPath.substring("redirect:".length());
				response.sendRedirect(redirectPath);
			}else{
				String jspPath = "/WEB-INF/"+viewPath+".jsp";
				request.getRequestDispatcher(jspPath).forward(request, response);
			}		
		} catch (Exception e) {

			e.printStackTrace();
		} 

	}



}
