CREATE TABLE IF NOT EXISTS user (
  id       VARCHAR2(32) PRIMARY KEY,
  username VARCHAR2(32),
  status   BOOLEAN,
  sign     VARCHAR2(32),
  avatar   VARCHAR2(32),
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