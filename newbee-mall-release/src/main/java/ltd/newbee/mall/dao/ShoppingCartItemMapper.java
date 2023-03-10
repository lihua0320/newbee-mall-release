package ltd.newbee.mall.dao;

import ltd.newbee.mall.entity.Goods;
import ltd.newbee.mall.entity.ShoppingCartItem;
import ltd.newbee.mall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShoppingCartItemMapper {
    /**
     * 分页列表数据
     * @param pageQueryUtil
     * @return
     */
    List<ShoppingCartItem> findShoppingCartItemList(PageQueryUtil pageQueryUtil);


    /**
     * 查询总数
     * @param pageQueryUtil
     * @return
     */
    int getTotalShoppingCartItem(PageQueryUtil pageQueryUtil);

    ShoppingCartItem selectByUserIdAndGoodsId(@Param("newBeeMallUserId") Long newBeeMallUserId, @Param("goodsId") Long goodsId);

    List<ShoppingCartItem> selectByUserId(@Param("newBeeMallUserId") Long newBeeMallUserId, @Param("number") int number);

    int selectCountByUserId(Long newBeeMallUserId);

    int insertSelective(ShoppingCartItem record);

    ShoppingCartItem selectByPrimaryKey(Long cartItemId);

    int updateByPrimaryKeySelective(ShoppingCartItem record);

    int deleteByPrimaryKey(Long cartItemId);

    int deleteBatch(List<Long> ids);
}
