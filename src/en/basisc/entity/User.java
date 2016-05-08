package en.basisc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import en.common.entity.Dmxx;


@Entity
@Table(name="xt_user")
public class User extends Dmxx{
	
    private String gsMc;//公司名称
	
	private String dept;//部门
	
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

	/**
	 * 属性:银行
	 * @return
	 */
	@Column(name="gsmc",length=100)
	public String getGsMc() {
		return gsMc;
	}
	public void setGsMc(String gsMc) {
		this.gsMc = gsMc;
	}
	
	/**
	 * 属性:地址
	 * @return
	 */
	@Column(name="address",length=100)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 属性:生日
	 * @return
	 */
	@Column(name="birthday",length=10)
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
	 * 属性:银行
	 * @return
	 */
	@Column(name="entry_date",length=10)
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	
	/**
	 * 属性:身份证号码
	 * @return
	 */
	@Column(name="id_number",length=20)
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	/**
	 * 属性:电话
	 * @return
	 */
	@Column(name="phone",length=20)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 属性:离职日期
	 * @return
	 */
	@Column(name="resignation_date",length=10)
	public String getResignationDate() {
		return resignationDate;
	}
	public void setResignationDate(String resignationDate) {
		this.resignationDate = resignationDate;
	}
	/**
	 * 属性:性别
	 * @return
	 */
	@Column(name="sex",length=2)
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 属性:银行
	 * @return
	 */
	@Column(name="bank",length=100)
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	/**
	 * 属性:银行账号
	 * @return
	 */
	@Column(name="bank_account",length=100)
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	/**
	 * 属性:基本工资
	 * @return
	 */
	@Column(name="basic_wage",length=100)
	public Double getBasicWage() {
		return basicWage;
	}
	public void setBasicWage(Double basicWage) {
		this.basicWage = basicWage;
	}
	
	/**
	 * 属性:担保人
	 * @return
	 */
	@Column(name="guarantor",length=100)
	public String getGuarantor() {
		return guarantor;
	}
	public void setGuarantor(String guarantor) {
		this.guarantor = guarantor;
	}
	/**
	 * 属性:担保人关系
	 * @return
	 */
	@Column(name="guarantor_relations",length=10)
	public String getGuarantorRelations() {
		return guarantorRelations;
	}
	public void setGuarantorRelations(String guarantorRelations) {
		this.guarantorRelations = guarantorRelations;
	}
	/**
	 * 属性:身份证地址
	 * @return
	 */
	@Column(name="id_addr",length=100)
	public String getIdAddr() {
		return idAddr;
	}
	public void setIdAddr(String idAddr) {
		this.idAddr = idAddr;
	}
	/**
	 * 属性:是否已婚
	 * @return
	 */
	@Column(name="is_marital")
	public Boolean getIsMarital() {
		return isMarital;
	}

	public void setIsMarital(Boolean isMarital) {
		this.isMarital = isMarital;
	}
	/**
	 * 属性:民族
	 * @return
	 */
	@Column(name="nation",length=50)
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	/**
	 * 属性:离职类型
	 * @return
	 */
	@Column(name="resignation_type",length=10)
	public String getResignationType() {
		return resignationType;
	}
	public void setResignationType(String resignationType) {
		this.resignationType = resignationType;
	}
	
	/**
	 * 属性:是否离职
	 * @return
	 */
	@Column(name="is_resignation")
	public Boolean getIsResignation() {
		return isResignation;
	}

	public void setIsResignation(Boolean isResignation) {
		this.isResignation = isResignation;
	}
	/**
	 * 属性:是否转正
	 * @return
	 */
	@Column(name="is_positive")
	public Boolean getIsPositive() {
		return isPositive;
	}
	public void setIsPositive(Boolean isPositive) {
		this.isPositive = isPositive;
	}
	/**
	 * 属性:社保日期
	 * @return
	 */
	@Column(name="social_security_date",length=10)
	public String getSocialSecurityDate() {
		return socialSecurityDate;
	}

	public void setSocialSecurityDate(String socialSecurityDate) {
		this.socialSecurityDate = socialSecurityDate;
	}
	/**
	 * 属性:转正日期
	 * @return
	 */
	@Column(name="zzrq",length=10)
	public String getZzrq() {
		return zzrq;
	}
	public void setZzrq(String zzrq) {
		this.zzrq = zzrq;
	}
	/**
	 * 属性:学历
	 * @return
	 */
	@Column(name="diploma",length=20)
	public String getDiploma() {
		return diploma;
	}

	public void setDiploma(String diploma) {
		this.diploma = diploma;
	}
	/**
	 * 属性:岗位
	 * @return
	 */
	@Column(name="post",length=50)
	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	/**
	 * 属性:是否签订劳动合同
	 * @return
	 */
	@Column(name="is_laborcontract")
	public Boolean getIsLaborcontract() {
		return isLaborcontract;
	}

	public void setIsLaborcontract(Boolean isLaborcontract) {
		this.isLaborcontract = isLaborcontract;
	}

	/**
	 * 属性:是否签订劳动合同
	 * @return
	 */
	@Column(name="laborcontract_no" ,length=100)
	public String getLaborcontractNo() {
		return laborcontractNo;
	}

	public void setLaborcontractNo(String laborcontractNo) {
		this.laborcontractNo = laborcontractNo;
	}
	
	/**
	 * 属性:部门
	 * @return
	 */
	@Column(name="dept" ,length=100)
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
}
