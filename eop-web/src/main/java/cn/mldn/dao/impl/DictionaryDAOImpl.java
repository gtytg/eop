package cn.mldn.dao.impl;

import cn.mldn.dao.IDictionaryDAO;
import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.util.web.annotation.Repository;
import cn.mldn.vo.Dictionary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class DictionaryDAOImpl extends AbstractDAO implements IDictionaryDAO {
    @Override
    public boolean doCreate(Dictionary vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doEdit(Dictionary vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemove(Set<Long> ids) throws SQLException {
        return false;
    }

    @Override
    public Dictionary findById(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<Dictionary> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<Dictionary> findSplit(Long currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    @Override
    public List<Dictionary> findSplit(Long currentPage, Integer lineSize, String column, String keyWord) throws SQLException {
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
    public List<Dictionary> findByType(String type) throws SQLException {
        ArrayList<Dictionary> list = new ArrayList<>();
        String sql = "select dctid,title,type,value from Dictionary where type = ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1,type);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Dictionary vo = new Dictionary();
            vo.setDctid(rs.getLong(1));
            vo.setTitle(rs.getString(2));
            vo.setType(rs.getString(3));
            vo.setValue(rs.getString(4));
           list.add(vo);
        }
        return list;
    }
}
