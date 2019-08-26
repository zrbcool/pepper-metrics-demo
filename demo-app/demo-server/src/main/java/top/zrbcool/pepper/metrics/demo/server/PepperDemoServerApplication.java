package top.zrbcool.pepper.metrics.demo.server;

import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.config.springsupport.AnnotationBean;
import com.weibo.api.motan.util.MotanSwitcherUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author zhangrongbincool@163.com
 * @version 19-8-26
 */
@SpringBootApplication
public class PepperDemoServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(PepperDemoServerApplication.class);
        MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
    }

    @Bean
    public AnnotationBean motanAnnotationBean() {
        AnnotationBean motanAnnotationBean = new AnnotationBean();
        motanAnnotationBean.setPackage("top.zrbcool.pepper.metrics.demo.server");
        return motanAnnotationBean;
    }
}
