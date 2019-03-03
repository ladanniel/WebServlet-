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
	//����һ��ʵ�������������ڶ�ȡweb.xml����ֵ���г�ʼ��
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
				//������Դ�أ����õ�config.xml�ĸ�Ԫ������class��Ӧ��valueֵ
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
		//1/������Դ·��
		String  uri = request.getRequestURI();
		//1/��ȡ����·����Ӧ����
		String contextPath = request.getContextPath();
		//1/��ȡ��Դ·����һ����Ӧ����
		String path= uri.substring(contextPath.length());
		System.out.println("path:"+path);
		//2/���ô����߼���jsp
		Handler handler = hMapping.getHandler(path);
		//�õ������߼���Ӧ�Ķ����뷽��
		Method mh =  handler.getMh();
		Object obj  = handler.getObj();
		Object rv = null;//�ö���㷽�� �������ķ���
		try {
			/**���ô������ķ���
			 * ����java���䣬�鿴�������ķ������Ƿ��������������в����������Ӧ�Ĳ�����ֵ
			 */
			Class [] types = mh.getParameterTypes();//��ò�������
			if (types.length>0) {//���в������Դ������������д���
				Object  [] args = new Object[types.length];//����һ��������������С��types��С���
				for (int i = 0; i < types.length; i++) {
					//������Ϊ���֣�1��ҳ���������request   2����Ӧresponse����
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
				//�ض��򣬲�����ͼ��ת�����ض����ַ
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
