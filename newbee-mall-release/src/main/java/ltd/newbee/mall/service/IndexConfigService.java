package ltd.newbee.mall.service;

import ltd.newbee.mall.controller.vo.NewBeeMallIndexConfigGoodsVO;
import ltd.newbee.mall.entity.Carousel;
import ltd.newbee.mall.entity.IndexConfig;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;

import java.util.List;

public interface IndexConfigService {
    /**
     * 热销、新品上线、为你推荐分页数据
     * @param pageQueryUtil
     * @return
     */
    PageResult getIndexConfigPage(PageQueryUtil pageQueryUtil);

    /**
     * 新增一条数据
     * @param indexConfig
     * @return
     */
    String insertSelective(IndexConfig indexConfig);

    /**
     * 修改
     * @param indexConfig
     * @return
     */
    String updateSelective(IndexConfig indexConfig);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    Boolean deleteBatch(Integer[] ids);

    List<NewBeeMallIndexConfigGoodsVO> getConfigGoodsesForIndex(int configType, int number);

}
