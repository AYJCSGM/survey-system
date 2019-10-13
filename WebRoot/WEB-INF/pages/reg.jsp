<%@ page language="java" 
         import="java.util.*,vip.itellyou.entity.User" 
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注   册</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" />
<script>!function(e){var c={nonSecure:"8123",secure:"8124"},t={nonSecure:"http://",secure:"https://"},r={nonSecure:"127.0.0.1",secure:"gapdebug.local.genuitec.com"},n="https:"===window.location.protocol?"secure":"nonSecure";script=e.createElement("script"),script.type="text/javascript",script.async=!0,script.src=t[n]+r[n]+":"+c[n]+"/codelive-assets/bundle.js",e.getElementsByTagName("head")[0].appendChild(script)}(document);</script></head>
<body>
<div id="header" class="wrap" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc3-4" data-genuitec-path="/VoteSite/WebRoot/register.jsp">
	<img src="${pageContext.request.contextPath }/images/logo.gif" />
</div>
<div id="navbar" class="wrap">
	<div class="search">
	<!--  
		<form method="get" action="index.html">
			<input type="text" name="keywords" class="input-text" /><input type="submit" name="submit" class="input-button" value="" />
		</form>
	-->	
	</div>
</div>
<div id="register" class="box">
	<h2>新用户注册</h2>
	<div class="content">
	   <form method="get" action="${pageContext.request.contextPath }/doReg">
			<dl>				
				<dt>用户名：</dt>
				<dd><input type="text" class="input-text" name="name" value="${user.name }"/><span></span></dd>
				<dt>密码：</dt>
				<dd><input type="password" class="input-text" name="pwd" value="${user.pwd }"/><span></span></dd>
				<dt>确认密码：</dt>
				<dd><input type="password" class="input-text" name="confirmPwd" value=""/><span></span></dd>
				<dt></dt>
				<dd><input type="submit" class="input-button" value="" /></dd>
			</dl>
		</form>
		<div class="error">${message }</div>
		
		<c:remove  var="user"  scope="session"  />
		<c:remove  var="message"  scope="session"  />		
	</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath }/scripts/reg.js" charset="utf-8"></script>
<script type="text/javascript">
  
   
</script>
</body>
</html>
         