<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<nav class="navbar navbar-expand-lg navbar-light fixed-top" style="background-color: #c4c4c4e3;">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">🍴Matjip</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">


            <ul class="navbar-nav me-auto" aria-labelledby="navbarDropdown">

                <li class="nav-item"><a href="/user/board/list?groupId=1" class="nav-link link-light px-2">한식</a></li>
                <li class="nav-item"><a href="/user/board/list?groupId=2" class="nav-link link-light px-2 ">양식</a></li>
                <li class="nav-item"><a href="/user/board/list?groupId=3" class="nav-link link-light px-2 ">중식</a></li>
                <li class="nav-item"><a href="/user/board/list?groupId=4" class="nav-link link-light px-2 ">일식</a></li>

            </ul>

            <ul class="navbar-nav">

                <c:if test="${empty pageContext.request.userPrincipal }">
                    <li class="nav-item"><a href="/login" class="nav-link link-light px-2">로그인 / 회원가입</a></li>
                </c:if>
                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item"><a href="/user/board/list?param=choice"
                                            class="nav-link link-light px-2"><sec:authentication
                            property="principal.member.name"/>님의 찜 목록</a></li>
                    <li class="nav-item"><a href="/user/board/list?param=recommend"
                                            class="nav-link link-light px-2"><sec:authentication
                            property="principal.member.name"/>님 맞춤 추천</a></li>
                    <li class="nav-item"><a id="logout" href="/logout" class="nav-link link-light px-2">로그 아웃</a></li>
                    <li class="nav-item"><a id="withdrawal" href="/user/withdrawal" class="nav-link link-light px-2">회원
                        탈퇴</a></li>
                </sec:authorize>
            </ul>

        </div>
    </div>
</nav>

<script>
    function logout() {
        let logout = document.getElementById('logout');

        const stop = ev => {
            ev.preventDefault();
            if (confirm("로그아웃 하시겠습니까?") == true) window.location.href = "/logout";
        }

        logout.addEventListener('click', stop);
    }

    function withdrawal() {
        let withdrawal = document.getElementById('withdrawal');

        const stop = ev => {
            ev.preventDefault();
            if (confirm("정말로 회원 탈퇴를 하시겠습니까?") == true) window.location.href = "/user/withdrawal";
        }

        withdrawal.addEventListener('click', stop);
    }

    (function () {
        logout();
        withdrawal();
    })();
</script>



