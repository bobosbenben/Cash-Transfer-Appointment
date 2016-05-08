Ext.define('app.sys.view.Role', {
			extend : 'app.module.view.GridBillModule',
			xtype : 'sys-role',
			requires : ['app.sys.controller.RoleController','app.sys.model.RoleModel'],
			uses:['app.ux.ButtonTransparent'],
			controller : 'rolecontroller',
			viewModel : {
				type : 'rolemodel'
			}
		});

