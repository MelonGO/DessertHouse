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
<jsp:useBean id="waiter_planbean" type="edu.nju.desserthouse.action.business.PlanBean"
 			scope="session"></jsp:useBean>
<jsp:useBean id="plan" class="edu.nju.desserthouse.model.Plan"
			scope="page"></jsp:useBean>
<jsp:useBean id="waiter_orderbean" type="edu.nju.desserthouse.action.business.OrderBean"
 			scope="session"></jsp:useBean>
<jsp:useBean id="order" class="edu.nju.desserthouse.model.Order"
			scope="page"></jsp:useBean>
<jsp:useBean id="waiter_userlist" type="edu.nju.desserthouse.action.business.UserBean"
 			scope="session"></jsp:useBean>
<jsp:useBean id="user" class="edu.nju.desserthouse.model.User"
			scope="page"></jsp:useBean>
<jsp:useBean id="waiter_housebean" type="edu.nju.desserthouse.action.business.HouseBean"
 			scope="session"></jsp:useBean>
<jsp:useBean id="house" class="edu.nju.desserthouse.model.House"
			scope="page"></jsp:useBean>
<%
 	pageContext.setAttribute("waiter", waiter_waiterbean.getWaiter());
 	pageContext.setAttribute("order", waiter_orderbean.getOrder());
 	pageContext.setAttribute("house", waiter_housebean.getHouse());
%>
<div class="container bg">
    <div class="row">
        <div class="col-md-6">
            <h1 class="font-big font-white">
                服务员: <jsp:getProperty name="waiter" property="waitername"/></br>
                <small>所属店铺: <jsp:getProperty name="house" property="housename"/>
                	(<jsp:getProperty name="house" property="main"/>)</small>
            </h1>
        </div>
        <div class="col-md-6">
        	<div class="col-md-12 pay-bg center-block">
            	<form action="<%=request.getContextPath() + "/Payment"%>" method="post" onsubmit="return(reserve_click())">
            		<input type="text" style="display:none" name="pay" value="order">
            		<textarea rows="3" name="content" style="margin-top: 10px" readonly="readonly" id="content"
            				class="form-control" ><jsp:getProperty name="order" property="content"/></textarea>
                	<input class="login" id="totalprice" type="text" name="totalprice" readonly="readonly"
                			value="<jsp:getProperty name="order" property="price"/>">
                	<div class="form-group">
                    <select class="form-control" name="userid">
                    	<option value="-1" id="notvip">非会员</option>
                    	<%
    						for (int i = 0; i < waiter_userlist.getListuser().size(); i++) {
    						pageContext.setAttribute("user", waiter_userlist.getListuser().get(i));
    					%>
                        <option value="<jsp:getProperty name="user" property="userid"/>">
                        	<jsp:getProperty name="user" property="username"/>
                        </option>
                        <%
                			}
						%>
                    </select>
                </div>
                	<button class="btn btn-success btn-block" style="margin-bottom: 10px">
                    	<span class="fa fa-dollar fa-2x">销售</span>
                	</button>
            	</form>
        	</div>
    	</div>
    </div>
    <div class="row">
    	<ul class="nav nav-tabs">
   			<li><a href="<%=request.getContextPath() + "/Sell?day=Monday"%>">MON</a></li>
   			<li><a href="<%=request.getContextPath() + "/Sell?day=Tuesday"%>">TUE</a></li>
   			<li><a href="<%=request.getContextPath() + "/Sell?day=Wednesday"%>">WED</a></li>
   			<li><a href="<%=request.getContextPath() + "/Sell?day=Thursday"%>">THU</a></li>
   			<li><a href="<%=request.getContextPath() + "/Sell?day=Friday"%>">FRI</a></li>
   			<li><a href="<%=request.getContextPath() + "/Sell?day=Saturday"%>">SAT</a></li>
   			<li><a href="<%=request.getContextPath() + "/Sell?day=Sunday"%>">SUN</a></li>
		</ul>
        <div class="col-md-12">
            <h2>甜品出售列表</h2>
            <table class="table waiter-list">
                <thead>
                <tr>
                	<th>图片</th>
                    <th>甜品名字</th>
                    <th>单价(元)</th>
                    <th>数量</th>
                </tr>
                </thead>
                <tbody>
                <%
    				for (int i = 0; i < waiter_planbean.getPlanlist().size(); i++) {
    					pageContext.setAttribute("plan",  waiter_planbean.getPlanlist().get(i));
    			%>
                <tr>
                	<td class="dessert_pic"><img src="/DessertHouseWeb/img/dessert_<jsp:getProperty name="plan" property="dessertid"/>.jpg" class="img-responsive" 
                		alt="pic" width="160" height="120"></td>
                    <td><jsp:getProperty name="plan" property="dessertname"/></td>
                    <td><jsp:getProperty name="plan" property="dessertprice"/></td>
                    <td><jsp:getProperty name="plan" property="amount"/>
                    	<button onclick="addContent(<jsp:getProperty name="plan" property="dessertid"/>)" 
                    		class="btn btn-warning pull-right fa fa-plus"></button>
                    	<button onclick="reduceContent(<jsp:getProperty name="plan" property="dessertid"/>)" 
                    		class="btn btn-warning pull-right fa fa-minus"></button>
                    </td>
                </tr>
                <%
                	}
				%>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script>

    function addContent(dessertid){
    	var postUrl = "<%=request.getContextPath() + "/Sell"%>";
        var postData = "add";
        var postData2 = dessertid;
        var ExportForm = document.createElement("FORM");
        document.body.appendChild(ExportForm);
        ExportForm.method = "POST";
        var newElement = document.createElement("input");  
        newElement.setAttribute("name", "sell");
        newElement.setAttribute("type", "hidden");
        var newElement2 = document.createElement("input"); 
        newElement2.setAttribute("name", "dessertId"); 
        newElement2.setAttribute("type", "hidden");
        ExportForm.appendChild(newElement);
        ExportForm.appendChild(newElement2);
        newElement.value = postData;
        newElement2.value = postData2;
        ExportForm.action = postUrl;
        ExportForm.submit();
    }
    
    function reduceContent(dessertid){
    	var postUrl = "<%=request.getContextPath() + "/Sell"%>";
        var postData = "reduce";
        var postData2 = dessertid;
        var ExportForm = document.createElement("FORM");
        document.body.appendChild(ExportForm);
        ExportForm.method = "POST";
        var newElement = document.createElement("input");  
        newElement.setAttribute("name", "sell");
        newElement.setAttribute("type", "hidden");
        var newElement2 = document.createElement("input"); 
        newElement2.setAttribute("name", "dessertId"); 
        newElement2.setAttribute("type", "hidden");
        ExportForm.appendChild(newElement);
        ExportForm.appendChild(newElement2);
        newElement.value = postData;
        newElement2.value = postData2;
        ExportForm.action = postUrl;
        ExportForm.submit();
    }
    
</script>

<script>
    function reserve_click() {
        var msg = "";
        if ($("#content").val().length == 0) {
            msg = "请选择要预定的甜品!";
            alert(msg);
            return false;
        }
    }
</script>

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