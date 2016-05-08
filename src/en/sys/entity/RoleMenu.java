package en.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import en.common.entity.BaseEntity;

@Entity
@Table(name="xt_role_menu")
public class RoleMenu extends BaseEntity{
	
	private Role role;

    private Menus menu;

    private String operateAdd;

    private String operateEdit;

    private String operateDelete;

    private String operatePrint;

    private String operateSearch;
    
    private String operateAudit;
    
    private String operateUndoAudit;

    /**
     * 属性:新建
     * @return
     */
    @Column(name="operate_add",length=1)
    public String getOperateAdd() {
        return operateAdd;
    }

    public void setOperateAdd(String operateAdd) {
        this.operateAdd = operateAdd;
    }

    /**
     * 属性:删除
     * @return
     */
    @Column(name="operate_delete",length=1)
    public String getOperateDelete() {
        return operateDelete;
    }

    public void setOperateDelete(String operateDelete) {
        this.operateDelete = operateDelete;
    }

    /**
     * 属性:修改
     * @return
     */
    @Column(name="operate_edit",length=1)
    public String getOperateEdit() {
        return operateEdit;
    }

    public void setOperateEdit(String operateEdit) {
        this.operateEdit = operateEdit;
    }

    /**
     * 属性:打印
     * @return
     */
    @Column(name="operate_print",length=1)
    public String getOperatePrint() {
        return operatePrint;
    }

    public void setOperatePrint(String operatePrint) {
        this.operatePrint = operatePrint;
    }

    /**
     * 属性:查询
     * @return
     */
    @Column(name="operate_search",length=1)
    public String getOperateSearch() {
        return operateSearch;
    }

    public void setOperateSearch(String operateSearch) {
        this.operateSearch = operateSearch;
    }

    /**
     * 属性:角色关联
     * @return
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="role_id")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * 属性:菜单关联
     * @return
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="menu_id")
    public Menus getMenu() {
        return menu;
    }

    public void setMenu(Menus menu) {
        this.menu = menu;
    }

    /**
     * 属性:审核
     * @return
     */
    @Column(name="operate_audit",length=1)
	public String getOperateAudit() {
		return operateAudit;
	}

	public void setOperateAudit(String operateAudit) {
		this.operateAudit = operateAudit;
	}
	 /**
     * 属性:反审
     * @return
     */
    @Column(name="operate_undo_audit",length=1)
	public String getOperateUndoAudit() {
		return operateUndoAudit;
	}

	public void setOperateUndoAudit(String operateUndoAudit) {
		this.operateUndoAudit = operateUndoAudit;
	}
}
