package en.common.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass //属性将映射到子类数据库中
public class Dmxx extends BaseEntity{
	
	private String zjm;  //用户名，登录时候用的用户名

	private String name;

	/**
	 * 属性:注记码，用户名
	 * 
	 * @return
	 */
	@Column(name="zjm",length=50)
	public String getZjm() {
		return zjm;
	}

	public void setZjm(String zjm) {
		this.zjm = zjm;
	}
	/**
	 * 属性:名称
	 * 
	 * @return
	 */
	@Column(name="name",length=100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
