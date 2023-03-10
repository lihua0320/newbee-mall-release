package ltd.newbee.mall.dao;

import ltd.newbee.mall.entity.Carousel;
import ltd.newbee.mall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarouselMapper {

    /**
     * 查询分页数据
     * @param pageQueryUtil
     * @return
     */
    List<Carousel> findCarouselList(PageQueryUtil pageQueryUtil);

    /**
     * 查询总数
     * @param pageQueryUtil
     * @return
     */
    int getTotalCarousels(PageQueryUtil pageQueryUtil);

    /**
     * 新增一条数据
     */
    int insertSelective(Carousel carousel);

    /**
     * 修改数据
     * @param carousel
     * @return
     */
    int updateByPrimaryKeySelective(Carousel carousel);

    /**
     * 主键获取详情
     * @param carouselId
     * @return
     */
    Carousel selectByPrimaryKey(Integer carouselId);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteBatch(Integer[] ids);

    List<Carousel> findCarouselsByNum(@Param("number") int number);
}
