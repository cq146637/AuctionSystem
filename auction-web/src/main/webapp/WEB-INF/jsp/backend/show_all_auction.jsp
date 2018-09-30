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
                        <div class="box">
                            <div class="box-header">
                                <h3 class="box-title">拍卖物品列表</h3>
                            </div>
                            <!-- /.box-header -->
                            <div class="box-body">
                                <table id="example1" class="table table-bordered table-striped">
                                    <thead>
                                    <tr>
                                        <th><input type="checkbox" id="auction_info_choice" name="choice"></th>
                                        <th>ID</th>
                                        <th>productId</th>
                                        <th>productName</th>
                                        <th>basePrice</th>
                                        <th>topPrice</th>
                                        <th>beginTime</th>
                                        <th>endTime</th>
                                        <th>Status</th>
                                        <th>payCount</th>
                                        <th>Options</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="auction" items="${auctionInfoList}">
                                        <tr>
                                            <td><input type="checkbox" name="choice"></td>
                                            <td>${auction.id}</td>
                                            <td>${auction.productId}</td>
                                            <td>${productMap[auction.id]}</td>
                                            <td>${auction.basePrice}</td>
                                            <td>${auction.topPrice}</td>
                                            <td>${auction.beginTime}</td>
                                            <td>${auction.endTime}</td>
                                            <td><span class="label label-success">${auction.status}</span></td>
                                            <td>${auction.payCount}</td>
                                            <td><i class="fa fa-edit" onclick="editAuctionInfo(this);"><div style="width: 35px"></div></i><i class="fa fa-remove" onclick="deleteAuctionInfo(this);"></i></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                    <tfoot>
                                    <tr>
                                        <th><i class="fa fa-remove"></i></th>
                                        <th>ID</th>
                                        <th>productId</th>
                                        <th>productName</th>
                                        <th>basePrice</th>
                                        <th>topPrice</th>
                                        <th>beginTime</th>
                                        <th>endTime</th>
                                        <th>Status</th>
                                        <th>payCount</th>
                                        <th>Options</th>
                                    </tr>
                                    </tfoot>
                                </table>
                                <div style="width: 10%; padding-left: 0;">
                                    <button type="button" class="btn btn-block btn-info" onclick="window.location.href='/erp/InsertAuctionInfo.html'">添加</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <!-- Main content -->
            <section class="content">
                <!-- Main row -->
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                            <div class="box-header">
                                <h3 class="box-title">竞拍记录列表</h3>
                            </div>
                            <!-- /.box-header -->
                            <div class="box-body">
                                <table id="example1_1" class="table table-bordered table-striped">
                                    <thead>
                                    <tr>
                                        <th><input type="checkbox" id="auction_record_choice" name="choice"></th>
                                        <th>ID</th>
                                        <th>payUserId</th>
                                        <th>payUserName</th>
                                        <th>auctionId</th>
                                        <th>ProductName</th>
                                        <th>price</th>
                                        <th>status</th>
                                        <th>Date</th>
                                        <th>Options</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="auction" items="${auctionRecordList}">
                                        <tr>
                                            <td><input type="checkbox" name="choice"></td>
                                            <td>${auction.id}</td>
                                            <td>${auction.payUserId}</td>
                                            <td>${payUserMap[auction.payUserId]}</td>
                                            <td>${auction.auctionId}</td>
                                            <td>${productMap[auction.auctionId]}</td>
                                            <td>${auction.price}</td>
                                            <td><span class="label label-success">${auction.status}</span></td>
                                            <td>Wed Oct 10 20:19:22 CST 2018</td>
                                            <td><i class="fa fa-edit" onclick="editAuctionRecord(this);"><div style="width: 35px"></div></i><i class="fa fa-remove" onclick="deleteAuctionRecord(this);"></i></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                    <tfoot>
                                    <tr>
                                        <th><i class="fa fa-remove"></i></th>
                                        <th>ID</th>
                                        <th>payUserId</th>
                                        <th>payUserName</th>
                                        <th>auctionId</th>
                                        <th>ProductName</th>
                                        <th>price</th>
                                        <th>status</th>
                                        <th>Date</th>
                                        <th>Options</th>
                                    </tr>
                                    </tfoot>
                                </table>
                                <div style="width: 10%; padding-left: 0;">
                                    <button type="button" class="btn btn-block btn-info" onclick="window.location.href='/erp/InsertAuctionRecord.html'">添加</button>
                                </div>
                            </div>
                        </div>
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
    <!-- DataTables -->
    <script src="../assets/libs/AdminLTE-2.4.5/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="../assets/libs/AdminLTE-2.4.5/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
    <!-- SlimScroll -->
    <script src="../assets/libs/AdminLTE-2.4.5/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
    <!-- FastClick -->
    <script src="../assets/libs/AdminLTE-2.4.5/bower_components/fastclick/lib/fastclick.js"></script>
    <!-- AdminLTE App -->
    <script src="../assets/libs/AdminLTE-2.4.5/dist/js/adminlte.min.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="../assets/libs/AdminLTE-2.4.5/dist/js/demo.js"></script>
    <!-- page script -->
    <script>
        $(function () {
            $('#example1').DataTable();
            $('#example1_1').DataTable();
        });
        function editAuctionRecord(ths) {
            var aid = $(ths).parent().parent().children()[1].innerText;
            var pay_username = $(ths).parent().parent().children()[3].innerText;
            var product_name = $(ths).parent().parent().children()[5].innerText;
            window.location.href = "/erp/QueryAuctionRecord.html?id=" + aid + "&payUserName=" + pay_username + "&productName=" + product_name
        }
        function deleteAuctionRecord(ths) {
            var aid = $(ths).parent().parent().children()[1].innerText;
            window.location.href = "/erp/DeleteAuctionRecord.html?id=" + aid
        }
        function deleteAuctionInfo(ths) {
            var aid = $(ths).parent().parent().children()[1].innerText;
            console.log(aid);
            window.location.href = "/erp/DeleteAuctionInfo.html?id=" + aid
        }
        function editAuctionInfo(ths) {
            var aid = $(ths).parent().parent().children()[1].innerText;
            var product_name = $(ths).parent().parent().children()[3].innerText;
            window.location.href = "/erp/QueryAuctionInfo.html?id=" + aid + "&productName=" + product_name
        }
        $("#auction_info_choice").click(function () {
            if ($(this).prop('checked')) {
                $(this).attr("checked", "checked");
                $(this).parent().parent().parent().next().children().each(function (index, ths) {
                    $($(ths).children()[0].children).attr("checked", "checked");
                })
            } else {
                $(this).removeAttr("checked");
                $(this).parent().parent().parent().next().children().each(function (index, ths) {
                    $($(ths).children()[0].children).removeAttr("checked");
                })

            }
        });
        $("#auction_record_choice").click(function () {
            if ($(this).prop('checked')) {
                $(this).attr("checked", "checked");
                $(this).parent().parent().parent().next().children().each(function (index, ths) {
                    $($(ths).children()[0].children).attr("checked", "checked");
                })
            } else {
                $(this).removeAttr("checked");
                $(this).parent().parent().parent().next().children().each(function (index, ths) {
                    $($(ths).children()[0].children).removeAttr("checked");
                })

            }
        });
    </script>
</body>
</html>
