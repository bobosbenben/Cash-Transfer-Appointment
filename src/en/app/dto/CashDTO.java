package en.app.dto;

import en.common.dto.DmxxDTO;

import java.util.Date;

/**
 * Created by syb on 2016/2/12.
 */
public class CashDTO extends DmxxDTO {

    private Double number;    //金额

    private Date date;        //支取日期

    private String remark;    //备注

    private String state;    //状态

    private String nodeNumber;//网点号

    private String staff;     //做出预约的员工

    private String reserve;

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNodeNumber() {
        return nodeNumber;
    }

    public void setNodeNumber(String nodeNumber) {
        this.nodeNumber = nodeNumber;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }
}
