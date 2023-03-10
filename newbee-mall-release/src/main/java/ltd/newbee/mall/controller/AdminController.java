package ltd.newbee.mall.controller;

import ltd.newbee.mall.entity.AdminUser;
import ltd.newbee.mall.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminUserService adminUserService;

    //从后端地址跳转到前端页面
    @GetMapping("/login")
    public String login(){
//        System.out.println("11111111111111");
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("userName") String userName, @RequestParam("password") String password,
                        @RequestParam("verifyCode") String verifyCode , HttpSession session){
            if(StringUtils.isEmpty(verifyCode)){
                session.setAttribute("errorMsg", "验证码不能为空");
                return "admin/login";
            }
            if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
                session.setAttribute("errorMsg", "用户名或密码不能为空");
                return "admin/login";
            }
            String kaptchaCode = session.getAttribute("verifyCode") + "";
            if(StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)){
                session.setAttribute("errorMsg", "验证码错误");
                return "admin/login";
            }

            AdminUser adminUser = adminUserService.login(userName, password);
            if(adminUser !=null){
                session.setAttribute("loginUser", adminUser.getNickName());
                session.setAttribute("loginUserId", adminUser.getAdminUserId());
                //session过期实践为2小时
                return "redirect:/admin/index";
            }else{
                session.setAttribute("errorMsg", "登录失败");
            }
        return "admin/login";
    }

    //从后端地址跳转到前端页面
    @GetMapping({"","/","/index"})
    public String index(HttpServletRequest request){
//        System.out.println("22222222222");
        request.setAttribute("path" ,"index");
        return "admin/index";
    }


    @GetMapping("/profile")
    public String profile(HttpServletRequest request){
        Integer loginUserId = (Integer) request.getSession().getAttribute("loginUserId");
        AdminUser adminUser = adminUserService.getUserDetailById(loginUserId);
        if(adminUser == null){
            return "admin/login";
        }
        request.setAttribute("path", "profile");
        request.setAttribute("loginUserName",adminUser.getLoginUserName());
        request.setAttribute("nickName",adminUser.getNickName());
        return "admin/profile";
    }

    //修改密码
    @PostMapping("/profile/password")
    @ResponseBody
    public String passwordUpdate(HttpServletRequest request,@RequestParam("originalPassword") String originalPassword,
                                 @RequestParam("newPassword") String NewPassword ){
            if(StringUtils.isEmpty(originalPassword) || StringUtils.isEmpty(NewPassword)){
                return "参数不能为空";
            }
        Integer loginUserId = (Integer) request.getSession().getAttribute("loginUserId");
            if(adminUserService.updatePassword(loginUserId, originalPassword, NewPassword)){
                //修改成功后清理session中的数据，前端控制跳转到登录页面
                request.getSession().removeAttribute("loginUserId");
                request.getSession().removeAttribute("loginUser");
                request.getSession().removeAttribute("errorMsg");
                return "success";
            }else{
                return "修改失败";
            }
    }

    //修改用户信息
    @PostMapping("/profile/name")
    @ResponseBody
    public String userNameUpdate(HttpServletRequest request,@RequestParam("loginUserName") String loginUserName,
                                 @RequestParam("nickName") String nickName){
        if (StringUtils.isEmpty(loginUserName) || StringUtils.isEmpty(nickName) ){
            return "参数不能为空";
        }
        Integer loginUserId = (Integer) request.getSession().getAttribute("loginUserId");
        if(adminUserService.updateName(loginUserId, loginUserName,nickName)){
            return "success";
        }else{
            return "修改失败";
        }
    }

    @GetMapping("/logout")
    public String  logout(HttpServletRequest request){
        request.getSession().removeAttribute("loginUserId");
        request.getSession().removeAttribute("loginUser");
        request.getSession().removeAttribute("errorMsg");
        return "admin/login";
    }
}
