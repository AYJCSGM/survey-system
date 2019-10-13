<%@ page language="java" 
         import="java.util.*" 
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header" class="wrap">
	<img src="${pageContext.request.contextPath }/images/logo.gif" />
</div>
<div id="navbar" class="wrap">
	<div class="profile">
	    <c:if test="${CurrentUser==null }">
	       <a href="${pageContext.request.contextPath }/login">登录</a>&nbsp;&nbsp;
	       <a href="${pageContext.request.contextPath }/reg">注册</a>
	    </c:if>
		<c:if test="${CurrentUser!=null }">
	       	您好，${CurrentUser.name }&nbsp;&nbsp;
	       	<a href="${pageContext.request.contextPath }/logout">退出</a>
	    </c:if>
		<span class="return"><a href="${pageContext.request.contextPath }/list">返回列表</a></span>
		<span class="addnew"><a href="${pageContext.request.contextPath }/m/add">添加新投票</a></span>
		<span class="modify"><a href="${pageContext.request.contextPath }/m/modify">维护</a></span>	
	</div>
	<div class="search">
		<form method="post" action="${pageContext.request.contextPath }/search">
			<input type="text" name="keywords" class="input-text" value="${keywords}"/><input type="submit" name="submit" class="input-button" value="" />
		</form>
	</div>
</div>
         