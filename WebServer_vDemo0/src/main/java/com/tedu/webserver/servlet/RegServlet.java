package com.tedu.webserver.servlet;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Arrays;
import com.tedu.webserver.http.HttpRequest;
import com.tedu.webserver.http.HttpResponse;

//ClientHandlerĿǰΥ���˸��ھ�ԭ��Ҫ���й��ܲ��
//����ע��ҵ��
public class RegServlet extends HttpServlet{
	public void service(HttpRequest request ,HttpResponse response){
		/**�ж��Ƿ�����ҵ��
		 * 
		 *//**ע��ҵ������
		 * 1��ͨ��request��ȡ�û���ע��ҳ���������ע����Ϣ
		 * 2��ʹ��RandomAccessFile��user.dat�ļ�
		 * 3����giant�û���Ϣд���ļ�
		 * 4������response����Ӧע��ɹ�ҳ��
		 * 
		 */
		try{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		int age = Integer.parseInt(request.getParameter("age"));
		try (RandomAccessFile raf = new RandomAccessFile("user.bat","rw")){
			raf.seek(raf.length());
			byte[]data = username.getBytes();
			data = Arrays.copyOf(data, 32);
			raf.write(data);
			
			data = password.getBytes();
			data = Arrays.copyOf(data, 32);
			raf.write(data);
			
			data = nickname.getBytes();
			data = Arrays.copyOf(data, 32);
			raf.write(data);
			
			raf.writeInt(age);
			
			System.out.println("����ע��ҵ�񣬲�����쳣������������������");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		forward("/myweb/reg_success.html", request,response);
		System.out.println("ע��ɹ���������쳣��������������������������������");
	}catch (Exception e) {
		e.printStackTrace();
	}

}
}
