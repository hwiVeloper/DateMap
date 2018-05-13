<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ include file="layout/header.jsp" %>

<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader("Expires",0);
%>
   <div class="valign-wrapper" style="height: 100vh;">
      <div class="row">
         <div class="col s12">
            <h3>로그인</h3>
            <div class="row">
               <form action="/loginPost" method="POST">
                  <!-- email -->
                  <div class="input-field col s12">
                     <input type="email" name="email" id="email" /> 
                     <label for="email">이메일</label>
                  </div>
                  
                  <!-- password -->
                  <div class="input-field col s12">
                     <input type="password" name="password" id="password" />
                     <label for="password">비밀번호</label>
                  </div>
                  
                  <!-- button -->
                  <div class="right-align">
                     <button class="btn waves-effect waves-light" type="submit" name="action">
                        로그인 <i class="material-icons right">send</i>
                     </button>
                  </div>
               </form>
            </div>
         </div>
      </div>
   </div>

<%@ include file="layout/footer.jsp" %>
