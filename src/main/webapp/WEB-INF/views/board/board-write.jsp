<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="../include/static-head.jsp" %>
    <link rel="stylesheet" href="/css/boardWrite.css">
    <title>🍴Matjip</title>
</head>

<body>

<%@ include file="/WEB-INF/views/include/nav.jsp" %>

<section id="top">
    <div class="section-content overlay d-flex justify-content-center align-items-center">
        <div class="container-xxl">
            <div class="row align-items-center">
                <div class="col-md-9 welcome main-title">
                    <h1 class="welcome-title fw-light">&nbsp 마음 껏 리뷰를 남겨 주세요</h1>
                </div>
            </div>
        </div>
    </div>
</section>


<div style = "padding: 3rem 3rem;"></div>

<form action="/user/board/save/${groupId}" method="post" enctype="multipart/form-data">

    <div class="section-content d-flex justify-content-center align-items-center">
        <div class="containerCustom">


            <div class="mb-3">
                <input class="form-control" type="file" id="formFile"  name="thumbNail">
                <label for="formFile" class="form-label explain"> &nbsp * 썸네일 사진을 골라주세요 (250*250) *</label>
            </div>

            <div class="input-group mb-3">
                <span class="input-group-text" id="inputGroup-sizing-default">제목</span>
                <input type="text" name="title" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
            </div>

            <div class="input-group mb-3">
                <label class="input-group-text" for="inputGroupSelect04">대표 지역</label>
                <select class="form-select" name="representativeArea" id="inputGroupSelect04" aria-label="Example select with button addon">
                    <option value="Seoul">서울</option>
                    <option value="gyeonggiDo">경기</option>
                    <option value="incheon">인천</option>
                    <option value="gangwonDo">강원도</option>
                    <option value="jeollaBukDo">전라북도</option>
                    <option value="jeollaNamDo">전라남도</option>
                    <option value="gyeongsangBukDo">경상북도</option>
                    <option value="gyeongsangNamDo">경상남도</option>
                    <option value="chungcheongDo">충청도</option>
                </select>

                <input type="radio" class="btn-check" name="options" id="option" disabled>
                <label class="btn btn-outline-secondary" for="option">태그설정</label>

                <input type="checkbox" class="btn-check" name="tag" value="atmosphere" id="option1">
                <label class="btn btn-outline-secondary" for="option1">분위기</label>

                <input type="checkbox" class="btn-check" name="tag" value="money" id="option2">
                <label class="btn btn-outline-secondary" for="option2">가성비</label>

                <input type="checkbox" class="btn-check" name="tag" value="reservation" id="option3">
                <label class="btn btn-outline-secondary" for="option3">예약 가능</label>

                <input type="checkbox" class="btn-check" name="tag" value="play" id="option4">
                <label class="btn btn-outline-secondary" for="option4">놀기 좋은</label>


            </div>

            <div class="input-group">
                <button type="button" class="btn btn-outline-secondary" style="color: black">가격 설정</button>
                <button type="button" class="btn btn-outline-secondary dropdown-toggle dropdown-toggle-split" data-bs-toggle="dropdown" aria-expanded="false">
                    <span class="visually-hidden">Toggle Dropdown</span>
                </button>
                <ul class="dropdown-menu">
                    <input type="range" min="0" max="100000" step="1000" class="slider" id="myRange">
                </ul>
                <input id="value" name="price" type="text" class="form-control" aria-label="Text input with segmented dropdown button">
            </div>
            <p class="explain"> 버튼을 눌러 대략적인 평균 금액을 설정하세요</p>

            <div class="input-group mb-3">
                <span class="input-group-text" id="inputGroup-sizing-default2">상세 위치</span>
                <input id="location" onkeyup='printLocation()' type="text" name="detailArea" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default2"
                placeholder="주소로 검색 ex) 제주특별자치도 제주시 첨단로 242"
                >
            </div>

            <div id="map" class="mb-3" style="width:100%;height:350px;"></div>

            <textarea id="content" name="content"></textarea>

            <div class="mb-3">
                <input class="form-control" type="file" multiple="multiple" id="formFileMultiple" name="attachFiles">
                <label for="formFileMultiple" class="form-label explain"> &nbsp * 첨부파일은 다중으로 선택 하실 수 있습니다.(단 한번에 올려야 합니다.) *</label>
            </div>

            <button type="submit" class="btn btn-primary">글등록</button>
        </div>
    </div>
    <input type="hidden" name="groupId" value="${groupId}">
</form>

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
        locationInfo = document.getElementById('location').value;

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

    // 메인 실행부
    (function () {
        printLocation();
        slider();

    })();
</script>

</body>

</html>