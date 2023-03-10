package ltd.newbee.mall.controller;

import com.sun.org.apache.regexp.internal.RE;
import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.entity.GoodsCategory;
import ltd.newbee.mall.service.CarouselService;
import ltd.newbee.mall.service.GoodsCategoryService;
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

@Controller
@RequestMapping("/admin")
public class GoodsCategoryController {
    @Autowired
    private GoodsCategoryService categoryService;
    @GetMapping("/categories")
    public String categoryPage(HttpServletRequest request, @RequestParam("categoryLevel") Byte categoryLevel,
                               @RequestParam("parentId") Long parentId, @RequestParam("backParentId") Long backParentId) {
        if (categoryLevel == null || categoryLevel < 1 || categoryLevel > 3) {
            return "error/error_5xx";
        }
        request.setAttribute("path", "tb_newbee_mall_category");
        request.setAttribute("parentId", parentId);
        request.setAttribute("backParentId", backParentId);
        request.setAttribute("categoryLevel", categoryLevel);
        return "admin/newbee_mall_category";
    }

    //列表
    @GetMapping("/categories/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty((String) params.get("page")) || StringUtils.isEmpty("limit")
                || StringUtils.isEmpty(("categoryLevel")) || StringUtils.isEmpty("parentId")) {
            return ResultGenerator.genFailResult("参数异常");
        }
        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(categoryService.getCategoryPage(pageQueryUtil));
    }

    //新增
    @RequestMapping  ("/categories/save")
    @ResponseBody
    public Result save(@RequestBody GoodsCategory goodsCategory){
        if( Objects.isNull(goodsCategory.getCategoryLevel())
                ||StringUtils.isEmpty(goodsCategory.getCategoryName())
                ||Objects.isNull(goodsCategory.getParentId())
                || Objects.isNull(goodsCategory.getCategoryRank())){
            return ResultGenerator.genFailResult("参数异常");
        }
        String result = categoryService.saveCategory(goodsCategory);
        if(ServiceResultEnum.SUCCESS.getResult().equals(result)){
            return ResultGenerator.genSuccessResult();
        }else{
            return ResultGenerator.genFailResult(result);
        }
    }

    //修改
    @RequestMapping  ("/categories/update")
    @ResponseBody
    public Result update(@RequestBody GoodsCategory  goodsCategory){
        if(Objects.isNull(goodsCategory.getCategoryId())
                ||Objects.isNull(goodsCategory.getCategoryLevel())
                ||StringUtils.isEmpty(goodsCategory.getCategoryName())
                ||Objects.isNull(goodsCategory.getCategoryId())
                ||Objects.isNull(goodsCategory.getCategoryRank())){
            return ResultGenerator.genFailResult("参数异常！");
        }
        String result = categoryService.updateGoodsCategory(goodsCategory);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)){
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult(result);
        }
    }

    //删除
    @PostMapping("/categories/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids){
        if (ids.length<1){
            return ResultGenerator.genFailResult("参数异常！");
        }
        if(categoryService.deleteBatch(ids)){
            return ResultGenerator.genSuccessResult();
        }else{
            return ResultGenerator.genFailResult("删除失败！");
        }
    }
}