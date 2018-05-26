package cn.mldn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.dao.IEMPResourceDetailsDAO;
import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.vo.EMPResourceDetails;
import cn.mldn.vo.Resource;

public class EMPResourceDetailsDAOImpl extends AbstractDAO implements IEMPResourceDetailsDAO {

	@Override
	public boolean doCreate(EMPResourceDetails vo) throws SQLException {
		String sql = "INSERT INTO emp_resource_details (eresid,resid,amount) VALUES (?,?,?)" ;
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, vo.getEredid());
		super.pstmt.setLong(2, vo.getResid());
		super.pstmt.setInt(3, vo.getAmount());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doEdit(EMPResourceDetails vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<Long> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EMPResourceDetails findById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EMPResourceDetails> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EMPResourceDetails> findSplit(Long currentPage, Integer lineSize) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EMPResourceDetails> findSplit(Long currentPage, Integer lineSize, String column, String keyWord)
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

	@Override
	public EMPResourceDetails findByEMPResourceDetailsAndEMPResource(Long eresid, Long resid) throws Exception {
		String sql = "SELECT eredid,amount FROM emp_resource_details WHERE eresid=? AND resid=?" ;
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1,eresid);
		super.pstmt.setLong(2, resid);
		ResultSet rs = super.pstmt.executeQuery() ;
		if(rs.next()) {
			EMPResourceDetails emprd = new EMPResourceDetails() ;
			emprd.setEredid(rs.getLong(1));
			emprd.setAmount(rs.getInt(2));
			return emprd ;
		}
		return null;
	}

	@Override
	public boolean doEditAmount(Long eredid, Integer amount) throws Exception {
		String sql = " UPDATE emp_resource_details SET amount=amount+"+amount+"WHERE eredid=?" ;
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, eredid);
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public List<EMPResourceDetails> findAllByEMPResourceDetails(Long eresid) throws Exception {
		List<EMPResourceDetails> all = new ArrayList<EMPResourceDetails>();
		String sql = "SELECT eredid,eresid,resid,amount FROM emp_resource_details WHERE eresid=?" ;
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1,eresid) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		while(rs.next()) {
			EMPResourceDetails emprd = new EMPResourceDetails() ;
			emprd.setEredid(rs.getLong(1));
			emprd.setEresid(rs.getLong(2));
			emprd.setResid(rs.getLong(3));
			emprd.setAmount(rs.getInt(4));
			all.add(emprd);
		}
		return all;
	}
	

//	@Override
//	public Map<Long, Resource> findAllByResource(Long eresid) throws Exception {
//		Map<Long, Resource> all = new HashMap<Long, Resource>();
//		String sql = " SELECT resid,spid,title,price,appdate,amount,photo,status,item,note FROM resource WHERE resid IN ( " 
//				+ " SELECT resid FROM resource WHERE eresid=?) ";
//		super.pstmt = super.conn.prepareStatement(sql);
//		super.pstmt.setLong(1, eresid);
//		ResultSet rs = super.pstmt.executeQuery() ;
//		while(rs.next()) {
//			Resource vo = new Resource();
//			vo.setResid(rs.getLong(1));
//			vo.setSpid(rs.getLong(2));
//			vo.setTitle(rs.getString(3));
//			vo.setPrice(rs.getDouble(4));
//			vo.setAppdate(rs.getDate(5));
//			vo.setAmount(rs.getInt(6));
//			vo.setPhoto(rs.getString(7));
//			vo.setStatus(rs.getInt(8));
//			vo.setItem(rs.getString(9));
//			vo.setNote(rs.getString(10));
//			all.put(rs.getLong(1), vo);
//		}
//		return all;
//	}

}
