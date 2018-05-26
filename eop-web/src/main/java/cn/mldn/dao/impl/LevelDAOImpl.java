package cn.mldn.dao.impl;

import cn.mldn.dao.ILevelDAO;
import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.util.web.annotation.Repository;
import cn.mldn.vo.Level;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class LevelDAOImpl extends AbstractDAO implements ILevelDAO {
    @Override
    public boolean doCreate(Level vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doEdit(Level vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemove(Set<Integer> ids) throws SQLException {
        return false;
    }

    @Override
    public Level findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<Level> findAll() throws SQLException {
        ArrayList<Level> list = new ArrayList<>();
        String sql = "select lid,title,losal,hisal from level";
        super.pstmt = super.conn.prepareStatement(sql);
        ResultSet rs = super.pstmt.executeQuery();
        while(rs.next()){
            Level vo = new Level();
            vo.setLid(rs.getInt(1));
            vo.setTitle(rs.getString(2));
            vo.setHisal(rs.getDouble(3));
            vo.setLosal(rs.getDouble(4));
            list.add(vo);
        }
        return list;
    }

    @Override
    public List<Level> findSplit(Long currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    @Override
    public List<Level> findSplit(Long currentPage, Integer lineSize, String column, String keyWord) throws SQLException {
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
