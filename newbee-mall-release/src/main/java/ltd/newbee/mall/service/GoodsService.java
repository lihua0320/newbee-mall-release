package ltd.newbee.mall.service;

import ltd.newbee.mall.entity.Goods;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;

public interface GoodsService {

    /**
     * 分页
     * @param pageQueryUtil
     * @return
     */
    PageResult getGoodsPage(PageQueryUtil pageQueryUtil);

    /**
     * 批量修改销售状态(上架下架)
     *
     * @param ids
     * @return
     */
    Boolean batchUpdateSellStatus(Long[] ids,int sellStatus);


    /**
     * 添加一条商品
     * @param goods
     * @return
     */
    String saveGoods(Goods goods);

    /**
     * 获取商品详情
     *
     * @param id
     * @return
     */
    Goods getGoodsById(Long id);

    /**
     * 修改商品信息
     *
     * @param goods
     * @return
     */
    String updateGoods(Goods goods);

    /**
     * 商品搜索
     *
     * @param pageUtil
     * @return
     */
    PageResult searchNewBeeMallGoods(PageQueryUtil pageUtil);


}
