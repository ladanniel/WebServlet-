package cn.tedu.web.servlet.hellowservlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet{
	
	public HelloServlet() {
		System.out.println("helloserlet的构造器");
	}

	@Override
	/**1、Servlet收到容器请求之后，会将Servlet实例化
	 * 2、接下来调用该实例的servlet方法来处理请求
	 * 3、容器会将请求对象和响应对象作为参数传递进来
	 * 注解：容器会提供网络相关的服务，这里容器会解析请求数据包并且将解析到的数据存放到请求（request）对象里面
	 * 开发人员不用再解析请求包，只需要调用请（request）求的方法即可
	 *    同理：开发人员只需要，调用响应（response）对象方法写入处理结果，容器会从响应（response）对象中取出处理结果，
	 *         然后生成响应数据包
	 *         容器提供网络相关的服务
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
              System.out.println("这是helloservlet的service方法");
              /**
               * 依据请求参数名，获得请求参数值。请求参数名不能写错，否则会return null
               */
              String number = request.getParameter("number");
              Integer.parseInt(number);//测试代码严谨度
              
              /**
               * 设置content-type消息头的（key-value）值，告诉浏览器服务器返回的数据类型
               */
              response.setContentType("text/html");
              /**
               * 通过响应(response)对象获取一个输出流
               */
              PrintWriter out = response.getWriter();
              /**
               * getwriter 将数据写入到响应里
               * 容器会从response中取出数据，然后创建response数据包，并发送给浏览器
               */
              out.println("<h1>HELLO KITY"+number+"</h1>");
              out.close();
	}
	

}
