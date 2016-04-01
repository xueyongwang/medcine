<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.menu.classes.GenMenuClass" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");	
	GenMenuClass gmc = new GenMenuClass();
	String userLx = request.getParameter("userLx");
	userLx = "0";
	String str_menu = gmc.genMenuStr("zid,fid,zname,app_link,app_target,leve","fun_menu", " where userLx='"+userLx+"' ORDER BY zbh");
				
	
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>后台管理</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
		<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<link href="css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" />
		<link href="css/AdminLTE.css" rel="stylesheet" type="text/css" />
		<script src="fix/jquery.min.js"></script>
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		  <script src="fix/html5shiv.js"></script>
		  <script src="fix/respond.min.js"></script>
		<![endif]-->
		<script>
		var winHeight = "";
		
		function comHei(){
			if (window.innerHeight) {
				winHeight = window.innerHeight; 
			}else if ((document.body) && (document.body.clientHeight)) {
				winHeight = document.body.clientHeight; 
			}
		}
		comHei();
		function setHeight(obj){
			var true_hei = winHeight-146;
			obj.height  = true_hei+"px";
		}
		$(window).resize(function() {  comHei(); var obj1= document.getElementById("mframe"); setHeight(obj1); });
		
		</script>
    </head>
    <body class="skin-blue">
        <!-- header logo: style can be found in header.less -->
        <header class="header">
            <a href="index.html" class="logo">
                <!-- Add the class icon to your logo image or logo icon to add the margining -->
                AdminLTE
            </a>
            <!-- Header Navbar: style can be found in header.less -->
            <nav class="navbar navbar-static-top" role="navigation">
                <!-- Sidebar toggle button-->
                <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <div class="navbar-right">
                    <ul class="nav navbar-nav">
                        
                        <!-- User Account: style can be found in dropdown.less -->
                        <li class="dropdown user user-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="glyphicon glyphicon-user"></i>
                                <span>Jane Doe <i class="caret"></i></span>
                            </a>
                            <ul class="dropdown-menu">
                                <!-- User image -->
                                <li class="user-header bg-light-blue">
                                    <img src="img/avatar3.png" class="img-circle" alt="User Image" />
                                    <p>
                                        Jane Doe - Web Developer
                                        <small>Member since Nov. 2012</small>
                                    </p>
                                </li>
                                <!-- Menu Footer-->
                                <li class="user-footer">
                                    <div class="pull-left">
                                        <a href="#" class="btn btn-default btn-flat">Profile</a>
                                    </div>
                                    <div class="pull-right">
                                        <a href="#" class="btn btn-default btn-flat">Sign out</a>
                                    </div>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="left-side sidebar-offcanvas">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="img/avatar3.png" class="img-circle" alt="User Image" />
                        </div>
                        <div class="pull-left info">
                            <p>Hello, Jane</p>

                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                    <!-- search form -->
                    <form action="#" method="get" class="sidebar-form">
                        <div class="input-group">
                            <input type="text" name="q" class="form-control" placeholder="Search..."/>
                            <span class="input-group-btn">
                                <button type='submit' name='seach' id='search-btn' class="btn btn-flat"><i class="fa fa-search"></i></button>
                            </span>
                        </div>
                    </form>
                    <!-- /.search form -->
                    
                    <ul class="sidebar-menu">
                    	<%=str_menu %>
						<!-- <li class="active">
						    <a href="index.html">
						        <i class="fa fa-dashboard"></i> <span>Dashboard</span>
						    </a>
						</li>
                        <li>
                            <a href="pages/widgets.html">
                                <i class="fa fa-th"></i><span>Widgets</span>fa-bar-chart-o,fa-laptop,fa-edit,fa-table,fa-calendar
                            </a>
                        </li>
						<li class="treeview">
						    <a href="#">
						        <i class="fa fa-folder"></i><span>Examples</span>
						        <i class="fa fa-angle-left pull-right"></i>
						    </a>
						    <ul class="treeview-menu">
                                <li><a href=""><i class="fa fa-angle-double-right"></i> Invoice</a></li>
                                <li><a href="pages/examples/login.html"><i class="fa fa-angle-double-right"></i> Login</a></li>
                                <li><a href="pages/examples/register.html"><i class="fa fa-angle-double-right"></i> Register</a></li>
                                <li><a href="pages/examples/lockscreen.html"><i class="fa fa-angle-double-right"></i> Lockscreen</a></li>
                                <li><a href="pages/examples/404.html"><i class="fa fa-angle-double-right"></i> 404 Error</a></li>
                                <li><a href="pages/examples/500.html"><i class="fa fa-angle-double-right"></i> 500 Error</a></li>
                                <li><a href="pages/examples/blank.html"><i class="fa fa-angle-double-right"></i> Blank Page</a></li>
                           </ul>
                         </li> -->
                    </ul>
                    
                </section>
                <!-- /.sidebar -->
            </aside>

            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Dashboard
                        <small>Control panel</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Dashboard</li>
                    </ol>
                </section>
				
                <!-- Main content -->
                <section class="content">
										
					<iframe name="content" id="mframe" src="content.html" scrolling="auto" height="100%" width="100%" onload="Javascript:setHeight(this)" frameborder="0"></iframe>

                </section><!-- /.content -->
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->

        
        <script src="js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/app.js" type="text/javascript"></script>
		

    </body>
</html>