package cn.mldn.dao.impl;

import cn.mldn.dao.IPlanDAO;
import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.util.web.annotation.Repository;
import cn.mldn.vo.Plan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class PlanDAOImpl extends AbstractDAO implements IPlanDAO {

    @Override
    public boolean doCreate(Plan vo) throws SQLException {
        String sql = "insert into plan(title,item,starttime,endtime,note,status,amount,eid) values(?,?,?,?,?,?,?,?)";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, vo.getTitle());
        super.pstmt.setString(2, vo.getItem());
        super.pstmt.setTimestamp(3, new java.sql.Timestamp(vo.getStarttime().getTime()));
        super.pstmt.setTimestamp(4, new java.sql.Timestamp(vo.getEndtime().getTime()));
        super.pstmt.setString(5, vo.getNote());
        super.pstmt.setInt(6, vo.getStatus());
        super.pstmt.setInt(7, vo.getAmount());
        super.pstmt.setString(8, vo.getEid());
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doEdit(Plan vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemove(Set<Long> ids) throws SQLException {
        StringBuffer sql = new StringBuffer("delete from plan where status = 0 and pid in(");
        ids.forEach((id) -> sql.append(id).append(","));
        sql.delete(sql.length() - 1, sql.length()).append(")");
        this.pstmt = this.conn.prepareStatement(sql.toString());
        return this.pstmt.executeUpdate() > 0;
    }

    @Override
    public Plan findById(Long id) throws SQLException {
        String sql = "select pid,title,item,starttime,endtime,status,amount,note,eid from plan where pid = ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setLong(1, id);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Plan vo = new Plan();
            vo.setPid(rs.getLong(1));
            vo.setTitle(rs.getString(2));
            vo.setItem(rs.getString(3));
            vo.setStarttime(rs.getTimestamp(4));
            vo.setEndtime(rs.getTimestamp(5));
            vo.setStatus(rs.getInt(6));
            vo.setAmount(rs.getInt(7));
            vo.setNote(rs.getString(8));
            vo.setEid(rs.getString(9));
            return vo;
        }
        return null;
    }

    @Override
    public List<Plan> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<Plan> findSplit(Long currentPage, Integer lineSize) throws SQLException {
        ArrayList<Plan> list = new ArrayList<>();
        String sql = "select pid,title,item,starttime,endtime,status,amount,note,eid from plan LIMIT ?,?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setLong(1, (currentPage - 1) * lineSize);
        super.pstmt.setLong(2, lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Plan vo = new Plan();
            vo.setPid(rs.getLong(1));
            vo.setTitle(rs.getString(2));
            vo.setItem(rs.getString(3));
            vo.setStarttime(rs.getTimestamp(4));
            vo.setEndtime(rs.getTimestamp(5));
            vo.setStatus(rs.getInt(6));
            vo.setAmount(rs.getInt(7));
            vo.setNote(rs.getString(8));
            vo.setEid(rs.getString(9));
            list.add(vo);
        }
        return list;
    }

    @Override
    public List<Plan> findSplit(Long currentPage, Integer lineSize, String column, String keyWord) throws SQLException {
        ArrayList<Plan> list = new ArrayList<>();
        String sql = "select pid,title,item,starttime,endtime,status,amount,note,eid " +
                " from plan where " + column + " LIKE ? LIMIT ?,?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%" + keyWord + "%");
        super.pstmt.setLong(2, (currentPage - 1) * lineSize);
        super.pstmt.setLong(3, lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Plan vo = new Plan();
            vo.setPid(rs.getLong(1));
            vo.setTitle(rs.getString(2));
            vo.setItem(rs.getString(3));
            vo.setStarttime(rs.getTimestamp(4));
            vo.setEndtime(rs.getTimestamp(5));
            vo.setStatus(rs.getInt(6));
            vo.setAmount(rs.getInt(7));
            vo.setNote(rs.getString(8));
            vo.setEid(rs.getString(9));
            list.add(vo);
        }
        return list;
    }

    @Override
    public Long getAllCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM plan";
        super.pstmt = super.conn.prepareStatement(sql);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getLong(1);
        }
        return 0L;
    }

    @Override
    public Long getAllCount(String column, String keyWord) throws SQLException {
        String sql = "SELECT COUNT(*) FROM plan WHERE " + column + " LIKE ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%" + keyWord + "%");
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getLong(1);
        }
        return 0L;
    }

    @Override
    public List<Long> findByIdAndStatusList(Set<Long> ids) throws SQLException {
        ArrayList<Long> list = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select pid from plan where status = 0 and pid in(");
        ids.forEach((id) -> sql.append(id).append(","));
        sql.delete(sql.length() - 1, sql.length()).append(")");
        super.pstmt = super.conn.prepareStatement(sql.toString());
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            list.add(rs.getLong(1));
        }
        return list;
    }

    @Override
    public boolean updateStatus(long id) throws SQLException {
        String sql = "update plan set status = 1 where pid = ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setLong(1, id);
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean updateAmount(Long amount, Long pid) throws SQLException {
        String sql = "update plan set amount =amount + ? where pid = ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setLong(1, amount);
        super.pstmt.setLong(2, pid);
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public Integer findByAmount(Long pid) throws SQLException {
        String sql = "select amount from plan where pid = ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setLong(1, pid);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean existsTitle(String title) throws SQLException {
        String sql = "select title from plan where title = ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, title);
        ResultSet rs = super.pstmt.executeQuery();
        return !rs.next();
    }

    @Override
    public List<Plan> findEidSplit(Long currentPage, Integer lineSize, String eid, String condition)
            throws SQLException {
        ArrayList<Plan> list = new ArrayList<>();
        StringBuffer sql = new StringBuffer(condition);
        sql.append(" LIMIT ?,?");
        super.pstmt = super.conn.prepareStatement(sql.toString());
        super.pstmt.setString(1, eid);
        super.pstmt.setLong(2, (currentPage - 1) * lineSize);
        super.pstmt.setLong(3, lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Plan vo = new Plan();
            vo.setPid(rs.getLong(1));
            vo.setTitle(rs.getString(2));
            vo.setItem(rs.getString(3));
            vo.setStarttime(rs.getTimestamp(4));
            vo.setEndtime(rs.getTimestamp(5));
            vo.setStatus(rs.getInt(6));
            vo.setAmount(rs.getInt(7));
            vo.setNote(rs.getString(8));
            vo.setEid(rs.getString(9));
            list.add(vo);
        }
        return list;
    }

    @Override
    public List<Plan> findEidSplit(Long currentPage, Integer lineSize, String column, String keyWord, String eid, String condition)
            throws SQLException {
        ArrayList<Plan> list = new ArrayList<>();
        StringBuffer sql = new StringBuffer(condition);
        sql.append(" LIMIT ?,?");
        super.pstmt = super.conn.prepareStatement(sql.toString());
        super.pstmt.setString(1, eid);
        super.pstmt.setString(2, "%" + keyWord + "%");
        super.pstmt.setString(3, eid);
        super.pstmt.setString(4, "%" + keyWord + "%");
        super.pstmt.setLong(5, (currentPage - 1) * lineSize);
        super.pstmt.setLong(6, lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Plan vo = new Plan();
            vo.setPid(rs.getLong(1));
            vo.setTitle(rs.getString(2));
            vo.setItem(rs.getString(3));
            vo.setStarttime(rs.getTimestamp(4));
            vo.setEndtime(rs.getTimestamp(5));
            vo.setStatus(rs.getInt(6));
            vo.setAmount(rs.getInt(7));
            vo.setNote(rs.getString(8));
            vo.setEid(rs.getString(9));
            list.add(vo);
        }
        return list;
    }

    public List<Plan> findMyEidSplit(Long currentPage, Integer lineSize, String column, String keyWord, String eid, String condition)
            throws SQLException {
        ArrayList<Plan> list = new ArrayList<>();
        StringBuffer sql = new StringBuffer(condition);
        sql.append(" LIMIT ?,?");
        super.pstmt = super.conn.prepareStatement(sql.toString());
        super.pstmt.setString(1, eid);
        super.pstmt.setString(2, "%" + keyWord + "%");
        super.pstmt.setLong(3, (currentPage - 1) * lineSize);
        super.pstmt.setLong(4, lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Plan vo = new Plan();
            vo.setPid(rs.getLong(1));
            vo.setTitle(rs.getString(2));
            vo.setItem(rs.getString(3));
            vo.setStarttime(rs.getTimestamp(4));
            vo.setEndtime(rs.getTimestamp(5));
            vo.setStatus(rs.getInt(6));
            vo.setAmount(rs.getInt(7));
            vo.setNote(rs.getString(8));
            vo.setEid(rs.getString(9));
            list.add(vo);
        }
        return list;
    }

    @Override
    public Long getEidAllCount(String eid, String condition) throws SQLException {
        String sql = condition;
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, eid);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getLong(1);
        }
        return 0L;
    }

    @Override
    public Long getEidAllCount(String column, String keyWord, String eid, String condition) throws SQLException {
        StringBuffer sql = new StringBuffer(condition);
        super.pstmt = super.conn.prepareStatement(sql.toString());
        super.pstmt.setString(1, eid);
        super.pstmt.setString(2, "%" + keyWord + "%");
        super.pstmt.setString(3, eid);
        super.pstmt.setString(4, "%" + keyWord + "%");
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getLong(1);
        }
        return 0L;
    }

    @Override
    public Long getMyEidAllCount(String column, String keyWord, String eid, String condition) throws SQLException {
        StringBuffer sql = new StringBuffer(condition);
        super.pstmt.setString(1, eid);
        super.pstmt.setString(2, "%" + keyWord + "%");
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getLong(1);
        }
        return 0L;
    }
}
