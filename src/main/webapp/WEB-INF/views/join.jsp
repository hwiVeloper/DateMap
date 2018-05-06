<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>

<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader("Expires",0);
%>

<script type="text/javascript">
$(document).ready(function() {
	$('.datepicker').datepicker({
		format: 'yyyy-mm-dd'
	});
	
	/* $('#joinSubmit').click(function() {
		$.ajax({
			method : 'POST',
			url : "/join",
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
			dataType : "text",
			data : JSON.stringify({
				id: $('#id').val(),
				password: $('#password').val()
			}),
			success : function() {
				console.log('success');
			},
			error : function(){
				alert('error');
			}
		});
	}); */
});
</script>



<div class="valign-wrapper" style="">
	<div class="row">
		<div class="col s12 m4 l4"></div>
		<div class="col s12 m5 l4">
			<h3>회원가입</h3>
			<div class="row">
				<form id="joinForm">
					<!-- email -->
					<div class="input-field col s12">
						<input type="email" name="id" id="id" required />
						<label for="id">이메일</label>
					</div>

					<!-- password -->
					<div class="input-field col s12">
						<input type="password" name="password" id="password" required />
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
							<label for="group1">성별</label>
						</p>
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
					<div class="right-align">
						<button id="joinSubmit" class="btn waves-effect waves-light" type="submit" name="action">
							회원가입 <i class="material-icons right">send</i>
						</button>
					</div>
				</form>
			</div>
		</div>
		<div class="col s12 m4 l4"></div>
	</div>
</div>

<%@ include file="layout/footer.jsp"%>