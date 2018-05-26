package cn.mldn.service;

import java.util.List;
import java.util.Map;

import cn.mldn.vo.Dept;

public interface IDeptService {
	/**
	 * 实现部门数据的增加
	 * @param vo 数据增加
	 * @return 部门增加成功返回true，增加失败返回false
	 * @throws Exception 程序异常
	 */
	public boolean addDept(Dept vo) throws Exception ;
	/**
	 * 实现部门列表显示
	 * @param currentPage 当前页
	 * @param lineSize 每页行数
	 * @return 返回如下结果：
	 * 1、key=allCount、value=所有部门个数
	 * 2、key=allDepts、value=所有部门
	 * @throws Exception 程序异常
	 */
	public Map<String,Object> listDept(long currentPage, int lineSize) throws Exception;
	/**
	 * 查出所有部门信息
	 * @return 部门信息集合
	 * @throws Exception 程序出错
	 */
	public List<Dept> listDept() throws Exception;
	
}
