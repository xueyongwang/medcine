function over(ch) {
	var x = ch.style.color;
	ch.style.backgroundColor = "#65b855";
	ch.style.color = x;
}
function out(ch) {
	var x = ch.style.color;
	ch.style.backgroundColor = x;
	ch.style.color = x;
}
function add_mk() {
	var basePath = document.getElementById("basePath").value;
	window.open(basePath+"function/wh_gn.jsp?bs=0", "_self");
}

//修改
function mk_update(i) {
	var basePath = document.getElementById("basePath").value;
	var mk_id = document.getElementById("mk_id" + i + "").value;
	window.open(basePath+"function/wh_gn.jsp?bs=1&mk_id=" + mk_id + "", "_self");
}

//删除
function mk_del(i) {
	var basePath = document.getElementById("basePath").value;
	var mk_id = document.getElementById("mk_id" + i + "").value;
	window.open(basePath+"function/wh_gn.jsp?bs=2&mk_id=" + mk_id + "", "_self");
}