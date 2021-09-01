package com.ogong.pms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.ogong.menu.Menu;
import com.ogong.menu.MenuGroup;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.Calender;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Manager;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.NewStudy;
import com.ogong.pms.domain.NoticeBoard;
import com.ogong.pms.domain.ToDo;
import com.ogong.pms.handler.AskBoardHandler;
import com.ogong.pms.handler.CafeHandler;
import com.ogong.pms.handler.CafeReservationHandler;
import com.ogong.pms.handler.CalenderHandler;
import com.ogong.pms.handler.FreeBoardHandler;
import com.ogong.pms.handler.LoginHandler;
import com.ogong.pms.handler.ManagerHandler;
import com.ogong.pms.handler.MemberHandler;
import com.ogong.pms.handler.NewStudyHandler;
import com.ogong.pms.handler.NoticeBoardHandler;
import com.ogong.pms.handler.ToDoHandler;
import com.ogong.util.Prompt;

public class App {
  List<NewStudy> studyList = new LinkedList<>();
  NewStudyHandler newStudyHandler = new NewStudyHandler(studyList);

  List<Member> memberList = new LinkedList<>();
  MemberHandler memberHandler = new MemberHandler(memberList);
  LoginHandler loginHandler = new LoginHandler(memberList, memberHandler);

  List<Manager> managerList = new ArrayList<>();
  ManagerHandler managerHandler = new ManagerHandler(managerList);

  List<NoticeBoard> noticeBoardList = new ArrayList<>();
  NoticeBoardHandler noticeboardHandler = new NoticeBoardHandler(noticeBoardList, managerList, managerHandler);

  List<AskBoard> askBoardList = new ArrayList<>();
  AskBoardHandler askBoardHandler = new AskBoardHandler(askBoardList);

  List<Cafe> cafeList = new ArrayList<>();
  List<CafeReview> cafeReview = new ArrayList<>();
  List<CafeReservation> reserList = new ArrayList<>();
  CafeHandler cafeHandler = new CafeHandler(cafeList, cafeReview, memberHandler, reserList);

  List<ToDo> toDoList = new ArrayList<>();
  ToDoHandler toDoHandler = new ToDoHandler(toDoList);

  List<FreeBoard> freeBoardList = new ArrayList<>();
  FreeBoardHandler freeBoardHandler = new FreeBoardHandler(memberList, freeBoardList, loginHandler);

  List<Calender> calenderList = new ArrayList<>();
  CalenderHandler calenderHandler = new CalenderHandler(calenderList);

  CafeReservationHandler cafeRservationHandler = new CafeReservationHandler(reserList);

  public static void main(String[] args) {
    App app = new App(); 
    app.welcomeservice();
  }

  void welcomeservice() {
    welcome().execute();
    service();
  }

  void service() {
    createMenu().execute();
    Prompt.close();
  }

  static Menu welcome() {
    ////////////////////////////////////////////////////////////////
    System.out.println("\u2728" + "오늘의 공부" + "\u2728");
    System.out.println("\u0020\u2229__\u2229\u0020\u00a0\u00a0\u00a0\u0020\u2229__\u2229\u0020");
    System.out.println("\u0028\u0020\u0027\u03c0\u0027\u0029\u00a0\u00a0\u00a0\u0028\u0027\u03c0\u0027\u00a0\u0029");
    System.out.println("\u0028\u0020\u2283\u2755\u2282\u0029\u00a0\u00a0\u00a0\u0028\u2283\u2755\u2282\u00a0\u0029");
    ////////////////////////////////////////////////////////////////
    MenuGroup welcomeMenuGroup = new MenuGroup("발표를 시작하겠습니다");
    welcomeMenuGroup.setPrevMenuTitle("시작");
    return welcomeMenuGroup;
  }

  Menu createMenu() {
    //---------------------------------------------------
    MenuGroup mainMenuGroup = new MenuGroup("메인");
    mainMenuGroup.setPrevMenuTitle("종료");

    createAdminMenu(mainMenuGroup);
    createUserMenu(mainMenuGroup);
    createCeoMenu(mainMenuGroup);

    //---------------------------------------------------
    return mainMenuGroup;
  }

