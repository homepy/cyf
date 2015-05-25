<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>统一风险监控平台</title>

<!-- Bootstrap  -->
<link rel="stylesheet" href="bootstrap-3.3.0/css/bootstrap.min.css">

<!-- Custom styles for this template -->
<link rel="stylesheet" href="css/jumbotron-narrow.css">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->

<script type="text/javascript" src="bootstrap-3.3.0/js/html5shiv.min.js"></script>
<script type="text/javascript" src="bootstrap-3.3.0/js/respond.min.js"></script>

</head>

<body>

	<div class="container">
		<div class="header">
			<nav>
				<ul class="nav nav-pills pull-right">
					<li role="presentation" class="active"><a href="#">Home</a></li>
					<li role="presentation"><a href="#">About</a></li>
					<li role="presentation"><a href="#">Contact</a></li>
				</ul>
			</nav>
			<h3 class="text-muted">&nbsp;</h3>
		</div>

		<div class="jumbotron">
			<h1>全流程统一风险监控平台<small></small></h1>
			<p class="lead"></p>
			<p>
				<!-- 
				<a class="btn btn-lg btn-primary" href="#" role="button">View
					details</a>
					 -->
			</p>
		</div>

		<div class="row marketing">
			<div class="col-lg-6">
				<h4>Net events</h4>
				<p>
					&nbsp;&nbsp;&nbsp;&nbsp;银行服务实时预警监控&nbsp; <a class="btn" href="net-events" role="button">View
						details &raquo;</a>
				</p>
				<h4>Net event details</h4>
				<p>
					&nbsp;&nbsp;&nbsp;&nbsp;银行服务预警查询&nbsp; <a class="btn" href="net-eventdetails" role="button">View
						details &raquo;</a>
				</p>


				<h4>Trade events</h4>
				<p>
					&nbsp;&nbsp;&nbsp;&nbsp;交易实时预警监控&nbsp; <a class="btn" href="trade-events" role="button">View
						details &raquo;</a>
				</p>
				<h4>Trade event details</h4>
				<p>
					&nbsp;&nbsp;&nbsp;&nbsp;交易预警查询&nbsp; <a class="btn" href="trade-eventdetails" role="button">View details
						&raquo;</a>
				</p>

				<h4>Application events</h4>
				<p>
					&nbsp;&nbsp;&nbsp;&nbsp;信用卡申请实时预警监控&nbsp; <a class="btn" href="app-events" role="button">View
						details &raquo;</a>
				</p>
				<h4>Application event details</h4>
				<p>
					&nbsp;&nbsp;&nbsp;&nbsp;信用卡申请预警查询&nbsp; <a class="btn" href="app-eventdetails" role="button">View details
						&raquo;</a>
				</p>
			</div>

			<div class="col-lg-6">
				<h4>Account Blacklist</h4>
				<p>
					&nbsp;&nbsp;&nbsp;&nbsp;银行服务&nbsp;账户黑名单&nbsp; <a class="btn" href="net-blacklists-acct" role="button">View
						details &raquo;</a>
				</p>


				<h4>ID Blacklist</h4>
				<p>
					&nbsp;&nbsp;&nbsp;&nbsp;银行服务&nbsp;证件号黑名单&nbsp; <a class="btn" href="net-blacklists-id" role="button">View details
						&raquo;</a>
				</p>

				<h4>IP Blacklist</h4>
				<p>
					&nbsp;&nbsp;&nbsp;&nbsp;银行服务&nbsp;IP地址黑名单&nbsp; <a class="btn" href="net-blacklists-ip" role="button">View details
						&raquo;</a>
				</p>
			</div>
		</div>

		<footer class="footer">
			<p align="center">&copy; homepy 2015</p>
		</footer>

	</div>
	<!-- /container -->
</body>
</html>
