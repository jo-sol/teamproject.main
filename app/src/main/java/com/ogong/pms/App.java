package com.ogong.pms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import com.ogong.menu.Menu;
import com.ogong.menu.MenuGroup;
import com.ogong.pms.domain.Admin;
import com.ogong.pms.domain.AdminNotice;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.Calender;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.domain.Comment;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.domain.ToDo;
import com.ogong.pms.handler.AdminCafeControlHandler;
import com.ogong.pms.handler.AdminCafeReviewListControlHandler;
import com.ogong.pms.handler.AdminInfoHandler;
import com.ogong.pms.handler.AdminMemberDeleteHandler;
import com.ogong.pms.handler.AdminMemberDetailHandler;
import com.ogong.pms.handler.AdminMemberUpdateHandler;
import com.ogong.pms.handler.AdminNoticeAddHandler;
import com.ogong.pms.handler.AdminNoticeDeleteHandler;
import com.ogong.pms.handler.AdminNoticeDetailHandler;
import com.ogong.pms.handler.AdminNoticeListHandler;
import com.ogong.pms.handler.AdminNoticeUpdateHandler;
import com.ogong.pms.handler.AdminStudyDeleteHandler;
import com.ogong.pms.handler.AskBoardAddHandler;
import com.ogong.pms.handler.AskBoardDeleteHandler;
import com.ogong.pms.handler.AskBoardDetailHandler;
import com.ogong.pms.handler.AskBoardListHandler;
import com.ogong.pms.handler.AskBoardMyListHandler;
import com.ogong.pms.handler.AskBoardUpdateHandler;
import com.ogong.pms.handler.AuthAdminLoginHandler;
import com.ogong.pms.handler.AuthAdminLogoutHandler;
import com.ogong.pms.handler.AuthCeoMemberLoginHandler;
import com.ogong.pms.handler.AuthCeoMemberLogoutHandler;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.AuthPerMemberLogoutHandler;
import com.ogong.pms.handler.CafeAddHandler;
import com.ogong.pms.handler.CafeDeleteHandler;
import com.ogong.pms.handler.CafeDetailHandler;
import com.ogong.pms.handler.CafeListHandler;
import com.ogong.pms.handler.CafeMyReservationListHandler;
import com.ogong.pms.handler.CafeMyReviewListHandler;
import com.ogong.pms.handler.CafeSearchHandler;
import com.ogong.pms.handler.CafeUpdateHandler;
import com.ogong.pms.handler.CalenderAddHandler;
import com.ogong.pms.handler.CalenderDeleteHandler;
import com.ogong.pms.handler.CalenderDetailHandler;
import com.ogong.pms.handler.CalenderListHandler;
import com.ogong.pms.handler.CalenderUpdateHandler;
import com.ogong.pms.handler.CeoAddHandler;
import com.ogong.pms.handler.CeoFindIdPwHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.FreeBoardAddHandler;
import com.ogong.pms.handler.FreeBoardDeleteHandler;
import com.ogong.pms.handler.FreeBoardDetailHandler;
import com.ogong.pms.handler.FreeBoardListHandler;
import com.ogong.pms.handler.FreeBoardUpdateHandler;
import com.ogong.pms.handler.MemberAddHandler;
import com.ogong.pms.handler.MemberDeleteHandler;
import com.ogong.pms.handler.MemberDetailHandler;
import com.ogong.pms.handler.MemberFindIdPwHandler;
import com.ogong.pms.handler.MemberListHandler;
import com.ogong.pms.handler.MemberUpdateHandler;
import com.ogong.pms.handler.MyStudyDeleteHandler;
import com.ogong.pms.handler.MyStudyDetailHandler;
import com.ogong.pms.handler.MyStudyListHandler;
import com.ogong.pms.handler.PromptCeoMember;
import com.ogong.pms.handler.PromptPerMember;
import com.ogong.pms.handler.StudyAddHandler;
import com.ogong.pms.handler.StudyListHandler;
import com.ogong.pms.handler.StudyUpdateHandler;
import com.ogong.pms.handler.ToDoAddHandler;
import com.ogong.pms.handler.ToDoDeleteHandler;
import com.ogong.pms.handler.ToDoDetailHandler;
import com.ogong.pms.handler.ToDoListHandler;
import com.ogong.pms.handler.ToDoUpdateHandler;
import com.ogong.util.Prompt;

