<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ include file="layout/header.jsp" %>

<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader("Expires",0);
%>

<c:if test="${ !empty loginFailMsg }">
<script>
M.toast({html: '<c:out value="${ loginFailMsg }" />'});
</script>
</c:if>

<c:if test="${ !empty logoutMsg }">
<script>
M.toast({html: '<c:out value="${ logoutMsg }" />'});
</script>
</c:if>

<style>
body {
	display: flex;
	min-height: 100vh;
	flex-direction: column;
}

main {
	flex: 1 0 auto;
}

body {
	background: #fff;
}

.input-field input[type=date]:focus+label,
.input-field input[type=text]:focus+label,
.input-field input[type=email]:focus+label,
.input-field input[type=password]:focus+label {
	/* color: #e91e63; */
}

.input-field input[type=date]:focus,
.input-field input[type=text]:focus,
.input-field input[type=email]:focus,
.input-field input[type=password]:focus {
	border-bottom: 2px solid;
	box-shadow: none;
}
</style>

<div class="section"></div>
<div class="center-align">
	<!-- <img class="responsive-img" style="width: 250px;" src="https://i.imgur.com/ax0NCsK.gif" /> -->
	<div class="section"></div>

	<h5 class="indigo-text">Date Map 로그인</h5>
	<div class="section"></div>

	<div class="container">
		<div class="z-depth-1 grey lighten-4 row" style="display: inline-block; padding: 32px 48px 0px 48px; border: 1px solid #EEE;">

			<form class="col s12" method="post" action="/login">
				<div class='row'>
					<div class='col s12'></div>
				</div>

				<div class="row">
					<div class="input-field col s12">
						<input class='validate' type="email" name="email" id="email" />
						<label for="email">이메일을 입력해 주세요.</label>
					</div>
				</div>

				<div class="row">
					<div class="input-field col s12">
						<input class="validate" type="password" name="password" id="password" />
						<label for="password">비밀번호를 입력해 주세요.</label>
					</div>
					<label style="float: right;">
					<a class="pink-text" href="#!"><b>비밀번호 찾기</b></a>
					</label>
				</div>

				<br />
				<div class="center-align">
					<div class="row">
						<button type="submit" name="" class="col s12 btn btn-large waves-effect indigo">로그인<i class="material-icons right">send</i></button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<a href="/join">계정 만들기</a>
</div>

<%@ include file="layout/footer.jsp" %>
