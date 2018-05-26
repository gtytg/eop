package cn.mldn.dao;

import cn.mldn.util.dao.IDAO;
import cn.mldn.vo.Role;

import java.util.List;

public interface IRoleDAO extends IDAO<String, Role> {

    /**
     * 根据eid获取对应权限
     *
     * @param eid
     * @return
     */
    List<Role> getRoleList(String eid) throws Exception;
}
