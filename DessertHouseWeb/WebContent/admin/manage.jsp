<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Management</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
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
            <li><a href="<%=request.getContextPath() + "/HouseManage"%>">店面管理</a></li>
            <li><a href="<%=request.getContextPath() + "/WaiterManage"%>">店员管理</a></li>
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

<div class="container bg">
    <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-12">
            <h1 class="font-big font-white">
					<jsp:useBean id="admin_listHouse"
						type="edu.nju.desserthouse.action.business.HouseBean"
						scope="session"></jsp:useBean>
					<jsp:useBean id="item" class="edu.nju.desserthouse.model.House"
						scope="page"></jsp:useBean>
					店铺</br>
                <small>店铺管理</small>
            </h1>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-12">
            <form class="navbar-form" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search House">
                </div>
                <button type="submit" class="btn btn-info fa fa-search"></button>
            </form>
        </div>
    </div>
    <div>
        <button class="btn btn-success btn-block fa fa-plus fa-3x" data-toggle="modal" data-target="#myModal"></button>
    </div>
    <hr class="division">
    <%
    	for (int i = 0; i < admin_listHouse.getHouselist().size(); i++) {
    		pageContext.setAttribute("item", admin_listHouse.getHouselist().get(i));
    %>
    <div class="row center-block">
        <div class="list-group">
            <div class="list-group-item active">
                <h4 class="list-group-item-heading">
                    店铺: <jsp:getProperty name="item" property="housename" />
                    (<jsp:getProperty name="item" property="main" />)
                    <button id='<jsp:getProperty name="item" property="houseid"/>' 
                    	onclick='click_delete(<jsp:getProperty name="item" property="houseid"/>)' 
                    	class="btn btn-default btn-sm fa fa-trash pull-right"></button>
                </h4>
            </div>
            <div class="list-group-item">
                <p class="list-group-item-text">
                	(ID: <jsp:getProperty name="item" property="houseid"/>)
                </p>
            </div>
            <div class="list-group-item">
                <p class="list-group-item-text">
                    详细地址: <jsp:getProperty name="item" property="address" />
                </p>
            </div>
            <HR>
        </div>
    </div>
    <%
    		}
	%>
	<div>
        <button class="btn btn-default btn-block fa fa-eraser fa-3x" data-toggle="modal"
                data-target="#refresh"></button>
    </div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel_1">
                    新建店铺
                </h4>
            </div>
            <form class="form-horizontal" role="form" 
            	action="<%=request.getContextPath() + "/HouseManage"%>"
                  method="post" onsubmit="return(house_create())">
                <div class="modal-body">
                	<input type="text" style="display:none" name="manage" value="add">
                    <div class="form-group">
                        <label for="housename" class="col-sm-2 control-label">店铺名称:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="housename"
                                   placeholder="请输入名称" name="housename">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="address" class="col-sm-2 control-label">店铺地址:</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="address"
                                   placeholder="请输入地址" name="address">
                        </div>
                    </div>
                    <div class="form-group">
						<label for="main" class="col-sm-2 control-label">类型:</label>
						<div class="col-sm-10">
							<select class="form-control" name="main">
								<option value="Main">总店</option>
								<option value="Branch">分店</option>
							</select>
						</div>
					</div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal" onclick="clear_all()">
                        取消
                    </button>
                    <button type="submit" class="btn btn-primary" onsubmit="">
                        确认
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="refresh" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel_2">
                    修改店铺信息
                </h4>
            </div>
            <form class="form-horizontal" role="form" action="<%=request.getContextPath() + "/HouseManage"%>"
                  method="post" onsubmit="return(house_refresh())">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="houseid-refresh" class="col-sm-2 control-label">店铺ID:</label>
                        <input type="text" style="display:none" name="manage" value="update">
                        <div class="col-sm-10">
                            <input type="number" class="form-control" id="houseid-refresh"
                                   placeholder="请输入要修改的店铺ID" name="houseid-refresh">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="housename-refresh" class="col-sm-2 control-label">店铺名称:</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="housename-refresh"
                                   placeholder="请输入修改后的店铺名称" name="housename-refresh">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="address-refresh" class="col-sm-2 control-label">店铺地址:</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="address-refresh"
                                   placeholder="请输入修改后的店铺地址" name="address-refresh">
                        </div>
                    </div>
                    <div class="form-group">
						<label for="main-refresh" class="col-sm-2 control-label">类型:</label>
						<div class="col-sm-10">
							<select class="form-control" name="main-refresh">
								<option value="Main">总店</option>
								<option value="Branch">分店</option>
							</select>
						</div>
					</div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal" onclick="clear_all_refresh()">
                        取消
                    </button>
                    <button type="submit" class="btn btn-primary" onsubmit="">
                        修改
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div>
	<img src="/DessertHouseWeb/img/logo.jpg" class="img-responsive img-circle center-block" 
		style="opacity: 0.8"> 
</div>


<script>
    function clear_all() {
        document.getElementById("housename").value = "";
        document.getElementById("address").value = "";
    }
    function clear_all_refresh() {
        document.getElementById("houseid-refresh").value = "";
        document.getElementById("housename-refresh").value = "";
        document.getElementById("address-refresh").value = "";
    }
    
    function click_delete(ID){
    	var postUrl = "<%=request.getContextPath() + "/HouseManage"%>";
        var postData = "delete";
        var postData2 = ID;
        var ExportForm = document.createElement("FORM");
        document.body.appendChild(ExportForm);
        ExportForm.method = "POST";
        var newElement = document.createElement("input");  
        newElement.setAttribute("name", "manage");
        newElement.setAttribute("type", "hidden");
        var newElement2 = document.createElement("input"); 
        newElement2.setAttribute("name", "deleteId"); 
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
    function house_create() {
        var msg = "";
        if ($("#housename").val().length == 0) {
            msg = "请输入店铺名称!";
            alert(msg);
            return false;
        }
        if ($("#address").val().length == 0) {
            msg = "请输入店铺地址!";
            alert(msg);
            return false;
        }
    }

    function house_refresh() {
        var msg = "";
        if ($("#houseid").val().length == 0) {
            msg = "请输入店铺ID!";
            alert(msg);
            return false;
        }
        if ($("#housename-refresh").val().length == 0) {
            msg = "请输入店铺名称!";
            alert(msg);
            return false;
        }
        if ($("#address-refresh").val().length == 0) {
            msg = "请输入店铺地址!";
            alert(msg);
            return false;
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