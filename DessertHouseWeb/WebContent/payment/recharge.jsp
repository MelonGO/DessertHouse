<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Recharge</title>
    
    <link href="<%=request.getContextPath() + "/bootstrap-3.3.5-dist/css/bootstrap.min.css"%>" rel="stylesheet">
    <link href="<%=request.getContextPath() + "/css/mystyle.css"%>" rel="stylesheet">
    <link href="<%=request.getContextPath() + "/css/queries.css"%>" rel="stylesheet">
    <link href="<%=request.getContextPath() + "/font-awesome-4.3.0/css/font-awesome.min.css"%>" rel="stylesheet">
    <script src="<%=request.getContextPath() + "/js/jquery.min.js"%>"></script>
    <script src="<%=request.getContextPath() + "/bootstrap-3.3.5-dist/js/bootstrap.min.js"%>"></script>
    <script src="<%=request.getContextPath() + "/js/myscript.js"%>"></script>
    
</head>
<body class="recharge">

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12 col-md-12 col-lg-12">
            <h1 class="recharge">
                <span class="fa fa-quote-left center-block"></span> 充值 <span class="fa fa-quote-right"></span>
            </h1>
			<jsp:useBean id="user_accountbean"
				type="edu.nju.desserthouse.action.business.AccountBean"
				scope="session"></jsp:useBean>
			<jsp:useBean id="item" class="edu.nju.desserthouse.model.Account"
				scope="page"></jsp:useBean>
			<%
        		pageContext.setAttribute("item", user_accountbean.getAccount());
        	%>
			<h2>账户余额: <jsp:getProperty name="item" property="payment"/></h2>
            <div>
                <form action="<%=request.getContextPath() + "/PayResult"%>" method="post" 
                		onsubmit="return(payment_create())">
                	<input type="text" style="display:none" name="paytype" value="recharge">
                	<div class="form-group">
                        <label for="password">用户ID: </label>
                        <input class="login" id="password" type="text" readonly="readonly"
                                name="userid" value="<jsp:getProperty name="item" property="userid"/>">
                    </div>
                    <div class="form-group">
                        <label for="money">支付金额: </label>
                        <input class="login" id="money" type="number"
                               placeholder="输入金额" name="money">
                    </div>
                    <div class="form-group">
                        <label for="password">银行卡密码: </label>
                        <input class="login" id="password" type="password"
                               placeholder="输入密码" name="password">
                    </div>
                    <button class="btn btn-success center-block btn-block btn-lg">
                        <span class="fa fa-check"></span>
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    function payment_create() {
        var msg = "";
        if ($("#money").val().length == 0) {
            msg = "请输入支付金额!";
            alert(msg);
            return false;
        }
        if ($("#password").val().length == 0) {
            msg = "请输入密码!";
            alert(msg);
            return false;
        }
    }
</script>

</body>
</html>