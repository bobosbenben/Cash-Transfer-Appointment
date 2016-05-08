package en.sys.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import en.common.entity.BaseEntity;

@Entity
@Table(name="xt_menu")
public class Menus extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	private String name;

    private String url;

    private Long parentId;

    private int deepth;

    private String menuIndex;

    private String menuGroup;
    
    private String menuLimits;//菜单可分配的权限，比如1111111 表示 增删改打查审反
    
    private String glyph;

	/**
     * 属性:名称
     * @return
     */
    @Column(name="name",length=100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
     * 属性:菜单资源url
     * @return
     */
    @Column(name="url",length=100)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
     * 属性:父id
     * @return
     */
    @Column(name="parent_id")
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
     * 属性:菜单级数
     * @return
     */
    @Column(name="deepth")
	public int getDeepth() {
		return deepth;
	}

	public void setDeepth(int deepth) {
		this.deepth = deepth;
	}
	/**
     * 属性:排序编码
     * @return
     */
    @Column(name="menuIndex",length=50)
	public String getMenuIndex() {
		return menuIndex;
	}

	public void setMenuIndex(String menuIndex) {
		this.menuIndex = menuIndex;
	}
	/**
     * 属性:菜单组
     * @return
     */
    @Column(name="menugroup",length=50)
	public String getMenuGroup() {
		return menuGroup;
	}

	public void setMenuGroup(String menuGroup) {
		this.menuGroup = menuGroup;
	}
	/**
     * 属性:菜单权限0显示,1不显示
     * @return
     */
    @Column(name="menu_limits",length=50)

    public String getMenuLimits() {
		return menuLimits;
	}

	public void setMenuLimits(String menuLimits) {
		this.menuLimits = menuLimits;
	}

	/**
     * 属性:Font-Awesome 样式图标 
     * @return
     */
    @Column(name="glyph",length=10)
	public String getGlyph() {
		return glyph;
	}

	public void setGlyph(String glyph) {
		this.glyph = glyph;
	}
	
	
}
