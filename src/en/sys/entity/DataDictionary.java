package en.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import en.common.entity.BaseEntity;
@Entity
@Table(name="xt_datadictionary")
public class DataDictionary extends BaseEntity {

	  private String data;
	  
	  private String dataType;
	  
	  private String whereBz;

	 /**
	   * 属性:数据
	   * @return
	   */
	  @Column(name="data",length=100)
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	 /**
	   * 属性:系统标识
	   * @return
	   */
	  @Column(name="where_bz",length=20)
	public String getWhereBz() {
		return whereBz;
	}

	public void setWhereBz(String whereBz) {
		this.whereBz = whereBz;
	}
	 /**
	   * 属性:数据类型
	   * @return
	   */
	  @Column(name="data_type",length=100)
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
}
