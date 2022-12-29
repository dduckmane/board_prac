<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <%@ include file="./include/static-head.jsp" %>
  <%--  custom css--%>
  <link rel="stylesheet" href="css/loginForm.css">
  <title>🍴Matjip</title>
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
                <div class="text-center py-10 font-monospace">
                    <h3 class="font-monospace fst-italic">회원가입/로그인</h3>
                </div>

                <div class="divider"></div>

                <div class="col-12 d-flex justify-content-center align-items-center py-2">
                    <a href="/oauth2/authorization/kakao">
                        <img height="70px" width="338px" src="//k.kakaocdn.net/14/dn/btqCn0WEmI3/nijroPfbpCa4at5EIsjyf0/o.jpg"/>
                    </a>
                </div>

                <div class="col-12 d-flex justify-content-center align-items-center">
                    <a href="/oauth2/authorization/google">
                        <img height="70px" width="340px" src="https://developers.google.com/static/identity/images/btn_google_signin_light_normal_web.png?hl=ko"/>
                    </a>
                </div>

                <div id="naver" class="col-12 d-flex justify-content-center align-items-center">
                    <a href="/oauth2/authorization/naver" class="h-100 w-100">
                    </a>
                </div>
                <div class="text-center py-10 font-monospace">
                    <h6 class="mt-3 font-monospace fst-italic" style="color:#858282;">1초 회원가입하고 바로 로그인하세요</h6>
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
