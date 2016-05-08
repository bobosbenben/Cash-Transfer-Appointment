package en.app.copybean.impl;

import en.app.dto.AppointmentDTO;
import en.app.dto.CashDTO;
import en.basisc.entity.Appointment;
import en.basisc.entity.Cash;
import en.common.copybean.ICopyBean;
import en.common.copybean.impl.GridCopyBeanImpl;
import en.common.dto.DTO;

/**
 * Created by syb on 2016/2/12.
 */
public class CashCopyBeanImpl extends GridCopyBeanImpl implements ICopyBean {

    @Override
    public Class<?> getEntityClass() {
        return Cash.class;
    }

    @Override
    public DTO createDTO() {
        return new CashDTO();
    }
}
