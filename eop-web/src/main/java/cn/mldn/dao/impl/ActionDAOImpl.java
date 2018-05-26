package cn.mldn.dao.impl;

import cn.mldn.dao.IActionDAO;
import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.util.web.annotation.Repository;
import cn.mldn.vo.Action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class ActionDAOImpl extends AbstractDAO implements IActionDAO {

    @Override
    public boolean doCreate(Action vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doEdit(Action vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemove(Set<String> ids) throws SQLException {
        return false;
    }

    @Override
    public Action findById(String id) throws SQLException {
        return null;
    }

    @Override
    public List<Action> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<Action> findSplit(Long currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    @Override
    public List<Action> findSplit(Long currentPage, Integer lineSize, String column, String keyWord) throws SQLException {
        return null;
    }

    @Override
    public Long getAllCount() throws SQLException {
        return null;
    }

    @Override
    public Long getAllCount(String column, String keyWord) throws SQLException {
        return null;
    }

    @Override
    public List<Action> getActionList(List<String> rid) throws Exception {
        ArrayList<Action> list = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select actid,rid,title from action where rid in(");
        rid.forEach((id) -> sql.append("'").append(id).append("'").append(","));
        sql.delete(sql.length() - 1, sql.length()).append(")");
        super.pstmt = super.conn.prepareStatement(sql.toString());
        ResultSet rs = super.pstmt.executeQuery();
        while(rs.next()){
            Action vo = new Action();
            vo.setActid(rs.getString(1));
            vo.setRid(rs.getString(2));
            vo.setTitle(rs.getString(3));
            list.add(vo);
        }
        return list;
    }
}
