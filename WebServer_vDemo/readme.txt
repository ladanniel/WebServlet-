   <v1>本版本改动
1：添加ClientHandler类，实现服务端通过多线程可以处理多客户操作，
2：在ClientHandler中添加一个方法readLine，完成读取一行字符串，因为客户端发送的请求中，
   请求行与消息头是以行为为单位发送过来的内容（CRLF结尾为一行）
3：在ClientHandler中通过读取客户端发送的第一行字符串，即：
请求内容，来测试readLine方法；
pm13:00
   <v2>本版本改动：
将客户端发送过来的请求完后只能解析出来，生成一个httpRequest对象保存，以便后续操作。
1：新建一个包：http
2：在http包中定义类httpRequest，用其每一个实例表示一个具体的客户端发送过来的请求
3：在ClientHandler中实例化httpRequest 并将Socket传入以便HTTPRequest获取输入流解析客户端请求
4：在HTTPRequest的构造方法中完成解析工作；
由于一个请求含有三部分：请求行，消息头，消息正文，所以在构造方法中也分为三部分进行解析；
pm4:59
   <v3>本版本改动：
将客户端请求的页面响应给客户端；
本次版本要了解Http协议中的响应（Response）规则，要严格按照HTTP协议的响应格式给与客户端响应，这样浏览器
才能得到正确的结果。
localhost：8080/index.html;index.html={url}

1:在项目目录中添加一个新目录webapps,使用该目录保存不同网站所需要的素材内容；
2：在webapps目录下新建子目录myweb，myweb作为我们测试的网站资源的目录；
注：在tomcat中webapps目录是存放所有web应用的。其中每一个子目录都是一个具体的web应用(一个网站内容，
    涵盖页面，图片等素材以及处理业务逻辑的java代码等)
3:在myweb中添加页面index.html
4：浏览器的地址栏中请求某个服务端资源时，是无法卸载服务端该资源的绝对路径的，只能写相对路径（这样
的好处也很多；比如：浏览器地址栏输入地址为：
http://  localhost：8080   /index.html）
协议部分 服务器地址信息    资源路径部分（请求行url内容）
我们可以规定在资源路径部分中的根为我们定义的保存所有资源的目录webapps;
那么，若想找到webapps/myweb/index.html页面的话，浏览器地址栏要输入：
http://localhost:8080/myweb/index.html
   <v4>:当该请求发送到服务端时，我们解析请求中请求行里的url部分会得到/myweb/index.html，那么我们就对应的从
webapps目录中找到对应资源：webapps/myweb/index.html并将该资源响应给客户端；

根据请求行中的url，对应找到webapps目录中相应资源，若找到则准备响应给客户端；在ClientHandler中添加分支判断；
查找客户端请求的资源是否存在。

5：以一个HTTP标准响应格式，将用户请求的资源回复给客户端；

   <v5>本版本改动：
对v4代码进行优化，将响应提成一个对象来表示，复用响应操作代码，并且使得响应内容可以进行设置
1：在http包中定义类HTTPResponse，使用该类的每个实例表示一个具体发送给客户端的响应，
2：在HTTPResponse中定义一个方法：flush，该方法用于将当前响应内容按照http协议的响应格式发送给客户端

3：修改ClientHandler中的响应代码，将以前写死的响应过程修改为设置一个HTTPResponse对象，最终调用flush给客户端响应即可

 <v6>-<v7>:本版本改动：在页面上支持对图片的显示工作，最终扩展通过此版本更新使服务端支持客户端请求的任意资源进行正确响应。
 <v7>解决客户端发送空请求的问题：HTTP协议允许客户端发送一个空请求（不是按照标准的request格式发送，而是没有内容）
 由于ClientHandler上来再解析request时我们读取请求行内容后会拆分三部分，如果此时发送的是空请求，则会出现数组
 下标越界的情况，导致后续操作无法进行，对于空请求，服务端的实际操作作为忽略即可，为此我们做如下改动
 
 1:在core包中定义一个自定义异常：EmptyRequestException；
 2:当解析请求遇到空请求时，HttpRequest抛出该异常给ClientHandler；
 3：ClientHandler在捕获空请求后，直接与客户端断开连接即可；
 在页面上支持对图片的显示工作，最终扩展通过此版本更新使服务端支持客户端请求的任意资源进行正确响应。
 
 1:在webapps/mayweb下添加一张图片
 2：在index.html中添加显示该图片的标签<img....>
 3:将webServer的start方法中循环接收客户端连接的while循环接触注释
 
 测试后发现，客户端在显示index页面时，实际对服务端请求了两次，第一次是请求我们在地址栏输入index页面内容，
 当浏览器解释该页面内容时，发现<img>标签后，会根据该标签
 中指定的路径再次发起连接请求获取该图片文件，虽然图片可以正常被显示，但服务端在响应该图片时在响应头中描述
 该图片类型时，依然告知客户端为html代码。（因为HTTPResponse中发送响应头的Content-Type是写死的发送“text/html”）
 这会导致将来客户端在请求其他资源时不能正常理解该资源导致显示出现异常；
 
 
 解决方式
 1：在HTTpContentext类中定义一个方法，可以根据资源名的后缀获取其对应的COntent-Type的值
 1.1在HttpCententtext中定义一个Map类型的静态属性，MIE_MAPPING，其中key为资源后缀名，value为其对应的
 Content-Type的值，
 1.2添加一个初始化该MAP的方法：intiMimeMapping
 1.3在HttpContext的静态块中调用初始化方法
 1.4：定义静态方法：getMinmeType(String ext)用来根据指定的资源后缀获取对应的Content-Type的值
 
 2：重构响应：
 在sendHerders方法中，现在的做法是发送了两个头，Content-Type与Content-Lnegth。但实际上不是每个响应头
 都必须包含这两个头，并且也可能会包含其他头，为此发送的响应头应当也是可以设置的；
 2.1：在HTTPResponse中添加一个Map类型的属性headers用于保存本次响应要发送给客户端的响应头内容，其中key为响应头的名字
      value为响应头的值
 2.2：提供一个putHeaders方法，允许外界向当前响应中设置要发送的响应头内容
 2.3：sendHeaders方法改为遍历headers这个Map，将每个响应头发送至客户端；
 
 
 
 <v7>PM   本版本改动：
 支持所有介质类型。tomcat中的conf目录里有一个web.xml文件，该文件中配置这所有资源后缀与对应的Content-Type的值，我们将该文件拷贝到我们的项目中，并在
 HTTPContext的initMimeMapping方法中解析该文件来初始化介质映射
 1：在项目目录中新建一个目录conf
 2：将tomcat中的web.xml文件拷贝到conf目录中；
 3：导入dom4j的jar包
 4：修改HTTPContext初始化介质类型映射的方法：initMimeMapping
 <v8> 本版本改动
 使服务器端支持处理业务操作：完成注册业务；
 1：在webapp/myweb/下新建一个注册页面reg.html
 
 
 