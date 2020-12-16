<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>회원탈퇴</title>
<script type="text/javascript">
	$(function() {
		$('#maintext').on('click',function(e){
			e.preventDefault();
	
		});
		$('#defectionbtn').on('click',function(e){
			e.preventDefault();
			var userPasswd = $('#userPasswd').val();
			if(userPasswd == ''){
				alert('비밀번호를 입력해주세요.');
				$('#userPasswd').focus();	
			}
			else{
				var result = confirm('정말 탈퇴하시겠습니까?');
				if (result){
					deleteform.submit();
				}
				else return;
			}
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
	
	<section class="container mt-4" style="max-width: 560px;">
		<form name="deleteform" method="post" action="${path}/user/defection">
			<div class="form-group">
				<h5>아이디</h5>
				<input type="text" id="userID" name="userID" class="form-control" value="${udto.userID}" readonly="readonly">
			</div>
			<div class="form-group">
				<h5>비밀번호</h5>
				<input type="password" id="userPasswd" name="userPasswd" class="form-control" placeholder="비밀번호를 입력하시고 회원탈퇴를 눌러주세요." >
			</div>
			<div class="form-group">
				<h5>이메일</h5>
				<input type="email" id="userEmail" name="userEmail" class="form-control" value="${udto.userEmail}" readonly="readonly">
				<div class="text-center">
					<button id="defectionbtn" type="submit" class="btn btn-primary mt-2">회원 탈퇴</button>
				</div>
			</div>
		</form>
	</section>
	
	<footer class="bg-dark mt-4 p-5 text-center" style="color: #FFFFFF;">
		Copyright &copy; 2020-11 한성현 developer.
	</footer>
</body>
</html>