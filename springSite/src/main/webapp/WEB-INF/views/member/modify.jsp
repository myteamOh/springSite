<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="email" value="${fn:split(member.email, '@')}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,  initial-scale=1.0,  maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Update Member!</title>
<!-- 모바일 웹 페이지 설정 -->
<link rel="shortcut icon" href="/resources/image/icon.png" />
<link rel="apple-touch-icon" href="/resources/image/icon.png" />
<!-- 모바일 웹 페이지 설정 끝 -->
<!--[if lt IE 9]> <script src="/resources/include/js/html5shiv.js"></script> <![endif]-->
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script type="text/javascript" src="/resources/include/js/modify.js"></script>
<script type="text/javascript">
	function errCodeCheck() {
		var errCode = '<c:out value = "${errCode}" />';
		if (errCode != '') {
			switch (parseInt(errCode)) {
			case 1:
				alert("기존 비밀번호 검증에 실패하였습니다. \n기존 비밀번호를 다시 확인해 주세요.");
				break;
			case 2:
				alert("회원정보 수정에 오류가 발생하였습니다. \n잠시 후 다시 진행해 주세요.");
				break;
			}
		}
	}
	loginUserId = "${member.userId}";
	function emailCheck() {
		var email = "${email[1]}";
		$("#emailDomain").val(email).prop("selected", "true");
	}
</script>
<style type="text/css">
#cl-dashboard {
	display: none;
}
</style>
</head>
<body>
	<div class="contentContainer">
		<div class="well">
			<form id="memberForm" class="form-horizontal">
				<input type="hidden" name="idx" id="idx" value="${member.idx}" /> <input
					type="hidden" name="email" id="email" />
				<div class="form-group form-group-sm">
					<label for="userId" class="col-sm-2 control-label">사용자 ID</label>
					<div class="col-sm-3">${member.userId}</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="oldUserPw" class="col-sm-2 control-label">기존 비밀
						번호 </label>
					<div class="col-sm-3">
						<input type="password" id="oldUserPw" name="oldUserPw"
							maxlength="15" class="form-control" placeholder="기존 비밀번호 입력">
					</div>
					<div class="col-sm-5">
						<p class="form-control-static error"></p>
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="userPw" class="col-sm-2 control-label">변경할 비밀
						번호 </label>
					<div class="col-sm-3">
						<input type="password" id="userPw" name="userPw" maxlength="15"
							class="form-control" placeholder="변경할 비밀번호 입력">
					</div>
					<div class="col-sm-5">
						<p class="form-control-static error"></p>
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="userPwCheck" class="col-sm-2 control-label">변경할
						비밀번호 확인</label>
					<div class="col-sm-3">
						<input type="password" id="userPwCheck" name="userPwCheck"
							maxlength="15" class="form-control" placeholder="변경할 비밀번호 입력  확인">
					</div>
					<div class="col-sm-5">
						<p class="form-control-static error"></p>
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="phone" class="col-sm-2 control-label">핸드폰 번호 </label>
					<div class="col-sm-3">
						<input type="text" id="phone" name="phone" maxlength="15"
							class="form-control" value="${member.phone}">
					</div>
					<div class="col-sm-5">
						<p class="form-control-static error"></p>
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="birth" class="col-sm-2 control-label"> 생년월일</label>
					<div class="col-sm-3">${member.pinno}******</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="userName" class="col-sm-2 control-label">회원이름</label>
					<div class="col-sm-3">${member.userName}</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="emailName" class="col-sm-2 control-label">회원
						이메일 </label>
					<div class="col-sm-3">
						<input type="text" id="emailName" name="emailName" maxlength="60"
							class="form-control" value="${email[0]}">
					</div>
					<div class="col-sm-2">
						<select id="emailDomain" class="form-control">
							<option value="naver.com">네이버</option>
							<option value="daum.net">다음</option>
							<option value="nate.com">네이트</option>
						</select>
					</div>
					<div class="col-sm-3">
						<p class="form-control-static error"></p>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-6">
						<input type="button" value="확인" id="modify"
							class="btn btn-default" /> <input type="button" value="재작성"
							id="modifyReset" class="btn btn-default" /> <input type="button"
							value="취소" id="modifyCancel" class="btn btn-default" />
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>