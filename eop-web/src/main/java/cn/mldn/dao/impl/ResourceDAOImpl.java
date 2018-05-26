package cn.mldn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.dao.IResourceDAO;
import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.util.web.annotation.Repository;
import cn.mldn.vo.Resource;

@Repository
public class ResourceDAOImpl extends AbstractDAO implements IResourceDAO {

    @Override
    public boolean doCreate(Resource vo) throws SQLException {
        String sql = "INSERT INTO resource(spid,title,price,appdate,amount,photo,status,item,note)" +
                " VALUES(?,?,?,?,?,?,?,?,?)" ;
        super.pstmt = super.conn.prepareStatement(sql) ;
        super.pstmt.setLong(1, vo.getSpid());
        super.pstmt.setString(2, vo.getTitle());
        super.pstmt.setDouble(3, vo.getPrice());
        super.pstmt.setDate(4, new java.sql.Date(vo.getAppdate().getTime()));
        super.pstmt.setInt(5, vo.getAmount());
        super.pstmt.setString(6, vo.getPhoto());
        super.pstmt.setInt(7, vo.getStatus());
        super.pstmt.setString(8, vo.getItem());
        super.pstmt.setString(9, vo.getNote());
        return super.pstmt.executeUpdate() > 0 ;
    }

    @Override
    public boolean doEdit(Resource vo) throws SQLException {
    	String sql = "UPDATE resource SET title=?,price=?,appdate=?,amount=?,photo=?,status=?,item=?,note=? WHERE resid=? " ;
        super.pstmt = super.conn.prepareStatement(sql) ;
        super.pstmt.setString(1, vo.getTitle());
        super.pstmt.setDouble(2, vo.getPrice());
        super.pstmt.setDate(3, new java.sql.Date(vo.getAppdate().getTime()));
        super.pstmt.setInt(4, vo.getAmount());
        super.pstmt.setString(5, vo.getPhoto());
        super.pstmt.setInt(6, vo.getStatus());
        super.pstmt.setString(7, vo.getItem());
        super.pstmt.setString(8, vo.getNote());
        super.pstmt.setLong(9, vo.getResid());
        return super.pstmt.executeUpdate() > 0 ;
    }

    @Override
    public boolean doRemove(Set<Long> ids) throws SQLException {
    	StringBuffer sql = new StringBuffer() ; // 需要进行SQL的拼凑处理
		sql.append("DELETE FROM resource WHERE mid IN (") ;
		for (Long id : ids) {
			sql.append(id).append(",") ;
		}
		sql.delete(sql.length() - 1, sql.length()) ; // 删除最后多余的“,”
		sql.append(")") ; // 完成SQL
		super.pstmt = super.conn.prepareStatement(sql.toString()) ;
		return super.pstmt.executeUpdate() > 0; 
    }

    @Override
    public Resource findById(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<Resource> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<Resource> findSplit(Long currentPage, Integer lineSize) throws SQLException {
        List<Resource> all = new ArrayList<Resource>();
        String sql = "SELECT resid,spid,title,price,appdate,amount,photo,status,item,note FROM resource WHERE status=1 LIMIT ?,?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setLong(1, (currentPage - 1) * lineSize);
        super.pstmt.setInt(2, lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Resource vo = new Resource();
            vo.setResid(rs.getLong(1));
            vo.setSpid(rs.getLong(2));
            vo.setTitle(rs.getString(3));
            vo.setPrice(rs.getDouble(4));
            vo.setAppdate(rs.getDate(5));
            vo.setAmount(rs.getInt(6));
            vo.setPhoto(rs.getString(7));
            vo.setStatus(rs.getInt(8));
            vo.setItem(rs.getString(9));
            vo.setNote(rs.getString(10));
            all.add(vo);
        }
        return all;
    }

    @Override
    public List<Resource> findSplit(Long currentPage, Integer lineSize, String column, String keyWord)
            throws SQLException {
        List<Resource> all = new ArrayList<Resource>();
        String sql = "SELECT resid,spid,title,price,appdate,amount,photo,status,item,note FROM " +
                " resource WHERE status=1 AND "
                + column + " LIKE ? LIMIT ?,?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%" + keyWord + "%");
        super.pstmt.setLong(2, (currentPage - 1) * lineSize);
        super.pstmt.setInt(3, lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Resource vo = new Resource();
            vo.setResid(rs.getLong(1));
            vo.setSpid(rs.getLong(2));
            vo.setTitle(rs.getString(3));
            vo.setPrice(rs.getDouble(4));
            vo.setAppdate(rs.getDate(5));
            vo.setAmount(rs.getInt(6));
            vo.setPhoto(rs.getString(7));
            vo.setStatus(rs.getInt(8));
            vo.setItem(rs.getString(9));
            vo.setNote(rs.getString(10));
            all.add(vo);
        }
        return all;
    }

    @Override
    public Long getAllCount() throws SQLException {
        return super.getAllCountHandle("resource"); // 根据AbstractDAO中的方法，计算“resource”数据表中的个数统计
    }

    @Override
    public Long getAllCount(String column, String keyWord) throws SQLException {
        return super.getAllCountSplitHandle("resource", column, keyWord);// 根据AbstractDAO中的方法，计算“resource”数据表中的模糊查询后的个数统计
    }

	@Override
	public Map<Long, Resource> findAllByResource(Long eresid) throws SQLException {
		Map<Long,Resource> all = new HashMap<Long,Resource>();
		String sql = " SELECT resid,spid,title,price,appdate,amount,photo,status,item,note FROM resource WHERE resid IN ( " 
				+ " SELECT resid FROM resource WHERE eresid=?) ";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, eresid);
		ResultSet rs = super.pstmt.executeQuery() ;
		while(rs.next()) {
			Resource vo = new Resource();
			vo.setResid(rs.getLong(1));
			vo.setSpid(rs.getLong(2));
			vo.setTitle(rs.getString(3));
			vo.setPrice(rs.getDouble(4));
			vo.setAppdate(rs.getDate(5));
			vo.setAmount(rs.getInt(6));
			vo.setPhoto(rs.getString(7));
			vo.setStatus(rs.getInt(8));
			vo.setItem(rs.getString(9));
			vo.setNote(rs.getString(10));
			all.put(rs.getLong(1), vo);
		}
		return all;
	}
	@Override
	public Map<Long, Resource> findAllBySupply(String eid) throws SQLException {
		return null;
	}

	@Override
	public List<Resource> resourceList(Long spid) throws SQLException {
		return null;
	}

	@Override
	public boolean doEditAmount(Long scid, Integer amount) throws SQLException {
		String sql = " UPDATE  " ;
		return false;
	}

}
