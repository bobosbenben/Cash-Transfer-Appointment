package en.app.copybean.impl;

import en.app.dto.AppointmentDTO;
import en.basisc.entity.Appointment;
import en.common.copybean.ICopyBean;
import en.common.copybean.impl.GridCopyBeanImpl;
import en.common.dto.DTO;


public class AppointmentCopyBeanImpl extends GridCopyBeanImpl implements ICopyBean {

    @Override
    public Class<?> getEntityClass() {
        return Appointment.class;
    }

    @Override
    public DTO createDTO() {
        return new AppointmentDTO();
    }
}
