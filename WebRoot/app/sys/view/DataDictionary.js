Ext.define('app.sys.view.DataDictionary', {
			extend : 'app.module.view.GridBillModule',
			xtype : 'sys-dd',
			requires : ['app.sys.controller.DDController','app.sys.model.DDModel'],
			uses:['app.ux.ButtonTransparent'],
			controller : 'ddcontroller',
			viewModel : {
				type : 'ddmodel'
			}
		});

