package ltd.newbee.mall.dao;

import ltd.newbee.mall.entity.GoodsCategory;
import ltd.newbee.mall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsCategoryMapper {

    /**
     * 分页列表数据
     * @param pageQueryUtil
     * @return
     */
    List<GoodsCategory> findCategoryList(PageQueryUtil pageQueryUtil);

    /**
     * 查询总数
     * @param pageQueryUtil
     * @return
     */
    int getTotalGoodsCategory(PageQueryUtil pageQueryUtil);


    /**
     * 新增
     * @param record
     * @return
     */
    int insertSelective(GoodsCategory record);

    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(GoodsCategory record);


    /**
     * 主键
     * @param categoryId
     * @return
     */
    GoodsCategory selectByPrimaryKey(Long categoryId);

    /**
     * 根据名称等级分类
     * @param categoryLevel
     * @param categoryName
     * @return
     */
    GoodsCategory selectByLevelAndName(@Param("categoryLevel") Byte categoryLevel,@Param("categoryName")String categoryName);


    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteBatch(Integer[] ids);

    /**
     * 删除一条
     * @param categoryId
     * @return
     */
    int deleteByPrimaryKey(Long categoryId);

    List<GoodsCategory> selectByLevelAndParentIdsAndNumber(@Param("parentIds") List<Long> parentIds, @Param("categoryLevel") int categoryLevel, @Param("number") int number);
     }
