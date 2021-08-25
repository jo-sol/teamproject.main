# teamproject.main
<<<<<<< HEAD
=======
하하하핳하
왜 안되지


# 액터
- ## <주액터>
    - 비회원
    - 회원
        - 팀원
            - 팀장
    - 관리자

- ## <보조액터>
    - 다음주소검색시스템
    - 페이스북로그인
    - 카카오로그인


# 유스케이스
- ## <비회원>
    - 스터디 목록 조회
    - 스터디 장소 조회
    - 공지사항 조회 및 상세보기
    - 회원 가입
     - (사이트 소개 : 사이트 소개를 통해 해당 사이트를 체험 할 수 있다.)


- ## <회원>
    - 스터디
        - 스터디 목록 상세보기 
    
    - 스터디 장소
        - 스터디 장소 상세보기
        - 스터디 장소 후기 조회 및 상세보기

    - 문의사항
        - 문의사항 등록
        - 문의사항 수정
        - 문의사항 삭제
        - 문의사항 조회 및 상세보기

    - 회원탈퇴   
    - 로그인
    - 로그아웃

    - ## 회원 - <사장>
        - 내 가게 작업 관리
            - 가게 등록 : 가게 등록
            - 가게 수정 : 가게 정보 수정
            - 가게 삭제 : 가게 삭제
            - 가게 조회 : 등록한 가게 목록 조회 및 상세 조회
            
        - 예약 관리
            - 예약 승인,거절
            - 예약 목록 조회

        - 장소 후기 게시글
            - 댓글 등록
            - 댓글 수정
            - 댓글 삭제

    - ## 회원 - <개인>
        - 스터디
            - 스터디 등록
            - 스터디 참여 신청
            - 내 스터디 조회 (빈칸)

        - 스터디 장소
            - 장소 예약 신청
            - 장소 예약 수정
            - 장소 예약 취소

        - 장소 후기 게시글
            - 후기 게시글 조회
            - 후기 게시글 등록
            - 후기 게시글 수정
            - 후기 게시글 삭제

        - ## 회원 - 개인 - <구성원>
            - 스터디
                - 작업 등록 : 캘린더, 게시판, 모임 등록
                - 작업 수정 : 캘린더, 게시판 수정
                - 작업 삭제 : 캘린더, 게시판, 모임 삭제
                - 작업 조회 : 캘린더, 게시판, 스터디 내 모임 조회
                - 스터디 탈퇴 : 참여 중인 스터디에서 탈퇴 (팝업창으로 한번 더 확인)

            - 구성원 관리
                - 구성원 내보내기 투표 신청
                - 구성원 내보내기 투표 참여
                (- 구성원 내보내기 수정,삭제, 취소?)

            - ## 회원 - 개인 - 구성원 - <조장>
                - 스터디 
                    - 스터디 정보 수정 : 생성한 스터디 제목, 내용(소개글), 카테고리 변경
                    - 스터디 삭제 : 자신이 생성한 스터디를 삭제 할 수 있다.
               
                - 구성원 관리
                    - 구성원 참여 승인 : 스터디 참여 신청을 확인하고 수락 할 수 있다.
                
                - 조장 관리
                    - 조장 권한 위임

- ## <관리자>
    - 회원
        - 회원 삭제
        - 회원 조회
        - 사장 회원관리
            - 가게 승인

    - 공지 게시글 관리
        - 공지 게시글 등록
        - 공지 게시글 수정
        - 공지 게시글 삭제
        - 공지 게시글 조회

    - 문의 게시글 관리
        - 문의 게시글 등록
        - 문의 게시글 수정
        - 문의 게시글 삭제
        - 문의 게시글 조회

    - 장소 후기 게시글 관리
        - 후기 게시글 조회
        - 후기 게시글 삭제

    - 스터디
        - 스터디 조회
        - 스터디 삭제






















