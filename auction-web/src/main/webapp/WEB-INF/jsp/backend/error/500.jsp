<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2018/9/10
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../common/base.jsp" />
</head>
<body>
    <div class="wrapper">
        <jsp:include page="../common/header.jsp" />
        <jsp:include page="../common/left.jsp" />
        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper">
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <h1>
                    500 Error Page
                </h1>
                <ol class="breadcrumb">
                    <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                    <li><a href="#">Examples</a></li>
                    <li class="active">500 error</li>
                </ol>
            </section>

            <!-- Main content -->
            <section class="content">

                <div class="error-page">
                    <h2 class="headline text-red">500</h2>

                    <div class="error-content">
                        <h3><i class="fa fa-warning text-red"></i> Oops! Something went wrong.</h3>

                        <p>
                            We will work on fixing that right away.
                            Meanwhile, you may <a href="../../index.html">return to dashboard</a> or try using the search form.
                        </p>

                        <form class="search-form">
                            <div class="input-group">
                                <input type="text" name="search" class="form-control" placeholder="Search">

                                <div class="input-group-btn">
                                    <button type="submit" name="submit" class="btn btn-danger btn-flat"><i class="fa fa-search"></i>
                                    </button>
                                </div>
                            </div>
                            <!-- /.input-group -->
                        </form>
                    </div>
                </div>
                <!-- /.error-page -->

            </section>
            <!-- /.content -->
        </div>
        <!-- /.content-wrapper -->
        <jsp:include page="../common/footer.jsp" />
        <jsp:include page="../common/sidebar.jsp" />
        <jsp:include page="../common/bottom.jsp" />
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
    </div>
</body>
</html>
