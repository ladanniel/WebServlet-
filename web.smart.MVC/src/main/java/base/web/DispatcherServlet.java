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
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import base.common.HandlerMapping;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HandlerMapping hMapping;
	
	@Override
	public void init() throws ServletException {
		SAXReader reader = new SAXReader();
		InputStream ips = getClass().getClassLoader().getResourceAsStream("config.xml");
		try {
			Document doc = reader.read(ips);
			Element root = doc.getRootElement();
			List<Element> list = root.elements();
			List<Object> beans = new ArrayList<Object>();
			for(Element ele :list){
				String className = ele.attributeValue("class");
				System.out.println(className);
				Object bean = Class.forName(className).newInstance();
				beans.add(bean);
				hMapping = new HandlerMapping();
				hMapping.process(beans);
				System.out.println("beans:"+beans);
				
			}
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
	}



	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
