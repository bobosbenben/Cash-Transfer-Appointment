package en.common.copybean.impl;

import en.common.dao.IGridDao;

public class GridCopyBeanImpl extends BaseCopyBeanImpl{
	
	protected IGridDao gridDao;

	public void setGridDao(IGridDao gridDao) {
		this.gridDao = gridDao;
	}
}
