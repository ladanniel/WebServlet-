package cn.tedu.web.servlet.user;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;


public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "����";
		/**
		 * URLEncoder���÷��������û�ʹ��ָ�����ַ������б��룬
		 * Ȼ�󽫱���֮����ֽ�ת����һ���ַ��������ַ�������һϵ��16��������%��ɣ���
		 */
		String str2 =URLEncoder.encode(str,"utf-8");
		System.out.println(str2);
		String str3 = URLDecoder.decode(str2,"utf-8");
		System.out.println("str3:"+str3);
	}
}
