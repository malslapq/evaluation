<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>이메일 인증</title>
<script type="text/javascript">
	$(function(){
		$('#resendemailbtn').on('click',function(e){
			e.preventDefault();
			$.ajax({
				type:'post',
				url:'${path}/user/Resendemail',
				dataType:'text',
				success:function(result){
					alert(result);
				},
				error:function(result){
					alert('error');
					console.log(result);
				}
			})
		});
		$('#evaluationPage').on('click',function(e){
			e.preventDefault();
			var userid = "<%=session.getAttribute("userID") %>";
			if(userid == 'null'){
				alert('로그인후 이용할 수 있습니다.');
				return;
			}
			else{
				location.href="${path}/evaluation/index?curPage=1";
			}
		});
	});
</script>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="${path}/user/index">강의평가 웹사이트</a>
		<button class="navbar-toggler bg-light" type="button" data-toggle="collapse" data-target="#navbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a id="evaluationPage" type="button" class="nav-link">강의평가</a>
				</li>
				<li class="nav-item dropdown">
					<c:if test="${sessionScope.userID != 'root'}">
						<a class="nav-link dropdown-toggle" type="button" id="dropdown" data-toggle="dropdown">
							회원메뉴
						</a>
						<div class="dropdown-menu" aria-labelledby="dropdown">
							<c:if test="${sessionScope.userID == null || sessionScope.userID == ''}">
								<a class="dropdown-item" href="${path}/user/loginform">로그인</a>
								<a class="dropdown-item" href="${path}/user/joinform">회원가입</a>
							</c:if>
							<c:if test="${sessionScope.userID != null}">
								<a class="dropdown-item" href="${path}/user/logout">로그아웃</a>
								<a class="dropdown-item" href="${path}/user/updateform">회원정보 수정</a>
								<a class="dropdown-item" href="${path}/user/defectionform">회원탈퇴</a>
							</c:if>
						</div>
					</c:if>
				</li>
				<c:if test="${sessionScope.userID == 'root'}">
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" type="button" id="dropdown" data-toggle="dropdown">
							관리자메뉴
						</a>
						<div class="dropdown-menu" aria-labelledby="dropdown">
							<a class="dropdown-item" href="${path}/user/logout">로그아웃</a>
							<a class="dropdown-item" href="${path}/evaluation/lectureaddform">강의 및 교수 추가</a>
						</div>
					</li>
				</c:if>
			</ul>
		</div>
	</nav>
	
	<section class="container">
		<div class="alert alert-warning mt-4" role="alert">
			이메일 인증을 하셔야 이용이 가능합니다. 인증메일을 다시 받으시겠습니까?
		</div>
		<a href="이메일보내기" class="btn btn-primary" id="resendemailbtn">인증메일 다시 받기</a>
	</section>
	
	<footer class="bg-dark mt-4 p-5 text-center" style="color: #FFFFFF;">
		Copyright &copy; 2020-11 한성현 developer.
	</footer>
</body>
</html>