<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "fn" uri ="http://java.sun.com/jsp/jstl/functions" %>
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
       
       <div class="fbdetailsection">
      
		  <div class="fbdetail1">
		  		${list.content }
		  		
		  </div>
		  <div class="fbdetail2">
		  		<div class="dd">
		  			<span> ${list.number } 제목 : </span>${list.title }
		  		</div>
		  		<div class="dd">
		  			<span>닉네임 : </span>${list.nickname }
		  		</div>
		  		<div class="dd">
		  			<span>날짜 : </span>${list.regdate }
		  		</div>
		  		<div class="dd">
		  			<div class="fbdd1"><span>조회수 : ${list.hits}</span></div>
		  			<div class="fbdd2"><span>좋아요 : ${list.likes}</span></div>
		  		</div>
	  			<div class="dd">
		  			<span>댓글</span>
		  		</div>
		  		<c:forEach items="${dcl}" var="dcl">
		  		<div class="ddnick">${dcl.nickname}</div>
		  		<div class="ddreg">${dcl.regdate}</div>
		  		<c:if test="${not empty color}">
		  		<div class="ddcommred" style="color:red">${dcl.comment}</div>
		  		</c:if>
		  		<c:if test="${empty color}">
		  		<div class="ddcomm" >${dcl.comment}</div>
		  		</c:if>
		 		</c:forEach>
		 			 
		  </div>
	
		<div class="eotrmf">
		<form action="freeboarddetail?number=${num}" method="post">
			
			<div class="tbox"><input type="text" name="comment" class="tboxinput"></div>
				<div class="botcolor">
				<input type="checkbox" name="color" value="red"><div class="red"></div>
				</div>
			<div class="submit"><input type="submit" value="댓글입력"></div>	
		</form>
		</div>
	
	</div>
	
	
	
	
	
	<a href="interest?number=${list.number}&like=${list.likes}"><button class="likes">좋아요</button></a>
	
	
	
	</div>
	</section>
<!-- 	<form action="freeboarddetail" method="post"> -->
<!-- 		<input type="button" name="interest" value="좋아요"> -->
<!-- 		<input type="submit" value="싫어요"> -->
<!-- 	</form> -->

	<footer>
         <div class="inner">
            <div class="footdiv">
            <h2>광고문의 123-4567</h2>
            </div> 
        </div>
    </footer>
	
    
  
</body>
</html>