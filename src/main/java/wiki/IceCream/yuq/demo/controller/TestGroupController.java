package wiki.IceCream.yuq.demo.controller;

import com.IceCreamQAQ.Yu.annotation.Action;
import com.IceCreamQAQ.Yu.annotation.Before;
import com.IceCreamQAQ.Yu.annotation.Synonym;
import com.IceCreamQAQ.Yu.job.JobManager;
import com.icecreamqaq.yuq.YuQ;
import com.icecreamqaq.yuq.annotation.GroupController;
import com.icecreamqaq.yuq.annotation.NextContext;
import com.icecreamqaq.yuq.entity.Group;
import com.icecreamqaq.yuq.entity.Member;
import com.icecreamqaq.yuq.message.Message;
import com.icecreamqaq.yuq.message.MessageItem;
import com.icecreamqaq.yuq.message.MessageItemFactory;

import javax.inject.Inject;

/***
 * GroupController 代表了，这个 Controller 将响应群消息。
 */
@GroupController
public class TestGroupController {

    /***
     * YuQ 接口是 YuQ Framework 向用户提供的一个便于使用的 API。
     * 通过注入 YuQ 来获得 YuQ 的实例，来调用 YuQ Framework 的绝大部分功能。
     * 如发送消息，撤回消息等等。
     */
    @Inject
    private YuQ yuq;

    /***
     * MessageItemFactory 用来创建 Message 的具体内容。
     */
    @Inject
    private MessageItemFactory mif;

    /***
     * Before 则为具体的控制器的动作前置验证，也可以称作拦截器，负责在 Action 处理消息之前进行验证。
     *
     * Before 方法接收 0 - 多个参数，通常，您所写下的参数，将会以名称匹配来进行依赖注入。
     *   支持注入的名称 - 注入的内容
     *     qq - 发送消息的 QQ 账号
     *     group - 发送消息的 QQ 群号
     *     message - 具体的 Message 对象
     *     messageId - 消息 ID
     *     sourceMessage - 未经处理的源消息内容，则为具体的 Runtime 的消息内容
     *     actionContext - 当前消息的 ActionContext 对象
     *     以及，您 Before 传递回来的需要保存的对象。
     *
     * Before 方法可以接受任何类型的返回值，当您返回了一个值的时候，框架会帮您保存起来，名称则为将类名的第一个字母转化小写后的名字。
     *
     * Before 方法可以抛出异常，来作为验证失败的中断处理方法。
     * 当您抛出了一个 Message 类型的异常后，如果您没有设置任何接收的 QQ，或 QQ 群，那么我们将会将消息发送至当前消息来源者，如果您设置了接收对象，那么发送至您的接收对象。
     * 当您想中断处理链路，并且不进行任何返回的时候，您可以抛出 DoNone 类型的异常。
     *
     * 一个 Controller 类内，可以接受多个 Before，他们按照一定的顺序依次执行，当所有 Before 执行完成之后，将继续执行 Action。
     */
    @Before
    public void before(long qq) {
        if (qq % 2 == 0) throw mif.text("你没有使用该命令的权限！").toMessage().toThrowable();
    }

    private boolean menuFlag = true;

