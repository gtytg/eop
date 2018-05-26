package cn.mldn.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.mldn.dao.IDeptDAO;
import cn.mldn.service.IDeptService;
import cn.mldn.util.service.abs.AbstractService;
import cn.mldn.util.web.annotation.Autowired;
import cn.mldn.util.web.annotation.Service;
import cn.mldn.vo.Dept;
@Service
public class DeptServiceImpl extends AbstractService implements IDeptService {
	@Autowired
	private IDeptDAO deptDAO ;
	@Override
	public boolean addDept(Dept vo) throws Exception {
		if (this.deptDAO.findByDname(vo.getDname()) == null) {
			return this.deptDAO.doCreate(vo);
		}
		return false;
	}
	@Override
	public Map<String, Object> listDept(long currentPage, int lineSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allDepts", this.deptDAO.findSplit(currentPage, lineSize));
		map.put("allRecorders", this.deptDAO.getAllCount());
		return map;
	}
	@Override
	public List<Dept> listDept() throws Exception {
		return this.deptDAO.findAll();
	}

}
