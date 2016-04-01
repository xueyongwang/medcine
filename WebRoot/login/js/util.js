var Util = {};
$(function() {
	document.ondragstart = function() {
		return false
	};
	$.ajaxSetup({
		cache : false
	});
	var f;
	var i;
	var d;
	var h;
	var b;
	var c;
	
	
	function e() {
		var j = $("#tagQuickInfo");
		if (j.length == 0) {
			j = $(
					"<div id='tagQuickInfo' class='shadow_1 radius3' style='display:none;'></div>")
					.appendTo("body")
		}
		return j
	}
	var a = document.referrer;
	if (a && a.indexOf("processon.com") < 0) {
		Util.setCookie("processon_referrer", encodeURI(a), 1)
	}
});
Array.prototype.inArray = function(b) {
	for ( var a = 0; a < this.length; a++) {
		if (this[a] == b) {
			return true
		}
	}
	return false
};
Array.prototype.indexOf = function(b) {
	for ( var a = 0; a < this.length; a++) {
		if (this[a] == b) {
			return a
		}
	}
	return -1
};
Array.prototype.remove = function(b) {
	var a = this.indexOf(b);
	if (a > -1) {
		this.splice(a, 1)
	}
};
Util.formatMsg = function(str, args) {
	if (typeof args != "object") {
		eval("args=['" + args + "']")
	}
	for ( var i = 0; i < args.length; i++) {
		var toReplace = "{" + i + "}";
		str = str.replace(toReplace, args[i])
	}
	return str
};
Util.formatNumber = function(e, c) {
	if (/[^0-9\.]/.test(e)) {
		return "0"
	}
	if (e == null || e == "") {
		return "0"
	}
	e = e.toString().replace(/^(\d*)$/, "$1.");
	e = (e + "00").replace(/(\d*\.\d\d)\d*/, "$1");
	e = e.replace(".", ",");
	var d = /(\d)(\d{3},)/;
	while (d.test(e)) {
		e = e.replace(d, "$1,$2")
	}
	e = e.replace(/,(\d\d)$/, ".$1");
	if (c == 0) {
		var b = e.split(".");
		if (b[1] == "00") {
			e = b[0]
		}
	}
	return e
};
Util.onlyNum = function(b) {
	var a = b || window.event;
	if (!(a.keyCode >= 8 && a.keyCode <= 20)
			|| (a.keyCode >= 33 && a.keyCode <= 46)) {
		if (!((a.keyCode >= 48 && a.keyCode <= 57) || (a.keyCode >= 96 && a.keyCode <= 105))) {
			if (window.event) {
				a.returnValue = false
			} else {
				a.preventDefault()
			}
		}
	}
	return a.keyCode
};

$.fn.clear = function() {
	$(this).find("input[type=text]").val("");
	$(this).find("input[type=password]").val("");
	$(this).find("textarea").val("");
	$(this).find("select").val("")
};


Util.get = function(a, b, c) {
	$.ajax({
		url : a,
		type : "GET",
		data : b,
		success : function(d) {
			if (d.error == "error") {
				$.simpleAlert("暂时无法处理您的请求，请稍候重试。", "error", 3000)
			} else {
				if (d.error == "notlogin") {
					Util.loginWindow("open", function() {
						Util.get(a, b, c)
					})
				} else {
					c(d)
				}
			}
		},
		error : function(d) {
		}
	})
};

