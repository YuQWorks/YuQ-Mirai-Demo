package wiki.IceCream.yuq.demo.controller;

import com.IceCreamQAQ.Yu.annotation.Action;
import com.icecreamqaq.yuq.annotation.ContextController;
import com.icecreamqaq.yuq.annotation.NextContext;
import com.icecreamqaq.yuq.annotation.PathVar;
import com.icecreamqaq.yuq.annotation.Save;
import com.icecreamqaq.yuq.controller.BotActionContext;
import com.icecreamqaq.yuq.message.Message;
import com.icecreamqaq.yuq.message.MessageItemFactory;

import javax.inject.Inject;

@ContextController
public class TestContextController {

    @Inject
    private MessageItemFactory mif;

    /***
     * 在 ContextController 里面的所有 Action，会被自动映射成上下文路由。
     *
     * 当上下文路由存在时，将优先进行上下文路由匹配。
     * 当上下文匹配失败时，并不会再进行正常的指令匹配。
     *
     * 上下文 Action 的功能与普通 Action 一致，只是触发条件不同。
     *
     * Save 注解表示，当 Action 正常完成时，将本参数内容，保存到 Session，可以供后续注入。
     * Save 保存名为 Named 注解声明内容，如参数未声明 Named 注解，则为参数名。
     */
    @Action("bindPhone")
    @NextContext("phoneVerKey")
    public String bindPhone(@Save @PathVar(0) String phone, BotActionContext actionContext) throws Message {
        if (phone.length() != 11){
            actionContext.setNextContext("bindPhone");
            throw mif.text("手机号码输入错误！请重新输入").toMessage();
        }
        return "请输入手机验证码。";
    }

    /***
     * 此处的 phone 则是上一步中保存下来的 phone
     * 当不再提供一个 NextContext 时，则完成一个上下文，回归普通。
     */
    @Action("phoneVerKey")
    public String phoneVerKey(@PathVar(0) String key,String phone){
        return String.format("您要绑定的手机号为：%s，手机验证码为：%s，绑定成功！",phone,key);
    }

}
