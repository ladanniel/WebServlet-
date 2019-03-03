package demo.controller;
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
		String  name = request.getParameter("uname");
		System.out.println("name:"+name);
		String pwd = request.getParameter("password");
		if ("ssd".equals(name) && "test".equals(pwd)) {
			// ��¼�ɹ�
			return "redirect:welcome.do";
			
		}
		else{
			//��¼ʧ��
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
