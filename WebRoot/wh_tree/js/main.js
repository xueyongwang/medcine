//----------------------------------------------
	var isResizing = false;
	function Resize_mousedown(event, obj) {
		obj.mouseDownX = event.clientX;
		obj.leftTdW = obj.previousSibling.offsetWidth;
		obj.setCapture();
		isResizing = true;
	}
	function Resize_mousemove(event, obj) {
		if (!isResizing)
			return;
		var newWidth = obj.leftTdW * 1 + event.clientX * 1 - obj.mouseDownX;
		if (newWidth > 0) {
			obj.previousSibling.style.width = newWidth + 'px';
		} else {
			obj.previousSibling.style.width = 1 + 'px';
		}
	}
	function Resize_mouseup(event, obj) {
		if (!isResizing)
			return;
		obj.releaseCapture();
		isResizing = false;
	}
	function Resize_setDefault(event, obj) {
		if (obj.innerText == "<") {
			document.getElementById("left").style.display = "none";
			obj.innerText = ">";
		} else {
			document.getElementById("left").style.display = "block";
			obj.innerText = "<";
		}
	}
