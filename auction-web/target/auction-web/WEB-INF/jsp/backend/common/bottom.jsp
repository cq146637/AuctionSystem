<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2018/9/10
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>尾部导入JS文件</title>
</head>
<body>
    <!-- 目前先不放 -->
    <!-- Do Active Left Navigator javascript code--->
    <!-- jQuery 3 -->
    <script src="../assets/libs/AdminLTE-2.4.5/bower_components/jquery/dist/jquery.min.js"></script>
    <script>
        $(function () {
            // 打开对应导航条
            $(".sidebar-menu ul a").each(function () {
                if ($(this).attr('href') == window.location.pathname) {
                    $(this).parent().addClass("active").parent().parent().addClass("active");
                }
            });
        })
    </script>
</body>
</html>
