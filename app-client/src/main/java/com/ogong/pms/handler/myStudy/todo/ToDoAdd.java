package com.ogong.pms.handler.myStudy.todo;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.domain.ToDo;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class ToDoAdd implements Command {

  int ToDoNo = 5;
  RequestAgent requestAgent;

  public ToDoAdd(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  //등록
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ To-Do List 등록");
    System.out.println();

    HashMap<String,String> params = new HashMap<>();
    params.put("studyNo", String.valueOf(request.getAttribute("inputNo")));

    requestAgent.request("study.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 스터디 출력 오류.");
      return;
    }

    Member member = AuthPerMemberLoginHandler.getLoginUser();
    Study myStudy = requestAgent.getObject(Study.class);

    if (member == null) {
      System.out.println(" >> 로그인 한 회원만 조회 가능합니다.");
      return;
    }

    if (myStudy.getStudyTitle() == null) {
      System.out.println(" >> 가입된 스터디가 없습니다.");
      return;
    }

    List <ToDo> todoList = myStudy.getMyStudyToDo();
    ToDo todo = new ToDo();

    todo.setTodoContent(Prompt.inputString(" 내용: "));
    todo.setTodoRemark(Prompt.inputString(" 비고: "));
    todo.setTodoStatus(1);
    todo.setTodoDate(new Date(System.currentTimeMillis()));

    String input = Prompt.inputString(" 정말 등록하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 등록을 취소하였습니다.");
      request.getRequestDispatcher("/myStudy/todoList").forward(request);
      return;
    }

    todo.setTodoNo(ToDoNo++);
    todoList.add(todo);
    myStudy.setMyStudyToDo(todoList);

    requestAgent.request("study.update", myStudy);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 해당 스터디에서 등록 실패.");
      return;
    }

    System.out.println(" >> 할 일이 등록되었습니다.");
    request.getRequestDispatcher("/myStudy/todoList").forward(request);
  }
}


