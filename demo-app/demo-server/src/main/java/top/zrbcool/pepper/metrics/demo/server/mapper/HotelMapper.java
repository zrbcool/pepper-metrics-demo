package top.zrbcool.pepper.metrics.demo.server.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.zrbcool.pepper.metrics.demo.server.domain.Hotel;

@Mapper
public interface HotelMapper {

  Hotel selectByCityId(int cityId);

}
