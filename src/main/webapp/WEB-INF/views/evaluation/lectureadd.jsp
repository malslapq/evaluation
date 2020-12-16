<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>강의평가 웹사이트</title>
<script type="text/javascript">
	$(function(){
		$('#evaluationPage').on('click',function(e){
			e.preventDefault();
			var userid = "<%=session.getAttribute("userID") %>";
			if(userid == 'null'){
				alert('로그인후 이용할 수 있습니다.');
				location.href="${path}/user/login";
				return;
			}
			else{
				location.href="${path}/evaluation/index?curPage=1";
			}
		});
		
		$('#addmodalbtn').on('click', function(){
			$.ajax({
				url:'${path}/user/sessioncheck',
				dataType:'text',
				success:function(result){
					if(result != ''){
						$('#addprofessorModal').modal('toggle');
					}
					else {
						alert('관리자 계정만 사용 가능합니다.');
						return;
					}
				},
				error:function(result){
					alert('error');
					console.log(result);
				}
			})
		});
		
		$('#lectureName').on('change',function(){
			var lectureName= $('#lectureName').val();
			$('#professordetail').empty();
			$.ajax({
				type:'post',
				contentType:'application/json',
				url:'${path}/evaluation/professordetail?lectureName='+lectureName,
				dataType:'json',
				success:function(result){
					$.each(result, function(i, plist){
						$('#professordetail').append('<div class="card bg-dark text-white mx-auto mt-3" style="width: 23%">'+
														'<div class="card-body">'+
															'<label>강의 명 : '+plist.lecturename+'</label><br>'+
															'<label>교수 명 : '+plist.professorname+'</label><br>'+
															'<label>등록된 평가 : '+plist.evaluation+'개</label>'+
															'<div>'+
																'<a href="'+plist.lecturename+'" id="'+plist.professorname+'" class="deleteProfessor btn btn-light form-control mt-2">교수 삭제</a>'+
															'</div>'+
														'</div>'+
													'</div>');
					});
					
				},
				error:function(result){
					alert('error');
					console.log(result);
				}
			});
		})
		
		$(document).on('click','.deleteProfessor', function(e){
			e.preventDefault();
			var deleteresult = confirm('삭제하시겠습니까?');
			var lecturename = $(this).attr('href');
			var professorname = $(this).attr('id');
			if (deleteresult){
				location.href="${path}/evaluation/deleteprofessor?lecturename="+lecturename+"&professorname="+professorname;				
			}
			else return;
		});
		
		$('#deletelecture').on('click', function(e){
			e.preventDefault();
			var lecturename = $('#lectureName').val();
			if(lecturename == ''){
				alert('강의를 선택해주세요');
				return;
			}
			var deleteresult = confirm('현재 선택된 강의를 삭제하시겠습니까?');
			if(deleteresult){
				location.href="${path}/evaluation/deletelecture?lecturename="+lecturename;
			}
			else return;
		});
		
	});
</script>

</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="${path}/user/index">강의평가 웹사이트</a>
		<button class="navbar-toggler bg-dark" type="button" data-toggle="collapse" data-target="#navbar">
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
							<a class="dropdown-item" href="${path}/evaluation/lectureaddform">강의 및 교수 등록</a>
						</div>
					</li>
				</c:if>
			</ul>
		</div>
	</nav>
	
	<section class="container">
		<div class="row justify-content-center mt-3" >
			<select id="lectureName" class="lectureName form-control mr-2" style="width: 200px;">
				<option value="">강의를 선택하세요</option>
				<c:if test="${lnlist != null}">
					<c:forEach var="lnlist" items="${lnlist}">
						<option value="${lnlist.lecturename}" >${lnlist.lecturename}</option>
					</c:forEach>
				</c:if>
			</select>
			<a type="button" id="addmodalbtn" class="btn btn-primary mr-2" data-toggle="modal">강의 및 교수 추가</a>
			<button id="deletelecture" class="btn btn-danger mr-2">강의삭제</button>
		</div>
		<div id="professordetail" class="container row mt-3 align-items-center">
		</div>
	</section>

	<div class="modal fade" id="addprofessorModal" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modal">강의 및 교수 등록</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="${path}/evaluation/addprofessor">
						<div class="from-group">
							<label>등록할 강의 명</label>
							<input type="text" id="lecturename" name="lecturename" class="form-control" maxlength="12">
						</div>
						<div class="form-group">
							<label>등록할 교수 명</label>
							<input type="text" id="professorname" name="professorname" class="form-control" maxlength="10">
						</div>
						<div class="modal-footer">
							<button id="addprofessorbtn" type="submit" class="btn btn-primary">강의 및 교수 등록</button>
							<button type="button" class='btn btn-secondary' data-dismiss="modal">취소</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<footer class="bg-dark mt-4 p-5 text-center" style="color: #FFFFFF;">
		Copyright &copy; 2020-11 한성현 developer.
	</footer>
</body>
</html>