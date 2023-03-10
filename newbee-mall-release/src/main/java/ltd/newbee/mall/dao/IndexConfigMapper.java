package ltd.newbee.mall.dao;

import ltd.newbee.mall.entity.Carousel;
import ltd.newbee.mall.entity.IndexConfig;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IndexConfigMapper {

    /**
     * 分页列表数据
     * @param pageQueryUtil
     * @return
     */
    List<IndexConfig> findIndexConfiglList(PageQueryUtil pageQueryUtil);

    /**
     * 查询总数
     * @param pageQueryUtil
     * @return
     */
    int getTotalIndexConfigs(PageQueryUtil pageQueryUtil);


    /**
     * 新增一条数据
     * @param indexConfig
     * @return
     */
    int insertSelective(IndexConfig indexConfig);

    /**
     * 修改
     * @param indexConfig
     * @return
     */
    int updateByPrimaryKeySelective(IndexConfig indexConfig);

    /**
     * 详情
     * @param configId
     * @return
     */
    IndexConfig selectByPrimarykey(Long configId);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteBatch(Integer[] ids);
    List<IndexConfig> findIndexConfigsByTypeAndNum(@Param("configType") int configType, @Param("number") int number);

}
