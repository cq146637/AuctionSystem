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
                        <div class="box">
                            <div class="box-header">
                                <h3 class="box-title">订单列表</h3>
                            </div>
                            <!-- /.box-header -->
                            <div class="box-body">
                                <table id="example1" class="table table-bordered table-striped">
                                    <thead>
                                    <tr>
                                        <th><input type="checkbox" id="order_info_choice" name="choice"></th>
                                        <th>ID</th>
                                        <th>Auction Record ID</th>
                                        <th>Product Name</th>
                                        <th>Pay Username</th>
                                        <th>Accept Address</th>
                                        <th>Phone</th>
                                        <th>Deliver Type</th>
                                        <th>Date</th>
                                        <th>Status</th>
                                        <th>Options</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="orderInfo" items="${orderInfoList}">
                                        <tr>
                                            <td><input type="checkbox" name="choice"></td>
                                            <td>${orderInfo.id}</td>
                                            <td>${orderInfo.auctionRecordId}</td>
                                            <td>${productMap[orderInfo.auctionRecordId]}</td>
                                            <td>${payUserMap[orderInfo.auctionRecordId]}</td>
                                            <td>${orderInfo.acceiptAddress}</td>
                                            <td>${orderInfo.phone}</td>
                                            <td>${orderInfo.deliverType}</td>
                                            <td>${orderInfo.createTime}</td>
                                            <td><span class="label label-success">Normal</span></td>
                                            <td><i class="fa fa-edit" onclick="editOrderInfo(this);"><div style="width: 35px"></div></i><i class="fa fa-remove" onclick="deleteOrderInfo(this);"></i></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                    <tfoot>
                                    <tr>
                                        <th><i class="fa fa-remove"></i></th>
                                        <th>ID</th>
                                        <th>Auction Record ID</th>
                                        <th>Product Name</th>
                                        <th>Pay Username</th>
                                        <th>Accept Address</th>
                                        <th>Phone</th>
                                        <th>Deliver Type</th>
                                        <th>Date</th>
                                        <th>Status</th>
                                        <th>Options</th>
                                    </tr>
                                    </tfoot>
                                </table>
                                <div style="width: 10%; padding-left: 0;">
                                    <button type="button" class="btn btn-block btn-info" onclick="window.location.href='/erp/InsertOrderInfo.html'">添加</button>
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
        });
        function editOrderInfo(ths) {
            var aid = $(ths).parent().parent().children()[1].innerText;
            var product_name = $(ths).parent().parent().children()[3].innerText;
            var pay_username = $(ths).parent().parent().children()[4].innerText;
            window.location.href = "/erp/QueryOrderInfo.html?id=" + aid + "&productName=" + product_name + "&payUserName=" + pay_username;
        }
        function deleteOrderInfo(ths) {
            var aid = $(ths).parent().parent().children()[1].innerText;
            window.location.href = "/erp/DeleteOrderInfo.html?id=" + aid
        }
        $("#order_info_choice").click(function () {
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
