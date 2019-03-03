package com.tedu.webserver.core;
/*a/解析(request请求：请求行，消息头，消息正文)——
 * b/处理response（一个响应分为三部分：状态行，响应头，响应正文）-
 * c/测试网站（index.html 包括图片显示）
 -d/（解析可能出现异常）处理Exception-（
  e/Content-Type图片文本等格式处理采用mapping映射关系(
  f/必须对响应头和设置实体进行重构，这些格式依赖于实体传送)）
 -g/重构响应头并提供可以设置响应头的入口——
 —h/网页index测试通过后，开始处理业务，在开始处理注册业务之前，
  i/先对url进行进一步解析：
1、处理表单提交：后的路径问题
 * 不含有参数的，如：/myweb/index.html
 *2：含有参数的，如：/myweb/reg?username=fan&password=123&......（name--key,123--value）
 *-（重构解析URL）同时重构注册和登录业务-利用反射机制，简化项目:
 *j/反射：与servlet的转发很相似：都是forWord（）；所以反射的条件：
 *1：指定server.xml路径；
 *2：创建反射(转发)类 httpServlet;
 *k/转码，中文也可读
 *l/表单提交有两种方式分别为：get和post，出于安全考虑，表单提交选择poset方式，而post方式的所有消息都隐藏在消息正文中,所以要对消息正文进行重构
 *
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebServer {
	private ServerSocket server;
	private ExecutorService threadPool;
	public WebServer() {
		try {
			System.out.println("开启一个服务端端口");
			server = new ServerSocket(8080);
			threadPool = Executors.newFixedThreadPool(50);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	public void start(){
		System.out.println("重构：为避免并发状况：此处改为线程池");
		try {
			while(true){
			System.out.println("等待客户端连接、、、、");
			Socket socket = server.accept();
			ClientHandler handler = new ClientHandler(socket);
			threadPool.execute(handler);
			System.out.println("一个客户端已经连接！");
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		WebServer service = new WebServer();
		service.start();
	}

}
