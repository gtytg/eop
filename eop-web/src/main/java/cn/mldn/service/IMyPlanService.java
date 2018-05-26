package cn.mldn.service;

import java.util.Map;

public interface IMyPlanService {

    /**
     * 进行当前用户的任务分页显示
     *
     * @param currentPage
     * @param lineSize
     * @param column
     * @param keyWord
     * @param eid
     * @return
     * @throws Exception
     */
    Map<String, Object> myPlanList(Long currentPage, Integer lineSize
            , String column, String keyWord,String eid) throws Exception;
}
