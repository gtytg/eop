package cn.mldn.dao;

import cn.mldn.util.dao.IDAO;
import cn.mldn.vo.Action;

import java.util.List;

public interface IActionDAO extends IDAO<String, Action> {

    List<Action> getActionList(List<String> rid) throws Exception;
}
