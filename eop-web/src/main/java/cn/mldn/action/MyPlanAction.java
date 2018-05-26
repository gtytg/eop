package cn.mldn.action;

import cn.mldn.service.IMyPlanService;
import cn.mldn.service.IPlanService;
import cn.mldn.util.web.SplitPageUtil;
import cn.mldn.util.web.action.AbstractAction;
import cn.mldn.util.web.annotation.Autowired;
import cn.mldn.util.web.annotation.Controller;
import cn.mldn.util.web.annotation.RequestMapping;
import cn.mldn.util.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/pages/back/admin/plan/*")
public class MyPlanAction extends AbstractAction {

    @Autowired
    IMyPlanService myPlanService;

    @Autowired
    IPlanService planService;


    @RequestMapping("my_plan_pre")
    public ModelAndView myPlanPre() {
        ModelAndView mav = new ModelAndView(super.getPage("my.plan.list.page"));
        SplitPageUtil spu = new SplitPageUtil("任务类型:item|任务名称:title|任务发布人:eid"
                , super.getPageKey("my.plan.list.action"));
        try {
            mav.addMap(this.myPlanService.myPlanList(spu.getCurrentPage(), spu.getLineSize(), spu.getColumn(), spu.getKeyword(), super.getEid()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    @RequestMapping("my_plan")
    public void myPlan(Long pid) throws Exception {
        super.print(JSON.toJSON(this.planService.getPlanDetails(pid)));
    }
}
