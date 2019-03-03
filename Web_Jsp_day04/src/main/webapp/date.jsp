<%@ page import="java.util.*,java.text.*"  contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
      <head>
           <body style="font-size: 320%;font-family:华文隶书; color: red;">
                
                                       一骑红尘妃子笑，无人知是荔枝来；<br/>
                                              山中方一日，世上已千年
                                       <br/>
                <%
                 Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                out.println(sdf.format(date));  
                %>
           </body>
      </head>
</html>