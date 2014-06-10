DROP TABLE IF EXISTS user;
CREATE TABLE user (
  user_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  username varchar(50) NOT NULL DEFAULT '',
  password varchar(50) NOT NULL DEFAULT '',
  nickname varchar(20) NOT NULL DEFAULT '',
  email varchar(200) NOT NULL DEFAULT '',
  question varchar(50) NOT NULL DEFAULT '',
  answer varchar(50) NOT NULL DEFAULT '',
  city varchar(50) NOT NULL DEFAULT '',
  province varchar(50) NOT NULL DEFAULT '',
  dance_type varchar(50) NOT NULL DEFAULT '',
  description varchar(50) NOT NULL DEFAULT '',
  sign varchar(50) NOT NULL DEFAULT '',
  sex smallint(3) NOT NULL DEFAULT 0,
  status smallint(3) NOT NULL DEFAULT 0,
  role smallint(3) NOT NULL DEFAULT 0,
  posttime datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  birthday datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  real_name varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (user_id),
  UNIQUE KEY uniq_email (email),
  UNIQUE KEY uniq_username (username)
);