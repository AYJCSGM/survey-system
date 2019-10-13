<%@ page language="java" 
         import="java.util.*,vip.itellyou.entity.Subject,vip.itellyou.entity.Option" 
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发布新投票</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" />
<script type="text/javascript">
var isIE = !!document.all;
function addOption()
{
	var voteoptions = document.getElementById("voteoptions");
	var _p = document.createElement("p");
	var _input = document.createElement("input");
	_input.type = "text";
	_input.className = "input-text";
	_input.setAttribute("name", "options");
	_p.appendChild(_input);
	var _a = document.createElement("a");
	_a.className = "del";
	_a.setAttribute("href", "javascript:;");
	if(isIE) {
		_a.attachEvent("onclick", delOption);
	} else {
		_a.addEventListener("click", delOption, false);
	}
	_a.appendChild(document.createTextNode("删除"));
	_p.appendChild(_a);
	voteoptions.appendChild(_p);
}
function delOption(e)
{
	if(!e) e = window.event;
	var a = e.srcElement || e.target;
	var obj = a.parentNode;
	obj.parentNode.removeChild(obj);
}
</script>
</head>
<body>
<%@ include file="top.jsp" %>
<div id="voteManage" class="box">
	<h2>添加新投票</h2>
	<div class="content">
	<form method="post" action="${pageContext.request.contextPath}/m/doAdd">
			<dl>
				<dt>投票内容：</dt>
				<dd>
				   <input type="hidden" name="id" value="${subject.id }"/>
				   <input type="text" class="input-text" name="title"  value="${subject.title }"/>
				</dd>
				<dt>投票类型：</dt>
				<dd>
		  		   <input type="radio" name="number" <c:if test="${subject.number==1 }"> checked="checked" </c:if> value="1" />单选
				   <input type="radio" name="number" <c:if test="${subject.number==2 }"> checked="checked" </c:if> value="2" />多选
				</dd>
				<c:if test="${subject.id!=null }">
					<dt>开始时间：</dt>
					<dd>
				  		<input type="text" class="input-text" name="startTime"  value="${subject.startTimeView }"/>
					</dd>
					<dt>结束时间：</dt>
					<dd>
		  		   		<input type="text" class="input-text" name="endTime"  value="${subject.endTimeView }"/>
					</dd>
				</c:if>
				<dt>投票选项：</dt>
				<c:if test="${subject.id==null }">
				<dd id="voteoptions">
					<p><input type="text" class="input-text" name="options" /></p>
					<p><input type="text" class="input-text" name="options" /></p>
				</dd>
				</c:if>
				<c:if test="${subject.id!=null}">
				<dd id="voteoptions">
					<c:forEach items="${subject.options}" var="option" varStatus="st">
						<p><input type="text" class="input-text" name="options" value="${option.content }"/><c:if test="${st.index>1}"><a class="del" href="javascript:;" onclick="delOption()">删除</a></c:if></p>
					</c:forEach>
				</dd>
				</c:if>
				<dt></dt>
				<dd class="button">
					<input type="image" src="${pageContext.request.contextPath }/images/button_submit.gif" />
					<a href="javascript:;" onclick="addOption()">增加选项</a>
					<a href="${pageContext.request.contextPath }/list">取消操作</a>
				</dd>
			</dl>
		</form>
		<div class="error">${message }</div>
		
		<c:remove  var="subject"  scope="session"  />
		<c:remove  var="message"  scope="session"  />
	</div>
</div>
</body>
</html>

         