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
<jsp:useBean id="waiter_dessertbean" type="edu.nju.desserthouse.action.business.DessertBean"
 			scope="session"></jsp:useBean>
<jsp:useBean id="dessert" class="edu.nju.desserthouse.model.Dessert"
			scope="page"></jsp:useBean>
<jsp:useBean id="waiter_housebean" type="edu.nju.desserthouse.action.business.HouseBean"
 			scope="session"></jsp:useBean>
<jsp:useBean id="house" class="edu.nju.desserthouse.model.House"
			scope="page"></jsp:useBean>
<%
 	pageContext.setAttribute("waiter", waiter_waiterbean.getWaiter());
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
    </div>
    <div>
        <button class="btn btn-success btn-block fa fa-plus fa-3x" data-toggle="modal" data-target="#planadd"></button>
    </div>
    <div class="row">
        <div class="col-md-12">
            <h2>产品计划</h2>
            <table class="table waiter-list">
                <thead>
                <tr>
                    <th>计划ID</th>
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
    				for (int i = 0; i < waiter_planbean.getPlanlist().size(); i++) {
    					pageContext.setAttribute("plan", waiter_planbean.getPlanlist().get(i));
    			%>
                <tr>
                    <td><jsp:getProperty name="plan" property="planid"/></td>
                    <td><jsp:getProperty name="plan" property="dessertname"/></td>
                    <td><jsp:getProperty name="plan" property="dessertprice"/></td>
                    <td><jsp:getProperty name="plan" property="amount"/></td>
                    <td><jsp:getProperty name="plan" property="day"/></td>
                    <td><jsp:getProperty name="plan" property="status"/></td>
                    <td><jsp:getProperty name="plan" property="plantime"/>
                        <button class='btn btn-primary fa fa-minus-circle center-block pull-right' 
                        		onclick='click_delete(<jsp:getProperty name="plan" property="planid"/>)'></button>
                        <button class='btn btn-warning fa fa-eraser center-block pull-right'
                                data-toggle='modal' data-target='#planupdate' 
                                onclick='get_PlanId(<jsp:getProperty name="plan" property="planid"/>)'></button>
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

<div class="modal fade" id="planadd" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel_1">
                    新增计划
                </h4>
            </div>
				<form class="form-horizontal" role="form"
					action="<%=request.getContextPath() + "/PlanManage"%>"
					method="post" onsubmit="return(plan_create())">
					<div class="modal-body">
						<input type="text" style="display: none" name="manage" value="add">
						<div class="form-group">
							<label for="waiterid" class="col-sm-2 control-label">店员ID:</label>
							<div class="col-sm-10">
								<input type="number" class="form-control" id="waiterid"
									name="waiterid" readonly="readonly"
									value="<jsp:getProperty name="waiter" property="waiterid"/>">
							</div>
						</div>
						<div class="form-group">
							<label for="houseid" class="col-sm-2 control-label">店铺ID:</label>
							<div class="col-sm-10">
								<input type="number" class="form-control" id="houseid"
									name="houseid" readonly="readonly"
									value="<jsp:getProperty name="house" property="houseid"/>">
							</div>
						</div>
						<div class="form-group">
							<label for="dessertidt" class="col-sm-2 control-label">选择甜品:</label>
							<div class="col-sm-10">
								<select class="form-control" name="dessertid">
									<%
    									for (int i = 0; i < waiter_dessertbean.getDessertlist().size(); i++) {
    									pageContext.setAttribute("dessert", waiter_dessertbean.getDessertlist().get(i));
    								%>
									<option value="<jsp:getProperty name="dessert" property="dessertid"/>">
										<jsp:getProperty name="dessert" property="dessertname"/>,<jsp:getProperty name="dessert" property="price"/>元
									</option>
									<%
                						}
									%>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="amount" class="col-sm-2 control-label">出售数量:</label>
							<div class="col-sm-10">
								<input type="number" class="form-control" id="amount" name="amount">
							</div>
						</div>
						<div class="form-group">
							<label for="day" class="col-sm-2 control-label">出售时间:</label>
							<div class="col-sm-10">
								<select class="form-control" name="day">
									<option value="Monday">Monday</option>
									<option value="Tuesday">Tuesday</option>
									<option value="Wednesday">Wednesday</option>
									<option value="Thursday">Thursday</option>
									<option value="Friday">Friday</option>
									<option value="Saturday">Saturday</option>
									<option value="Sunday">Sunday</option>
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

<div class="modal fade" id="planupdate" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel_2">
                    修改计划
                </h4>
            </div>
            <form class="form-horizontal" role="form" action="<%=request.getContextPath() + "/PlanManage"%>"
                  method="post" onsubmit="return(waiter_refresh())">
					<div class="modal-body">
						<div class="form-group">
							<label for="planid-refresh" class="col-sm-2 control-label">计划ID:</label>
							<input type="text" style="display: none" name="manage"
								value="update">
							<div class="col-sm-10">
								<input type="number" class="form-control" id="planid-refresh"
									name="planid-refresh" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label for="dessertid-refresh" class="col-sm-2 control-label">选择甜品:</label>
							<div class="col-sm-10">
								<select class="form-control" name="dessertid-refresh">
									<%
    									for (int i = 0; i < waiter_dessertbean.getDessertlist().size(); i++) {
    									pageContext.setAttribute("dessert", waiter_dessertbean.getDessertlist().get(i));
    								%>
									<option value="<jsp:getProperty name="dessert" property="dessertid"/>">
										<jsp:getProperty name="dessert" property="dessertname"/>
									</option>
									<%
                						}
									%>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="amount-refresh" class="col-sm-2 control-label">出售数量:</label>
							<div class="col-sm-10">
								<input type="number" class="form-control" id="amount-refresh" name="amount-refresh">
							</div>
						</div>
						<div class="form-group">
							<label for="day-refresh" class="col-sm-2 control-label">出售时间:</label>
							<div class="col-sm-10">
								<select class="form-control" name="day-refresh">
									<option value="Monday">Monday</option>
									<option value="Tuesday">Tuesday</option>
									<option value="Wednesday">Wednesday</option>
									<option value="Thursday">Thursday</option>
									<option value="Friday">Friday</option>
									<option value="Saturday">Saturday</option>
									<option value="Sunday">Sunday</option>
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
	function get_PlanId(planId){
    	var plan_id = document.getElementById("planid-refresh");
    	plan_id.value = planId;
	}

    function clear_all() {
        document.getElementById("amount").value = "";
    }
    function clear_all_refresh() {
        document.getElementById("amount-refresh").value = "";
    }
    
    function click_delete(ID){
    	var postUrl = "<%=request.getContextPath() + "/PlanManage"%>";
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
    function plan_create() {
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

    function plan_refresh() {
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