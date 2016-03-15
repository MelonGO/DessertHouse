<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Information</title>
    
    <link href="<%=request.getContextPath() + "/bootstrap-3.3.5-dist/css/bootstrap.min.css"%>" rel="stylesheet">
    <link href="<%=request.getContextPath() + "/css/mystyle.css"%>" rel="stylesheet">
    <link href="<%=request.getContextPath() + "/css/queries.css"%>" rel="stylesheet">
    <link href="<%=request.getContextPath() + "/font-awesome-4.3.0/css/font-awesome.min.css"%>" rel="stylesheet">
    <script src="<%=request.getContextPath() + "/js/jquery.min.js"%>"></script>
    <script src="<%=request.getContextPath() + "/bootstrap-3.3.5-dist/js/bootstrap.min.js"%>"></script>
    <script src="<%=request.getContextPath() + "/js/myscript.js"%>"></script>
    
</head>
<body class="user-info">

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

<div class="container-fluid register">
	<div class="row font-white" style="opacity: 0.9">
        <h1 class="font-big">
            <jsp:useBean id="user_userinfo"
                         type="edu.nju.desserthouse.action.business.UserBean"
                         scope="session"></jsp:useBean>
            <jsp:useBean id="item" class="edu.nju.desserthouse.model.User"
                         scope="page"></jsp:useBean>
			<jsp:useBean id="user_accountbean"
						 type="edu.nju.desserthouse.action.business.AccountBean"
						 scope="session"></jsp:useBean>
			<jsp:useBean id="account"
					     class="edu.nju.desserthouse.model.Account"
					     scope="page"></jsp:useBean>
				会员信息
        </h1>
        <%
        pageContext.setAttribute("item", user_userinfo.getUser());
        pageContext.setAttribute("account", user_accountbean.getAccount());
        %>
         <div class="row">
            <div class="col-md-6">
                <button
					onclick="click_recharge(<jsp:getProperty name="item" property="userid"/>)"
					class="btn btn-warning center-block btn-lg btn-block">充值</button>
            </div>
            <div class="col-md-3">
                <button onclick="click_exchange(<jsp:getProperty name="item" property="userid"/>)"
                        class="btn btn-primary center-block btn-lg btn-block">兑换</button>
            </div>
            <div class="col-md-3">
            	<button onclick="click_cancel(<jsp:getProperty name="item" property="userid"/>)"
                        class="btn btn-danger center-block btn-lg btn-block">取消会员</button>
           	</div>
        </div>
        <form role="form" action="/DessertHouseWeb/user.update" method="post">
            <div class="form-group">
                <label for="username">用户名：</label>
                <input type="text" class="form-control input-lg" name="username" id="username"
                       value="<jsp:getProperty name="item" property="username"/>">
            </div>
            <div class="form-group">
                <label for="cardid">会员卡号：</label>
                <input type="text" class="form-control input-lg" readonly="readonly" name="cardid" id="cardid"
                       value="<jsp:getProperty name="item" property="cardid"/>">
            </div>
            <div class="form-group">
            	<label for="status">状态：</label>
                <input type="text" class="form-control input-lg" readonly="readonly" name="status" id="status"
                       value='<jsp:getProperty name="item" property="status"/>'>
            </div>
            <div class="form-group">
            	<label for="status">余额：</label>
                <input type="text" class="form-control input-lg" readonly="readonly" name="status" id="status"
                       value='<jsp:getProperty name="account" property="payment"/>'>
            </div>
            <div class="form-group">
                <label for="reward">积分：</label>
                <input type="text" class="form-control input-lg" readonly="readonly" name="reward" id="reward"
                		value="<jsp:getProperty name="item" property="reward"/>">
            </div>
            <div class="form-group">
                <label for="level">等级：</label>
                <input type="text" class="form-control input-lg" readonly="readonly" name="level" id="level"
                       value="<jsp:getProperty name="item" property="level"/>">
            </div>
            <div class="form-group">
                <label for="gender">性别：</label>
                <select class="form-control input-lg" name="gender" id="gender"
                        value="">
                <option value="male">男</option>
                <option value="female">女</option>
                </select>
            </div>
            <div class="form-group">
                <label for="age">年龄：</label>
                <input type="number" class="form-control input-lg" name="age" id="age"
                       value="<jsp:getProperty name="item" property="age"/>">
            </div>
            <div class="form-group">
                <label for="address">地区：</label>
                <select class="form-control input-lg" name="address" id="address">
                	<option value="Asia">亚洲</option>
                	<option value="America">美洲</option>
                	<option value="Europe">欧洲</option>
                	<option value="Africa">非洲</option>
                	<option value="Oceania">大洋洲</option>
                </select>
            </div>
            <button type="submit" class="btn btn-info btn-block fa fa-save fa-2x"></button>
        </form>
    </div>
    <div><p></p></div>
