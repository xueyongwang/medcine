package com.menu.classes;

import com.service.classes.ZnnToolsClass;

/**
 * 对应于用户菜单权限
 * @author Administrator
 *
 */
public class UserClass {

	ZnnToolsClass ztc = new ZnnToolsClass();
	/**
	 * 返回登录参数
	 * */
	public int getLoginParam(String username,String password){
		int index = -1;
		String psd = ztc.get_zd_value("password", "user", "where username='"+username+"'","查询用户密码");
		if(!psd.equals(null)){
				if(psd.equals(password)){
					index = 1;
				}else{
					index = 2;
				}
		}else{
			index = 3;
		}
		return index;
	}
	/**
	 * 合成用户类型下拉框
	 */
	public String getUserLxSel(){
		String re = "";
		String[][] userLxArr = ztc.get_zd_valueStr1("userLxBh,userLxName", "userlx", " where isSet='0'","查询用户类型");
		re+="<select name=\"userLx\" id=\"userLx\">\n";
		re+="<option value=\"null\">==请选择用户类型==</option>\n";
		for(int i=0;i<userLxArr.length;i++)
		{
			re+="<option value=\""+userLxArr[i][0]+"\">"+userLxArr[i][1]+"</option>\n";
		}
		re+="</select>\n";
		return re;
	}
	/**
	 * 合成合作企业类型下拉框
	 */
	public String getCompanyNameSel(String isSet,String isFun){
		String re = "",isFunStr="";
		String[][] userLxArr = ztc.get_zd_valueStr1("cooName,isSet", "cooperation", " where isSet='"+isSet+"'","查询合作企业");
//		System.out.println("uer---------"+userLxArr.length);
		if(!isFun.equals("")){
			isFunStr = "onblur=get_DwValue()";
		}
		re+="<select name=\"companyName\" id=\"companyName\" "+isFunStr+">\n";
		re+="<option value=\"null\">==请选择合作企业==</option>\n";
		if(userLxArr.length>0){
			for(int i=0;i<userLxArr.length;i++)
			{
				re+="<option value=\""+userLxArr[i][0]+"\">"+userLxArr[i][0]+"</option>\n";
			}
		}
		re+="</select>\n";
		return re;
	}
	/**
	 * 合成用戶類型串兒
	 * @return
	 */
	public String getUserLx(){
		String userLxStr = "";
		String[][] userLxArr = ztc.get_zd_valueStr1("userLxName,userLxBh", "userlx", "","查询用戶類型");
		if(userLxArr.length>0){
			for(int i=0;i<userLxArr.length;i++){
				Object[] obj = userLxArr[i];
				String userLxName=(String)obj[0];
				String userlx=(String)obj[1];
				userLxStr += "<input type=\"radio\" name=\"act\" onblur=\"get_act(this)\"  value=\""+userlx+"\"/>"+""+userLxName+"";
			}
		}
		return userLxStr;
	}
	/**
	 * 合成合作单位组织结构下来框
	 * @param companyName
	 * @return
	 */
	public String get_ssDwZzjgStr(String companyName){
		String zzjgStr = "";
		String zzjgArr[][] = ztc.get_zd_valueStr1("zname,zid", "user_zzjg_tree", "where companyName='"+companyName+"' and fid!='-1' and leve=1", "查询组织结构树");
		System.out.println("uer---------"+zzjgArr.length);
		
		if(zzjgArr.length>0){
			zzjgStr+="<select name=\"userLx\" id=\"userLx\">\n";
			zzjgStr+="<option>==请选择用户类型==</option>\n";
			for(int i=0;i<zzjgArr.length;i++)
			{
				zzjgStr+="<option value=\""+zzjgArr[i][1]+"\">"+zzjgArr[i][0]+"</option>\n";
			}
		}else{
			zzjgStr+="<select name=\"userLx\" id=\"userLx\"  disabled=\"disabled\">\n";
			zzjgStr+="<option>==还未设置该企业的组织类型==</option>\n";
		}
		zzjgStr+="</select>\n";		
		return zzjgStr;
	}
	/**
	 * 
	 * @return
	 */
	public String get_userMessStr(String zdName)
	{
		String userShowMessStr = "";
		if(zdName.equals(" ")){
			userShowMessStr = "<font color=\"red\">暂无</font>";
		}else{
			userShowMessStr = zdName;
		}
		return userShowMessStr;
	}
	
