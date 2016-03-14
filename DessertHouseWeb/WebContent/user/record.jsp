<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Management</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<%=request.getContextPath() + "/bootstrap-3.3.5-dist/css/bootstrap.min.css"%>" rel="stylesheet">
    <link href="<%=request.getContextPath() + "/css/mystyle.css"%>" rel="stylesheet">
    <link href="<%=request.getContextPath() + "/css/queries.css"%>" rel="stylesheet">
    <link href="<%=request.getContextPath() + "/font-awesome-4.3.0/css/font-awesome.min.css"%>" rel="stylesheet">
    <script src="<%=request.getContextPath() + "/js/jquery.min.js"%>"></script>
    <script src="<%=request.getContextPath() + "/bootstrap-3.3.5-dist/js/bootstrap.min.js"%>"></script>
    <script src="<%=request.getContextPath() + "/js/myscript.js"%>"></script>

</head>
<body class="main">
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse"
                data-target="#example-navbar-collapse">
            <span class="sr-only">切换导航</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="">Dessert House</a>
    </div>
    <div class="collapse navbar-collapse" id="example-navbar-collapse">
        <ul class="nav navbar-nav">
            <li class="active"><a href="">今日特惠</a></li>
            <li><a href="<%=request.getContextPath() + "/Reserve"%>">预定</a></li>
            <li class="dropdown">
                <a href="#"
                   class="dropdown-toggle"
                   data-toggle="dropdown"> 活动 <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="">买一送一</a></li>
                </ul>
            </li>
        </ul>
        <div>
            <ul class="nav navbar-nav navbar-right navbar-right-padding">
                <li class="dropdown">
                    <a href="" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-user fa-2x"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="<%=request.getContextPath() + "/UserInfo"%>">信息</a></li>
                        <li><a href="<%=request.getContextPath() + "/UserRecord"%>">消费记录</a></li>
                        <li><a href="<%=request.getContextPath() + "/index.html"%>">登出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<jsp:useBean id="user_orderbean" type="edu.nju.desserthouse.action.business.OrderBean"
 			scope="session"></jsp:useBean>
<jsp:useBean id="order" class="edu.nju.desserthouse.model.Order"
			scope="page"></jsp:useBean>
<jsp:useBean id="user_userinfo" type="edu.nju.desserthouse.action.business.UserBean"
 			scope="session"></jsp:useBean>
<jsp:useBean id="user" class="edu.nju.desserthouse.model.User"
			scope="page"></jsp:useBean>
<%
 	pageContext.setAttribute("user", user_userinfo.getUser());
%>
<div class="container bg">
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12">
            <h1 class="font-big font-white">
                会员: <jsp:getProperty name="user" property="username"/></br>
                <small>消费记录</small>
            </h1>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <h2>消费记录</h2>
            <table class="table waiter-list">
                <thead>
                <tr>
                    <th>订单ID</th>
                    <th>店名</th>
                    <th>会员姓名</th>
                    <th>内容</th>
                    <th>总价</th>
                    <th>状态</th>
                    <th>创建时间</th>
                </tr>
                </thead>
                <tbody>
                <%
    				for (int i = 0; i < user_orderbean.getOrderlist().size(); i++) {
    					pageContext.setAttribute("order", user_orderbean.getOrderlist().get(i));
    			%>
                <tr>
                    <td><jsp:getProperty name="order" property="orderid"/></td>
                    <td><jsp:getProperty name="order" property="housename"/></td>
                    <td><jsp:getProperty name="order" property="username"/></td>
                    <td><jsp:getProperty name="order" property="content"/></td>
                    <td><jsp:getProperty name="order" property="price"/></td>
                    <td><jsp:getProperty name="order" property="status"/></td>
                    <td><jsp:getProperty name="order" property="ordertime"/></td>
                </tr>
                <%
                	}
				%>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div>
	<img src="/DessertHouseWeb/img/logo.jpg" class="img-responsive img-circle center-block" 
		style="opacity: 0.8"> 
</div>

<footer>
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <ul class="legals">
                    <li><a href="#">More Information</a></li>
                    <li><a href="#">MelonGO</a></li>
                </ul>
            </div>
            <div class="col-md-6 credit">
                <p>&copy;Copyright 2015 - melon</p>
            </div>
        </div>
    </div>
</footer>

</body>
</html>