# 회의록?
- 프로젝트 주제 선정
  - 1차: 두 개 주제를 선정해서 소개
	- 프로젝트 설명(PPT)
	- 프로젝트명
	- 현황 및 문제점: 현재 해당 업무나 일을 처리하는 상황과 그에 따른 불편한 점
	- 해결방안 및 이점: 어떻게 해결할 것인지에 대한 목표와 그에 따른 사용자의 이점
	- UI 프로토타입 작성
	- 무료 웹 서비스를 이용하여 UI 프로토타입 작성
	- 프로젝트로 만들려는 서비스를 분명하게 공유하기 위함
  - 2차: 한 개 주제를 선정해서 상세하게 소개
	- 프로젝트 설명 개정
	- 기존 설명 보완
	- UI 프로토타입 상세화 
	- 기존 UI 프로토타입 보완
- 요구사항 정의
  - 액터 식별 및 정의
  - 유스케이스 식별 및 정의
  - 유스케이스 명세서 작성
- DB 모델링
  - ER 다이어그램 작성
  - 데이터베이스 생성 및 테이블 생성
- 구현 
  - 애플리케이션 구현
- 발표


<비트캠프 자바 207기 팀프로젝트>
- 1일차
- 일정 : 2021.08.10.화
- 팀원 : 2팀(A반) ー김은채,전예린,송다혜,조솔,우형민
- 목표 : 자바,HTML,CSS,Spring Framework 등을 활용한 웹사이트 및 애플리케이션 구현
- 내용 : 프로젝트 팀 결정 및 A/B 반 결정


- 2일차
- 일정 : 2021.08.11.수
- 목표 : 두 개 주제를 선정해서 소개
- 내용 :
1. 진행할 프로젝트에 대해 팀원들과 회의를 진행하였다
2. 뷰티, 동물(포인핸드), 요리, 사내프로그램, 도서관리 프로그램, 일정관리 프로그램 등 다양한 의견이 나왔고 데이터가 필요한 정보제공 서비스 위주의 사이트와 데이터가 많이 필요하지 않은 관리서비스 위주의 사이트로 구분하여 선정기준을 세웠다
3. 결과적으로 요리, 일정관리 2가지 주제 결정하였다
4. 요리 사이트 현황과 참고 사이트를 검색해보고 불편한 점과 문제점 등 분석하였다
이에 따른 해결방안과 우리가 제공하고 싶은 기능들에 대해 아이디어를 제안하고 구현할 방향성을 설정하였다
5. 사이트맵을 트리구조로 직접 적어보면서 구현해야할 화면페이지를 결정하고 정리하였다
6. 필요한 페이지 각각에 들어갈 기능과 레이아웃에 대해 의견을 나누면 직접 수기로 스케치하였다
7. 일정관리 사이트에 대한 현황, 참고 사이트, 문제점 등에 대해 회의하였다
8. 마찬가지로 이에 따른 해결방안과 우리가 제공하고 싶은 기능들에 대해 아이디어를 제안하고 구현할 방향성을 설정하였다.
9. 사이트맵을 트리구조로 직접 적어보면서 구현해야할 화면페이지를 결정하고 정리하였다
10. 조원들과 다같이 카카오오븐을 직접 사용해 보며 사용법을 익히고 스케치한 내용을 바탕으로 기본틀을 만들었다


- 3일차
- 일정 : 2021.08.12.목
- 목표 : 프로젝트 발표 PPT 작성, 카카오 오븐 사용해서 UI 프로토타입 작성
- 역할 : PPT – 우형민,김은채 / UI프로토타입 – 조솔,전예린,송다혜
- 내용 :
1. 어제 회의한 내용을 바탕으로 ppt와 ui프로토타입을 작성하였다
2. 역할 분담을 통해 조원 2명은 회의 내용 정리와 발표 자료를 준비하고 ppt를 만들었다
3. 조원 3명은 리서치와 자료검색을 하면서 레이아웃을 새로 짜고 카카오 오븐을 사용해서 어제 만든 UI프로토타입을 제작 및 수정하였다
4. 각자 맡은 부분을 서로 설명하고 공유하였다


- 4일차
- 일정 : 2021.08.12.목
- 목표 : 프로젝트 발표 PPT 작성, 카카오 오븐 사용해서 UI 프로토타입 작성
- 내용 :
1. 어제 진행한 스터디를 이어서 완성시키기

- 5일차
- 일정 : 2021.08.13.금
>>>>>>> 9ba6c9696f2079ce0c4a84c03031bca1a4b908a3
