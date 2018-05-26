package cn.mldn.dao.impl;

import cn.mldn.dao.IDeptDAO;
import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.util.web.annotation.Repository;
import cn.mldn.vo.Dept;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class DeptDAOImpl extends AbstractDAO implements IDeptDAO {
	@Override
	public Dept findByDname(String dname) throws Exception {
		String sql = "SELECT did,dname,eid,maxnum,currnum FROM dept WHERE dname = ?" ;
		super.pstmt = super.conn.prepareStatement(sql);
    	super.pstmt.setString(1, dname);
    	ResultSet rs = super.pstmt.executeQuery();
    	if (rs.next()) {
    		Dept dept = new Dept();
    		dept.setDid(rs.getLong(1));
    		dept.setDname(rs.getString(2));
    		dept.setEid(rs.getString(3));
    		dept.setMaxnum(rs.getInt(4));
    		dept.setCurrnum(rs.getInt(5));
    		return dept ;
    	}
        return null;
	}
	
	@Override
    public boolean doCreate(Dept vo) throws SQLException {
    	String sql = "INSERT INTO dept(dname,eid,maxnum,currnum) VALUES (?,?,?,?)" ;
    	super.pstmt = super.conn.prepareStatement(sql);
    	super.pstmt.setString(1, vo.getDname());
    	super.pstmt.setString(2, vo.getEid());
    	super.pstmt.setInt(3, vo.getMaxnum());
    	super.pstmt.setInt(4, vo.getCurrnum());
    	int update = super.pstmt.executeUpdate();
        return update > 0;
    }

    @Override
    public boolean doEdit(Dept vo) throws SQLException {
    	String sql = "UPDATE dept SET dname=?,eid=?,maxnum=?,currnum? WHERE did=?" ;
    	super.pstmt = super.conn.prepareStatement(sql);
    	super.pstmt.setString(1, vo.getDname());
    	super.pstmt.setString(2, vo.getEid());
    	super.pstmt.setInt(3, vo.getMaxnum());
    	super.pstmt.setInt(4, vo.getCurrnum());
    	super.pstmt.setLong(5, vo.getDid());
    	int update = super.pstmt.executeUpdate();
        return update > 0;
    }

    @Override
    public boolean doRemove(Set<Long> ids) throws SQLException {
        return false;
    }

    @Override
    public Dept findById(Long id) throws SQLException {
    	
    	String sql = "SELECT did,dname,eid,maxnum,currnum FROM dept WHERE did = ?" ;
    	super.pstmt = super.conn.prepareStatement(sql);
    	super.pstmt.setLong(1, id);
    	ResultSet rs = super.pstmt.executeQuery();
    	if (rs.next()) {
    		Dept dept = new Dept();
    		dept.setDid(rs.getLong(1));
    		dept.setDname(rs.getString(2));
    		dept.setEid(rs.getString(3));
    		dept.setMaxnum(rs.getInt(4));
    		dept.setCurrnum(rs.getInt(5));
    		return dept ;
    	}
        return null;
    }

    @Override
    public List<Dept> findAll() throws SQLException {
        List<Dept> list = new ArrayList<>();
        String sql = "select did,dname,eid,maxnum,currnum from dept";
        super.pstmt = super.conn.prepareStatement(sql);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Dept vo = new Dept();
            vo.setDid(rs.getLong(1));
            vo.setDname(rs.getString(2));
            vo.setEid(rs.getString(3));
            vo.setMaxnum(rs.getInt(4));
            vo.setCurrnum(rs.getInt(5));
            list.add(vo);
        }
        return list;
    }

    @Override
    public List<Dept> findSplit(Long currentPage, Integer lineSize) throws SQLException {
    	List<Dept> all = new ArrayList<Dept>();
    	String sql = "SELECT did,dname,eid,maxnum,currnum FROM dept LIMIT ?,?" ;
    	super.pstmt = super.conn.prepareStatement(sql);
    	super.pstmt.setLong(1, (currentPage - 1)* lineSize);
    	super.pstmt.setInt(2, lineSize);
    	ResultSet rs = super.pstmt.executeQuery();
    	while (rs.next()) {
            Dept vo = new Dept();
            vo.setDid(rs.getLong(1));
            vo.setDname(rs.getString(2));
            vo.setEid(rs.getString(3));
            vo.setMaxnum(rs.getInt(4));
            vo.setCurrnum(rs.getInt(5));
            all.add(vo);
        }
        return all;
    }

    @Override
    public List<Dept> findSplit(Long currentPage, Integer lineSize, String column, String keyWord) throws SQLException {
        return null;
    }

    @Override
    public Long getAllCount() throws SQLException {
    	String sql = "SELECT COUNT(*) FROM dept" ;
    	super.pstmt = super.conn.prepareStatement(sql);
    	ResultSet rs = super.pstmt.executeQuery();
    	if (rs.next()) {
    		long count = rs.getLong(1);
    		return count ;
    	}
        return 0L;
    }

    @Override
    public Long getAllCount(String column, String keyWord) throws SQLException {
        return null;
    }

	
}
