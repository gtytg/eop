package cn.mldn.action;

import cn.mldn.service.IPlanService;
import cn.mldn.util.web.SplitPageUtil;
import cn.mldn.util.web.action.AbstractAction;
import cn.mldn.util.web.annotation.Autowired;
import cn.mldn.util.web.annotation.Controller;
import cn.mldn.util.web.annotation.RequestMapping;
import cn.mldn.util.web.servlet.ModelAndView;
import cn.mldn.vo.Plan;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/pages/back/admin/plan/*")
public class PlanAction extends AbstractAction {

    @Autowired
    IPlanService planService;

    /**
     * 实现任务调度前的处理
     */
    @RequestMapping("plan_add_pre")
    public ModelAndView planAddPre() throws Exception {
        ModelAndView mav = new ModelAndView(super.getPage("add.page"));
        mav.addMap(this.planService.getDictionaryType("plan"));
        return mav;
    }

    /**
     * 进行增加业务
     */
    @RequestMapping("plan_add")
    public ModelAndView planAdd(Plan plan) throws Exception {
        plan.setStatus(0);
        plan.setAmount(0);
        plan.setEid(super.getEid());
        ModelAndView mav =  new ModelAndView(super.getPage("add.action"));
        mav.addMap(this.planService.addPlan(plan));
        return mav;
    }

    /**
     * 列表页面显示
     */
    @RequestMapping("plan_list")
    public ModelAndView planList() throws Exception {
        ModelAndView mav = new ModelAndView(super.getPage("list.page"));
        SplitPageUtil spu = new SplitPageUtil("任务类型:item|任务名称:title|任务发布人:eid", super.getPageKey("list.action"));
        try {
            mav.addMap(this.planService.UnpublishedPlanList(spu.getCurrentPage(), spu.getLineSize(), spu.getColumn(), spu.getKeyword(), super.getEid()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    /**
     * 处理未发布任务业务
     */
    @RequestMapping("delete_plan")
    public void deletePlan(long[] pids) throws Exception {
        Set<Long> gids = super.handleLongArrayToSet(pids); // 进行ids的数据处理
        Map<String, Object> result = new HashMap<>();
        result.put("pid", this.planService.deleteUnpublishedPlan(gids));
        Object o = JSON.toJSON(result);
        super.print(o);
    }

    /**
     * 更新任务状态业务
     */
    @RequestMapping("update_status")
    public void updateStatus(long misid) throws Exception {
        Map<String, Object> result = new HashMap<>();
        result.put("flag", this.planService.updateStatus(misid));
        super.print(JSON.toJSON(result));
    }

    /**
     * 获取发布任务详情页面
     */
    @RequestMapping("get_plan_details")
    public ModelAndView getPlanDetails(long pid) throws Exception {
        ModelAndView mav = new ModelAndView(super.getPage("details.page"));
        mav.addMap(this.planService.getPlanDetails(pid));
        return mav;
    }

    /**
     * 异步删除任务人员
     */
    @RequestMapping("delete_plan_personnel")
    public void deletePlanPersonnel(String eid, Long pid) throws Exception {
        super.print(JSON.toJSON(this.planService.deletePlanPersonnel(eid, pid)));
    }

    /**
     * 异步加载片段
     */
    @RequestMapping("plan_details_modal")

    public void planDetailsModal(Long pid) throws Exception {
        super.print(JSON.toJSON(this.planService.planDetailsModal(super.getEid(), pid)));
    }

    /**
     * 异步查询部门可用人数
     */
    @RequestMapping("get_did_emp_list")
    public void getDidEmpList(Long did, Long pid) throws Exception {
        super.print(JSON.toJSON(this.planService.getDidEmpList(super.getEid(), did, pid)));
    }

    /**
     * 异步增加任务人员
     */
    @RequestMapping("emp_plan_add")
    public void empPlanAdd(String eid, Long pid) throws Exception {
        super.print(JSON.toJSON(this.planService.addDetails(eid, pid)));
    }
}
