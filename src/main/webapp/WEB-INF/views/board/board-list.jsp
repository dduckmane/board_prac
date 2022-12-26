<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <%@ include file="../include/static-head.jsp" %>
    <title>🍴Matjip</title>
    <%--  custom css--%>
    <link rel="stylesheet" href="/css/boardList.css">
</head>
<style>
    .btn {
        height: 2.4rem;
    }
    .btn {
        font-size: 1rem;
    }
    #content {
        width: 20rem;
      }
    #image {
        width: 25vw;
        height: 20vw;
        object-fit: fill;
    }
    .card {
        border: 0px;
    }
    .filter {
        width: 95vw;
    }

    @media (min-width: 768px) {
        .filter {
            width: 750px;
        }
    }

    @media (min-width: 992px) {
        .filter {
            width: 970px;
        }
    }

    @media (min-width: 1200px) {
        .filter {
            width: 50vw;
        }
    }


</style>
<body>

<%@ include file="/WEB-INF/views/include/nav.jsp" %>
<%--top 영역--%>
<section id="top">
    <div class="section-content overlay d-flex justify-content-center align-items-center">
        <div class="container-xxl">
            <div class="row align-items-center">
                <div class="col-md-9 welcome main-title">
                    <h1 class="welcome-title fw-light">
                        &nbsp ${title} 페이지에 오신걸 환영합니다.
                    </h1>
                </div>
            </div>
        </div>
    </div>
</section>

<%--filter 영역--%>
<section id="filterList">
    <div class="d-flex container-fluid mt-2 pe-0">
        <div class="dropdown">
            <%--대표 필터 버튼--%>
            <button class="btn btn-outline-success dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                필터 적용
            </button>
<%--            필터 목록 나열--%>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                <li class="dropdown-item">
<%--                    form 데이터로 서버에 전송--%>
                    <form class="" action="">
                        <input type="hidden" name="${requestParam}" value="${Param}">
<%--                        가장 상위 row--%>
                        <div class="filter row">
<%--                            핉터 3개를 관리--%>
                            <div class="col-lg-6 col-12 pe-2">
<%--                                필터 3개의 row--%>
                                <div class="row">
<%--                                    필터 1--%>
                                    <div class="col-md-4 pe-md-0" >
                                        <div id="money">
                                            <select class="form-select" id="inputGroupSelect4"
                                                    aria-label="Example select with button addon"
                                                    name="price">

                                                <option value="">가격 대</option>
                                                <option value="10000">~10000</option>
                                                <option value="20000">10000 ~ 20000</option>
                                                <option value="30000">20000 ~ 30000</option>
                                                <option value="excess">30000 ~</option>

                                            </select>
                                        </div>
                                    </div>
    <%--                                    필터 2--%>
                                    <div class="col-md-4 p-md-0">
                                        <div id="tag">
                                            <select class="form-select" id="inputGroupSelect3"
                                                    aria-label="Example select with button addon"
                                                    name="tag">

                                                <option value="">태그로 검색</option>
                                                <c:forEach var="tag" items="${tags}">
                                                    <option value="${tag}">${tag.description}</option>
                                                </c:forEach>

                                            </select>
                                        </div>
                                    </div>
    <%--                                    필터 3--%>
                                    <div class="col-md-4 p-md-0">
                                        <div id="sortBox">
                                            <select class="form-select" id="inputGroupSelect2"
                                                    aria-label="Example select with button addon"
                                                    name="sort">

                                                <option value="">정렬</option>
                                                <option value="createdDateDESC">최근 순</option>
                                                <option value="createdDateASC">오래된 순</option>
                                                <option value="viewCnt">조회수 순</option>

                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
<%--                            검색 쪽 필터 부분--%>
                            <div class="col-lg-6 pt-2 pt-lg-0 col-12 pe-2">
                                <div class="row d-flex  align-items-center">
<%--                                    필터 1 검색-버튼 --%>
                                    <div class="col-3 p-lg-0 pe-0">
                                        <div id="searchBox">
                                            <select class="form-select" id="inputGroupSelect"
                                                    aria-label="Example select with button addon"
                                                    onchange="searchCondition()">

                                                <option class="opt" value="">검색 필터</option>
                                                <option class="opt" value="title">제목</option>
                                                <option class="opt" value="name">작성자</option>
                                                <option class="opt" value="all">작성자+제목</option>

                                            </select>
                                        </div>
                                    </div>
<%--                                    필터 2: input 영역--%>
                                    <div class="col-6 p-0">
                                        <div id="content d-flex w-100">
                                            <input id="filterInput" name="" class="form-control input" type="search" placeholder="Search" aria-label="Search">
                                        </div>
                                    </div>
<%--                                    필터 3: 버튼--%>
                                    <div class="col-3 pe-3">
                                        <button class="d-flex w-100 btn btn-outline-success justify-content-center align-items-center" type="submit">Search</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </li>
            </ul>
        </div>
