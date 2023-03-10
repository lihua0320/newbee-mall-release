package ltd.newbee.mall.controller;


import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.entity.IndexConfig;
import ltd.newbee.mall.service.IndexConfigService;
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
public class IndexConfigController {

    @Autowired
    private IndexConfigService indexConfigService;

    @GetMapping("/indexConfigs")
    public String indexConfigPage(HttpServletRequest request,
                                  @RequestParam("configType") int configType){
        request.setAttribute("configType", configType);
        return "admin/newbee_mall_index_config";
    }


    //分页列表
    @GetMapping("/indexConfigs/list")
    @ResponseBody
    public Result list(@RequestParam Map<String,Object> params){
        if(StringUtils.isEmpty((String) params.get("page")) || StringUtils.isEmpty("limit")){
            return ResultGenerator.genFailResult("异参数常");
        }
        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(indexConfigService.getIndexConfigPage(pageQueryUtil));
    }


    //新增
    @PostMapping("/indexConfigs/save")
    @ResponseBody
    public Result save(@RequestBody IndexConfig indexConfig){
        if(Objects.isNull(indexConfig.getConfigType()) || StringUtils.isEmpty(indexConfig.getConfigName())
            || Objects.isNull(indexConfig.getConfigRank())){
            return ResultGenerator.genFailResult("参数异常");
        }
       String result = indexConfigService.insertSelective(indexConfig);
        if(ServiceResultEnum.SUCCESS.getResult().equals(result)){
            return ResultGenerator.genSuccessResult();
        }else{
            return ResultGenerator.genFailResult(result);
        }
   }

   //修改
   @PostMapping("/indexConfigs/update")
   @ResponseBody
   public Result update(@RequestBody IndexConfig indexConfig){
        if (Objects.isNull(indexConfig.getConfigId())
            || Objects.isNull(indexConfig.getConfigType())
            || StringUtils.isEmpty(indexConfig.getConfigName())
            || Objects.isNull(indexConfig.getConfigRank())){
            return ResultGenerator.genFailResult("参数异常");
        }
       String result = indexConfigService.updateSelective(indexConfig);
       if(ServiceResultEnum.SUCCESS.getResult().equals(result)){
           return ResultGenerator.genSuccessResult();
       }else{
           return ResultGenerator.genFailResult(result);
       }
   }

   //删除
   @PostMapping("/indexConfigs/delete")
   @ResponseBody
   public Result delete(@RequestBody Integer[] ids){
       if(ids.length < 1){
           return ResultGenerator.genFailResult("参数异常!");
       }
       if(indexConfigService.deleteBatch(ids)){
           return ResultGenerator.genSuccessResult();
       }else{
           return ResultGenerator.genFailResult("删除失败");
       }
   }
}
