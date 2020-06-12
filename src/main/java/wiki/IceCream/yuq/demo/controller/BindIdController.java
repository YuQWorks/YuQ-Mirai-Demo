package wiki.IceCream.yuq.demo.controller;

import com.IceCreamQAQ.Yu.annotation.Action;
import com.icecreamqaq.yuq.annotation.*;
import com.icecreamqaq.yuq.controller.BotActionContext;

@GroupController
@ContextController
public class BindIdController {

    @Action("{绑定(i|I)(d|D)}")
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
