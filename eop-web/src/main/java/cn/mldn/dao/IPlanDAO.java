package cn.mldn.dao;

import cn.mldn.util.dao.IDAO;
import cn.mldn.vo.Plan;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface IPlanDAO extends IDAO<Long, Plan> {

    /**
     * 根据id & 状态查询列表
     *
     * @return 返回查询结果
     * @throws SQLException 程序异常
     */
    List<Long> findByIdAndStatusList(Set<Long> ids) throws SQLException;

    /**
     * 根据id更新任务状态
     *
     * @return 更新成功返回true 失败返回false
     * @throws SQLException
     */
    boolean updateStatus(long id) throws SQLException;

    /**
     * 更新任务人数
     *
     * @param amount
     * @param pid
     * @return
     * @throws SQLException
     */
    boolean updateAmount(Long amount, Long pid) throws SQLException;

    /**
     * 根据pid查询当前人数
     *
     * @param pid
     * @return
     * @throws SQLException
     */
    Integer findByAmount(Long pid) throws SQLException;

    /**
     * 根据eid查询分页
     *
     * @param currentPage
     * @param lineSize
     * @param eid
     * @return
     * @throws SQLException
     */
    List<Plan> findEidSplit(Long currentPage, Integer lineSize, String eid, String condition) throws SQLException;

    /**
     * 根据eid分页模糊查询
     *
     * @param currentPage
     * @param lineSize
     * @param column
     * @param keyWord
     * @param eid
     * @return
     * @throws SQLException
     */
    List<Plan> findEidSplit(Long currentPage, Integer lineSize, String column, String keyWord, String eid,String condition) throws SQLException;

    /**
     * 根据eid查询计数
     *
     * @param eid
     * @return
     * @throws SQLException
     */
    public Long getEidAllCount(String eid,String condition) throws SQLException;

    /**
     * 根据eid分页模糊计数
     *
     * @param column
     * @param keyWord
     * @param eid
     * @return
     * @throws SQLException
     */
    public Long getEidAllCount(String column, String keyWord, String eid,String condition) throws SQLException;


    /**
     * 根据eid分页模糊计数
     *
     * @param column
     * @param keyWord
     * @param eid
     * @return
     * @throws SQLException
     */
    public Long getMyEidAllCount(String column, String keyWord, String eid,String condition) throws SQLException;

    /**
     * 获取用户自身的分页模糊查询
     *
     * @param currentPage
     * @param lineSize
     * @param column
     * @param keyWord
     * @param eid
     * @param condition
     * @return
     * @throws SQLException
     */
    public List<Plan> findMyEidSplit(Long currentPage, Integer lineSize, String column, String keyWord, String eid, String condition) throws SQLException;
    /**
     * 查询是否有重名任务
     *
     * @param title
     * @return
     * @throws SQLException
     */
    boolean existsTitle(String title) throws SQLException;
}
