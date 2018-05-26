package cn.mldn.dao.impl;

import cn.mldn.dao.IPlanDetailsDAO;
import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.util.web.annotation.Repository;
import cn.mldn.vo.PlanDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class PlanDetailsDAOImpl extends AbstractDAO implements IPlanDetailsDAO {
    @Override
    public boolean doCreate(PlanDetails vo) throws SQLException {
        String sql = "insert into plan_details(pid,eid) values(?,?)";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setLong(1, vo.getPid());
        super.pstmt.setString(2, vo.getEid());
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doEdit(PlanDetails vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemove(Set<Long> ids) throws SQLException {
        return false;
    }

    @Override
    public PlanDetails findById(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<PlanDetails> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<PlanDetails> findSplit(Long currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    @Override
    public List<PlanDetails> findSplit(Long currentPage, Integer lineSize, String column, String keyWord) throws SQLException {
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
    public List<String> findByPid(Long pid) throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        String sql = "select eid from plan_details where pid = ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setLong(1, pid);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            list.add(rs.getString(1));
        }
        return list;
    }

    @Override
    public boolean getEidRemove(String eid) throws SQLException {
        String sql = "delete from plan_details where eid = ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, eid);
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean currentPidExists(String eid, Long pid) throws SQLException {
        String sql = "select eid from plan_details where " +
                " eid = ? and pid = ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, eid);
        super.pstmt.setLong(2, pid);
        ResultSet rs = super.pstmt.executeQuery();
        return !rs.next();
    }

    @Override
    public Long findByEidAndPid(String eid, Long pid) throws SQLException {
        String sql = "select count(*) " +
                "from plan_details where eid = ? and pid in " +
                "((select pid from plan where pid not in " +
                "(SELECT pid from plan where starttime > (select endtime from plan where pid = ?)" +
                "or endtime < (select starttime from plan where pid = ?))))";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, eid);
        super.pstmt.setLong(2, pid);
        super.pstmt.setLong(3, pid);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getLong(1);
        }
        return 0L;
    }

    @Override
    public List<String> existsEid(Long pid) throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        String sql = "select eid from plan_details where pid in " +
                " ((select pid from plan where pid not in " +
                "(SELECT pid from plan where starttime > (select endtime from plan where pid = ?)" +
                "or endtime < (select starttime from plan where pid = ?))))";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setLong(1, pid);
        super.pstmt.setLong(2, pid);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            list.add(rs.getString(1));
        }
        return list;
    }
}
