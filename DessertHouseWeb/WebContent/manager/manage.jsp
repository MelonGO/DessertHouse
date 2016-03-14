<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manager Management</title>
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
        	<li class="active"><a href="<%=request.getContextPath() + "/ApproveInfo"%>">计划审批</a></li>
            <li><a href="<%=request.getContextPath() + "/Statistics"%>">统计分析</a></li>
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

<jsp:useBean id="manager_planbean" type="edu.nju.desserthouse.action.business.PlanBean"
 			scope="session"></jsp:useBean>
<jsp:useBean id="plan" class="edu.nju.desserthouse.model.Plan"
			scope="page"></jsp:useBean>
<jsp:useBean id="manager_managerbean" type="edu.nju.desserthouse.action.business.ManagerBean"
 			scope="session"></jsp:useBean>
<jsp:useBean id="manager" class="edu.nju.desserthouse.model.Manager"
			scope="page"></jsp:useBean>
<%
	pageContext.setAttribute("manager", manager_managerbean.getManager());
%>
<div class="container bg">
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12">
            <h1 class="font-big font-white">
                经理: <jsp:getProperty name="manager" property="managername"/>
            </h1>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <h2>计划审批</h2>
            <table class="table waiter-list">
                <thead>
                <tr>
                    <th>计划ID</th>
                    <th>店员</th>
                    <th>店铺</th>
                    <th>甜品名字</th>
                    <th>甜品价格</th>
                    <th>甜品数量</th>
                    <th>出售时间</th>
                    <th>状态</th>
                    <th>创建时间</th>
                </tr>
                </thead>
                <tbody>
                <%
    				for (int i = 0; i < manager_planbean.getPlanlist().size(); i++) {
    					pageContext.setAttribute("plan", manager_planbean.getPlanlist().get(i));
    			%>
                <tr>
                    <td><jsp:getProperty name="plan" property="planid"/></td>
                    <td><jsp:getProperty name="plan" property="waitername"/></td>
                    <td><jsp:getProperty name="plan" property="housename"/></td>
                    <td><jsp:getProperty name="plan" property="dessertname"/></td>
                    <td><jsp:getProperty name="plan" property="dessertprice"/></td>
                    <td><jsp:getProperty name="plan" property="amount"/></td>
                    <td><jsp:getProperty name="plan" property="day"/></td>
                    <td><jsp:getProperty name="plan" property="status"/></td>
                    <td><jsp:getProperty name="plan" property="plantime"/>
                        <button class='btn btn-primary fa fa-check center-block pull-right' 
                        		onclick='click_pass(<jsp:getProperty name="plan" property="planid"/>)'></button>
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

<div>
	<img src="/DessertHouseWeb/img/logo.jpg" class="img-responsive img-circle center-block" 
		style="opacity: 0.8"> 
</div>

<script>
    function click_pass(ID){
    	var postUrl = "<%=request.getContextPath() + "/Approve"%>";
        var postData2 = ID;
        var ExportForm = document.createElement("FORM");
        document.body.appendChild(ExportForm);
        ExportForm.method = "POST";
        var newElement2 = document.createElement("input"); 
        newElement2.setAttribute("name", "planId"); 
        newElement2.setAttribute("type", "hidden");
        ExportForm.appendChild(newElement2);
        newElement2.value = postData2;
        ExportForm.action = postUrl;
        ExportForm.submit();
    }
</script>

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