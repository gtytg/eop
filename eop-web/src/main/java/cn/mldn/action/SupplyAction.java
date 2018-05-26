package cn.mldn.action;

import java.util.Date;
import java.util.Set;

import cn.mldn.service.IResourceService;
import cn.mldn.service.ISupplyService;
import cn.mldn.util.web.ParameterUtil;
import cn.mldn.util.web.SplitPageUtil;
import cn.mldn.util.web.action.AbstractAction;
import cn.mldn.util.web.annotation.Autowired;
import cn.mldn.util.web.annotation.Controller;
import cn.mldn.util.web.annotation.RequestMapping;
import cn.mldn.util.web.servlet.ModelAndView;
import cn.mldn.util.web.servlet.ServletObjectUtil;
import cn.mldn.vo.Resource;
import cn.mldn.vo.Supply;
import com.sun.org.apache.xpath.internal.operations.Mod;

@Controller
@RequestMapping("/pages/back/admin/supply/*")
public class SupplyAction extends AbstractAction {

    @Autowired
    private ISupplyService supplyService;
    @Autowired
    private IResourceService resourceService;

    /**
     * 采购申请页面
     */
    @RequestMapping("supply_apply_add_pre")
    public ModelAndView addPre() {
        ModelAndView mav = new ModelAndView(super.getPage("supply_apply_add.page"));
        return mav;
    }

    /**
     * 进行采购表的增加业务
     */
    @RequestMapping("supply_apply_add")
    public ModelAndView add(Supply vo) throws Exception {
        vo.setEid(super.getEid());
        this.supplyService.add(vo);
        ModelAndView mav = new ModelAndView(super.getPage("supply_apply_list.action"));
        return mav;
    }

    /**
     * 当前员工的采购申请列表页面
     */
    @RequestMapping("supply_apply_list")
    public ModelAndView list() throws Exception {
        ModelAndView mav = new ModelAndView(super.getPage("supply_apply_list.page"));
        SplitPageUtil spu = new SplitPageUtil("申请标题:title"
                , super.getPageKey("supply_apply_list.action"));
        mav.addMap(this.supplyService.supplyEidList(spu.getCurrentPage()
                , spu.getLineSize(), spu.getColumn(), spu.getKeyword(), super.getEid()));
        return mav;
    }

    /**
     * 办公资源列表显示
     */
    @RequestMapping("supply_details_list")
    public ModelAndView detailsList(Long spid) throws Exception {
        ModelAndView mav = new ModelAndView(super.getPage("supply_details_list.page"));
        mav.add("spid", spid);
        mav.add("allResources", this.supplyService.applyList(spid));
        return mav;
    }

    /**
     * 商品采购增加页面
     */
    @RequestMapping("goods_add_pre")
    public ModelAndView goodsAddPre(String spid) throws Exception {
        ModelAndView mav = new ModelAndView(super.getPage("supply_goods_add.page"));
        mav.add("spid", spid);
        return mav;
    }

    /**
     * 商品增加业务
     */
    @RequestMapping("supply_goods_add")
    public void goodsAdd(Resource vo) throws Exception {
        try {
            Set<String> names = ServletObjectUtil.getParameter().createUUIDFileName("photo");
            ServletObjectUtil.getParameter().saveUpload("photo","/upload/resource");
            names.forEach((name) -> vo.setPhoto(name));
            this.resourceService.add(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
       ServletObjectUtil.getResponse().sendRedirect("/eop/pages/back/admin/supply/supply_details_list.action?spid=" + vo.getSpid());
        return;
    }

    /**
     * 商品修改页面
     */
    @RequestMapping("supply_goods_edit")
    public ModelAndView goodEdit(String title, Double price, int amount, String photo, String item, String note, Long spid) throws Exception {
        Resource res = new Resource();
        res.setStatus(0);
        res.setAppdate(new Date());
        res.setSpid(spid);
        res.setTitle(title);
        res.setPrice(price);
        res.setAmount(amount);
        res.setPhoto("123.jpg");
        res.setItem(item);
        res.setNote(note);
        this.resourceService.edit(res);
        ModelAndView mav = new ModelAndView(super.getPage("supply_details_list.page"));
        return mav;
    }
}