public class App {
  List<Study> studyList = new LinkedList<>();
  List<Member> memberList = new LinkedList<>();
  List<AdminNotice> adminNoticeList = new ArrayList<>();
  List<AskBoard> askBoardList = new ArrayList<>();
  List<Cafe> cafeList = new ArrayList<>();
  List<CafeReview> cafeReview = new ArrayList<>();
  List<CafeReservation> reserList = new ArrayList<>();
  List<ToDo> toDoList = new ArrayList<>();
  List<FreeBoard> freeBoardList = new ArrayList<>();
  List<Calender> calenderList = new ArrayList<>();
  List<Admin> adminList = new ArrayList<>();
  List<CeoMember> ceoMemberList = new ArrayList<>();
  List<Comment> commentList = new ArrayList<>();

  // 해시맵 추가(0904)
  HashMap<String, Command> commandMap = new HashMap<>();

  PromptPerMember promptPerMember = new PromptPerMember(memberList); 
  PromptCeoMember promptCeoMember = new PromptCeoMember(ceoMemberList);

  //CeoMemberHandler ceoMemberHandler = new CeoMemberHandler(ceoMemberList);
  //MyStudyHandler myStudyHandler = new MyStudyHandler(studyList, studyHandler);

  class MenuItem extends Menu {
    String menuId;

    public MenuItem(String title, String menuId) {
      this(title, ENABLE_ALL, menuId);
    }

    public MenuItem(String title, int enableState, String menuId) {
      super(title, enableState);
      this.menuId = menuId;
    }

    @Override
    public void execute() {
      Command command = commandMap.get(menuId);
      command.execute();
    }
  }

  public static void main(String[] args) {
    App app = new App(); 
    app.welcomeservice();
  }

