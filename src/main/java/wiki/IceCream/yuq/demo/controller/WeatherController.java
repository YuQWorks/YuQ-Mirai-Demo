package wiki.IceCream.yuq.demo.controller;

import com.IceCreamQAQ.Yu.annotation.*;
import com.IceCreamQAQ.Yu.controller.ActionContext;
import com.IceCreamQAQ.Yu.entity.DoNone;
import com.icecreamqaq.yuq.YuQ;
import com.icecreamqaq.yuq.annotation.GroupController;
import com.icecreamqaq.yuq.annotation.QMsg;
import com.icecreamqaq.yuq.controller.BotActionContext;
import com.icecreamqaq.yuq.message.Message;
import com.icecreamqaq.yuq.message.MessageItemFactory;
import javax.inject.Inject;

@GroupController
public class WeatherController {
    @Inject
    private MessageItemFactory mif;

    /**
     * weight表示权重，越低越优先,默认值是0
     * @param group 信息发送者所在群 YuQ会自动注入
     * @param qq 信息发送者的ID YuQ会自动注入
     * @throws DoNone 不做任何事情的异常
     */
    @Before(weight = 0)
    public void WeatherBefore(long group,long qq) throws DoNone, Exception{
        if(group != 12345L) {
            //通过抛出异常来停止信息前往 Action，抛出 DoNone 异常，会中断处理链路并声明路由完成。
            throw new DoNone();
        }
        if(qq != 122222L){
            //将一条 Message.toThrowable() 会让这个信息返回到信息来源处。可以起到提示的作用。
            throw mif.text("你无权使用这个类里面的命令").toMessage().toThrowable();
        }
    }

    /**
     * 此处使用 @Action 将函数变成指令的响应器
     * <p>
     * 指令格式应该是 "weather 南京"
     * <p>
     * 使用 @Synonym 使命令拥有别名
     * <p>
     * 所以”天气 南京“ 和 ”天气预报 南京“ 同样会触发命令
     */
    @Action("weather {city}")
    @Synonym({"天气 {city}","天气预报 {city}"})
    @QMsg
    public Message weather(String city){
        if(city.equals("")){
            //使用mif将String变成Message
            return mif.text("要查询的城市名称不能为空").toMessage();
        }
        //使用伴生方法将String变成Message
        return Message.Companion.toMessage(city+"的天气是。。。。");
    }


    /**
     * After与 Before 极其类似，除了出现的时间点不同，使用和逻辑上基本一致
     * @param reMessage 这是由Action处理完发送的信息，目前还没有交给服务器
     * @param qq 同Before
     * @return 并非直接返回信息来源，可以在后续@After中进行使用，所以这个方法无返回值，直接修改reMessage也可实现。
     */
    @After
    public Message addNotice(Message reMessage,long qq){
        //使用mif添加At效果，同理可使用mif添加图片等。
         return reMessage.plus(mif.at(qq));
    }


    /**
     * 直接使用注入来获取YuQ对象，对象内存储着Bot的各种信息，包括 群列表，好友列表等。
     */
    @Inject
    private YuQ yuq;

    /**
     * Catch 与 Before 、 After 类似，但是必须要求参数来捕获指定异常
     * @param exception 这里捕获空指针异常
     * @return 也并非返回信息来源，而是留给后续使用
     */
    @Catch(error = NullPointerException.class)
    public void reportNullPoint(NullPointerException exception){
        //从YuQ（此时的YuQ是你的Bot）中获取指定对象并且发送信息。
        yuq.getFriends().get(12345L).sendMessage(mif.text(exception.toString()).toMessage());
    }



}
