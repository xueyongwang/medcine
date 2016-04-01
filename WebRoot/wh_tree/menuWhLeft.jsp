<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.service.classes.TreeClass" %>   
<%@ page import="com.service.classes.Jdbc_ch,com.service.classes.ZnnToolsClass" %>    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String treeTable = request.getParameter("tree_table");//表名
String addZd = request.getParameter("add_zd");//原有基础上新添加的字段
String addValue = request.getParameter("add_value");//原有基础上新添加的字段
String isfun = request.getParameter("isfun");//app_link是否为一个方法      0：不是	 1：是
String userLx = request.getParameter("userLx");//用户类型  
Jdbc_ch db = new Jdbc_ch();
ZnnToolsClass ztc = new ZnnToolsClass();
TreeClass tc = new TreeClass();

String maxId = ztc.getId("id",treeTable);

String selFname = tc.addFname(addZd,addValue);
System.out.println("-------------"+selFname);
selFname = selFname.equals(" ")?" where userLx='"+userLx+"';":" where "+selFname+ "and userLx='"+userLx+"';";
String query_sql = "select id from "+treeTable +selFname;
List<?> queryList = db.query(query_sql);
if(queryList.size()==0||queryList==null){//表中无记录，新建一条记录
	String zdKey = "";
	String zdValue = "";
	String creatTableSql = "";
	if(!addZd.equals("null")&&!addValue.equals("null")){ 
		String zdArr[] = addZd.split(",");
		for (int i = 0; i < zdArr.length; i++) {
			zdKey += ","+zdArr[i];
			zdValue += ",'"+addValue+"'";
		}
	}
	if(isfun.equals("1")){ 
		creatTableSql = "INSERT INTO `"+treeTable+"` (id,zid,userLx,zname,fid,fname,zbh,fbh,leve,message,wh_link,app_link,wh_target,app_target,introduce "+zdKey+") VALUES ("+maxId+",'"+treeTable+"_"+maxId+"','"+userLx+"','tree_root','-1','','01','-1',0,NULL,'wh_tree.jsp?zid="+treeTable+"_"+maxId+"&leve=0&tree_table="+treeTable+"&add_zd="+addZd+"&isfun="+isfun+"&add_value="+addValue+"','javascript:"+treeTable+"(\\\\\\'1\\\\\\')','windowspp','window1','' "+zdValue+");";
	}else if(isfun.equals("0")){ 
		creatTableSql = "INSERT INTO `"+treeTable+"` (id,zid,userLx,zname,fid,fname,zbh,fbh,leve,message,wh_link,app_link,wh_target,app_target,introduce "+zdKey+") VALUES ("+maxId+",'"+treeTable+"_"+maxId+"','"+userLx+"','tree_root','-1','','01','-1',0,NULL,'wh_tree.jsp?zid="+treeTable+"_"+maxId+"&leve=0&tree_table="+treeTable+"&add_zd="+addZd+"&isfun="+isfun+"&add_value="+addValue+"',NULL,'windowspp','window1','' "+zdValue+");";
	}
	System.out.println("-------------"+creatTableSql);
	Jdbc_ch.useSQL(creatTableSql);
}


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<title>菜单树</title>
	<link rel="StyleSheet" href="js/dtree.css" type="text/css" />
	<script type="text/javascript" src="js/dtree.js"></script>
</head>

<body style="background-color:##fff ; margin-left:0px; margin-top: 0px;">
	<div class="dtree" style="background-image: url(../images/left_bg1.gif)">
		<script type="text/javascript">
			<%
				request.setCharacterEncoding("utf-8");
				response.setCharacterEncoding("utf-8");	
				String sstr = "select zid,fid,zname,wh_link,wh_target from "+treeTable + selFname;
			%>
		    var dtree = <%=tc.getBuidingTreeString(sstr, "dtree")%>
	        document.write(dtree);
		</script>
	</div>
</body>
</html>