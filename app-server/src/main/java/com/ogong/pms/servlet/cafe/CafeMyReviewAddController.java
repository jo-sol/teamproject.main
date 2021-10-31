package com.ogong.pms.servlet.cafe;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeReservationDao;
import com.ogong.pms.dao.CafeReviewDao;
import com.ogong.pms.domain.CafeReview;

@WebServlet("/cafe/reviewAdd")
public class CafeMyReviewAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeDao cafeDao;
  CafeReviewDao cafeReviewDao;
  CafeReservationDao cafeReservationDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeDao = (CafeDao) 웹애플리케이션공용저장소.getAttribute("cafeDao");
    cafeReviewDao = (CafeReviewDao) 웹애플리케이션공용저장소.getAttribute("cafeReviewDao");
    cafeReservationDao = (CafeReservationDao) 웹애플리케이션공용저장소.getAttribute("cafeReservationDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      int memberNo = Integer.parseInt(request.getParameter("perNo"));
      int reservationNo = Integer.parseInt(request.getParameter("reservationNo"));
      String content = request.getParameter("content");
      int grade = Integer.parseInt(request.getParameter("grade"));

      CafeReview cafeReview = new CafeReview();

      cafeReview.setReservationNo(reservationNo);
      cafeReview.setContent(content);
      cafeReview.setGrade(grade);

      try {
        cafeReviewDao.insertCafeReview(cafeReview);
        cafeReservationDao.updateCafeReservationReviewStatus(cafeReview.getReservationNo());
        sqlSession.commit();
      } catch (Exception e) {
        sqlSession.rollback();
      }

      //      request.setAttribute("perNo", memberNo);
      //      request.getRequestDispatcher("reviewList").forward(request, response);
      response.sendRedirect("reviewList?perNo=" + memberNo);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}