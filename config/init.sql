DROP TABLE IF EXISTS video;
CREATE TABLE video (
  video_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL DEFAULT '',
  description varchar(200) NOT NULL DEFAULT '',
  url varchar(200) NOT NULL DEFAULT '',
  play int(10) NOT NULL default 0,
  comments int(10) NOT NULL default 0,
  love int(10) NOT NULL default 0,
  user_id int(10) NOT NULL default 0,
  nickname varchar(20) NOT NULL DEFAULT '',
  posttime datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  del smallint(3) NOT NULL DEFAULT 0,
  status smallint(3) NOT NULL DEFAULT 0,
  video_type smallint(3) NOT NULL DEFAULT 0,
  video_grade_type smallint(3) NOT NULL DEFAULT 0,
  video_source_type smallint(3) NOT NULL DEFAULT 0,
  label varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (video_id),
  UNIQUE KEY uniq_url (url)
);

DROP TABLE IF EXISTS comment;
CREATE TABLE comment (
  comment_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  video_id int(10) NOT NULL default 0,
  content varchar(200) NOT NULL DEFAULT '',
  user_id int(10) NOT NULL default 0,
  nickname varchar(20) NOT NULL DEFAULT '',
  reply_user_id int(10) NOT NULL default 0,
  reply_nickname varchar(20) NOT NULL DEFAULT '',
  posttime datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  del smallint(3) NOT NULL DEFAULT 0,
  status smallint(3) NOT NULL DEFAULT 0,
  PRIMARY KEY (comment_id)
);


CREATE TABLE `user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL DEFAULT '',
  `password` varchar(50) NOT NULL DEFAULT '',
  `nickname` varchar(20) NOT NULL DEFAULT '',
  `email` varchar(200) NOT NULL DEFAULT '',
  `question` varchar(50) NOT NULL DEFAULT '',
  `answer` varchar(50) NOT NULL DEFAULT '',
  `city` varchar(50) NOT NULL DEFAULT '',
  `province` varchar(50) NOT NULL DEFAULT '',
  `dance_type` varchar(50) NOT NULL DEFAULT '',
  `description` varchar(50) NOT NULL DEFAULT '',
  `sign` varchar(50) NOT NULL DEFAULT '',
  `sex` smallint(3) NOT NULL DEFAULT '0',
  `status` smallint(3) NOT NULL DEFAULT '0',
  `role` smallint(3) NOT NULL DEFAULT '0',
  `posttime` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  `birthday` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  `real_name` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uniq_email` (`email`),
  UNIQUE KEY `uniq_username` (`username`)
);

DROP TABLE IF EXISTS announcement;
CREATE TABLE announcement (
  announcement_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  title varchar(100) NOT NULL DEFAULT '',
  content varchar(500) NOT NULL DEFAULT '',
  img_url varchar(200) NOT NULL DEFAULT '',
  posttime datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  PRIMARY KEY (announcement_id)
);

DROP TABLE IF EXISTS notice;
CREATE TABLE notice (
  notice_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  user_id int(10) NOT NULL default 0,
  img_url varchar(200) NOT NULL DEFAULT '',
  comment_id int(10) NOT NULL default 0,
  video_id int(10) NOT NULL default 0,
  content varchar(200) NOT NULL DEFAULT '',
  posttime datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  op_user_id int(10) NOT NULL default 0,
  PRIMARY KEY (notice_id)
);

DROP TABLE IF EXISTS ad;
CREATE TABLE ad (
  ad_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  ad_area_type int(10) NOT NULL default 0,
  index_num int(10) NOT NULL default 0,
  url varchar(200) NOT NULL DEFAULT '',
  img_url varchar(200) NOT NULL DEFAULT '',
  posttime datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  del smallint(3) NOT NULL DEFAULT 0,
  PRIMARY KEY (ad_id)
);