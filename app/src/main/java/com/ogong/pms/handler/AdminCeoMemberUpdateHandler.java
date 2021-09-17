package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.CeoMember;
import com.ogong.util.Prompt;

public class AdminCeoMemberUpdateHandler extends AbstractCeoMemberHandler {

  PromptCeoMember promptCeoMember;

  public AdminCeoMemberUpdateHandler(List<CeoMember> ceoMemberList) {
    super(ceoMemberList);
  }

  //관리자용
  @Override 
  public void execute() {
    System.out.println();
    System.out.println("▶ 기업회원 변경"); 
    int inputceoNo = Prompt.inputInt(" 번호 : ");
    System.out.println();

    CeoMember ceoMember = promptCeoMember.findByCeoMemberNo(inputceoNo);

    if (ceoMember == null) {
      System.out.println(" >> 해당 번호의 기업 회원이 없습니다.");
      return;
    }

    String ceoBossName = Prompt.inputString(" 대표자명(" + ceoMember.getCeoBossName()  + ") : ");
    String ceophoto = Prompt.inputString(" 사진(" + ceoMember.getCeoPhoto()  + ") : ");
    String ceoEmail = Prompt.inputString(" 이메일(" + ceoMember.getCeoEmail() + ") : ");
    String ceoPassword = Prompt.inputString(" 비밀번호(" + ceoMember.getCeoPassword() + ") : ");

    String input = Prompt.inputString(" 정말 변경하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 기업 회원 변경을 취소하였습니다.");
      return;
    }

    ceoMember.setCeoBossName(ceoBossName);
    ceoMember.setCeoEmail(ceoEmail);
    ceoMember.setCeoPassword(ceoPassword);
    ceoMember.setCeoPhoto(ceophoto);

    System.out.println(" >> 기업 회원 정보를 변경하였습니다.");
  }

}
