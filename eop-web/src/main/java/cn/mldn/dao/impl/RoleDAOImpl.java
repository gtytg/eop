package cn.mldn.dao.impl;

import cn.mldn.dao.IRoleDAO;
import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.util.web.annotation.Repository;
import cn.mldn.vo.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDAOImpl extends AbstractDAO implements IRoleDAO {
    @Override
    public List<Role> getRoleList(String eid) throws Exception {
        ArrayList<Role> list = new ArrayList<>();
        String sql = "SELECT rid,title FROM role where rid in " +
                "(select rid from dept_role where did = (SELECT did FROM emp where eid = ?))";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, eid);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Role vo = new Role();
            vo.setRid(rs.getString(1));
            vo.setTitle(rs.getString(2));
            list.add(vo);
        }
        return list;
    }

    @Override
    public boolean doCreate(Role vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doEdit(Role vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemove(Set<String> ids) throws SQLException {
        return false;
    }

    @Override
    public Role findById(String id) throws SQLException {
        return null;
    }

    @Override
    public List<Role> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<Role> findSplit(Long currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    @Override
    public List<Role> findSplit(Long currentPage, Integer lineSize, String column, String keyWord) throws SQLException {
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
}
