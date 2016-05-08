package en.basisc.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import en.common.entity.BaseEntity;
import en.sys.entity.Role;


@Entity
@Table(name="xt_loginuser")
public class LoginUser extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private String loginAccount;

	private String name;

	private String loginPassword;

	private String ip;
	
	private String curLogintime;

	private Integer searchDay;// 查询天数

	private Boolean isLookPrice;// 查看价格
	
	private Boolean isLookWage;//查看工资权限
	
	private Boolean isLookCommpany;//售后查看分公司权限

	private Boolean isModifyPrice;// 修改别人的单据

	private Set<Role> roles = new HashSet<Role>();
	
	private User user;

	private String limitsCompanys;// 权限公司代码，比如1,2,3之类。
	
	private Boolean isIndependentRole;

	/**
	 *  属性:名称 
	 * @return
	 */
	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 *  属性:登陆账号 
	 * @return
	 */
	@Column(name="login_account",length=50)
	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	/**
	 * 属性:登陆密码
	 * @return
	 */
	@Column(name="login_password",length=50)
	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	/**
	 *  属性:登陆IP
	 * @return
	 */
	@Column(length=20)
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="xt_loginuser_role",joinColumns={@JoinColumn(name="loginuser_id")},inverseJoinColumns={@JoinColumn(name="role_id")})
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	/**
	 * 属性:最近登陆时间
	 * @return
	 */
	@Column(name="cur_login_time",length=10)
	public String getCurLogintime() {
		return curLogintime;
	}

	public void setCurLogintime(String curLogintime) {
		this.curLogintime = curLogintime;
	}

	/**
	 * 属性:查询开数
	 * @return
	 */
	@Column(name="search_day")
	public Integer getSearchDay() {
		return searchDay;
	}

	public void setSearchDay(Integer searchDay) {
		this.searchDay = searchDay;
	}

	/**
	 * 属性:是否查看价格
	 * @return
	 */
	@Column(name="is_look_price")
	public Boolean getIsLookPrice() {
		return isLookPrice;
	}

	public void setIsLookPrice(Boolean isLookPrice) {
		this.isLookPrice = isLookPrice;
	}
   /**
    * 属性:是否查看工资
    * @return
    */
	@Column(name="is_look_wage")
	public Boolean getIsLookWage() {
		return isLookWage;
	}

	public void setIsLookWage(Boolean isLookWage) {
		this.isLookWage = isLookWage;
	}
	  /**
	    * 属性:是否查分公司
	    * @return
	    */
		@Column(name="is_look_company")
	public Boolean getIsLookCommpany() {
		return isLookCommpany;
	}

	public void setIsLookCommpany(Boolean isLookCommpany) {
		this.isLookCommpany = isLookCommpany;
	}
	/**
	 * 属性:是否修改价格
	 * @return
	 */
	@Column(name="is_modify_price")
	public Boolean getIsModifyPrice() {
		return isModifyPrice;
	}

	public void setIsModifyPrice(Boolean isModifyPrice) {
		this.isModifyPrice = isModifyPrice;
	}

	/**
	 * 属性:权限公司
	 * @return
	 */
	@Column(name="limits_companys",length=200)
	public String getLimitsCompanys() {
		return limitsCompanys;
	}

	public void setLimitsCompanys(String limitsCompanys) {
		this.limitsCompanys = limitsCompanys;
	}
	/**
	 * 属性:关联用户
	 * @return
	 */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 属性:是否独立权限
	 * @return
	 */
	@Column(name="is_independent_role")
	public Boolean getIsIndependentRole() {
		return isIndependentRole;
	}

	public void setIsIndependentRole(Boolean isIndependentRole) {
		this.isIndependentRole = isIndependentRole;
	}
	

}
