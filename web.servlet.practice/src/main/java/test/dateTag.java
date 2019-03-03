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
		System.out.println("这是日期的doTag方法");
		PageContext pc = (PageContext) getJspContext();//获得页面上下文,通过得到的页面上下文方法来获取out隐含的out输出方法 并用标签输出工具去接收 getOut方法
		JspWriter out= pc.getOut();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		out.println(sdf.format(date));


	}


}
