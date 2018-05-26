package cn.mldn.action;

import cn.mldn.service.IUserService;
import cn.mldn.util.web.CookieUtil;
import cn.mldn.util.web.action.AbstractAction;
import cn.mldn.util.web.annotation.Autowired;
import cn.mldn.util.web.annotation.Controller;
import cn.mldn.util.web.annotation.RequestMapping;
import cn.mldn.util.web.servlet.ModelAndView;
import cn.mldn.util.web.servlet.ServletObjectUtil;

@Controller
@RequestMapping("/pages/back/*")
public class UserAction extends AbstractAction {

    @Autowired
    IUserService userService;

    /**
     * 用户首页
     */
    @RequestMapping("index")
    public ModelAndView index(){
            ModelAndView mav = new ModelAndView("/pages/back/index.jsp");
        return mav;
    }

    /**
     * 修改密码页面
     */
    @RequestMapping("update_password_pre")
    public ModelAndView updatePasswordPre() {
        ModelAndView mav = new ModelAndView(super.getPage("password.update.page"));
        return mav;
    }

    /**
     * 进行密码修改业务
     */
    @RequestMapping("update_password")
    public ModelAndView updatePassword() throws Exception {
        String pwd = ServletObjectUtil.getRequest().getParameter("pwd");
        String newPwd = ServletObjectUtil.getRequest().getParameter("newpwd");
        boolean flag = userService.exitPassword(pwd, newPwd, super.getEid());
        CookieUtil cookieUtil = new CookieUtil(ServletObjectUtil.getRequest(), ServletObjectUtil.getResponse());
        cookieUtil.cleanEid();
        ServletObjectUtil.getRequest().getSession().invalidate();
        ModelAndView mav = new ModelAndView("/pages/plugins/back/back_forward.jsp");
        if (flag) {
            super.setUrlAndMsg(mav, "login.action", "password.update.success");
        } else {
            super.setUrlAndMsg(mav, "login.action", "password.update.fail");
        }
        return mav;
    }
}
