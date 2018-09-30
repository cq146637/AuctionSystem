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
                                <h3 class="box-title">添加竞拍记录</h3>
                            </div>
                            <!-- /.box-header -->
                            <!-- form start -->
                            <form role="form" action="/erp/InsertAuctionRecord.html" method="post">
                                <div class="box-body">
                                    <div class="form-group" style="display: none">
                                        <label for="product_id_input">拍卖ID</label>
                                        <input type="text" class="form-control" id="product_id_input" name="auctionId" value="${productList[0][0]}" placeholder="产品ID">
                                    </div>
                                    <div class="form-group">
                                        <label>产品名称</label>
                                        <select id="select_product" class="form-control select2 select2-hidden-accessible" style="width: 100%;" tabindex="-1" aria-hidden="true" name="productName">
                                            <c:forEach var="product" items="${productList}" varStatus="status" >
                                                <c:if test="${status.index == 0}">
                                                    <option selected="selected" value="${product[0]}">${product[1]}</option>
                                                </c:if>
                                                <c:if test="${status.index != 0}">
                                                    <option value="${product[0]}">${product[1]}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group" style="display: none">
                                        <label for="pay_username_input">竞拍者ID</label>
                                        <input type="text" class="form-control" id="pay_username_input" name="payUserId" value="${payUserList[0].id}" placeholder="用户ID">
                                    </div>
                                    <div class="form-group">
                                        <label>竞拍者</label>
                                        <select id="select_pay_user" class="form-control select2 select2-hidden-accessible" style="width: 100%;" tabindex="-1" aria-hidden="true" name="payUserName">
                                            <c:forEach var="payUser" items="${payUserList}" varStatus="status" >
                                                <c:if test="${status.index == 0}">
                                                    <option selected="selected" value="${payUser.id}">${payUser.userName}</option>
                                                </c:if>
                                                <c:if test="${status.index != 0}">
                                                    <option value="${payUser.id}">${payUser.userName}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputText2">出价</label>
                                        <input type="text" class="form-control" id="exampleInputText2" name="price" placeholder="出价">
                                    </div>
                                    <div class="form-group">
                                        <label>当前状态</label>
                                        <select class="form-control select2 select2-hidden-accessible" style="width: 100%;" tabindex="-1" aria-hidden="true" name="status">
                                            <option selected="selected">出局</option>
                                            <option>领先</option>
                                        </select>
                                    </div>
                                </div>
                                <!-- /.box-body -->

                                <div class="box-footer">
                                    <button type="submit" class="btn btn-primary">提交</button>
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
    <!-- date-range-picker -->
    <script src="../assets/libs/AdminLTE-2.4.5/bower_components/moment/min/moment.min.js"></script>
    <script src="../assets/libs/AdminLTE-2.4.5/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
    <!-- daterange picker -->
    <link rel="stylesheet" href="../assets/libs/AdminLTE-2.4.5/bower_components/bootstrap-daterangepicker/daterangepicker.css">
    <!-- bootstrap datepicker -->
    <link rel="stylesheet" href="../assets/libs/AdminLTE-2.4.5/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
    <!-- FastClick -->
    <script src="../assets/libs/AdminLTE-2.4.5/bower_components/fastclick/lib/fastclick.js"></script>
    <!-- AdminLTE App -->
    <script src="../assets/libs/AdminLTE-2.4.5/dist/js/adminlte.min.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="../assets/libs/AdminLTE-2.4.5/dist/js/demo.js"></script>
    <script>
        //Date range picker with time picker
        var cb = function(start, end, label) {
            $('#scheduledEndTime span').html(start.format('YYYY-MM-DD HH:mm:ss'));
        };
        var optionSet1 = {
            startDate: moment().subtract(29, 'days'),
            showDropdowns: false,
            showWeekNumbers: false,
            timePicker: true,
            timePickerIncrement: 1,
            singleDatePicker: true,
            timePicker24Hour: true,
            locale: {
                format: 'YYYY-MM-DD HH:mm:ss',
                daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
                monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            },
            opens: 'left',
            buttonClasses: ['btn btn-default'],
            applyClass: 'btn-small btn-primary',
            cancelClass: 'btn-small',
            format: 'YYYY-MM-DD HH:mm:ss',

        };
        $('#scheduledEndTime span').html(moment().subtract(29, 'days').format('YYYY-MM-DD HH:mm:ss'));
        $('#reservationtime').daterangepicker(optionSet1, cb);
        $('#reservationtime1').daterangepicker(optionSet1, cb);
        $("#select_product").change(function () {
            $("#product_id_input").val($(this).val());
        });
        $("#select_pay_user").change(function () {
            $("#pay_username_input").val($(this).val());
        })
    </script>
</body>
</html>
