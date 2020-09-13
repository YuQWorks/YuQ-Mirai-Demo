# YuQ-Mirai

YuQ-Mirai 是一个依赖于 [Mirai-Rain](https://github.com/YuQWorks/Mirai-Rain) 的 YuQ Framework 的机器人实现。

YuQ Framework 的目的就是让机器人开发变得更简单和更有效率。

这是一个 Demo 项目。  
框架依旧处于开发阶段，我将尽可能的保证 API 尽量少的变动。

##

Demo 同时提供了 Maven 和 Gradle 的项目配置。  
请选择一个喜欢的使用。

##

Gradle 下载默认采用了 冰糕Luminous 提供的镜像地址。  
如果想采用官方地址下载，请修改 `./gradle/wapper/gradle-wrapper.properties` 文件内的相应值。

## 特性

### 路由映射
参考 wiki.IceCream.yuq.demo.controller.TestGroupController
### 依赖注入
参考 wiki.IceCream.yuq.demo.controller.TestGroupController
### 事件系统
参考 wiki.IceCream.yuq.demo.event.OnMessageEvent
### 后台队列
参考 wiki.IceCream.yuq.demo.job.JobMain
### 数据库支持
使用方法参考 [SuperDemo](https://github.com/YuQWorks/YuQ-SuperDemo)
### Web支持
使用方法参考 [SuperDemo](https://github.com/YuQWorks/YuQ-SuperDemo)
## 使用方法：
clone  
编辑 /src/main/resource/conf/YuQ.properties 填写合适的内容。  
run wiki.IceCream.yuq.demo.Start  

YuQ-Mirai 可直接启动，无需任何外部手段/依赖。

打包：
```
./gradlew build
```
对于 Eclipse 用户，可以考虑使用 `./gradlew eclipse` 命令生成 Eclipse 项目。

更详细的使用文档：[文档](https://yuqworks.github.io/YuQ-Doc/)