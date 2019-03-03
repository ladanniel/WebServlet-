<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<style type="text/css">
     form {
	text-align: left;
}

</style>
</head>
<body style="font-size: 30px; color: red;">
	欢迎登录 Login......
	<form action="login.do" method="post">
		<fieldset>
			<legend>登录</legend>
			用户名：<input name="username" type="text"><br /> 
		         密    码：<input name="password" type="password"><br /> 
		          <input type="submit" value="确定">
		          ${login_failed }
		</fieldset>
	</form>


</body>
</html>