  // 관리자
  Menu createAdminMenu(MenuGroup mainMenuGroup) {
    return null;
  }

  // 회원
  Menu createUserMenu(MenuGroup mainMenuGroup) {
    MenuGroup userMenuGroup = new MenuGroup("개인"); 
    mainMenuGroup.add(userMenuGroup);
    userMenuGroup.add(new Menu("회원가입", Menu.ENABLE_LOGOUT) {
      @Override
      public void execute() {
        memberHandler.add();
      }});


    //-------------------------------------------------------------
    //로그인o, SNS로그인o, ID/PW 찾기o, 회원가입o, 로그아웃o
    MenuGroup loginMenu = new MenuGroup("로그인"); 
    userMenuGroup.add(loginMenu);

    loginMenu.add(new Menu("로그인", Menu.ENABLE_LOGOUT) {
      @Override
      public void execute() {
        loginHandler.addLoginPage();
      }});
    loginMenu.add(new Menu("NAVER로 시작하기", Menu.ENABLE_LOGOUT) {
      @Override
      public void execute() {
        loginHandler.naverLogin();
      }});
    loginMenu.add(new Menu("KAKAO로 시작하기", Menu.ENABLE_LOGOUT) {
      @Override
      public void execute() {
        loginHandler.kakaoLogin();
      }});
    loginMenu.add(new Menu("GOOGLE로 시작하기", Menu.ENABLE_LOGOUT) {
      @Override
      public void execute() {
        loginHandler.googleLogin();
      }});
    loginMenu.add(new Menu("ID/PW 찾기", Menu.ENABLE_LOGOUT) {
      @Override
      public void execute() {
        memberHandler.selectFindEmailPw();
      }});
    loginMenu.add(new Menu("회원 가입", Menu.ENABLE_LOGOUT) {
      @Override
      public void execute() {
        memberHandler.add();
      }});
    loginMenu.add(new Menu("로그아웃", Menu.ENABLE_LOGIN) {
      @Override
      public void execute() {
        loginHandler.logOut();
      }});
    //--------------------------------------------------------------- 

    //--------------------------------------------------------------- 
    // 리턴위치 지정(탈퇴시 메인으로 돌아가기)
    MenuGroup mypageMenu = new MenuGroup("마이 페이지", Menu.ENABLE_LOGIN); 
    userMenuGroup.add(mypageMenu);
    mypageMenu.add(new Menu("개인 정보") {
      @Override
      public void execute() {
        memberHandler.detail();
        selectMyPage();
        return;
      }
    });
    mypageMenu.add(new Menu("예약 내역") {
      @Override
      public void execute() {
        cafeHandler.listReservation();
      }
    });








    MenuGroup studyMenu = new MenuGroup("모든 스터디"); 
    userMenuGroup.add(studyMenu);

    MenuGroup myStudyMenu = new MenuGroup("내 스터디"); 
    userMenuGroup.add(myStudyMenu);
    // 캘린더, 투두, 자유 게시판, 구성원, 화상채팅

    MenuGroup cafeMenu = new MenuGroup("스터디 장소"); 
    userMenuGroup.add(cafeMenu);
    // 리뷰, 예약 

    MenuGroup csMenu = new MenuGroup("고객 센터"); 
    userMenuGroup.add(csMenu);
    // 공지사항, 문의게시판    












    return userMenuGroup;
  }

  // 기업
  Menu createCeoMenu(MenuGroup mainMenuGroup) {
    return null;
  }

