<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/8/18
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    登录成功
    <%=request.getSession().getAttribute("globalId")%>
    <%=request.getSession().getAttribute("userName")%>
    <a href="/ssoClient/user/loginOut">退出</a>
</body>
</html>
