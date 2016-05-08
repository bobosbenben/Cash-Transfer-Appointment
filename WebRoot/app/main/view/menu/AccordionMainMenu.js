/**
 * 折叠式(accordion)菜单，样式可以自己用css进行美化
 */

Ext.define('app.main.view.menu.AccordionMainMenu', {
			extend : 'Ext.panel.Panel',
			alias : 'widget.mainmenuaccordion',
			title : '系统菜单',
			mixins:['app.ux.TreeFilter'],
			layout : {
				type : 'accordion',
				activeOnTop:true,
				titleCollapse: true,
				animate : true
			},
			 defaults: {
			        bodyStyle: 'padding:10px'
			    },
			initComponent : function() {
				this.items=[];
				var vm = this.up('app-main').getViewModel();
				var menus = vm.get('systemMenu');
				for(var i in menus){
					var menugroup = menus[i];
					var accpanel = {
						menuAccordion : true,
						title : menugroup.text,
						layout : 'fit',
						dockedItems : [{
									dock : 'left',
									xtype : 'toolbar',
									items : []
								}]
					};
					for (var j in menugroup.items) {
						var menumodule = menugroup.items[j];
						accpanel.dockedItems[0].items.push({
									xtype : 'buttontransparent',
									text : this.addSpace(menumodule.text, 12),
									itemId:menumodule.text,
									glyph : eval(0+menumodule.glyph),
									//glyph : 0xf039,
									action: menumodule.url,
									id:menumodule.id,
									handler : 'onMainMenuClick'
								});
					}
					this.items.push(accpanel);
				};
				this.callParent(arguments);
			},

			addSpace : function(text, len) {
				var result = text;
				for (var i = text.length; i < len; i++) {
					result += '　';
				}
				return result;
			}
		})