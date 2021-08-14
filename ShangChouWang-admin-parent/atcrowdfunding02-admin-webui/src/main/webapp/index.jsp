<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="layer/layer.js"></script>
<script type="text/javascript">

    $(function () {
        $("#btn1").click(function () {
           $.ajax({
               "url": "send/array.one.html",           // 请求目标资源的地址
               "type": "post",                     // 请求方式
               "data": {
                   "array": [5,8,12]               // 要发送的请求参数
                },
               "dataType": "text",                 // 如何对待服务器端返回的数据
               "success": function (response) {    // 服务器端成功处理请求后调用的回调函数
                   alert("Success");
               },
               "error":function (response) {       // 服务器端失败处理请求后调用的回调函数
                   alert("Fail");
               }
           });
        });

        $("#btn2").click(function () {

            var array = [5,8,12];
            var responboy = JSON.stringify(array);

            $.ajax({
                "url": "send/array.two.json",       // 请求目标资源的地址
                "type": "post",                     // 请求方式
                "data": responboy,
                "contentType": "application/json;charset=UTF-8",
                "dataType": "json",                 // 如何对待服务器端返回的数据
                "success": function (response) {    // 服务器端成功处理请求后调用的回调函数
                    alert("Success");
                    console.log(response)
                },
                "error":function (response) {       // 服务器端失败处理请求后调用的回调函数
                    alert("Fail");
                }
            });
        });

        $("#btn3").click(function () {

            // 准备好要发送到服务器的数组
            var array = [5, 8, 12];

            // 将JSON数组转换为JSON字符串
            var requestBoy = JSON.stringify(array);

            $.ajax({
                "url": "send/array.three.html",     // 请求目标资源的地址
                "type": "post",                     // 请求方式
                "data": requestBoy,                 // 请求体
                "contentType": "application/json;charset=UTF-8",   // 设置请求体的内容类型,告诉服务器端本次请求的请求体是JSON数据
                "dataType": "text",                 // 如何对待服务器端返回的数据
                "success": function (response) {    // 服务器端成功处理请求后调用的回调函数
                    alert("Success");
                },
                "error":function (response) {       // 服务器端失败处理请求后调用的回调函数
                    alert("Fail");
                }
            });
        });

    });
</script>
</head>


<body>

    <a href="test/ssm.html">测试SSM整合环境</a>

    <br/>
    <br/>

    <button id="btn1">Send [5,8,12] One</button>

    <br/>
    <br/>
    <button id="btn2">Send [5,8,12] Two</button>

    <br/>
    <br/>
    <button id="btn3">Send [5,8,12] Three</button>

    <%response.sendRedirect("admin/to/login/page.html");%>
</body>
</html>
