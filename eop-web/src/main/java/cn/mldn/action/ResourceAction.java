package cn.mldn.action;

import cn.mldn.service.IResourceService;
import cn.mldn.util.web.SplitPageUtil;
import cn.mldn.util.web.action.AbstractAction;
import cn.mldn.util.web.annotation.Autowired;
import cn.mldn.util.web.annotation.Controller;
import cn.mldn.util.web.annotation.RequestMapping;
import cn.mldn.util.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages/back/admin/resource/*")
public class ResourceAction extends AbstractAction {

    @Autowired
    private IResourceService resourceService;

    @RequestMapping("resource_list")
    public ModelAndView list() throws Exception {
        ModelAndView mav = new ModelAndView(super.getPage("list.page"));
        //"商品名称:title"是确认需要模糊查询的字段
        SplitPageUtil spu = new SplitPageUtil("商品名称:title", super.getPageKey("list.action"));
        mav.addMap(this.resourceService.list(spu.getColumn(), spu.getKeyword(), spu.getCurrentPage(), spu.getLineSize()));
        return mav;
    }
}

