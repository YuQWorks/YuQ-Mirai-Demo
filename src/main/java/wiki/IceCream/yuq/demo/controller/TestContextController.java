package wiki.IceCream.yuq.demo.controller;

import com.IceCreamQAQ.Yu.annotation.Action;
import com.icecreamqaq.yuq.annotation.*;
import com.icecreamqaq.yuq.controller.BotActionContext;
import com.icecreamqaq.yuq.controller.NextActionContext;
import com.icecreamqaq.yuq.message.Message;

/**
 * 此方法接近弃用，因为单路径有更加简洁的情况。
 * @see BpController
 */
@ContextController
public class TestContextController {

    /**
     * 在 ContextController 里面的所有 Action，会被自动映射成上下文路由。
     * <p>
     * 当上下文路由存在时，将优先进行上下文路由匹配。
     * 当上下文匹配失败时，并不会再进行正常的指令匹配。
     * <p>
     * 上下文 Action 的功能与普通 Action 一致，只是触发条件不同。
     * <p>
     * Save 注解表示，当 Action 正常完成时，将本参数内容，保存到 Session，可以供后续注入。
     * Save 保存名为 Named 注解声明内容，如参数未声明 Named 注解，则为参数名。
     */
    @Action("bindPhone")
    @NextContext("phoneVerKey")
    @ContextTip("请输入手机号码。")
    @ContextTip(value = "手机号码输入错误，请重新输入。", status = 1)
    public void bindPhone(@Save @PathVar(0) String phone) {
        if (phone.length() != 11) throw new NextActionContext("bindPhone", 1);
    }

    /**
     * 此处的 phone 则是上一步中保存下来的 phone
     * <p>
     * 当不再提供一个 NextContext 时，则完成一个上下文，回归普通。
     */
    @Action("phoneVerKey")
    @ContextTip("请输入手机验证码。")
    @ContextTip(value = "手机验证码输入错误，请重新输入。", status = 1)
    public String phoneVerKey(@PathVar(0) String key, String phone) {
        if (key.length() != 4) throw new NextActionContext("phoneVerKey", 1);
        return String.format("您要绑定的手机号为：%s，手机验证码为：%s，绑定成功！", phone, key);
    }

}
