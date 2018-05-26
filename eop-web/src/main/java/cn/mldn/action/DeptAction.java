package cn.mldn.action;

import com.alibaba.fastjson.JSON;

import cn.mldn.service.IDeptService;
import cn.mldn.util.web.SplitPageUtil;
import cn.mldn.util.web.action.AbstractAction;
import cn.mldn.util.web.annotation.Autowired;
import cn.mldn.util.web.annotation.Controller;
import cn.mldn.util.web.annotation.RequestMapping;
import cn.mldn.util.web.servlet.ModelAndView;
import cn.mldn.vo.Dept;

@Controller
@RequestMapping("/pages/back/admin/dept/*")
public class DeptAction extends AbstractAction {
    @Autowired
    private IDeptService deptService;

    /**
     * 通过action路径跳转到部门增加页面
     *
     * @return 部门增加的JSP页面
     * @throws Exception 程序异常
     */
    @RequestMapping("dept_add_pre")
    public String deptAddPre() throws Exception {
        return super.getPage("add.page");
    }

    /**
     * 进行部门增加操作
     *
     * @param dept 要增加的部门基本信息
     * @return 返回到部门列表页面
     * @throws Exception 程序异常
     */
    @RequestMapping("dept_add")
    public ModelAndView deptAdd(Dept dept) throws Exception {
        ModelAndView mav = new ModelAndView(super.getForwardPage());
        dept.setCurrnum(0);
        if (this.deptService.addDept(dept)) {
            super.setUrlAndMsg(mav, super.getPageKey("list.action"), "dept.add.success");
        } else {
            super.setUrlAndMsg(mav, super.getPageKey("list.action"), "dept.add.false");
        }
        return mav;
    }


    @RequestMapping("dept_edit_pre")
    public String deptEditPre() throws Exception {
        return super.getPage("edit.page");
    }

    @RequestMapping("dept_edit")
    public ModelAndView deptEdit(long did) throws Exception {
        ModelAndView mav = new ModelAndView(super.getForwardPage());
//		dept.setCurrnum(0);
//        if (this.deptService.addDept(did)) {
//        	super.setUrlAndMsg(mav, super.getPageKey("list.action"), "dept.add.success");
//        } else {
//        	super.setUrlAndMsg(mav, super.getPageKey("list.action"), "dept.add.false");
//        }
        return mav;
    }

    @RequestMapping("list_dept")
    public void listDept() throws Exception {
        super.print(JSON.toJSONString(this.deptService.listDept()));
    }

    /**
     * 跳转到部门列表页面
     *
     * @return 部门列表页面的地址
     * @throws Exception 程序异常
     */
    @RequestMapping("dept_list")
    public ModelAndView planList() throws Exception {
        ModelAndView mav = new ModelAndView(super.getPage("list.page"));
        SplitPageUtil spu = new SplitPageUtil("", super.getPageKey("list.action"));
        mav.addMap(this.deptService.listDept(spu.getCurrentPage(), spu.getLineSize()));
        return mav;
    }
}
