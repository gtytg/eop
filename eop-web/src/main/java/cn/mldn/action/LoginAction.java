package cn.mldn.action;

import cn.mldn.service.ILoginServile;
import cn.mldn.util.bean.ResourceUtil;
import cn.mldn.util.encrypt.EncryptUtil;
import cn.mldn.util.web.CookieUtil;
import cn.mldn.util.web.action.AbstractAction;
import cn.mldn.util.web.annotation.Autowired;
import cn.mldn.util.web.annotation.Controller;
import cn.mldn.util.web.annotation.RequestMapping;
import cn.mldn.util.web.servlet.ModelAndView;
import cn.mldn.util.web.servlet.ServletObjectUtil;
import cn.mldn.vo.Action;
import cn.mldn.vo.Emp;
import cn.mldn.vo.Role;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller

public class LoginAction extends AbstractAction {

    public static final String ACTION_TITLE = "用户";

    @Autowired
    ILoginServile loginServile;

    /**
     * 登录页面
     */
    @RequestMapping("/login_pre")
    public ModelAndView login_pre() {
        ModelAndView mav = new ModelAndView("/login.jsp");
        return mav;
    }

    /**
     * 用户登录注销，登录注销后所有的Cookie信息将被删除
     *
     * @return 提示页面，随后跳转回登录页
     */
    @RequestMapping("/logout")
    public ModelAndView logout() {
        ModelAndView mav = new ModelAndView(super.getForwardPage());
        CookieUtil cookieUtil = new CookieUtil(ServletObjectUtil.getRequest(), ServletObjectUtil.getResponse());
        cookieUtil.cleanEid();
        ServletObjectUtil.getRequest().getSession().invalidate();
        super.setUrlAndMsg(mav, "login.action", "logout.success", ACTION_TITLE);
        return mav;
    }

    /**
     * 登录业务请求
     */
    @RequestMapping("/login")
    public ModelAndView login(Emp vo) {
        ModelAndView mav = new ModelAndView(super.getPage("login.action"));
        vo.setPassword(EncryptUtil.encrypt(vo.getPassword()));
        try {
            Map<String, Object> map = loginServile.login(vo);
            Emp emp = (Emp) map.get("emp");
            List<Role> roleList = (List<Role>) map.get("roleList");
            List<Action> actionList = (List<Action>) map.get("actionList");
            if (emp != null) {
                Long time = null;
                if (emp.getLastDate() != null) {
                    time = emp.getLastDate().getTime();
                }
                if (time != null) {
                    ServletObjectUtil.getRequest().getSession().setAttribute("lastdate",
                            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time));
                }
                ServletObjectUtil.getRequest().getSession().setAttribute("eid", vo.getEid());
                ServletObjectUtil.getRequest().getSession().setAttribute("ename", emp.getEname());
                ServletObjectUtil.getRequest().getSession().setAttribute("lid", emp.getLid());
                ServletObjectUtil.getRequest().getSession().setAttribute("roleList", roleList);
                ServletObjectUtil.getRequest().getSession().setAttribute("actionList", actionList);
                CookieUtil cookieUtil = new CookieUtil(ServletObjectUtil.getRequest(),
                        ServletObjectUtil.getResponse());
                cookieUtil.saveEid(vo.getEid());
                mav.setPath(ResourceUtil.getPage("forward.page"));
                super.setUrl(mav, "index.action");
                super.setMsg(mav, "login.success");
            } else {
                super.setMsg(mav, "login.fail");
            }
        } catch (Exception e) {
            super.setMsg(mav, "login.fail");
            e.printStackTrace();
        }
        return mav;
    }

    /**
     * 验证码检测，用于ajax异步验证处理
     */
    @RequestMapping("/code_check")
    public void check(String code) {
        String rand = (String) ServletObjectUtil.getRequest().getSession().getAttribute("rand");
        if (rand == null || "".equals(rand)) {
            super.print(false);
        } else {
            super.print(rand.equalsIgnoreCase(code));
        }
    }
}
