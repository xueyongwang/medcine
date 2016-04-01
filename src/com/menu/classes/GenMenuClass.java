package com.menu.classes;

import com.service.classes.ZnnToolsClass;

public class GenMenuClass {
	/**
	 * 生成菜单
	 * @param zd_name 0zid,1fid,2zname,3app_link,4app_target,5leve
	 * @param tbName
	 * @param whereTj
	 * @return
	 */
	public String genMenuStr(String zd_name,String tbName,String whereTj){
		ZnnToolsClass znn = new ZnnToolsClass();
		String[][] arr = znn.get_zd_valueStr1(zd_name,tbName,whereTj,"查询用户菜单");
		String[][] tempArr = new String[0][6];
		String re = "";
		int rc = 2;//标记开始
		for (int i = 0; i < arr.length; i++) {
			if(arr[i][1].equals("-1")){
				continue;
			}else if(rc==2){
				//放到临时数组
				tempArr = makNewArr(arr[i],tempArr);
				rc =0;
			}else if(!arr[i][5].equals("1")){//如果上一个标记标记自己不应该生成，并且自己也不为一级，如果为三级标签一样在二级中
				//放到临时数组
				tempArr = makNewArr(arr[i],tempArr);
			}else if(arr[i][5].equals("1")){//如果上一个标记标记自己不应该生成，并且自己也不为一级
				//生成临时数组中的数据，同时清空数据
				re+=outStr(tempArr);
				for (int j = 0; j < tempArr.length; j++) {
					for (int j2 = 0; j2 < tempArr[0].length; j2++) {
						System.out.print(tempArr[j][j2]+"\t");
					}
					System.out.println();
				}
				tempArr = new String[0][6];
				tempArr = makNewArr(arr[i],tempArr);
			}
		}
		return re;
	}
	
	/**
	 * 生成数组的内容
	 * @param tempArr
	 * @return
	 */
	private String outStr(String[][] tempArr) {
		// TODO Auto-generated method stub
		System.out.println("----");
		String sty_str = "fa-dashboard,fa-bar-chart-o,fa-laptop,fa-edit,fa-table,fa-calendar,fa-folder,fa-th";
		String re = "";
		if(tempArr.length==1){
			//如果长度为1
			re+="<li>" +
				"    <a href=\"index.html\" target = \"\">" +
				"        <i class=\"fa fa-dashboard\"></i><span>"+tempArr[0][2]+"</span>" +
				"    </a>" +
				"</li>";
		}else{
			re+="<li class=\"treeview\">" +
				"    <a href=\"#\">" +
				"        <i class=\"fa fa-dashboard\"></i><span>"+tempArr[0][2]+"</span>" +
				"        <i class=\"fa fa-angle-left pull-right\"></i>" +
				"    </a>" +
				"    <ul class=\"treeview-menu\">";
			for (int i = 1; i < tempArr.length; i++) {
				re+="<li><a href=\"\"><i class=\"fa fa-angle-double-right\"></i> "+tempArr[i][2]+"</a></li>";
			}
			re+="</ul></li>";
		}
		return re;
	}


	/**
	 * 将两个宽度相同的数组合成一个完整的长数组
	 * 
	 * @param oldArr1	一维数组 长度为另一个数组的宽度 
	 * @param oldArr2
	 * @return
	 */
	private String[][] makNewArr(String[] oldArr1, String[][] oldArr2) {
		
		int length = 1 + oldArr2.length;
		int width = oldArr1.length;
		String[][] newArr = new String[length][width];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				if (i > oldArr2.length-1) {
					newArr[i][j] = oldArr1[j];
					System.out.print(oldArr1[j]+"\t");
					
				} else {
					newArr[i][j] = oldArr2[i][j];
				}
				//System.out.println();
			}
		}
		return newArr;
	}
	
}
