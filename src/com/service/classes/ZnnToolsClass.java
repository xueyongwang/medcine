package com.service.classes;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class ZnnToolsClass {

	S_Connect conn=new S_Connect();
	Jdbc_ch jdbc = new Jdbc_ch();
	/**
	 * 根据所给的字段名，数据库表名，以及所给的条件，获取对应的字段的值(单个字段查出的单个值)
	 * @param zd_name  要查的字段名
	 * @param tbName   查的字段对应的表名
	 * @param whereTj  查字段对应的条件
	 * @param whereTj  提示sql串儿
	 * @return
	 */
	public String get_zd_value(String zd_name,String tbName,String whereTj,String sqlTsStr){
		String zd_value = "";
		String sql = "select distinct "+zd_name+" from "+tbName+" "+whereTj+";" ;
		System.out.println("sql=="+sqlTsStr+"=="+sql);
		List list = conn.query(sql);
		if(list.size()>0)
		{
			Object[] obj = (Object[]) list.get(0) ;
			zd_value = (String) obj[0] ;
		}
		return 	zd_value;	
	}
	/**
	 * 根据所给的字段名，数据库表名，以及所给的条件，获取对应的字段的值(单个字段查出的单个值)
	 * @param zd_name  要查的字段名
	 * @param tbName   查的字段对应的表名
	 * @param whereTj  查字段对应的条件
	 * @param whereTj  提示sql串儿
	 * @return
	 */
	public String get_zd_value1(String zd_name,String tbName,String whereTj,String sqlTsStr){
		String zd_value = "";
		String sql = "select distinct "+zd_name+" from "+tbName+" "+whereTj+";" ;
		System.out.println("sql=="+sqlTsStr+"=="+sql);
		List list = conn.query(sql) ;
		if(list.size()>0)
		{
			Object[] obj = (Object[]) list.get(0) ;
			zd_value = (String) obj[0]+"/"+(String) obj[0];
		}
		return 	zd_value;	
	}
	/**
	 * 根据所给的字段名，数据库表名，以及所给的条件，获取对应的字段的值(单个字段查出的单个值)
	 * @param zd_name  要查的字段名
	 * @param tbName   查的字段对应的表名
	 * @param whereTj  查字段对应的条件
	 * @param whereTj  提示sql串儿
	 * @return
	 */
	public String get_zd_value2(String zd_name,String tbName,String whereTj,String sqlTsStr){
		String zd_value = "";
		String sql = "select distinct "+zd_name+" from "+tbName+" "+whereTj+";" ;
		System.out.println("sql=="+sqlTsStr+"=="+sql);
		List list = jdbc.query(sql) ;
		if(list.size()>0)
		{
			Object[] obj = (Object[]) list.get(0) ;
			zd_value = (String) obj[0];
		}
		return 	zd_value;	
	}

	/**
	 * 隔行换颜色
	 * @param num
	 * @return
	 */
	public String get_trColor(int num,String colorA,String colorB){
		String trColorStr = "";
		if(num%2==0){
			trColorStr = "style='background-color:"+colorA+"'";
		}else if(num%2==1){
			trColorStr = "style='background-color:"+colorB+"'";
		}
		return trColorStr;
	}
	/**
	 * 根据所给的字段名，数据库表名，以及所给的条件，获取对应的字段的值（单个字段查出的多个值）
	 * 返回     str ======'zd_value','zd_value','zd_value'的串儿
	 * @param zd_name  要查的字段名
	 * @param tbName   查的字段对应的表名
	 * @param whereTj  查字段对应的条件
	 * @param whereTj  提示sql串儿
	 * @return
	 */
	public String get_zd_valueStr(String zd_name,String tbName,String whereTj,String sqlTsStr){
		String zd_valueStr = "";
		String sql = "select "+zd_name+" from "+tbName+" "+whereTj+";" ;
		System.out.println("sql=="+sqlTsStr+"=="+sql);
		List list = conn.query(sql);
		if(list.size()>0)
		{
			for(int i=0;i<list.size();i++){
				Object[] obj = (Object[]) list.get(i) ;
				if(zd_valueStr.equals("")){
					zd_valueStr = "'"+(String) obj[0] +"'";
				}else{
					zd_valueStr += ",'"+(String) obj[0] +"'";
				}
			}
		}
		return 	zd_valueStr;	
	}
	/**
	 * 根据所给的字段名，数据库表名，以及所给的条件，将数据存入到二维数组中
	 * 返回     二维数组
	 * @param zd_name  要查的字段名
	 * @param tbName   查的字段对应的表名
	 * @param whereTj  查字段对应的条件
	 * @param whereTj  提示sql串儿,
	 * @return
	 */
	public String[][] get_zd_valueStr1(String zd_name,String tbName,String whereTj,String sqlTsStr){
		String sql = "select "+zd_name+" from "+tbName+" "+whereTj+";" ;
		System.out.println("sql=="+sqlTsStr+"=="+sql);
		List list = jdbc.query(sql);
		int zdLen = zd_name.split(",").length;
		String[][] obj = new String[list.size()][zdLen];
		if(list.size()>0)
		{
			for(int i=0;i<list.size();i++){
				Object[] obj1 = (Object[])list.get(i);
				for(int j=0;j<obj1.length;j++){
					obj[i][j] = (String)obj1[j];
				}
			}
		}
		return obj;
	}
	
	/**
	 * 根据所给的字段名串儿，数据库表名，以及所给的条件，获取对应的字段的值（多个字段查出的多个值--->一条记录）
	 * 返回     str ======zd_value+分隔符+zd_value+分隔符+zd_value的串儿
	 * @param zd_name  要查的字段名
	 * @param tbName   查的字段对应的表名
	 * @param whereTj  查字段对应的条件
	 * @param sqlTsStr 提示sql串儿
	 * @param fgf      分隔符   
	 * @return
	 */
	public String get_mutiZd_valueStr(String zd_nameStr,String tbName,String whereTj,String sqlTsStr,String fgf){
		String zd_valueStr = "";
		String sql = "select distinct "+zd_nameStr+" from "+tbName+" "+whereTj+";" ;
		System.out.println("sql=="+sqlTsStr+"=="+sql);
		List list = conn.query(sql);
		System.out.println("list.size()==="+list.size());
		if(list.size()>0)
		{
			Object[] obj = (Object[]) list.get(0) ;
			for(int i=0;i<obj.length;i++){
				if(i==0){
					zd_valueStr = ""+(String) obj[0] +" ";
				}else{
					zd_valueStr += fgf+(String) obj[i] +" ";
				}
			}
		}
		return 	zd_valueStr;	
	}
	/**
	 * 根据所给的字段名串儿，数据库表名，以及所给的条件，获取对应的字段的值（多个字段查出的多个值--->一条记录）
	 * 返回     re_arr一维数组
	 * @param zd_name  要查的字段名
	 * @param tbName   查的字段对应的表名
	 * @param whereTj  查字段对应的条件
	 * @param sqlTsStr 提示sql串儿
	 * @return
	 */
	public String[] get_mutiZd_valueStr(String zd_nameStr,String tbName,String whereTj,String sqlTsStr){
		int len = zd_nameStr.split(",").length;
		String[] re_arr = new String [len];
		String sql = "select distinct "+zd_nameStr+" from "+tbName+" "+whereTj+";" ;
		System.out.println("sql=="+sqlTsStr+"=="+sql);
		List list = conn.query(sql);
		if (list.size() > 0) {
			re_arr = (String[]) list.get(0);
		}else{
			return null;
		}
		return re_arr;
	}
	/**
	 * 根据所给的字段名串儿，数据库表名，以及所给的条件，获取对应的字段的值（多个字段查出的多个值--->多条记录）
	 * 返回  str ======例子：zd_value+fgf1+zd_value+fgf2+zd_value+fgf1+zd_value
	 * @param zd_name  要查的字段名(至少两个字段)
	 * @param tbName   查的字段对应的表名
	 * @param whereTj  查字段对应的条件
	 * @param sqlTsStr 提示sql串儿
	 * @param fgf1              一条记录的各个字段间的分割符
	 * @param fgf2             多条记录间的分割符  
	 * @return
	 */
	public String get_mutiZd_valueStr(String zd_nameStr,String tbName,String whereTj,String sqlTsStr,String fgf1,String fgf2){
		String zd_valueStr = "";
		String sql = "select distinct "+zd_nameStr+" from "+tbName+" "+whereTj+";" ;
		System.out.println("sql=="+sqlTsStr+"=="+sql);
		List<?> list = conn.query(sql);
		if(list.size()>0)
		{
			for(int i=0;i<list.size();i++){
				Object[] obj = (Object[]) list.get(i) ;
				for(int k=0;k<obj.length;k++){
					
					if(zd_valueStr.equals("")){
						zd_valueStr = ""+(String) obj[0] +"";
					}else if(i==0){
						zd_valueStr += fgf1+(String) obj[k] +"";
					}else if(i!=0&&k==obj.length-1){
						zd_valueStr += (String) obj[k];
					}else if(i!=0&&k!=obj.length-1){
						zd_valueStr += (String) obj[k] + fgf1 ;
					}
				}
				zd_valueStr += fgf2;
			}
			int len1 = fgf2.length();
			int len2 = zd_valueStr.length();
			int len3 = len2-len1;
			zd_valueStr = zd_valueStr.toString().substring(0,len3);
		}
		return 	zd_valueStr;	
	}
	/**
	 * 根据所给的字段名，数据库表名，以及所给的条件，获取对应的字段的最大值
	 * @param zd_name  要查的字段名
	 * @param tbName   查的字段对应的表名
	 * @param whereTj  查字段对应的条件
	 * @param sqlTsStr  提示sql串儿
	 * @return
	 */
	public String get_Max_value(String zd_name,String tbName,String whereTj,String sqlTsStr){
		String max_value = "";
		String sql = "select max("+zd_name+") as maxvalue from "+tbName+" "+whereTj+";" ;
		System.out.println("sql=="+sqlTsStr+"=="+sql);
		List list = conn.query(sql) ;
		if(list.size()>0)
		{
			Object[] obj = (Object[]) list.get(0) ;
			max_value = (String) obj[0] ;
		}
		return 	max_value;	
	}
	/**
	 * 获取数据库中的最大的长度
	 * @param zd
	 * @param table_name
	 * @return
	 */
	public String getId(String zd,String table_name){
		String sql1="select max(length("+zd+")) from "+table_name;
		List rsList = conn.query(sql1);
		String maxLen="";
		if(rsList.size()>0){
			Object[] obj = (Object[])rsList.get(0); 
			maxLen=(String)obj[0];
		}
		int gnId;
		String gn_id="";
		if(maxLen!=""){
			String sql22="select max("+zd+") from "+table_name+" where  length("+zd+")="+maxLen;
			List<?> rsList2 = conn.query(sql22);
			if(rsList2.size()>0){
				Object[] obj2 = (Object[])rsList2.get(0); 
				gnId=Integer.parseInt((String)obj2[0])+1;
				gn_id=""+gnId;
			}
		}
		if(gn_id==""){
			gn_id="1";
		}
		return gn_id;
	}
	/**
	 * 根据所给的字段名，数据库表名，以及所给的条件，查看数据库中是否有该信息
	 * @param tbName   查的字段对应的表名
	 * @param whereTj  对应的条件
	 * @param sqlTsStr 提示sql串儿
	 * @return 0：无；1：有
	 */
	public String get_have_Zd_value(String tbName,String whereTj,String sqlTsStr){
		String have_Zd_value = "";
		String sql = "select * from "+tbName+" "+whereTj+";" ;
		System.out.println("sql=="+sqlTsStr+"=="+sql);
		List list = conn.query(sql) ;
		if(list.size()>0)
		{
			//1有
			have_Zd_value = "1" ;
		}else{
			have_Zd_value = "0" ;
		}
		return 	have_Zd_value;	
	}
	/**
	 * 根据所给的字段名，数据库表名，以及所给的条件，查看数据库中记录数
	 * @param tbName   查的字段对应的表名
	 * @param whereTj  对应的条件
	 * @param sqlTsStr 提示sql串儿
	 * @return 
	 */
	public int get_Zd_Num(String tbName,String whereTj,String sqlTsStr){
		int Zd_Num = 0;
		String sql = "select * from "+tbName+" "+whereTj+";" ;
		System.out.println("sql=="+sqlTsStr+"=="+sql);
		List list = conn.query(sql) ;
		if(list.size()>0)
		{
			Zd_Num = list.size();
		}
		return 	Zd_Num;	
	}
	/**
	 * 根据所给的字段名，数据库表名，以及所给的条件，查看数据库中记录数(查询特定字段)
	 * @param tbName   查的字段对应的表名
	 * @param whereTj  对应的条件
	 * @param sqlTsStr 提示sql串儿
	 * @return 
	 */
	public int get_Zd_Num1(String zdStr,String tbName,String whereTj,String sqlTsStr){
		int Zd_Num = 0;
		String sql = "select * from "+tbName+" "+whereTj+";" ;
		System.out.println("sql=="+sqlTsStr+"=="+sql);
		List list = conn.query(sql) ;
		if(list.size()>0)
		{
			Zd_Num = list.size();
		}
		return 	Zd_Num;	
	}
	/**
	 * 根据给的字段名串儿，字段值串儿，表名，给给定的表中插入数据
	 * @param zd_nameStr  字段名格式：字段1,字段2,字段3
	 * @param zd_valueStr 字段值的格式：'值1','值2','值3'
	 * @param tbName	     表名
	 * @param sqlTsStr    提示信息
	 * re = 1 成功 re = 0失败
	 */
	public String insertDataSql(String zd_nameStr,String zd_valueStr,String tbName,String sqlTsStr){
		String sql = "insert into "+tbName+" ("+zd_nameStr+") values ("+zd_valueStr+");";
		System.out.println("sql=="+sqlTsStr+"=="+sql);
		String re = jdbc.useSQL(sql);
		return re;
	}
	
	/**
	 * 根据给的字段名串儿，字段值串儿，表名，给给定的表中插入数据
	 * @param zd_nameStr  字段名格式：字段1,字段2,字段3
	 * @param zd_valueStr 字段值的格式：'值1','值2','值3'
	 * @param tbName	     表名
	 * @param sqlTsStr    提示信息
	 * re = 1 成功 re = 0失败
	 */
	public String insertDataArrSql(String[] zd_nameStr,String[] zd_valueStr,String tbName,String sqlTsStr){
		
		
		String sql = "insert into "+tbName+" ("+zd_nameStr+") values ("+zd_valueStr+");";
		for (int i = 0; i < zd_valueStr.length; i++) {
			sql+=zd_nameStr+",";
		}
		
		System.out.println("sql=="+sqlTsStr+"=="+sql);
		String re = jdbc.useSQL(sql);
		return re;
	}
	/**
	 * 删除信息的sql
	 * @param zd_nameStr
	 * @param zd_valueStr
	 * @param tbName
	 * @param sqlTsStr
	 */
	public void delMess_sql(String tbName,String whereTj,String sqlTsStr){
		String sql = "delete from "+tbName+" "+whereTj+";";
		System.out.println("sql=="+sqlTsStr+"=="+sql);
		conn.useSQL(sql);
	}
	/**
	 * 更新指定的数据
	 * @param zd_nameAndValue 更新的字段值='value'(zdName='zdValue')
	 * @param tbName   		     更新字段对应的表名
	 * @param whereTj  		     更新字段对应的条件
	 * @param sqlTsStr 		     提示sql串儿
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String updateDataSql(String zd_nameAndValue,String tbName,String whereTj,String sqlTsStr){
		String max_value = "0";
		String sql = "update "+tbName+" set "+zd_nameAndValue+" "+whereTj+"" ;
		System.out.println("sql=="+sqlTsStr+"=="+sql);
		conn.useSQL(sql);
		return 	max_value;	
	}
	/**
	 * 生成yyyyMMddHHmmss的时间串儿
	 * @return
	 */
	public static String getTimeStr(){
		String timeStr = "" ;		
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
        Date currentTime = new Date();//得到当前系统时间 
        String str_date1 = formatter.format(currentTime); //将日期时间格式化 
        timeStr = str_date1.toString().replaceAll("/","").replaceAll(":","").replaceAll(" ",""); //将Date型日期时间转换成字符串形式 		
		return timeStr ;
	}
	/**
	 * 生成yyyy/MM/dd HH:mm:ss的事件串儿
	 * @return
	 */
	public static String getNormalTime(){
		String timeStr = "" ;		
	    java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
	    Date currentTime = new Date();//得到当前系统时间 
	    timeStr = formatter.format(currentTime); //将日期时间格式化 
		return timeStr ;
	}
	/**
	 * 生成yyyy-MM-dd HH:mm:ss的事件串儿
	 * @return
	 */
	public static String getNormalTime2(){
		String timeStr = "" ;		
	    java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    Date currentTime = new Date();//得到当前系统时间 
	    timeStr = formatter.format(currentTime); //将日期时间格式化 
		return timeStr ;
	}
  /**
   *  获取当天开始时时间
   * @return
   */
	  public String getNowdayTime() {
	   String re_time="";
	   re_time=getNormalTime2();
	   re_time=re_time.split(" ")[0];
	  return re_time;
	  }
	  /**
	   *  获取当月第一天
	   * @return
	   */
	  public String getFirstDayOfMonth() {
	   String str = "";
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	   Calendar lastDate = Calendar.getInstance();
	   lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
	   str = sdf.format(lastDate.getTime());
	   str+=" 00:00:00";
	   return str;
	  }

	  /**
	   * 获得本周一的日期
	   */
	  private int weeks = 0;
	  public String getMondayOFWeek() {
		  Calendar calendar = Calendar.getInstance();
		   int min = calendar.getActualMinimum(Calendar.DAY_OF_WEEK); //获取周开始基准
		   int current = calendar.get(Calendar.DAY_OF_WEEK); //获取当天周内天数
		   java.text.SimpleDateFormat formatter=null;
		   formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   calendar.add(Calendar.DAY_OF_WEEK, min-current); //当天-基准，获取周开始日期
		   Date start = calendar.getTime();
		   calendar.add(Calendar.DAY_OF_WEEK, 6); //开始+6，获取周结束日期
		   Date end = calendar.getTime();
		   System.out.printf("start=%tF, end=%tF ", start, end);
			String reTime = formatter.format(start); // 将日期时间格式化
			reTime=reTime.split(" ")[0]+" 00:00:00";
		  // System.out.println("本周第一天===="+reTime);
		   return reTime;
	  }
	/**
	 * 修改时间串儿
	 * @param upTime
	 * @return
	 */
	public static String changeTimeStr(String upTime)
	{
		upTime = upTime.replaceAll("/", "").replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "") ;
		return upTime ;
	}
	/**
	 * 修改时间串儿
	 * @param upTime
	 * @return
	 */
	public static String changeTimeStr1(String upTime)
	{
		upTime = upTime.replaceAll("/", "").replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "") ;
		return upTime ;
	}
	/**
	 * 调用该方法，实现阿拉伯数字和汉字大写形式的转换（大题数量小于999）
	 * @param dth
	 * @return
	 */
	public String szhz_trans(String dth){
		String[] arr_dth=dth.split("");
		int len=arr_dth.length;
		String dth_hz="";
		for(int i=len-1;i>=0;i--){
			String get_hzxs=szhz_trans2(arr_dth[i]);
			if(len==1){dth_hz=get_hzxs;}
			if(len==2){
				if(i==1){
					if(get_hzxs.equals("零")){
					}else{dth_hz=get_hzxs;}
				}else if(i==0){
					if(dth_hz.equals("零")){
						dth_hz+="十";
					}else{dth_hz+=get_hzxs;}
				}
			}
			if(len==3){
				if(i==2){
					if(get_hzxs.equals("零")){						
					}else{dth_hz=get_hzxs;}
					}else if(i==1){
						if(dth_hz.equals("零")){
							dth_hz+="十";
						}else{dth_hz+=get_hzxs;}
					}else if(i==0){
						if(dth_hz.equals("零")){
							dth_hz+="百";
						}else{dth_hz+=get_hzxs;}
					}		
			}
		}
		return dth_hz;
	}
	
	/**
	 * 返回是“零”时，做判断
	 * 阿拉伯数字0--9转换成汉子
	 * @param one
	 * @return
	 */
	public String szhz_trans2(String one){
		String re_dxxs="";
		if(one.equals("0")){re_dxxs="零";}
		if(one.equals("1")){re_dxxs="一";}
		if(one.equals("2")){re_dxxs="二";}
		if(one.equals("3")){re_dxxs="三";}
		if(one.equals("4")){re_dxxs="四";}
		if(one.equals("5")){re_dxxs="五";}
		if(one.equals("6")){re_dxxs="六";}
		if(one.equals("7")){re_dxxs="七";}
		if(one.equals("8")){re_dxxs="八";}
		if(one.equals("9")){re_dxxs="九";}
		return re_dxxs;
	}
	/**
	 * 实现讲一个字符转换成含有标记的串儿，例如传过来的一个字符或者字符串aaa，修饰符为@ 
	 * 则返回的字符串为 @aaa@ 
	 * @param char1		     字符
	 * @param decollator  修饰字符的分割符
	 * @return
	 */
	public String switchCharToStr(String char1,String decollator){
		String reStr = "";
		reStr +=decollator+char1+decollator;
		return reStr;
		
	}
	/**  
	 * 时间格式转换
	 * @param   str_time    初始时间串
	 * @return   目标形式时间串
	 */
	public String time_change(String str_time) {// 串转换成时间格式 swb 10.4 add
		if(str_time == null || str_time.trim().length() == 0)
		{
			return str_time ;
		}
		
		if(str_time.indexOf("/")>-1 || str_time.indexOf("-")>-1){
			return str_time ;
		}
		
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time_send = "";
		try {
			Date date = df.parse(str_time);
			// time_send=date.toLocaleString();//转化为 yyyy-mm-dd hh:mm:ss 格式
			time_send = formatter.format(date);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			time_send = str_time ;
		}
		//System.out.println("时间--" + time_send);
		return time_send;
	}
	/**
	 * 复制文件
	 * @param source_file  旧文件路径
	 * @param dest_file    新复制路径
	 * @throws IOException
	 */
	public static void Copyfile(String source_file,String dest_file) throws IOException{
        try{
             File to=new File(dest_file);
        	 if (!to.exists()) { 
                  to.createNewFile();
             } 
            FileInputStream fis = new FileInputStream(source_file);
            FileOutputStream fos = new FileOutputStream(dest_file);      
            //byte by[]=new byte [fis.available()];
            byte bytes[]=new byte [1024];
            int c; 
            while ((c=fis.read(bytes))!=-1){   
            	    fos.write(bytes,0,c);
            } 
            fis.close();
            fos.close();                           
	        System.out.println("已经成功将文件"+source_file+"复制到"+dest_file+"！");
        } catch (FileNotFoundException e){
            System.err.print("FileCopy类的方法copyFile()方法中出现异常，具体原因："+e.getMessage());
        } catch (IOException e){
            System.err.print("FileCopy类的方法copyFile()方法中出现异常，具体原因："+e.getMessage());
        }
	}
	/**
	 * 串保存为文件
	 * @param toPath
	 * @param htmlStr
	 * @return 
	 */
	public static boolean strSaveToFile2(String toPath,String htmlStr){
		boolean bool = true ;
		File htmlfile = new File(toPath);
		FileOutputStream fos;
		try {
			//fos = new FileOutputStream(htmlfile,false); //nn 存在
			fos = new FileOutputStream(htmlfile,false);
			//3.输出操作
		   try {
			   fos.write(htmlStr.getBytes());
			   fos.flush();
			   fos.close();
			} catch (IOException e) {
				e.printStackTrace();
				bool = false ;
			}			   
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			bool = false ;
		}
		return bool ;
	}
	/**
	 * 串保存为文件
	 * @param toPath
	 * @param htmlStr
	 * @return 
	 */
	public static boolean strSaveToFile(String toPath,String htmlStr){
		boolean bool = true ;
		File htmlfile = new File(toPath);
		OutputStreamWriter pw = null;//定义一个流
		try {
			pw = new OutputStreamWriter(new FileOutputStream(htmlfile),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bool=false;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bool=false;
		}//确认流的输出文件和编码格式，此过程创建了“test.txt”实例
		try {
			pw.write(htmlStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bool=false;
		}//将要写入文件的内容，可以多次write
		try {
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bool=false;
		}//关闭流
		return bool;
	}
	/**
	 * 将数据流转化为串儿
	 * 获取word的html串 并替换 " 为 '
	 * @param q_html
	 * @return
	 */
	public String c_mak_htmlstr(String q_html){
		File file = new File(q_html);
		FileInputStream in_stream;
		String tb_stream = "" ;
		try {
			in_stream = new FileInputStream(file);
			// 将流转化为串（html）
			BufferedInputStream br = new BufferedInputStream(in_stream);
			byte[] b = new byte[br.available()];
			br.read(b);
			tb_stream = new String(b);
			//tb_stream = tb_stream.replaceAll("'", "\"");
			tb_stream = tb_stream.replaceAll("\"","'");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return tb_stream;
	}
	/**
	 * 根据文件路径创建文件
	 * @param filePath
	 * @throws IOException
	 */
	public void createNewDocument(String filePath) throws IOException{
		File file = new File(filePath);
		if(!file.exists()){
			file.createNewFile();
		}
	}
	/**
	 * 根据文件路径创建文件夹
	 * @param filePath
	 */
	public static void createFileDir(String filePath)
	{
		File file = new File(filePath);
		if(!file.exists())
		{
			file.mkdirs() ;
		}
	}
	/**
	 * 根据文件路径删除文件
	 * @param filePath
	 * @return 
	 */
	public static boolean deleteFile(String filePath)
	{
		boolean reVal = false;
		File file = new File(filePath);
		if(file.exists()){
			System.out.println("文件存在，正在删除文件.....");
			file.delete();
			reVal = true;
		}else{
			System.out.println("文件不存在，删除失败!!!");
			reVal = false;
		}
		return reVal;
	}
	   /**
     *  根据路径删除指定的目录或文件，无论存在与否
     *@param sPath  要删除的目录或文件
     *@return 删除成功返回 true，否则返回 false。
     */
    public boolean DeleteFolder(String sPath) {
    	boolean flag = false;
    	File file = new File(sPath);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                return deleteFile(sPath);
            } else {  // 为目录时调用删除目录方法
                return deleteDirectory(sPath);
            }
        }
    }
    /**
     * 删除目录（文件夹）以及目录下的文件
     * @param   sPath 被删除目录的文件路径
     * @return  目录删除成功返回true，否则返回false
     */
    public boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 测试获取各种路径
     * @throws IOException
     */
    public void showURL() throws IOException {
    	  
    	  // 第一种：获取类加载的根路径   D:\git\daotie\daotie\target\classes
    	  File f = new File(this.getClass().getResource("/").getPath());
    	  System.out.println(f);

    	  // 获取当前类的所在工程路径; 如果不加“/”  获取当前类的加载目录  D:\git\daotie\daotie\target\classes\my
    	  File f2 = new File(this.getClass().getResource("").getPath());
    	  System.out.println(f2);

    	  // 第二种：获取项目路径    D:\git\daotie\daotie
    	  File directory = new File("");// 参数为空
    	  String courseFile = directory.getCanonicalPath();
    	  System.out.println(courseFile);
    	  

    	  // 第三种：  file:/D:/git/daotie/daotie/target/classes/
    	  URL xmlpath = this.getClass().getClassLoader().getResource("");
    	  System.out.println(xmlpath);
    	 

    	  // 第四种： D:\git\daotie\daotie
    	  System.out.println(System.getProperty("user.dir"));
    	  /*
    	   * 结果： C:\Documents and Settings\Administrator\workspace\projectName
    	   * 获取当前工程路径
    	   */

    	  // 第五种：  获取所有的类路径 包括jar包的路径
    	  System.out.println(System.getProperty("java.class.path"));
    	  
    	 }
	/**
	 * 页面返回提示
	 * @param str
	 * @param out
	 */
	public static void viewMsg(String str,PrintWriter out){
		String alertMsg = "";
		if(str.length()>1){
			alertMsg = "alert('"+str+"');" ;
		}
		out.println("<script>" +
						alertMsg+
						"history.go(-1);"+ 
					"</script>");		
		out.flush();
		out.close();
	}
	/**
	 * 打开新页面
	 * @param url     页面路径
	 * @param target  target名
	 * @param out     
	 */
	public static void openNewPage(String url,String target,PrintWriter out){
		  out.println("<html>");  
	      out.println("<script type=\"text/javascript\">");  
	      out.println("window.open ('"+url+"','"+target+"')");  
	      out.println("</script>");  
	      out.println("</html>");
	      out.flush();
		  out.close();
	}
	/** 
	 * 汉字转Unicode 
	 * @param s 
	 * @return 
	 */  
	public static String gbEncoding(final String s){  
	   String str = "";  
	   for (int i = 0; i < s.length(); i++) {  
	      int ch = (int) s.charAt(i);  
	      str += "\\u" + Integer.toHexString(ch);  
	   }  
	    return str;  
	  }  
	 /** 
	   * Unicode转汉字 
	   * @param str 
	   * @return 
	   */  
	  public static String encodingtoStr(String str){  
	      Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");  
	      Matcher matcher = pattern.matcher(str);  
	      char ch;  
	    while (matcher.find()) {  
	       ch = (char) Integer.parseInt(matcher.group(2), 16);  
	       str = str.replace(matcher.group(1), ch + "");  
	      }  
	      return str;  
	  }  
	  /**
	   * 大写字母转换小写
	   * @return
	   */
	  public char dxSwitchXx(char zimu){
		  char xx =(char)(zimu+32);  
		  return xx;
	  }
	  /**
	   * 将某个字符串儿插入到另一个字符串儿指定为位置中
	   * @param oldStr
	   * @param addStr
	   * @param pos1
	   * @return
	   */
	  public String insertStrToZdPostion(String oldStr,String addStr,int pos1){
		  String newStr = "";
		  System.out.println("执行了替换的方法！！！！！");
		  newStr = oldStr.substring(0,pos1) + addStr + oldStr.substring(pos1,oldStr.length());
		  return newStr;
	  }
    /**
	 *
	 * 转换java编码 URLDecoder
	 * @param str
	 * @return 
     */
	  public String URLEncoder(String str){
		  if(str == null || str.isEmpty()){
			  return str; 
			} 
			  try{
				  str = URLEncoder.encode(str, "UTF-8");
			  }catch(Exception e){
				  System.err.printf("URLEncoder.encode转换汉字编码格式时候发生异常!", e);
			  }
			  return str; 
		  }
    /**
	 *
	 * 转换ajax提交的表单编码 URLDecoder
	 * @param str
	 * @return 
     */
	  public  String URLDecoder(String str){
		  if(str == null || str.isEmpty()){
			  return str; 
			} 
			  try{
				  str = URLDecoder.decode(str, "UTF-8");
			  }catch(Exception e){
				  System.err.printf("URLDecoder.decode转换前台传过来的汉字编码格式时候发生异常!", e);
			  }
			  return str; 
		  }
	/**
	 * 删除串儿的第一个分割符
	 * @param str
	 * @param fgf
	 * @return
	 */
	  public String delStrFristFgf(String str,String fgf){
		  int loc = str.indexOf(fgf);
		  if(loc==-1){
			  return str;  
		  }else if(loc==0){
			  str = str.substring(loc,str.length());
		  }
		  return str;
	  }
		/**
		 * 获取时间控件的串
		 * @return
		 */
		public String getTimeKjN(){
			String reValues="";
			reValues="<div align=\"left\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
				"统计周期:&nbsp;&nbsp;" +
				"<select id=\"selZq\">" +
					"<option value=\"\">====请选择====</option>" +
					"<option value=\"1\">全部</option>" +
					"<option value=\"2\">当天</option>" +
					"<option value=\"3\">本周</option>" +
					"<option value=\"4\">本月</option>" +
				"</select>" +
				"<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
				"按时间段统计:&nbsp;&nbsp;" +
				"<font size=\"-1\">" +
					"<label for=\"star_time\">从：</label>" +
					"<input type=\"text\" id=\"star_time\" name=\"star_time\" onclick=\"WdatePicker()\" value=\"\" />&nbsp;" +
					"<label for=\"end_time\">至：</label>&nbsp;" +
					"<input id=\"end_time\" name=\"end_time\" onclick=\"WdatePicker()\" value=\"\"/>" +
				"</font>";
			return reValues;
		}
		 /**
		  * 获取用户ip
		  * @param request
		  * @return
		  */
		 public String getUserIp(HttpServletRequest request){
			 String ip = request.getHeader("x-forwarded-for");
		       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		           ip = request.getHeader("Proxy-Client-IP");
		       }
		       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		           ip = request.getHeader("WL-Proxy-Client-IP");
		       }
		       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		           ip = request.getRemoteAddr();
		       }
		       return ip;
		 }
		 /**  
	   	 * 执行sql事务
	   	 * @param  comsql   要执行的sql语句串
	   	 * @return 成功1，失败0
	   	 */
			public String fun_sql_shiwu1(String comsql,String fgf) {
				Statement stmt = null;
				Connection conn = null;
				ResultSet rs = null;
				String jud = "0";
				try {
					conn = Jdbc_ch.getConn();
					boolean autoCommit = conn.getAutoCommit();
					conn.setAutoCommit(false);
					stmt = conn.createStatement();
					String[] zsql = comsql.split(fgf);
					for (int i = 0; i < zsql.length; i++) {
						System.out.println(zsql[i]);
						stmt.addBatch(zsql[i]);

					}
					stmt.executeBatch();
					conn.commit();
					conn.setAutoCommit(autoCommit);
					stmt.close();
					System.out.println("操作成功！");
				} catch (Exception e) {
					jud = "1";
					System.out.println("操作失败、任务撤消！");
					try {
						// 回滚、取消前述操作
						conn.rollback();
					} catch (Exception e1) {
						jud = "1";
						e.printStackTrace();
					}
				} finally {
					try {
						if (conn != null) {
							conn.close();
						}
					} catch (Exception e) {
						jud = "1";
						e.printStackTrace();
					}
				}
				return jud;
			}
	/**
	 * 给文件中写入数据串儿
	 * @param path   	      写入路径
	 * @param text		      文件类型
	 * @param data_type   例如： utf-8,GBK,GB2312,Big5
	 */
	 public static void writeText(String path, String text,String data_type) 
	 {
		  try {
		    FileOutputStream o = new FileOutputStream(path);
		    o.write(text.getBytes(data_type));
		    o.close();
		  } catch(Exception e) {}
	 }	
	 /**
      * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
      */
	    public static void readFileByBytes(String fileName) {
	        File file = new File(fileName);
	        InputStream in = null;
	        try {
	            System.out.println("以字节为单位读取文件内容，一次读一个字节：");
	            // 一次读一个字节
	            in = new FileInputStream(file);
	            int tempbyte;
	            while ((tempbyte = in.read()) != -1) {
	                System.out.write(tempbyte);
	            }
	            in.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return;
	        }
	        try {
	            System.out.println("以字节为单位读取文件内容，一次读多个字节：");
	            // 一次读多个字节
	            byte[] tempbytes = new byte[100];
	            int byteread = 0;
	            in = new FileInputStream(fileName);
	            showAvailableBytes(in);
	            // 读入多个字节到字节数组中，byteread为一次读入的字节数
	            while ((byteread = in.read(tempbytes)) != -1) {
	                System.out.write(tempbytes, 0, byteread);
	            }
	        } catch (Exception e1) {
	            e1.printStackTrace();
	        } finally {
	            if (in != null) {
	                try {
	                    in.close();
	                } catch (IOException e1) {
	                }
	            }
	        }
	    }

	    /**
	     * 以字符为单位读取文件，常用于读文本，数字等类型的文件
	     */
	    public static void readFileByChars(String fileName) {
	        File file = new File(fileName);
	        Reader reader = null;
	        try {
	            System.out.println("以字符为单位读取文件内容，一次读一个字节：");
	            // 一次读一个字符
	            reader = new InputStreamReader(new FileInputStream(file));
	            int tempchar;
	            while ((tempchar = reader.read()) != -1) {
	                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
	                // 但如果这两个字符分开显示时，会换两次行。
	                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
	                if (((char) tempchar) != '\r') {
	                    System.out.print((char) tempchar);
	                }
	            }
	            reader.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        try {
	            System.out.println("以字符为单位读取文件内容，一次读多个字节：");
	            // 一次读多个字符
	            char[] tempchars = new char[30];
	            int charread = 0;
	            reader = new InputStreamReader(new FileInputStream(fileName));
	            // 读入多个字符到字符数组中，charread为一次读取字符数
	            while ((charread = reader.read(tempchars)) != -1) {
	                // 同样屏蔽掉\r不显示
	                if ((charread == tempchars.length)
	                        && (tempchars[tempchars.length - 1] != '\r')) {
	                    System.out.print(tempchars);
	                } else {
	                    for (int i = 0; i < charread; i++) {
	                        if (tempchars[i] == '\r') {
	                            continue;
	                        } else {
	                            System.out.print(tempchars[i]);
	                        }
	                    }
	                }
	            }

	        } catch (Exception e1) {
	            e1.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e1) {
	                }
	            }
	        }
	    }

	    /**
	     * 以行为单位读取文件，常用于读面向行的格式化文件
	     */
	    public static String readFileByLines(String fileName) {
	        File file = new File(fileName);
	        BufferedReader reader = null;
	        String tempString = null;
	        String dataString = null;
	        try {
//	            System.out.println("以行为单位读取文件内容，一次读一整行：");
	            reader = new BufferedReader(new FileReader(file));
	            int line = 1;
	            // 一次读入一行，直到读入null为文件结束
	            while ((tempString = reader.readLine()) != null) {
	                // 显示行号
//	                System.out.println("line " + line + ": " + tempString);
	                tempString += tempString;
	                line++;
	                dataString = tempString;
	                System.out.println("dataString===\n\n"+dataString);
	            }
	            reader.close();
	           
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e1) {
	                }
	            }
	        }
