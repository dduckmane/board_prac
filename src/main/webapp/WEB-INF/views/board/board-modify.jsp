<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Matjip</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/board.css">
    <script type="text/javascript" src="/ckeditor/ckeditor.js"></script>
</head>
<body>

<form action="/user/board/edit/${BoardUpdateForm.id}" method="post">
    제목: <input type="text" name="title" value="${BoardUpdateForm.title}">
    <textarea id="content" name="content">${BoardUpdateForm.content}</textarea>
    <input type="submit" value="글등록">
    <input type="hidden" name="boardId" value="${BoardUpdateForm.id}">
</form>

<script type="text/javascript">	// 글쓰기 editor 및 사진 업로드 기능
CKEDITOR.replace('content',
    {
        filebrowserUploadUrl:'/food/imageUpload.do'
    });
</script>
</body>

</html>