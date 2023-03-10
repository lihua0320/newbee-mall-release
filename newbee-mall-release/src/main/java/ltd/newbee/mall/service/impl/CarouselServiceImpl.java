package ltd.newbee.mall.service.impl;

import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.controller.vo.NewBeeMallIndexCarouselVO;
import ltd.newbee.mall.dao.CarouselMapper;
import ltd.newbee.mall.entity.Carousel;
import ltd.newbee.mall.service.CarouselService;
import ltd.newbee.mall.util.BeanUtil;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public PageResult getCarouselPage(PageQueryUtil pageQueryUtil) {
        List<Carousel> carouselList = carouselMapper.findCarouselList(pageQueryUtil);
        //总记录数
        int totalCarousels = carouselMapper.getTotalCarousels(pageQueryUtil);
        PageResult pageResult = new PageResult(carouselList, totalCarousels, pageQueryUtil.getLimit(),pageQueryUtil.getPage());
        return pageResult;
    }

    @Override
    public String insertSelective(Carousel carousel) {
        if(carouselMapper.insertSelective(carousel) > 0){
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String updateCarousel(Carousel carousel) {
        Carousel cl = carouselMapper.selectByPrimaryKey(carousel.getCarouselId());
        if(cl == null){
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        cl.setCarouselRank(carousel.getCarouselRank());
        cl.setRedirectUrl(carousel.getRedirectUrl());
        cl.setCarouselUrl(carousel.getCarouselUrl());
        cl.setUpdateTime(new Date());
        if(carouselMapper.updateByPrimaryKeySelective(cl) > 0){
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public Carousel getCarouselById(Integer carouselId) {
        return carouselMapper.selectByPrimaryKey(carouselId);
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        if(ids.length < 1){
            return false;
        }
        return carouselMapper.deleteBatch(ids) > 0;
    }

    @Override
    public List<NewBeeMallIndexCarouselVO> getCarouselsForIndex(int number){
        List<NewBeeMallIndexCarouselVO> newBeeMallIndexCarouselVOS = new ArrayList<>(number);
        List<Carousel> carousels = carouselMapper.findCarouselsByNum(number);
        if (!CollectionUtils.isEmpty(carousels)) {
            newBeeMallIndexCarouselVOS = BeanUtil.copyList(carousels, NewBeeMallIndexCarouselVO.class);
        }
        return newBeeMallIndexCarouselVOS;
    }

}
