Ext.define('app.basis.view.HumanResource', {
			extend : 'app.module.view.GridBillModule',
			xtype : 'basis-hr',
			id:'hrmodule',
			requires : ['app.basis.controller.HRController','app.basis.model.HRModel'],
			uses:['app.ux.ButtonTransparent'],

			controller : 'hrcontroller',
			viewModel : {
				type : 'hrmodel'
			},
			layout:'border',
			initwest:function(vm){
				
				this.westModule = Ext.create('app.module.view.TreePanelModule',{
					region : 'west',
					viewModel:vm,
					loadtreeurl:'basis/company'
					
				})
			}
		});

