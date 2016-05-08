package en.basis.dto;

import en.common.dto.DmxxDTO;


public class UserDTO extends DmxxDTO{

	private String idNumber; //身份证号码

	private String phone; //电话号码

	private String sex; //性别

	private String birthday; //出生日期

	private String address; //家庭地址

	private String entryDate; //入职日期

	private String resignationDate; //离职日期

	private Double basicWage; //基本工资
	private String bank; //
	private String bankAccount; //银行账号 
	private Boolean isPositive; 
	private String zzrq; //转正日期
	private String socialSecurityDate; //社保日期
	private Boolean isResignation; //是否离职
	private String resignationType; //离职类型
	
	
	private Boolean isMarital; //是否已婚
	private String nation; //民族
	private String idAddr; //身份证地址
	private String guarantor; //担保人
	private String guarantorRelations; //担保人关系
	private String diploma;//学历
	private String post;//岗位
	private Boolean isLaborcontract;//劳动合同
	
	private String laborcontractNo;
	
	private String loginName ;
	
	private String relationLogin;
	
	private String loginpwd;
	
	private String dept;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRelationLogin() {
		return relationLogin;
	}

	public void setRelationLogin(String relationLogin) {
		this.relationLogin = relationLogin;
	}

	public String getLoginpwd() {
		return loginpwd;
	}

	public void setLoginpwd(String loginpwd) {
		this.loginpwd = loginpwd;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getResignationDate() {
		return resignationDate;
	}

	public void setResignationDate(String resignationDate) {
		this.resignationDate = resignationDate;
	}

	public Double getBasicWage() {
		return basicWage;
	}

	public void setBasicWage(Double basicWage) {
		this.basicWage = basicWage;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public Boolean getIsPositive() {
		return isPositive;
	}

	public void setIsPositive(Boolean isPositive) {
		this.isPositive = isPositive;
	}

	public String getZzrq() {
		return zzrq;
	}

	public void setZzrq(String zzrq) {
		this.zzrq = zzrq;
	}

	public String getSocialSecurityDate() {
		return socialSecurityDate;
	}

	public void setSocialSecurityDate(String socialSecurityDate) {
		this.socialSecurityDate = socialSecurityDate;
	}

	public Boolean getIsResignation() {
		return isResignation;
	}

	public void setIsResignation(Boolean isResignation) {
		this.isResignation = isResignation;
	}

	public String getResignationType() {
		return resignationType;
	}

	public void setResignationType(String resignationType) {
		this.resignationType = resignationType;
	}

	public Boolean getIsMarital() {
		return isMarital;
	}

	public void setIsMarital(Boolean isMarital) {
		this.isMarital = isMarital;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getIdAddr() {
		return idAddr;
	}

	public void setIdAddr(String idAddr) {
		this.idAddr = idAddr;
	}

	public String getGuarantor() {
		return guarantor;
	}

	public void setGuarantor(String guarantor) {
		this.guarantor = guarantor;
	}

	public String getGuarantorRelations() {
		return guarantorRelations;
	}

	public void setGuarantorRelations(String guarantorRelations) {
		this.guarantorRelations = guarantorRelations;
	}

	public String getDiploma() {
		return diploma;
	}

	public void setDiploma(String diploma) {
		this.diploma = diploma;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Boolean getIsLaborcontract() {
		return isLaborcontract;
	}

	public void setIsLaborcontract(Boolean isLaborcontract) {
		this.isLaborcontract = isLaborcontract;
	}

	public String getLaborcontractNo() {
		return laborcontractNo;
	}

	public void setLaborcontractNo(String laborcontractNo) {
		this.laborcontractNo = laborcontractNo;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
}
