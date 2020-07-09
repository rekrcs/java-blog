<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/part/head.jspf"%>

<style>
/* lib */
.form1 {
	display: block;
	width: 100%;
}

.form1 .form-row {
	align-items: center;
	display: flex;
}
</style>
<%="<style>.form1 .form-row:not(:first-child) { margin-top : 10px; }</style>"%>
<style>
.form1 .form-row>.label {
	width: 100px;
}

.form1 .form-row>.input {
	flex-grow: 1;
}

.form1 .form-row>.input>input {
	display: block;
	width: 100%;
	box-sizing: border-box;
	padding: 10px;
}

.member-join-box {
	margin-top: 30px;
}

@media ( max-width : 700px ) {
	.form1 .form-row {
		display: block;
	}
}

.input {
	display: inline-block;
	border: 1px solid black;
}
</style>
<div class="member-join-box con">
	<form action="doJoin" method="post" class="join-form form1">
		<div class="form-row">
			<div class="label">아이디</div>
			<div class="input">
				<input name="loginId" type="text" placeholder="아이디를 입력해 주세요" />
			</div>
		</div>
		<div class="form-row">
			<div class="label">이름</div>
			<div class="input">
				<input name="name" type="text" placeholder="이름을 입력해 주세요" />
			</div>
		</div>
		<div class="form-row">
			<div class="label">닉네임</div>
			<div class="input">
				<input name="nickName" type="text" placeholder="닉네임을 입력해 주세요" />
			</div>
		</div>
		<div class="form-row">
			<div class="label">비번</div>
			<div class="input">
				<input name="loginPw" type="password" placeholder="비번을 입력해 주세요" />
			</div>
		</div>
		<div class="form-row">
			<div class="label">비번확인</div>
			<div class="input">
				<input name="loginPwConfirm" type="password"
					placeholder="비번을 한번더 입력해 주세요" />
			</div>
		</div>
		<div class="form-row">
			<div class="label">회원가입</div>
			<div class="input">
				<input value="전송" type="submit" /> <a href="../article/list">취소</a>
			</div>
		</div>
	</form>
</div>
<%@ include file="/jsp/part/foot.jspf"%>
