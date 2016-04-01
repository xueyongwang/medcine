package com.menu.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.classes.Jdbc_ch;
import com.service.classes.ZnnToolsClass;

public class GnServlet extends HttpServlet {

	Jdbc_ch db = new Jdbc_ch();
	ZnnToolsClass ztc = new ZnnToolsClass();
	private static final long serialVersionUID = 8129498196037795151L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		List<String> list = new ArrayList<String>();
		String bs = request.getParameter("bs"); // 操作
		String url = request.getParameter("url");
		String re = "";
		Date date = new Date();
		SimpleDateFormat f1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String sysTime = f1.format(date);
		if (bs.equals("0")) { // 新增
			String menu_name = request.getParameter("mk_name");
			String dyHyperlink = request.getParameter("dyHyperlink");
			String introduce = request.getParameter("introduce");
			String menu_id = ztc.getId("id", "mk_dj");
			String sql = "insert into mk_dj (mk_id,name,lx,introduce,dyHyperlink,time) values('"
					+ menu_id
					+ "','"
					+ menu_name
					+ "','1','"
					+ introduce
					+ "','" + dyHyperlink + "','" + sysTime + "');";
			list.add(sql);
			try {
				re = db.useBatch(list);
			} catch (Exception e) {
				re = "0";
			}
		} else if (bs.equals("1")) { // 修改
			String menu_id = request.getParameter("mk_id");
			String menu_name = request.getParameter("mk_name");// menu_lx
			String dyHyperlink = request.getParameter("dyHyperlink");
			String introduce = request.getParameter("introduce");
			String sql = "update mk_dj set name='" + menu_name
					+ "',dyHyperlink='" + dyHyperlink + "',introduce='"
					+ introduce + "',time='" + sysTime + "' where mk_id='"
					+ menu_id + "';";
			list.add(sql);
			try {
				re = db.useBatch(list);
			} catch (Exception e) {
				re = "0";
			}
		} else if (bs.equals("2")) {// 删除
			String menu_id = request.getParameter("mk_id");
			String sql = "delete from mk_dj where mk_id='" + menu_id + "';";
			list.add(sql);
			try {
				re = db.useBatch(list);
			} catch (Exception e) {
				re = "0";
			}
		}else if(bs.equals("3")){//设置用户类型菜单
			String userLx = request.getParameter("userLx");
			String gnMenuName = request.getParameter("gnMenuName");
			String introduce = request.getParameter("introduce");
			String dq_id = ztc.getId("id", "fun_menu");//当前treeTable的ID
			String zd_nameStr ="id,zid,zname,userLx,fid,fname,zbh,fbh,leve,message,wh_link,app_link,wh_target,app_target,introduce";
			String zd_valueStr = ""+dq_id+",'fun_menu_"+dq_id+"','"+gnMenuName+"','"+userLx+"','-1','','01','-1','0','"+introduce+"','menuWhTree.jsp?leve=0&zid=fun_menu_"+dq_id+"&tree_table=fun_menu&add_zd=null&isfun=0&add_value=null&userLx="+userLx+"','','windowspp','window1','"+introduce+"'";
				re = ztc.insertDataSql(zd_nameStr, zd_valueStr, "fun_menu", "添加用户功能菜单树的根节点");
				ztc.updateDataSql("isSet='1'", "userlx", " where userLxBh='"+userLx+"'", "更新用户类型表单");
		}else if(bs.equals("4")){//设置合作企业的组织结构根目录
			String companyName = request.getParameter("companyName");//企业名称
			String gnMenuName = request.getParameter("gnMenuName");//组织结构名称
			String introduce = request.getParameter("introduce");//组织结构简介
			String dq_id = ztc.getId("id", "user_zzjg_tree");//当前treeTable的ID
			String newcompanyName = URLEncoder.encode(URLEncoder.encode(companyName,"utf-8"),"utf-8");
			String zd_nameStr ="id,zid,zname,companyName,fid,fname,zbh,fbh,leve,message,wh_link,app_link,wh_target,app_target,introduce";
			String zd_valueStr = ""+dq_id+",'user_zzjg_tree_"+dq_id+"','"+gnMenuName+"','"+companyName+"','-1','','01','-1','0','"+introduce+"','companyWhTree.jsp?leve=0&zid=user_zzjg_tree_"+dq_id+"&tree_table=user_zzjg_tree&add_zd=null&isfun=0&add_value=null&companyName="+newcompanyName+"','','windowspp','window1','"+introduce+"'";
				re = ztc.insertDataSql(zd_nameStr, zd_valueStr, "user_zzjg_tree", "添加组织结构菜单树的根节点");
				ztc.updateDataSql("isSet='1'", "cooperation", " where cooName='"+companyName+"'", "更新合作企业的设置类型");
		}
		if (re.equals("1")) {
			out.println("<script>" + " alert('设置成功！');" + " var url='" + url
					+ "';" + " window.location=url" + " </script>");
		} else {
			out.println("<script>" + " alert('设置失败,请重新设置！');" + " var url='"
					+ url + "';" + " window.location=url" + "</script>");
		}
	}


}
