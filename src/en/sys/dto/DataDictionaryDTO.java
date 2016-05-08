package en.sys.dto;

import en.common.dto.DTO;

public class DataDictionaryDTO extends DTO{

	private String data;
	  
	private String dataType;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	  
}
