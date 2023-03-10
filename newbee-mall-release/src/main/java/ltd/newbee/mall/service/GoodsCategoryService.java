package ltd.newbee.mall.service;

import ltd.newbee.mall.controller.vo.NewBeeMallIndexCarouselVO;
import ltd.newbee.mall.controller.vo.NewBeeMallIndexCategoryVO;
import ltd.newbee.mall.controller.vo.SearchPageCategoryVO;
import ltd.newbee.mall.entity.GoodsCategory;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsCategoryService {
    /**
     *分类管理 分页数据
     * @param pageQueryUtil
     * @return
     */
    PageResult getCategoryPage(PageQueryUtil pageQueryUtil);

    /**
     * 新增一条数据
     * @param goodsCategory
     * @return
     */
    String saveCategory(GoodsCategory goodsCategory);

    /**
     * 修改
     * @param goodsCategory
     * @return
     */
    String updateGoodsCategory(GoodsCategory goodsCategory);

    /**
     * 批量删除分类数据
     * @param ids
     * @return
     */
    Boolean deleteBatch(Integer[] ids);

    GoodsCategory selectByLevelAndName(@Param("categoryLevel") Byte categoryLevel, @Param("categoryName") String categoryName);
    List<GoodsCategory> selectByLevelAndParentIdsAndNumber(List<Long> parentIds, int categoryLevel);

    GoodsCategory getGoodsCategoryById(Long id);

    /**
     * 返回分类数据(首页调用)
     *
     * @return
     */
    List<NewBeeMallIndexCategoryVO> getCategoriesForIndex();

    /**
     * 返回分类数据(搜索页调用)
     *
     * @param categoryId
     * @return
     */
    SearchPageCategoryVO getCategoriesForSearch(Long categoryId);


}



