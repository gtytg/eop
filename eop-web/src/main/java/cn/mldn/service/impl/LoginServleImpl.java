package cn.mldn.service.impl;

import cn.mldn.dao.IActionDAO;
import cn.mldn.dao.IEmpDAO;
import cn.mldn.dao.IRoleDAO;
import cn.mldn.service.ILoginServile;
import cn.mldn.util.service.abs.AbstractService;
import cn.mldn.util.web.annotation.Autowired;
import cn.mldn.util.web.annotation.Service;
import cn.mldn.vo.Action;
import cn.mldn.vo.Emp;
import cn.mldn.vo.Role;

import java.util.*;

@Service
public class LoginServleImpl extends AbstractService implements ILoginServile {

    @Autowired
    private IEmpDAO empDAO;

    @Autowired
    private IRoleDAO roleDAO;

    @Autowired
    private IActionDAO actionDAO;

    @Override
    public Map<String, Object> login(Emp vo) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();
        Emp emp = empDAO.findById(vo.getEid());
        Date current = new Date();
        if (emp != null) {
            if (emp.getPassword().equals(vo.getPassword())) {
                empDAO.updateLastDate(vo.getEid(), current);
                List<Role> roleList = roleDAO.getRoleList(vo.getEid());
                roleList.forEach((role) -> list.add(role.getRid()));
                List<Action> actionList = actionDAO.getActionList(list);
                map.put("emp", emp);
                map.put("roleList", roleList);
                map.put("actionList", actionList);
            }
        }
        return map;
    }
}
