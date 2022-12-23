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
    <style>
        .error {
            color: #ef0505;
            font-size: 0.9rem;
        }
    </style>
</head>

<body>

<%@ include file="/WEB-INF/views/include/nav.jsp" %>

<spring:hasBindErrors name="boardUpdateForm"/>
<p id="errorTag" class="d-none"><form:errors path="boardUpdateForm.tag"/></p>
<p id="errorPhoto" class="d-none"><form:errors path="boardUpdateForm.thumbNail"/></p>
<p id="errorTitle" class="d-none"><form:errors path="boardUpdateForm.title"/></p>
<p id="errorRegion" class="d-none"><form:errors path="boardUpdateForm.detailArea"/></p>

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


<div style = "padding: 3rem 3rem;"></div>

<form action="/user/board/edit" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${boardUpdateForm.id}">
    <div class="section-content d-flex justify-content-center align-items-center">
        <div class="containerCustom">


            <div class="mb-3">
                <input class="form-control" type="file" id="formFile" accept="image/*" name="thumbNail">
                <label for="formFile" class="form-label explain" style="color:#a9a166;"> &nbsp * 파일을 설정하지 않는 다면 기존 사진 그대로 유지 됩니다. *</label>
            </div>

            <div class="input-group mb-3">
                <span class="input-group-text" id="inputGroup-sizing-default">제목</span>
                <input id="title" onkeyup="titleValidation()" value="${boardUpdateForm.title}" type="text" name="title" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
            </div>
            <p id="errorFieldTitle" class="pe-3 error" style="display:none;">제목은 최소 2글자이상 50글자 미만입니다.</p>

            <div class="input-group ">

                <label class="input-group-text" for="inputGroupSelect04">대표 지역</label>

                <select class="form-select" name="representativeArea" id="inputGroupSelect04" aria-label="Example select with button addon">

                    <c:forEach var="region" items="${regions}">
                        <option id="${region}" value=${region}>${region.description}</option>
                    </c:forEach>

                </select>

                <input type="hidden" id="region" value="${boardUpdateForm.representativeArea}">

                <c:forEach var="tag" varStatus="status" items="${tags}">
                    <input type="checkbox" class="btn-check" name="tag" value=${tag} id=${status.index}>
                    <label class="btn btn-outline-secondary" for=${status.index}>${tag.description}</label>
                </c:forEach>

            </div>
            <p class="explain ps-2 mb-3" style="color:#a9a166;"> 태그는 최소 1개이상 선택해주세요</p>

            <div class="input-group">
                <button type="button" class="btn btn-outline-secondary" style="color: black">가격 설정</button>
                <button type="button" class="btn btn-outline-secondary dropdown-toggle dropdown-toggle-split" data-bs-toggle="dropdown" aria-expanded="false">
                    <span class="visually-hidden">Toggle Dropdown</span>
                </button>
                <ul class="dropdown-menu">
                    <input type="range" value="${boardUpdateForm.price}" min="0" max="100000" step="1000" class="slider" id="myRange">
                </ul>
                <input id="value" name="price" type="text" class="form-control" aria-label="Text input with segmented dropdown button">
            </div>
            <p class="explain ps-2"> 버튼을 눌러 대략적인 평균 금액을 설정하세요</p>

            <div class="input-group mb-3">
                <span class="input-group-text" id="inputGroup-sizing-default2">상세 위치</span>
                <input id="location" value="${boardUpdateForm.detailArea}" onkeyup='printLocation()' type="text" name="detailArea" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default2"
                       placeholder="주소로 검색 ex) 제주특별자치도 제주시 첨단로 242"
                >
            </div>
            <p id="errorFieldRegion" class="pe-3 error">상세 위치를 입력해주세요</p>
            <div id="map" class="mb-3" style="width:100%;height:350px;"></div>

            <textarea id="content" name="content">${boardUpdateForm.content}</textarea>

            <button type="submit" class="btn btn-primary">수정 완료</button>
        </div>
    </div>
</form>
<%--</spring:hasBindErrors>--%>

<div style = "padding: 3rem 3rem;"></div>

<!-- footer 시작 -->
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
<!-- footer 종료 -->

<script type="text/javascript" src="/ckeditor/ckeditor.js"></script>
<script type="text/javascript">	// 글쓰기 editor 및 사진 업로드 기능
CKEDITOR.replace('content',
    {
        filebrowserUploadUrl:'/food/imageUpload.do'
    });
</script>
<script type="text/javascript" src="http://dapi.kakao.com/v2/maps/sdk.js?appkey=33596d28073e490ff8a0bf0fd3c448fb&libraries=services"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
    let locationInfo=null;

    function printLocation()  {
        let error = document.getElementById('errorFieldRegion');

        locationInfo = document.getElementById('location').value;

        if(locationInfo.length>=1&&locationInfo.length<=3){
            error.style.display='block'
        }else {
            error.style.display='none'
        }

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
        geocoder.addressSearch(locationInfo, function(result, status) {

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
    function slider(){
        var slider = document.getElementById("myRange");
        var output = document.getElementById("value");
        output.value = slider.value;

        slider.oninput = function() {
            output.value = this.value;
        }
    }
    function titleValidation(){
        let errorTitle = document.getElementById('errorFieldTitle');
        errorTitle.style.display='block';

        let title = document.getElementById('title').value.length;

        if(title>1&&title<50) errorTitle.style.display='none';


    }

    function error(){
        let errorTag = document.getElementById('errorTag').textContent;
        let errorPhoto = document.getElementById('errorPhoto').textContent;
        let errorTitle = document.getElementById('errorTitle').textContent;
        let errorRegion = document.getElementById('errorRegion').textContent;

        let errors
            = new Array(errorTag, errorPhoto, errorTitle, errorRegion)
            .filter(value => value!=='');

        if(errors.length==0) return;
        let errorMessage=errors[0];

        for (let i = 1; i <errors.length ; i++) {
            errorMessage+="\n"+errors[i];
        }
        alert(errorMessage);
    }
    function checkRegion(){
        let region = document.getElementById('region');
        let regionTag = document.getElementById(region.value);
        console.log(regionTag)
        regionTag.selected=true;
    }



    // 메인 실행부
    (function () {
        printLocation();
        slider();
        error();
        checkRegion();
    })();
</script>

</body>

</html>