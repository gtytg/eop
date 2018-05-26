package cn.mldn.service.impl;

import java.util.HashMap;
import java.util.Map;

import cn.mldn.dao.IResourceDAO;
import cn.mldn.service.IResourceService;
import cn.mldn.util.service.abs.AbstractService;
import cn.mldn.util.web.annotation.Autowired;
import cn.mldn.util.web.annotation.Service;
import cn.mldn.vo.Resource;

import java.util.Map;

import java.util.Date;

@Service
public class ResourceServiceImpl extends AbstractService implements IResourceService {

	@Autowired
	private IResourceDAO resourceDAO;
	
	@Override
	public Map<String, Object> list(String column, String keyWord, long currentPage, int lineSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (super.isSearch(column, keyWord)) { // 需要进行模糊查询
			map.put("allResource", this.resourceDAO.findSplit(currentPage, lineSize, column, keyWord));
			map.put("allRecorders", this.resourceDAO.getAllCount(column, keyWord));
		} else {
			map.put("allResource", this.resourceDAO.findSplit(currentPage, lineSize));
			map.put("allRecorders", this.resourceDAO.getAllCount());
		}
		return map;
	}

    @Override
    public boolean add(Resource res) throws Exception {
        boolean flag = false;
        if (res == null) {
            return flag;
        }
        res.setAppdate(new Date());
        if (this.resourceDAO.doCreate(res)) {
            flag = this.resourceDAO.doCreate(res);
        }
        return flag;
    }
    @Override
    public boolean edit(Resource res) throws Exception {
        return false;
    }
}
