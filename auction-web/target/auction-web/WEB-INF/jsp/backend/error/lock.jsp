<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2018/9/10
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <jsp:include page="../common/base.jsp" />
</head>
<body class="hold-transition lockscreen">
<!-- Automatic element centering -->
<div class="lockscreen-wrapper">
    <div class="lockscreen-logo">
        <a href="#"><b>萤火</b>拍卖</a>
    </div>
    <!-- User name -->
    <div class="lockscreen-name">（锁屏中）</div>

    <!-- START LOCK SCREEN ITEM -->
    <div class="lockscreen-item">
        <!-- lockscreen image -->
        <div class="lockscreen-image">
            <img src="../assets/libs/AdminLTE-2.4.5/dist/img/user1-128x128.jpg" alt="User Image">
        </div>
        <!-- /.lockscreen-image -->

        <!-- lockscreen credentials (contains the form) -->
        <form class="lockscreen-credentials" action="/erp/Lock.html" id="lock_submit" method="post">
            <div class="input-group">
                <input type="password" class="form-control" name="password" placeholder="password">
                <div class="input-group-btn">
                    <button type="button" class="btn" onclick="$('#lock_submit').submit()"><i class="fa fa-arrow-right text-muted"></i></button>
                </div>
            </div>
        </form>
        <!-- /.lockscreen credentials -->
    </div>
    <!-- /.lockscreen-item -->
    <div class="text-center">
        <c:if test="${message != null}">
            <span style="color: red;">密码错误</span>
        </c:if>
    </div>
    <div class="help-block text-center">
        Enter your password to retrieve your session
    </div>
    <div class="text-center">
        <a href="/erp/Login.html">Or sign in as a different user</a>
    </div>
    <div class="lockscreen-footer text-center">
        Copyright &copy; 2018-2019 <b><a href="#" class="text-black">萤火拍卖</a></b><br>
        All rights reserved
    </div>
</div>
<!-- /.center -->

<!-- jQuery 3 -->
<script src="../assets/libs/AdminLTE-2.4.5/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="../assets/libs/AdminLTE-2.4.5/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
</body>
</html>
</html>
