package controller;
//�������ӵ�jsp���߼�����
//�����߼� ����ҵ���߼�
import javax.servlet.http.HttpServletRequest;
import base.annotation.RequestMapping;

public class HelloController {
	@RequestMapping("/hello.do")
	public String Hello(){
		System.out.println("helloController�ķ���������");
		//������ͼ��
		return "hello";
	}
	@RequestMapping("/toLogin.do")
	public String toLogin(){
		System.out.println("����HelloController��toLogin����");
		
		return "login";
	}
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request){
		
		System.out.println("����HelloController��login����");
		//��ȡ�û���������
		String uname = request.getParameter("username");
		String pwd = request.getParameter("password");
		System.out.println("uname :"+uname+" pwd:"+pwd);
		if ("ssd".equals(uname) && "test".equals(pwd)) {
			return "redirect:welcome.do";
		}else{
			request.setAttribute("login_failed", "�û������������");
		}
			return "redirect:welcome.do";
	}
	@RequestMapping("/welcome.do")
	public String wel(){
		System.out.println("����HelloController��wel����");
		return "welcome";
	}

}
