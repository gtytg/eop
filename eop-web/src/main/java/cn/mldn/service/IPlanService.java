package cn.mldn.service;

import cn.mldn.vo.Plan;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IPlanService {

    List<Long> deleteUnpublishedPlan(Set<Long> ids) throws Exception;

    /**
     * 查找数据字典中plan类型数据
     *
     * @param type plan类型
     * @return 返回如下数据
     * key = planList value = plan类型集合
     * @throws Exception 程序异常
     */
    Map<String, Object> getDictionaryType(String type) throws Exception;

    /**
     * 保存Plan对象
     *
     * @param vo Plan对象
     * @return
     * @throws Exception 程序异常
     */
    Map<String,Object> addPlan(Plan vo) throws Exception;

    /**
     * 根据pid删除数据
     *
     * @param ids
     * @return
     * @throws Exception
     */
    List<Long> getRemovePid(Set<Long> ids) throws Exception;

    /**
     * 进行未发布任务的分页查询，如果没有查询列或者查询关键字则进行整体查询
     *
     * @param currentPage 当前页
     * @param lineSize    当前页显示行数
     * @param column      查询字段
     * @param keyWord     查询关键字
     * @return
     * @throws Exception
     */
    Map<String, Object> UnpublishedPlanList(Long currentPage, Integer lineSize
            , String column, String keyWord,String eid) throws Exception;

    /**
     * 更新任务状态业务
     *
     * @param id pid查询更新
     * @return
     * @throws Exception
     */
    boolean updateStatus(long id) throws Exception;

    /**
     * 根据pid查询详情进行页面展示
     * key = empList value = 执行任务的员工
     * key = deptList value = dept集合
     * key = level value = leval集合
     * key = plan value = 任务详情
     *
     * @param id pid查询
     * @return
     * @throws Exception
     */
    Map<String, Object> getPlanDetails(Long id) throws Exception;

    /**
     * 根据eid进行计划表人员删除
     * key = amount value = 删除后更新的人数
     *
     * @param eid 删除人员id
     * @return
     * @throws Exception
     */
    Map<String, Integer> deletePlanPersonnel(String eid,Long pid) throws Exception;

    /**
     * 异步加载片段
     * key = deptList value = dept 集合
     *
     * @return
     * @throws Exception
     */
    Map<String, Object> planDetailsModal(String eid,Long pid) throws Exception;

    /**
     * 异步加载查询后片段
     * key = empList value = emp 集合
     * key = LevelList value = level 集合
     *
     * @param eid
     * @param did
     * @return
     * @throws Exception
     */
    Map<String, Object> getDidEmpList(String eid,Long did,Long pid) throws Exception;

    /**
     * 异步增加任务人员
     * key = emp value = emp对象
     * key = amount value = 增加后的人数
     * key = deptList value = dept集合
     * key = level value = leval集合
     * key = flag value = 返回增加或者失败的结果
     *
     * @param eid
     * @param pid
     * @return
     * @throws Exception
     */
    Map<String,Object> addDetails(String eid,Long pid) throws Exception;
}
