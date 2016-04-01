package com.menu.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.classes.Jdbc_ch;
import com.service.classes.TreeClass;
import com.menu.classes.MakTreeBh;
import com.service.classes.ZnnToolsClass;

public class MenuTreeServlet extends HttpServlet {
	
	
	
	private static final long serialVersionUID = -2176517569905530330L;
	
	ZnnToolsClass asu = new ZnnToolsClass();
	TreeClass tc = new TreeClass();
	Jdbc_ch db = new Jdbc_ch();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	
	String zdKey = "";//增加的字段名
	String zdValue = "";//对应的值
	
	String bs = request.getParameter("bs");//操作
	String isfun = request.getParameter("isfun");//是否有跳转的js方法  0：无 1：有
	String treeTable = request.getParameter("tree_table");//维护哪棵table树
	String addZd = request.getParameter("add_zd");//tree要增加的字段名，以','隔开，串末尾不能有','
	String addValue = request.getParameter("add_value");//tree要增加的字段值，以','隔开，串末尾不能有','
	String fid = request.getParameter("fid");
	String znode = request.getParameter("znode");
	String jj_node = request.getParameter("jj_node");//简介
	String sel_fid = request.getParameter("sel_fid");
	String userLx = request.getParameter("userLx");//用户类型
	System.out.println("userLx===="+userLx);
	
	
	if(bs.equals("add")){
		String selFname = tc.addFname(addZd, addValue);
//		TODO;赵年年---add--userLx;同下
		selFname = selFname.equals(" ")?selFname:" and "+selFname+"";//赵年年-add-15-05-15
		String dq_id = asu.getId("id", treeTable);//当前treeTable的ID
		int leve = Integer.parseInt(request.getParameter("leve")) + 1;//树的级别
		if(!addZd.equals("null")&&!addValue.equals("null")){
			String zdArr[] = addZd.split(",");
			for (int i = 0; i < zdArr.length; i++){
				zdKey += ","+zdArr[i];
				zdValue += ",'"+addValue+"'";
			}
		}
		String sql = "select zid,zname,message from "+treeTable+" where zid='"+fid+"' and userLx='"+userLx+"' "+selFname+"";
		List<?> rsList = db.query(sql);
		if(rsList.size()>0){
			Object []obj = (Object[])rsList.get(0);
			String zname = (String)obj[1];
			String message = (String)obj[2];
			String zt_message = message + "-->" + znode;
			String add_sql = "";
			if(isfun.equals("1")){
				add_sql = "insert into "+treeTable+" (id,zid,zname,userLx,fid,fname,zbh,fbh,leve,message,wh_link,app_link,wh_target,app_target,introduce"+zdKey+") " +
					"values ("+dq_id+",'"+treeTable+"_"+dq_id+"','"+znode+"','"+userLx+"','"+fid+"','"+zname+"','','','"+leve+"','"+zt_message+"','menuWhTree.jsp?leve="+leve+"&zid="+treeTable+"_"+dq_id+"&tree_table="+treeTable+"&add_zd="+addZd+"&isfun="+isfun+"&add_value="+addValue+"&userLx="+userLx+"','javascript:"+treeTable+"(\\\\\\'"+dq_id+"\\\\\\')','windowspp','window1','"+jj_node+"' "+zdValue+")";
			}else if(isfun.equals("0")){
				add_sql = "insert into "+treeTable+" (id,zid,zname,userLx,fid,fname,zbh,fbh,leve,message,wh_link,app_link,wh_target,app_target,introduce"+zdKey+") " +
					"values ("+dq_id+",'"+treeTable+"_"+dq_id+"','"+znode+"','"+userLx+"','"+fid+"','"+zname+"','','','"+leve+"','"+zt_message+"','menuWhTree.jsp?leve="+leve+"&zid="+treeTable+"_"+dq_id+"&tree_table="+treeTable+"&add_zd="+addZd+"&isfun="+isfun+"&add_value="+addValue+"&userLx="+userLx+"','','windowspp','window1','"+jj_node+"' "+zdValue+")";
			}
			System.out.println("add_sql===="+add_sql);
			System.err.println(add_sql);
			Jdbc_ch.useSQL(add_sql);
			String re = updBhMsg("zid,zname,fid,leve,zbh,fbh",treeTable,selFname,userLx);
			System.out.println("re===000000000000="+re);
			if(re.equals("1")){
				viewMsg("添加成功!", out);
			}
		}
	}else if(bs.equals("del")){
		String selFname = tc.addFname(addZd, addValue);
		selFname = selFname.equals(" ")?selFname:" and "+selFname;
		String fbh = "";
		String fbh_sql = "select zbh from "+treeTable+" where zid = '"+fid+"' and userLx='"+userLx+"'";
		System.out.println("fbh_sql=="+fbh_sql);
		List<?> list = db.query(fbh_sql);
		if(list.size()>0&&list!=null){
			Object []obj = (Object[])list.get(0);
			fbh = (String)obj[0];
		}
		String del_sql = "delete from "+treeTable+" where zbh like '"+fbh+"%' and userLx='"+userLx+"' "+selFname+"";
		System.out.println("fbh_sql=="+del_sql);
		Jdbc_ch.useSQL(del_sql);
		String re = updBhMsg("zid,zname,fid,leve,zbh,fbh",treeTable,selFname,userLx);
		if(re.equals("1")){
			out.println("<script>" + "alert('删除成功!');parent.main.location.reload(true);" + "</script>");
		}
	}else if(bs.equals("mod")){
		String mod_sql = "update "+treeTable+" set zname = '"+znode+"',introduce = '"+jj_node+"',fid='"+sel_fid+"' where zid ='"+fid+"' and userLx='"+userLx+"'";
		Jdbc_ch.useSQL(mod_sql);
		out.println("<script>" + "alert('修改成功!');parent.main.location.reload(true);" + "</script>");
	}else if(bs.equals("show")){
		String id = request.getParameter("id");
		List<?> mkList = db.query("select zid,zname from "+treeTable+" where Id = "+id+" and userLx='"+userLx+"';");
		String mkId = "",mkName="";
		if(mkList.size()>0&&mkList!=null){
			Object mkObj[] = (Object[])mkList.get(0);
			mkId = (String)mkObj[0];
			mkName = (String)mkObj[1];
		}
		out.write(mkId+"@"+mkName);
	}else if(bs.equals("set_gn")){
		String appLink = request.getParameter("app_link");
		String set_sql = "update "+treeTable+" set app_link = '"+appLink+"' where zid ='"+fid+"' and userLx='"+userLx+"'";
		Jdbc_ch.useSQL(set_sql);
		out.println("<script>" + "alert('功能绑定成功!');parent.main.location.reload(true);" + "</script>");
		
	}
	out.flush();
	out.close();
	}
	
	/**
	 * 更新树中zbh,fbh信息
	 * @param zd
	 * @param tb
	 * @param selFname
	 * @return
	 */
	public String updBhMsg(String zd,String tb,String selFname,String userLx){
	MakTreeBh mt = new MakTreeBh();
	userLx = " and userLx="+userLx;
	String sql = "";
	String[][] bh_deal = mt.dealTask(zd,tb,selFname,userLx);
	List<String> list = new ArrayList<String>();
	for(int i=0;i<bh_deal.length;i++)
	{
	    sql = "update "+tb+" set zbh='"+bh_deal[i][1]+"',fbh='"+bh_deal[i][0]+"' where zid='"+bh_deal[i][4]+"' "+userLx+"";
		list.add(sql);
	}
	System.out.println("sql===---->"+sql);
	String re = db.useBatch(list);
	return re;
	}
	
	private void viewMsg(String str, PrintWriter out) {
	String alertMsg = "";
	if (str.length() > 1) {
		alertMsg = "alert('" + str + "');";
	}
	out.println("<script>" + alertMsg + "var url =document.referrer;"
			+ "window.location=url; "
			+ "parent.mkgxMain.location.reload(true);" + "</script>");
	}
}
