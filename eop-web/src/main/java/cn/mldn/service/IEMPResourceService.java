package cn.mldn.service;

import cn.mldn.vo.EMPResource;

public interface IEMPResourceService {

	/**
	 * 实现商品的增加处理
	 * @param res 要保存的商品信息
	 * @return 保存成功返回true，失败返回false
	 * @throws Exception 程序异常
	 */
	public boolean add(EMPResource res)throws Exception ;
}
