package cn.mldn.dao;

import java.sql.SQLException;
import java.util.List;

import cn.mldn.util.dao.IDAO;
import cn.mldn.vo.Resource;
import cn.mldn.vo.Supply;

public interface ISupplyDAO extends IDAO<Long, Supply> {

	/**
	 * 根据一个雇员ID查询出当前雇员的所有资源采购申请单
	 * 
	 * @param eid
	 *            要查询的雇员ID
	 * @return 返回雇员所有的资源采购申请单
	 * @throws SQLException
	 *             SQL
	 */
	public List<Supply> suppyList(String eid) throws SQLException;
	
	
	/**
	 * 根据资源采购申请单编号查询办公资源详细列表
	 * @param spid 资源采购订单编号
	 * @return 办公资源详细列表
	 * @throws SQLException
	 */
	public List<Resource> findByApply(Long spid) throws SQLException ;
	
	/**
	 * 根据一个雇员的ID查询出所有得当前雇员得资源申请单
	 * @param currentPage 当前页数
	 * @param lineSize 每页显示得行数
	 * @param column 要检索得字段
	 * @param keyWord 要检索得关键字
	 * @param eid 当前雇员ID
	 * @return 返回当前雇员得所有资源申请单
	 * @throws SQLException
	 */
	public List<Supply> findEidSplit(Long currentPage, Integer lineSize, String column, String keyWord,String eid)
			throws SQLException ;
	
	/**
	 * 根据一个雇员的ID查询出所有得当前雇员得资源申请单
	 * @param currentPage 当前页数
	 * @param lineSize 每页显示得行数
	 * @param eid 当前雇员ID
	 * @return 返回当前雇员得所有资源申请单
	 * @throws SQLException
	 */
	public List<Supply> findEidSplit(Long currentPage, Integer lineSize, String eid) throws SQLException ;
	
	/**
	 * 根据一个雇员ID模糊查询出统计出资源申请单
	 * @param column 要检索得字段
	 * @param keyWord 要检索得关键字 
	 * @param eid 要统计的雇员ID
	 * @return 返回统计数量
	 * @throws SQLException
	 */
	public Long getEidAllCount(String column,String keyWord,String eid) throws SQLException ;
	
	/**
	 * 根据一个雇员ID统计所有得资源申请单
	 * @param eid 要统计的雇员ID
	 * @return 返回统计数量
	 * @throws SQLException
	 */
	public Long getEidAllCount(String eid) throws SQLException ;
}
