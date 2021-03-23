<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
<!DOCTYPE html>
 <c:set var="root" value="${pageContext.request.contextPath}" />
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/Myproject2/css/Myproject.css">
     <link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	 <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>

 <script>
    $(document).ready(function(){
      $('.slider').bxSlider();
    });
  </script>
</head>

<body>
    
    <div class="wrap">

        <!-- <div>
            <a href="www.naver.com"><img src="/naverimg.jpg" alt=""></a>

        </div> -->
    <header>
        <div class="inner">
           
            <div class="headertop">
                <c:if test="${empty sessionScope.id}">
                <a href="login"><input type="button" value="로그인" class="headlogin"></a>
<!--                   <a href="logout"><input type="button" value="로그아웃" class="headlogout"></a> -->
                  <a href="join"><input type="button" value="회원가입" class="headsignup"></a>
                  </c:if>
                  
                   <c:if test="${not empty sessionScope.id}">
<!--                 <a href="login"><input type="button" value="로그인" class="headlogin"></a> -->
                  <a href="logout"><input type="button" value="로그아웃" class="headlogout"></a>
<!--                   <a href="join"><input type="button" value="회원가입" class="headsignup"></a> -->
                  </c:if>
             </div>

            <div class="banner">
                <h1><span class="burin">부리니티</span>에 오신것을 환영합니다 </h1>
                <h3>(부린이들의 커뮤니티)</h3>
                
            </div>
        </div>
    </header>

    <nav class="menubar">
        <div class="inner">
             <ul>
                <li>청약 일정</li>
                <li>부동산 뉴스모음</li>
                <li>부동산 이미지 게시판</li>
                <a href="freeboard"><li>자유 게시판</li></a>
                <li>공지사항</li>
             </ul>
         </div>
    </nav>

    <section class="mainsection">
        <div class="inner">
            <div class="maincont">
         
            <div class="slider">
   				 <img src="${root}/img/dmere1.jpg" alt="드메르1">
  			     <img src="${root}/img/dmere1.jpg" alt="드메르2">
   				 <img src="${root}/img/dmere1.jpg" alt="드메르3">
   			
			</div>

        </div>
    </section>

    <footer>
         <div class="inner">
            <div class="footdiv">
            <h2>광고문의 123-4567</h2>
            </div> 
        </div>
    </footer>
    </div>
</body>
</html>