package cn.mldn.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.vo.Resource;
import cn.mldn.vo.Supply;

public interface ISupplyService {

	/**
	 * 根据指定的雇员ID列出对应采购申请表的办公资源
	 * 
	 * @param eid
	 *            要查询的雇员ID
	 * @return 返回有如下的内容： 1、key = allSupplys、value = 【LIST集合】雇员对应的采购申请单中的所有办公资源记录
	 *         2、key = allResources、value = 【MAP集合】采购申请单中的办公资源详情
	 * @throws Exception
	 *             程序异常
	 */
	public Map<String, Object> allList(String eid) throws Exception;

	/**
	 * 实现采购单的数据增加处理
	 * 
	 * @param sup
	 *            要增加的采购单信息
	 * @return 保存成功返回true,否则返回false
	 * @throws Exception
	 *             程序异常
	 */
	public boolean add(Supply sup) throws Exception;

	/**
	 * 实现采购单的数据修改处理
	 * 
	 * @param sup
	 *            要修改的采购单信息
	 * @return 保存成功返回true,否则返回false
	 * @throws Exception
	 *             程序异常
	 */
	public boolean edit(Supply sup) throws Exception;

	/**
	 * 实现办一组公资源的删除
	 * 
	 * @param resids
	 *            要删除的办公资源ID
	 * @return 成功返回true
	 * @throws Exception
	 *             程序异常
	 */
	public boolean remove(Set<Long> resids) throws Exception;

	/**
	 * 传入一个雇员ID得到所有的采购申请单
	 * 
	 * @param eid
	 *            要查询的雇员ID
	 * @return 返回当前雇员的所有的采购申请单
	 * @throws Exception
	 *             程序异常
	 */
	public List<Supply> supplyList(String eid) throws Exception;

	/**
	 * 传入一个采购申请单ID得到该申请单下的所有办公资源
	 * 
	 * @param 要查询的采购申请单ID
	 * @return 返回当前采购申请单下的所有的办公资源
	 * @throws Exception
	 *             程序异常
	 */
	public List<Resource> applyList(Long spid) throws Exception;

	/**
	 * 根据一个雇员ID查询所有的申请单资源并且分页模糊查询
	 * 
	 * @param currentPage
	 *            当前页数
	 * @param lineSize
	 *            每页的行数
	 * @param column
	 *            要检索的字段
	 * @param keyWord
	 *            要检索的关键字
	 * @param eid
	 *            要查询的雇员
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> supplyEidList(Long currentPage, Integer lineSize, String column, String keyWord,
			String eid) throws Exception;
}
