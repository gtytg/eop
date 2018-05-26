package cn.mldn.dao;


import java.sql.SQLException;
import java.util.Map;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.mldn.util.dao.IDAO;
import cn.mldn.vo.Resource;

public interface IResourceDAO extends IDAO<Long, Resource> {
	
	/**
	 * 根据雇员资源编号列表中保存的办公资编号查询出所有的商品信息
	 * @param eresid 雇佣资源编号
	 * @return 一个雇员商品列表中对应商品的完整信息
	 * @throws SQLException SQL异常
	 */
	public Map<Long,Resource> findAllByResource(Long eresid) throws SQLException ;

	
	/**
	 * 根据雇员采购申请单ID查询出所有的办公资源信息
	 * @param eid 雇员ID
	 * @return 一个用户采购申请单ID中对应商品的完整信息
	 * @throws SQLException SQL
	 */
	public Map<Long, Resource> findAllBySupply(String eid) throws SQLException ;

	/**
	 * 根据一个资源采购申请单ID查询出所有的办公资源
	 * @param spid 要查询的资源采购申请单ID
	 * @return 返回所有的办公资源
	 * @throws SQLException SQL
	 */
	public List<Resource> resourceList(Long spid) throws SQLException;
	
	/**
	 * 对列表中已经存在的商品做数量上的叠加操作
	 * @param scid 资源详情ID
	 * @param amount 数量变更，在已有的数量上加1
	 * @return 更新成功返回true
	 * @throws SQLException SQL
	 */
	public boolean doEditAmount(Long scid,Integer amount) throws SQLException ;
	
	
}
