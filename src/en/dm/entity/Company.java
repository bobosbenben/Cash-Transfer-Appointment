package en.dm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import en.common.entity.Dmxx;

@Entity
@Table(name="dm_company")
public class Company  extends Dmxx{
	
	private String sjgsDm;

	private String hsgsDm;

	private String shortName;

	private String companyType;
	
	private String postAccount;
	
	private Boolean isIndependentAccounting;
	
	private String cashAccount;
	
	private String town;
	
	private Boolean isStore;
	
	private Boolean isDayknot;
	
	private String area;
	
	private Long deepth;

	private String nodeNumber;

	private String phone;

	/**
	 * 属性:上级公司代码
	 * @return
	 */
	@Column(name="sj_gsdm",length=100)
	public String getSjgsDm() {
		return sjgsDm;
	}

	public void setSjgsDm(String sjgsDm) {
		this.sjgsDm = sjgsDm;
	}

	/**
	 * 属性:核算公司代码
	 * @return
	 */
	@Column(name="hs_gsdm",length=100)
	public String getHsgsDm() {
		return hsgsDm;
	}

	public void setHsgsDm(String hsgsDm) {
		this.hsgsDm = hsgsDm;
	}

	/**
	 * 属性:简称
	 * @return
	 */
	@Column(name="short_name",length=100)
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * 属性:公司类型
	 * @return
	 */
	@Column(name="company_type",length=50)
	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	/**
	 * 属性:刷卡账户
	 * @return
	 */
	@Column(name="post_account",length=50)
	public String getPostAccount() {
		return postAccount;
	}

	public void setPostAccount(String postAccount) {
		this.postAccount = postAccount;
	}

	/**
	 * 属性:是否独立核算
	 * @return
	 */
	@Column(name="is_independent_accounting")
	public Boolean getIsIndependentAccounting() {
		return isIndependentAccounting;
	}

	public void setIsIndependentAccounting(Boolean isIndependentAccounting) {
		this.isIndependentAccounting = isIndependentAccounting;
	}
	/**
	 * 属性:现金账户
	 * @return
	 */
	@Column(name="cash_account",length=50)
	public String getCashAccount() {
		return cashAccount;
	}

	public void setCashAccount(String cashAccount) {
		this.cashAccount = cashAccount;
	}
	/**
	 * 属性:城镇
	 * @return
	 */
	@Column(name="town",length=50)
	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	/**
	 * 属性:是否店面
	 * @return
	 */
	@Column(name="is_store")
	public Boolean getIsStore() {
		return isStore;
	}

	public void setIsStore(Boolean isStore) {
		this.isStore = isStore;
	}

	/**
	 * 属性:区域
	 * @return
	 */
	@Column(name="area",length=50)
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
/**
 * 树弄菜单所用deepth
 * @return
 */
	@Column(name="deepth")
	public Long getDeepth() {
		return deepth;
	}

	public void setDeepth(Long deepth) {
		this.deepth = deepth;
	}
	/**
	 * 属性:是否需要日结
	 * @return
	 */
	@Column(name="is_dayknot")
	public Boolean getIsDayknot() {
		return isDayknot;
	}

	public void setIsDayknot(Boolean isDayknot) {
		this.isDayknot = isDayknot;
	}
	/**
	 * 属性:网点号
	 * @return
	 */
	@Column(name="node_number")
	public String getNodeNumber() {return nodeNumber;}

	public void setNodeNumber(String nodeNumber) {
		this.nodeNumber = nodeNumber;
	}
	/**
	 * 属性:联系电话
	 * @return
	 */
	@Column(name="phone")
	public String getPhone() {return phone;}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
