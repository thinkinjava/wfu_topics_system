<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>计算机工程学院系统登录</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/style/login.css"> 
<script type="text/javascript" src="<%=path%>/js/jquery-1.10.2.min.js" ></script>
<script type="text/javascript">
	$(function(){
			//回车提交
			$(document).keyup(function(event){
				  if(event.keyCode ==13){
					  login();
				  }
				});
			//点击提交
			$('#login_submit').click(function(){
				login();
			});
			$('#Kaptcha').click(     
			        function() {     
			           $(this).hide().attr('src','Kaptcha.jpg?' + Math.floor(Math.random() * 100)).fadeIn();     
			    });
			
		});
	
	function login(){
		if($("input[name='userName']").val()==''){
			alert("请输入用户名");
		}else if($("input[name='passWord']").val()==''){
			alert("请输入密码");
		}else if($("input[name='captcha']").val()==''){
			alert("请输入验证码");
		}else{
			$.ajax({
				url:'<%=path %>/loginController/login',
				data:$('#login_form').serialize(),
				type:'post',
				datatype:'json',
				success:function(data){
					var d=eval('('+data+')');
					if(d.status=="true"){
						window.location.href='index.jsp';
					}else{
						alert(d.message);
						 $("#Kaptcha").hide().attr('src','Kaptcha.jpg?' + Math.floor(Math.random() * 100)).fadeIn();
						//window.location.href='login.jsp';
					}
				}
			});
		}

	}
</script>
</head>

<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				
			</h1>

			<div class="login_headerContent">
				<div class="navList">
					<ul>
						<li><a href="#">后台主页</a></li>
						<li><a href="http://www.wfu.edu.cn" target="_blank">潍坊学院主页</a></li>
					</ul>
				</div>
				<h2 class="login_title">请登录</h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form id="login_form" >

					<p>
						<label>用户名:</label>
						<input type="text" name="userName" style="width: 150px;"  id="username" />
					</p>
					<p>
						<label>密&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
						<input type="password" name="passWord" style="width: 150px;"  id="password"/>
					</p>
					<p>
						<label>验证码</label>			
						<input class="captcha" name="captcha" type="text" id="captcha"  nullmsg="请输入验证码!" />
						<img style="width:85px;height:35px;margin-top: -10px;" align="absmiddle" id="Kaptcha" src="Kaptcha.jpg"/>
					</p>
					<p>
						<!-- 0部门	,1教师,2学生,3访客 -->
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="userType" value="1"/>管理员
						<input type="radio" name="userType" value="1" checked="checked"/>教师
						<input type="radio" name="userType" value="2" />学生
						
					</p>
					<!--  <p>
						<label>验证码:</label>
						<input type="text" id="captcha_key" style="width: 70px;float:left;" name="captcha_key" class="login_input validate[required,maxSize[6]]" size="6" />
						<span><img src="" alt="点击刷新验证码" width="75" height="24" id="captcha"/></span>
					</p>
					<p>
						<label>记住我:</label>
						<input type="checkbox" id="rememberMe" name="rememberMe"/>
					</p>-->					
					<div class="login_bar" style="disply:block;float:left;">
						<input class="sub" type="button" id="login_submit" value=""/>
					</div>
				</form>
			</div>
			<div class="login_banner"><img src="<%=path%>/images/login_banner.jpg" /></div>
			<div class="login_main">
				<ul class="helpList">
					<li><a href="javascript:void(0)" >建议使用火狐,谷歌浏览器</a></li>
					<li><a href="javascript:void(0)" >账号密码都为自己的学号<br/>请登录后及时修改</a></li>
				</ul>
				

			</div>
		</div>
		<div id="login_footer">
			Copyright &copy; 2012-2013, wfu.edu.cn, All Rights Reserve.
		</div>
	</div>
</body>
</html>