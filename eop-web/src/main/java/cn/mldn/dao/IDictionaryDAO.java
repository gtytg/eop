package cn.mldn.dao;

import cn.mldn.util.dao.IDAO;
import cn.mldn.vo.Dictionary;

import java.sql.SQLException;
import java.util.List;


public interface IDictionaryDAO extends IDAO<Long, Dictionary> {

    /**
     * 根据数字字典类型查询详情
     *
     * @param type 查询类型
     * @return 返回Dictionary对象
     * @throws SQLException 异常抛出
     */
    List<Dictionary> findByType(String type) throws SQLException;
}
