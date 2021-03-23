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
       
       <div class="freeboardsearch">
       <form action="freeboard">
   			<select name="choice">
				<option ${(param.choice == "title")?"selected":""} value="title">제목</option>
				<option ${(param.choice == "nickname")?"selected":""} value="nickname">닉네임</option>
			</select>
			 <input type="text" name="search" value="${param.search}" >
			 <input type="submit" value="검색" >
	   </form>
       </div>
       
       
       
            <div class="freeboardsection">
         		
<%-- 	${list.contentid} --%>
<%-- 	${list.title} --%>

         	<div class="boarddiv">
         	<div class="content"><span>제목</span></div>
         	<div class="nickname"><span>닉네임</span></div>
         	<div class="regdate"><span>작성일</span></div>
         	<div class="hits"><span>조회수</span></div>
         	<div class="like"><span>좋아요</span></div>
         	
         	</div>
<%--          	<c:if test="${param.search == null}"> --%>
         	<c:forEach items="${list}" var="list" varStatus = "t">
			<div class="boarddiv">
			<div class="content">
			 <c:if test="${empty sessionScope.id}">
			<a href="login" onclick="alert('로그인 후에 이용해주세요.')"><span>${list.title}</span></a>
			</c:if>
			<c:if test="${not empty sessionScope.id}">
			<a href="freeboarddetail?number=${list.number}"><span>${list.title}</span></a>
			</c:if>
			</div>
         	<div class="nickname"><span>${list.nickname}</span></div>
         	<div class="regdate"><span>${list.regdate}</span></div>
         	<div class="hits"><span><fmt:formatNumber type="number" pattern="###,###"
					value="${list.hits}" /></span></div>
         	<div class="like"><span><fmt:formatNumber type="number" pattern="###,###"
					value="${list.likes}" /></span></div>
			</div>
			</c:forEach>
<%-- 				</c:if> --%>
				
				
<%-- 				<c:if test="${param.search != null}"> --%>
<%-- 				<c:forEach items="${slist}" var="slist" varStatus = "t"> --%>
<!-- 			<div class="boarddiv"> -->
<%-- 			<div class="content"><span>${slist.title}</span></div> --%>
<%--          	<div class="nickname"><span>${slist.nickname}</span></div> --%>
<%--          	<div class="regdate"><span>${slist.regdate}</span></div> --%>
<%--          	<div class="hits"><span>${slist.hits}</span></div> --%>
<%--          	<div class="like"><span>${slist.like}</span></div> --%>
<!-- 			</div> -->
<%-- 			</c:forEach> --%>
				
<%-- 				</c:if> --%>
				<!-- startnum 변수 선언 및 값 할당 -->
	<c:set var = "page" value = "${empty param?1:param.p}"></c:set>
	<c:set var = "startNum" value = "${page-(page-1)%5}"></c:set>
	<c:set var = "lastNum" value = "${fn:substringBefore(Math.ceil(count/9),'.')}"></c:set>
				
				<!-- 현재 페이지 찍어보는것 -->
<!-- 	<div> -->
<!-- 		<h3>현재 페이지</h3> -->
<%-- 		<div><span>${(empty param.p)?1:param.p}</span>/ ${lastNum} page</div> --%>
		
<!-- 	</div> -->
	
	<!-- 페이지 이동 -->
	<!-- prev 이전 -->
		<div class="page">
		
		<c:if test="${startNum > 1}">
			<a href = "?p=${startNum-1}&f=${param.f}&q=${param.q}" class="prev">prev</a>
		</c:if>
		<c:if test="${startNum <= 1}">
			<a href = "#" onclick="alert('첫번째 페이지입니다.');" class="prev">prev</a>
		</c:if>
	
	<ul>
		<c:forEach var="i" begin="0" end="4">
		
		<c:if test="${param.p == (startNum+i)}">
				<c:set var= "style" value= "font-weight:bold; color:red;" />
		</c:if>
		
		<c:if test="${param.p != (startNum+i)}">
				<c:set var= "style" value= "" />
		</c:if>
		
		<c:if test="${(startNum+i)<=lastNum}">
		<li class=pnum><a style = "${style}" href="?p=${startNum+i}&f=${param.f}&q=${param.q}">${startNum+i}</a>
		</li>
		</c:if>
		
		</c:forEach>
	</ul>
	
	<!-- next 다음 -->
		<c:if test="${startNum+5 < lastNum}">
			<a href = "?p=${startNum+5}&f=${param.f}&q=${param.q}" class="next">next</a>
		</c:if>
		<c:if test="${startNum+5 > lastNum}">
			<a href = "#" onclick="alert('마지막 페이지입니다.');" class="next">next</a>
		</c:if>
				
       		</div>
       		
       		
      	</div>
      	</div>
    </section>
    <div class="writer">
    <c:if test="${empty sessionScope.id}">
<a href="login" onclick="alert('로그인 후에 이용해주세요.')" class="write_a"><span>글쓰기</span></a>
</c:if>
<c:if test="${not empty sessionScope.id}">
<a href="write" class="write_a"><span>글쓰기</span></a>
</c:if>
</div>
<!-- <div class="writer"><a href="write"><input type="button" value="글쓰기"></a></div> -->
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