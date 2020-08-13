# 项目说明
> 致力于打造，生产级商用级脚手架。此项目的核心是基于SpringBoot2框架，按照微服务的方式，划分服务间的模块功能开发，以及良好的SpringCloud迁移方案。

此项目为伪微服务的单体架构，灵活的微服务方案，便捷的服务部署

# 关于重量级的SpringCloud
之前用过一段时间SpringCloud，优点肯定是很丰富的，超强的服务拓展能力，集群部署等。
但是发现，在项目前期，过度的使用SpringCloud研发进度以及服务器成本将会非常恐怖。因此设计了一套轻量级的“微服务”开发框架

# 此项目的面向人群
- 快速开发产品功能
- 商业使用
- 中小型体量（微服务开发方式，单独jar包部署）
- 轻量级后台管理

# 后端项目核心组件
- Spring Boot2 (核心组件 WEB，AOP，IOC)
- Spring Security And OAuth2 (接口鉴权)
- Swagger2.10 (接口文档说明)
- Flyway (数据库结构管理)
- Mybatis Plus 3.3.0(数据库操作)
- Dynamic Datasource 3.0.0(动态数据源切换，为分库以及数据隔离做准备)

## 基础组件库
- HuTool (JAVA基础工具集)
- Commons Lang3 (JAVA基础工具集)
- Joda Time (日期处理)
- FastJson (序列化工具)

# Web端核心项目组件
参考 https://gitee.com/mathcoder23/spring-boot2-template-web
# 部署方式
- jar包部署
- Docker部署

# 项目规划

## 1.0.x 功能(开发中)
- 后端基础架构
- Web端后端基础通讯以及骨架
- 基于OAuth2鉴权通讯

## 1.1.x 功能(规划)
- 前后端实时通讯
- 完善用户管理体系
- 完善用户权限体系

## 1.2.x (规划)
- 三端框架实现
- 小程序及H5（uniApp）
- Web管理端
- 后台接口
