package com.tedu.webserver.http;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
/**响应对象
 * 该类的每个实例用于表示一个服务端发送给客户端的响应内容
 * 注：发送给客户端的内容
 * 注：设计原则：高类聚，低耦合（功能拆的不能再拆：每个部分只负责应该负责的那一部分）
 */
public class HttpResponse {
	private Socket socket; //客户端接口
	private OutputStream out;
	private int statusCode;//发送状态
	private File entity;//文件实体
	
	public HttpResponse(Socket socket){
		/**响应正文相关信息定义
		 * 要响应的实体文件（file不一样）
		 */
		 
		 try {
			 this.socket=socket;
			this.out = socket.getOutputStream();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		 
		
		
		
	}
	//将响应内容以http标准格式发送给客户端
	public void flush(){
		/**响应客户端做三件事
		 * 1：发送状态行
		 * 2：发送响应头
		 * 3：发送响应正文
		 */
		sendStatusLine();
		sendHeaders();
		sendContent();
	}
	public void sendStatusLine(){
		
		
	}
	public void sendHeaders(){
		
	}
	public void sendContent(){
		
	}
	

}
