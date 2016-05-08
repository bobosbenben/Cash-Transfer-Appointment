package en.sys.dto;

import en.common.dto.DTO;

public class MenuDTO extends DTO{
	
	private String name;

    private String url;

    private Long parentId;

    private int deepth;

    private String menuIndex;

    private String menuLimits;//菜单可分配的权限，比如1111111 表示 增删改打查审反
    
    private String parentName;
    
    private String menuGroup;
    
    private String glyph;

	public String getMenuGroup() {
		return menuGroup;
	}

	public void setMenuGroup(String menuGroup) {
		this.menuGroup = menuGroup;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public int getDeepth() {
		return deepth;
	}

	public void setDeepth(int deepth) {
		this.deepth = deepth;
	}

	public String getMenuIndex() {
		return menuIndex;
	}

	public void setMenuIndex(String menuIndex) {
		this.menuIndex = menuIndex;
	}

	public String getMenuLimits() {
		return menuLimits;
	}

	public void setMenuLimits(String menuLimits) {
		this.menuLimits = menuLimits;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getGlyph() {
		return glyph;
	}

	public void setGlyph(String glyph) {
		this.glyph = glyph;
	}
}
