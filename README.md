#码匠社区

#资料
[spring官网](https://spring.io/guides/)
[spring官网](https://spring.io/guides/)
[spring官网]spring boot官网
#工具
Git
Visual Paradigml 画图工具 类图 时序图 流程图
Flyway 数据库版本管理  V1__create_user_table.sql
本地再次提交代码 2019/7/3 20:48


别人提交了代码
修复冲突
使用h2数据库
```
sql腳本
CREATE CACHED TABLE PUBLIC.USER(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(50),
    ACCOUNT_ID VARCHAR(50),
    TOKEN CHAR(36),
    GMT_CREATE BIGINT,
    GMT_MODIFY BIGINT
)

CREATE TABLE question
(
    id int AUTO_INCREMENT PRIMARY KEY,
    title varchar(50),
    description text,
    gmt_create bigint,
    gmt_modify bigint,
    creator int,
    comment_count int DEFAULT 0,
    view_count int DEFAULT 0,
    like_count int DEFAULT 0,
    tag varchar(256)
);


```
IT教程吧
https://www.itjc8.com/
