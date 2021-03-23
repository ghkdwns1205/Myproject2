<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/Myproject2/css/Myproject.css">
<script type="text/javascript" src="${root}/js/user/signUp.js"></script>
</head>

<body>

    <div class="wrap">

        <!-- <div>
            <a href="www.naver.com"><img src="/naverimg.jpg" alt=""></a>

        </div> -->
        
    <header>
        <div class="inner">
        
            <div class="headertop">
                    <a href="login"><input type="button" value="로그인" class="headlogin"></a>
                  <a href="main"><input type="button" value="메인홈페이지"></a>
            </div>
        
            <div class="banner">
                <h1><span class="burin">부리니티</span>에 오신것을 환영합니다 </h1>
                <h3>(부린이들의 커뮤니티)</h3>
                
            </div>
        </div>
    </header>

    <section class="join">
        <div class="inner">
            <br>
            <fieldset>
                <legend>회원가입</legend>
<br>
<form id="joinform" name="joinform" method="post" action="join" onsubmit="return createFrom(this)">
             <div class="nid">아이디(닉네임) :
                 <input type="text" name="nid" size="15">
                 <button type="button" onclick="idCheck(joinform, '${root}')">중복 확인</button>
             </div>
<br>
             <div class="npass">비밀번호 :
                  <input type="password" name="npass" size="15" minlength="3">
             </div>
<br>
             
             <div class="npasscheck">비밀번호 확인 :
                  <input type="password" name="npasscheck" size="15" minlength="3">
             </div>
<br>
             <div class="name">이름 :
                  <input type="text" name="name" size="15">
             </div>
<br>
             <div class="email">이메일 :
                  <input type="text" name="email" size="20">
             </div>
<br>
             <div class="gender">성별 :
                <input type="radio" name="gender" value="M">남자
                <input type="radio" name="gender" value="W">여자
           </div>
<br>
                <div class="joinsub">

                    <input type="submit" value="가입">
                    <input type="reset" value="취소">

                </div>
</form>
            </fieldset>
        </div>
    </section>
    </div>
</body>
</html>