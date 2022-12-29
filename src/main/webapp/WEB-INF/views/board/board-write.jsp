<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="../include/static-head.jsp" %>
    <link rel="stylesheet" href="/css/boardWrite.css">
    <title>🍴Matjip</title>
</head>

<body>

<%@ include file="/WEB-INF/views/include/nav.jsp" %>

<spring:hasBindErrors name="boardSaveForm"/>
<p id="errorTag" class="d-none"><form:errors path="boardSaveForm.tag"/></p>
<p id="errorPhoto" class="d-none"><form:errors path="boardSaveForm.thumbNail"/></p>
<p id="errorTitle" class="d-none"><form:errors path="boardSaveForm.title"/></p>
<p id="errorRegion" class="d-none"><form:errors path="boardSaveForm.detailArea"/></p>

<section id="top">
    <div class="section-content overlay d-flex justify-content-center align-items-center">
        <div class="container-xxl">
            <div class="row align-items-center">
                <div class="col welcome main-title">
                    <p style="font-size: 2rem;" class="welcome-title fw-light">
                        마음껏 리뷰를 남겨주세요
                    </p>
                </div>
            </div>
        </div>
    </div>
</section>


<div style="padding: 3rem 3rem;"></div>

<form action="/user/board/save/${groupId}" method="post" enctype="multipart/form-data">

    <div class="section-content d-flex justify-content-center align-items-center">
        <div class="containerCustom">

            <div class="mb-3">
                <input class="form-control" type="file" id="formFile" accept="image/*" name="thumbNail">
                <label for="formFile" class="form-label explain"> &nbsp * 썸네일 사진을 골라주세요 가로 세로 비율이 같아야 합니다. *</label>
            </div>

            <div class="input-group mb-3">
                <span class="input-group-text" id="inputGroup-sizing-default">제목</span>
                <input id="title" onkeyup="titleValidation()" type="text" name="title" value="${boardSaveForm.title}"
                       class="form-control" aria-label="Sizing example input"
                       aria-describedby="inputGroup-sizing-default">
            </div>
            <p id="errorFieldTitle" class="pe-3 error" style="display:none;">제목은 최소 2글자이상 50글자 미만입니다.</p>

            <div class="input-group ">

                <label class="input-group-text" for="inputGroupSelect04">대표 지역</label>

                <input type="hidden" id="region" value="${boardSaveForm.representativeArea}">
                <select class="form-select" name="representativeArea" id="inputGroupSelect04"
                        aria-label="Example select with button addon">

                    <c:forEach var="region" items="${regions}">
                        <option id="${region}" value=${region}>${region.description}</option>
                    </c:forEach>

                </select>

                <input type="radio" class="btn-check" name="options" id="option" disabled>
                <label class="btn btn-outline-secondary" for="option">태그설정</label>

                <c:forEach var="tag" varStatus="status" items="${tags}">
                    <input type="checkbox" class="btn-check tag" name="tag"
                           value=${tag} <c:if test="${fn:contains(boardSaveForm.tag,tag)}">checked</c:if>
                           id=${status.index}>
                    <label class="btn btn-outline-secondary tagButton" for=${status.index}>${tag.description}</label>
                </c:forEach>


            </div>
            <p class="explain ps-2 mb-3"> 태그는 최소 1개이상 선택해주세요</p>

            <div class="input-group">
                <button type="button" class="btn btn-outline-secondary" style="color: black">가격 설정</button>
                <button type="button" class="btn btn-outline-secondary dropdown-toggle dropdown-toggle-split"
                        data-bs-toggle="dropdown" aria-expanded="false">
                    <span class="visually-hidden">Toggle Dropdown</span>
                </button>
                <ul class="dropdown-menu">
                    <input type="range" value="${boardSaveForm.price}" min="0" max="100000" step="1000" class="slider"
                           id="myRange">
                </ul>
                <input id="value" name="price" type="text" class="form-control"
                       aria-label="Text input with segmented dropdown button">
            </div>
            <p class="explain ps-2"> 버튼을 눌러 대략적인 평균 금액을 설정하세요</p>

            <div class="input-group mb-3">
                <span class="input-group-text" id="inputGroup-sizing-default2">상세 위치</span>
                <input id="location" value="${boardSaveForm.detailArea}" onkeyup='printLocation()' type="text"
                       name="detailArea" class="form-control" aria-label="Sizing example input"
                       aria-describedby="inputGroup-sizing-default2"
                       placeholder="주소로 검색 ex) 제주특별자치도 제주시 첨단로 242"
                >
            </div>
            <p id="errorFieldRegion" class="pe-3 error">상세 위치를 입력해주세요</p>
            <div id="map" class="mb-3" style="width:100%;height:350px;"></div>

            <textarea id="content" name="content"> * 이 설명은 보고 지워주세요 * 1. 사진 모양을 클릭한다. 2. 업로드 버튼을 누른다. 3. 파일 선택에서 사진을 고른다. 4. 서버로 전송 버튼을 누른다. 5. 크기를 조절한다. 6. 확인버튼을 누른다.${boardSaveForm.content}</textarea>

            <button id="subButton" type="submit" class="btn btn-primary">글등록</button>
        </div>
    </div>
    <input type="hidden" name="groupId" value="${groupId}">
