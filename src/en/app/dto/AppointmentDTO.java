package en.app.dto;

import en.common.dto.DmxxDTO;
import java.util.Date;

public class AppointmentDTO extends DmxxDTO{

    private String name;      //账户名称

    private String account;   //账号

    private Double number;    //金额

    private String phone;     //联系电话

    private Date date;        //支取日期

    private String way;       //支取途径

    private String remark;    //备注

    private String state;    //状态

    private String nodeNumber;//网点号

    private String reserve;     //做出预约的员工

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNodeNumber() {return nodeNumber;}

    public void setNodeNumber(String nodeNumber) {this.nodeNumber = nodeNumber;}

    public String getReserve() {return reserve;}

    public void setReserve(String reserve) {this.reserve = reserve;}

}
