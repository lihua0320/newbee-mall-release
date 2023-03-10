package ltd.newbee.mall.controller;

import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.entity.Carousel;
import ltd.newbee.mall.service.CarouselService;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

@Controller    //RestController = Controller + ResponseBody
@RequestMapping("/admin")
public class CarouselController {

    @Autowired
    private CarouselService carouselService;


    @GetMapping("/carousels")
    public String login(HttpServletRequest request){
        request.setAttribute("path", "newbee_mall_carousel");
        return "admin/newbee_mall_carousel";
    }

    //分页列表
    @GetMapping("/carousels/list")
    @ResponseBody
    public Result list(@RequestParam Map<String,Object> params){
            if(StringUtils.isEmpty((String) params.get("page")) || StringUtils.isEmpty("limit")){
                return ResultGenerator.genFailResult("异参数常");
            }
            PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
            return ResultGenerator.genSuccessResult(carouselService.getCarouselPage(pageQueryUtil));
    }

    //新增数据
    @PostMapping("/carousels/save")
    @ResponseBody
    public Result save(@RequestBody Carousel carousel){
        if(StringUtils.isEmpty(carousel.getCarouselUrl()) ||
                Objects.isNull(carousel.getCarouselRank())){
            return ResultGenerator.genFailResult("参数异常!");
        }
        String result = carouselService.insertSelective(carousel);
        if(ServiceResultEnum.SUCCESS.getResult().equals(result)){
            return ResultGenerator.genSuccessResult();
        }else{
            return ResultGenerator.genFailResult(result);
        }
    }

    //修改
    @PostMapping("/carousels/update")
    @ResponseBody
    public Result update(@RequestBody Carousel carousel){
        if(Objects.isNull(carousel.getCarouselId()) ||
            StringUtils.isEmpty(carousel.getCarouselUrl()) || Objects.isNull(carousel.getCarouselRank())){
            return ResultGenerator.genFailResult("参数异常!");
        }
        String result = carouselService.updateCarousel(carousel);
        if(ServiceResultEnum.SUCCESS.getResult().equals(result)){
            return ResultGenerator.genSuccessResult();
        }else{
            return ResultGenerator.genFailResult(result);
        }
    }

    //详情
    @GetMapping("/carousels/info/{id}")
    @ResponseBody
    public Result info(@PathVariable("id") Integer carouselId){
        Carousel carousel = carouselService.getCarouselById(carouselId);
        if(carousel == null){
            return ResultGenerator.genFailResult(ServiceResultEnum.DATA_NOT_EXIST.getResult());
        }
        return ResultGenerator.genSuccessResult(carousel);
    }


    //删除
    @PostMapping("/carousels/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids){
        if(ids.length < 1){
            return ResultGenerator.genFailResult("参数异常!");
        }
        if(carouselService.deleteBatch(ids)){
            return ResultGenerator.genSuccessResult();
        }else{
            return ResultGenerator.genFailResult("删除失败");
        }
    }
}
