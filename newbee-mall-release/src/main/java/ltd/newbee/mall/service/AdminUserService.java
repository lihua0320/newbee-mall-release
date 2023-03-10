package ltd.newbee.mall.service;

import ltd.newbee.mall.entity.AdminUser;
import ltd.newbee.mall.util.PageResult;
import org.apache.ibatis.annotations.Param;

public interface AdminUserService {
    AdminUser login(String userName, String password);

    /**
     * 根据用户id，获取用户信息
     * @param loginUserId
     * @return
     */
    AdminUser getUserDetailById(Integer loginUserId);

    /**
     * 修改用户信息
     * @param loginUserId
     * @param loginUserName
     * @param nickName
     * @return
     */
    Boolean updateName(Integer loginUserId,String loginUserName,String nickName);


    /**
     * 修改用户密码
     * @param loginUserId
     * @param originalPassword
     * @param NewPassword
     * @return
     */
    Boolean updatePassword(Integer loginUserId,String originalPassword, String NewPassword);

}
