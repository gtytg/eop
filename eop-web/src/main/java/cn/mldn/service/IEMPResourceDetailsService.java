package cn.mldn.service;

import java.util.Map;

import cn.mldn.vo.EMPResourceDetails;

public interface IEMPResourceDetailsService {
	/**
	 * 实现办公资源待领取列表的增加处理
	 * @param emprd 要保存的商品信息
	 * @return 保存成功返回true，否则返回false
	 * @throws Exception 程序异常
	 */
	public boolean add(EMPResourceDetails emprd) throws Exception ;

	/**
	 * 根据指定雇员资源申请ID列出对应的所有商品列表中的全部数据信息
	 * @param eresid 要查找的雇员资源申请ID
	 * @return 返回有如下的信息内容：
	 * 1、key = allEMPResourceDetails、value = 【List集合】雇员对应的商品列表中的所有商品记录
	 * 2、key = allResource、value = 【Map集合】用户商品列表中的全部商品信息
	 * @throws Exception 程序异常
	 */
	public Map<String, Object>list(Long eresid)throws Exception ;
}