  private void selectMyPage() {
    System.out.println();
    System.out.println("1. 수정하기");
    System.out.println("2. 문의 내역");
    System.out.println("3. 탈퇴하기");
    System.out.println("4. 뒤로가기");

    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1: memberHandler.update(); break;
      case 2: askBoardHandler.list(); break;
      case 3: memberHandler.delete(); return;
      default : return;
    }
  }















  // 메인의 하위메뉴 시작
  //---------------------------------------------------
  //    MenuGroup joinMenu = new MenuGroup("회원가입");
  //    mainMenuGroup.add(joinMenu);
  //    joinMenu.add(new Menu("개인") {
  //      @Override
  //      public void execute() {
  //        memberHandler.add(); 
  //      }});
  //    joinMenu.add(new Menu("기업") {
  //      @Override
  //      public void execute() {
  //        memberHandler.add(); 
  //      }});
  //    //---------------------------------------------------
  //
  //
  //    //---------------------------------------------------
  //    MenuGroup loginMenu = new MenuGroup("로그인");
  //    mainMenuGroup.add(loginMenu);
  //    loginMenu.add(new Menu("개인") {
  //      @Override
  //      public void execute() {
  //        loginHandler.addLoginPage();
  //      }});
  //
  //    loginMenu.add(new Menu("기업") {
  //      @Override
  //      public void execute() {
  //        loginHandler.addLoginPage(); 
  //      }});
  //    loginMenu.add(new Menu("NAVER로 시작하기") {
  //      @Override
  //      public void execute() {
  //        loginHandler.naverLogin(); 
  //      }});
  //    loginMenu.add(new Menu("KAKAO로 시작하기") {
  //      @Override
  //      public void execute() {
  //        loginHandler.kakaoLogin(); 
  //      }});
  //    loginMenu.add(new Menu("GOOGLE로 시작하기") {
  //      @Override
  //      public void execute() {
  //        loginHandler.googleLogin(); 
  //      }});
  //    loginMenu.add(new Menu("회원 가입") {
  //      @Override
  //      public void execute() {
  //        memberHandler.add();
  //      }});
  //    loginMenu.add(new Menu("ID/PW 찾기") {
  //      @Override
  //      public void execute() {
  //        memberHandler.findEmail();
  //      }});
  //
  //    loginMenu.add(new Menu("로그아웃") {
  //      @Override
  //      public void execute() {
  //        loginHandler.logOut();
  //      }});
  //
  //    //---------------------------------------------------
  //
  //
  //    //---------------------------------------------------
  //    MenuGroup memberMenu = new MenuGroup("마이페이지");
  //    mainMenuGroup.add(memberMenu);
  //    memberMenu.add(new Menu("회원 가입 목록 보기") {
  //      @Override
  //      public void execute() {
  //        memberHandler.list(); 
  //      }});
  //    memberMenu.add(new Menu("내 가입 정보보기") {
  //      @Override
  //      public void execute() {
  //        memberHandler.detail(); 
  //      }});
  //    memberMenu.add(new Menu("개인 정보 수정하기") {
  //      @Override
  //      public void execute() {
  //        memberHandler.update(); 
  //      }});
  //    memberMenu.add(new Menu("회원 탈퇴하기") {
  //      @Override
  //      public void execute() {
  //        memberHandler.delete(); 
  //      }});
  //    //---------------------------------------------------
  //
  //
  //    //---------------------------------------------------
  //    MenuGroup studyMenu = new MenuGroup("모든 스터디");
  //    mainMenuGroup.add(studyMenu);
  //    studyMenu.add(new Menu("내 스터디 등록하기") {
  //      @Override
  //      public void execute() {
  //        newStudyHandler.add(); 
  //      }});
  //    studyMenu.add(new Menu("스터디 목록보기") {
  //      @Override
  //      public void execute() {
  //        newStudyHandler.list(); 
  //      }});
  //    studyMenu.add(new Menu("스터디 상세보기") {
  //      @Override
  //      public void execute() {
  //        newStudyHandler.detail(); 
  //      }});
  //    studyMenu.add(new Menu("내 스터디 변경하기/마이페이지 권한") {
  //      @Override
  //      public void execute() {
  //        newStudyHandler.update(); 
  //      }});
  //    studyMenu.add(new Menu("내 스터디 삭제하기//마이페이지 권한") {
  //      @Override
  //      public void execute() {
  //        newStudyHandler.delete(); 
  //      }});
  //    //---------------------------------------------------
  //
  //
  //    //---------------------------------------------------
  //    MenuGroup myStudyMenu = new MenuGroup("내 스터디");
  //    mainMenuGroup.add(myStudyMenu);
  //    MenuGroup caMenu = new MenuGroup("캘린더");
  //    myStudyMenu.add(caMenu);
  //    caMenu.add(new Menu("일정 등록") {
  //      @Override
  //      public void execute() {
  //        calenderHandler.add(); 
  //      }});
  //    //---------------------------------------------------
  //
  //    // 내스터디메뉴의 하위 메뉴 시작
  //    //---------------------------------------------------
  //    MenuGroup calenderMenu = new MenuGroup("캘린더");
  //    myStudyMenu.add(calenderMenu);
  //    calenderMenu.add(new Menu("일정 등록") {
  //      @Override
  //      public void execute() {
  //        calenderHandler.add(); 
  //      }});
  //    calenderMenu.add(new Menu("일정 목록") {
  //      @Override
  //      public void execute() {
  //        calenderHandler.list(); 
  //      }});
  //    calenderMenu.add(new Menu("일정 상세보기") {
  //      @Override
  //      public void execute() {
  //        calenderHandler.detail(); 
  //      }});
  //    calenderMenu.add(new Menu("일정 변경") {
  //      @Override
  //      public void execute() {
  //        calenderHandler.update(); 
  //      }});
  //    calenderMenu.add(new Menu("일정 삭제") {
  //      @Override
  //      public void execute() {
  //        calenderHandler.delete(); 
  //      }});
  //    //---------------------------------------------------
  //
  //
  //    //---------------------------------------------------
  //    MenuGroup todoMenu = new MenuGroup("To-Do List");
  //    mainMenuGroup.add(todoMenu);
  //    todoMenu.add(new Menu("To-Do List 등록") {
  //      @Override
  //      public void execute() {
  //        toDoHandler.add(); 
  //      }});
  //    todoMenu.add(new Menu("To-Do List 목록") {
  //      @Override
  //      public void execute() {
  //        toDoHandler.list(); 
  //      }});
  //    todoMenu.add(new Menu("To-Do List 상세보기") {
  //      @Override
  //      public void execute() {
  //        toDoHandler.detail(); 
  //      }});
  //    todoMenu.add(new Menu("To-Do List 변경") {
  //      @Override
  //      public void execute() {
  //        toDoHandler.update(); 
  //      }});
  //    todoMenu.add(new Menu("To-Do List 삭제") {
  //      @Override
  //      public void execute() {
  //        toDoHandler.delete(); 
  //      }});
  //    //---------------------------------------------------
  //
  //
  //    //---------------------------------------------------
  //    MenuGroup freeBoardMenu = new MenuGroup("자유게시판");
  //    mainMenuGroup.add(freeBoardMenu);
  //    freeBoardMenu.add(new Menu("자유게시판 게시글 작성") {
  //      @Override
  //      public void execute() {
  //        freeBoardHandler.add(); 
  //      }});
  //    freeBoardMenu.add(new Menu("자유게시판 게시글 목록") {
  //      @Override
  //      public void execute() {
  //        freeBoardHandler.list(); 
  //      }});
  //    freeBoardMenu.add(new Menu("자유게시판 게시글 상세보기") {
  //      @Override
  //      public void execute() {
  //        freeBoardHandler.detail(); 
  //      }});
  //    freeBoardMenu.add(new Menu("자유게시판 게시글 수정") {
  //      @Override
  //      public void execute() {
  //        freeBoardHandler.update(); 
  //      }});
  //    freeBoardMenu.add(new Menu("자유게시판 게시글 삭제") {
  //      @Override
  //      public void execute() {
  //        freeBoardHandler.delete(); 
  //      }});
  //    //---------------------------------------------------
  //
  //
  //    //---------------------------------------------------
  //    MenuGroup cafeMenu = new MenuGroup("스터디 장소");
  //    mainMenuGroup.add(cafeMenu);
  //    cafeMenu.add(new Menu("장소 등록/기업 권한") {
  //      @Override
  //      public void execute() {
  //        cafeHandler.add();
  //      }
  //    });
  //    cafeMenu.add(new Menu("장소 목록") {
  //      @Override
  //      public void execute() {
  //        cafeHandler.list();
  //      }
  //    });
  //    cafeMenu.add(new Menu("장소 검색") {
  //      @Override
  //      public void execute() {
  //        cafeHandler.find();
  //      }
  //    });
  //    cafeMenu.add(new Menu("장소 상세보기") {
  //      @Override
  //      public void execute() {
  //        cafeHandler.detail();
  //      }
  //    });
  //    cafeMenu.add(new Menu("장소 정보 변경하기") {
  //      @Override
  //      public void execute() {
  //        cafeHandler.update();
  //      }
  //    });
  //    cafeMenu.add(new Menu("장소 삭제하기") {
  //      @Override
  //      public void execute() {
  //        cafeHandler.delete();
  //      }
  //    });
  //    //    cafeMenu.add(new Menu("장소 리뷰등록") {
  //    //      @Override
  //    //      public void execute() {
  //    //        cafeHandler.addReview();
  //    //        //loginHandler.addLoginPage();
  //    //      }
  //    //    });
  //    cafeMenu.add(new Menu("장소 예약 내역 보기") {
  //      @Override
  //      public void execute() {
  //        cafeHandler.listReservation();
  //      }
  //    });
  //    //---------------------------------------------------
  //
  //
  //    //---------------------------------------------------
  //    MenuGroup managerMenu = new MenuGroup("고객센터/관리자");
  //    mainMenuGroup.add(managerMenu);
  //    managerMenu.add(new Menu("공지사항 등록하기") {
  //      @Override
  //      public void execute() {
  //        managerHandler.add(); 
  //      }});
  //    managerMenu.add(new Menu("공지사항 수정하기") {
  //      @Override
  //      public void execute() {
  //        managerHandler.update(); 
  //      }});
  //    managerMenu.add(new Menu("공지사항 삭제하기") {
  //      @Override
  //      public void execute() {
  //        managerHandler.delete(); 
  //      }});
  //    //---------------------------------------------------
  //
  //
  //    //---------------------------------------------------
  //    MenuGroup noticeMenu = new MenuGroup("고객센터/회원");
  //    mainMenuGroup.add(noticeMenu);
  //    noticeMenu.add(new Menu("공지사항 목록") {
  //      @Override
  //      public void execute() {
  //        noticeboardHandler.list(); 
  //      }});
  //    noticeMenu.add(new Menu("공지사항 상세보기") {
  //      @Override
  //      public void execute() {
  //        noticeboardHandler.detail(); 
  //      }});
  //    //---------------------------------------------------
  //
  //
  //    //---------------------------------------------------
  //    MenuGroup askMenu = new MenuGroup("문의사항/회원");
  //    mainMenuGroup.add(askMenu);
  //    askMenu.add(new Menu("문의사항 등록") {
  //      @Override
  //      public void execute() {
  //        askBoardHandler.add(); 
  //      }});
  //    askMenu.add(new Menu("문의사항 목록") {
  //      @Override
  //      public void execute() {
  //        askBoardHandler.list(); 
  //      }});
  //    askMenu.add(new Menu("문의사항 상세보기") {
  //      @Override
  //      public void execute() {
  //        askBoardHandler.detail(); 
  //      }});
  //    askMenu.add(new Menu("문의사항 변경") {
  //      @Override
  //      public void execute() {
  //        askBoardHandler.update(); 
  //      }});
  //    askMenu.add(new Menu("문의사항 삭제") {
  //      @Override
  //      public void execute() {
  //        askBoardHandler.delete(); 
  //      }});
  //---------------------------------------------------


  // 로그인 호출하는거 새로운 방법?
  //  void login(MenuGroup mainMenuGroup, MenuGroup joinMenu) {
  //    joinMenu.add(new Menu("개인") {
  //      @Override
  //      public void execute() {
  //        memberHandler.add(); 
  //      }});
  //    joinMenu.add(new Menu("기업") {
  //      @Override
  //      public void execute() {
  //        memberHandler.add(); 
  //      }});
  //  }
}

