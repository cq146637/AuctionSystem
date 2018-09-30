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
                    订单管理
                    <small>订单信息管理</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                    <li class="active">订单管理</li>
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
                                <h3 class="box-title">添加订单</h3>
                            </div>
                            <!-- /.box-header -->
                            <!-- form start -->
                            <form role="form" action="/erp/InsertOrderInfo.html" method="post">
                                <div class="box-body">
                                    <div class="form-group" style="display: none">
                                        <label for="auction_record_id_input">竞拍记录ID</label>
                                        <input type="text" class="form-control" id="auction_record_id_input" name="auctionRecordId" value="${productAuctionList[0][1]}" placeholder="竞拍记录ID">
                                    </div>
                                </div>
                                <div class="box-body">
                                    <div class="form-group">
                                        <label>商品名称</label>
                                        <select id="select_product" class="form-control select2 select2-hidden-accessible" style="width: 100%;" tabindex="-1" aria-hidden="true" name="productName">
                                            <c:forEach var="productAuction" items="${productAuctionList}" varStatus="status" >
                                                <c:if test="${status.index == 0}">
                                                    <option selected="selected" value="${productAuction[1]}">${productAuction[0]}</option>
                                                </c:if>
                                                <c:if test="${status.index != 0}">
                                                    <option value="${productAuction[1]}">${productAuction[0]}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="box-body">
                                    <div class="form-group">
                                        <label for="exampleInputText3">收货地址</label>
                                        <input type="text" class="form-control" id="exampleInputText3" name="acceiptAddress"  placeholder="收货地址">
                                    </div>
                                </div>
                                <div class="box-body">
                                    <div class="form-group">
                                        <label for="exampleInputText4">电话号码</label>
                                        <input type="tel" class="form-control" id="exampleInputText4" name="phone"  placeholder="电话号码">
                                    </div>
                                </div>
                                <div class="box-body">
                                    <div class="form-group">
                                        <label >快递商家</label>
                                        <select class="form-control select2 select2-hidden-accessible" style="width: 100%;" tabindex="-1" aria-hidden="true" name="deliverType">
                                            <option selected="selected">京东快递</option>
                                            <option>圆通快递</option>
                                            <option>顺丰快递</option>
                                        </select>
                                    </div>
                                </div>
                                <!-- /.box-body -->
                                <div class="box-footer">
                                    <button type="submit" class="btn btn-primary" onclick="toastr.success('提交数据成功');">提交</button>
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
    <!-- toastr -->
    <link href="../assets/libs/AdminLTE-2.4.5/bower_components/toastr/toastr.min.css" rel="stylesheet" />
    <script src="../assets/libs/AdminLTE-2.4.5/bower_components/toastr/toastr.min.js"></script>
    <!-- AdminLTE App -->
    <script src="../assets/libs/AdminLTE-2.4.5/dist/js/adminlte.min.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="../assets/libs/AdminLTE-2.4.5/dist/js/demo.js"></script>
    <script>
        toastr.options.positionClass = 'toast-bottom-right';
        $("#select_product").change(function () {
            $("#auction_record_id_input").val($(this).val());
        })
    </script>
</body>
</html>
