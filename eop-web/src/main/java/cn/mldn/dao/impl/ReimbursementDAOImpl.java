package cn.mldn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.mldn.dao.IReimbursementDAO;
import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.util.web.annotation.Repository;
import cn.mldn.vo.Reimbursement;
@Repository
public class ReimbursementDAOImpl extends AbstractDAO implements IReimbursementDAO {

	@Override
	public boolean doCreate(Reimbursement vo) throws SQLException {
		String sql="INSERT INTO reimbursement(title,item,note) VALUES(?,?,?)";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getTitle());
		super.pstmt.setString(2, vo.getItem());
		super.pstmt.setString(3, vo.getNote());
		return super.pstmt.executeUpdate()>0;
	}

	@Override
	public boolean doEdit(Reimbursement vo) throws SQLException {
		String sql="UPDATE reimbursement SET title=?,SET item=?,SET note=? WHERE eid=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getTitle());
		super.pstmt.setString(2, vo.getItem());
		super.pstmt.setString(3, vo.getNote());
		super.pstmt.setString(4, vo.getEid());
		return super.pstmt.executeUpdate()>0;
	}

	@Override
	public boolean doRemove(Set<Long> ids) throws SQLException {
		StringBuffer sql=new StringBuffer();
		sql.append("DELETE FROM reimbursement WHERE status IN(0,3) AND eid IN(");
		for(Long id:ids) {
			sql.append("'").append(id).append("',");
		}
		sql.delete(sql.length()-1, sql.length()).append(")");
		return super.pstmt.executeUpdate()>0;
	}

	@Override
	public Reimbursement findById(Long id) throws SQLException {
		Reimbursement vo=null;
		String sql="SELECT title,item,money,credate,auditdate,status FROM reimbursement";
		super.pstmt=super.conn.prepareStatement(sql);
		ResultSet rs=super.pstmt.executeQuery();
		if(rs.next()) {
			vo=new Reimbursement();
			vo.setTitle(rs.getString(1));
			vo.setItem(rs.getString(2));
			vo.setMoney(rs.getDouble(3));
			vo.setCredate(rs.getDate(4));
			vo.setAuditdate(rs.getDate(5));
			vo.setStatus(rs.getInt(6));
		}
		return vo;
	}

	@Override
	public List<Reimbursement> findAll() throws SQLException {
		List<Reimbursement>all=new ArrayList<Reimbursement>();
		String sql="SELECT title,item,money,credate,auditdate,status FROM reimbursement";
		super.pstmt=super.conn.prepareStatement(sql);
		ResultSet rs=super.pstmt.executeQuery();
		while(rs.next()) {
			Reimbursement vo=new Reimbursement();
			vo.setTitle(rs.getString(1));
			vo.setItem(rs.getString(2));
			vo.setMoney(rs.getDouble(3));
			vo.setCredate(rs.getDate(4));
			vo.setAuditdate(rs.getDate(5));
			vo.setStatus(rs.getInt(6));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Reimbursement> findSplit(Long currentPage, Integer lineSize) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> findSplit(Long currentPage, Integer lineSize, String column, String keyWord)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getAllCount() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getAllCount(String column, String keyWord) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 搜索
	 * @param column 列名称
	 * @param keyWord 关键字
	 * @return 搜索到的结果
	 * @throws SQLException SQL异常
	 */
	public List<Reimbursement> Search(String column, String keyWord) throws SQLException {
		List<Reimbursement>all=new ArrayList<Reimbursement>();
		String sql="SELECT title,item,money,credate,auditdate,status FROM reimbursement WHERE "+column+" LIKE ?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setString(1, "%"+keyWord+"%");
		super.pstmt=super.conn.prepareStatement(sql);
		ResultSet rs=super.pstmt.executeQuery();
		if(rs.next()) {
			Reimbursement vo=new Reimbursement();
			vo.setTitle(rs.getString(1));
			vo.setItem(rs.getString(2));
			vo.setMoney(rs.getDouble(3));
			vo.setCredate(rs.getDate(4));
			vo.setAuditdate(rs.getDate(5));
			vo.setStatus(rs.getInt(6));
			all.add(vo);
		}
		return all;
	}
}
