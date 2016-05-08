package en.basisc.entity;

import en.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="xt_appointment")
public class Appointment extends BaseEntity{

    private static final long serialVersionUID = 1L;//大概是没有用到的字段

    private String name;      //账户名称

    private String account;   //账号

    private Double number;    //金额

    private String phone;     //联系电话

    private String nodeNumber;//网点号

    private Date date;        //支取日期

    private String way;       //支取途径

    private String remark;    //备注

    private String state;    //状态

    private String reserve;   //保留字段

    /**
     * 属性:账户名称
     * @return
     */
    @Column(name="name",length=255)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 属性:账号
     * @return
     */
    @Column(name="account",length=24)
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 属性:金额
     * @return
     */
    @Column(name="number")
    public double getNumber() {
        return number;
    }
    public void setNumber(double number) {
        this.number = number;
    }

    /**
     * 属性:联系电话
     * @return
     */
    @Column(name="phone",length=24)
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 属性:预约网点
     * @return
     */
    @Column(name="node_number",length=20)
    public String getNodeNumber() {
        return nodeNumber;
    }
    public void setNodeNumber(String nodeNumber) {
        this.nodeNumber = nodeNumber;
    }
    /**
     * 属性:支取日期
     * @return
     */
    @Column(name="date")
    public Date getDate() {return date;}
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * 属性:支取途径
     * @return
     */
    @Column(name="way",length=64)
    public String getWay() {
        return way;
    }
    public void setWay(String way) {
        this.way = way;
    }

    /**
     * 属性:账户名称
     * @return
     */
    @Column(name="remark",length=1024)
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 属性:状态
     * @return
     */
    @Column(name="state",length=24)
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 属性:保留字段
     * @return
     */
    @Column(name="reserve",length=255)
    public String getReserve() {
        return reserve;
    }
    public void setReserve(String reserve) {
        this.reserve = reserve;
    }
}