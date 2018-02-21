CREATE TABLE IF NOT EXISTS user (
  id       VARCHAR2(32) PRIMARY KEY,
  username VARCHAR2(32),
  status   BOOLEAN,
  sign     VARCHAR2(32),
  avatar   VARCHAR2(255),
  password VARCHAR2(60) --Password
);
CREATE TABLE IF NOT EXISTS role (
  id       VARCHAR2(32) PRIMARY KEY,
  rolename VARCHAR2(32) --角色名
);

CREATE TABLE IF NOT EXISTS user_role (
  id      VARCHAR2(32) PRIMARY KEY,
  user_id VARCHAR2(32), --用户id？复合主键吗？感觉不需要
  role_id VARCHAR2(32)  --角色id
);
-- USER_RELATIONAL表中的friend_id 命名并不妥，实际的用户关系已经改由表中的status来表明
-- 表中的user_id是为了处理用户之间的关系而准备的，与user_group表并不冲突，也不多余
CREATE TABLE IF NOT EXISTS USER_RELATIONAL (
  id          VARCHAR2(32) PRIMARY KEY,
  user_id     VARCHAR2(32),
  friend_id   VARCHAR2(32),
  friend_note VARCHAR2(32),
  group_id    VARCHAR2(32),
  status      VARCHAR2(2)      --双方在为同意好友关系之前，将一直存在一个false的记录，以提醒用户每次上线后处理，（拒绝或者添加）
);
CREATE TABLE IF NOT EXISTS user_group (
  ID         VARCHAR2(32) , -- UUID不好看，这玩意省事
  USER_ID    VARCHAR2(32) ,
  GROUP_NAME VARCHAR2(32) ,
  primary key(ID)
);