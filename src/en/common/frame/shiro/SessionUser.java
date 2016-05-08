package en.common.frame.shiro;

import java.io.Serializable;

import en.basisc.entity.LoginUser;
import en.basisc.entity.User;
import en.dm.entity.Company;


public class SessionUser implements Serializable {

	private static final long serialVersionUID = 1L;

    private Long id;
	
	private Long loginId;

	private String sessionId;

	private String loginAccount;

	private String userName;
	
	private String gsdm;

	private String gsMc;
	
	private String bigGsdm;

	private Integer searchDay;// 查询天数

	private Boolean isLookPrice;// 查看价格
	
	private Boolean isLookWage;//查看工资权限
	
	//private Boolean isLookCommpany;//售后查看分公司权限

	private Boolean isModifyPrice;// 修改别人的单据

	//private String addr;
	//private String loginTime;

	private String limitsCompanys;
	
	private String hsgsDm;   //核算公司代码
	
	//private Boolean isDayknot;
	
	//private String numberFormat;
	
	//private Boolean isIntegerMgr;

	public String getHsgsDm() {
		return hsgsDm;
	}

	public void setHsgsDm(String hsgsDm) {
		this.hsgsDm = hsgsDm;
	}
	public SessionUser(String sessionId, LoginUser luser,String bigGsdm, Company company) throws Exception{
		this.bigGsdm = bigGsdm;
		this.loginId=luser.getId();
		this.loginAccount = luser.getLoginAccount();
		User user = luser.getUser();
		this.id= user.getId();
		this.userName=user.getName();
		this.sessionId = sessionId;
		this.gsdm = luser.getGsDm();
		Integer ii = luser.getSearchDay();
		if (ii == null)
			this.searchDay = 0;
		else
			this.searchDay=ii;
		this.isLookPrice = luser.getIsLookPrice();
		this.isLookWage = luser.getIsLookWage();
		this.isModifyPrice = luser.getIsModifyPrice();
		//this.isLookCommpany = luser.getIsLookCommpany();
		this.limitsCompanys = luser.getLimitsCompanys();
		if(company.getGsDm().equals(bigGsdm))
			this.hsgsDm = bigGsdm;
		else
		  this.hsgsDm = company.getHsgsDm();
		
		this.gsMc = company.getName();
		//this.isDayknot = company.getIsDayknot();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLoginId() {
		return loginId;
	}

	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGsdm() {
		return gsdm;
	}

	public void setGsdm(String gsdm) {
		this.gsdm = gsdm;
	}

	public String getBigGsdm() {
		return bigGsdm;
	}

	public void setBigGsdm(String bigGsdm) {
		this.bigGsdm = bigGsdm;
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

	public Boolean getIsModifyPrice() {
		return isModifyPrice;
	}

	public void setIsModifyPrice(Boolean isModifyPrice) {
		this.isModifyPrice = isModifyPrice;
	}

	public String getGsMc() {
		return gsMc;
	}

	public void setGsMc(String gsMc) {
		this.gsMc = gsMc;
	}

	public String getLimitsCompanys() {
		return limitsCompanys;
	}

	public void setLimitsCompanys(String limitsCompanys) {
		this.limitsCompanys = limitsCompanys;
	}

	
}
