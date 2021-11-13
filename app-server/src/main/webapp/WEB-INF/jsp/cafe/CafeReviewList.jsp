<%@page import="com.ogong.pms.web.cafe.CafeHandlerHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <style>
  * {
    font-size: 14px;
  }
  a {
   text-decoration:none;
  }
  .all-content {
    width: 100%;
    margin: 0 auto;
    padding: 40px 0;
  }
  </style>
</head>
<body>
<br><br>
<div class="all-content">
<c:if test='${not empty reviewList}'>
<table class="table table-responsive text-center align-middle">
	<thead>
	  <tr>
	    <th>번호</th>
	    <th>스터디카페</th>
	    <th>별점</th>
	    <th>내용</th>
	    <th>등록일</th>
	    <th>수정</th>
	    <th>삭제</th>
	  </tr>
	</thead>
  <tbody>

	<c:forEach items="${reviewList}" var="review">
	 <tr>
	    <td>${review.reviewNo}</td>
	    <td><a href='detail?no=${review.cafe.no}'>${review.cafe.name}</a></td>
	    <td>
	    <c:set var="grade" value="${review.grade}" /> 
	          <% 
	          int grade = (int) pageContext.getAttribute("grade");
	          String star = CafeHandlerHelper.getReviewGradeStatusLabel(grade);
	          pageContext.setAttribute("star", star);
	          %>
	      ${star}(${review.grade}/5)
	    </td>
	    <td>${review.content}</td>
	    <td>${review.registeredDate}</td>
	    <td>
	      <button class="btn btn-outline-dark btn-sm"><a href="reviewUpdateForm?reviewNo=${review.reviewNo}">수정</a></button>
	    </td>
	    <td>
	      <button class="btn btn-outline-dark btn-sm"><a href="reviewDelete?reviewNo=${review.reviewNo}">삭제</a></button>
	    </td>
	 </tr>
	</c:forEach>
  </tbody>
</table>
</c:if>

<c:if test='${empty reviewList}'>
   등록된 리뷰가 없습니다.<br><br>  
</c:if>
<br>
</div>