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
    <link rel="stylesheet" href="<%=request.getContextPath() + "/animate.css-master/animate.min.css"%>">
    
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
            <li class="active"><a href="#">今日特惠</a></li>
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
<jsp:useBean id="user_planbean" type="edu.nju.desserthouse.action.business.PlanBean"
 			scope="session"></jsp:useBean>
<jsp:useBean id="plan" class="edu.nju.desserthouse.model.Plan"
			scope="page"></jsp:useBean>
<jsp:useBean id="user_housebean"
			type="edu.nju.desserthouse.action.business.HouseBean"
			scope="session"></jsp:useBean>
<jsp:useBean id="house" class="edu.nju.desserthouse.model.House"
			scope="page"></jsp:useBean>
<%
 	pageContext.setAttribute("user", user_userinfo.getUser());
	pageContext.setAttribute("order", user_orderbean.getOrder());
%>

<div class="container bg">
    <div class="row">
        <div class="col-md-6">
            <h1 class="font-big font-white">
                会员: <jsp:getProperty name="user" property="username"/></br>
                <small>预定商品</small>
            </h1>
        </div>
        <div class="col-md-6">
        	<div class="col-md-12 pay-bg center-block">
            	<form action="<%=request.getContextPath() + "/Reserve"%>" method="post" onsubmit="return(reserve_click())">
            		<input type="text" style="display:none" name="reserve" value="addOrder">
            		<textarea rows="3" name="content" style="margin-top: 10px" readonly="readonly" id="content"
            				class="form-control" ><jsp:getProperty name="order" property="content"/></textarea>
                	<input class="login" id="totalprice" type="text" name="totalprice" readonly="readonly"
                			value="<jsp:getProperty name="order" property="price"/>">
                	<input type="text" style="display:none" name="userid" 
                			value="<jsp:getProperty name="user" property="userid"/>">
                	<button class="btn btn-success btn-block" style="margin-bottom: 10px">
                    	<span class="fa fa-dollar fa-2x">预定</span>
                	</button>
            	</form>
        	</div>
    	</div>
    </div>
    <div class="row">
    	<div class="col-lg-12 col-md-12 col-sm-12">
            <form method="post" action="<%=request.getContextPath() + "/SelectHouse"%>" 
            		class="navbar-form" role="search">
                <div class="form-group">
                    <select class="form-control" name="houseid" id="select">
                    	<option value="-1">请选择店铺</option>
                    	<%
    						for (int i = 0; i < user_housebean.getHouselist().size(); i++) {
    						pageContext.setAttribute("house", user_housebean.getHouselist().get(i));
    					%>
    					<option value="<jsp:getProperty name="house" property="houseid"/>">
                        	<jsp:getProperty name="house" property="housename"/>
                        </option>
                        <%
                			}
						%>
                    </select>
                </div>
                <button type="submit" class="btn btn-info fa fa-search fa-2x"></button>
            </form>
        </div>
        <ul class="nav nav-tabs">
   			<li><a href="<%=request.getContextPath() + "/Reserve?day=Monday"%>">MON</a></li>
   			<li><a href="<%=request.getContextPath() + "/Reserve?day=Tuesday"%>">TUE</a></li>
   			<li><a href="<%=request.getContextPath() + "/Reserve?day=Wednesday"%>">WED</a></li>
   			<li><a href="<%=request.getContextPath() + "/Reserve?day=Thursday"%>">THU</a></li>
   			<li><a href="<%=request.getContextPath() + "/Reserve?day=Friday"%>">FRI</a></li>
   			<li><a href="<%=request.getContextPath() + "/Reserve?day=Saturday"%>">SAT</a></li>
   			<li><a href="<%=request.getContextPath() + "/Reserve?day=Sunday"%>">SUN</a></li>
		</ul>
        <div class="col-md-12">
            <h2>甜品出售列表</h2>
            <table class="table waiter-list">
                <thead>
                <tr>
                	<th>图片</th>
                    <th>店名</th>
                    <th>甜品名字</th>
                    <th>单价(元)</th>
                    <th>数量</th>
                </tr>
                </thead>
                <tbody>
                <%
    				for (int i = 0; i < user_planbean.getPlanlist().size(); i++) {
    					pageContext.setAttribute("plan",  user_planbean.getPlanlist().get(i));
    			%>
                <tr>
                	<td class="dessert_pic"><img src="/DessertHouseWeb/img/dessert_<jsp:getProperty name="plan" property="dessertid"/>.jpg" class="img-responsive" 
                		alt="pic" width="160" height="120"></td>
                    <td><jsp:getProperty name="plan" property="housename"/></td>
                    <td><jsp:getProperty name="plan" property="dessertname"/></td>
                    <td><jsp:getProperty name="plan" property="dessertprice"/></td>
                    <td><jsp:getProperty name="plan" property="amount"/>
                    	<button onclick="addContent(<jsp:getProperty name="plan" property="dessertid"/>,
                    					<jsp:getProperty name="plan" property="houseid"/>)" 
                    		class="btn btn-warning pull-right fa fa-plus"></button>
                    	<button onclick="reduceContent(<jsp:getProperty name="plan" property="dessertid"/>,
                    					<jsp:getProperty name="plan" property="houseid"/>)" 
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

