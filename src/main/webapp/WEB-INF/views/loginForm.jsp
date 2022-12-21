<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>

  <%@ include file="./include/static-head.jsp" %>
  <%--  custom css--%>
  <link rel="stylesheet" href="css/loginForm.css">
  <title>🍴Matjip</title>
    <style>
        #naver {
            width: 330px;
            height: 65px;
        }
    </style>
</head>

<body>

<%@ include file="/WEB-INF/views/include/nav.jsp" %>


<section id="top">
  <div class="section-content overlay d-flex justify-content-center align-items-center">
    <div class="container-xxl">
      <div class="row align-items-center">
        <div class="col-md-9 welcome main-title">
          <h1 class="welcome-title fw-light">&nbsp 로그인 페이지 입니다. </h1>
        </div>
      </div>
    </div>
  </div>
</section>

<div style="padding: 3rem 3rem;"></div>

<section>

    <div class="section-content overlay d-flex justify-content-center align-items-center">
        <div class="container-xl">
            <div class="row d-flex justify-content-center align-items-center">
                <div class="text-center py-10">
                    <h3>저희 Matjip 에 와주셔서 감사합니다.</h3>
                    <h3>버튼을 눌러 1초 회원가입 로그인을 진행하세요</h3>
                </div>
                <div class="col-12 d-flex justify-content-center align-items-center py-2">
                    <a href="/oauth2/authorization/kakao">
                        <img height="70px" width="350px" src="//k.kakaocdn.net/14/dn/btqCn0WEmI3/nijroPfbpCa4at5EIsjyf0/o.jpg"/>
                    </a>
                </div>

                <div class="col-12 d-flex justify-content-center align-items-center">
                    <a href="/oauth2/authorization/google">
                        <img height="80px" width="350px" src="https://developers.google.com/static/identity/images/btn_google_signin_light_normal_web.png?hl=ko"/>
                    </a>
                </div>

                <div class="col-12 d-flex justify-content-center align-items-center">
                    <a href="/oauth2/authorization/facebook">
                        <img height="80px" width="350px" src="https://scontent-ssn1-1.xx.fbcdn.net/v/t39.2365-6/294967112_614766366879300_4791806768823542705_n.png?_nc_cat=105&ccb=1-7&_nc_sid=ad8a9d&_nc_ohc=tWdI3DiNAqEAX_E-ups&_nc_ht=scontent-ssn1-1.xx&oh=00_AfCvYLc-T09wTMM3UNJ7m6SOfARrwhprVKpwN_BuUfc7HA&oe=63A3F464"/>
                    </a>
                </div>

                <div id="naver" class="col-12 d-flex justify-content-center align-items-center">
                    <a href="/oauth2/authorization/naver" class="h-100 w-100">
                    </a>
                </div>

            </div>
        </div>
    </div>
</section>

<div style="padding: 3rem 3rem;"></div>

<%--footer--%>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
<%--footer--%>

<script src="js/app.js"></script>
<div id="fb-root"></div>
<script async defer crossorigin="anonymous" src="https://connect.facebook.net/ko_KR/sdk.js#xfbml=1&version=v15.0&appId=5529594730443349&autoLogAppEvents=1" nonce="4btaXXTM"></script>
</body>

</html>
