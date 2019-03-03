package demo.controller;
//处理连接到jsp的逻辑方法
//处理逻辑 处理业务逻辑
import javax.servlet.http.HttpServletRequest;
import base.annotation.RequestMapping;

public class HelloController {
	@RequestMapping("/hello.do")
	public String Hello(){
		System.out.println("helloController的方法调用了");
		//返回视图名
		return "hello";
	}
	@RequestMapping("/toLogin.do")
	public String toLogin(){
		System.out.println("这是HelloController的toLogin方法");
		
		return "login";
	}
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request){
		
		System.out.println("这是HelloController的login方法");
		//读取用户名和密码
		String  name = request.getParameter("uname");
		System.out.println("name:"+name);
		String pwd = request.getParameter("password");
		if ("ssd".equals(name) && "test".equals(pwd)) {
			// 登录成功
			return "redirect:welcome.do";
			
		}
		else{
			//登录失败
			request.setAttribute("login_failed", "用户名或密码错误");
		}
		return "redirect:welcome.do";
	}
	@RequestMapping("/welcome.do")
	public String wel(){
		System.out.println("这是HelloController的wel方法");
		return "welcome";
	}

}
