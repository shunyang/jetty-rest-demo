## 目的
该demo为采用内嵌jetty+spring的方式搭建的web项目，主要可用于rest服务开发

## 项目使用过程
* 下载
git clone  git@github.com:shunyang/jetty-rest-demo.git
cd jetty-rest-demo
* 构建：
mvn clean install -Pdev -DskipTests
* 运行：
方式一：java -jar target/jetty-rest-demo-dev-*.jar 
方式二：直接执行JettyServer中的main方法
*生产部署：
可参考脚本bin/start.sh


