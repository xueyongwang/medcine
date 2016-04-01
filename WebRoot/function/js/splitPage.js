var pageJlNum = 12 ; //每页显示行数
var LinkDivId = "LinkDiv_swbAdd" ;
//判断表格是否需要增加翻页功能
function addTr(){
	
	var tbId=document.getElementById("tbIdBs").value;
	if(tbId==""||tbId=="noId"){}else{
		try{
			var objTb=document.getElementById(tbId);
			var objTr=objTb.getElementsByTagName("tr");
			showFirstPage(tbId,pageJlNum);//显示首页
			changeLink(); 
		}catch(e){
		}
	}
}
//显示首页
function showFirstPage(tbId,pageJlNum){
	var objTb=document.getElementById(tbId);
	var objTr=objTb.getElementsByTagName("tr");
	var trNum=objTr.length;
	pageJlNum=pageJlNum-0+1;
	if((pageJlNum-0)>(trNum-0)){ 
		for(var i=0;i<trNum;i++){
			objTr[i].style.display="block";
		}
	}else{

		for(var tr_i=0;tr_i<trNum;tr_i++){
			if(tr_i>=pageJlNum){
				objTr[tr_i].style.display="none";	
			}else{
				objTr[tr_i].style.display="block";
			}
		}
	}
}
function changeLink()
{
//	alert("333");
	var tbId=document.getElementById("tbIdBs").value;
	var objTb=document.getElementById(tbId);
	var spanBs="span_"+tbId;
	var tblHeadRowCount = 1 ;
	var nowPage = 1 ;
	var linkStr = mak_Link(objTb, tblHeadRowCount, nowPage);
	$("#"+spanBs).html(linkStr);
}
/**
 * 合成连接串
 * @param {Object} oTbl  
 * @param {Object} tblHeadRowCount 表格头部行数
 * @param {Object} nowPage 当前所在页
 * @return {TypeName} 
 */
function mak_Link(oTbl, tblHeadRowCount, nowPage)
{
	if(oTbl == null || oTbl == undefined) return ;
	var str = "" ;
	var trNum = oTbl.getElementsByTagName("tr").length;
	var totalRowCount = parseInt(trNum) - parseInt(tblHeadRowCount) ;
	var pageCount = workoutPageCount(totalRowCount, pageJlNum);
	
	str += "<span id=\""+LinkDivId+"\" >" ;//<span class="STYLE1">
	str += "共"+ totalRowCount + "条记录&nbsp;&nbsp;" ;
	str += "<input type=\"hidden\" name=\"nowPageIpt\" id=\"nowPageIpt\" value=\""+nowPage+"\" />" ;
	str += "<a href=\"javascript:void(0)\" onclick=changePage(\"first\",\"nowPageIpt\",\""+oTbl.id+"\",\""+tblHeadRowCount+"\")>首页</a>&nbsp;&nbsp;" ;
	str += "<a href=\"javascript:void(0)\" onclick=changePage(\"pre\",\"nowPageIpt\",\""+oTbl.id+"\",\""+tblHeadRowCount+"\")>前页</a>&nbsp;&nbsp;" ;
	str += "<a href=\"javascript:void(0)\" onclick=changePage(\"next\",\"nowPageIpt\",\""+oTbl.id+"\",\""+tblHeadRowCount+"\")>后页</a>&nbsp;&nbsp;" ;
	str += "<a href=\"javascript:void(0)\" onclick=changePage(\"last\",\"nowPageIpt\",\""+oTbl.id+"\",\""+tblHeadRowCount+"\")>尾页</a>&nbsp;&nbsp;&nbsp;" ;
	str += "<input type=\"text\" name=\"tzIpt\" id=\"tzIpt\" value=\""+nowPage+"\" " +
				   //"onkeyup=\"this.value=this.value.replace(/\\D/g,\"\")\" " +
				   "title=\"请输入有效数字  \" style=\"width:30px;height:18px;\" />"+pageCount+"&nbsp;" ;
	str += "<a href=\"javascript:void(0)\" onclick=changePage(\"tiaozhuan\",\"tzIpt\",\""+oTbl.id+"\",\""+tblHeadRowCount+"\")>跳转</a>&nbsp;&nbsp;" ;
	str += "</span>" ;
	return str ;
}

function mak_Link2(oTbl, tblHeadRowCount, nowPage)
{
	if(oTbl == null || oTbl == undefined) return ;
	var str = "" ;
	var trNum = oTbl.getElementsByTagName("tr").length;
	var totalRowCount = parseInt(trNum) - parseInt(tblHeadRowCount) ;
	var pageCount = workoutPageCount(totalRowCount, pageJlNum);
	
	str += "共"+ totalRowCount + "条记录&nbsp;&nbsp;" ;
	str += "<input type=\"hidden\" name=\"nowPageIpt\" id=\"nowPageIpt\" value=\""+nowPage+"\" />" ;
	str += "<a href=\"javascript:void(0)\" onclick=changePage(\"first\",\"nowPageIpt\",\""+oTbl.id+"\",\""+tblHeadRowCount+"\")>首页</a>&nbsp;&nbsp;" ;
	str += "<a href=\"javascript:void(0)\" onclick=changePage(\"pre\",\"nowPageIpt\",\""+oTbl.id+"\",\""+tblHeadRowCount+"\")>前页</a>&nbsp;&nbsp;" ;
	str += "<a href=\"javascript:void(0)\" onclick=changePage(\"next\",\"nowPageIpt\",\""+oTbl.id+"\",\""+tblHeadRowCount+"\")>后页</a>&nbsp;&nbsp;" ;
	str += "<a href=\"javascript:void(0)\" onclick=changePage(\"last\",\"nowPageIpt\",\""+oTbl.id+"\",\""+tblHeadRowCount+"\")>尾页</a>&nbsp;&nbsp;&nbsp;" ;
	str += "<input type=\"text\" name=\"tzIpt\" id=\"tzIpt\" value=\""+nowPage+"\" " +
				   //"onkeyup=\"this.value=this.value.replace(/\\D/g,\"\")\" " +
				   "title=\"请输入有效数字  \" style=\"width:30px;height:18px;\" />"+pageCount+"&nbsp;" ;
	str += "<a href=\"javascript:void(0)\" onclick=changePage(\"tiaozhuan\",\"tzIpt\",\""+oTbl.id+"\",\""+tblHeadRowCount+"\")>跳转</a>&nbsp;&nbsp;" ;
	
	return str ;
}