  public App() {
    commandMap.put("/calender/add", new CalenderAddHandler(calenderList));
    commandMap.put("/calender/list", new CalenderListHandler(calenderList, commandMap));
    commandMap.put("/calender/detail", new CalenderDetailHandler(calenderList, commandMap));
    commandMap.put("/calender/update", new CalenderUpdateHandler(calenderList));
    commandMap.put("/calender/delete", new CalenderDeleteHandler(calenderList));

    commandMap.put("/member/add", new MemberAddHandler(memberList));
    commandMap.put("/member/list", new MemberListHandler(memberList, commandMap));
    commandMap.put("/member/detail", new MemberDetailHandler(memberList, commandMap));
    commandMap.put("/member/update", new MemberUpdateHandler(memberList));
    commandMap.put("/member/delete", new MemberDeleteHandler(memberList, promptPerMember));

    commandMap.put("/ceoMember/login", new AuthCeoMemberLoginHandler(ceoMemberList));
    commandMap.put("/ceoMember/logout", new AuthCeoMemberLogoutHandler());
    commandMap.put("/ceoMember/findIdPw", new CeoFindIdPwHandler(ceoMemberList, promptCeoMember));
    commandMap.put("/ceoMember/add", new CeoAddHandler(ceoMemberList));

    commandMap.put("/adminMember/detail", new AdminMemberDetailHandler(memberList, promptPerMember));
    commandMap.put("/adminMember/update", new AdminMemberUpdateHandler(memberList, promptPerMember));
    commandMap.put("/adminMember/delete", new AdminMemberDeleteHandler(memberList, promptPerMember));

    commandMap.put("/freeBoard/add", new FreeBoardAddHandler(freeBoardList, memberList, commentList, commandMap));
    commandMap.put("/freeBoard/list", new FreeBoardListHandler(freeBoardList, commentList, commandMap));
    commandMap.put("/freeBoard/detail", new FreeBoardDetailHandler(freeBoardList, commentList, commandMap));
    commandMap.put("/freeBoard/update", new FreeBoardUpdateHandler(freeBoardList, commentList, commandMap));
    commandMap.put("/freeBoard/delete", new FreeBoardDeleteHandler(freeBoardList, commentList, commandMap));

    commandMap.put("/toDo/add", new ToDoAddHandler(toDoList));
    commandMap.put("/toDo/list", new ToDoListHandler(toDoList));
    commandMap.put("/toDo/detail", new ToDoUpdateHandler(toDoList));
    commandMap.put("/toDo/update", new ToDoDetailHandler(toDoList));
    commandMap.put("/toDo/delete", new ToDoDeleteHandler(toDoList));

    commandMap.put("/askBoard/add",  new AskBoardAddHandler(askBoardList, memberList, commentList));
    commandMap.put("/askBoard/list", new AskBoardListHandler(askBoardList, commentList));
    commandMap.put("/askBoard/detail", new AskBoardDetailHandler(askBoardList, commentList, adminList));
    commandMap.put("/askBoard/update", new AskBoardUpdateHandler(askBoardList, commentList));
    commandMap.put("/askBoard/delete", new AskBoardDeleteHandler(askBoardList, commentList));
    commandMap.put("/askBoard/myList", new AskBoardMyListHandler(askBoardList, commentList));

    commandMap.put("/cafe/add", new CafeAddHandler(cafeList, cafeReview, reserList, ceoMemberList));
    commandMap.put("/cafe/list", new CafeListHandler(cafeList, cafeReview, reserList, commandMap));
    commandMap.put("/cafe/detail", new CafeDetailHandler(cafeList, cafeReview, reserList));
    commandMap.put("/cafe/update", new CafeUpdateHandler(cafeList, cafeReview, reserList));
    commandMap.put("/cafe/delete", new CafeDeleteHandler(cafeList, cafeReview, reserList));
    commandMap.put("/cafe/search", new CafeSearchHandler(cafeList, cafeReview, reserList, commandMap));
    commandMap.put("/cafe/reservationList", new CafeMyReservationListHandler(cafeList, cafeReview, reserList, promptPerMember));
    commandMap.put("/cafe/myReviewList", new CafeMyReviewListHandler(cafeList, cafeReview, reserList));

    commandMap.put("/cafe/control", new AdminCafeControlHandler(cafeList, cafeReview, reserList));
    commandMap.put("/cafe/reviewList", new AdminCafeReviewListControlHandler(cafeList, cafeReview, reserList)); 

    commandMap.put("/adminNotice/add", new AdminNoticeAddHandler(adminNoticeList));
    commandMap.put("/adminNotice/list", new AdminNoticeListHandler(adminNoticeList));
    commandMap.put("/adminNotice/update", new AdminNoticeUpdateHandler(adminNoticeList));
    commandMap.put("/adminNotice/detail", new AdminNoticeDetailHandler(adminNoticeList));
    commandMap.put("/adminNotice/delete", new AdminNoticeDeleteHandler(adminNoticeList));

    commandMap.put("/admin/login", new AuthAdminLoginHandler(adminList));
    commandMap.put("/admin/logout", new AuthAdminLogoutHandler());

    commandMap.put("/member/login", new AuthPerMemberLoginHandler(promptPerMember, memberList));
    commandMap.put("/member/findIdPw", new MemberFindIdPwHandler(promptPerMember));
    commandMap.put("/member/logout", new AuthPerMemberLogoutHandler());

    commandMap.put("/admin/info", new AdminInfoHandler(adminList));

    commandMap.put("/study/add", new StudyAddHandler(studyList, promptPerMember));
    commandMap.put("/study/list", new StudyListHandler(studyList));
    commandMap.put("/study/update", new StudyUpdateHandler(studyList));
    commandMap.put("/study/delete", new AdminStudyDeleteHandler(studyList));

    commandMap.put("/myStudy/delete", new MyStudyDeleteHandler(studyList));
    commandMap.put("/myStudy/list", new MyStudyListHandler(studyList, commandMap));
    commandMap.put("/myStudy/detail", new MyStudyDetailHandler(studyList, commandMap));
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


  //--------------------------------------------------------
  Menu createMenu() {

    MenuGroup mainMenuGroup = new MenuGroup("메인");
    mainMenuGroup.setPrevMenuTitle("종료");

    mainMenuGroup.add(createAdminMenu());
    mainMenuGroup.add(createMemberMenu());
    mainMenuGroup.add(createCeoMenu());

    return mainMenuGroup;
  }

  // -----------------------------------------------------------------------------------------------
  // 관리자 메인
  Menu createAdminMenu() {
    MenuGroup adminMenuGroup = new MenuGroup("관리자");

    adminMenuGroup.add(new MenuItem("로그인", Menu.ENABLE_ADMINLOGOUT, "/admin/login"));
    adminMenuGroup.add(new MenuItem("로그아웃", Menu.ENABLE_ADMINLOGIN, "/admin/logout"));
    adminMenuGroup.add(new MenuItem("마이 페이지", Menu.ENABLE_ADMINLOGIN, "/admin/info"));

    adminMenuGroup.add(createControlMemberMenu());  // 회원 관리
    adminMenuGroup.add(createControlStudyMenu());   // 스터디 관리
    adminMenuGroup.add(createControlReviewMenu());  // 장소 관리
    adminMenuGroup.add(createAdminCSMenu());        // 고객센터 관리

    return adminMenuGroup;
  }

  // 관리자 하위 메뉴2 - 회원 관리
  private Menu createControlMemberMenu() {
    MenuGroup adminUserMenu = new MenuGroup("회원 관리", Menu.ENABLE_ADMINLOGIN); 

    adminUserMenu.add(new MenuItem("개인 회원 조회", "/member/list"));
    adminUserMenu.add(new MenuItem("사장 회원 조회", "ceo 구현 전"));

    return adminUserMenu;
  }

  // 관리자 하위 메뉴3 - 스터디 관리
  private Menu createControlStudyMenu() {
    MenuGroup adminStudyMenu = new MenuGroup("스터디 관리"); 

    adminStudyMenu.add(new MenuItem("목록","/study/list"));
    adminStudyMenu.add(new MenuItem("삭제","/study/delete"));
    return adminStudyMenu;
  }

  // 관리자 하위 메뉴4 - 장소 후기 관리
  private Menu createControlReviewMenu() {
    MenuGroup adminCafeReviewMenu = new MenuGroup("장소 관리"); 

    adminCafeReviewMenu.add(new MenuItem("장소 게시글 관리","/cafe/control"));
    adminCafeReviewMenu.add(new MenuItem("장소 리뷰 관리","/cafe/reviewList")); 

    return adminCafeReviewMenu;
  }

  //관리자 하위 메뉴5 - 고객센터 관리
  private Menu createAdminCSMenu() {
    MenuGroup csMenu = new MenuGroup("고객센터 관리");

    csMenu.add(createAdminNoticeMenu());
    csMenu.add(createAdminAskMenu());

    return csMenu;
  }

  // 5-1
  private Menu createAdminNoticeMenu() {
    MenuGroup adminNoticeMenu = new MenuGroup("공지사항"); 

    adminNoticeMenu.add(new MenuItem("등록", "/adminNotice/add"));
    adminNoticeMenu.add(new MenuItem("목록", "/adminNotice/list"));
    adminNoticeMenu.add(new MenuItem("상세", "/adminNotice/detail"));
    adminNoticeMenu.add(new MenuItem("수정", "/adminNotice/update"));
    adminNoticeMenu.add(new MenuItem("삭제", "/adminNotice/delete"));

    return adminNoticeMenu;
  }

  // 5-2
  private Menu createAdminAskMenu() {
    MenuGroup adminaskMenu = new MenuGroup("문의사항");

    adminaskMenu.add(new MenuItem("목록", "/askBoard/list"));
    adminaskMenu.add(new MenuItem("상세", "/askBoard/detail"));
    adminaskMenu.add(new MenuItem("삭제", "/askBoard/delete"));

    return adminaskMenu;
  }

  // -----------------------------------------------------------------------------------------------
  // 개인 회원 메인
  Menu createMemberMenu() {
    MenuGroup userMenuGroup = new MenuGroup("개인"); 

    userMenuGroup.add(new MenuItem("회원가입", Menu.ENABLE_LOGOUT, "/member/add"));
    userMenuGroup.add(new MenuItem("로그아웃", Menu.ENABLE_LOGIN, "/member/logout"));
    userMenuGroup.add(new MenuItem("로그인", Menu.ENABLE_LOGOUT, "/member/login"));
    userMenuGroup.add(new MenuItem("ID/PW 찾기", Menu.ENABLE_LOGOUT, "/member/findIdPw"));

    userMenuGroup.add(createMyPageMenu());      // 마이페이지
    userMenuGroup.add(createStudyMenu());       // 스터디 찾기
    userMenuGroup.add(createMystudyMenu());     // 내 스터디
    userMenuGroup.add(createCafeMenu());        // 장소 예약하기
    userMenuGroup.add(createCSMenu());          // 고객센터

    return userMenuGroup;
  }

  //------------------------------------------------------------------------------------

  // 개인 하위 메뉴2 - 마이페이지 (로그인 했을때)
  private Menu createMyPageMenu() {
    MenuGroup myPageMenu = new MenuGroup("마이 페이지", Menu.ENABLE_LOGIN); 

    myPageMenu.add(new MenuItem("개인정보", "/member/detail"));
    myPageMenu.add(new MenuItem("문의내역", "/askBoard/myList"));
    myPageMenu.add(new MenuItem("예약내역", "/cafe/reservationList"));
    myPageMenu.add(new MenuItem("후기내역", "/cafe/myReviewList"));
    myPageMenu.add(new MenuItem("탈퇴하기", "/member/delete"));
    return myPageMenu;
  }

  //개인 하위 메뉴3 - 모든스터디
  // <구현안됨>
  // 이름으로 스터디 검색x
  // 지역으로 선택할지, 대면/비대면 선택할지, 인원수로 선택할지 필터검색x
  private Menu createStudyMenu() {
    MenuGroup allStudyMenu = new MenuGroup("스터디 찾기"); 

    allStudyMenu.add(new MenuItem("등록", Menu.ENABLE_LOGIN, "/study/add"));
    allStudyMenu.add(new MenuItem("목록","/study/list"));
    allStudyMenu.add(new MenuItem("변경", Menu.ENABLE_LOGIN, "/study/update"));

    return allStudyMenu; 
  }

  //개인 하위 메뉴4 - 내 스터디
  // <구현안됨>
  // detail 에서 댓글 수정기능x
  private Menu createMystudyMenu() {
    MenuGroup myStudyMenu = new MenuGroup("내 스터디", Menu.ENABLE_LOGIN); 
    // <구현안됨>
    // 내 스터디 하위 메뉴 1 - 구성원
    // 내 스터디 하위 메뉴 3 - 투두리스트
    // 내 스터디 하위 메뉴 5 - 화상미팅
    myStudyMenu.add(new MenuItem("내 스터디 목록", "/myStudy/list"));
    return myStudyMenu;
  }

  //개인 하위 메뉴5 - 스터디 장소
  private Menu createCafeMenu() {
    MenuGroup cafeMenu = new MenuGroup("장소 예약"); 

    cafeMenu.add(new MenuItem("등록", "/cafe/add")); // 기업권한
    cafeMenu.add(new MenuItem("목록", "/cafe/list"));
    //cafeMenu.add(new MenuItem("장소 검색", "/cafe/search"));
    //cafeMenu.add(new MenuItem("장소 상세", "/cafe/detail"));
    cafeMenu.add(new MenuItem("수정", "/cafe/update"));
    cafeMenu.add(new MenuItem("삭제", "/cafe/delete"));

    return cafeMenu;
  }

  //개인 하위 메뉴6 - 고객센터
  private Menu createCSMenu() {
    MenuGroup memberCSMenu = new MenuGroup("고객센터");

    memberCSMenu.add(createNoticeMenu());
    memberCSMenu.add(createAskBoardMenu());

    return memberCSMenu;
  }

  // 6-1
  private Menu createNoticeMenu() {
    MenuGroup noticeMenu = new MenuGroup("공지사항"); 

    noticeMenu.add(new MenuItem("목록", "/adminNotice/list"));
    noticeMenu.add(new MenuItem("상세", "/adminNotice/detail"));

    return noticeMenu;
  }

  // 6-2
  // 문의사항 상세보기 (댓글 목록 조회만) >> 회원 권한
  private Menu createAskBoardMenu() {
    MenuGroup askBoardMenu = new MenuGroup("문의사항");

    askBoardMenu.add(new MenuItem("등록", "/askBoard/add"));
    askBoardMenu.add(new MenuItem("목록", "/askBoard/list"));
    askBoardMenu.add(new MenuItem("상세", "/askBoard/detail"));
    askBoardMenu.add(new MenuItem("변경", "/askBoard/update"));
    askBoardMenu.add(new MenuItem("삭제", "/askBoard/delete"));

    return askBoardMenu;
  }

  // 기업
  Menu createCeoMenu() {
    MenuGroup ceoMemberMenuGroup = new MenuGroup("기업");

    ceoMemberMenuGroup.add(new MenuItem("회원가입", Menu.ENABLE_CEOLOGOUT, "/ceoMember/add"));
    ceoMemberMenuGroup.add(new MenuItem("로그인", Menu.ENABLE_CEOLOGOUT, "/ceoMember/login"));
    ceoMemberMenuGroup.add(new MenuItem("ID/PW 찾기", Menu.ENABLE_CEOLOGOUT, "/ceoMember/findIdPw"));
    ceoMemberMenuGroup.add(new MenuItem("로그아웃", Menu.ENABLE_CEOLOGIN, "/ceoMember/logout"));

    return ceoMemberMenuGroup;
  }
}