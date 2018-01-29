<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/WEB-INF/tld/custom_tag.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지 게시판 관리</title>
<script type="text/javascript" src="/resources/include/js/board.js"></script>
<script type="text/javascript">
	search = "<c:out value = '${data.search}' />";
	start_date = "<c:out value = '${data.start_date}' />";
	end_date = "<c:out value = '${data.end_date}' />";
	keyword = "<c:out value = '${data.keyword}' />";
</script>
</head>
<body>
	<h2 class="sub-header">게시판 리스트</h2>
	<div class="well">
		<form class="form-inline" id="f_search">
			<input type="hidden" name="page" id="page"> <input
				type="hidden" name="pageSize" id="pageSize">

			<h3>
				<span class="label label-success">검색조건</span>
			</h3>

			<div class="form-group">
				<select id="search" name="search" class="form-control">
					<option value="b_title">제목</option>
					<option value="b_name">작성자</option>
					<option value="b_content">내용</option>
					<option value="b_date">작성일자</option>
				</select>
			</div>
			<div class="form-group" id="textCheck">
				<input type="text" name="keyword" id="keyword" class="form-control"
					placeholder="검색어를입력하세요.">
			</div>

			<div class="form-group" id="dateCheck">
				<input type="date" name="start_date" id="start_date"
					placeholder="시작일자" class="form-control"> <input type="date"
					name="end_date" id="end_date" placeholder="종료일자"
					class="form-control">
			</div>

			<button type="button" class="btn btn-primary" id="searchBtn">Search</button>
			<button type="button" class="btn btn-primary" id="allSearchBtn">All
				Search</button>
			<button type="button" class="btn btn-primary" id="excelDownBtn">Excel
				Down</button>
		</form>
	</div>

	<div class="table-responsive">
		<table class="table table-boardered">
			<thead>
				<tr>
					<th class="tac">글번호</th>
					<th class="tac">글제목</th>
					<th class="tac">작성일</th>
					<th class="tac">작성자</th>
				</tr>
			</thead>

			<tbody>
				<!-- 데이터 출력 -->
				<c:choose>
					<c:when test="${not empty boardList}">
						<c:forEach var="board" items="${boardList}" varStatus="status">
							<tr class="tac" data-num="${board.b_num}">
								<td>${board.b_num}</td>
								<td class="goDetail tal">${board.b_title}</td>
								<td>${board.b_date}</td>
								<td class="name">${board.b_name}</td>
							</tr>
						</c:forEach>
					</c:when>

					<c:otherwise>
						<tr>
							<td colspan="4" class="tac">등록된 게시물이 존재하지 않습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>

		<!-- =================================페이지 네비게이션 시작======================= -->
		<div id="boardPage">
			<tag:paging page="${param.page}" total="${total}" list_size="10"></tag:paging>
		</div>
	</div>
</body>
</html>