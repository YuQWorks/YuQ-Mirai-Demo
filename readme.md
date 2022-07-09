# YuQ-Mirai

YuQ-Mirai 是一个依赖于 [Mirai](https://github.com/mamoe/mirai) 的 YuQ Framework 的机器人实现。

YuQ Framework 的目的就是让机器人开发变得更简单和更有效率。

这是一个 Demo 项目。  
框架依旧处于开发阶段，我将尽可能的保证 API 尽量少的变动。

##

Demo 同时提供了 Maven 和 Gradle 的项目配置。  
请选择一个喜欢的使用。

## 基础开发介绍:

在 YuQ 我们仅需很简单的代码，就可以完成很复杂的功能。  
比如，我们要针对一个指令"菜单"，进行一个标准的菜单消息回复。
```Java
@GroupController
public class GroupMenu{ 
    @Action("菜单")
    public String menu(){
        return "这是具体的菜单内容。";
    } 
}
```
YuQ 会在指令式机器人的开发中，提供非常好的帮助，让开发者能有更好的开发体验。  
在 Controller 中，我们的 Action 方法，返回的内容，会直接构建成消息，并发送当当前消息源。  
通过路由映射，我们可以很方便的编写指令，只需要将 Class 声明为一个 Controller，并且编写 Action 方法。  
其余的，YuQ 会帮您完成。  

比如我们想禁言一个人，禁言的指令为"ban @xxx或QQ号码 time"
我们只需要编写：
```Java
@GroupController
public class GroupMenu{
     @Action("ban {ban} {time}")
     public String ban(Member ban, int time){
         ban.ban(time);
         return "好的！";
     }
}
```
这样，我们就可以很轻易的完成 ban 这个指令了。

对于需要连续对话的指令式机器人，基于 YuQ 也可以轻松满足。  

更详细的使用文档：[文档](https://yuqworks.github.io/YuQ-Doc/)

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