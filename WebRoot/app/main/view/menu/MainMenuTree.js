/**
 * 树状菜单，显示在主界面的左边
 */
Ext.define('app.main.view.menu.MainMenuTree', {
			extend : 'Ext.tree.Panel',
			alias : 'widget.mainmenutree',
			title : '系统菜单',
			rootVisible : false,
			lines : true,
			mixins:['app.ux.TreeFilter'],
			initComponent : function() {
				this.store = Ext.create('Ext.data.TreeStore', {
							root : {
								text : '系统菜单',
								leaf : false//,
								//expanded : true
							}
						});
				var vm = this.up('app-main').getViewModel();
				var menus = vm.get('systemMenu');
				var root = this.store.getRootNode();
				for (var i in menus) {
					var menugroup = menus[i];
					var menuitem = root.appendChild({
								text : menugroup.text,
								expanded : menugroup.expanded,
								icon : menugroup.icon,
								id:menugroup.id,
								action:menugroup.url,
								glyph : 0+menugroup.glyph
							});
					for (var j in menugroup.items) {
						var menumodule = menugroup.items[j];
						var childnode = {
							moduleId : menumodule.text,
							moduleName : menumodule.module,
							id:menumodule.id,
							action:menumodule.url,
							text : menumodule.text,
							glyph : 0+menumodule.glyph,
							leaf : true
						};
						menuitem.appendChild(childnode);
					}
				}
				this.callParent(arguments);
			},
			listeners: {  
		        itemclick: function(obj, record, item, index, e, eOpts){  
		            this.up('app-main').getController().onMainMenuClick(record.data,e);  
		        }  
		    } 
		})