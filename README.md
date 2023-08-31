# yanchangbi
这是一个智能BI分析平台项目，基于Spring Boot + React + Mq +AIGC 开发，
区别于普通的BI平台，用户只需要导入原始数据集，提出分析目标，即可自动生成可视化图表和分析结论，实现低成本数据分析
环境依赖：
node v16.20.1

back：
启动sql/create_table.sql 创建数据库
启动com/yanchang/bizmq/BiInitMain 初始化队列
启动com/yanchang/MainApplication 启动后端
