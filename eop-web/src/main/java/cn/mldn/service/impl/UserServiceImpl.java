package cn.mldn.service.impl;

import cn.mldn.dao.IEmpDAO;
import cn.mldn.service.IUserService;
import cn.mldn.util.encrypt.EncryptUtil;
import cn.mldn.util.service.abs.AbstractService;
import cn.mldn.util.web.annotation.Autowired;
import cn.mldn.util.web.annotation.Service;
import cn.mldn.vo.Emp;

@Service
public class UserServiceImpl extends AbstractService implements IUserService {

    @Autowired
    private IEmpDAO empDAO;

    @Override
    public boolean exitPassword(String oldpwd, String newpwd, String eid) throws Exception {
        if ("".equals(eid) || eid == null || oldpwd == null || "".equals(oldpwd) || "".equals(newpwd) || newpwd == null) {
            return false;
        }
        boolean flag = false;
        Emp emp = empDAO.findById(eid);
        if (EncryptUtil.encrypt(oldpwd).equals(emp.getPassword())) {
            flag = empDAO.updatePassword(EncryptUtil.encrypt(newpwd), eid);
        }
        return flag;
    }
}
