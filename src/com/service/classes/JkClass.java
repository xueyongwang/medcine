package com.service.classes;

import java.util.List;


public class JkClass {
	Jdbc_ch Jdbc_ch = new Jdbc_ch();
	ZnnToolsClass ztc=new ZnnToolsClass();
	//jdbh,zmk_name,zmk_id,level,len
	public Object[][] makTreeObj(List list){
		Object[][] treeObj  = new Object[list.size()][5];
		for(int i=0;i<list.size();i++){
			Object[] obj = (Object[]) list.get(i);
			for(int j=0;j<obj.length;j++){
				treeObj[i][j] = obj[j];
			}
		}
		return treeObj ;
	}

	public int[][] tdCountRange(Object[][] treeObj,int n){
		int[][] obj = new int [99][3] ;
		int objCount = 0 ;
		for(int i=0;i<treeObj.length;i++){
			int level = Integer.parseInt((treeObj[i][3].toString()));
			if(level == n){
				obj[objCount][0] = i ;
				int len = Integer.parseInt(treeObj[i][4].toString());//节点长度
				String jdbh = (String) treeObj[i][0];
				obj[objCount][1] = Integer.parseInt(getRange(treeObj,i,len,jdbh).split("!")[0]); //范围
				obj[objCount][2] = Integer.parseInt(getRange(treeObj,i,len,jdbh).split("!")[1]);//级别
				objCount ++ ;
			}else{
				continue;
			}
		}
		return obj ;
	}
	
	private String getRange(Object[][] treeObj,int i,int len,String fjdbh){
		int range = 0 ;
		int leve = Integer.parseInt(treeObj[i][3].toString());
		if(i+1 == treeObj.length){
			
		}else{
			for(int j=i+1;j<treeObj.length;j++){
				int zlen = Integer.parseInt(treeObj[j][4].toString());
				if(zlen<=len){
					break ;
				}else{
					String zjdbh = (String) treeObj[j][0];
					int level = Integer.parseInt(treeObj[j][3].toString());
					zjdbh = zjdbh.substring(0,len);
					if(zjdbh.equals(fjdbh)){
						range ++ ;
						if(leve<level){
							leve = level ;
						}
					}else{
						break;
					}
				}
			}
		}
		range += 1 ;
		return Integer.toString(range) +"!" + Integer.toString(leve);
	}
	
	/**
	 * 合成类型选择按钮
	 * @param basePath
	 * @param lxOrName
	 * @param tableName
	 * @return
	 */
	public String makLevelMsg(String basePath,String lxOrName,String tableName){
		String levelStr = "" ,ssdg1 = "" ;
		String[][]  firstLevelArr = ztc.get_zd_valueStr1(lxOrName, tableName, " where fid = '-1' ", "查询"+tableName+"数据");
//		String[][] firstLevelArr = mak_firstLevelArr(lxOrName,tableName);
		if(firstLevelArr == null){
			levelStr = "noMess" ;
		}else{
			ssdg1 = firstLevelArr[0][0];
			System.out.println("ssdg1==="+ssdg1);
			for(int i=0;i<firstLevelArr.length;i++)
			{
				String userLx = firstLevelArr[i][0];
				String zname = firstLevelArr[i][1];
				levelStr += "<input type='button' name='userLx' class=\"btn1_mouseout\" id='userLx"+i+"' value='"+zname+"' onclick=\"changeDg('"+userLx+"','"+basePath+"')\"/>" ;
			}
			levelStr = ssdg1 + "@#" + levelStr ;
		}
		System.out.println("levelStr===="+levelStr);
		return levelStr ;
	} 
	
	private String[][] mak_firstLevelArr(String lxOrName, String tableName){
		String sql = "select distinct '"+lxOrName+"' userLx,zname from '"+tableName+"' where fid = '-1'" ;
		List list = Jdbc_ch.query(sql);
		if(list.size()>0){
			String[][] levelArr = new String[list.size()][2] ;
			for(int i=0;i<list.size();i++){
				Object[] obj = (Object[]) list.get(i);
				levelArr[i][0] = (String) obj[0] ;
				levelArr[i][1] = (String) obj[1] ;
			}
			return levelArr ;
		}else{
			return null ;
		}
	}
	
	public String  mak_gnStr(){
		String gnStr = "" ;
		String sql = "select id,dyHyperlink,introduce,gnName from mk_dj " ;
		List list = Jdbc_ch.query(sql) ;
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] obj = (Object[]) list.get(i);
				String introduce = (String)obj[2] ;
				if(introduce == null || introduce.equals("")){
					introduce = "无" ;
				}
				String str_css = "style=\"color:blue;\"" ;
				if(i%2 == 0){str_css = "style=\"color:purple;\"" ;}
					
				String chxValue = (String)obj[0]+"@!"+(String)obj[1]+"@!"+introduce;
				gnStr += "<tr "+str_css+"><td>" +
							"<input type='checkbox' name='gnchx' value='"+chxValue+"'/>" +
							"&nbsp;&nbsp;&nbsp;" + (String)obj[3] +
						 "</td></tr>" ;
			}
			gnStr = "<table border='1' cellpadding='0' cellspacing='0' style='width:100%;font-size:13px;'>" +gnStr+ "</table>" +
					"<div><input style='width:50' type='button' name='selBack' id='selBack' value='提交' onclick=\"reBack('1')\"/>" +
						 "<input style='width:50' type='button' name='nullBack' id='nullBack' value='返回' onclick=\"reBack('0')\"/></div>" ;
		}else{
			gnStr = "没有可选功能" ;
		}
		
		return gnStr ;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
