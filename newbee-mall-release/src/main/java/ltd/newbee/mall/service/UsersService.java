package ltd.newbee.mall.service;

import ltd.newbee.mall.controller.vo.NewBeeMallUserVO;
import ltd.newbee.mall.entity.Users;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface UsersService {
    /**
     * 后台分页
     *
     * @param pageUtil
     * @return
     */
    PageResult getUsersPage(PageQueryUtil pageUtil);

    /**
     * 注册
     * @param loginName
     * @param password
     * @return
     */
    String register(String loginName,String password);

    /**
     * 登录
     * @param loginName
     * @param passwordMd5
     * @param httpSession
     * @return
     */


    /**
     * 登录
     *
     * @param loginName
     * @param passwordMd5
     * @param httpSession
     * @return
     */
    String login(String loginName, String passwordMd5, HttpSession httpSession);


    /**
     * 用户信息修改并返回最新的用户信息
     *
     * @param mallUser
     * @return
     */
    NewBeeMallUserVO updateUserInfo(Users mallUser, HttpSession httpSession);

    /**
     * 用户禁用与解除禁用(0-未锁定 1-已锁定)
     *
     * @param ids
     * @param lockStatus
     * @return
     */
    Boolean lockUsers(Integer[] ids, int lockStatus);

}
