
--스터디 조장, 구성원 테스트값 보면서 넣어야함

insert into study_board (title, content, view_ct, created_dt, member_no, study_no)
values ('제목1', '내용입니다.', '2', '2021-10-5', '1' , '1');

insert into study_board (title, content, view_ct, created_dt, member_no, study_no)
values ('제목2', '내용2입니다.', '0', '2021-10-10', '3' , '1');

insert into study_board (title, content, view_ct, created_dt, member_no, study_no)
values ('제목3', '내용3입니다.', '0', '2021-10-20', '5' , '1');

insert into study_board (title, content, view_ct, created_dt, member_no, study_no)
values ('제목1', '내용1입니다.', '0', '2021-09-01', '2' , '1');

insert into study_board (title, content, view_ct, created_dt, member_no, study_no)
values ('제목4', '내용4입니다.', '0', '2021-09-01', '4' , '1');

insert into study_board (title, content, view_ct, created_dt, member_no, study_no)
values ('제목6', '내용6입니다.', '0', '2021-09-01', '6' , '1');

-- 목록
/*
select
  s.study_no study_no,
  sb.st_board_no,
  sb.title,
  sb.content,
  sb.view_ct,
  sb.created_dt,
  m.member_no per_no,
  m.nickname nickname,
  m.status member_status,
  sbf.file_no file_no,
  sbf.name atcFileName,
  (select count(*) from study_board_file sbf where sb.st_board_no=sbf.st_board_no) count_file,
  (select count(*) from study_board_comment sbc where sb.st_board_no=sbc.st_board_no) count_comment,
  (select count(*) from study_board_like sbl where sb.st_board_no=sbl.st_board_no) count_like
 from study_board sb
   left outer join study s on sb.study_no=s.study_no
   left outer join member m on sb.member_no=m.member_no
   left outer join study_board_file sbf on sb.st_board_no=sbf.st_board_no
  where
   sb.study_no=1
<<<<<<< HEAD
   order by s.study_no
   
   -- updateFile
   update study_board_file sbf set
      name = #{fileName.atcFileName}
    where
      sbf.st_board_no=#{freeBoardNo} and sbf.file_no=#{atcFileNo}
=======
   order by sb.st_board_no
>>>>>>> a708787c8c160da7b71b0288f95277ff2a2ffc02
 */
