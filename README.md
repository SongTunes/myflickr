# myflickr
一个在线图片管理和分享平台
## 后端
#### 环境
Java JDK 1.8
#### 框架
* Maven == 3.6.3
* Spring Boot == 2.7.2
* Mybatis Plus == 3.5.3

#### 用户鉴权和持久化登录
Token (Java JWT == 4.3.0)

## 数据库
* [openGauss数据库](https://opengauss.obs.cn-south-1.myhuaweicloud.com/2.0.0/arm/openGauss-2.0.0-openEuler-64bit-all.tar.gz)
  * 基于PostgreSQL
  * 驱动: huaweicloud-dws-jdbc == 8.1.0
  * 主备节点实现HA
* 触发器
  * 功能: 日期合法性检查
* 存储过程
  * 功能: 获取用户活跃城市信息

## 部署
* 平台: [华为云](https://www.huaweicloud.com/)
* 设备: 2台ECS服务器
* CPU架构: 鲲鹏计算
* 规格: 2vCPUs|4GiB
* 操作系统: [openEuler 20.03 64bit with ARM(40GB)](https://www.openeuler.org/zh/download/archive/detail/?version=openEuler%2020.03%20LTS)

## 核心功能演示
#### 注册
<img src="https://github.com/SongTunes/myflickr/tree/main/doc/signup.gif" width="500px" >

#### 登录
<img src="https://github.com/SongTunes/myflickr/tree/main/doc/login.gif" width="500px" >

#### 图片浏览
<img src="https://github.com/SongTunes/myflickr/tree/main/doc/pic_browse.gif" width="500px" >

#### 添加图片
<img src="https://github.com/SongTunes/myflickr/tree/main/doc/add_pic.gif" width="500px" >

#### 发布管理
<img src="https://github.com/SongTunes/myflickr/tree/main/doc/submit_manage.gif" width="500px" >

#### 用户导入导出
<img src="https://github.com/SongTunes/myflickr/tree/main/doc/import_export.gif" width="500px" >

