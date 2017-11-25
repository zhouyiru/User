create table usertable(
		userid number(30) primary key,
		username varchar2(200) not null,
		userpwd varchar2(200) not null,
		userage number(10) not null,
		usergender varchar2(10) not null,
		useredu varchar2(20) not null,
		userphone number(38) not null,
		useraddress varchar2(200) not null
		userimg varchar2(20),
		status number(10),
		validatecode varchar2(50),
		email varchar2(30)
		);


drop table usertable;
drop sequence userseq;
create sequence userseq start with 1;
		commit;
create table message(
        no number(20),
        title varchar2(100),
        name varchar2(20),
        content varchar2(200),
        mdate date
        );
create sequence mseq start with 1;
    commit;
             
  create table discuss(
       no number(20),
       id number(20),
       name varchar2(20),
       content varchar2(200),
       mdate date
  );

