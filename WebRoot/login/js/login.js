function login(){
	//去掉tips
	$.closeErrorTip();

	var sel_lx = "";	//接受参数选择类型
	$(".sex_lx").children("div").each(function() {
		var sel=$(this).children("ins").attr("class");
		if(sel=='checked'){
			sel_lx=$(this).attr("value");
		}
	});
	
	if(sel_lx==""){
		$("#username").errorTip("请选择类型");
		//alert("请选择类型");
		return 0;
	}
	document.getElementById("act").value=sel_lx;
	loginform.submit();
	
	
	/*
	 * 
	 * */
	
}