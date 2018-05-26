package cn.mldn.service.impl;

import java.util.HashMap;
import java.util.Map;

import cn.mldn.dao.IEMPResourceDetailsDAO;
import cn.mldn.dao.IResourceDAO;
import cn.mldn.service.IEMPResourceDetailsService;
import cn.mldn.util.service.abs.AbstractService;
import cn.mldn.util.web.annotation.Autowired;
import cn.mldn.util.web.annotation.Service;
import cn.mldn.vo.EMPResourceDetails;

@Service
public class EMPResourceDetailsServiceImpl extends AbstractService implements IEMPResourceDetailsService {

	@Autowired
	private IEMPResourceDetailsDAO emprdDAO ;
	@Autowired
	private IResourceDAO resourceDAO ;
	@Override
	public boolean add(EMPResourceDetails emprd) throws Exception {
		EMPResourceDetails oldemprd = this.emprdDAO.findByEMPResourceDetailsAndEMPResource(emprd.getEredid(), emprd.getResid());
		if(oldemprd == null) {
			emprd.setAmount(1);
			return this.emprdDAO.doCreate(emprd) ;
		}else {
			return this.emprdDAO.doEditAmount(oldemprd.getEredid(), 1);
		}
	}
	@Override
	public Map<String, Object> list(Long eresid) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>() ;
		map.put("allEMPResourceDetails", this.emprdDAO.findAllByEMPResourceDetails(eresid));
		map.put("allResource", this.resourceDAO.findAllByResource(eresid));
		return map;
	}

}
