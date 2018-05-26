<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>

<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader("Expires",0);
%>

<style type="text/css">
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

.red:checked + span:before {
	top: -4px;
	left: -3px;
	width: 12px;
	height: 22px;
	border-top: 2px solid transparent;
	border-left: 2px solid transparent;
	border-right: 2px solid red!important; /* You need to change the colour here */
	border-bottom: 2px solid red!important; /* And here */
	-webkit-transform: rotate(40deg);
	-moz-transform: rotate(40deg);
	-ms-transform: rotate(40deg);
	-o-transform: rotate(40deg);
	transform: rotate(40deg);
	-webkit-backface-visibility: hidden;
	-webkit-transform-origin: 100% 100%;
	-moz-transform-origin: 100% 100%;
	-ms-transform-origin: 100% 100%;
	-o-transform-origin: 100% 100%;
	transform-origin: 100% 100%;
}
</style>

<script type="text/javascript">
$(document).ready(function() {
	$('.datepicker').datepicker({
		format: 'yyyy-mm-dd'
	});
});

function submitForm() {
	alert("확인");
	// 비밀번호 체크
	if ($('#password').val() != $('#password-confirm').val() ) {
		alert('비밀번호 입력을 확인해 주세요.');
		return false;
	}
}
</script>

<div class="row">
<div class="col s0 m3 l3"></div>
<div class="center-align col s12 m6 l6">
	<div class="section">
		<h5 class="indigo-text">Date Map 등록</h5>
		<div class="section"></div>
		
		<div class="container">
			<div class="z-depth-1 grey lighten-4 row" style="display: inline-block; padding: 32px 48px 0px 48px; border: 1px solid #EEE;">
			
				<form class="col s12" id="joinForm" name="joinForm" method="POST" action="/join" onsubmit="return submitForm()">
					
					<div class="row">
						<!-- email -->
						<div class="input-field col s12">
							<input class="validate" type="email" name="id" id="id" required />
							<label for="id">이메일</label>
						</div>
		
						<!-- password -->
						<div class="input-field col s12">
							<input type="password" name="password" id="password" class="validate" required />
							<label for="password">비밀번호</label>
						</div>
						
						<!-- password -->
						<div class="input-field col s12">
							<input type="password" name="password-confirm" id="password-confirm" required />
							<label for="password-confirm">비밀번호 확인</label>
						</div>
						
						<!-- name -->
						<div class="input-field col s12">
							<input type="text" name="name" id="name" required />
							<label for="name">이름</label>
						</div>
						
						<!-- nickname -->
						<div class="input-field col s12">
							<input type="text" name="nickname" id="nickname" required />
							<label for="nickname">닉네임</label>
						</div>
						
						<!-- sex -->
						<div class="input-field col s12">
							<p>
								<label><input name="sex" type="radio" value="1" checked /><span>남성</span></label>
								<label><input name="sex" type="radio" value="2" /><span>여성</span></label>
							</p>
						</div>
						
						<!-- birthday -->
						<div class="input-field col s12">
							<input type="text" class="datepicker" name="birthday" id="birthday" required />
							<label for="birthday">생일</label>
						</div>
		
						<!-- button -->
						<div class="center-align">
							<div class="row">
								<button id="btnForm" type="submit" class="col s12 btn btn-large waves-effect indigo">회원등록<i class="material-icons right">check</i></button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<div class="col s0 m3 l3"></div>
</div>
<%@ include file="layout/footer.jsp"%>