package top.zrbcool.pepper.metrics.demo.server.motan;

import com.weibo.api.motan.config.springsupport.annotation.MotanService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPropsHolder;
import redis.clients.jedis.PjedisPool;
import top.zrbcool.pepper.metrics.demo.api.HelloService;
import top.zrbcool.pepper.metrics.demo.server.dao.CityDao;
import top.zrbcool.pepper.metrics.demo.server.mapper.HotelMapper;

import java.util.concurrent.TimeUnit;


/**
 * Description:
 *
 * @author zhiminxu
 * @version 2019-08-14
 */
@MotanService(export = "demoMotan:8002")
public class HelloServiceImpl implements HelloService, InitializingBean {
    @Autowired
    private CityDao cityDao;
    @Autowired
    private HotelMapper hotelMapper;
    private PjedisPool jedisPool;

    public String sayHello(String name) {
        int waitTime = RandomUtils.nextInt(50, 100);
        cityDao.selectCityById(1);
        hotelMapper.selectByCityId(1);
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set("string", "robin");
            jedis.get("string");
            jedis.lpush("list", "robin");
            jedis.rpop("list");
            jedis.hset("hash", "robin", "robin");
            jedis.hget("hash", "robin");
        }
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello " + name + " ! Wait time is " + waitTime;
    }

    @Override
    public void afterPropertiesSet() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(5);
        config.setMaxIdle(5);
        config.setMinIdle(5);
        config.setMaxWaitMillis(6000);
        config.setTestOnBorrow(false);
        config.setTestOnReturn(false);
        config.setTestWhileIdle(true);
        config.setTestOnCreate(false);

        JedisPropsHolder.NAMESPACE.set("myns");
        this.jedisPool = new PjedisPool(config, "redis", 6379);

    }
}
