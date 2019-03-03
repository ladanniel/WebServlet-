package cn.tedu.webservlet.dateservlet;

import java.text.SimpleDateFormat;
import java.util.Date;

public class date {
  public static void main(String[] args) {
	  Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(date);
		System.out.println(str);
}
}
