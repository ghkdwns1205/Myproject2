<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"  href="/Myproject2/css/Myproject.css">
</head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
 <script>

$(document).ready(function () {
// 아이디비번 호버
$('.sec2 .inner .serchid').hover(function(){
    $('.sec2 .inner ul .serchid .show1').stop().show(1000);
    
}, function(){
    $('.sec2 .inner ul .serchid .show1').stop().hide(1000);
});

// 소셜네트워크 호버
$('.sec2 .inner .social').hover(function(){
    $('.sec2 .inner ul .social .show2').stop().show(1000);
    
}, function(){
    $('.sec2 .inner ul .social .show2').stop().hide(1000);
});


})

</script>
<body>
    <div class="wrap">

        <!-- <div>
            <a href="www.naver.com"><img src="/naverimg.jpg" alt=""></a>

        </div> -->
    <header>
        <div class="inner">
        
             <div class="headertop">
                  <a href="main"><input type="button" value="메인홈페이지"></a>
                
            </div>
             
            <div class="banner">
                <h1><span class="burin">부리니티</span>에 오신것을 환영합니다 </h1>
                <h3>(부린이들의 커뮤니티)</h3>
                
            </div>
        </div>
    </header>
    <section class="sec1">
    <form action="login" method="post">
        <div class="inner">
            <div class="login">
            <div class="loginid">ID : <input type="text" class="logid" name="id" placeholder="아이디를 입력해주세요"></div><br>
            <div>PASSWORD : <input type="password" class="logpass" name="pass" placeholder="비밀번호를 입력해주세요"></div>
            </div>
            <div class="button"><input type="submit" value="로그인하기" ></div>
        </div>
        </form>
    </section>
    <section class="sec2">
        <div class="inner">
            <ul>
                  <li class="serchid">아이디/비밀번호 찾기 ▼
                    <ul class="ulfont show1">
                       <li><a href="">아이디 찾기</a></li>
                       <li><a href="">비밀번호 찾기</a></li>
                    </ul>
                </li>
                 <li><a href="/Myproject2/join">회원가입</a></li>
                <li class="social">소셜계정 로그인 ▼
                    <ul class="ulfont show2">
                       <li><a href="${apiURL}">네이버 로그인</a></li>
                       <li><a href="">카카오톡 로그인</a></li>
                    </ul>
                </li>
            </ul>

        </div>
    </section>
    </div>
</body>
</html>