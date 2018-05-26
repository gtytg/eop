package cn.mldn.service.impl;


import cn.mldn.dao.ISupplyDAO;
import cn.mldn.service.ISupplyPurchaseService;
import cn.mldn.util.service.abs.AbstractService;
import cn.mldn.util.web.annotation.Autowired;
import cn.mldn.util.web.annotation.Service;
import cn.mldn.vo.Supply;

@Service
public class SupplyPurchaseServiceImpl extends AbstractService implements ISupplyPurchaseService {
    @Autowired
    private ISupplyDAO supplyDAO;

    @Override
    public boolean add(Supply sup) throws Exception {
        return this.supplyDAO.doCreate(sup);
    }

}
