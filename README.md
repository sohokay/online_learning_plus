@[TOC](Spring Boot+Vue实现前后分离的在线学习与评测系统)
# 前言
## 本系统用到的框架与软件
在部署本系统之前，您需要有以下知识：Spring Boot、Mybatis-Plus、Mysql、Redis、Vue、Nginx、Docker等前后端知识，并且至少使用以上知识编写过一套完整的前后分离系统。
## 本系统实现的功能
1 系统通用模块：系统的登录注册，安全性检验，注销等等。
2 教学模块
（1）	教师课前发布学习目标，制作教学资源、发布自测习题。
（2）	学生课前线上自主学习、线上自测自查。
（3）	学生课后线上交流讨论、提交作业
3 评价模块
（1）	系统自动评价学生的学习过程
（2）	教师课后获取学生学习过程数据，了解学生的学习状况
（3）	学生查看个人的学习过程评价
4 系统管理员：对系统稳定性、安全性进行管理，服务监控，系统错误日志。
5 爬虫系统：爬取mooc的课程信息
# 在线访问
学生子系统(使用谷歌浏览器开启手机模式访问)：[
student.musuixin.cn](http://student.musuixin.cn)
    账号：13313950000
    密码：123456
教师子系统：
[teacher.musuixin.cn/](http://teacher.musuixin.cn)
    账号：13313951111
    密码：123456
教师子系统：
[admin.musuixin.cn/](http://admin.musuixin.cn)
    账号：13313952222
    密码：123456   
# 下载与启动
## 下载
本系统上传到了Github，地址：[https://github.com/musuixin/online_learning_plus](https://github.com/musuixin/online_learning_plus) 您可以克隆或者下载压缩包。


## 后端启动
**主要是修改各种类中的配置项和yml文件中配置，需要修改的部分已经在带码中注释给出，以下主要是为了提醒修改的地方。修改红框中*号部分即可**
### 目录结构
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200826210908372.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2NjE4NTMx,size_16,color_FFFFFF,t_70#pic_center)
如图，本系统使用maven进行模块化开发，直接使用idea打开，并让idea识别pom.xml文件即可。
### 导入数据库
文件中附带了sql文件，直接导入mysql即可
### 修改SendSmsUtil类
此类为使用腾讯云的短信服务发送短信，使用之间请先了解。不使用也行，但您会无法使用短信系统。
![](https://img-blog.csdnimg.cn/20200826205409960.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2NjE4NTMx,size_16,color_FFFFFF,t_70#pic_center)
### 修改UploadPicture类
此类主要为上传文件，使用七牛云系统，使用之前请先了解。不使用也行，但您会无法使用文件上传系统。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200826210136964.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2NjE4NTMx,size_16,color_FFFFFF,t_70#pic_center)
### 修改application.yml文件
一共有四个application.yml文件，都要修改
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200826210513272.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2NjE4NTMx,size_16,color_FFFFFF,t_70#pic_center)
### 运行
运行四个带有web后缀的系统主类即可。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200826211253633.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2NjE4NTMx,size_16,color_FFFFFF,t_70#pic_center)
## 前端启动
前端启动较为简单。前端分为学生，教师，管理员三个模块，依次进入这三个目录，运行npm install与npm run serve即可。也可以使用webstorm打开，运行。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200826212602736.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2NjE4NTMx,size_16,color_FFFFFF,t_70#pic_center)
# 访问登录
使用之前的在线访问中的账户与密码即可访问。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200826221058768.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2NjE4NTMx,size_16,color_FFFFFF,t_70#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200826221146151.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2NjE4NTMx,size_16,color_FFFFFF,t_70#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200826221205140.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2NjE4NTMx,size_16,color_FFFFFF,t_70#pic_center)
# 总结
本系统模拟了简单的学生在线学习选课，教师添加课程，分析学生学习数据等，本系统为我的本科毕业设计，仅仅实现了简单的功能，还有许多不足和一些bug。欢迎学习交流。QQ：1804125652
