<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>회원정보수정</title>
<script type="text/javascript">
	$(function() {
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
		
		// 패스워드 수정
		$('#passwdupdatebtn').on('click',function(e){
			e.preventDefault();
			$('#passwdform').attr('style','display:block');
			$('#passwdupdatebtn').attr('style','display:none');
		});
		$('#passwdcancelbtn').on('click',function(e){
			e.preventDefault();
			$('#passwdform').attr('style','display:none');
			$('#passwdupdatebtn').attr('style','display:block');
			$('#passwdupdatebtn').attr('style','width:59px');
		});
		$('#passwdupdate').on('click',function(e){
			e.preventDefault();
			var newuserPasswd = $('#newuserPasswd').val();
			var olduserPasswd = $('#olduserPasswd').val();
			if(newuserPasswd == '' || olduserPasswd == ''){
				alert('입력 안된 비밀번호란이 있습니다.');
			}
			else{
				$.ajax({
					type:'post',
					url:'${path}/user/passwdupdate?newuserPasswd='+newuserPasswd+"&olduserPasswd="+olduserPasswd,
					dataType:'text',
					success:function(result){
						alert(result);
						$('#newuserPasswd').val('');
						$('#olduserPasswd').val('');
						$('#passwdform').attr('style','display:none');
						$('#passwdupdatebtn').attr('style','display:block');
						$('#passwdupdatebtn').attr('style','width:59px');
					},
					error:function(result){
						console.log(result);
						alert("error");
					}
				});
			}
		});
		// 이메일 수정
		$('#emailupdatebtn').on('click',function(e){
			e.preventDefault();
			$('#emailform').attr('style','display:block');
			$('#emailupdatebtn').attr('style','display:none');
		});
		$('#emailcancelbtn').on('click',function(e){
			e.preventDefault();
			$('#emailform').attr('style','display:none');
			$('#emailupdatebtn').attr('style','display:block');
			$('#emailupdatebtn').attr('style','width:59px');
		});
		$('#emailupdate').on('click', function(e){
			e.preventDefault();
			var useremail = $('#userEmail').val();
			if(useremail == ''){
				alert('이메일을 입력해주세요.');
				return;
			}
			else{
				$.ajax({
					type:'post',
					url:'${path}/user/emailupdate?userEmail='+useremail,
					dataType:'json',
					success:function(result){
						alert(result.msg);
						$('#userEmail').val('');							
						$('#emailform').attr('style','display:none');
						$('#emailupdatebtn').attr('style','display:block');
						$('#emailupdatebtn').attr('style','width:59px');
						if (result.email != null){
							$('#email').val(result.email);
						}
					},
					error:function(result){
						console.log(result);
						alert("error");
					}
				});
			}
		})
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
			<div id="passwd" class="form-group">
				<h5>비밀번호</h5>
				<div id="passwdform" class="form-group mt-2" style="display:none">
					<input type="password" id="olduserPasswd" name="olduserPasswd" class="form-control" placeholder="기존 비밀번호를 입력해주세요.">
					<input type="password" id="newuserPasswd" name="newuserPasswd" class="form-control mt-2" placeholder="변경할 비밀번호를 입력해주세요.">
					<a id="passwdcancelbtn" class="btn btn-outline-dark mt-2">수정 취소</a>
					<a id="passwdupdate" class="btn btn-outline-dark mt-2">수정 완료</a>
				</div>
				<a id="passwdupdatebtn" href="#" class="btn btn-outline-dark mt-2">수정</a>
			</div>
			<div class="form-group">
				<h5>이메일</h5>
				<input type="email" id="email" class="form-control" value="${udto.userEmail}" readonly="readonly">
				<div id="emailform" class="form-group mt-2" style="display:none">
					<input type="email" id="userEmail" name="userEmail" class="form-control" placeholder="변경할 이메일을 입력해주세요.">
					<a id="emailcancelbtn" class="btn btn-outline-dark mt-2">수정 취소</a>
					<a id="emailupdate" class="btn btn-outline-dark mt-2">수정 완료</a>
				</div>
				<a id="emailupdatebtn" href="#" class="btn btn-outline-dark mt-2">수정</a>
			</div>
		</form>
	</section>
	
	<footer class="bg-dark mt-4 p-5 text-center" style="color: #FFFFFF;">
		Copyright &copy; 2020-11 한성현 developer.
	</footer>
</body>
</html>