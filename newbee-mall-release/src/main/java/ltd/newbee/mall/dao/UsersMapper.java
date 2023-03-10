package ltd.newbee.mall.dao;

import ltd.newbee.mall.entity.Goods;
import ltd.newbee.mall.entity.Users;
import ltd.newbee.mall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersMapper {

    /**
     * 分页列表数据
     * @param pageQueryUtil
     * @return
     */
    List<Users> findUsersList(PageQueryUtil pageQueryUtil);

    /**
     * 查询总数
     * @param pageQueryUtil
     * @return
     */
    int getTotalUsers(PageQueryUtil pageQueryUtil);

    Users selectByLoginName(String loginName);

    int insertSelective(Users record);

    int updateByPrimaryKeySelective(Users record);

    Users selectByPrimaryKey(Long userId);
    Users selectByLoginNameAndPasswd(@Param("loginName") String loginName, @Param("password") String password);

    int lockUserBatch(@Param("ids") Integer[] ids, @Param("lockStatus") int lockStatus);





}
