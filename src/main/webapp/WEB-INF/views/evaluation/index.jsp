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
		
		$('#registerbtn').on('click', function(){
			$.ajax({
				url:'${path}/user/sessioncheck',
				dataType:'text',
				success:function(result){
					if(result != ''){
						$('#registerModal').modal('toggle');
					}
					else {
						alert('로그인을 해주세요');
						location.href="${path}/user/login";
					}
				},
				error:function(result){
					alert('error');
					console.log(result);
				}
			})
		});
		
		$('#reportbtn').on('click', function(){
			$.ajax({
				url:'${path}/user/sessioncheck',
				dataType:'text',
				success:function(result){
					if(result != ''){
						$('#reportModal').modal('toggle');
					}
					else {
						alert('로그인을 해주세요');
						location.href="${path}/user/login";
					}
				},
				error:function(result){
					alert('error');
					console.log(result);
				}
			})
		});
		
		$('#evaluationAddbtn').on('click',function(e){
			e.preventDefault();
			var userid = "<%=session.getAttribute("userID") %>";
			var lecturename = $('#lectureName').val();
			var professorname = $('#professorName').val();
			var lectureyear = $('#lectureYear').val();
			var semesterdivide = $('#semesterDivide').val();
			var lecturedivide = $('#lectureDivide').val();
			var evaluationtitle = $('#evaluationTitle').val();
			var evaluationcontent = $('#evaluationContent').val();
			var totalscore = $('#totalScore').val();
			var creditscore = $('#creditScore').val();
			var comfortablescore = $('#comfortableScore').val();
			var lecturescore = $('#lectureScore').val();
			if (lecturename == '' || professorname == '' || evaluationtitle == '' || evaluationcontent == ''){
				alert('입력이 안된 사항이 있습니다.');
				return;
			}
			else{
				$.ajax({
					type:'post',
					contentType:'application/json',
					url:'${path}/evaluation/'+userid,
					data:JSON.stringify({
						userid:userid, lecturename:lecturename, professorname:professorname, lectureyear:lectureyear, semesterdivide:semesterdivide, lecturedivide:lecturedivide,
						evaluationtitle:evaluationtitle, evaluationcontent:evaluationcontent, totalscore:totalscore, creditscore:creditscore, comfortablescore:comfortablescore, lecturescore:lecturescore
					}),
					dataType:'text',
					success:function(result){
						if(result != null){
							alert(result);
							$('#registerModal').modal('toggle');
							location.href="${path}/evaluation/index";
						}
						else{
							alert('등록실패');
						}
					},
					error:function(result){
						alert('error');
						console.log(result);
					}
				});		
			}
		});
		
		$('#sendreportbtn').on('click', function(e){
			e.preventDefault();
			var reporttitle = $('#reporttitle').val();
			var reportcontent = $('#reportcontent').val();		
			if (reporttitle == '' || reportcontent == ''){
				alert('입력안된 사항이 있습니다.');
			}
			else{
				$.ajax({
					type:'post',
					contentType:'application/json; charset=utf-8',
					url:'${path}/evaluation/report',
					data:JSON.stringify({reporttitle:reporttitle, reportcontent:reportcontent}),
					dataType:'text',
					success:function(result){
						alert(result);
						$('#reportModal').modal('toggle');
					},
					error:function(result){
						alert('error');
						console.log(result);
					}
				});
			}
		});
		
		$('.choicepage').on('click', function(e){
			e.preventDefault();
			var curPage = $(this).attr('href');
			var key = $('#key').val();
			var search = $('#search').val();
			var searchtype = $('#searchtype').val();
			$(location).attr('href','${path}/evaluation/index?curPage='+curPage+'&key='+key+'&search='+search+'&searchtype='+searchtype);
		});
		
		$('.likebtn').on('click', function(e){
			e.preventDefault();
			var evaluationid = $(this).attr('onclick');
			var professorname = $(this).attr('id');
			var lecturename = $(this).attr('target');
			$.ajax({
				type:'post',
				contentType:'application/json',
				url:'${path}/evaluation/likeyupdate',
				data:JSON.stringify({evaluationid:evaluationid, professorname:professorname, lecturename:lecturename}),
				dataType:'json',
				success:function(result){
					$('.'+result.evaluationid).empty();
					$('.'+result.evaluationid).append(result.likecnt);
				},
				error:function(result){
					alert('error');
					console.log(result);
				}
			});
		})
		$('.deletebtn').on('click', function(e){
			e.preventDefault();
			var result = confirm('삭제하시겠습니까?');
			if(result){
				var evaluationid = $(this).attr('onclick');
				$(location).attr('href','${path}/evaluation/delete?evaluationid='+evaluationid);
			}
		})
		
		$('#lectureName').on('change',function(){
			var lectureName= $('#lectureName').val();
			$('#professorName').empty();
			$('#professorName').append('<option value="">선택하세요</option>');
			$.ajax({
				type:'post',
				contentType:'application/json',
				url:'${path}/evaluation/selectprofessorname?lectureName='+lectureName,
				dataType:'json',
				success:function(result){
					$.each(result, function(i, item){
						$('#professorName').append('<option value="'+item.professorname+'">'+item.professorname+'</opdtion');
					});
					
				},
				error:function(result){
					alert('error');
					console.log(result);
				}
			});
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
	
	<section class="container">
		<form method="get" action="${path}/evaluation/index" class="form-inline mt-3">
			<input type="hidden" name="searchcurPage" value="1">
			<select id="key" name="key" class="form-control mx-1 mt-2">
				<option value="" <c:out value="${pdto.key == '' ? 'selected': ''}"/>>전체</option>
				<option value="전공" <c:out value="${pdto.key == '전공' ? 'selected': ''}"/>>전공</option>
				<option value="교양" <c:out value="${pdto.key == '교양' ? 'selected': ''}"/>>교양</option>
				<option value="기타" <c:out value="${pdto.key == '기타' ? 'selected': ''}"/>>기타</option>
			</select>
			<select id="searchtype" name="searchtype" class="form-control mx-1 mt-2">
				<option value="" <c:out value="${pdto.searchtype == '' ? 'selected': ''}"/>>최신순</option>
				<option value="likecount" <c:out value="${pdto.searchtype == 'likecount' ? 'selected': ''}"/>>추천순</option>
			</select>
			<input type="text" id="search" name="search" class="form-control mx-1 mt-2" placeholder="내용을 입력하세요." value="${pdto.search}">
			<button type="submit" class="btn btn-primary mx-1 mt-2">검색</button>
			<a id="registerbtn" class="btn btn-primary mx-1 mt-2" data-toggle="modal">등록하기</a>
			<a id="reportbtn" class="btn btn-danger mx-1 mt-2" data-toggle="modal">신고하기</a>
		</form>
		<!-- 반복문으로 데이터 가져와서 출력  -->
		<c:forEach var="evaluation" items="${list}">
		<div class="card bg-light mt-3">
			<div class="card-header bg-ligth">
				<div class="row">
					<div class="col-8 text-left">강의 : ${evaluation.lecturename}&nbsp;<small> 교수 : ${evaluation.professorname}</small></div>
					<div class="col-4 text-right">종합<span style="color: red;">${evaluation.totalscore}</span></div>
				</div>
				<div class="card-body">
					<h5 class="card-title">${evaluation.evaluationtitle}&nbsp;<small>(${evaluation.lectureyear}년 ${evaluation.semesterdivide} ${evaluation.lecturedivide})(<fmt:formatDate timeZone="KST" pattern="yyyy-MM-dd HH:mm:ss" value="${evaluation.regdate}"/>/${evaluation.userid}님이 등록)</small>
					</h5>
					<p class="card-text">${evaluation.evaluationcontent}</p>
					<div class="row">
						<div class='col-9 text-left'>
							성적<span style="color: red;">${evaluation.creditscore}</span>
							분위기<span style="color: red;">${evaluation.comfortablescore}</span>
							강의<span style="color: red;">${evaluation.lecturescore}</span>
							추천수<span class="${evaluation.evaluationid}" style="color: green;">${evaluation.likecount}</span>
						</div>
						<div class="col-3 text-right">
							<a type="button" onclick="${evaluation.evaluationid}" target="${evaluation.lecturename}" id="${evaluation.professorname}" class="likebtn btn btn-outline-primary">추천</a>
							<a type="button" onclick="${evaluation.evaluationid}" class="deletebtn btn btn-outline-danger">삭제</a>							
						</div>
					</div>
				</div>
			</div>
		</div>
		</c:forEach>
	</section>
	
	<div class="container mt-4">
		<div class="text-center">
			<c:if test="${pdto.startPage > 5}">
				<a type="button" href="${pdto.startPage-1}" class="choicepage btn btn-outline-info text-dark">이전</a>
			</c:if>
			<c:if test="${pdto.startPage != 0}">
				<c:forEach var="i" begin="${pdto.startPage}" end="${pdto.endPage}">
					<a type="button" class="choicepage btn btn-outline-dark text-dark" href="${i}">${i}</a>			
				</c:forEach>
			</c:if>
			<c:if test="${pdto.endPage > pdto.totPage}">
				<a type="button" href="${pdto.endPage+1}" class="choicepage btn btn-outline-info text-dark">다음</a>
			</c:if>
		</div>
	</div>
	
	<div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modal">평가등록</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form method="post">
						<div class="form-row">
							<div class="form-group col-sm-6">
								<label>강의명</label>
								<select id="lectureName" name="lectureName" class="form-control">
									<option value="">선택하세요</option>
									<c:forEach var="lnlist" items="${lnlist}">
										<option value="${lnlist.lecturename}">${lnlist.lecturename}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group col-sm-6">
								<label>교수명</label>
								<select id="professorName" name="professorName" class="form-control">
									<option value="">선택하세요</option>
								</select>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-sm-4">
								<label>수강연도</label>
								<select id="lectureYear" name="lectureYear" class="form-control">
									<option value="2010">2010</option>
									<option value="2011">2011</option>
									<option value="2012">2012</option>
									<option value="2013">2013</option>
									<option value="2014">2014</option>
									<option value="2015">2015</option>
									<option value="2016">2016</option>
									<option value="2017">2017</option>
									<option value="2018">2018</option>
									<option value="2019">2019</option>
									<option value="2020" selected="selected">2020</option>
									<option value="2021">2021</option>
								</select>
							</div>
							<div class="form-group col-sm-4">
								<label>수강학기</label>
								<select id="semesterDivide" name="semesterDivide" class="form-control">
									<option value="1학기">1학기</option>
									<option value="여름학기">여름학기</option>
									<option value="2학기">2학기</option>
									<option value="겨울학기">겨울학기</option>
								</select>
							</div>
							<div class="form-group col-sm-4">
								<label>강의구분</label>
								<select id="lectureDivide" name="lectureDivide" class="form-control">
									<option value="전공">전공</option>
									<option value="교양">교양</option>
									<option value="기타">기타</option>
								</select>
							</div>
						</div>
						<div class="from-group">
							<label>제목</label>
							<input type="text" id="evaluationTitle" name="evaluationTitle" class="form-control" maxlength="30">
						</div>
						<div class="form-group">
							<label>내용</label>
							<textarea id="evaluationContent" name="evaluationContent" class="form-control" maxlength="2048" style="height: 180px;"></textarea>
						</div>
						<div class="form-row">
							<div class="form-group col-sm-3">
								<label>종합</label>
								<select id="totalScore" name="totalScore" class="form-control">
									<option value="A">A</option>
									<option value="B">B</option>
									<option value="C">C</option>
									<option value="D">D</option>
									<option value="F">F</option>
								</select>
							</div>
							<div class="form-group col-sm-3">
								<label>성적</label>
								<select id="creditScore" name="creditScore" class="form-control">
									<option value="A">A</option>
									<option value="B">B</option>
									<option value="C">C</option>
									<option value="D">D</option>
									<option value="F">F</option>
								</select>
							</div>
							<div class="form-group col-sm-3">
								<label>꿀농도</label>
								<select id="comfortableScore" name="comfortableScore" class="form-control">
									<option value="A">A</option>
									<option value="B">B</option>
									<option value="C">C</option>
									<option value="D">D</option>
									<option value="F">F</option>
								</select>
							</div>
							<div class="form-group col-sm-3">
								<label>강의</label>
								<select id="lectureScore" name="lectureScore" class="form-control">
									<option value="A">A</option>
									<option value="B">B</option>
									<option value="C">C</option>
									<option value="D">D</option>
									<option value="F">F</option>
								</select>
							</div>
						</div>
						<div class="modal-footer">
							<button id="evaluationAddbtn" type="submit" class="btn btn-primary">등록하기</button>
							<button id="evaluationCancelbtn" type="button" class='btn btn-secondary' data-dismiss="modal">취소</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="reportModal" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modal">신고하기</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
						<div class="from-group">
							<label>신고제목</label>
							<input type="text" id="reporttitle" name="reportTitle" class="form-control" maxlength="30">
						</div>
						<div class="form-group">
							<label>신고내용</label>
							<textarea id="reportcontent" name="reportContent" class="form-control" maxlength="2048" style="height: 180px;"></textarea>
						</div>
						<div class="modal-footer">
							<button id="sendreportbtn" type="button" class="btn btn-danger">신고하기</button>
							<button type="button" class='btn btn-secondary' data-dismiss="modal">취소</button>
						</div>
				</div>
			</div>
		</div>
	</div>
	<footer class="bg-dark mt-4 p-5 text-center" style="color: #FFFFFF;">
		Copyright &copy; 2020-11 한성현 developer.
	</footer>
</body>
</html>