function workoutPageCount(totalCount, perPageNum)
{
	var pageCount = 1 ;
	var yushu = parseInt(totalCount) % parseInt(perPageNum) ;
	pageCount = parseInt(parseInt(totalCount) / parseInt(perPageNum)) ;
	if(yushu > 0)
	{
		pageCount += 1 ;
	}
	
	return pageCount ;
}

function changePage(action, nowPageIpt, oTblId, tblHeadRowCount)
{
	
	var oTbl = document.getElementById(oTblId) ;
	var trNum = oTbl.getElementsByTagName("tr").length;
	var totalRowCount = parseInt(trNum) - parseInt(tblHeadRowCount) ;
	var pageCount = workoutPageCount(totalRowCount, pageJlNum);
	
	var prePage = document.getElementById(nowPageIpt).value ;
	var iPrePage = parseInt(prePage);
	var nowPage = workoutNowPage(pageCount, iPrePage, action)
	
	changeViewTr(oTbl, tblHeadRowCount, nowPage) ;
	
	var newLinkStr = mak_Link2(oTbl, tblHeadRowCount, nowPage) ;
	
	//document.getElementById(LinkDivId).innerHTML = newLinkStr ;
	$("#"+LinkDivId).html(newLinkStr);
}

function workoutNowPage(pageCount, prePage, action)
{
	var nowPage = 1 ;
	
	if(action == "pre")
	{
		prePage -= 1 ;
	}else if(action == "first")
	{
		prePage = 1 ;
	}else if(action == "next")
	{
		prePage += 1 ;
	}else if(action == "last")
	{
		prePage = pageCount ;
	}
	
	
	if(prePage<1)
	{
		nowPage = 1 ;
	}else if(prePage>pageCount)
	{
		nowPage = pageCount ;
	}else
	{
		nowPage = prePage ;
	}
	
	return nowPage ;
}

function changeViewTr(oTbl, tblHeadRowCount, nowPage)
{
	var trNum = oTbl.getElementsByTagName("tr").length;
	var totalRowCount = parseInt(trNum);
	if(parseInt(trNum) - parseInt(tblHeadRowCount) < 1) return ;
	
	var beginRow = (parseInt(nowPage)-1) * pageJlNum + parseInt(tblHeadRowCount) ;
	var endRow = parseInt(nowPage) * pageJlNum + parseInt(tblHeadRowCount) ;
	
	//alert(tblHeadRowCount+"//"+beginRow+"//"+endRow+"//"+totalRowCount);
	try
	{
		for(var i=parseInt(tblHeadRowCount); i<totalRowCount; i++)
		{
			if(i <endRow && i >=beginRow)
			{
				oTbl.getElementsByTagName("tr")[i].style.display="block";
			}
			else
			{
				oTbl.getElementsByTagName("tr")[i].style.display="none";
			}
		}
	}catch(e)
	{}
}

//////////////验证//////
/**
 * 验证不能为空
 */
function not_null(Ipt) {
	if (Ipt.value.replace(/\s/g, '') == '') {
		alert("请输内容!");
		//Ipt.focus();
		return false;
	}
}

/**
 * 验证邮箱 
 */
function isEmail(Ipt) {
	var myreg = /^([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+.[a-zA-Z]{2,4}$/;
	var textbox1 = document.getElementById(Ipt);
	var val = textbox1.value;
	if (!myreg.test(val)) {
		alert("请输入有效邮箱地址，如：i@163.com");
		textbox1.focus();
		return false;
	}
}

/**
 * 验证网址
 */
function isUrl(Ipt) {
	var myreg = /^http:\/\/(www\.)?.+.?$/;
	var textbox1 = document.getElementById(Ipt);
	var val = textbox1.value;
	if (!myreg.test(val)) {
		alert("请输入有效网址，如：http://baidu.com");
		textbox1.focus();
		return false;
	}
}

/**
 * 验证日期
 */
function isDate(Ipt) {
	var myreg = /^((?:19|20)\d\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/;
	var textbox1 = document.getElementById(Ipt);
	var val = textbox1.value;
	if (!myreg.test(val)) {
		alert("请输入有效日期，如：2000-01-01");
		textbox1.focus();
		return false;
	}
}
/**
 * 验证整数
 */
function isDouble(obj){
	var myreg = /^[0-9]*[1-9][0-9]*$/;
	var val = obj.value;
	if (!myreg.test(val)) {
		alert("请输入整数，如：200");
		obj.focus();
		return false;
	}
}

