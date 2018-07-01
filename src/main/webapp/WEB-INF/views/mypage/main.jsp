<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../layout/header.jsp" %>
<%@ include file="../layout/nav.jsp" %>
<%@ page session="true" %>

<style type="text/css">
.divider {
	height: 1px;
	border-bottom: 1px solid #000;
	margin-top:1em;
	margin-bottom:1em;
}
</style>

<div class="container">
	<div class="row">
		<div class="col s12" style="margin:1em;"></div>
		<div class="col s12">
			<div class="row">	
				<div class="col s4 center-align">
					<img class="circle responsive-img" style="width:150px" src="https://github.com/hwiveloper.png" alt="" />
				</div>
				<div class="col s8">
					<h4><c:out value="${ session.name }" /></h4>
					<h5><c:out value="${ session.nickname }" /></h5>
				</div>
			</div>
		</div>
	</div>
	<div class="row divider"></div>
	<div class="row">
	<c:forEach items="${ mypageList }" var="item">
		<div class="col s3">
			<img src="resources/upload/${ item.fileName }" onerror="this.src='/resources/noimage.png'" alt="" class="responsive-img" />
		</div>
	</c:forEach>
	</div>
</div>

<%@ include file="../layout/footer.jsp" %>
