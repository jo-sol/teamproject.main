package com.ogong.pms.servlet.askBoard;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.dao.AskBoardDao;
import com.ogong.pms.domain.AskBoard;

@WebServlet("/askboard/permydetail")
public class AskBoardPerMyDetailCotroller extends HttpServlet {
  private static final long serialVersionUID = 1L;

  AskBoardDao askBoardDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    askBoardDao = (AskBoardDao) 웹애플리케이션공용저장소.getAttribute("askBoardDao");
  }

  //마이페이지 - 내가 쓴 문의내역(개인)
  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int askNo = Integer.parseInt(request.getParameter("askNo"));
      AskBoard myAskBoard = askBoardDao.findByNo(askNo);

      if (myAskBoard == null) {
        throw new Exception("문의게시글 상세 오류!");
      }

      request.setAttribute("myAskBoard", myAskBoard);
      request.getRequestDispatcher("/askBoard/AskBoardPerMyDetail.jsp").forward(request, response);

    } catch (Exception e) {
      e.getStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}