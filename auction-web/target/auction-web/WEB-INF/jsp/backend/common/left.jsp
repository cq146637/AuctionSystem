<%@ page import="org.sczs.auction.domain.ErpUser" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2018/9/10
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ErpUser erpUser = (ErpUser) session.getAttribute("erpUser");%>
<html>
<head>
    <title>导航条</title>
</head>
<body>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="../assets/libs/AdminLTE-2.4.5/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p><%=erpUser.getUserName()%></p>
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>
        <!-- search form -->
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Search...">
                <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
            </div>
        </form>
        <!-- /.search form -->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header">主导航</li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-dashboard"></i> <span>大盘统计</span>
                    <span class="pull-right-container">
                      <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/erp/main.html"><i class="fa fa-circle-o"></i> 网站访问统计</a></li>
                    <li><a href="/erp/main.html"><i class="fa fa-circle-o"></i> 当前拍卖统计</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-files-o"></i>
                    <span>拍卖管理</span>
                    <span class="pull-right-container">
                      <span class="label label-primary pull-right">2</span>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/erp/QueryAllAuction.html"><i class="fa fa-circle-o"></i> 当前拍卖信息管理</a></li>
                    <li><a href="/erp/QueryAllAuction.html"><i class="fa fa-circle-o"></i> 历史拍卖信息管理</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-table"></i> <span>分类管理</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/erp/ShowCategorys.html"><i class="fa fa-circle-o"></i> 分类信息管理</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-pie-chart"></i> <span>商品管理</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/erp/ShowProducts.html"><i class="fa fa-circle-o"></i> 商品信息管理</a></li>
                    <li><a href="/erp/ShowProductImg.html"><i class="fa fa-circle-o"></i> 图片信息管理</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-laptop"></i> <span>评价管理</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/erp/ShowRemarks.html"><i class="fa fa-circle-o"></i> 评价信息管理</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-edit"></i> <span>订单管理</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/erp/ShowOrderInfo.html"><i class="fa fa-circle-o"></i> 订单信息管理</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-user"></i> <span>用户管理</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/erp/ShowPayUsers.html"><i class="fa fa-circle-o"></i> 竞拍者信息查询</a></li>
                    <li><a href="/erp/ShowSaleUsers.html"><i class="fa fa-circle-o"></i> 拍卖者信息查询</a></li>
                    <li><a href="/erp/ShowErpUsers.html"><i class="fa fa-circle-o"></i> ERP信息查询</a></li>
                </ul>
            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
</body>
</html>
