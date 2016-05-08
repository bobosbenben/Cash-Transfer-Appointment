Ext.define('app.main.view.Main', {
			extend : 'Ext.container.Viewport',
			xtype : 'app-main',
			requires : ['app.main.controller.MainController','app.main.model.MainModel'],
			uses : ['app.main.view.region.Top','app.main.view.region.Center','app.main.view.region.Left',
			       'app.main.view.win.SysInfoWin'
				   ],

			controller : 'maincontroller',
			viewModel : {
				type : 'main'
			},

			initComponent : function() {
				this.callParent();
			},

			layout : {
				type : 'border'
			},
			items : [{
						xtype : 'maintop',
						region : 'north',
						split:true
					}, {
						xtype : 'mainmenu',
						region : 'west', 
						title : '导航菜单',
						width : 220,
						collapsible : true,
						split : true
						
					}, {
						region : 'center', 
						xtype : 'maincenter'
					}]
		});
