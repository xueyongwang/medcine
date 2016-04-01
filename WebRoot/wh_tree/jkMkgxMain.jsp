<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.service.classes.JkClass"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <base href="<%=basePath%>">
    <title>My JSP 'jkMkgxMain.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
		<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/2012.css"/>
	<script type="text/javascript">
		function changeDg(userLx,basePath){
			var url = basePath+"wh_tree/menuWhLeft.jsp?userLx="+userLx+"&tree_table=fun_menu&isfun=1&add_zd=null&add_value=null";
			window.open(url,"mkgxMain");
		}
		function addFirstMk(){
			var str = window.showModalDialog("addFirstMk.jsp", window,
				"dialogTop:150px;dialogLeft:600px;dialogWidth:350px;dialogHeight:220px;status:no; help:no; scroll:yes");
			if(str == "ok"){
				window.location.reload(true);
			}
		}
	</script>
	
	<style>
    .IN{
      position:absolute;
      right:0px;
      bottom:0px;
      background-color:;
    }body{
         
    }.Menu{
          position:absolute;
          right:0px;
          top:23%;
    }a{
      TEXT-DECORATION: none;
    }.b1{
       background-image:url(images/lx1.jpg);
    }.bd{
       background-image:url(images/bgc.jpg);
       background-repeat:no-repeat;
    }.rig{
       filter:progid:dximageTransform.Microsoft.Gradient(gradientType=1,startColorStr=#FFFFFF,endColorStr=#024627);
    }
</style>
  </head>
  
  <body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
   <input type="hidden" value="<%=basePath%>" id="basePath" name="basePath" />
   <table style="width:100%;height:1%" border="0" cellpadding="0" cellspacing="0" 
   bordercolorlight="#002222" bgcolor="#66CC00" style="filter: wave(add = false, feq = 1, lightstrength = 30, phase = 20, 
   strenth = 10)">
		<tr align="center">
			<td>
				<%
				    JkClass jkClass = new JkClass() ; 
				    String leftUrl = basePath+"wh_tree/menuWhLeft.jsp" ;
			    	String levelBar = jkClass.makLevelMsg(basePath,"userLx,zname","fun_menu");  
					if(!levelBar.equals("noMess")){
					 	String userLx = levelBar.split("@#")[0];
						String levelStr = levelBar.split("@#")[1];
						out.println(levelStr);
						leftUrl+="?userLx="+userLx+"&tree_table=fun_menu&isfun=1&add_zd=null&add_value=null";
				    }
				%>
			</td>
		</tr>
	</table>
	<table style="width:100%;height:98%;"  border=1 cellspacing=0 cellpadding=0 >
		<tr>
			<td name="left" id="left" width=18% height=100% valign="bottom">
				<iframe height=100% name="mkgxMain" id="mkgxMain" width=100% frameborder=1 src="<%=leftUrl %>"></iframe>
			</td>
			<td  style="width:2px;cursor:e-resize;background-color:#cccccc;" height=85%  align="center"   valign="middle">
				<font style="size:2px;background-color:#eeeeee;cursor:pointer;" onmousedown="Resize_setDefault(event,this);"></font>
			</td>
			<td height=100% >
				<iframe name="windowspp" id="windowspp" allowtransparency="" frameborder=1 class="bd" scrolling=yes width=100% src="" height=100%>
				</iframe>
			</td>
         </tr>
	</table>
  </body>
</html>