//	        System.out.println("tempString===\n"+dataString);
			return dataString;
	    }

	    /**
	     * 随机读取文件内容
	     */
	    public static void readFileByRandomAccess(String fileName) {
	        RandomAccessFile randomFile = null;
	        try {
	            System.out.println("随机读取一段文件内容：");
	            // 打开一个随机访问文件流，按只读方式
	            randomFile = new RandomAccessFile(fileName, "r");
	            // 文件长度，字节数
	            long fileLength = randomFile.length();
	            // 读文件的起始位置
	            int beginIndex = (fileLength > 4) ? 4 : 0;
	            // 将读文件的开始位置移到beginIndex位置。
	            randomFile.seek(beginIndex);
	            byte[] bytes = new byte[10];
	            int byteread = 0;
	            // 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。
	            // 将一次读取的字节数赋给byteread
	            while ((byteread = randomFile.read(bytes)) != -1) {
	                System.out.write(bytes, 0, byteread);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (randomFile != null) {
	                try {
	                    randomFile.close();
	                } catch (IOException e1) {
	                }
	            }
	        }
	    }

	    /**
	     * 显示输入流中还剩的字节数
	     */
	    private static void showAvailableBytes(InputStream in) {
	        try {
	            System.out.println("当前字节输入流中的字节数为:" + in.available());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	        /**
	         * A方法追加文件：使用RandomAccessFile
	         */
	        public static void appendMethodA(String fileName, String content) {
	            try {
	                // 打开一个随机访问文件流，按读写方式
	                RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
	                // 文件长度，字节数
	                long fileLength = randomFile.length();
	                //将写文件指针移到文件尾。
	                randomFile.seek(fileLength);
	                randomFile.writeBytes(content);
	                randomFile.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }

	        /**
	         * B方法追加文件：使用FileWriter
	         */
	        public static void appendMethodB(String fileName, String content) {
	            try {
	                //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
	                FileWriter writer = new FileWriter(fileName, true);
	                writer.write(content);
	                writer.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	      /**
	       * list数组转json串儿  
	       * @param seleTbResArr
	       * @param zdStr
	       * @return
	       */
		 public static StringBuffer ListToJson(String[][] seleTbResArr,String zdStr){
			 StringBuffer sb = new StringBuffer();
			 String[] zdArr = zdStr.split(",");
		        boolean first = true;
		        sb.append("[");
		        for (int i = 0; i < seleTbResArr.length; i++) {
		        	String[] blogItem = (String[]) seleTbResArr[i];
		            System.out.println("blogItem==="+blogItem[0]+"\t"+blogItem[1]+"\t"+blogItem[2]);
		            if (!first) {
		                sb.append(",");
		            }
		            sb.append("{");
		            for(int k=0;k<blogItem.length;k++){
		            	sb.append(""+zdArr[k]+": '" + blogItem[k] + "', ");
		            }
		            sb.append("}");
		            first = false;
		        }
	        sb.append("]");
	        System.out.println(sb.toString());
			return sb;
		 }
	  public static void main(String[] args) throws IOException
	  {
		  String fierstr = "javascript%25EF%25BC%259Avoid(0)"; 
		  //System.out.println("fierstr=="+fierstr);
		  ZnnToolsClass ztc = new ZnnToolsClass();
		  //fierstr = ztc.URLDecoder(ztc.URLDecoder(fierstr));
		  //fierstr = fierstr.replace("/'/g" ,"!@!");
		  //System.out.println("fierstr=="+fierstr);
		  String abc = "&nbsp";
		  System.out.println(abc.replace("&nbsp", "1"));

	  }
}
