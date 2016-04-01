package com.service.classes;

import java.util.List;



public class TreeClass {
	Jdbc_ch db = new Jdbc_ch();
	public String getBuidingTreeString(String sql, String dtreeName) {
		System.out.println("sql==="+sql);
		List<?> tablelzList = db.query(sql);
		Object[] object = new Object[5];
		if (tablelzList == null || tablelzList.size() == 0) {
			return "";
		}
		StringBuffer contents = new StringBuffer();
		contents.append(" new dTree('" + dtreeName + "');");
		for (int i = 0; i < tablelzList.size(); i++) {
			object = (Object[]) tablelzList.get(i);
			String menuId = (String) object[0];
			String menuName = (String) object[2];
			contents.append("\n");
			contents.append(dtreeName + ".add('");
			contents.append(menuId);
			contents.append("','");
			contents.append((String)object[1]);
			contents.append("','");
			contents.append((String)menuName);
			contents.append("','");
			contents.append((String)object[3]);
			contents.append("','','"+(String)object[4]+"','','');");
			contents.append("\n");
			//System.out.println(contents.toString());
		}
		
		return contents.toString();
	}

	public String getNewFname(String fid,String treeTable){
		
    	String newFname = "";
    	String sql = "select zname from "+treeTable+" where zid = '"+fid+"'";
    	List<?> list = db.query(sql);
    	if(list!=null&&list.size()>0){
    		Object []obj = (Object[])list.get(0);
    		newFname = (String)obj[0];
    	}
    	return newFname;
    }
	
	public String addFname(String addZd,String addValue){
		String selFname = " ";
		System.out.println("addZd==="+addZd+"===addValue==="+addValue);
		if(!addZd.equals("null")&&!addValue.equals("null")){
			String addZdArr[] = addZd.split(",");
			String addValueArr[] = addValue.split(",");
			for(int i=0;i<addZdArr.length;i++){
				selFname += " "+addZdArr[i]+" = '"+addValueArr[i]+"' and";
			}
		}
		selFname = selFname.endsWith("and")?selFname.substring(0,selFname.length()-3):selFname;
		return selFname;
	}
	//数据库中待方法  ---设置有复选框
	public String getBuidingTreeString1(String sql, String dtreeName) {
		System.out.println("getBuidingTreeString1方法"+sql);
		Jdbc_ch jdbc_ch = new Jdbc_ch();
		List tablelzList = jdbc_ch.query(sql);
		Object[] object = new Object[5];
		if (tablelzList == null || tablelzList.size() == 0) {
			
			return "new dTree('" + dtreeName + "');";
		}
		System.out.println("sql----111"+sql);
		//sstr="select menuId,menupid,menuName,dyHyperlink1,title,target,icon,iconopen,isopen from mkgxdjmk 
		StringBuffer contents = new StringBuffer();
		contents.append(" new dTree('" + dtreeName + "',true);");//设置有复选框
		for (int i = 0; i < tablelzList.size(); i++) {
			object = (Object[]) tablelzList.get(i);
			String menuId = (String) object[0];
			String menuName = (String) object[2];
			contents.append("\n");
			contents.append(dtreeName + ".add('");
			contents.append(menuId);
			contents.append("','");
			contents.append((String)object[1]);
			contents.append("','");
			contents.append((String)menuName);
			contents.append("','");
			contents.append((String)object[3]);
			contents.append("','','"+(String)object[4]+"','','');");
			contents.append("\n");
			System.out.println(contents.toString());
		}
		System.out.println("contents===="+contents.toString());
		return contents.toString();
	}
	//数据库中待方法  无复选框
	public String getBuidingTreeString2(String sql, String dtreeName) {
	//	sql="select zmk_id,fmk_id,zmk_name,dyHyperlink2,title,target,icon,iconopen,isOpen from fljbdata";
	System.out.println("getBuidingTreeString2方法====="+sql);
		Jdbc_ch jdbc_ch = new Jdbc_ch();
		List tablelzList = jdbc_ch.query(sql);
		Object[] object = new Object[9];
		if (tablelzList == null || tablelzList.size() == 0) {
			return "new dTree('" + dtreeName + "');";
		}
		//sstr="select menuId,menupid,menuName,dyHyperlink1,title,target,icon,iconopen,isopen from mkgxdjmk 
		StringBuffer contents = new StringBuffer();
		contents.append(" new dTree('" + dtreeName + "',false);");//无复选框
		for (int i = 0; i < tablelzList.size(); i++) {
			object = (Object[]) tablelzList.get(i);
			String menuId = (String) object[0];
			String menuName = (String) object[2];
			contents.append("\n");
			contents.append(dtreeName + ".add('");
			contents.append(menuId);
			contents.append("','");
			contents.append(object[1]);
			contents.append("','");
			contents.append(menuName);
			contents.append("','");
			
			contents.append(object[3]);
			//<a href ="javascript:alert("111")"></a>;
			//contents.append("javascript:a(1);");
			
			contents.append("','");
			contents.append(object[4]);
			contents.append("','");
			
			//contents.append(object[5]);
			contents.append("");
			
			contents.append("','");
			contents.append(object[6]);
			contents.append("','");
			contents.append(object[7]);
			contents.append("','");
			contents.append(object[8]);
			contents.append("');");
			contents.append("\n");
		}
		//System.out.println("contents===="+contents.toString());
		return contents.toString();
	}
	
}
