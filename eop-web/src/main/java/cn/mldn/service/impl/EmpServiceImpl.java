package cn.mldn.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.dao.IEmpDAO;
import cn.mldn.service.IEmpService;
import cn.mldn.util.service.abs.AbstractService;
import cn.mldn.util.web.annotation.Autowired;
import cn.mldn.util.web.annotation.Service;
import cn.mldn.vo.Emp;

@Service
public class EmpServiceImpl extends AbstractService implements IEmpService {
    @Autowired
    private IEmpDAO empDAO;

    @Override
    public boolean addEmp(Emp vo) throws Exception {
        boolean flag = false;
        if (vo == null) {
            return flag;
        }
        if (this.empDAO.findById(vo.getEid()) == null) {
            flag = this.empDAO.doCreate(vo);
            return flag;
        } else {
            return flag;
        }
    }

    @Override
    public boolean editEmp(Emp vo) throws Exception {
        if (this.empDAO.findById(vo.getEid()) == null) {
            return false;
        } else {
            return this.empDAO.doEdit(vo);
        }
    }

    @Override
    public List<Emp> listEmp() throws Exception {
        List<Emp> list = this.empDAO.findAll();
        return list;
    }

    @Override
    public Map<String, Object> listEmp(long currentPage, int lineSize) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("allEmps", this.empDAO.findSplit(currentPage, lineSize));
        map.put("allRecorders", this.empDAO.getAllCount());
        return map;
    }

    @Override
    public Map<String, Object> listEmp(long currentPage, int lineSize, String column, String keyWord) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if (column == null || "".equals(column) || keyWord == null || "".equals(keyWord)) {
            map.put("allEmps", this.empDAO.findSplit(currentPage, lineSize));
            map.put("allRecorders", this.empDAO.getAllCount());
        } else {
            map.put("allEmps", this.empDAO.findSplit(currentPage, lineSize, column, keyWord));
            map.put("allRecorders", this.empDAO.getAllCount(column, keyWord));
        }
        return map;
    }

    @Override
    public boolean deleteEmp(Set<String> ids) throws Exception {
        if (ids == null || ids.size() == 0) {
            return false;
        } else {
            return this.empDAO.doRemove(ids);
        }
    }

}
