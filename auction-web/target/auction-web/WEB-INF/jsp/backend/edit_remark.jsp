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
    <jsp:include page="common/base.jsp" />
</head>
<body>
    <div class="wrapper">
        <jsp:include page="common/header.jsp" />
        <jsp:include page="common/left.jsp" />
        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper">
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <h1>
                    评价管理
                    <small>评价信息管理</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                    <li class="active">评价管理</li>
                </ol>
            </section>

            <!-- Main content -->
            <section class="content">
                <!-- Main row -->
                <div class="row">
                    <div class="col-xs-12">
                        <!-- general form elements -->
                        <div class="box box-primary">
                            <div class="box-header with-border">
                                <h3 class="box-title">修改评价信息</h3>
                            </div>
                            <!-- /.box-header -->
                            <!-- form start -->
                            <form role="form" action="/erp/UpdateRemark.html" method="post">
                                <div class="box-body">
                                    <div class="form-group">
                                        <label for="exampleInputText0">评价ID</label>
                                        <input type="text" class="form-control" id="exampleInputText0" name="id" value="${remark.id}" placeholder="评价ID" readonly="readonly">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputText1">商品ID</label>
                                        <input type="text" class="form-control" id="exampleInputText1" name="productId" value="${remark.productId}" placeholder="商品ID" readonly="readonly">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputText6">商品名称</label>
                                        <input type="text" class="form-control" id="exampleInputText6" name="productName" value="${productName}" placeholder="商品名称" readonly="readonly">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputText2">竞拍者ID</label>
                                        <input type="text" class="form-control" id="exampleInputText2" name="payUserId" value="${remark.payUserId}" placeholder="竞拍者ID" readonly="readonly">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputText3">竞拍者</label>
                                        <input type="text" class="form-control" id="exampleInputText3" name="payUserName" value="${payUserName}" placeholder="竞拍者" readonly="readonly">
                                        <%--<p class="help-block">Example block-level help text here.</p>--%>
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputText4">评价内容</label>
                                        <textarea  class="form-control" id="exampleInputText4" name="content" placeholder="评价内容">${remark.content}</textarea>
                                    </div>
                                </div>
                                <!-- /.box-body -->
                                <div class="box-footer">
                                    <button type="submit" class="btn btn-primary">修改</button>
                                    <button type="button" class="btn btn-default" onclick="history.go(-1)">返回</button>
                                </div>
                            </form>
                        </div>
                        <!-- /.box -->
                    </div>
                </div>
            </section>
        </div>
        <jsp:include page="common/footer.jsp" />
        <jsp:include page="common/sidebar.jsp" />
        <jsp:include page="common/bottom.jsp" />
    </div>
    <!-- jQuery 3 -->
    <script src="../assets/libs/AdminLTE-2.4.5/bower_components/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap 3.3.7 -->
    <script src="../assets/libs/AdminLTE-2.4.5/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="../assets/libs/AdminLTE-2.4.5/bower_components/fastclick/lib/fastclick.js"></script>
    <!-- AdminLTE App -->
    <script src="../assets/libs/AdminLTE-2.4.5/dist/js/adminlte.min.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="../assets/libs/AdminLTE-2.4.5/dist/js/demo.js"></script>
</body>
</html>
