package cn.mldn.action;

import java.util.ArrayList;
import java.util.Date;

import cn.mldn.service.IEmpService;
import cn.mldn.util.encrypt.EncryptUtil;
import cn.mldn.util.web.SplitPageUtil;
import cn.mldn.util.web.action.AbstractAction;
import cn.mldn.util.web.annotation.Autowired;
import cn.mldn.util.web.annotation.Controller;
import cn.mldn.util.web.annotation.RequestMapping;
import cn.mldn.util.web.servlet.ModelAndView;
import cn.mldn.util.web.servlet.ServletObjectUtil;
import cn.mldn.vo.Emp;

@Controller
@RequestMapping("/pages/back/admin/emp/*")
public class EmpAction extends AbstractAction {
    @Autowired
    private IEmpService empService;

    @RequestMapping("emp_add_pre")
    public ModelAndView empAddPre() throws Exception {
        ModelAndView mav = new ModelAndView(super.getPage("add.page"));
        return mav;
    }

    @RequestMapping("emp_add")
    public ModelAndView empAdd(Emp emp) throws Exception {
        ModelAndView mav = new ModelAndView(super.getForwardPage());
        String photo = null;
        if (ServletObjectUtil.getParameter().isUpload()) {
            photo = (String) new ArrayList(ServletObjectUtil.getParameter().createUUIDFileName("photo")).get(0);
            String imagePath = ServletObjectUtil.getRequest().getRealPath("/") + "upload/emp/" + photo;
            ServletObjectUtil.getParameter().saveUpload("photo", imagePath);
        } else {
            photo = "nophoto.png";
        }
        emp.setPhoto(photo);
        emp.setHiredate(new Date());
        emp.setPassword(EncryptUtil.encrypt(emp.getPassword()));
        if (this.empService.addEmp(emp)) {
            super.setUrlAndMsg(mav, super.getPageKey("list.action"), "emp.add.success");
        } else {
            super.setUrlAndMsg(mav, super.getPageKey("list.action"), "emp.add.false");
        }
        return mav;
    }

    @RequestMapping("emp_list")
    public ModelAndView empList() throws Exception {
        ModelAndView mav = new ModelAndView(super.getPage("list.page"));
        SplitPageUtil spu = new SplitPageUtil("姓名:ename", super.getPageKey("list.action"));
        mav.addMap(this.empService.listEmp(spu.getCurrentPage(), spu.getLineSize(), spu.getColumn(), spu.getKeyword()));
        return mav;
    }
}
