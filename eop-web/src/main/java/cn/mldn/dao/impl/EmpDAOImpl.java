package cn.mldn.dao.impl;

import cn.mldn.dao.IEmpDAO;
import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.util.web.annotation.Repository;
import cn.mldn.vo.Emp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class EmpDAOImpl extends AbstractDAO implements IEmpDAO {

	@Override
	public boolean doCreate(Emp vo) throws SQLException {
		String sql = "INSERT INTO emp(eid,lid,did,ename,salary,phone,password,photo,note,hiredate,ineid,status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getEid());
		super.pstmt.setInt(2, vo.getLid());
		super.pstmt.setLong(3, vo.getDid());
		super.pstmt.setString(4, vo.getEname());
		super.pstmt.setDouble(5, vo.getSalary());
		super.pstmt.setString(6, vo.getPhone());
		super.pstmt.setString(7, vo.getPassword());
		super.pstmt.setString(8, vo.getPhoto());
		super.pstmt.setString(9, vo.getNote());
		super.pstmt.setDate(10, new java.sql.Date(vo.getHiredate().getTime()));
		super.pstmt.setString(11, vo.getIneid());
		super.pstmt.setInt(12, vo.getStatus());
		int update = super.pstmt.executeUpdate();
		return update > 0;
	}

	@Override
	public boolean doEdit(Emp vo) throws SQLException {
		String sql = "UPDATE emp SET lid=?,did=?,ename=?,salary=?,phone=?,password=?,photo=?,note=?,hiredate=?,ineid=?,status=? WHERE eid=? ";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, vo.getLid());
		super.pstmt.setLong(2, vo.getDid());
		super.pstmt.setString(3, vo.getEname());
		super.pstmt.setDouble(4, vo.getSalary());
		super.pstmt.setString(5, vo.getPhone());
		super.pstmt.setString(6, vo.getPassword());
		super.pstmt.setString(7, vo.getPhoto());
		super.pstmt.setString(8, vo.getNote());
		super.pstmt.setDate(9, new java.sql.Date(vo.getHiredate().getTime()));
		super.pstmt.setString(10, vo.getIneid());
		super.pstmt.setInt(11, vo.getStatus());
		super.pstmt.setString(12, vo.getEid());
		int update = super.pstmt.executeUpdate();
		return update > 0;
	}

	@Override
	public boolean doRemove(Set<String> ids) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM emp WHERE eid IN (");
		for (String id : ids) {
			sql.append("'").append(id).append("',");
		}
		sql.delete(sql.length() - 1, sql.length()).append(")");
		super.pstmt = super.conn.prepareStatement(sql.toString());
		int update = super.pstmt.executeUpdate();
		return update > 0;
	}

	   @Override
	    public Emp findById(String id) throws SQLException {
	        String sql = "select eid,lid,did,ename,salary,phone,password,photo,note,hiredate,ineid,status,lastdate from emp where eid = ?";
	        super.pstmt = super.conn.prepareStatement(sql);
	        super.pstmt.setString(1, id);
	        ResultSet rs = super.pstmt.executeQuery();
	        while (rs.next()) {
	            Emp vo = new Emp();
	            vo.setEid(rs.getString(1));
	            vo.setLid(rs.getInt(2));
	            vo.setDid(rs.getLong(3));
	            vo.setEname(rs.getString(4));
	            vo.setSalary(rs.getDouble(5));
	            vo.setPhone(rs.getString(6));
	            vo.setPassword(rs.getString(7));
	            vo.setPhoto(rs.getString(8));
	            vo.setNote(rs.getString(9));
	            vo.setHiredate(rs.getDate(10));
	            vo.setIneid(rs.getString(11));
	            vo.setStatus(rs.getInt(12));
	            vo.setLastDate(rs.getTimestamp(13));
	            return vo;
	        }
	        return null;
	    }
    

	@Override
	public List<Emp> findAll() throws SQLException {
		List<Emp> all = new ArrayList<Emp>();
		String sql = "SELECT eid,lid,did,ename,salary,phone,password,photo,note,hiredate,ineid,status FROM emp";
		super.pstmt = super.conn.prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			Emp emp = new Emp();
			emp.setEid(rs.getString(1));
			emp.setLid(rs.getInt(2));
			emp.setDid(rs.getLong(3));
			emp.setEname(rs.getString(4));
			emp.setSalary(rs.getDouble(5));
			emp.setPhone(rs.getString(6));
			emp.setPassword(rs.getString(7));
			emp.setPhoto(rs.getString(8));
			emp.setNote(rs.getString(9));
			emp.setHiredate(rs.getDate(10));
			emp.setIneid(rs.getString(11));
			emp.setStatus(rs.getInt(12));
			all.add(emp);
		}
		return all;
	}

	@Override
	public List<Emp> findSplit(Long currentPage, Integer lineSize) throws SQLException {
		List<Emp> all = new ArrayList<Emp>();
		String sql = "SELECT eid,lid,did,ename,salary,phone,password,photo,note,hiredate,ineid,status FROM emp LIMIT ?,?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, (currentPage - 1) * lineSize);
		super.pstmt.setInt(2, lineSize);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			Emp emp = new Emp();
			emp.setEid(rs.getString(1));
			emp.setLid(rs.getInt(2));
			emp.setDid(rs.getLong(3));
			emp.setEname(rs.getString(4));
			emp.setSalary(rs.getDouble(5));
			emp.setPhone(rs.getString(6));
			emp.setPassword(rs.getString(7));
			emp.setPhoto(rs.getString(8));
			emp.setNote(rs.getString(9));
			emp.setHiredate(rs.getDate(10));
			emp.setIneid(rs.getString(11));
			emp.setStatus(rs.getInt(12));
			all.add(emp);
		}
		return all;
	}

	@Override
	public List<Emp> findSplit(Long currentPage, Integer lineSize, String column, String keyWord) throws SQLException {
		List<Emp> all = new ArrayList<Emp>();
		String sql = "SELECT eid,lid,did,ename,salary,phone,password,photo,note,hiredate,ineid,status FROM emp WHERE "
				+ column + " LIKE ? LIMIT ?,?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, "%" + keyWord + "%");
		super.pstmt.setLong(2, (currentPage - 1) * lineSize);
		super.pstmt.setInt(3, lineSize);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			Emp emp = new Emp();
			emp.setEid(rs.getString(1));
			emp.setLid(rs.getInt(2));
			emp.setDid(rs.getLong(3));
			emp.setEname(rs.getString(4));
			emp.setSalary(rs.getDouble(5));
			emp.setPhone(rs.getString(6));
			emp.setPassword(rs.getString(7));
			emp.setPhoto(rs.getString(8));
			emp.setNote(rs.getString(9));
			emp.setHiredate(rs.getDate(10));
			emp.setIneid(rs.getString(11));
			emp.setStatus(rs.getInt(12));
			all.add(emp);
		}
		return all;
	}

	@Override
	public Long getAllCount() throws SQLException {
		String sql = "SELECT COUNT(*) FROM emp";
		super.pstmt = super.conn.prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			Long count = rs.getLong(1);
			return count;
		} else {
			return 0L;
		}
	}

	@Override
	public Long getAllCount(String column, String keyWord) throws SQLException {
		String sql = "SELECT COUNT(*) FROM emp WHERE " + column + " LIKE ?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, "%" + keyWord + "%");
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			Long count = rs.getLong(1);
			return count ;
		} else {
			return 0L;
		}
	}
