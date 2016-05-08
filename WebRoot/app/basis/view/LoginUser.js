Ext.define('app.basis.view.LoginUser', {
			extend : 'app.module.view.GridBillModule',
			xtype : 'basis-luser',
			id:'lusermodule',
			requires : ['app.basis.controller.LUserController','app.basis.model.LUserModel'],
			uses:['app.ux.ButtonTransparent'],

			controller : 'lusercontroller',
			viewModel : {
				type : 'lusermodel'
			},
			layout:'border',
			initwest:function(vm){
				
				this.westModule = Ext.create('app.module.view.TreePanelModule',{
					region : 'west',
					viewModel:vm,
					loadtreeurl:'basis/company'
				})
			},
			initComponent : function() {
				var me = this,
				    con = this.getController();
				this.callParent();
				var  gridpanel = this.query("gridpanel[name='modulegridcenter']")[0];
				     gridpanel.on("edit",function(editor,e,  eOpts){
				     con.OnGridCellEditor(editor,e,  eOpts);
				})
			   
			}
		});

