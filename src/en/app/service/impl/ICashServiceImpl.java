package en.app.service.impl;

import en.app.copybean.impl.CashCopyBeanImpl;
import en.app.service.ICashService;
import en.common.copybean.ICopyBean;
import en.common.dto.DTO;
import en.common.entity.BaseEntity;
import en.common.frame.shiro.SessionUser;
import en.common.service.impl.GridServiceImpl;
import en.common.util.helper.LongUtil;
import en.common.util.helper.ResultEntity;
import en.common.util.helper.StringUtil;
import org.springframework.stereotype.Service;

/**
 * Created by syb on 2016/2/12.
 */

@Service
public class ICashServiceImpl extends GridServiceImpl implements ICashService{

    @Override
    protected ICopyBean getCopyBean() {
        CashCopyBeanImpl ccb = new CashCopyBeanImpl();
        ccb.setGridDao(gridDao);
        return ccb;
    }


    @Override
    public void updateBillByDTO(SessionUser sessionuser, DTO dto) throws Exception {
        ResultEntity re = createResultEntity();
        BaseEntity bill = null;
        ICopyBean cb = getCopyBean();
        if (LongUtil.isNewId(dto.getId())) {
            bill = (BaseEntity) cb.getEntityClass().newInstance();
        } else {
            bill = (BaseEntity)gridDao.getById(cb.getEntityClass(), dto.getId());
        }
        cb.copyDTO2Entity(sessionuser, bill, dto);
        gridDao.save(bill);
        re.setResultType(0);
    }

    public void remove(SessionUser sessionUser,String selectedId) throws Exception{

        //更新rolemenu
        selectedId = StringUtil.realStr(selectedId);
        //String rolemenusql = "delete from xt_rolemenu where roleid  in("+selectedId+")";
        //gridDao.executeSql(rolemenusql);
        //更新role
        //String menusql = "delete from xt_role where tid in("+selectedId+")";
        //gridDao.updateSql(menusql);
        super.remove(sessionUser, selectedId);
    }

    /*********************重写***************/
    public void updateBill(SessionUser sessionuser, String data) throws Exception {
        super.updateBill(sessionuser, data);
    }
}
