package cn.tedu.web.servlet.user;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;


public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "楚乔";
		/**
		 * URLEncoder，该方法的作用会使用指定的字符集进行编码，
		 * 然后将编码之后的字节转换成一个字符串（该字符串是由一系列16进制数加%组成）；
		 */
		String str2 =URLEncoder.encode(str,"utf-8");
		System.out.println(str2);
		String str3 = URLDecoder.decode(str2,"utf-8");
		System.out.println("str3:"+str3);
	}
}