@Override
public List<Emp> getByEidList(List<String> eids) throws SQLException {
    StringBuffer sql = new StringBuffer();
    ArrayList<Emp> list = new ArrayList<>();
    sql.append("select eid,lid,did,ename,salary,phone,password,photo,note,hiredate,ineid,status,lastdate from emp where eid in(");
    eids.forEach((eid) -> sql.append("'").append(eid).append("'").append(","));
    sql.delete(sql.length() - 1, sql.length()).append(")");
    super.pstmt = super.conn.prepareStatement(sql.toString());
    ResultSet rs = super.pstmt.executeQuery();
    while (rs.next()) {
        Emp vo = new Emp();
        vo.setEid(rs.getString(1));
        vo.setLid(rs.getInt(2));
        vo.setDid(rs.getLong(3));
        vo.setEname(rs.getString(4));
        vo.setSalary(rs.getDouble(5));
        vo.setPhone(rs.getString(6));
        vo.setPassword(rs.getString(7));
        vo.setPhoto(rs.getString(8));
        vo.setNote(rs.getString(9));
        vo.setHiredate(rs.getDate(10));
        vo.setIneid(rs.getString(11));
        vo.setStatus(rs.getInt(12));
        vo.setLastDate(rs.getDate(13));
        list.add(vo);
    }
    return list;
}

