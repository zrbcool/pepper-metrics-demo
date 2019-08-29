package top.zrbcool.pepper.metrics.demo.client.service;

import com.weibo.api.motan.config.springsupport.annotation.MotanReferer;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.zrbcool.pepper.metrics.demo.api.HelloService;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangrongbincool@163.com
 * @date 19-8-26
 */
@Component
public class MotanClientService implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(MotanClientService.class);
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(5);
    @MotanReferer(basicReferer = "motantestClientBasicConfig", group = "testgroup", directUrl = "server:8002", check = false)
    private HelloService service;

    @Override
    public void afterPropertiesSet() throws Exception {
        for (int i = 0; i < 2; i++) {
            EXECUTOR.submit((Runnable) () -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    log.error("", e);
                }
                for (;;) {
                    try {
                        String name = service.sayHello("hahaha");
                        TimeUnit.MILLISECONDS.sleep(RandomUtils.nextInt(50, 100));
                    } catch (InterruptedException e) {
                        log.error("", e);
                    }
                }
            });
        }
    }
}
