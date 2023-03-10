package ltd.newbee.mall.service.impl;

import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.controller.vo.NewBeeMallIndexConfigGoodsVO;
import ltd.newbee.mall.dao.GoodsMapper;
import ltd.newbee.mall.dao.IndexConfigMapper;
import ltd.newbee.mall.entity.Carousel;
import ltd.newbee.mall.entity.Goods;
import ltd.newbee.mall.entity.IndexConfig;
import ltd.newbee.mall.service.IndexConfigService;
import ltd.newbee.mall.util.BeanUtil;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndexConfigServiceImpl implements IndexConfigService {

    @Autowired
    private IndexConfigMapper indexConfigMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public PageResult getIndexConfigPage(PageQueryUtil pageQueryUtil) {
        List<IndexConfig> indexConfigList = indexConfigMapper.findIndexConfiglList(pageQueryUtil);
        //总记录数
        int totalIndexConfigs = indexConfigMapper.getTotalIndexConfigs(pageQueryUtil);
        PageResult pageResult = new PageResult(indexConfigList, totalIndexConfigs, pageQueryUtil.getLimit(),pageQueryUtil.getPage());
        return pageResult;
    }

    @Override
    public String insertSelective(IndexConfig indexConfig) {
        if(indexConfigMapper.insertSelective(indexConfig) > 0){
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String updateSelective(IndexConfig indexConfig) {
        IndexConfig ic = indexConfigMapper.selectByPrimarykey(indexConfig.getConfigId());
        if(ic == null){
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        indexConfig.setUpdateTime(new Date());
        if(indexConfigMapper.updateByPrimaryKeySelective(indexConfig) > 0){
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        if(ids.length < 1){
            return false;
        }
        return indexConfigMapper.deleteBatch(ids) > 0;
    }

    @Override
    public List<NewBeeMallIndexConfigGoodsVO> getConfigGoodsesForIndex(int configType, int number) {
        List<NewBeeMallIndexConfigGoodsVO> newBeeMallIndexConfigGoodsVOS = new ArrayList<>(number);
        List<IndexConfig> indexConfigs = indexConfigMapper.findIndexConfigsByTypeAndNum(configType, number);
        if (!CollectionUtils.isEmpty(indexConfigs)) {
            //取出所有的goodsId
            List<Long> goodsIds = indexConfigs.stream().map(IndexConfig::getGoodsId).collect(Collectors.toList());
            List<Goods> newBeeMallGoods = goodsMapper.selectByPrimaryKeys(goodsIds);
            newBeeMallIndexConfigGoodsVOS = BeanUtil.copyList(newBeeMallGoods, NewBeeMallIndexConfigGoodsVO.class);
            for (NewBeeMallIndexConfigGoodsVO newBeeMallIndexConfigGoodsVO : newBeeMallIndexConfigGoodsVOS) {
                String goodsName = newBeeMallIndexConfigGoodsVO.getGoodsName();
                String goodsIntro = newBeeMallIndexConfigGoodsVO.getGoodsIntro();
                // 字符串过长导致文字超出的问题
                if (goodsName.length() > 30) {
                    goodsName = goodsName.substring(0, 30) + "...";
                    newBeeMallIndexConfigGoodsVO.setGoodsName(goodsName);
                }
                if (goodsIntro.length() > 22) {
                    goodsIntro = goodsIntro.substring(0, 22) + "...";
                    newBeeMallIndexConfigGoodsVO.setGoodsIntro(goodsIntro);
                }
            }
        }
        return newBeeMallIndexConfigGoodsVOS;
    }
}