<div>
	<img src="/DessertHouseWeb/img/logo.jpg" class="img-responsive img-circle center-block" 
		style="opacity: 0.8"> 
</div>

<script>

    function addContent(dessertid,houseid){
    	var postUrl = "<%=request.getContextPath() + "/Reserve"%>";
        var postData = "addDessert";
        var postData2 = dessertid;
        var postData3 = houseid;
        var ExportForm = document.createElement("FORM");
        document.body.appendChild(ExportForm);
        ExportForm.method = "POST";
        var newElement = document.createElement("input");  
        newElement.setAttribute("name", "reserve");
        newElement.setAttribute("type", "hidden");
        var newElement2 = document.createElement("input"); 
        newElement2.setAttribute("name", "dessertId"); 
        newElement2.setAttribute("type", "hidden");
        var newElement3 = document.createElement("input"); 
        newElement3.setAttribute("name", "houseId"); 
        newElement3.setAttribute("type", "hidden");
        ExportForm.appendChild(newElement);
        ExportForm.appendChild(newElement2);
        ExportForm.appendChild(newElement3);
        newElement.value = postData;
        newElement2.value = postData2;
        newElement3.value = postData3;
        ExportForm.action = postUrl;
        ExportForm.submit();
    }
    
    function reduceContent(dessertid,houseid){
    	var postUrl = "<%=request.getContextPath() + "/Reserve"%>";
        var postData = "reduceDessert";
        var postData2 = dessertid;
        var postData3 = houseid;
        var ExportForm = document.createElement("FORM");
        document.body.appendChild(ExportForm);
        ExportForm.method = "POST";
        var newElement = document.createElement("input");  
        newElement.setAttribute("name", "reserve");
        newElement.setAttribute("type", "hidden");
        var newElement2 = document.createElement("input"); 
        newElement2.setAttribute("name", "dessertId"); 
        newElement2.setAttribute("type", "hidden");
        var newElement3 = document.createElement("input"); 
        newElement3.setAttribute("name", "houseId"); 
        newElement3.setAttribute("type", "hidden");
        ExportForm.appendChild(newElement);
        ExportForm.appendChild(newElement2);
        ExportForm.appendChild(newElement3);
        newElement.value = postData;
        newElement2.value = postData2;
        newElement3.value = postData3;
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

<script>
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

var sel = document.getElementById("select");
var id = getUrlParam("house");
for(var i = 0; i<sel.length; i++){
    if(sel.options[i].value == id){
        sel.options[i].selected = 'selected';
    }
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