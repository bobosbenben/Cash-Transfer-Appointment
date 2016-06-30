package en.basisc.entity;

import en.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by syb on 2016/6/29.
 */
@Entity
@Table(name="xt_cashback")
public class CashBack extends BaseEntity {

    private static final long serialVersionUID = 1L;//大概是没有用到的字段

    private Double number;    //金额

    private String nodeNumber;//网点号

    private Date date;        //支取日期

    private String staff;     //预约人

    private String remark;    //备注

    private String state;    //状态

    private String reserve;   //保留字段

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
     * 属性:预约人
     * @return
     */
    @Column(name="staff",length=64)
    public String getStaff() {
        return staff;
    }
    public void setStaff(String staff) {
        this.staff = staff;
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
