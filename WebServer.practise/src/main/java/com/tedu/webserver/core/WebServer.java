package com.tedu.webserver.core;
/*a/����(request���������У���Ϣͷ����Ϣ����)����
 * b/����response��һ����Ӧ��Ϊ�����֣�״̬�У���Ӧͷ����Ӧ���ģ�-
 * c/������վ��index.html ����ͼƬ��ʾ��
 -d/���������ܳ����쳣������Exception-��
  e/Content-TypeͼƬ�ı��ȸ�ʽ�������mappingӳ���ϵ(
  f/�������Ӧͷ������ʵ������ع�����Щ��ʽ������ʵ�崫��)��
 -g/�ع���Ӧͷ���ṩ����������Ӧͷ����ڡ���
 ��h/��ҳindex����ͨ���󣬿�ʼ����ҵ���ڿ�ʼ����ע��ҵ��֮ǰ��
  i/�ȶ�url���н�һ��������
1��������ύ�����·������
 * �����в����ģ��磺/myweb/index.html
 *2�����в����ģ��磺/myweb/reg?username=fan&password=123&......��name--key,123--value��
 *-���ع�����URL��ͬʱ�ع�ע��͵�¼ҵ��-���÷�����ƣ�����Ŀ:
 *j/���䣺��servlet��ת�������ƣ�����forWord���������Է����������
 *1��ָ��server.xml·����
 *2����������(ת��)�� httpServlet;
 *k/ת�룬����Ҳ�ɶ�
 *l/���ύ�����ַ�ʽ�ֱ�Ϊ��get��post�����ڰ�ȫ���ǣ����ύѡ��poset��ʽ����post��ʽ��������Ϣ����������Ϣ������,����Ҫ����Ϣ���Ľ����ع�
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
			System.out.println("����һ������˶˿�");
			server = new ServerSocket(8080);
			threadPool = Executors.newFixedThreadPool(50);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	public void start(){
		System.out.println("�ع���Ϊ���Ⲣ��״�����˴���Ϊ�̳߳�");
		try {
			while(true){
			System.out.println("�ȴ��ͻ������ӡ�������");
			Socket socket = server.accept();
			ClientHandler handler = new ClientHandler(socket);
			threadPool.execute(handler);
			System.out.println("һ���ͻ����Ѿ����ӣ�");
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
