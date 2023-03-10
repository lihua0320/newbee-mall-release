package ltd.newbee.mall.controller;

import ltd.newbee.mall.service.GoodsService;
import ltd.newbee.mall.service.UsersService;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


    @Controller
    @RequestMapping("/admin")
    public class UsersController {

        @Autowired
        private UsersService usersService;

        @GetMapping("/users")
        public String goodsPage(HttpServletRequest request) {
            request.setAttribute("path", "newbee_mall_user");
            return "admin/newbee_mall_user";
        }
        //列表
        @GetMapping("/users/list")
        @ResponseBody
        public Result list(@RequestParam Map<String, Object> params) {
            if (org.springframework.util.StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
                return ResultGenerator.genFailResult("参数异常！");
            }
            PageQueryUtil pageUtil = new PageQueryUtil(params);
            return ResultGenerator.genSuccessResult(usersService.getUsersPage(pageUtil));
        }

        /**
         * 用户禁用与解除禁用(0-未锁定 1-已锁定)
         */
        @RequestMapping(value = "/users/lock/{lockStatus}", method = RequestMethod.POST)
        @ResponseBody
        public Result delete(@RequestBody Integer[] ids, @PathVariable int lockStatus) {
            if (ids.length < 1) {
                return ResultGenerator.genFailResult("参数异常！");
            }
            if (lockStatus != 0 && lockStatus != 1) {
                return ResultGenerator.genFailResult("操作非法！");
            }
            if (usersService.lockUsers(ids, lockStatus)) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("禁用失败");
            }
        }
}