    /***
     * Action 则为具体的控制器的动作，负责处理收到的消息。
     *
     * Action 方法接收 0 - 多个参数，通常，您所写下的参数，将会以名称匹配来进行依赖注入。
     *   支持注入的名称 - 注入的内容
     *     qq - 发送消息的 QQ 账号
     *     group - 发送消息的 QQ 群号
     *     message - 具体的 Message 对象
     *     messageId - 消息 ID
     *     sourceMessage - 未经处理的源消息内容，则为具体的 Runtime 的消息内容
     *     actionContext - 当前消息的 ActionContext 对象
     *     以及，您 Before 传递回来的需要保存的对象。
     *
     * Action 可接收方法可以接受任何类型的返回值，当您返回了一个值的时候，
     *   如果您返回的是 Message 类型的时候，我们会帮您发送这个消息，如果您没有设置任何接收的 QQ，或 QQ 群，那么我们将会将消息发送至当前消息来源者，如果您设置了接收对象，那么发送至您的接收对象。
     *   如果您返回了一个 String 类型的时候，我们会帮您构建一个 Message，并发送到当前消息的来源。
     *   如果您返回了一个 MessageItem 类型的时候，我们会帮您构建一个 Message，并发送到当前消息的来源。
     *   如果您返回的是其他类型，我们会帮您调用 toString 方法，并构建一个 Message，然后发送到当前消息的来源。
     *
     * Action 方法可以抛出异常，来返回一些信息。
     *   当您抛出了一个 Message 类型的异常后，如果您没有设置任何接收的 QQ，或 QQ 群，那么我们将会将消息发送至当前消息来源者，如果您设置了接收对象，那么发送至您的接收对象。
     *   当您想中断处理链路，并且不进行任何返回的时候，您可以抛出 DoNone 类型的异常。
     * @return
     */
    @Action("菜单")
    public Object menu(long qq) {
        if (menuFlag)
            return mif.at(qq).plus("，您好。\n" +
                    "这里是基础菜单。" +
                    "但是由于这是一个演示 Demo，他没有什么功能。" +
                    "所以也并没有菜单。" +
                    "那就这样吧。");
        return "菜单被禁用！";
    }

    /***
     * Action 内，不仅可以写单级指令，还可以写多级指令。
     * 最后的 {flag} 则代表了一个可变内容，他可以根据方法参数类型，自动映射为指定类型。
     */
    @Action("设置 菜单开关 {flag}")
    public String menu2(boolean flag) {
        menuFlag = flag;
        return "菜单开关：" + flag;
    }

    /***
     * 可以在路由内书写 { 名称 : 正则表达式 } 来动态匹配指令上的内容。
     * 如果你想匹配任意内容，则 : 及后续可以省略。示例：{color}
     * 本例子则代表只匹配单个文本。
     */
    @Action("发个{color:.}包")
    public String sendPackage(String color) {
        return String.format("QQ%s包！", color);
    }

    /***
     * YuQ 可以将您发送的数字QQ号，或者 At 某人，智能转化为您所需要的内容。
     * 本处就将对象转化为 Member 的实例。
     * 通过调用 Member 的 ban 方法，可以将目标禁言一段时间。单位：秒。
     * 通过书写 Member 类型的 qq 参数，即可获取当前消息发送者的 Member 实例。
     * 通过调用 Member 的 isAdmin 方法，可以获取当前目标是否具有管理员权限。（管理员与群主都具有管理员权限）
     *
     * 本 Action 的作用，如果发送者是管理员，就将目标禁言一段时间，如果发送人不是管理员，就将自己禁言一段时间。
     */
    @Action("禁言 {sb} {time}")
    @Synonym({"ban {sb} {time}"})
    public String ban(Member sb, Member qq, int time) {
        if (time < 60) time = 60;
        if (qq.isAdmin()) {
            sb.ban(time);
            return "好的";
        }
        qq.ban(time);
        return "您没有使用该命令的权限！为了防止恶意操作，你已被禁言相同时间。";
    }

    @Inject
    private JobManager jobManager;

    /***
     * 我们可以通过注入 JobManager 快速，动态的创建定时与时钟任务。
     * 写在路由中的 {time} 与整级路由 {nr} 最大的不同，就是写在路由中的，只能用 String 类型接受，并不能智能匹配成其他类型。
     */
    @Action("{time}秒后说 {nr}")
    public Object timeSend(String time, MessageItem nr, Group group) {
        Message nm = new Message().plus(nr);
        jobManager.registerTimer(() -> group.sendMessage(nm), Integer.parseInt(time) * 1000);
        return "好的";
    }


    /***
     * NextContext 注解用来声明完成之后进入某个上下文。
     * 这是一个用来进入上下文的 Action，当然，他与普通的 Action 没有什么区别。
     * 他可以完成普通 Action 的所有功能。
     * 只是当他在正常完成之后，会帮你把上下文自动切换到 NextContext 中声明的内容。
     *
     * 正常完成则为，当 Action 方法没有产生任何异常时，Action 方为正常完成。
     */
    @Action("绑定手机号")
    @NextContext("bindPhone")
    public void bindPhone(long qq) {
        if (qq % 2 == 0) throw mif.text("您无需绑定手机号码。").toMessage().toThrowable();
    }

}
