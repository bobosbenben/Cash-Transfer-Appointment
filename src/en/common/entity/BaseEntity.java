package en.common.entity;

import org.hibernate.annotations.Type;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity implements Serializable {
	
    private Long id = new Long(-1);

    private String gsDm;

    protected Boolean status;

    private String lrrq;

    private String xgrq;

    private Long lrrDm;

    private Long xgrDm;

    private String bz;
    
    private String bz2;
    
    /**
	 * 属性:主键
	 * 类型:Long
	 * @return
	 */
	@Id
	@GeneratedValue
	@Column(name="tid")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
	 * 属性:状态标志
	 * 类型:boolean
	 * @return
	 */
    @Column(name="status")
    @Type(type = "org.hibernate.type.BooleanType")
    public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
    
	/**
	 * 属性:录入日期
	 * 
	 * @return
	 */
	@Column(name="lrrq",length=50)
    public String getLrrq() {
        return lrrq;
    }

    public void setLrrq(String lrrq) {
        this.lrrq = lrrq;
    }

    /**
	 * 属性:修改日期
	 * 类型:Date
	 * @return
	 */
	@Column(name="xgrq",length=50)
    public String getXgrq() {
        return xgrq;
    }

    public void setXgrq(String xgrq) {
        this.xgrq = xgrq;
    }

    /**
	 * 属性:录入人代码
	 * 类型:Long
	 * @return
	 */
	@Column(name="lrrDm")
    public Long getLrrDm() {
        return lrrDm;
    }

    public void setLrrDm(Long lrrDm) {
        this.lrrDm = lrrDm;
    }

    /**
   	 * 属性:修改人代码
   	 * 类型:Long
   	 * @return
   	 */
   	@Column(name="xgrDm")
    public Long getXgrDm() {
        return xgrDm;
    }

    public void setXgrDm(Long xgrDm) {
        this.xgrDm = xgrDm;
    }

    /**
	 * 属性:备注
	 * 类型:String
	 * @return
	 */
	@Column(name="bz" ,length = 200)
    public String getBz() {
    	return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }
    /**
   	 * 属性:内部备注:一般用于程序员使用
   	 * 类型:String
   	 * @return
   	 */
   	@Column(name="bz2" ,length = 200)
    public String getBz2() {
        return bz2;
    }

    public void setBz2(String bz2) {
        this.bz2 = bz2;
    }
    

    /**
	 * 属性:公司代码
	 * 类型:String
	 * @return
	 */
	@Column(name="gsdm" ,length = 50)
    public String getGsDm() {
        return gsDm;
    }

    public void setGsDm(String gsDm) {
        this.gsDm = gsDm;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        String cls = getClass().getName();
        String cln = cls.substring(cls.lastIndexOf('.') + 1);

        buffer.append("[" + cln + "] ");
        buffer.append("id = " + this.id + ", ");
        buffer.append("lrrq = " + this.lrrq + ", ");
        buffer.append("lrrdm = " + this.lrrDm + ", ");
        buffer.append("xgrq = " + this.xgrq + ", ");
        buffer.append("xgrdm = " + this.xgrDm + ", ");
        buffer.append("bz = " + this.bz + ", ");
        return buffer.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof BaseEntity)) {
            return false;
        }

        if ((getClass().isAssignableFrom(obj.getClass()))
                || (obj.getClass().isAssignableFrom(getClass()))) {

        } else {
            return false;
        }

        BaseEntity entity = (BaseEntity) obj;
        if (entity.getId().equals(getId())) {
            return true;
        } else {
            return false;
        }
    }

    protected StringBuffer getFormatString2(String name, String value, boolean b) {
        StringBuffer sb = new StringBuffer();
        sb.append("\"" + name + "\":\"").append(value).append("\"");
        if (b)
            sb.append(",");
        return sb;
    }

}