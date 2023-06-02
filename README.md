### 数据库创建
- 首先根据Database.md的sql创建11个数据表
-----------
### 结构说明
- src里面是主要文件,targe是编译生成的对应文件
- src/test可以里面使用@test注解生成测试项目，可以生成测试用例--->单元测试时可以用
- src/main为主要程序
- src/main/common已经完成了 JOSN消息封装类Result 和 跨域请求Web..
-----------
- src/main/generator里面有四个包,其中
  - controller 直接对接web的POST和GET请求, 使用@RequestMapping("<url>")对接前端
的POST和GET方法,使用service的方法完成
  - service 实现了对数据库的自定义服务,**需要自定义**,里面又细分为 **接口定义** 和 **接口实现**
  - mapper 实现了对实体类的抽象, 它继承于BaseMapper(mybatis写法),已完成增删改查的基本操作
  - entity 是实体类,与数据库一一对应,**已经实现了定义**
-----------
### 使用说明
- src/main里面是主程序，需要在resource/application.yml里面更改数据库的名称，账户名和密码
- 目前已经调用mybatis自动生成11个表单的增删改查函数，需要的话可以查看 **b站** 或者 **参考我已经完成的代码**