	/**
	 * 合成用户个人中心菜单树
	 * @param userLx
	 * @return
	 */
	public String mak_userMenuTree(String userLx,String basePath,String tbName){
		String mainPageMenuStr = "",url="";
		String wheretj = " where fid!='-1' and leve='1'";
		if(!userLx.equals("1")){//非商户
			wheretj += " and zbh!='0104'";
		}
		String zdValStr = "zname,zid,fname,fid,app_link,leve";
		String[][] blockStr = ztc.get_zd_valueStr1(zdValStr,tbName,wheretj, "用户个人中心菜单树");
		
		for(int i=0;i<blockStr.length;i++)
		{
			String zname = blockStr[i][0];
			System.out.println("blockStr[i][4]=="+blockStr[i][4]);
			if(blockStr[i][4].equals("")||blockStr[i][4].equals("null"))
			{
				url = "javascript:void(0)";
			}else{
//				url = blockStr[i][4].split("ElectricBusinessPlatform")[1];
//				url = url.substring(1,url.length());
				url = blockStr[i][4];
				
			}
			mainPageMenuStr +="<li><a class=\"active\" href=\""+url+"\" target=\"window1\">"+zname+"</a>"+
					              "<ul style=\"display: none;\">" +
						           		conSecondMenu((String)blockStr[i][1],basePath,tbName)+ 
					              "</ul>"+
						      "</li>";
		}
		System.out.println("mainPageMenuStr===="+mainPageMenuStr);
		return mainPageMenuStr;
	}
	
	/**
	 * 服务于通用版的客服中心
	 * @param userLx
	 * @return
	 */
	public String mak_userMenuTree2(String userLx,String basePath,String tbName){
		
		String mainPageMenuStr = "";
		String wheretj = "";
		String zdValStr = "lb_id,mk_id,mk_name,dy_table";
		String[][] blockStr = ztc.get_zd_valueStr1(zdValStr,tbName,wheretj, "通用版用户中心");
		
		for(int i=0;i<blockStr.length;i++)
		{
			String zname = blockStr[i][2];
			mainPageMenuStr +="<li><a class=\"active\" href=\""+basePath+"service_mall/com_page/sszn/sszn.jsp?mk_id="+blockStr[i][3]+"\" target=\"window1\">"+zname+"</a></li>";
		}
		System.out.println("mainPageMenuStr===="+mainPageMenuStr);
		return mainPageMenuStr;
	}
	
	public String conSecondMenu(String fid,String basePath,String tbName) 
	{
		String SecondMenu="",url="";
		String wheretj = " where fid='"+fid+"' and leve='2'";
		String zdValStr = "zname,zid,fname,fid,app_link,leve";
		String[][] blockStr = ztc.get_zd_valueStr1(zdValStr, tbName,wheretj, "查询产品分类信息");	
		for(int i=0;i<blockStr.length;i++)
		{
//			String funtionName = blockStr[i][4].split(":")[1].replaceAll("\\\\", "");
			System.out.println("blockStr[i][4]=="+blockStr[i][4]);
			if(blockStr[i][4].equals("")||blockStr[i][4].equals("null"))
			{
				url = "javascript:void(0)";
			}else{
//				url = blockStr[i][4].split("ElectricBusinessPlatform")[1];
//				url = url.substring(1,url.length());
				url = blockStr[i][4];
				
			}
			SecondMenu +="<li><a href=\""+url+"\" target=\"window1\">"+blockStr[i][0]+"</a></li>";
		}
		return SecondMenu;
	}
}