</div>

<script>
	var sel = document.getElementById("gender");
    var val = "<jsp:getProperty name="item" property="gender"/>";
    for(var i = 0; i<sel.length; i++){
    	if(sel.options[i].value == val){
    		sel.options[i].selected = 'selected';
    	}
    }
    
    var sel_1 = document.getElementById("address");
    var val_1 = "<jsp:getProperty name="item" property="address"/>";
    for(var i = 0; i<sel_1.length; i++){
    	if(sel_1.options[i].value == val_1){
    		sel_1.options[i].selected = 'selected';
    	}
    }
    
    function click_recharge(ID){
    	var postUrl = "<%=request.getContextPath() + "/Payment"%>";
        var postData = "recharge";
        var postData2 = ID;
        var ExportForm = document.createElement("FORM");
        document.body.appendChild(ExportForm);
        ExportForm.method = "POST";
        var newElement = document.createElement("input");  
        newElement.setAttribute("name", "pay");
        newElement.setAttribute("type", "hidden");
        var newElement2 = document.createElement("input"); 
        newElement2.setAttribute("name", "userId"); 
        newElement2.setAttribute("type", "hidden");
        ExportForm.appendChild(newElement);
        ExportForm.appendChild(newElement2);
        newElement.value = postData;
        newElement2.value = postData2;
        ExportForm.action = postUrl;
        ExportForm.submit();
    }
    
    function click_cancel(ID){
    	var postUrl = "<%=request.getContextPath() + "/Payment"%>";
        var postData = "cancel";
        var postData2 = ID;
        var ExportForm = document.createElement("FORM");
        document.body.appendChild(ExportForm);
        ExportForm.method = "POST";
        var newElement = document.createElement("input");  
        newElement.setAttribute("name", "pay");
        newElement.setAttribute("type", "hidden");
        var newElement2 = document.createElement("input"); 
        newElement2.setAttribute("name", "userId"); 
        newElement2.setAttribute("type", "hidden");
        ExportForm.appendChild(newElement);
        ExportForm.appendChild(newElement2);
        newElement.value = postData;
        newElement2.value = postData2;
        ExportForm.action = postUrl;
        ExportForm.submit();
    }
    
    function click_exchange(ID){
    	var postUrl = "<%=request.getContextPath() + "/Payment"%>";
        var postData = "exchange";
        var postData2 = ID;
        var ExportForm = document.createElement("FORM");
        document.body.appendChild(ExportForm);
        ExportForm.method = "POST";
        var newElement = document.createElement("input");  
        newElement.setAttribute("name", "pay");
        newElement.setAttribute("type", "hidden");
        var newElement2 = document.createElement("input"); 
        newElement2.setAttribute("name", "userId"); 
        newElement2.setAttribute("type", "hidden");
        ExportForm.appendChild(newElement);
        ExportForm.appendChild(newElement2);
        newElement.value = postData;
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
                <p>&copy;Copyright 2016 - melon</p>
            </div>
        </div>
    </div>
</footer>

</body>
</html>