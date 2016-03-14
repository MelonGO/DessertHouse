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

<jsp:useBean id="admin_listWaiter" type="edu.nju.desserthouse.action.business.WaiterBean"
		scope="session"></jsp:useBean>
<jsp:useBean id="item" class="edu.nju.desserthouse.model.Waiter"
		scope="page"></jsp:useBean>
<jsp:useBean id="admin_housebean" type="edu.nju.desserthouse.action.business.HouseBean"
		scope="session"></jsp:useBean>
<jsp:useBean id="house" class="edu.nju.desserthouse.model.House"
		scope="page"></jsp:useBean>
<div class="container bg">
    <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-12">
            <h1 class="font-big font-white">
                店员</br>
                <small>店员管理</small>
            </h1>
        </div>
    </div>
    <div>
        <button class="btn btn-success btn-block fa fa-plus fa-3x" data-toggle="modal" data-target="#waiteradd"></button>
    </div>
    <div class="row">
        <div class="col-md-12">
            <h2>店员列表</h2>
            <table class="table waiter-list">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>店员名字</th>
                    <th>性别</th>
                    <th>店员密码</th>
                    <th>所属店铺ID</th>
                </tr>
                </thead>
                <tbody>
                <%
    				for (int i = 0; i < admin_listWaiter.getWaiterlist().size(); i++) {
    					pageContext.setAttribute("item", admin_listWaiter.getWaiterlist().get(i));
    			%>
                <tr>
                    <td><jsp:getProperty name="item" property="waiterid"/></td>
                    <td><jsp:getProperty name="item" property="waitername"/></td>
                    <td><jsp:getProperty name="item" property="gender"/></td>
                    <td><jsp:getProperty name="item" property="waiterpassword"/></td>
                    <td><jsp:getProperty name="item" property="houseid"/>
                        <button class='btn btn-primary fa fa-minus-circle center-block pull-right' 
                        		onclick='click_delete(<jsp:getProperty name="item" property="waiterid"/>)'></button>
                        <button class='btn btn-warning fa fa-eraser center-block pull-right'
                                data-toggle='modal' data-target='#waiterupdate' 
                                onclick='get_WaiterId(<jsp:getProperty name="item" property="waiterid"/>)'></button>
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

<div class="modal fade" id="waiteradd" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel_1">
                    新增店员
                </h4>
            </div>
				<form class="form-horizontal" role="form"
					action="<%=request.getContextPath() + "/WaiterManage"%>"
					method="post" onsubmit="return(waiter_create())">
					<div class="modal-body">
						<input type="text" style="display: none" name="manage" value="add">
						<div class="form-group">
							<label for="waitername" class="col-sm-2 control-label">店员名称:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="waitername"
									placeholder="请输入新增店员名称" name="waitername">
							</div>
						</div>
						<div class="form-group">
							<label for="waiterpassword"
								class="col-sm-2 control-label">店员密码:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="waiterpassword"
									placeholder="请输入新增店员密码" name="waiterpassword">
							</div>
						</div>
						<div class="form-group">
							<label for="gender" class="col-sm-2 control-label">性别:</label>
							<div class="col-sm-10">
								<select class="form-control" name="gender">
									<option value="male">男</option>
									<option value="female">女</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="houseid" class="col-sm-2 control-label">所属店铺:</label>
							<div class="col-sm-10">
								<select class="form-control" name="houseid">
									<%
    									for (int i = 0; i < admin_housebean.getHouselist().size(); i++) {
    									pageContext.setAttribute("house", admin_housebean.getHouselist().get(i));
    								%>
									<option value="<jsp:getProperty name="house" property="houseid"/>">
										<jsp:getProperty name="house" property="housename"/>
									</option>
									<%
                						}
									%>
								</select>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"
							onclick="clear_all()">取消</button>
						<button type="submit" class="btn btn-primary" onsubmit="">
							确认</button>
					</div>
				</form>
			</div>
    </div>
</div>

<div class="modal fade" id="waiterupdate" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel_2">
                    修改店员信息
                </h4>
            </div>
            <form class="form-horizontal" role="form" action="<%=request.getContextPath() + "/WaiterManage"%>"
                  method="post" onsubmit="return(waiter_refresh())">
					<div class="modal-body">
						<div class="form-group">
							<label for="waiterid-refresh" class="col-sm-2 control-label">店员ID:</label>
							<input type="text" style="display: none" name="manage"
								value="update">
							<div class="col-sm-10">
								<input type="number" class="form-control" id="waiterid-refresh"
									name="waiterid-refresh" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label for="waitername-refresh" class="col-sm-2 control-label">店员名称:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="waitername-refresh"
									placeholder="请输入修改后的店员名称" name="waitername-refresh">
							</div>
						</div>
						<div class="form-group">
							<label for="waiterpassword-refresh"
								class="col-sm-2 control-label">店员密码:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="waiterpassword-refresh"
									placeholder="请输入修改后的店员密码" name="waiterpassword-refresh">
							</div>
						</div>
						<div class="form-group">
							<label for="gender-refresh" class="col-sm-2 control-label">性别:</label>
							<div class="col-sm-10">
								<select class="form-control" name="gender-refresh">
									<option value="male">男</option>
									<option value="female">女</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="houseid-refresh" class="col-sm-2 control-label">所属店铺:</label>
							<div class="col-sm-10">
								<select class="form-control" name="houseid-refresh">
									<%
    									for (int i = 0; i < admin_housebean.getHouselist().size(); i++) {
    									pageContext.setAttribute("house", admin_housebean.getHouselist().get(i));
    								%>
									<option value="<jsp:getProperty name="house" property="houseid"/>">
										<jsp:getProperty name="house" property="housename"/>
									</option>
									<%
                						}
									%>
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
	function get_WaiterId(waiterId){
    	var waiter_id = document.getElementById("waiterid-refresh");
    	waiter_id.value = waiterId;
	}

    function clear_all() {
        document.getElementById("waitername").value = "";
        document.getElementById("waiterpassword").value = "";
    }
    function clear_all_refresh() {
        document.getElementById("waiterid-refresh").value = "";
        document.getElementById("waitername-refresh").value = "";
        document.getElementById("waiterpassword-refresh").value = "";
    }
    
    function click_delete(ID){
    	var postUrl = "<%=request.getContextPath() + "/WaiterManage"%>";
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
    function waiter_create() {
        var msg = "";
        if ($("#waitername").val().length == 0) {
            msg = "请输入店员名称!";
            alert(msg);
            return false;
        }
        if ($("#waiterpassword").val().length == 0) {
            msg = "请输入店员密码!";
            alert(msg);
            return false;
        }
    }

    function waiter_refresh() {
        var msg = "";
        if ($("#waiterid-refresh").val().length == 0) {
            msg = "请输入店员ID!";
            alert(msg);
            return false;
        }
        if ($("#waitername-refresh").val().length == 0) {
            msg = "请输入店员名称!";
            alert(msg);
            return false;
        }
        if ($("#waiterpassword-refresh").val().length == 0) {
            msg = "请输入店员密码!";
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