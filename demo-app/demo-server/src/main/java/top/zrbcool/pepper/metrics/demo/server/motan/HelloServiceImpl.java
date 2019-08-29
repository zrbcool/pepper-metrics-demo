package top.zrbcool.pepper.metrics.demo.server.motan;

import com.weibo.api.motan.config.springsupport.annotation.MotanService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import top.zrbcool.pepper.metrics.demo.api.HelloService;
import top.zrbcool.pepper.metrics.demo.server.dao.CityDao;
import top.zrbcool.pepper.metrics.demo.server.mapper.HotelMapper;


/**
 * Description:
 *
 * @author zhiminxu
 * @version 2019-08-14
 */
@MotanService(export = "demoMotan:8002")
public class HelloServiceImpl implements HelloService {
    @Autowired
    private CityDao cityDao;
    @Autowired
    private HotelMapper hotelMapper;
    public String sayHello(String name) {
        int waitTime = RandomUtils.nextInt(50, 100);
        cityDao.selectCityById(1);
        hotelMapper.selectByCityId(1);
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello " + name + " ! Wait time is " + waitTime;
    }
}
