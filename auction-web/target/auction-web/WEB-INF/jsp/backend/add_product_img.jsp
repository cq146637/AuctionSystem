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
    <link rel="stylesheet" href="../assets/libs/AdminLTE-2.4.5/bower_components/bootstrap-fileinput/css/fileinput.min.css ">
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
                    商品管理
                    <small>图片信息管理</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                    <li class="active">商品管理</li>
                </ol>
            </section>

            <!-- Main content -->
            <section class="content">
                <!-- Main row -->
                <div class="row">
                    <div class="col-xs-12">
                        <!-- general form elements -->
                        <div class="box box-primary">
                            <div class=" container kv-main">
                            <div class="box-header with-border">
                                <h3 class="box-title">添加商品图片</h3>
                            </div>
                            <!-- /.box-header -->
                            <!-- form start -->
                                <form role="form" action="/erp/AddProductImg.html" method="post" enctype="multipart/form-data">
                                <div class="box-body">
                                    <div class="form-group" style="display:none;">
                                        <label for="exampleInputText0">商品ID</label>
                                        <input type="text" class="form-control" id="exampleInputText0" name="productId" placeholder="商品ID" value="${productList[0].id}">
                                    </div>
                                    <div class="form-group">
                                        <label>商品名称</label>
                                        <select id="select_product" class="form-control select2 select2-hidden-accessible" style="width: 100%;" tabindex="-1" aria-hidden="true" name="productName">
                                            <c:forEach var="product" items="${productList}" varStatus="status" >
                                                <c:if test="${status.index == 0}">
                                                    <option selected="selected" value="${product.id}">${product.productName}</option>
                                                </c:if>
                                                <c:if test="${status.index != 0}">
                                                    <option value="${product.id}">${product.productName}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>展示图</label>
                                        <input id="showPictureFile" type="file" name="showPictureFile"
                                               class="file-loading" value="" /> <input type="hidden" id="showPictureFileUUID"
                                                                                           name="url" value="">
                                    </div>
                                    <div class="form-group">
                                        <label>细节图</label>
                                        <input id="detailPictureFile" type="file" name="showPictureFile"
                                               multiple class="file-loading" value="" /> <input id="detailPictureFileUUID" type="hidden"
                                                                                       name="urlList" value="">
                                    </div>
                                </div>
                                <!-- /.box-body -->
                                <div class="box-footer">
                                    <button type="submit" class="btn btn-primary">提交</button>
                                    <button type="button" class="btn btn-default" onclick="history.go(-1)">返回</button>
                                </div>
                            </form>
                            </div>
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
    <!-- bootstrap-fileinput -->
    <script src="../assets/libs/AdminLTE-2.4.5/bower_components/bootstrap-fileinput/js/fileinput.min.js "></script>
    <script src="../assets/libs/AdminLTE-2.4.5/bower_components/bootstrap-fileinput/js/locales/zh.js "></script>
    <!-- FastClick -->
    <script src="../assets/libs/AdminLTE-2.4.5/bower_components/fastclick/lib/fastclick.js"></script>
    <!-- AdminLTE App -->
    <script src="../assets/libs/AdminLTE-2.4.5/dist/js/adminlte.min.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="../assets/libs/AdminLTE-2.4.5/dist/js/demo.js"></script>
    <script>
        $("#select_product").change(function () {
            $("#exampleInputText0").val($(this).val());
        });

        $("#detailPictureFile").fileinput({
            //上传的地址
            uploadUrl:"/erp/StoreProductImg.html",
            uploadAsync : true, //默认异步上传
            showUpload : false, //是否显示上传按钮,跟随文本框的那个
            showRemove : false, //显示移除按钮,跟随文本框的那个
            showCaption : true,//是否显示标题,就是那个文本框
            showPreview : true, //是否显示预览,不写默认为true
            dropZoneEnabled : false,//是否显示拖拽区域，默认不写为true，但是会占用很大区域
            browseClass: "btn btn-info", //按钮样式
            //minImageWidth: 50, //图片的最小宽度
            //minImageHeight: 50,//图片的最小高度
            //maxImageWidth: 1000,//图片的最大宽度
            //maxImageHeight: 1000,//图片的最大高度
            //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
            //minFileCount: 0,
            maxFileCount : 4, //表示允许同时上传的最大文件个数
            enctype : 'multipart/form-data',
            validateInitialCount : true,
            previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany : "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
            allowedFileTypes : [ 'image' ],//配置允许文件上传的类型
            allowedPreviewTypes : [ 'image' ],//配置所有的被预览文件类型
            allowedPreviewMimeTypes : [ 'jpg', 'png', 'gif' ],//控制被预览的所有mime类型
            language : 'zh'
        });
        //异步上传返回结果处理
        $("#detailPictureFile").on("fileuploaded", function(event, data, previewId, index) {
            var ref = $(this).attr("data-ref");
            console.log(data.response.message);
            $("#detailPictureFileUUID").val($("#detailPictureFileUUID").val() + "," + data.response.message);
        });

        $("#showPictureFile").fileinput({
            //上传的地址
            uploadUrl:"/erp/StoreProductImg.html",
            uploadAsync : true, //默认异步上传
            showUpload : false, //是否显示上传按钮,跟随文本框的那个
            showRemove : false, //显示移除按钮,跟随文本框的那个
            showCaption : true,//是否显示标题,就是那个文本框
            showPreview : true, //是否显示预览,不写默认为true
            dropZoneEnabled : false,//是否显示拖拽区域，默认不写为true，但是会占用很大区域
            browseClass: "btn btn-info", //按钮样式
            //minImageWidth: 50, //图片的最小宽度
            //minImageHeight: 50,//图片的最小高度
            //maxImageWidth: 1000,//图片的最大宽度
            //maxImageHeight: 1000,//图片的最大高度
            //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
            //minFileCount: 0,
            maxFileCount : 1, //表示允许同时上传的最大文件个数
            enctype : 'multipart/form-data',
            validateInitialCount : true,
            previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany : "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
            allowedFileTypes : [ 'image' ],//配置允许文件上传的类型
            allowedPreviewTypes : [ 'image' ],//配置所有的被预览文件类型
            allowedPreviewMimeTypes : [ 'jpg', 'png', 'gif' ],//控制被预览的所有mime类型
            language : 'zh'
        });
        //异步上传返回结果处理
        $("#showPictureFile").on("fileuploaded", function(event, data, previewId, index) {
            var ref = $(this).attr("data-ref");
            $("#showPictureFileUUID").val(data.response.message);

        });
    </script>
</body>
</html>
