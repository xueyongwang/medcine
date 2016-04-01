package com.menu.classes;

import java.util.List;

import com.service.classes.Jdbc_ch;


public class MakTreeBh {
	
	Jdbc_ch db = new Jdbc_ch();
	
	//这个class服务于树
	//获取类别树，分配用户所能操作的类别
	public String[][] get_lbTree(String zdMesg,String tbName,String selFname,String userLx){
		String sql = "select "+zdMesg+" from "+tbName+" where zid!='-1' "+selFname+userLx+" order by Id;";
		System.out.println("sql==="+sql);
		List<?> list = db.query(sql);
		if(list.size()>0){
			String[][] value= new String[list.size()][6];
			for(int i=0;i<list.size();i++){
				Object[] obj = (Object[]) list.get(i);
				value[i][0] = (String) obj[0];
				value[i][1] = (String) obj[1];
				value[i][2] = (String) obj[2];
				value[i][3] = (String) obj[3];
				value[i][4] = (String) obj[4];
				value[i][5] = (String) obj[5];
			}
			return value;
		}else{
			return null;
		}
	}
	
	public String[][] mak_newArr(String zdMesg,String tbName,String selFname,String userLx){
		String[][] value = get_lbTree(zdMesg,tbName,selFname,userLx);
		int raws = value.length ;
		String[][] newTask = new String[raws][6];
		for(int i=0;i<newTask.length;i++){
			if(i==0){  //处理根
				newTask[0][0] = "-1";               //zbh
				newTask[0][1] = "01";             //zbh
				newTask[0][2] = "1";
				newTask[0][3] = "0";
				newTask[0][4] = (String) value[i][0];     //z_id
				newTask[0][5] =  (String) value[i][2];    //f_id
			}else{
				newTask[i][0] = (String) value[i][4];
				newTask[i][1] = (String) value[i][5];
				newTask[i][2] = "0";
				newTask[i][3] = "0";
				newTask[i][4] = (String) value[i][0];
				newTask[i][5] =  (String) value[i][2];    
			}
			
	}
	return newTask;
	}
	
	/*
	 * 遍历数组
	 */
	public String[][] dealTask(String zdMesg,String tbName,String selFname,String userLx){
//		if(!userLx.equals("")){
//			userLx = " and userLx="+userLx;
//		}
		String[][] newTask = mak_newArr(zdMesg,tbName,selFname,userLx);
		int jls = newTask.length - 1;   //记录数
		int ds = 1;                     //已处理记录数
		while(ds<jls){
			int raw = 0;
			for(raw=0;raw<=jls;raw++){   
				if(newTask[raw][2].equals("0")){
					String re = search_f(raw,newTask,newTask[raw][5]);
					if(re.equals("")){
						continue;
					}
					newTask[raw][2] = "1";
					newTask[raw][0] = re.split("@!@")[1];   //f_bh
					newTask[raw][1] = re.split("@!@")[0];   //z_bh
					ds ++;
					
				}
			}
		}
		return newTask;
	}
	
	
	public void bl_arr(String[][] task){
		for(int i=0;i<task.length;i++){
			for(int j=0;j<task[0].length;j++){
				System.out.print(task[i][j]+"\t");
			}
			System.out.print("\n");
		}
	}
	
	/*
	 * 找父
	 */
	public String search_f(int raw,String[][] task,String f_id){
		String re = "";
		int f_yw = 0;
		int f_raw = 0;
		for(int i=0;i<raw;i++){
			if(task[i][4].equals(f_id)){
				if(task[i][2].equals("1")){
					f_raw = i;
					f_yw = 1;
				}
			}
		}
		if(f_yw == 0){
			for(int j=raw;j<task.length;j++){
				if(task[j][4].equals(f_id)){
					if(task[j][2].equals("1")){
						f_yw = 1;
						f_raw = j;
					}
				}
			}
		}
		if(f_yw == 1){
			int dealzs = Integer.parseInt(task[f_raw][3]) + 1;
			task[f_raw][3] = ""+dealzs+"";
			if(task[f_raw][0].equals("")){
				re = mak_bh(task[f_raw][1],task[f_raw][3])+"@!@"+task[f_raw][1];
			}else{
				re = mak_bh(task[f_raw][1],task[f_raw][3])+"@!@"+task[f_raw][1];
			}
			
		}
		return re;
	}
	
	public String mak_bh(String fbh,String index){
		String re = "";
		re += fbh;
		String bw = "";
		for(int i=0;i<(2-index.length());i++){
			bw += "0";
		}
		re += bw+index;
		return re;
	}

}
