<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>统一认证系统</h1>

    <a href="${pageContext.request.contextPath}/aaa/get1">需要认证的url:   http://localhost:8084/Project12_AuthCenter_SpringMVC/aaa/get1</a>

    <hr/>
    <br/>
    <a href="${pageContext.request.contextPath}/bbb/get1">不需要认证的url:   http://localhost:8084/Project12_AuthCenter_SpringMVC/bbb/get1</a>
</body>
</html>