Util.filterXss = function(a) {
	a = a.toString();
	a = a.replace(/[<%3C]/g, "&lt;");
	a = a.replace(/[>%3E]/g, "&gt;");
	a = a.replace(/"/g, "&quot;");
	a = a.replace(/'/g, "&#39;");
	return a
};
$.fn.disable = function(c, b) {
	$(this).attr("disable", true);
	$(this).addClass("opacity");
	for ( var a = 0; a < $(this).length; a++) {
		var d = $(this)[a];
		$(d).unbind("mouseover.disable").bind("mouseover.disable", function() {
			var e = $("<div class='disabled-mask'></div>").appendTo("body");
			if (!c) {
				c = 2
			}
			e.css({
				width : $(this).outerWidth() + c,
				height : $(this).outerHeight() + 4,
				top : $(this).offset().top,
				left : $(this).offset().left
			});
			if (b) {
				e.css("z-index", b)
			}
			e.bind("mouseout", function() {
				$(this).remove()
			})
		}).bind("focus", function() {
			$(this).blur()
		});
		$(d).trigger("mouseover.disable")
	}
	return this
};

$.fn.enable = function() {
	$(this).attr("disable", false);
	$(this).removeClass("opacity");
	for ( var a = 0; a < $(this).length; a++) {
		var b = $(this)[a];
		$(b).unbind("mouseover.disable").unbind("focus")
	}
	$(".disabled-mask").trigger("mouseout");
	return this
};

//设置cookie
Util.setCookie = function(b, c, a) {
	var d = new Date();
	d.setDate(d.getDate() + a);
	document.cookie = b + "=" + escape(c)
			+ ((a == null) ? "" : ";expires=" + d.toGMTString())
};
//获取cookie
Util.getCookies = function(b) {
	if (document.cookie.length > 0) {
		var c = document.cookie.indexOf(b + "=");
		if (c != -1) {
			c = c + b.length + 1;
			var a = document.cookie.indexOf(";", c);
			if (a == -1) {
				a = document.cookie.length
			}
			return unescape(document.cookie.substring(c, a))
		}
	}
	return ""
};

(function(c) {
	var d = 0;
	

	c.fn.fileSize = function() {
		var i = this.get(0);
		var e = 0;
		if (c.browser.msie && !i.files) {
			var g = i.value;
			var h = new ActiveXObject("Scripting.FileSystemObject");
			var f = h.GetFile(g);
			e = f.Size
		} else {
			e = i.files[0].size
		}
		return e * 1024
	};
	c.fn.errorTip = function(g, f) {
		var i;
		var h = "error";
		if (c(".signin-error").length) {
			c(".signin-error").remove()
		}
		if (f != null) {
			h = f
		}
		var e = '<span class="signin-error"><span class="signin-' + h
				+ '-tip">' + g + '<label class="signin-' + h
				+ '-tip-arrow right"></label><label class="signin-' + h
				+ '-tip-arrow right1"></label></span></span>';
		if (c(this).offset().left < 200) {
			e = '<span class="signin-error"><span class="signin-' + h
					+ '-tip">' + g + '<label class="signin-' + h
					+ '-tip-arrow left"></label><label class="signin-' + h
					+ '-tip-arrow left1"></label></span></span>'
		}
		c("body").append(e);
		i = c(".signin-error");
		i.css({
			left : c(this).offset().left - i.width(),
			top : c(this).offset().top + c(this).height() / 2 - 7,
			opacity : "0",
			filter : "alpha(opacity=0)"
		});
		c(this).addClass(h);
		if (c(this).offset().left < 200) {
			i.animate({
				left : c(this).offset().left + i.width(),
				top : c(this).offset().top + c(this).height() / 2 - 7,
				opacity : "0.7",
				filter : "alpha(opacity=70)"
			}, 200)
		} else {
			i.animate({
				left : c(this).offset().left - i.width() - 14,
				top : c(this).offset().top + c(this).height() / 2 - 7,
				opacity : "0.7",
				filter : "alpha(opacity=70)"
			}, 200)
		}
	};
	c.closeErrorTip = function() {
		c(".signin-error").remove();
		c("input.error").removeClass("error")
	};
	var b = {};
	var a = null;
	c.fn.streamInput = function(u) {
		if (!this[0] || !u.face || this[0].nodeName != "DIV"
				|| this.attr("stream_id")) {
			return
		}
		var h = {
			target : this
		};
		var f = c.extend(h, u);
		var r = {
			id : "",
			range : null
		};
		var s = Object.keys(b);
		r.id = s.length ? b[s[s.length - 1]].id + 1 : 1;
		r.stream_id = "stream_" + (s.length ? b[s[s.length - 1]].id + 1 : 1);
		this.attr({
			contentEditable : "true",
			spellcheck : "false",
			accesskey : "q",
			stream_id : r.stream_id
		});
		c(f.face).attr("for_stream", r.stream_id);
		b[r.stream_id] = r, a = r;
		var o = false, i = false, k = 0, l = 0, m = 0, p = !!f.home;
		c(f.target).off("click.stream keyup.stream").on(
				"click.stream keyup.stream", function(w) {
					a = b[c(this).attr("stream_id")];
					a.target = c(this);
					var v = window.getSelection();
					a.range = v.getRangeAt(0).cloneRange()
				});
		c(f.target).off("DOMSubtreeModified.stream")
				.on(
						"DOMSubtreeModified.stream",
						function(D) {
							if (!o) {
								return
							}
							o = false;
							if (c(this).children().length < 1) {
								return
							}
							q(c(this));
							t(c(this));
							if (i) {
								q(c(this).find(".paste-cont"));
								var E = c(this).html();
								c(this).empty();
								var x = window.getSelection();
								var B = x.getRangeAt(0);
								B.deleteContents();
								var w = c("<div>" + E + "</div>");
								var A = w.html(), y = document
										.createElement("div"), F = document
										.createDocumentFragment(), z, v;
								if (A) {
									y.innerHTML = A;
									while ((z = y.firstChild)) {
										v = F.appendChild(z);
										if (z.nodeName == "SPAN") {
											k = F.childNodes.length
													+ z.childNodes.length - 1
										}
									}
								}
								B.insertNode(F);
								if (v) {
									B = B.cloneRange();
									B.collapse(true);
									g(c(this), false);
									B.setStart(this, k
											|| this.childNodes.length - l);
									B.setEnd(this, k || this.childNodes.length
											- l);
									if (l && m) {
										var C = this.childNodes.length - l;
										z = this.childNodes[C];
										B.setStart(z, z.data.length - m);
										B.setEnd(z, z.data.length - m)
									}
									x.removeAllRanges();
									x.addRange(B)
								}
							} else {
								e(this)
							}
						});

	}
})(jQuery);

String.prototype.isEmpty = function() {
	if (this.replace(/(^\s*)|(\s*$)/g, "").length <= 0) {
		return true
	} else {
		return false
	}
};
String.prototype.notEmpty = function() {
	return !this.isEmpty()
};
String.prototype.isEmail = function() {
	if (this.isEmpty()
			|| (!/^([\w]+)(.[\w]+)*@([\w-]+\.){1,5}([A-Za-z]){2,4}$/.test(this))) {
		return false
	} else {
		return true
	}
};
String.prototype.isPhoneNumber = function() {
	if (this.isEmpty()
			|| (!/^0{0,1}(13[0-9]|15[7-9]|153|156|18[7-9])[0-9]{8}$/.test(this))) {
		return false
	} else {
		return true
	}
};
String.prototype.minLength = function(a) {
	if (this.length >= a) {
		return true
	} else {
		return false
	}
};
String.prototype.maxLength = function(a) {
	if (this.length <= a) {
		return true
	} else {
		return false
	}
};
String.prototype.cut = function(a, d) {
	var c = "";
	if (this == "") {
		return c
	}
	if (typeof d == "undefined") {
		d = "..."
	}
	var e = 0;
	for ( var b = 0; b < this.length; b++) {
		if (this.charCodeAt(b) > 127 || this.charCodeAt(b) == 94) {
			e += 2
		} else {
			e++
		}
	}
	if (e <= a) {
		return this.toString()
	}
	e = 0;
	a = (a > d.length) ? a - d.length : a;
	for ( var b = 0; b < this.length; b++) {
		if (this.charCodeAt(b) > 127 || this.charCodeAt(b) == 94) {
			e += 2
		} else {
			e++
		}
		if (e > a) {
			c += d;
			break
		}
		c += this.charAt(b)
	}
	return c
};
String.prototype.byteLength = function() {
	var b = 0;
	for ( var a = 0; a < this.length; a++) {
		if (this.charCodeAt(a) > 127 || this.charCodeAt(a) == 94) {
			b += 2
		} else {
			b++
		}
	}
	return b
};
