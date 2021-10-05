package com.ogong.pms.handler.admin;

import static com.ogong.pms.domain.Cafe.GENERAL;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.pms.handler.cafe.PromptCafe;
import com.ogong.util.Prompt;

public class AdminCafeApprovalHandler implements Command {

  PromptCafe promptcafe;

  public AdminCafeApprovalHandler (PromptCafe promptcafe) {
    this.promptcafe = promptcafe;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 장소 승인");

    while (true) {
      System.out.println();
      int inputCafeNo = Prompt.inputInt(" 장소 번호 : ");

      Cafe cafe = promptcafe.findByCafeNo(inputCafeNo);

      if (cafe == null) {
        System.out.println(" >> 번호를 다시 선택하세요.");
        continue;
      } else if (cafe.getCafeStatus() != 0) {
        System.out.println(" >> 승인 대기 중인 카페가 아닙니다.\n    번호를 다시 선택하세요.");
        continue;
      } else if (cafe.getCafeStatus() == 0) {
        String input = Prompt.inputString(" 정말 승인하시겠습니까? (네 / 아니오) ");
        System.out.println();
        if (!input.equalsIgnoreCase("네")) {
          System.out.println(" >> 장소 승인을 취소하였습니다.");
          return;
        }
        cafe.setCafeStatus(GENERAL);
        promptcafe.updateCafe(cafe);
        System.out.printf(" >> '%s'를 승인하였습니다.\n", cafe.getName());
        return;
      }
    }
  }
}
