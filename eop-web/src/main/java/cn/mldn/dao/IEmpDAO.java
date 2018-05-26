package cn.mldn.dao;

import cn.mldn.util.dao.IDAO;
import cn.mldn.vo.Emp;

import java.sql.SQLException;
import java.util.List;

public interface IEmpDAO extends IDAO<String, Emp> {

    /**
     * 根据eids查询列表
     *
     * @param eids
     * @return
     * @throws SQLException
     */
    List<Emp> getByEidList(List<String> eids) throws SQLException;

    /**
     * 根据eid查询出大于当前用户权限的人员
     *
     * @param eid
     * @return
     * @throws SQLException
     */
    List<Emp> getBylEidList(String eid) throws SQLException;

    /**
     * 根据eid & did查询出大于当前用户权限的人员
     *
     * @param eid
     * @param did
     * @return
     * @throws SQLException
     */
    List<Emp> getDidEmpList(String eid,Long did) throws SQLException;

    /**
     * 根据eid修改密码
     *
     * @param eid
     * @return
     * @throws SQLException
     */
    boolean updatePassword(String password,String eid) throws SQLException;

    /**
     * 根据eid查询最后一次上线时间
     *
     * @param eid
     * @return
     * @throws SQLException
     */
    boolean updateLastDate(String eid,java.util.Date lastDate)throws SQLException;
}
