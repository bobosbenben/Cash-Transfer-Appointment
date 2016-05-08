package en.common.util.helper;

public enum ExtjsCssEnum {

	
	pcNeptune("pcneptune/pc-theme-neptune-all"),mobileNeptune("mobile-ext-theme-neptune-all"),pcClassic("pc-theme-classic-all"),mobileClassic("theme-cupertino-classic-all");
	private String value;
	
	private ExtjsCssEnum(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
