package cn.mldn.service.impl;

import cn.mldn.dao.*;
import cn.mldn.service.IPlanService;
import cn.mldn.util.service.abs.AbstractService;
import cn.mldn.util.web.annotation.Autowired;
import cn.mldn.util.web.annotation.Service;
import cn.mldn.vo.*;

import java.util.*;

@Service
public class PlanServiceImpl extends AbstractService implements IPlanService {

    @Autowired
    IEmpDAO empDAO;

    @Autowired
    IDeptDAO deptDAO;

    @Autowired
    IPlanDAO planDAO;

    @Autowired
    ILevelDAO levelDAO;

    @Autowired
    IDictionaryDAO dictionaryDAO;

    @Autowired
    IPlanDetailsDAO planDetailsDAO;

    @Override
    public List<Long> deleteUnpublishedPlan(Set<Long> ids) throws Exception {
        List<Long> list = planDAO.findByIdAndStatusList(ids);
        if (list.size() == ids.size()) {
            planDAO.doRemove(ids);
        }
        return list;
    }

    @Override
    public Map<String, Object> getDictionaryType(String type) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("planList", dictionaryDAO.findByType(type));
        return map;
    }

    @Override
    public Map<String, Object> addPlan(Plan vo) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        if (vo == null) {
            map.put("msg", "任务缺少必要填写信息");
        }
        if (planDAO.existsTitle(vo.getTitle())) {
            if (vo.getStarttime().compareTo(new Date()) >= 0) {
                if (vo.getStarttime().compareTo(vo.getEndtime()) < 1) {
                    if (planDAO.doCreate(vo)) {
                        map.put("msg", "任务创建成功！");
                    } else {
                        map.put("msg", "任务创建失败,您的信息填写不正确！");
                    }
                } else {
                    map.put("msg", "您选择的开始时间大于结束时间不可创建！");
                }
            } else {
                map.put("msg", "您选择的时间小于当前时间不可创建！");
            }
        } else {
            map.put("msg", "您创建的任务名字已有人创建请更换！");
        }
        return map;
    }

    @Override
    public List<Long> getRemovePid(Set<Long> ids) throws Exception {
        if (ids == null) {
            return null;
        }
        return planDAO.findByIdAndStatusList(ids);
    }

    @Override
    public Map<String, Object> UnpublishedPlanList(Long currentPage
            , Integer lineSize, String column, String keyWord, String eid) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (super.isSearch(column, keyWord)) {
            map.put("planList", this.planDAO.findEidSplit(currentPage, lineSize, column, keyWord
                    , eid, condition(column, "1")));
            map.put("allRecorders", this.planDAO.getEidAllCount(column, keyWord, eid, condition(column, "2")));
        } else {
            map.put("planList", this.planDAO.findEidSplit(currentPage, lineSize, eid, condition("", "3")));
            map.put("allRecorders", this.planDAO.getEidAllCount(eid, condition("", "4")));
        }
        return map;
    }

    private static String condition(String column, String status) throws Exception {
        switch (status) {
            case "1":
                return "select p.pid,p.title,p.item,p.starttime,p.endtime,p.status,p.amount,p.note,p.eid from" +
                        "(select pid,title,item,starttime,endtime,status,amount,note,eid " +
                        "from plan where eid = ? and " + column + " like ?" +
                        "UNION ALL (select pid,title,item,starttime,endtime,status,amount,note,eid " +
                        "from plan where eid != ?" +
                        " and status = 1 and " + column + " like ? ORDER BY status))as p ";
            case "2":
                return "select count(*) from(select pid,title,item,starttime,endtime,status,amount,note,eid " +
                        "from plan where eid = ? and " + column + " like ?" +
                        "UNION ALL (select pid,title,item,starttime,endtime,status,amount,note,eid " +
                        "from plan where eid != ?" +
                        " and status = 1 and " + column + " like ? ORDER BY status))as plan ";
            case "3":
                return "select pid,title,item,starttime,endtime,status,amount,note,eid " +
                        " from plan where eid = ? or status = 1 ORDER BY status ";
            case "4":
                return "SELECT COUNT(*) FROM plan where eid = ?  or status = 1 ORDER BY status ";
        }
        return null;
    }

    @Override
    public boolean updateStatus(long id) throws Exception {
        if (id == 0) {
            return false;
        }
        return planDAO.updateStatus(id);
    }

    @Override
    public Map<String, Object> getPlanDetails(Long pid) throws Exception {
        if (pid == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        List<String> pidList = planDetailsDAO.findByPid(pid);
        if (pidList.size() != 0) {
            List<Emp> empList = empDAO.getByEidList(pidList);
            map.put("levelList", levelDAO.findAll());
            map.put("empList", empList);
        }
        map.put("deptList", deptDAO.findAll());
        map.put("plan", planDAO.findById(pid));
        return map;
    }

    @Override
    public Map<String, Integer> deletePlanPersonnel(String eid, Long pid) throws Exception {
        if (eid == null || "".equals(eid)) {
            return null;
        }
        HashMap<String, Integer> map = new HashMap<>();
        if (planDetailsDAO.getEidRemove(eid)) {
            planDAO.updateAmount(-1L, pid);
            map.put("amount", planDAO.findByAmount(pid));
        }
        return map;
    }

    @Override
    public Map<String, Object> planDetailsModal(String eid, Long pid) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("pidList", planDetailsDAO.existsEid(pid));
        map.put("levelList", levelDAO.findAll());
        map.put("deptList", deptDAO.findAll());
        map.put("empList", empDAO.getBylEidList(eid));
        return map;
    }

    @Override
    public Map<String, Object> getDidEmpList(String eid, Long did, Long pid) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("levelList", levelDAO.findAll());
        map.put("pidList", planDetailsDAO.existsEid(pid));
        if (did == 9999) {
            map.put("empList", empDAO.getBylEidList(eid));
        } else {
            map.put("empList", empDAO.getDidEmpList(eid, did));
        }
        return map;
    }

    @Override
    public Map<String, Object> addDetails(String eid, Long pid) throws Exception {
        boolean flag = false;
        HashMap<String, Object> map = new HashMap<>();
        if (planDetailsDAO.currentPidExists(eid, pid)) {
            if (planDetailsDAO.findByEidAndPid(eid, pid) == 0L) {
                PlanDetails vo = new PlanDetails();
                vo.setEid(eid);
                vo.setPid(pid);
                Emp emp = empDAO.findById(eid);
                flag = planDetailsDAO.doCreate(vo);
                planDAO.updateAmount(1L, pid);
                Plan plan = planDAO.findById(pid);
                map.put("emp", emp);
                map.put("amount", plan.getAmount());
                map.put("pidList", planDetailsDAO.existsEid(pid));
                map.put("deptList", deptDAO.findAll());
                map.put("levelList", levelDAO.findAll());
            }
        }
        map.put("flag", flag);
        return map;
    }
}
