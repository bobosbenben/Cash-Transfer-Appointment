package en.common.dto;

import java.io.Serializable;

public class DTO implements Serializable{

	private String gsDm;

	protected Boolean status;

	protected Long id;
	
	protected String lrrq;

	protected String bz;
	
	protected String gsMc;
	
	public String getGsMc() {
		return gsMc;
	}

	public void setGsMc(String gsMc) {
		this.gsMc = gsMc;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getGsDm() {
		return gsDm;
	}

	public void setGsDm(String gsDm) {
		this.gsDm = gsDm;
	}

	public String getLrrq() {
		return lrrq;
	}

	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}
	
}
