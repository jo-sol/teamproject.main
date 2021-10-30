package com.ogong.pms.servlet.auth;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.domain.Member;

@WebServlet("/member/login")
public class AuthPerMemberLoginHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MemberDao memberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String email = request.getParameter("email");
    String password = request.getParameter("password");

    try {
      Member member = memberDao.findByEmailAndPassword(email, password);
      request.setAttribute("loginMember", member);
      request.getRequestDispatcher("../auth/PerLogin.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);

      request.getRequestDispatcher("/Error.jsp").forward(request, response);

    }

  } 
}
