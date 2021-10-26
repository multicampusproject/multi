-- SQLINES LICENSE FOR EVALUATION USE ONLY
create table multiuser(
  member_email varchar(100) not null primary key,
  member_id varchar(100) unique not null,
  member_name varchar(30) not null,
  member_state int default 0
);
select * from multiuser;
-- SQLINES LICENSE FOR EVALUATION USE ONLY
create table multimusic(
    music_num int primary key,
    music_artist varchar(100) not null,
    music_emotion varchar(100) not null,
    music_copyright int not null,
    music_age int not null,
    music_gender varchar(30) not null,
    music_title varchar(1000) not null,
    music_url varchar(3000) not null
);
  
  
-- SQLINES LICENSE FOR EVALUATION USE ONLY
create table multiimage(
    image_name varchar(100) not null primary key,
    image_age int not null,
    image_gender varchar(100) not null,
    image_emotion varchar(100) not null,
    member_email varchar(100) not null  
);
ALTER TABLE multiimage 
ADD FOREIGN KEY (member_email) 
REFERENCES multiuser (member_email)
ON DELETE CASCADE;

-- SQLINES LICENSE FOR EVALUATION USE ONLY
create table multihistory(
    recommend_code int not null auto_increment primary key,
    image_name varchar(100) not null,
    music_num int not null,
    member_email varchar(100) not null
);

ALTER TABLE multihistory 
ADD FOREIGN KEY (image_name) 
REFERENCES multiimage (image_name)
ON DELETE CASCADE;

ALTER TABLE multihistory 
ADD FOREIGN KEY (music_num) 
REFERENCES multimusic (music_num)
ON DELETE CASCADE;

ALTER TABLE multihistory 
ADD FOREIGN KEY (member_email) 
REFERENCES multiuser (member_email)
ON DELETE CASCADE;

-- SQLINES LICENSE FOR EVALUATION USE ONLY
create table multinotice(
    notice_code int not null auto_increment primary key,
    notice_subject varchar(100) not null,
    notice_content varchar(3000) not null,
    notice_regdate datetime default CURRENT_TIMESTAMP,
    member_email varchar(100) not null
);

ALTER TABLE multinotice 
ADD FOREIGN KEY (member_email) 
REFERENCES multiuser (member_email)
ON DELETE CASCADE;

-- SQLINES LICENSE FOR EVALUATION USE ONLY
create table multiqna(
    qna_code int auto_increment primary key,
    qna_subject varchar(100) not null,
    qna_content varchar(3000) not null,
    qna_regdate datetime default CURRENT_TIMESTAMP,
    member_email varchar(100) not null
);

ALTER TABLE multiqna 
ADD FOREIGN KEY (member_email) 
REFERENCES multiuser (member_email)
ON DELETE CASCADE;