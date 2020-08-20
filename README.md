**项目说明** 
- sky是一个自我练习的项目
- 包括：
    - 最基础的SpringBoot项目
    - 数据结构和算法
    - 并发编程
<br>

**具有如下特点** 
- 友好的代码结构及注释，便于阅读及二次开发
- 引入quartz定时任务，可动态完成任务的添加、修改、删除、暂停、恢复及日志查看等功能
- 引入API模板，根据token作为登录令牌，极大的方便了APP接口开发
- 引入Hibernate Validator校验框架，轻松实现后端校验
- 引入云存储服务，已支持：七牛云、阿里云、腾讯云等
- 引入swagger文档支持，方便编写API接口文档
<br> 

**项目结构** 
```
happy-sky-fast
│
├─common 公共模块
│  ├─aspect 系统日志
│  ├─exception 异常处理
│  ├─validator 后台校验
│  └─xss XSS过滤

├─db  项目SQL语句
│
├─common 公共模块
│  ├─aspect 系统日志
│  ├─exception 异常处理
│  ├─validator 后台校验
│  └─xss XSS过滤
│ 
├─config 配置信息
│ 
├─modules 功能模块
│  ├─app API接口模块(APP调用)
│  ├─job 定时任务模块
│  ├─oss 文件服务模块
│  └─sys 权限模块
│ 
├─SkyApplication 项目启动类
│  
├──resources 
│  ├─mapper SQL对应的XML文件
│  └─static 静态资源

** 练习 ** 
happy-sky-fast
│
├─happy-sky-arithmetic 算法模块
│  ├─sort 排序
│  ├─tree 二叉树
│  ├─...
│
├─happy-sky-concurrent 多线程并发
│  ├─Runnable 实现 Runnable 接口 *
│  ├─Thread   继承 Thread 类 *
│  ├─callable 任务包装
│  ├─Timer    任务继承Thread 类
│  ├─等(匿名内部类，lambda表达式)

```
<br> 