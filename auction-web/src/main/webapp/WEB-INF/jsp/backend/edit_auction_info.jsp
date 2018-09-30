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
                    拍卖管理
                    <small>历史拍卖信息管理</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                    <li class="active">拍卖管理</li>
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
                                <h3 class="box-title">修改拍卖物品信息</h3>
                            </div>
                            <!-- /.box-header -->
                            <!-- form start -->
                            <form role="form" action="/erp/UpdateAuctionInfo.html" method="post">
                                <div class="box-body">
                                    <div class="form-group">
                                        <label for="exampleInputText0">竞拍物品ID</label>
                                        <input type="text" class="form-control" id="exampleInputText0" name="id" value="${auctionInfo.id}" placeholder="竞拍物品ID" readonly="readonly">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputText10">产品Id</label>
                                        <input type="text" class="form-control" id="exampleInputText10" name="productId" value="${auctionInfo.productId}" placeholder="产品Id" readonly="readonly">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputText1">产品名称</label>
                                        <input type="text" class="form-control" id="exampleInputText1" name="productName" value="${productName}" placeholder="产品名称" readonly="readonly">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputText2">起拍价</label>
                                        <input type="text" class="form-control" id="exampleInputText2" name="basePrice" value="${auctionInfo.basePrice}" placeholder="起拍价">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputText3">当前最高价</label>
                                        <input type="text" class="form-control" id="exampleInputText3" name="topPrice" value="${auctionInfo.topPrice}" placeholder="当前最高价">
                                        <%--<p class="help-block">Example block-level help text here.</p>--%>
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputText4">开始时间</label>
                                        <input type="text" class="form-control" id="exampleInputText4" name="beginTime" value="${auctionInfo.beginTime}" placeholder="开始时间">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputText5">结束时间</label>
                                        <input type="text" class="form-control" id="exampleInputText5" name="endTime" value="${auctionInfo.endTime}" placeholder="结束时间">
                                    </div>
                                    <div class="form-group">
                                        <label>当前状态</label>
                                        <select class="form-control select2 select2-hidden-accessible" style="width: 100%;" tabindex="-1" aria-hidden="true" name="status">
                                            <option selected="selected">${auctionInfo.status}</option>
                                            <option>未开始</option>
                                            <option>正在进行</option>
                                            <option>已结束</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputText7">竞拍次数</label>
                                        <input type="text" class="form-control" id="exampleInputText7" name="payCount" value="${auctionInfo.payCount}" placeholder="竞拍次数">
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