@Override
public List<Emp> getBylEidList(String eid) throws SQLException {
    String sql = "select eid,lid,did,ename,salary,phone,password,photo,note,hiredate,ineid,status,lastdate " +
            " from emp where lid > (SELECT lid FROM emp where eid = ?)";
    super.pstmt = super.conn.prepareStatement(sql);
    super.pstmt.setString(1, eid);
    ResultSet rs = super.pstmt.executeQuery();
    ArrayList<Emp> list = new ArrayList<>();
    while (rs.next()) {
        Emp vo = new Emp();
        vo.setEid(rs.getString(1));
        vo.setLid(rs.getInt(2));
        vo.setDid(rs.getLong(3));
        vo.setEname(rs.getString(4));
        vo.setSalary(rs.getDouble(5));
        vo.setPhone(rs.getString(6));
        vo.setPassword(rs.getString(7));
        vo.setPhoto(rs.getString(8));
        vo.setNote(rs.getString(9));
        vo.setHiredate(rs.getDate(10));
        vo.setIneid(rs.getString(11));
        vo.setStatus(rs.getInt(12));
        vo.setLastDate(rs.getDate(13));
        list.add(vo);
    }
    return list;
}
@Override
public List<Emp> getDidEmpList(String eid, Long did) throws SQLException {
    String sql = "select eid,lid,did,ename,salary,phone,password,photo,note,hiredate,ineid,status,lastdate " +
            " from emp where lid > (SELECT lid FROM emp where eid = ?) and did = ?";
    super.pstmt = super.conn.prepareStatement(sql);
    super.pstmt.setString(1, eid);
    super.pstmt.setLong(2, did);
    ResultSet rs = super.pstmt.executeQuery();
    ArrayList<Emp> list = new ArrayList<>();
    while (rs.next()) {
        Emp vo = new Emp();
        vo.setEid(rs.getString(1));
        vo.setLid(rs.getInt(2));
        vo.setDid(rs.getLong(3));
        vo.setEname(rs.getString(4));
        vo.setSalary(rs.getDouble(5));
        vo.setPhone(rs.getString(6));
        vo.setPassword(rs.getString(7));
        vo.setPhoto(rs.getString(8));
        vo.setNote(rs.getString(9));
        vo.setHiredate(rs.getDate(10));
        vo.setIneid(rs.getString(11));
        vo.setStatus(rs.getInt(12));
        vo.setLastDate(rs.getDate(13));
        list.add(vo);
    }
    return list;
}

@Override
public boolean updatePassword(String password, String eid) throws SQLException {
    String sql = "update emp set password = ? where eid = ?";
    super.pstmt = super.conn.prepareStatement(sql);
    super.pstmt.setString(1, password);
    super.pstmt.setString(2, eid);
    return super.pstmt.executeUpdate() > 0;
}

@Override
public boolean updateLastDate(String eid,java.util.Date lastDate) throws SQLException {
    String sql = "update emp set lastdate = ? where eid = ?";
    super.pstmt = this.conn.prepareStatement(sql);
    super.pstmt.setTimestamp(1, new java.sql.Timestamp(lastDate.getTime()));
    this.pstmt.setString(2, eid);
    return this.pstmt.executeUpdate() > 0;
}
}
