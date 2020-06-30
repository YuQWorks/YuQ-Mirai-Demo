package wiki.IceCream.yuq.demo.controller;

import com.IceCreamQAQ.Yu.annotation.Action;
import com.icecreamqaq.yuq.annotation.*;
import com.icecreamqaq.yuq.controller.BotActionContext;

/***
 * 虽说目前 GroupController、PrivateController 与 ContextController 可以混用，但并不推荐。
 * 两者中的 Before 可能会造成逻辑冲突。
 * 并且 GroupController、PrivateController 中的无需上下文的 Action 被 ContextController 加载解析，可能造成无意义的性能浪费，并且可能带来问题。
 */
@GroupController
@ContextController
public class BindIdController {

    @Action("\\绑定(i|I)(d|D)\\")
    @NextContext("bindId")
    public String bindId(@Save @PathVar(1) String id, BotActionContext context) {
        if (id == null) return "你绑定 NM 呢？";
        // 发送验证码的代码

        context.getSession().set("key", "你的验证码");
        return "您的验证码已经发送到聊天框，请在接下来的 30 分钟内，在本群回复您的验证码";
    }

    @Action("bindId")
    public String doBindId(@PathVar(0) String reKey, String id, String key) {
        // 验证 id 的代码。
        if (key.equals(reKey)) return "绑定成功";
        return "绑定失败";
    }

}
