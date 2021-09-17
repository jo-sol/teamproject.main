package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.AdminNotice;
import com.ogong.util.Prompt;

public class AdminNoticeDeleteHandler extends AbstractAdminNoticeHandler {

  public AdminNoticeDeleteHandler(List<AdminNotice> adminNoticeList) {
    super(adminNoticeList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println();
    System.out.println("▶ 공지 삭제");
    int adminnotiNo = Prompt.inputInt(" 번호 : ");

    AdminNotice adminWriteList = findByNotiNo(adminnotiNo);

    if (adminWriteList == null) {
      System.out.println(" >> 공지를 다시 선택하세요.");
      return;
    }

    String inputnotice = Prompt.inputString(" 정말 삭제하시겠습니까? (네 / 아니오) ");
    if (!inputnotice.equalsIgnoreCase("네")) {
      System.out.println(" >> 삭제가 취소되었습니다.");
      return;
    }

    adminNoticeList.remove(adminWriteList);

    System.out.println(" >> 공지가 삭제되었습니다.");
  }

}
