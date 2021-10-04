package com.ogong.pms.handler.cafe;

import java.util.Collection;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.handler.AuthCeoMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class CeoCafeListHandler implements Command {

  PromptCafe promptcafe;

  public CeoCafeListHandler(PromptCafe promptcafe) {
    this.promptcafe = promptcafe; 
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 카페 목록");
    System.out.println();

    CeoMember ceoMember = null;
    try {
      ceoMember = AuthCeoMemberLoginHandler.getLoginCeoMember();
    } catch (NullPointerException e) {
      System.out.println(" >> 로그인 하세요.");
    }

    Collection<Cafe> cafeList = promptcafe.getCafeListByCeoMember(ceoMember.getCeoNo());

    if (cafeList.isEmpty()) {
      System.out.println(" >> 등록된 카페가 없습니다.");
      System.out.println("\n----------------------");
      System.out.println("1. 등록");
      System.out.println("0. 이전");
      int selectNo = Prompt.inputInt("선택> ");
      switch (selectNo) {
        case 1 : request.getRequestDispatcher("/ceoMember/cafeAdd").forward(request); break;
        default : return;
      }

    } else {

      for (Cafe cafe : cafeList) {
        System.out.printf(" (%s)\n [%s] | %s\n" , cafe.getNo(), cafe.getName()
            , CafeHandlerHelper.getCafeStatusLabel(cafe.getCafeStatus()));
      }

      System.out.println("\n----------------------");
      System.out.println("1. 상세");
      System.out.println("2. 등록");
      System.out.println("0. 이전");
      int selectNo = Prompt.inputInt("선택> ");
      switch (selectNo) {
        case 1 : request.getRequestDispatcher("/ceoMember/myCafeDetail").forward(request); break;
        case 2 : request.getRequestDispatcher("/ceoMember/cafeAdd").forward(request); break;
        default : return;
      }
    }
  }
}