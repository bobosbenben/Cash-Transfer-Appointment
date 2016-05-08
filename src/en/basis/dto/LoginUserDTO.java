package en.basis.dto;


import en.common.dto.DmxxDTO;

public class LoginUserDTO extends DmxxDTO{

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

	private String roleString;
	
	private String limitsCompanys;// 权限公司代码，比如1,2,3之类。

	
	private Boolean isIndependentRole;
	
	public Boolean getIsIndependentRole() {
		return isIndependentRole;
	}

	public void setIsIndependentRole(Boolean isIndependentRole) {
		this.isIndependentRole = isIndependentRole;
	}

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCurLogintime() {
		return curLogintime;
	}

	public void setCurLogintime(String curLogintime) {
		this.curLogintime = curLogintime;
	}

	public Integer getSearchDay() {
		return searchDay;
	}

	public void setSearchDay(Integer searchDay) {
		this.searchDay = searchDay;
	}

	public Boolean getIsLookPrice() {
		return isLookPrice;
	}

	public void setIsLookPrice(Boolean isLookPrice) {
		this.isLookPrice = isLookPrice;
	}

	public Boolean getIsLookWage() {
		return isLookWage;
	}

	public void setIsLookWage(Boolean isLookWage) {
		this.isLookWage = isLookWage;
	}

	public Boolean getIsLookCommpany() {
		return isLookCommpany;
	}

	public void setIsLookCommpany(Boolean isLookCommpany) {
		this.isLookCommpany = isLookCommpany;
	}

	public Boolean getIsModifyPrice() {
		return isModifyPrice;
	}

	public void setIsModifyPrice(Boolean isModifyPrice) {
		this.isModifyPrice = isModifyPrice;
	}

	public String getLimitsCompanys() {
		return limitsCompanys;
	}

	public void setLimitsCompanys(String limitsCompanys) {
		this.limitsCompanys = limitsCompanys;
	}

	public String getRoleString() {
		return roleString;
	}

	public void setRoleString(String roleString) {
		this.roleString = roleString;
	}
}
