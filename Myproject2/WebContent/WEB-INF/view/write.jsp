<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/Myproject2/css/Myproject.css">
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
        <div class="writesection">
            <form method="post" action="write">    
            <div class="writechoice">
                     <span>게시판 선택 : </span>
                     <select  name="table" >
                        <option class="writeopt" value="freeboard">부동산 뉴스모음</option>
                        <option class="writeopt" value="freeboard">묻고 답하기</option>
                        <option class="writeopt" value="freeboard">자유게시판</option>
                     </select>
             </div>

            <div class="writetitle">
                <span>제목 : </span>
                <input type="text" name="title">
            </div>

            <div class="writetextarea">
                <textarea cols="30px" name="content"></textarea>
            </div>
            <input type="hidden" name="nickname" value="${id}">
            <div class="finishwriter"><input type="submit" value="글쓰기끄읏~"></div>
</form>
</div>   
           </div>
      </div>
</section>

</body>
</html>