</form>
<%--</spring:hasBindErrors>--%>

<div style="padding: 3rem 3rem;"></div>

<!-- footer 시작 -->
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
<!-- footer 종료 -->

<script type="text/javascript" src="/ckeditor/ckeditor.js"></script>
<script type="text/javascript">    // 글쓰기 editor 및 사진 업로드 기능
CKEDITOR.replace('content',
    {
        filebrowserUploadUrl: '/food/imageUpload.do'
    });
</script>
<script type="text/javascript"
        src="http://dapi.kakao.com/v2/maps/sdk.js?appkey=33596d28073e490ff8a0bf0fd3c448fb&libraries=services"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
    let locationInfo = null;

    function validLocation() {
        locationInfo = document.getElementById('location').value;

        return !(locationInfo.length >= 1 && locationInfo.length <= 3);
    }

    function printLocation() {
        let error = document.getElementById('errorFieldRegion');

        validLocation() ? error.style.display = 'none' : error.style.display = 'block';

        var mapContainer = document.getElementById('map'), // 지도를 표시할 div
            mapOption = {
                center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
                level: 3 // 지도의 확대 레벨
            };

        // 지도를 생성합니다
        var map = new kakao.maps.Map(mapContainer, mapOption);

        // 주소-좌표 변환 객체를 생성합니다
        var geocoder = new kakao.maps.services.Geocoder();

        // 주소로 좌표를 검색합니다
        geocoder.addressSearch(locationInfo, function (result, status) {

            // 정상적으로 검색이 완료됐으면
            if (status === kakao.maps.services.Status.OK) {

                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                // 결과값으로 받은 위치를 마커로 표시합니다
                var marker = new kakao.maps.Marker({
                    map: map,
                    position: coords
                });

                // 인포윈도우로 장소에 대한 설명을 표시합니다
                var infowindow = new kakao.maps.InfoWindow({
                    content: '<div style="width:150px;text-align:center;padding:6px 0;">위치</div>'
                });
                infowindow.open(map, marker);

                // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                map.setCenter(coords);
            }
        });

    }

    function slider() {
        var slider = document.getElementById("myRange");
        var output = document.getElementById("value");
        output.value = slider.value;

        slider.oninput = function () {
            output.value = this.value;
        }
    }

    function checkTitle() {
        let title = document.getElementById('title').value.length;

        return title > 1 && title < 50;
    }

    function titleValidation() {
        let errorTitle = document.getElementById('errorFieldTitle');
        errorTitle.style.display = 'block';


        if (checkTitle()) errorTitle.style.display = 'none';
    }

    function error() {
        let errorTag = document.getElementById('errorTag').textContent;
        let errorPhoto = document.getElementById('errorPhoto').textContent;
        let errorTitle = document.getElementById('errorTitle').textContent;
        let errorRegion = document.getElementById('errorRegion').textContent;

        let errors
            = new Array(errorTag, errorPhoto, errorTitle, errorRegion)
            .filter(value => value !== '');
        if (errors.length == 0) return;
        let errorMessage = errors[0];

        for (let i = 1; i < errors.length; i++) {
            errorMessage += "\n" + errors[i];
        }
        alert(errorMessage);
    }

    function checkRegion() {
        let region = document.getElementById('region');
        let regionTag = document.getElementById(region.value);

        regionTag.selected = true;
    }

    function validTag() {
        let tagList = [...(document.querySelectorAll('.tag'))];

        let valid = false;
        for (let tag of tagList) {
            if (tag.checked) valid = true;
        }
        return valid;

    }

    function allValidation() {

        const allValidation = ev => {
            let validTitle = checkTitle();
            let validationLocation = validLocation();
            let tag = validTag();
            let titleNotEmpty = document.getElementById('title').value.length != 0;
            let locationNotEmpty = document.getElementById('location').value.length != 0;
            let validThumbNail = document.getElementById('formFile').value.length != 0;

            if (!(validThumbNail && tag && validTitle && titleNotEmpty && validationLocation && locationNotEmpty)) {
                ev.preventDefault();

                let errormessage = '';
                if (!validThumbNail) errormessage += "썸네일은 필수 입니다." + '\n';
                if (!validTitle) errormessage += "제목은 2글자에서 50글자 사이입니다." + '\n';
                if (!titleNotEmpty) errormessage += "제목은 필수 값입니다." + '\n';
                if (!validationLocation) errormessage += "상세 위치를 자세히 작성해야합니다." + '\n';
                if (!locationNotEmpty) errormessage += "상세위치를 작성해야 합니다.";
                if (!tag) errormessage += "테그를 하나이상 설정 해야합니다." + '\n';

                alert(errormessage)
            }
        }
        let button = document.getElementById('subButton');

        button.addEventListener('click', allValidation);

    }


    // 메인 실행부
    (function () {
        allValidation();
        printLocation();
        slider();
        error();
        checkRegion();
    })();
</script>

</body>

</html>