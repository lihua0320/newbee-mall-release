package ltd.newbee.mall.dao;

import ltd.newbee.mall.entity.Goods;
import ltd.newbee.mall.entity.GoodsCategory;
import ltd.newbee.mall.entity.StockNumDTO;
import ltd.newbee.mall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {

    /**
     * 分页列表数据
     * @param pageQueryUtil
     * @return
     */
    List<Goods> findGoodsList(PageQueryUtil pageQueryUtil);

    /**
     * 查询总数
     * @param pageQueryUtil
     * @return
     */
    int getTotalGoods(PageQueryUtil pageQueryUtil);

    /**
     *
     * @param goodsIds
     * @param sellStatus
     * @return
     */
    int batchUpdateSellStatus(@Param("orderIds")Long[] goodsIds,@Param("sellStatus") int sellStatus);

    /**
     * 新增
     * @param record
     * @return
     */
    int insertSelective(Goods record);
    List<Goods> selectByPrimaryKeys(List<Long> goodsIds);

    Goods selectByPrimaryKey(Long goodsIds);
    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKeySelective(Goods record);
    int updateByPrimaryKey(Goods record);

    int updateStockNum(@Param("stockNumDTOS") List<StockNumDTO> stockNumDTOS);

    List<Goods> findNewBeeMallGoodsListBySearch(PageQueryUtil pageUtil);

    int getTotalNewBeeMallGoodsBySearch(PageQueryUtil pageUtil);


}