<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.service.classes.ZnnToolsClass"%>
<%@ page language="java" import="com.service.classes.TreeClass"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	
	TreeClass tc = new TreeClass();
	ZnnToolsClass ztc = new ZnnToolsClass();
	
	String f_id = request.getParameter("zid");
	String leve = request.getParameter("leve");
	String treeTable = request.getParameter("tree_table");
	String addZd = request.getParameter("add_zd");
	String addValue = request.getParameter("add_value");
	String isfun = request.getParameter("isfun");
	String userLx = request.getParameter("userLx");
	String selFname = tc.addFname(addZd,addValue);
	selFname = selFname.equals(" ")?selFname:" and "+selFname+"'";
	int leve_num = Integer.parseInt(leve);
	String fname="",zid="",ff_name="",jianjie = "",wh_link="",leves="",ff_id = "",zbh="",app_link="";
	int s_size = 0;
	if (f_id != "" && f_id != null) {
		String[][] treeArr = ztc.get_zd_valueStr1("zname,zid,fname,introduce,wh_link,leve,fid,zbh,app_link",treeTable,"where zid='" + f_id + "' and userLx='"+userLx+"' "+selFname+"","查询树sql");
		
		String sql = "select zname,zid,fname,introduce,wh_link,leve,fid,zbh,app_link from "+treeTable+" where zid='" + f_id + "' and userLx='"+userLx+" "+selFname+"";
		
		Object[] obj = (Object[]) treeArr[0];
		fname = obj[0].toString();
		zid = obj[1].toString();
		ff_name = obj[2].toString();
		jianjie = obj[3].toString();
		leves = obj[5].toString();//leves值，加子目录时leves加1
		ff_id = obj[6].toString();
		zbh = obj[7].toString();
		app_link = obj[8].toString();
		ff_name = tc.getNewFname(ff_id,treeTable);
		jianjie = jianjie.equals("") || jianjie.equals(null)?"无":jianjie;
		s_size = ztc.get_Zd_Num1("id",treeTable," where fid='" + zid + "' and userLx='"+userLx+"'","查询字段数目");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>节点维护</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript">
		function add_node(){//增加
			document.getElementById("fjdtr1").style.display="table-row";   
			document.getElementById("fjdtr2").style.display="none";
			document.getElementById("btn_hidd").style.display="table-row";	
			document.getElementById("set_gn_tr").style.display="none";	
			document.getElementById("url_tr").style.display="none";	
			document.getElementById("znode").disabled="";	
			document.getElementById("jj_node").disabled="";	
			document.getElementById("fnode").value=document.getElementById("znode").value;
			document.getElementById("znode").value="";
			document.getElementById("jj_node").value="";
			document.getElementById("bs").value="add";	
		}
		
		function del_node(){//删除
			document.getElementById("znode").disabled="disabled";	
			document.getElementById("jj_node").disabled="disabled";	
			document.getElementById("set_gn_tr").style.display="none";	
			document.getElementById("url_tr").style.display="none";	
			document.getElementById("bs").value="del";	
			var s_size=document.getElementById("s_size").value;
			if(parseInt(s_size)>0){//有父
				if(confirm("非末级节点，确认【删除】？")){
					form1.submit();
				}else{
					return;
				}
			}else{//无父
				form1.submit();
			}	
		}
		
		function mod_node(){//修改
			document.getElementById("fjdtr2").style.display="table-row";
			document.getElementById("fjdtr1").style.display="none";
			document.getElementById("btn_hidd").style.display="table-row";
			document.getElementById("set_gn_tr").style.display="none";	
			document.getElementById("url_tr").style.display="none";	
			document.getElementById("znode").disabled="";	
			document.getElementById("znode").value="<%=fname %>";	
			document.getElementById("jj_node").disabled="";	
			document.getElementById("jj_node").value="<%=jianjie %>";
			document.getElementById("bs").value="mod";	
		}
		
		function set_gn(){
			document.getElementById("znode").disabled="disabled";
			document.getElementById("fnode").disabled="disabled";
			document.getElementById("app_link").disabled="";
			document.getElementById("set_gn_tr").style.display="table-row";
			document.getElementById("url_tr").style.display="table-row";
			document.getElementById("btn_hidd").style.display="table-row";
			document.getElementById("bs").value="set_gn";
		}
		
		function submit_in(){
			var znode = document.getElementById("znode").value;
			var jj_node = document.getElementById("jj_node").value;
			if(znode!=""&&jj_node!=""){
				form1.submit();
			}
		}
		
		function get_gn_url(){
			var set_gn_type = document.getElementById("set_gn_type").value;
			if(set_gn_type=="1"){
				//mod by wxy
				var reValue = window.showModalDialog("<%=basePath %>function/function_list.jsp?bs=0", "_blank", "center:yes;dialogWidth:600px;dialogHeight:350px;scroll:yes");
				document.getElementById("app_link").value = reValue;
				
				
			}
		}
		</script>
		<link href="<%=basePath%>wh_tree/css/tb.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>css/2012.css" rel="stylesheet" type="text/css" />
	</head>
	<body class="body">
		<form action="servlet/MenuTreeServlet" method="post" name="form1" id="form1"><!-- 保存的方法 -->
			<fieldset class="fieldset1" style="width: 500px; height: 400px">
				<legend class="drop0">节点登记 </legend>
				<p style="text-align: center;" >
					<input type="button" name="btn1" id="btn1" value="增加节点" onclick="add_node()" />&nbsp;
					<input type="button" name="btn2" id="btn2" value="修改节点" onclick="mod_node()" />&nbsp;
					<input type="button" name="btn3" id="btn3" value="删除节点" onclick="del_node()" />&nbsp;
					<input type="button" name="btn3" id="btn3" value="绑定功能" onclick="set_gn()" />&nbsp; 
					<input type="hidden" name="bs" id="bs" value="" />
					<input type="hidden" name="fid" id="fid" value="<%=f_id%>" />
					<input type="hidden" name="leve" id="leve" value="<%=leves%>" />
					<input type="hidden" name="s_size" id="s_size" value="<%=s_size%>" />
					<input type="hidden" name="tree_table" id="tree_table" value="<%=treeTable%>" />
					<input type="hidden" name="add_zd" id="add_zd" value="<%=addZd%>" />
					<input type="hidden" name="add_value" id="add_value" value="<%=addValue%>" />
					<input type="hidden" name="isfun" id="isfun" value="<%=isfun%>" />
					<input type="hidden" name="userLx" id="userLx" value="<%=userLx%>" />
				</p>
				<br />
				<table width="410" height="180" border="1" class="i_table" style="margin-left: 40px; font-size: 13px;">
					<tr id="fjdtr1">
						<td>
							<label for="fnode">父节点名</label>
						</td>
						<td>
							<input type="text" name="fnode" id="fnode" value="<%=ff_name%>" disabled="disabled" height="30" size="40" />
						</td>
					</tr>
					<tr id="fjdtr2" style="display: none">
						<td>
							<label for="sel_fid">父节点名</label>
						</td>
						<td>
							<select id="sel_fid" name="sel_fid">
							<%
							String[][] updArr = ztc.get_zd_valueStr1("zid,zname",treeTable," where zbh not like '"+zbh+"%' and userLx='"+userLx+"' "+selFname+"","查询树字段信息");
							if(updArr.length>0){
								for(int i=0;i<updArr.length;i++){
									Object obj[] = (Object[])updArr[i];
									String key_zid = (String)obj[0];
									String value_zname = (String)obj[1];
									if(ff_id.equals(key_zid)){
										%>
										<option selected="selected" value="<%=key_zid %>"><%=value_zname %></option>
										<%
									}else{
										%>
										<option value="<%=key_zid %>"><%=value_zname %></option>
										<%
									}
									
								}
							}
							%>
								
							</select>
						</td>
					</tr>
					<tr>
						<td width="111">
							<label for="znode">当前节点名</label>
						</td>
						<td width="283">
							<input type="text" name="znode" id="znode" height="30" value="<%=fname%>" disabled="disabled" size="40" />
						</td>
					</tr>
					<tr id="set_gn_tr" style="display: none">
						<td>
							<label for="fnode">设置功能类型</label>
						</td>
						<td>
							<select id="set_gn_type" name="set_gn_type">
								<option selected="selected" value="0">手动录入URL</option>
								<option value="1">引入功能URL</option>
							</select>
						</td>
					</tr>
					<tr id="url_tr">
						<td width="111">
							<label for="app_link">关联功能URL</label>
						</td> 
						<td width="283">
							<input type="text" name="app_link" id="app_link" height="30" value="<%=app_link%>" onclick="get_gn_url()" disabled="disabled" size="40" />
						</td>
					</tr>
					<tr>
						<td height="72">
							<label for="jj_node">
								节点简介
							</label>
						</td>
						<td height="72">
							<textarea name="jj_node" id="jj_node" cols="33" disabled="disabled" rows="5"><%=jianjie %></textarea>
						</td>
					</tr>
					<tr id = "btn_hidd" align="center" style="display: none">
						<td colspan="2" align="center">
							<input style="width: 55" type="button" id="btn3" value="提交" onclick="submit_in()" />&nbsp;
							<input type="button" name="fh" id="fh" style="width: 55" value="返回" onclick="history.go(-1)"></input>
						</td>
					</tr>
				</table>
				<br><br><br>
				
			</fieldset>
		</form>
	</body>
</html>
