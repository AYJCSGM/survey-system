<%@ page language="java" 
         import="java.util.*,vip.itellyou.entity.Subject,vip.itellyou.entity.Option" 
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>参与投票</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" />
</head>
<body>
<%@ include file="top.jsp" %>
<div id="vote" class="wrap">
	<h2>参与投票</h2>
	<ul class="list">
		<li>
			<h4>${subject.title}</h4>
			<p class="info">共有 ${subject.optionCount}个选项，已有${subject.userCount}个网友参与了投票。</p>
			<form method="post" action="${pageContext.request.contextPath}/m/doVote">
			    <input type="hidden" name="subjectId" value="${subject.id }"/> 
				<ol>
				     <c:forEach items="${subject.options}" var="option" varStatus="status"> 
					     <li><input <c:if test="${subject.number==2}">type="checkbox"</c:if><c:if test="${subject.number==1}">type="radio"</c:if> name="options"  value="${option.id }"/>${option.content}</li>
				     </c:forEach>
				 				 
				</ol>
				<p class="voteView"><input type="image" src="${pageContext.request.contextPath}/images/button_vote.gif" /><a href="${pageContext.request.contextPath}/m/view"><img src="${pageContext.request.contextPath}/images/button_view.gif" /></a></p>
			</form>
			<div class="error">${message }</div>		
		
		    <c:remove  var="message"  scope="session"  />
		</li>
	</ul>
</div>
</body>
</html>
         