Ext.define('app.sys.view.Menu', {
			extend : 'app.module.view.GridBillModule',
			xtype : 'sys-menu',
			requires : ['app.sys.controller.MenuController','app.sys.model.MenuModel'],
			uses:['app.ux.ButtonTransparent'],

			controller : 'menucontroller',
			viewModel : {
				type : 'menumodel'
			},
			layout:'border',
			initwest:function(vm){
				this.westModule = Ext.create('app.module.view.TreePanelModule',{
					region : 'west',
					id:'menutreepanel',
					viewModel:vm,
					dockedItems: [{
				        xtype: 'toolbar',
				        dock: 'top',
				        defaults : {
							xtype : 'buttontransparent'
						},
				        items: [{
				            text: '新增',
				            glyph : 0xf055,
					        bind : {
								hidden : '{!tn_isToolbarAdd}'
							},
				            handler:'btnAdd'
				        },'->',{
				            text: '删除',
				            glyph : 0xf014,
				             bind : {
								hidden : '{!tn_isToolbarDel}'
							},
				            handler:'btnDel'
				        }]
				    }]
					
				})
			}
		});

