## 帖子 post

- **帖子id（主键）**postid int
- 用户id（用户的外键） userid int
- 所属主题  post_partition varchar(10)
- 标题 title varchar(20)
- 正文内容 content varchar(5000) 
- 评论数量 comment_num int
- 点赞数量 like_num int
- 发帖时间 post_time date
- 文件 photos varchar(1000)

CREATE TABLE post (
  postid INT PRIMARY KEY,
  userid INT,
  post_partition VARCHAR(10),
  title VARCHAR(20),
  content VARCHAR(5000),
  comment_num INT,
  like_num INT,
  post_time DATETIME,
  photos VARCHAR(1000)
  );

## 一级评论 pcomment

- **帖子评论id（主键）** pcommentid int 主键
- 用户id（用户的外键）userid int
- 评论目标id（帖子的外键）ptargetid int
- 点赞数量 like_num int
- 评论内容 pctext varchar(1000)
- 评论时间 time datetime

CREATE TABLE pcomment (
  pcommentid INT PRIMARY KEY,
  userid INT,
  ptargetid INT,
  like_num INT,
  pctext VARCHAR(1000),
  time DATETIME
  );

## 二级评论 ccomment

- **评论id（主键）** ccommentid int
- 用户id（用户的外键）userid int
- 评论目标id（评论的外键）ctargetid int
- 点赞数量 like_num bigint
- 评论内容 cctext varchar(100)
- 评论时间 time datetime
- 回复用户id usertargetid int

CREATE TABLE ccomment (
ccommentid INT PRIMARY KEY,
userid INT,
ctargetid INT,
like_num BIGINT,
cctext VARCHAR(100),
time DATETIME,
usertargetid INT
);

## 帖子点赞 plike

- **点赞id**  plikeid bigint
- 点赞人id（用户的外键）userid int
- 点赞目标id（帖子的外键）ptargetid int

CREATE TABLE plike (
  plikeid BIGINT PRIMARY KEY,
  userid INT,
  ptargetid INT
  );

## 帖子的评论点赞 pclike

- **点赞id**  pclikeid int
- 点赞人id（用户的外键）userid int
- 点赞目标id（帖子的外键）pctargetid int

CREATE TABLE pclike (
  pclikeid INT PRIMARY KEY,
  userid INT,
  pctargetid INT
  );

## 评论的评论点赞 cclike

- **点赞id**  cclikeid int
- 点赞人id（用户的外键）userid int
- 点赞目标id（评论的外键）cctargetid int

CREATE TABLE cclike (
  cclikeid INT PRIMARY KEY,
  userid INT,
  cctargetid INT
  );

## 用户 user

- **用户id** userid int
- 手机号 phone char(15)
- 邮箱号 email varchar(255)
- 密码 password varchar(255) 
- 用户名称（昵称) name varchar(50)
- 头像 profile  varchar(50)
- 封禁到期时间 banTime date

CREATE TABLE user (
  userid INT PRIMARY KEY,
  phone CHAR(15),
  email VARCHAR(255),
  password VARCHAR(255),
  name VARCHAR(50),
  profile VARCHAR(50),
  banTime TIME
  );

## 管理员 admin

- **账号** account varchar(100)
- 密码 password varchar(100)

CREATE TABLE admin (
account VARCHAR(100) PRIMARY KEY,
password VARCHAR(100)
);

## 举报 sue

- **举报id** sueid int
- 举报目标类型 targettype enum
  - 帖子
  - 帖子的评论
  - 评论的评论

- 举报目标id  ptargetid int
- 用户id（举报人id，用户的外键）userid int
- 举报原因 reason varchar(1000)
- 举报时间 sue_time datetime
- 举报处理情况 status varchar(20)/enum
  - ok 已处理
  - nosin 经检查无违规
  - wait 受理中
- 是否处理 finish boolean tinyint(1)

CREATE TABLE sue (
sueid INT PRIMARY KEY,
targettype ENUM('帖子', '帖子的评论', '评论的评论'),
ptargetid INT,
userid INT,
reason VARCHAR(1000),
sue_time DATETIME,
status VARCHAR(20),
finish BOOLEAN
);

## 通知 notice

- **通知id** noticeid int
- 接受者id（用户的外键）receiver int
- 发送者id（用户的外键）sender int 
- 通知类型 type enum
  - 帖子被评论 pcomment
  - 评论被评论 ccomment
  - 被惩罚 punish
- 通知内容 ntext varchar(100）
- 是否已读 read tinyint(1)

CREATE TABLE notice (
noticeid INT PRIMARY KEY,
receiver INT,
sender INT,
sender INT,
type ENUM('帖子被评论', '评论被评论', '被惩罚'),
ntext VARCHAR(100),
is_read TINYINT(1)
);

## 收藏 psave

- **点赞id**  psaveid int
- 点赞人id（用户的外键）userid int
- 点赞目标id（帖子的外键）ptargetid int

CREATE TABLE psave (
psaveid INT PRIMARY KEY,
userid INT,
ptargetid INT
);