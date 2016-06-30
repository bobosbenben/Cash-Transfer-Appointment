package en.app.copybean.impl;

import en.app.dto.CashBackDTO;
import en.basisc.entity.CashBack;
import en.common.copybean.ICopyBean;
import en.common.copybean.impl.GridCopyBeanImpl;
import en.common.dto.DTO;

/**
 * Created by syb on 2016/6/29.
 */
public class CashBackCopyBeanImpl extends GridCopyBeanImpl implements ICopyBean {

    @Override
    public Class<?> getEntityClass() {
        return CashBack.class;
    }

    @Override
    public DTO createDTO() {
        return new CashBackDTO();
    }
}
