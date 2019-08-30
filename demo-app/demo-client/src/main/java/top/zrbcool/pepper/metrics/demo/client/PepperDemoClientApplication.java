package top.zrbcool.pepper.metrics.demo.client;

import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.config.springsupport.AnnotationBean;
import com.weibo.api.motan.util.MotanSwitcherUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangrongbincool@163.com
 * @version 19-8-26
 */
@SpringBootApplication
public class PepperDemoClientApplication {
    private static final Logger log = LoggerFactory.getLogger(PepperDemoClientApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(PepperDemoClientApplication.class);
        MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
    }



    @Bean
    public AnnotationBean motanAnnotationBean() {
        AnnotationBean motanAnnotationBean = new AnnotationBean();
        motanAnnotationBean.setPackage("top.zrbcool.pepper.metrics.demo.client");
        return motanAnnotationBean;
    }
}
