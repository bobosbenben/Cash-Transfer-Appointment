package en.sys.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import en.basisc.entity.LoginUser;
import en.common.entity.BaseEntity;


@Entity
@Table(name="xt_role")
public class Role extends BaseEntity{
	
	private String name;
	
	private Set<LoginUser> loginuser = new HashSet<LoginUser>();

	/**
	 * 关联登陆用户
	 * @return
	 */
	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY,mappedBy="roles")
	public Set<LoginUser> getLoginuser() {
		return loginuser;
	}

	public void setLoginuser(Set<LoginUser> loginuser) {
		this.loginuser = loginuser;
	}

	/**
	 * 属性:名称 
	 * @return
	 */
	@Column(length=50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
