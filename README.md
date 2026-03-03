# 基于认知诊断的汉语学习资源推荐系统

## 项目简介
本系统是一个前后端分离的汉语学习平台，基于认知诊断模型分析学习者的知识掌握情况，实现个性化学习资源推荐。

## 技术栈
后端 (hou/): Spring Boot, Maven, MySQL, 认知诊断模型
前端 (qian/): Vue.js, Yarn, Element UI
视频处理 (ff/): FFmpeg

## 功能特性
用户注册登录
学习资源展示
认知诊断评估
个性化学习推荐
视频播放学习
学习进度追踪

## 项目结构
.
├── hou/                    # 后端代码
│   ├── src/               # 源代码
│   ├── pom.xml            # Maven配置
│   └── HELP.md            # 帮助文档
├── qian/                   # 前端代码
│   ├── study/             # 学习模块
│   │   ├── src/           # 源代码
│   │   ├── public/        # 静态资源
│   │   └── package.json   # 依赖配置
├── ff/                     # 视频处理工具
│   └── ffmpeg-7.0-essentials_build/
└── README.md               # 项目说明

## 快速开始
后端启动:
cd hou
mvn spring-boot:run

前端启动:
cd qian/study
yarn install
yarn serve

数据库配置:
1. 创建MySQL数据库
2. 修改 hou/src/main/resources/application.yml 中的数据库配置
3. 运行SQL脚本初始化数据

## 算法说明
本系统采用认知诊断模型，通过分析学习者的答题情况，诊断其对各个知识点的掌握程度，然后推荐适合其水平的学习资源。
