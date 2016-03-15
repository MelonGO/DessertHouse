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
    <script src="<%=request.getContextPath() + "/Chart.js-master/Chart.js"%>"></script>

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
<jsp:useBean id="manager_userbean" type="edu.nju.desserthouse.action.business.UserBean"
 			scope="session"></jsp:useBean>
<jsp:useBean id="user" class="edu.nju.desserthouse.model.User"
			scope="page"></jsp:useBean>
<jsp:useBean id="manager_housebean" type="edu.nju.desserthouse.action.business.HouseBean"
 			scope="session"></jsp:useBean>
<jsp:useBean id="manager_orderbean" type="edu.nju.desserthouse.action.business.OrderBean"
 			scope="session"></jsp:useBean>
<jsp:useBean id="order" class="edu.nju.desserthouse.model.Order"
			scope="page"></jsp:useBean>
<%
	pageContext.setAttribute("manager", manager_managerbean.getManager());
 	String[] ageData = (String[]) session.getAttribute("ageData");
 	int[] addressData= (int[]) session.getAttribute("addressData");
 	String[] genderData = (String[]) session.getAttribute("genderData");
 	List<Map.Entry<String, Integer>> dessertRank = (List<Map.Entry<String, Integer>>) session.getAttribute("dessertRank");
%>

<div class="container bg">
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12">
            <h1 class="font-big font-white">
                经理: <jsp:getProperty name="manager" property="managername"/></br>
                <small>统计分析</small>
            </h1>
        </div>
    </div>
    <div class="row">
    	<div class="col-md-6">
    		<h3>性别</h3>
            <h4 style="background-color: green">男性:</h4>
            <h4 style="background-color: pink">女性:</h4>
            <canvas id="myChart_1" width="300" height="300"></canvas>
    	</div>
    	<div class="col-md-6">
        	<h3>地区分布</h3>
    		<canvas id="myChart_2" width="300" height="300"></canvas>
    	</div>
    </div>
    <div class="row">
    	<div class="col-md-7">
    		<h3>各年龄段百分比</h3>
            <p style="background-color: #F38630">0-15岁</p>
            <p style="background-color: #30E4CC">16-30岁</p>
            <p style="background-color: #F0EF7C">31-60</p>
            <p style="background-color: #69D2E7">60以上</p>
    	</div>
    	<div class="col-md-5">
        	<canvas id="myChart" width="300" height="300"></canvas>
    	</div>
    </div>
    <div class="row">
    	<div class="col-md-12">
    		<h1>热卖排行：</h1>
    		<table class="table waiter-list">
    			<thead>
                <tr>
                    <th>甜品		卖出数量</th>
                </tr>
                </thead>
                <tbody>
    			<%
    				for(int i=0;i<dessertRank.size();i++){
    					String str = dessertRank.get(i).toString();
    			%>
    			<tr>
    				<td><%=str %></td>
    			</tr>
    			<%
    				}
    			%>
    			</tbody>
    		</table>
    	</div>
    </div>
    
    <div class="row">
        <div class="col-md-12">
            <h2>会员列表</h2>
            <table class="table waiter-list">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>会员姓名</th>
                    <th>性别</th>
                    <th>年龄</th>
                    <th>卡号</th>
                    <th>状态</th>
                    <th>积分</th>
                    <th>等级</th>
                    <th>地区</th>
                </tr>
                </thead>
                <tbody>
                <%
    				for (int i = 0; i < manager_userbean.getListuser().size(); i++) {
    					pageContext.setAttribute("user",manager_userbean.getListuser().get(i));
    			%>
                <tr>
                    <td><jsp:getProperty name="user" property="userid"/></td>
                    <td><jsp:getProperty name="user" property="username"/></td>
                    <td><jsp:getProperty name="user" property="gender"/></td>
                    <td><jsp:getProperty name="user" property="age"/></td>
                    <td><jsp:getProperty name="user" property="cardid"/></td>
                    <td><jsp:getProperty name="user" property="status"/></td>
                    <td><jsp:getProperty name="user" property="reward"/></td>
                    <td><jsp:getProperty name="user" property="level"/></td>
                    <td><jsp:getProperty name="user" property="address"/></td>
                </tr>
                <%
                	}
				%>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <h1>预定销售情况</h1>
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
    				for (int i = 0; i < manager_orderbean.getOrderlist().size(); i++) {
    					pageContext.setAttribute("order", manager_orderbean.getOrderlist().get(i));
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

<script>
var data = [
        	{
        		value: <%=ageData[0]%>,
        		color:"#F38630"
        	},
        	{
        		value : <%=ageData[1]%>,
        		color : "#30E4CC"
        	},
        	{
        		value : <%=ageData[2]%>,
        		color : "#F0EF7C"
        	},
        	{
        		value : <%=ageData[3]%>,
        		color : "#69D2E7"
        	}			
        ]

var ctx = document.getElementById("myChart").getContext("2d");
var myNewChart = new Chart(ctx).Pie(data);
</script>

<script>

var data_1 = [
          	{
          		value: <%=genderData[0]%>,
          		color:"green"
          	},
          	{
          		value : <%=genderData[1]%>,
          		color : "pink"
          	}
          ]
var ctx_1 = document.getElementById("myChart_1").getContext("2d");
var myNewChart_1 = new Chart(ctx_1).Doughnut(data_1);

</script>

<script>

var data_2 = {
          	labels : ["Asia","America","Europe","Africa","Oceania"],
			datasets : [
				{
					fillColor : "rgba(151,187,205,0.5)",
					strokeColor : "rgba(220,220,220,1)",
					data : [<%=addressData[0]%>,<%=addressData[1]%>,<%=addressData[2]%>,<%=addressData[3]%>,<%=addressData[4]%>]
				}
			]
}
var ctx_2 = document.getElementById("myChart_2").getContext("2d");
var myNewChart_2 = new Chart(ctx_2).Bar(data_2);

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