package cn.mldn.service;

import java.util.Map;

import cn.mldn.vo.Resource;


public interface IResourceService {

	/**
	 * 实现办公资源表的增加
	 * 
	 * @param res
	 *            要增加的资源数据
	 * @return 增加成功返回true
	 * @throws Exception
	 *             程序异常
	 */
	public boolean add(Resource res) throws Exception;
	
	/**
	 * 实现办公资源表的修改
	 * 
	 * @param res
	 *            要修改的资源数据
	 * @return 增加成功返回true
	 * @throws Exception
	 *             程序异常
	 */
	public boolean edit(Resource res) throws Exception;
	
	
	public Map<String, Object> list(String column, String keyWord, long currentPage, int lineSize) throws Exception;
}
