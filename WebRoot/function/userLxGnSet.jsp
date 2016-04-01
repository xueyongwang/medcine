<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.menu.classes.UserClass"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
UserClass uc = new UserClass();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userLxGn.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	
  </head>
    <body>
  <form method="post" name="form1" id="form1" action="servlet/GnServlet"><!--com.eleBusiPlat.servlet-->
  	<input type="hidden" name="url" id="url" value="<%=basePath %>function/userLxGnSet.jsp">
  	<input type="hidden" name="bs" id="bs" value="3">
     	<div id="mk_dj" style="position:absolute;top:20px;left:20px;">
     	<fieldset class="fieldset1" width="98%">
			<legend class="drop0">用户分类菜单名设置</legend><br/>
	    	<p>
	    	<label class="drop1" for="name">用户类型：</label><%=uc.getUserLxSel() %>
	    	</p><br/>
	    	<p>
	    	<label class="drop1" for="name">对应功能菜单名称：</label><input type="text" id="gnMenuName" name="gnMenuName" value="" />
	    	</p><br/>
	    	<p>
	    	<label class="drop1" for="name">功能简介：</label><textarea id="introduce" name="introduce" class="required"></textarea>
	    	</p><br/>
	    	<input type="submit" name="handBtn" id="handBtn" class="btn1_mouseout" value=" 提 交 " />
	    	<input type="button" name="handBtn" id="handBtn" class="btn1_mouseout" value=" 返回" onclick="history.go(-1);"/>
    		</fieldset>
    	</div>
     </form>

  </body>
</html>
