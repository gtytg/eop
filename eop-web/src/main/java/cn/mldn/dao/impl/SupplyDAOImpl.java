package cn.mldn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.mldn.dao.ISupplyDAO;
import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.util.web.annotation.Repository;
import cn.mldn.vo.Resource;
import cn.mldn.vo.Supply;

@Repository
public class SupplyDAOImpl extends AbstractDAO implements ISupplyDAO {

	
	
	@Override
    public List<Supply> suppyList(String eid) throws SQLException {
        List<Supply> all = new ArrayList<Supply>();
        String sql = "SELECT spid,title,amount,money,status FROM supply_purchase WHERE eid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, eid);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Supply vo = new Supply();
            vo.setSpid(rs.getLong(1));
            vo.setTitle(rs.getString(2));
            vo.setAmount(rs.getInt(3));
            vo.setMoney(rs.getDouble(4));
            vo.setStatus(rs.getInt(5));
            all.add(vo);
        }
        return all;
    }

    @Override
    public boolean doCreate(Supply vo) throws SQLException {
        String sql = "INSERT INTO supply_purchase(eid,title,note,status) VALUES(?,?,?,?)";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, vo.getEid());
        super.pstmt.setString(2, vo.getTitle());
        super.pstmt.setString(3, vo.getNote());
        super.pstmt.setInt(4, 0);
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doEdit(Supply vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemove(Set<Long> ids) throws SQLException {
        return false;
    }

    @Override
    public Supply findById(Long id) throws SQLException {
		return null;
    }
	@Override
	public List<Supply> findSplit(Long currentPage, Integer lineSize) throws SQLException {
		 	List<Supply> all = new ArrayList<Supply>();
	        String sql = " SELECT spid,title,amount,money,status FROM supply_purchase limit ?,? ";
	        super.pstmt = super.conn.prepareStatement(sql);
	        super.pstmt.setLong(1, (currentPage - 1) * lineSize);
	        super.pstmt.setLong(2, lineSize);
	        ResultSet rs = super.pstmt.executeQuery();
	        while (rs.next()) {
	            Supply vo = new Supply();
	            vo.setSpid(rs.getLong(1));
	            vo.setTitle(rs.getString(2));
	            vo.setAmount(rs.getInt(3));
	            vo.setMoney(rs.getDouble(4));
	            vo.setStatus(rs.getInt(5));
	            all.add(vo);
	        }
	        return all;
	}


	@Override
	public List<Supply> findSplit(Long currentPage, Integer lineSize, String column, String keyWord)
			throws SQLException {
			List<Supply> all = new ArrayList<Supply>();
	        String sql = " SELECT spid,title,amount,money,status FROM supply_purchase where " + column + " like ? limit ?,? ";
	        super.pstmt = super.conn.prepareStatement(sql);
	        super.pstmt.setString(1, "%" + keyWord + "%");
	        super.pstmt.setLong(1, (currentPage - 1) * lineSize);
	        super.pstmt.setLong(2, lineSize);
	        ResultSet rs = super.pstmt.executeQuery();
	        while (rs.next()) {
	            Supply vo = new Supply();
	            vo.setSpid(rs.getLong(1));
	            vo.setTitle(rs.getString(2));
	            vo.setAmount(rs.getInt(3));
	            vo.setMoney(rs.getDouble(4));
	            vo.setStatus(rs.getInt(5));
	            all.add(vo);
	        }
	        return all;
	}

    @Override
    public List<Supply> findAll() throws SQLException {
        List<Supply> all = new ArrayList<Supply>();
        String sql = "SELECT spid,title,amount,money,status FROM supply_purchase ";
        super.pstmt = super.conn.prepareStatement(sql);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            Supply vo = new Supply();
            vo.setSpid(rs.getLong(1));
            vo.setTitle(rs.getString(2));
            vo.setAmount(rs.getInt(3));
            vo.setMoney(rs.getDouble(4));
            vo.setStatus(rs.getInt(5));
            all.add(vo);
            return all;
        }
        return null;
    }


    @Override
    public Long getAllCount() throws SQLException {
        return super.getAllCountHandle("supply_purchase");
    }

    @Override
    public Long getAllCount(String column, String keyWord) throws SQLException {
        String sql = "SELECT COUNT(*) FROM supply_purchase WHERE " + column + " LIKE ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%" + keyWord + "%");
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getLong(1);
        }
        return 0L;

    }

	@Override
	public List<Resource> findByApply(Long spid) throws SQLException {
		List<Resource> all = new ArrayList<Resource>();
		String sql = "SELECT resid,spid,title,price,amount,photo,status FROM resource WHERE spid=? " ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setLong(1, spid);
		ResultSet rs = super.pstmt.executeQuery() ;
		while(rs.next()) {
			Resource vo = new Resource() ;
			vo.setResid(rs.getLong(1));
			vo.setSpid(rs.getLong(2));
			vo.setTitle(rs.getString(3));
			vo.setPrice(rs.getDouble(4));
			vo.setAmount(rs.getInt(5));
			vo.setPhoto(rs.getString(6));
			vo.setStatus(rs.getInt(7));
			all.add(vo) ;
		}
		return all;
	}
	@Override
	public List<Supply> findEidSplit(Long currentPage, Integer lineSize, String column, String keyWord, String eid)
			throws SQLException {
		List<Supply> all = new ArrayList<Supply>();
        String sql = " SELECT spid,title,amount,money,status FROM supply_purchase WHERE eid=?" + column + " like ? limit ?,? ";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, eid);
        super.pstmt.setString(2, "%" + keyWord + "%");
        super.pstmt.setLong(3, (currentPage - 1) * lineSize);
        super.pstmt.setLong(4, lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Supply vo = new Supply();
            vo.setSpid(rs.getLong(1));
            vo.setTitle(rs.getString(2));
            vo.setAmount(rs.getInt(3));
            vo.setMoney(rs.getDouble(4));
            vo.setStatus(rs.getInt(5));
            all.add(vo);
        }
        return all;
	}

	@Override
	public Long getEidAllCount(String column, String keyWord, String eid) throws SQLException {
		 	String sql = "SELECT COUNT(*) FROM supply_purchase WHERE eid=? AND" + column + " LIKE ?";
	        super.pstmt = super.conn.prepareStatement(sql);
	        super.pstmt.setString(1, eid);
	        super.pstmt.setString(2, "%" + keyWord + "%");
	        ResultSet rs = super.pstmt.executeQuery();
	        if (rs.next()) {
	            return rs.getLong(1);
	        }
	        return 0L;
	}

	@Override
	public Long getEidAllCount(String eid) throws SQLException {
		String sql = "SELECT COUNT(*) FROM supply_purchase WHERE eid=? ";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, eid);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getLong(1);
        }
        return 0L;
	}

	@Override
	public List<Supply> findEidSplit(Long currentPage, Integer lineSize, String eid) throws SQLException {
			List<Supply> all = new ArrayList<Supply>();
			String sql = " SELECT spid,title,amount,money,status FROM supply_purchase WHERE eid=? limit ?,? ";
	        super.pstmt = super.conn.prepareStatement(sql);
	        super.pstmt.setString(1, eid);
	        super.pstmt.setLong(2, (currentPage - 1) * lineSize);
	        super.pstmt.setLong(3, lineSize);
	        ResultSet rs = super.pstmt.executeQuery();
	        while (rs.next()) {
	            Supply vo = new Supply();
	            vo.setSpid(rs.getLong(1));
	            vo.setTitle(rs.getString(2));
	            vo.setAmount(rs.getInt(3));
	            vo.setMoney(rs.getDouble(4));
	            vo.setStatus(rs.getInt(5));
	            all.add(vo);
	        }
	        return all;
	}

}
