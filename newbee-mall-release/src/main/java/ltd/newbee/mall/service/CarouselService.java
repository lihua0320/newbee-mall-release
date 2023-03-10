package ltd.newbee.mall.service;

import ltd.newbee.mall.controller.vo.NewBeeMallIndexCarouselVO;
import ltd.newbee.mall.entity.Carousel;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;

import java.util.List;

public interface CarouselService {

    /**
     * 轮播图分页数据
     * @param pageQueryUtil
     * @return
     */
    PageResult getCarouselPage(PageQueryUtil pageQueryUtil);

    /**
     * 新增一条数据
     */
    String insertSelective(Carousel carousel);

    /**
     * 修改
     * @param carousel
     * @return
     */
    String updateCarousel(Carousel carousel);

    /**
     * 详情
     * @param carouselId
     * @return
     */
    Carousel getCarouselById(Integer carouselId);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    Boolean deleteBatch(Integer[] ids);

    List<NewBeeMallIndexCarouselVO> getCarouselsForIndex(int number);
}
