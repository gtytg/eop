package cn.mldn.dao;

import java.sql.SQLException;
import java.util.List;

import cn.mldn.util.dao.IDAO;
import cn.mldn.vo.EMPResourceDetails;

public interface IEMPResourceDetailsDAO extends IDAO<Long, EMPResourceDetails>{
	
	/**
	 * 根据雇佣资源申请编号获取当前用户的商品列表全部信息
	 * @param eresid 雇佣资源申请ID
	 * @return 当前雇佣资源的商品列表信息
	 * @throws SQLException 程序异常
	 */
	public List<EMPResourceDetails> findAllByEMPResourceDetails(Long eresid)throws Exception ;
	/**
	 * 根据雇员资源申请和办公资源获取一个商品列表信息
	 * @param eresid 雇员资源申请ID
	 * @param resid 赶工资源ID
	 * @return 如果该信息存在则返回VO对象，否则返回null
	 * @throws Exception 程序异常
	 */
	public EMPResourceDetails findByEMPResourceDetailsAndEMPResource(Long eresid,Long resid)throws Exception ;
	
	/**
	 * 根据商品列表的保存信息编号进行商品数量的变更处理
	 * @param eredid 资源详情ID
	 * @param amount 要更新的数量，在已有的数量上进行叠加
	 * @return 更新成功返回true
	 * @throws Exception 程序异常
	 */
	public boolean doEditAmount(Long eredid,Integer amount)throws Exception ;
}
