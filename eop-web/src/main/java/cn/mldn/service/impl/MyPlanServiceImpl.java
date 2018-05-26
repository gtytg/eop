package cn.mldn.service.impl;

import cn.mldn.dao.IPlanDAO;
import cn.mldn.service.IMyPlanService;
import cn.mldn.util.service.abs.AbstractService;
import cn.mldn.util.web.annotation.Autowired;
import cn.mldn.util.web.annotation.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MyPlanServiceImpl extends AbstractService implements IMyPlanService {

    @Autowired
    IPlanDAO planDAO;

    @Override
    public Map<String, Object> myPlanList(Long currentPage, Integer lineSize
            , String column, String keyWord, String eid) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (super.isSearch(column, keyWord)) {
            map.put("myPlanList", this.planDAO.findMyEidSplit(currentPage, lineSize, column, keyWord
                    , eid, condition(column, "1")));
            map.put("allRecorders", this.planDAO.getMyEidAllCount(column, keyWord, eid, condition(column, "2")));
        } else {
            map.put("myPlanList", this.planDAO.findEidSplit(currentPage, lineSize, eid, condition("", "3")));
            map.put("allRecorders", this.planDAO.getEidAllCount(eid, condition("", "4")));
        }
        return map;
    }

    private static String condition(String column, String status) throws Exception {
        switch (status) {
            case "1":
                return "select p.pid,p.title,p.item,p.starttime,p.endtime,p.status,p.amount,p.note,p.eid " +
                        "from plan_details pd,plan p where pd.pid = p.pid and pd.eid = ? and "+column+" like ?";
            case "2":
                return "select count(*) from plan_details pd,plan p " +
                        "where pd.pid = p.pid and pd.eid = ? and "+column+" like ?";
            case "3":
                return "select p.pid,p.title,p.item,p.starttime,p.endtime,p.status,p.amount,p.note,p.eid" +
                        " from plan_details pd,plan p where pd.pid = p.pid and pd.eid = ? ";
            case "4":
                return "SELECT COUNT(*) from plan_details pd,plan p where pd.pid = p.pid and pd.eid = ? ";
        }
        return null;
    }
}
