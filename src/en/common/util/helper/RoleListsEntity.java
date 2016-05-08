package en.common.util.helper;

import en.common.entity.BaseEntity;

public class RoleListsEntity extends BaseEntity{
	
	   private String operateAdd;

	    private String operateEdit;

	    private String operateDelete;

	    private String operatePrint;

	    private String operateSearch;
	    
	    private String operateAudit;
	    
	    private String operateUndoAudit;
	    
	    private Long menuId;

		public String getOperateAdd() {
			return operateAdd;
		}

		public void setOperateAdd(String operateAdd) {
			this.operateAdd = operateAdd;
		}

		public String getOperateEdit() {
			return operateEdit;
		}

		public void setOperateEdit(String operateEdit) {
			this.operateEdit = operateEdit;
		}

		public String getOperateDelete() {
			return operateDelete;
		}

		public void setOperateDelete(String operateDelete) {
			this.operateDelete = operateDelete;
		}

		public String getOperatePrint() {
			return operatePrint;
		}

		public void setOperatePrint(String operatePrint) {
			this.operatePrint = operatePrint;
		}

		public String getOperateSearch() {
			return operateSearch;
		}

		public void setOperateSearch(String operateSearch) {
			this.operateSearch = operateSearch;
		}

		public String getOperateAudit() {
			return operateAudit;
		}

		public void setOperateAudit(String operateAudit) {
			this.operateAudit = operateAudit;
		}

		public String getOperateUndoAudit() {
			return operateUndoAudit;
		}

		public void setOperateUndoAudit(String operateUndoAudit) {
			this.operateUndoAudit = operateUndoAudit;
		}

		public Long getMenuId() {
			return menuId;
		}

		public void setMenuId(Long menuId) {
			this.menuId = menuId;
		}
		
}
