package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class AdminMemberDetailHandler extends AbstractMemberHandler {

  PromptPerMember promptPerMember;

  public AdminMemberDetailHandler(List<Member> memberList, PromptPerMember promptPerMember) {
    super(memberList);
    this.promptPerMember = promptPerMember;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println();
    System.out.println("▶ 회원 프로필 상세보기");

    for (Member member : memberList) {
      if (member.getPerNickname() != AuthAdminLoginHandler.getLoginAdmin().getMasterNickname()) {

        int inputMemberNo = Prompt.inputInt(" 번호 : ");

        member = promptPerMember.findByMemberNo(inputMemberNo);
        System.out.println();
        System.out.printf(" [%s]\n", member.getPerNickname());
        System.out.printf(" >> 이메일 : %s\n", member.getPerEmail());
        System.out.printf(" >> 사진 : %s\n", member.getPerPhoto());
        System.out.printf(" >> 가입일 : %s\n", member.getPerRegisteredDate());

        return;
      }
    }
    System.out.println(" >> 로그인 하세요.");
  }
}
