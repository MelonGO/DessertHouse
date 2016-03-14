<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Waiter Management</title>
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
        	<li class="active"><a href="<%=request.getContextPath() + "/waiter.userinfo"%>">会员信息</a></li>
            <li><a href="<%=request.getContextPath() + "/Sell"%>">销售</a></li>
            <li><a href="<%=request.getContextPath() + "/ReserveManage"%>">会员订单</a></li>
            <li><a href="<%=request.getContextPath() + "/Record"%>">记录</a></li>
            <li><a href="<%=request.getContextPath() + "/Plan"%>">产品计划</a></li>
        </ul>
        <div>
            <ul class="nav navbar-nav navbar-right navbar-right-padding">
                <li class="dropdown">
                    <a href="" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-user fa-2x"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="<%=request.getContextPath() + "/index.html"%>">登出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<jsp:useBean id="waiter_waiterbean" type="edu.nju.desserthouse.action.business.WaiterBean"
 			scope="session"></jsp:useBean>
<jsp:useBean id="waiter" class="edu.nju.desserthouse.model.Waiter"
			scope="page"></jsp:useBean>
<jsp:useBean id="waiter_orderbean" type="edu.nju.desserthouse.action.business.OrderBean"
 			scope="session"></jsp:useBean>
<jsp:useBean id="order" class="edu.nju.desserthouse.model.Order"
			scope="page"></jsp:useBean>
<jsp:useBean id="waiter_userbean" type="edu.nju.desserthouse.action.business.UserBean"
 			scope="session"></jsp:useBean>
<jsp:useBean id="user" class="edu.nju.desserthouse.model.User"
			scope="page"></jsp:useBean>
<jsp:useBean id="waiter_housebean" type="edu.nju.desserthouse.action.business.HouseBean"
 			scope="session"></jsp:useBean>
<jsp:useBean id="house" class="edu.nju.desserthouse.model.House"
			scope="page"></jsp:useBean>
<%
 	pageContext.setAttribute("waiter", waiter_waiterbean.getWaiter());
 	pageContext.setAttribute("user", waiter_userbean.getUser());
 	pageContext.setAttribute("house", waiter_housebean.getHouse());
%>
<div class="container bg">
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12">
            <h1 class="font-big font-white">
                服务员: <jsp:getProperty name="waiter" property="waitername"/></br>
                <small>所属店铺: <jsp:getProperty name="house" property="housename"/>
                	(<jsp:getProperty name="house" property="main"/>)</small>
            </h1>
        </div>
        <div class="col-lg-12 col-md-12 col-sm-12">
            <form method="post" action="<%=request.getContextPath() + "/Record"%>" 
            		class="navbar-form" role="search">
                <div class="form-group">
                    <select class="form-control" name="userid" id="select">
                    	<option value="-1">请选择会员</option>
                    	<%
    						for (int i = 0; i < waiter_userbean.getListuser().size(); i++) {
    						pageContext.setAttribute("user", waiter_userbean.getListuser().get(i));
    					%>
    					<option value="<jsp:getProperty name="user" property="userid"/>">
                        	<jsp:getProperty name="user" property="username"/>
                        </option>
                        <%
                			}
						%>
                    </select>
                </div>
                <button type="submit" class="btn btn-info fa fa-search fa-2x"></button>
            </form>
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
    				for (int i = 0; i < waiter_orderbean.getOrderlist().size(); i++) {
    					pageContext.setAttribute("order", waiter_orderbean.getOrderlist().get(i));
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