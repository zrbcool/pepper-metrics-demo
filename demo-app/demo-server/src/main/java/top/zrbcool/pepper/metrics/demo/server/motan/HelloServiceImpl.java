package top.zrbcool.pepper.metrics.demo.server.motan;

import com.weibo.api.motan.config.springsupport.annotation.MotanService;
import org.apache.commons.lang3.RandomUtils;
import top.zrbcool.pepper.metrics.demo.api.HelloService;


/**
 * Description:
 *
 * @author zhiminxu
 * @version 2019-08-14
 */
@MotanService(export = "demoMotan:8002")
public class HelloServiceImpl implements HelloService {
    public String sayHello(String name) {
        int waitTime = RandomUtils.nextInt(1, 10);
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello " + name + " ! Wait time is " + waitTime;
    }
}
