
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/part/head.jspf"%>

<!-- 글쓰는 중 유효하지 않은 input 방지  -->
<script>
	var writeFormSubmitted = false;

	function submitWriteForm(form) {
		if (writeFormSubmitted) {
			alert('처리 중입니다.');
			return;
		}

		form.title.value = form.title.value.trim();
		if (form.title.value.length == 0) {
			alert('제목을 입력해주세요.');
			form.title.focus();

			return;
		}

		form.body.value = form.body.value.trim();
		if (form.body.value.length == 0) {
			alert('내용을 입력해주세요.');
			form.body.focus();

			return;
		}

		form.submit();
		writeFormSubmitted = true;
	}
</script>

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

.form1 .form-row>.input>input, .form1 .form-row>.input>textarea {
	display: block;
	width: 100%;
	box-sizing: border-box;
	padding: 10px;
}

.form1 .form-row>.input>select {
	padding: 10px;
}

.form1 .form-row>.input>textarea {
	height: 500px;
}

@media ( max-width : 700px ) {
	.form1 .form-row {
		display: block;
	}
}

/* cus */
.write-form-box {
	margin-top: 30px;
}

.input {
	display: inline-block;
	border: 1px solid black;
}
</style>


<div class="write-form-box con">
	<form action="doWrite" method="POST" class="write-form form1"
		onsubmit="submitWriteForm(this); return false;">
		<div class="form-row">
			<div class="label">카테고리 선택</div>
			<div class="input">
				<select name="cateItemId">
					<%
						for (CateItem cateItem : cateItems) {
					%>
					<option value="<%=cateItem.getId()%>"><%=cateItem.getName()%></option>
					<%
						}
					%>

				</select>
			</div>
		</div>
		<div class="form-row">
			<div class="label">제목</div>
			<div class="input">
				<input name="title" type="text" placeholder="제목을 입력해주세요." />
			</div>
		</div>
		<div class="form-row">
			<div class="label">내용</div>
			<div class="input">
				<textarea name="body" placeholder="내용을 입력해주세요."></textarea>
			</div>
		</div>
		<div class="form-row">
			<div class="label">전송</div>
			<div class="input">
				<input type="submit" value="전송" /> <a href="list">취소</a>
			</div>
		</div>
	</form>
</div>

<%@ include file="/jsp/part/foot.jspf"%>