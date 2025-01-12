<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>

    <style>
    * {
      margin: 0;
      padding: 0;
      font-size: 14px;
      line-height: 1.5;
    }
    
    .nav-scroller py-1 mb-2 {
      text-align: -webkit-center;
    }
    
    .justify-content-between {
      width: 300px;
      align-items: center;
      /* text-align: center; */
      /* justify-content: center; */
    }
    
    .pt-4 {
      height: auto;
      padding-top: 0;
    }
    
    .link-secondary:hover {
      text-decoration: underline;
    }
    
    /* .btn {
      justify-content: center;
    } */
    
    /* ul {
      list-style: none;
    } */
    
    /* .tabmenu {
      xmax-width: 1000px;
      xmargin: 0 auto;
      xposition: relative;
      xmargin-top: 50px;
    } */
    
    /* .tabmenu ul li {
      display: inline-block;
      width: 20%;
      float: left;
    } */
    
    /* .tabmenu ul li a {
      display: block;
      xline-height: 40px;
      xtext-decoration: none;
    } */
    
    /* .tabCon {
      display: none;
      position: absolute;
      xpadding: 20px 0px;
      xleft: 0;
      xtop: 40px;
      xbox-sizing: border-box;
      xwidth: 1000px;
    } */
    
    /* .btnCon:target {
      xbackground: rgb(247, 231, 215);
    } */
    
    /* .btnCon:target .tabCon {
      display: block;
    } */
    
    .modal-backdrop {
      position: relative;
    }

    .modal-backdrop.show {
      opacity: 0;
    }
    
    .modal-title {
      font-weight: bold;
    }
    
    .modal-body {
      padding: 2rem;
    }

    .mb-3 select {
      height: 33.5px;
      width: 442px;
      /* width: 100px; */
    }
    
    #services {
      width: 100%;
    }
    
    .section-header p {
      padding-bottom: 60px;
    }
    
    /* form {
      padding-bottom: 50px;
    } */
    
    .section-bg {
      background: white;
    }
    
    #search {
      text-align: center;
      padding-bottom: 30px;
    }
    
    h3 p form .write {
      width: 1299px;
    }
    
    .row {
      padding-top: 30px;
    }
    
    #empty-study {
      text-align: center;
    }
    
    /* #services .box {
      height: 309.6px;
    } */
    
    .intro {
      /* 한 줄 자르기 */
      display: inline-block;
      width: 331.99px;
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;

      /* 여러 줄 자르기 추가 스타일 */
      white-space: normal;
      line-height: 1.2;
      height: 1.5em;
    }
    </style>

    <!-- <script>
      location.href = "#tab1";
    </script> -->

  </head>
  <body>
  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>
  
  <!-- <div class="tabmenu">
    <ul> -->

  <div class="nav-scroller py-1 mb-2" style="text-align:-webkit-center;">
    <nav class="nav d-flex justify-content-between">
      <a class="p-2 link-secondary" href="${contextPath}/app/study/list">전체</a>
      <a class="p-2 link-secondary" href="${contextPath}/app/study/list/ing">진행</a>
      <a class="p-2 link-secondary" href="${contextPath}/app/study/list/end">종료</a>
    </nav>
  </div>

    <!--===== 종료 스터디 목록 =====-->
    <!-- <li id="tab1" class="btnCon"><a class="btn" href="#tab1">종료</a>
      <div class="tabCon"> -->
  
    <!-- 스터디 목록 -->
    <section id="services" class="services section-bg">
      <div class="container" data-aos="fade-up">

        <header class="section-header">
          <h3 style="width: 1299px;">Today Study</h3>
          <p style="width: 1299px;">함께 성장할 스터디를 모집해보세요</p>
        </header>
        
        <!-- 검색 -->
          <div id="search" style="width: 1299px;">
            <form action="../search" method='get' style="width: 1299px;">
              <select name="where">
                <option value="1">분야</option>
                <option value="2">제목</option>
                <option value="3">지역</option>
              </select> <input type="text" name="keyword">
              <button class="btn btn-outline-dark btn-sm">검색</button>
            </form>
          </div>
          
          
          
          <c:if test="${loginUser ne null}">
          <!-- 스터디 등록 -->
          <!-- <div type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="@mdo">
            <i class="bi bi-pencil-square"></i>
            글쓰기
          </div> -->

          <div class="write" style="width:1299px; text-align:right;">
          <button type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="@mdo" style="margin-right:11px;">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
              <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
              <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
            </svg>
            글쓰기
          </button>
          </div>

           <div class="modal fade" id="exampleModal" tabindex="-1"
             aria-labelledby="exampleModalLabel" aria-hidden="true">
             <div class="modal-dialog">
               <div class="modal-content">

                 <!-- 상단 헤더 -->
                 <div class="modal-header">
                   <h5 class="modal-title" id="exampleModalLabel">스터디 등록</h5>
                   <button type="button" class="btn-close"
                     data-bs-dismiss="modal" aria-label="Close"></button>
                 </div>

                 <div class="modal-body">
                   <form action='../add' method='post'>
                     <!-- 제목 -->
                     <div class="mb-3">
                       <label for='f-studyTitle'>제목</label> <input
                         id='f-studyTitle' type='text' name='studyTitle'
                         class="form-control" required
                         oninvalid="this.setCustomValidity('제목을 입력하세요.')"
                         oninput="this.setCustomValidity('')">
                     </div>

                     <!-- 분야 -->
                     <div class="mb-3">
                       <label for='f-subjectNo'>분야</label> <select name="subjectNo">
                         <option value="1" name="subjectNo" selected>어학</option>
                         <option value="2" selected>자격증</option>
                         <option value="3" selected>취업</option>
                         <option value="4" selected>IT</option>
                         <option value="5" selected>예체능</option>
                         <option value="6" selected>기타</option>
                       </select>
                     </div>
                     
                     <!-- 지역 -->
                     <div class="mb-3">
                       <label for='f-area'>지역</label> <input id='f-area'
                         type='text' name='area' class="form-control" required
                         oninvalid="this.setCustomValidity('시 / 도 / 구를 입력하세요.')"
                         oninput="this.setCustomValidity('')">
                     </div>

                     <!-- 최대 인원수 -->
                     <div class="mb-3">
                       <label for='f-numberOfPeple'>최대 인원수</label> <select
                         name="numberOfPeple">
                         <option value="2" name="numberOfPeple" selected>2</option>
                         <option value="3" selected>3</option>
                         <option value="4" selected>4</option>
                         <option value="5" selected>5</option>
                         <option value="6" selected>6</option>
                         <option value="7" selected>7</option>
                         <option value="8" selected>8</option>
                         <option value="9" selected>9</option>
                         <option value="10" selected>10</option>
                         <option value="11" selected>11</option>
                         <option value="12" selected>12</option>
                         <option value="13" selected>13</option>
                         <option value="14" selected>14</option>
                         <option value="15" selected>15</option>
                         <option value="16" selected>16</option>
                         <option value="17" selected>17</option>
                         <option value="18" selected>18</option>
                         <option value="19" selected>19</option>
                         <option value="20" selected>20</option>
                         <option value="21" selected>21</option>
                         <option value="22" selected>22</option>
                         <option value="23" selected>23</option>
                         <option value="24" selected>24</option>
                         <option value="25" selected>25</option>
                         <option value="26" selected>26</option>
                         <option value="27" selected>27</option>
                         <option value="28" selected>28</option>
                         <option value="29" selected>29</option>
                         <option value="30" selected>30</option>
                       </select>
                     </div>

                     <!-- 대면 상태 -->
                     <div class="mb-3">
                       <label for='f-faceNo'>대면 상태</label> <select name="faceNo">
                         <option value="1" name="faceNo" selected>대면</option>
                         <option value="2" selected>비대면</option>
                         <option value="3" selected>대면/비대면</option>
                       </select>
                     </div>

                     <!-- 소개글 -->
                     <div class="mb-3">
                       <label for='f-introduction'>소개글</label>
                       <textarea id='f-introduction' type='text'
                         name='introduction' class="form-control" rows="3" required
                         oninvalid="this.setCustomValidity('소개글을 입력하세요.')"
                         oninput="this.setCustomValidity('')"></textarea>
                     </div>

                     <!-- 진행 상태 -->
                     <div class="mb-3">
                       <label for='f-studyStatus'>진행 상태</label> <select
                         name="studyStatus">
                         <option value="1" name="studyStatus" selected>진행</option>
                         <option value="2" disabled>종료</option>
                       </select>
                     </div>

                     <!-- 하단 버튼 -->
                     <div class="modal-footer">
                       <button type="button" class="btn btn-light"
                         data-bs-dismiss="modal">취소</button>
                       <button class="btn btn-dark">등록</button>
                     </div>

                   </form>
                 </div>

               </div>
             </div>
           </div>

          </c:if>



        <div class="row">
        <c:if test='${not empty studyEndList}'>
          <c:forEach items="${studyEndList}" var="study">
            <div class="col-md-6 col-lg-4" data-aos="zoom-in" data-aos-delay="100">
              <div class="box">
                
                <c:choose>
                  <c:when test="${study.subjectNo eq '1'}">
                    <div class="icon" style="background:#fceef3;"><i class="bi bi-globe2" style="color:#ff689b;"></i></div>
                  </c:when>
                  <c:when test="${study.subjectNo eq '2'}">
                    <div class="icon" style="background:#fff0da;"><i class="bi bi-book" style="color:#e98e06;"></i></div>
                  </c:when>
                  <c:when test="${study.subjectNo eq '3'}">
                    <div class="icon" style="background:#e6fdfc;"><i class="bi bi-briefcase" style="color:#3fcdc7;"></i></div>
                  </c:when>
                  <c:when test="${study.subjectNo eq '4'}">
                    <div class="icon" style="background:#eafde7;"><i class="bi bi-display" style="color:#41cf2e;"></i></div>
                  </c:when>
                  <c:when test="${study.subjectNo eq '5'}">
                    <div class="icon" style="background:#e1eeff;"><i class="bi bi-lightbulb" style="color:#2282ff;"></i></div>
                  </c:when>
                  <c:when test="${study.subjectNo eq '6'}">
                    <div class="icon" style="background:#ecebff;"><i class="bi bi-collection" style="color:#8660fe;"></i></div>
                  </c:when>
                </c:choose>
  
                <h4 class="title"><a href="../detail?studyno=${study.studyNo}">${study.studyTitle}</a></h4>
  
                <c:choose>
                  <c:when test="${study.countMember ne study.numberOfPeple && study.studyStatus ne '2'}">
                    <p id="wanted" style="text-align:right; font-size:12px; font-family:fantasy; margin:0;">모집중</p>
                  </c:when>
                  <c:when test="${study.countMember eq study.numberOfPeple && study.studyStatus ne '2'}">
                    <p id="wanted" style="text-align:right; font-size:12px; font-family:fantasy; margin:0;">모집중</p>
                  </c:when>
                  <c:when test="${study.countMember ne study.numberOfPeple && study.studyStatus eq '2'}">
                    <p id="wanted" style="text-align:right; font-size:12px; font-family:fantasy; margin:0;">모집완료</p>
                  </c:when>
                  <c:when test="${study.countMember eq study.numberOfPeple && study.studyStatus eq '2'}">
                    <p id="wanted" style="text-align:right; font-size:12px; font-family:fantasy; margin:0;">모집완료</p>
                  </c:when>
                </c:choose>
  
                <div class="intro">
                  <p style="text-align:justify; -webkit-text-stroke-width:thin;">${study.introduction}</p>
                </div>
                <p class="description">${study.faceName}</p>
                <p class="description">인원 ${study.countMember}/${study.numberOfPeple}</p>
                <p class="description">${study.owner.perNickname} ⭐${study.countBookMember}</p>
              
              </div>
            </div>
          </c:forEach>
        </c:if>
        
        <!-- 검색 결과 -->
          <div id="empty-study">
            <c:if test='${empty studyEndList}'>
              검색 결과가 존재하지 않습니다.
              <br>
            </c:if>
          </div>
        </div>

      </div>
    </section>
    <!-- End 스터디 목록 -->

     <!-- </div>
   </li> -->
   <!--===== End 종료 스터디 목록 =====-->



    <!-- </ul>
  </div> -->

  </body>
</html>
