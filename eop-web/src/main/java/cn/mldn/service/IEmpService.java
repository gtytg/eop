package cn.mldn.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.vo.Emp;

public interface IEmpService {
	/**
	 * 实现雇员的增加操作
	 * @param vo 要增加的雇员信息
	 * @return 增加成功返回ture，增加失败返回false
	 * @throws Exception 程序出现异常
	 */
	public boolean addEmp(Emp vo) throws Exception;
	/**
	 * 实现雇员信息的编辑操作
	 * @param vo 要编辑的雇员信息
	 * @return 编辑成功返回true，编辑失败返回false
	 * @throws Exception 程序出现异常
	 */
	public boolean editEmp(Emp vo) throws Exception;
	/**
	 * 实现雇员的删除操作
	 * @param ids 要删除的雇员编号集合
	 * @return 删除成功返回true，删除失败返回false
	 * @throws Exception 程序出现异常
	 */
	public boolean deleteEmp(Set<String> ids) throws Exception;
	
	/**
	 * 实现查询所有雇员信息的操作
	 * @return 返回查询雇员信息的list集合
	 * @throws Exception 程序出现异常
	 */
	public List<Emp> listEmp () throws Exception;
	/**
	 * 实现查询雇员信息的分页查询
	 * @param currentPage 当前页面
	 * @param lineSize 每页显示的数据条数
	 * @return 返回查询结果如下：
	 * 1、key=allRecorders、value=所有的数据行数
	 * 2、key=allEmps、value=所有的雇员
	 * @throws Exception 程序异常
	 */
	public Map<String,Object> listEmp (long currentPage,int lineSize) throws Exception;
	/**
	 * 实现雇员信息的关键字查询操作
	 * @param currentPage 当前页面
	 * @param lineSize 每页显示的数据条数
	 * @param column 要查询的字段名称
	 * @param keyWord 要查询的关键字
	 * @return 返回查询结果如下：
	 * 1、key=allRecorders、value=所有的数据行数
	 * 2、key=allEmps、value=查询出的雇员
	 * @throws Exception 程序异常
	 */
	public Map<String,Object> listEmp (long currentPage,int lineSize,String column,String keyWord) throws Exception;
	
	
	
	
	
}
