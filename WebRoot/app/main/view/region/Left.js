/**
 * 左边的菜单区域，可以放树形菜单或折叠菜单
 */
Ext.define('app.main.view.region.Left', {
			extend : 'Ext.panel.Panel',
			alias : 'widget.mainmenu',
			uses : ['app.main.view.menu.MainMenuTree',
					'app.main.view.menu.AccordionMainMenu'],

			layout : {
				type : 'accordion',
				animate : true
			},
			glyph : 0xf0c9,
			tools : [{
						type : 'pin',
						tooltip : '层叠方式显示菜单',
						listeners : {
							click : function(tool) {
								var panel = tool.up('mainmenu');
								panel.insert(0, {
											xtype : 'mainmenuaccordion'
										});
								//panel.items.items[0].expand();
								panel.remove(panel.down('mainmenutree'), true);
								tool.hide();
								tool.nextSibling().show();
							}
						}
					}, {
						type : 'unpin',
						tooltip : '树状方式显示菜单',
						hidden : true,
						listeners : {
							click : function(tool) {
								var panel = tool.up('mainmenu');
								panel.removeAll();
								panel.insert(0, {
											xtype : 'mainmenutree'
										});
								tool.hide();
								tool.previousSibling().show();
							}
						}
					}],

			initComponent : function() {
				this.items = [{
							xtype : 'mainmenutree'
						}];
				this.callParent();
			}

		})