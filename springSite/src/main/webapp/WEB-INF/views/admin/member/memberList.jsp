<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
</head>
<body>
	<h2 class="sub-header">회원 리스트</h2>
	<div class="table-responsive">
		<table class="table table-boardered">
			<!-- table-striped -->
			<thead>
				<tr>
					<th class="tac">회원번호</th>
					<th class="tac">회원아이디</th>
					<th class="tac">회원명</th>
					<th class="tac">회원이메일</th>
					<th class="tac">회원핸드폰</th>
					<th class="tac">등록일</th>
				</tr>
			</thead>

			<tbody>
				<c:choose>
					<c:when test="${not empty memberList}">
						<c:forEach var="member" items="${memberList}" varStatus="status">
							<tr class="tac" data-idx="${member.idx}">
								<td>${status.count}</td>
								<td><span class="goDetail">${member.userId}</span></td>
								<td>${member.userName}</td>
								<td>${member.email}</td>
								<td>${member.phone}</td>
								<td>${member.joinDate}</td>
							</tr>
						</c:forEach>
					</c:when>

					<c:otherwise>
						<tr>
							<td colspan="6" align="center">현재 회원이 존재하지 않습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</body>
</html>