<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../layout/header.jsp" %>
<%@ include file="../layout/nav.jsp" %>
<%@ page session="true" %>

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
</div>

<%@ include file="../layout/footer.jsp" %>
