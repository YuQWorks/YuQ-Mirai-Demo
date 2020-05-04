package wiki.IceCream.yuq.demo.job;

import com.IceCreamQAQ.Yu.annotation.Cron;
import com.IceCreamQAQ.Yu.annotation.JobCenter;

@JobCenter
public class JobMain {

    /***
     * 时钟任务。
     * value 参数可接受 1d 1h 1m 1s 1S（天，小时，分钟，秒，毫秒）的参数。
     * 同时也支持类似于 5m5s 的组合参数。
     *
     * 当然如果你愿意，1d2S111m56h7998s333m 这样的参数也能顺利解析并正确使用。
     * 但是代码是写给自己看的，为什么要跟自己过不去呢？
     *
     * 时钟任务方法不接受任何参数，也不接受任何返回值。
     */
    @Cron("10s")
    public void ten(){
        System.out.println("到十秒钟啦！");
    }

}
