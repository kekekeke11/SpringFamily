<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://cdn.staticfile.org/jquery/3.5.1/jquery.js"></script>
</head>
<body>
    <h1>客户的系统</h1>

    <a href="${pageContext.request.contextPath}/getToken">获取Token</a>
    <hr/>
    <button onclick="getToken()">获取Token存到本地浏览器，并准备携带Token访问另一个服务器上的资源</button>
    <hr/>
    <button onclick="alertToken()">弹出本地浏览器存储的Token</button>
    <button onclick="clearToken()">清空本地浏览器存储的Token</button>
</body>
<script>
    //获取Token，获取成功后，准备携带Token访问另一个服务器上的资源
    function getToken(){
        alert("执行");
        $.ajax({
            url:"${pageContext.request.contextPath}/getToken" ,
            success:function(result){
                if(result!=null){
                    // alert(result.access_token);
                    window.localStorage.setItem("access_token", result.access_token);
                    //获取到Token后，请求其它服务资源
                    window.location.href = "http://localhost:8084/Project12_AuthCenter_SpringMVC/aaa/get1?access_token="+ window.localStorage.getItem("access_token");
                }else {
                    alert("获取到的Token为空")
                }
                // alert(result);
                // alert(JSON.stringify(result));
            },
            error: function(xhr,status,error){
                alert("获取Token失败");
            }
        });
    }

    function alertToken(){
        alert(window.localStorage.getItem("access_token"));
    }

    function clearToken(){
        window.localStorage.clear();
    }
</script>
</html>
