Ext.define('app.basis.view.Company', {
			extend : 'app.module.view.GridBillModule',
			xtype : 'basis-company',
			requires : ['app.basis.controller.CompanyController','app.basis.model.CompanyModel'],
			uses:['app.ux.ButtonTransparent'],

			controller : 'companycontroller',
			viewModel : {
				type : 'companymodel'
			},
			layout:'border',
			initwest:function(vm){
				
				this.westModule = Ext.create('app.module.view.TreePanelModule',{
					region : 'west',
					viewModel:vm
					
				})
			}
		});

