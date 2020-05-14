package wiki.IceCream.yuq.demo.controller;

import com.IceCreamQAQ.Yu.annotation.Action;
import com.IceCreamQAQ.Yu.annotation.Before;
import com.icecreamqaq.yuq.YuQ;
import com.icecreamqaq.yuq.annotation.GroupController;
import com.icecreamqaq.yuq.annotation.NextContext;
import com.icecreamqaq.yuq.annotation.PathVar;
import com.icecreamqaq.yuq.annotation.Save;
import com.icecreamqaq.yuq.message.Message;
import com.icecreamqaq.yuq.message.MessageFactory;
import com.icecreamqaq.yuq.message.MessageItemFactory;

import javax.inject.Inject;
import java.io.File;

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
     * MessageFactory 可以用来创建 Message 对象，用来发送消息。
     */
    @Inject
    private MessageFactory mf;

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
    public void before(long qq) throws Message {
        if (qq % 2 == 0) throw mif.text("你没有使用该命令的权限！").toMessage();
    }

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
     */
    @Action("菜单")
    public Message menu(long qq) {
        return mf.newMessage().plus(mif.image(new File("")));
    }

    /***
     * PathVar 注解。
     * 您可以通过 PathVar 注解，将消息的内容，注入到方法参数。
     * 通过合理指定 Type 属性来更加轻松的使用参数。
     * 但不是所有消息内容都支持转化到相应类型，如果无法转化，则会返回 null
     *
     * 如：PathVar.Type.Switch，可以将文字内容智能的转化为 boolean 类型，这样在做开关类的选项时，就要方便的多了。
     */
    @Action("设置")
    public String menu2(@PathVar(value = 1, type = PathVar.Type.Switch) boolean flag) {
        return "设置选项：" + flag;
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
    public String bindPhone(long qq) throws Message {
        if (qq % 2 != 0) throw mif.text("您无需绑定手机号码。").toMessage();
        return "请输入手机号码。";
    }

}