<%--오른 쪽 필터 시작--%>
        <div class="ms-auto">
            <c:choose>
<%--                1. 메인 카테고리 영역에서 글을 작성 시--%>
                <c:when test="${listParam.groupId!=null}">
                    <a class="btn btn-outline-success navbar-brand me-0" href="/user/board/save/${listParam.groupId}">글쓰기</a>
                </c:when>
<%--                2. 메인 카테고리 외에서 글을 작성시 --> 모달 생성 후 카테고리를 선택 --%>
                <c:otherwise>
                    <button type="button" class="btn btn-outline-success navbar-brand me-0" data-bs-toggle="modal" data-bs-target="#exampleModal">
                        글쓰기
                    </button>

                    <!-- Modal 양역 -->
                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">글쓰기</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    카테고리를 고르세요
                                </div>

                                <div class="modal-footer">
                                    <a class="btn btn-outline-success navbar-brand me-0" href="/user/board/save/1">한식</a>
                                    <a class="btn btn-outline-success navbar-brand me-0" href="/user/board/save/2">일식</a>
                                    <a class="btn btn-outline-success navbar-brand me-0" href="/user/board/save/3">중식</a>
                                    <a class="btn btn-outline-success navbar-brand me-0" href="/user/board/save/4">양식</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
            <a class="m-0 btn btn-outline-success navbar-brand" href="/user/board/list?${requestParam}=${Param}">필터 초기화</a>
        </div>

    </div>
</section>


<div style = "padding: 3rem 3rem;"></div>

<!-- 리스트 시작 -->
<section id="boardList">
    <div class="row">
        <c:forEach var="item" varStatus="status" items="${BoardDtoList}">
            <div class="col-md-3 col-sm-6 p-0">
                <div class="col-md">
                    <div class="card mb-3 p-0">
                        <a href="/user/board/${item.id}">
                            <img id="image" data-item-id="${item.id}" class="card-img card-img-left img-fluid w-100 h-100" style="object-fit: cover" src="" alt="Card image" >
                        </a>
                        <div class="card-body p-1 m-0 d-flex flex-column justify-content-center align-items-center">
                            <h5 class="card-title">${item.subTitle} <c:if test="${item.newArticle}"><img src="https://img.icons8.com/office/16/null/new.png"/></c:if></h5>
                            <p class="card-text p-0 m-0 text-align">
                                조회수: ${item.viewCnt}
                            </p>
                            <input type="checkbox" class="btn-check" name="options" id="${item.id}">
                            <label class="btn btn-outline-danger p-0 d-flex justify-content-center align-items-center" for="${item.id}">😍</label>
                        </div>
                    </div>
                </div>

            </div>
        </c:forEach>
    </div>
</section>


<!-- pagination 시작 -->
<section>
    <div class="container mt-3">
        <ul class="pagination justify-content-center">
            <c:if test="${not pageMaker.first}">
                <li class="page-item"><a class="page-link" href="">이전</a></li>
            </c:if>

            <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                <li class="page-item ${pageMaker.nowPage == num ? 'active' : ''}"><a class="page-link" href="/user/board/list?groupId=${listParam.groupId}&page=${num-1}">${num}</a></li>
            </c:forEach>

            <c:if test="${not pageMaker.last}">
                <li class="page-item"><a class="page-link" href="">다음</a></li>
            </c:if>
        </ul>
    </div>
</section>
<!-- pagination 종료 -->

<!-- footer 시작 -->
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
<!-- footer 종료 -->

<script>

  function showImage(){

    const $thumbList = document.querySelectorAll('img[data-item-id]');
    for (let $thumb of [...$thumbList]) {
      let itemId = $thumb.dataset.itemId;
        fetch('/images?itemId=' + itemId)
                .then(res => res.blob())
                .then(img => {
                  const url = URL.createObjectURL(img);
                  $thumb.src = url;
                });
    }
  }

  function searchCondition(){
      let select = document.getElementById('inputGroupSelect');
      let condition1 = select.options[select.selectedIndex].value;

      let filterInput = document.getElementById('filterInput');
      filterInput.name=condition1;
  }

  function choiceBoard(){
      let choices = document.querySelectorAll('#boardList .btn-check');

      for(let choice of [...choices]){
          choice.addEventListener("click",e=>{

              console.log(choice.id)

              const choiceData = {
                  boardId: choice.id
              }
              const reqInfo = {
                  method: 'POST'
                  , headers: {
                      'content-type': 'application/json'

                  }
                  , body: JSON.stringify(choiceData)

              }
              fetch('/user/choice',reqInfo);
          })
      }

  }
  function checkedChoiceButton(){
      fetch('/user/choice')
          .then(res => res.json())
          .then(checkedIdList => {
              for(let boardId of [...checkedIdList]){
                  let board = document.getElementById(boardId);
                  board.checked=true;
              }
          })
  }



  (function (){
    choiceBoard();
    showImage();
    checkedChoiceButton();
    // searchCondition();

  })();
</script>
</body>
</html>