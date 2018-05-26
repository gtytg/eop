package cn.mldn.dao;

import cn.mldn.util.dao.IDAO;
import cn.mldn.vo.PlanDetails;

import java.sql.SQLException;
import java.util.List;

public interface IPlanDetailsDAO extends IDAO<Long, PlanDetails> {

    /**
     * 根据pid查询数据
     *
     * @param pid
     * @return
     * @throws SQLException
     */
    List<String> findByPid(Long pid) throws SQLException;

    /**
     * 根据eid & pid查询出当前任务有无添加且与其他任务时间无冲突的人员
     *
     * @param eid
     * @param pid
     * @return
     * @throws SQLException
     */
    Long findByEidAndPid(String eid,Long pid)throws SQLException;

    /**
     * 根据eid删除数据
     *
     * @param eid
     * @return
     * @throws SQLException
     */
    boolean getEidRemove(String eid) throws SQLException;

    /**
     * 通过eid & pid 查询当前任务是否有此人员
     *
     * @param eid
     * @return 有人员返回false 无人员返回true
     * @throws SQLException
     */
    boolean currentPidExists(String eid,Long pid) throws SQLException;

    /**
     * 通过pid 查询出当前任务是否有此人员
     *
     * @param pid
     * @return
     * @throws SQLException
     */
    List<String> existsEid(Long pid) throws SQLException;


}
