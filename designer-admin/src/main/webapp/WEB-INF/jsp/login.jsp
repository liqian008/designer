<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>Pannonia - premium responsive admin template by Eugene Kopyov</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<!--[if IE 8]><link href="css/ie8.css" rel="stylesheet" type="text/css" /><![endif]-->
<!--[if IE 9]><link href="css/ie9.css" rel="stylesheet" type="text/css" /><![endif]-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,600,700' rel='stylesheet' type='text/css'>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script>

<script type="text/javascript" src="js/plugins/forms/jquery.uniform.min.js"></script>

<script type="text/javascript" src="js/files/bootstrap.min.js"></script>

<script type="text/javascript" src="js/files/login.js"></script>

</head>

<body class="no-background">
	
	<jsp:include page="./inc/header.jsp"></jsp:include>
	
	
    <!-- Login block -->
    <div class="login">
        <div class="navbar">
            <div class="navbar-inner">
                <h6><i class="icon-user"></i>Login page</h6>
                <div class="nav pull-right">
                    <a href="login.html#" class="dropdown-toggle navbar-icon" data-toggle="dropdown"><i class="icon-cog"></i></a>
                    <ul class="dropdown-menu pull-right">
                        <li><a href="login.html#"><i class="icon-plus"></i>Register</a></li>
                        <li><a href="login.html#"><i class="icon-refresh"></i>Recover password</a></li>
                        <li><a href="login.html#"><i class="icon-cog"></i>Settings</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="well">
            <form action="./doLogin" class="row-fluid" method="post">
                <div class="control-group">
                    <label class="control-label">Username</label>
                    <div class="controls"><input class="span12" type="text" name="userName" placeholder="username" /></div>
                </div>
                
                <div class="control-group">
                    <label class="control-label">Password:</label>
                    <div class="controls"><input class="span12" type="password" name="passWord" placeholder="password" /></div>
                </div>

                <div class="control-group">
                    <div class="controls"><label class="checkbox inline"><input type="checkbox" name="checkbox1" class="styled" value="" checked="checked">Remember me</label></div>
                </div>

                <div class="login-btn"><input type="submit" value="Log me in" class="btn btn-danger btn-block" /></div>
            </form>
        </div>
    </div>
    <!-- /login block -->

	
	
	<jsp:include page="./inc/footer.jsp"></jsp:include>
	

</body>
</html>
