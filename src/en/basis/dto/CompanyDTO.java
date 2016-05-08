package en.basis.dto;

import en.common.dto.DmxxDTO;


public class CompanyDTO extends DmxxDTO{

	private String sjgsDm;

	private String shortName;

	private String companyType;
	
	private String postAccount;
	
	private Boolean isIndependentAccounting;
	
	private String cashAccount;
	
	private String town;
	
	private Boolean isStore;
	
	private String area;
	
	private String deepth;
	
	private String sjgsMc;
	
	private Boolean isDayknot;

	private String phone;

	private String nodeNumber;

	public String getDeepth() {
		return deepth;
	}

	public void setDeepth(String deepth) {
		this.deepth = deepth;
	}

	public String getSjgsMc() {
		return sjgsMc;
	}

	public void setSjgsMc(String sjgsMc) {
		this.sjgsMc = sjgsMc;
	}

	public String getSjgsDm() {
		return sjgsDm;
	}

	public void setSjgsDm(String sjgsDm) {
		this.sjgsDm = sjgsDm;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getPostAccount() {
		return postAccount;
	}

	public void setPostAccount(String postAccount) {
		this.postAccount = postAccount;
	}

	public Boolean getIsIndependentAccounting() {
		return isIndependentAccounting;
	}

	public void setIsIndependentAccounting(Boolean isIndependentAccounting) {
		this.isIndependentAccounting = isIndependentAccounting;
	}

	public String getCashAccount() {
		return cashAccount;
	}

	public void setCashAccount(String cashAccount) {
		this.cashAccount = cashAccount;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public Boolean getIsStore() {
		return isStore;
	}

	public void setIsStore(Boolean isStore) {
		this.isStore = isStore;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Boolean getIsDayknot() {
		return isDayknot;
	}

	public void setIsDayknot(Boolean isDayknot) {
		this.isDayknot = isDayknot;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNodeNumber() {
		return nodeNumber;
	}

	public void setNodeNumber(String nodeNumber) {
		this.nodeNumber = nodeNumber;
	}
}
