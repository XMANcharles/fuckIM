--初始化脚本,第一次运行打开
-- INSERT INTO user VALUES ('0001', '耿鑫1', 1, '我要打十个', 'http://tp1.sinaimg.cn/5619439268/180/40030060651/1','$2a$10$iPFXYw0maUa143FZlvfdLuBfGLSPtuKkVF5bU86USl4vVXL60fuuC');
-- INSERT INTO user VALUES ('0002', '耿鑫2', 1, '我要打十个！', 'aaa.jpg','$2a$10$iPFXYw0maUa143FZlvfdLuBfGLSPtuKkVF5bU86USl4vVXL60fuuC');
-- INSERT INTO user VALUES ('0003', '耿鑫3', 0, '我要打十个！', 'aaa.jpg','$2a$10$iPFXYw0maUa143FZlvfdLuBfGLSPtuKkVF5bU86USl4vVXL60fuuC');
-- INSERT INTO user VALUES ('0004', 'colder', 1, 'Fuck! World!！', 'aaa.jpg','$2a$10$iPFXYw0maUa143FZlvfdLuBfGLSPtuKkVF5bU86USl4vVXL60fuuC');

--ROLE
-- INSERT INTO ROLE VALUES ('0001', '管理员');
-- INSERT INTO ROLE VALUES ('0002', 'VIP');
-- INSERT INTO ROLE VALUES ('0003', 'USER');

--USER_ROLE
-- INSERT INTO USER_ROLE VALUES ('0001', '0001', '0001');
-- INSERT INTO USER_ROLE VALUES ('0002', '0001', '0003');
-- INSERT INTO USER_ROLE VALUES ('0003', '0002', '0003');
-- INSERT INTO USER_ROLE VALUES ('0004', '0004', '0002');

--USER_RELATION
-- INSERT INTO user_relational VALUES ('0001', '0001', '0002','摇就完事了','000001','FF' );
-- INSERT INTO user_relational VALUES ('0002', '0002', '0001','帅气小哥哥','000002','FF' );
-- INSERT INTO user_relational VALUES ('0003', '0001', '0004','斯科达摩','000001','FF' );
-- INSERT INTO user_relational VALUES ('0004', '0004', '0001','哈萨克斯','000001','FF' );


--USER_GROUP
-- INSERT INTO user_group VALUES ('000001','0001', '我渴望奶子');
-- INSERT INTO user_group VALUES ('000002','0002', '正义必胜！');
