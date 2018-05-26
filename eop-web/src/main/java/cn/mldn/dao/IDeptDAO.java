package cn.mldn.dao;

import cn.mldn.util.dao.IDAO;
import cn.mldn.vo.Dept;

public interface IDeptDAO extends IDAO<Long, Dept> {
	/**
	 * 根据输入的部门名称查找部门是否已经存在
	 * @param name 输入的部门名称
	 * @return 如果已经存在返回dept实例化对象，如果不存在返回null
	 * @throws Exception 程序出现异常
	 */
	public Dept findByDname (String dname) throws Exception ;
}
