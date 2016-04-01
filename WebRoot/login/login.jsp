<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >

<HTML>
	<head>
		<META content="IE=10.000" http-equiv="X-UA-Compatible">
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
		<title>登录 </title>  
		<link href="<%=basePath %>login/js/global_zh.css" rel="stylesheet" type="text/css">
		<link href="<%=basePath %>login/js/signin.css" rel="stylesheet" type="text/css">
		<script src="<%=basePath %>login/js/jquery.js" type="text/javascript"></script>
		<script src="<%=basePath %>login/js/util.js" type="text/javascript"></script>
		<script src="<%=basePath %>login/js/login.js" type="text/javascript" charset="UTF-8"></script>
		<script src="<%=basePath %>login/js/icheck.js"type="text/javascript" ></script>
		<script type="text/javascript">
		//监听回车,触发动作
			function on_return(){
				if(window.event.keyCode == 13){
					login();
				}
			}
		</script>
		<style>
			.checkbox,.radio{display: inline-block;*display: inline;*zoom:1;height: 24px; line-height: 24px; margin-right: 20px;}
	        .checkbox ins,.radio ins{display: inline-block;*display: inline;*zoom:1; width: 23px; height: 22px; vertical-align: middle; background: url('img/blue.png') no-repeat; margin-right: 8px; -webkit-transition:all 0.1s linear; -moz-transition:all 0.1s linear; -o-transition:all 0.1s linear; -ms-transition:all 0.1s linear; transition:all 0.1s linear;vertical-align: middle;}
	        .checkbox ins{background-position: 0px 0px; }
	        .radio ins{background-position: -120px 0px; }
	        .checkbox .hover{background-position: -24px 0px; }
	        .checkbox .checked{background-position: -48px 0px; }
	        .checkbox .enable{background-position: -96px 0px;}
	        .checkbox .disabled{background-position: -72px 0px;}
	        .radio .hover{background-position: -144px 0px;}
	        .radio .checked{background-position: -168px 0px;}
	        .radio .enable{background-position: -214px 0px;}
	        .radio .disabled{background-position: -191px 0px;}
	        .checkbox span,.radio span{display: inline-block;*display: inline;*zoom:1;vertical-align: middle; }
		</style>
	</head> 
	<body onkeydown="on_return();">
	
		<div class="signin-bg"></div>
		<div class="signin-container">
		<div style="margin: 170px 0px 30px 45px;">
		<div style="width: 340px; float: left; display: inline-block;">
			<div style="margin-bottom: 8px;">
				<a href=""><img style="width: 200px;" src="<%=basePath %>login/img/title.png" border="0"></a>
			</div>
		
		<form name="loginform" id="loginform" action="<%=basePath %>servlet/Login" method="post">
		
		<div class="sex_lx" id = "lx_se">
		    <div class="radio" name="sex" value="0"><ins></ins><span>管理员</span></div>
			<div class="radio" name="sex" value="1"><ins></ins><span>教师</span></div>
			<div class="radio" name="sex" value="2"><ins></ins><span>学生</span></div>
        </div>
			<input type="hidden" name="act" value="0" id="act"/>
			<div style="margin-top: 25px;">
				<INPUT name="username" class="signin-txt" id="username" type="text" placeholder="用户编号" value="">
			</div>
			<div style="margin-top: 20px;">
				<INPUT name="password" class="signin-txt" id="password" type="password" placeholder="密码" value="">
			</div>
			<div style="margin-top: 20px;">
				<span class="button default signin-btn" id="signin_btn" onclick="login()">立即登录</span>	
				<a class="signin-forget" href="">忘记密码</a>
			</div>
			<div style="color: rgb(34, 34, 34); margin-top: 38px;">账号有问题?&nbsp; 请联系&nbsp;
			<a  style="color: rgb(228, 87, 61);" 
			href="../contact_us/index.html" title="联系我们">联系我们</a></div>
		</form>
		</div>
		
		<div class="clear"></div></div></div>
		
	</body>
</HTML>
