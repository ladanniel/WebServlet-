package test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class dateTag extends SimpleTagSupport{

	@Override
	public void doTag() throws JspException, IOException {
		System.out.println("�������ڵ�doTag����");
		PageContext pc = (PageContext) getJspContext();//���ҳ��������,ͨ���õ���ҳ�������ķ�������ȡout������out������� ���ñ�ǩ�������ȥ���� getOut����
		JspWriter out= pc.getOut();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		out.println(sdf.format(date));


	}


}
