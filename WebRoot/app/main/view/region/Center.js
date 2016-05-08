/**
 * 系统界面的主区域,是一个tabpanel,可以有多个tab页面，用来放置各个模块。
 */
Ext.define('app.main.view.region.Center', {
	extend : 'Ext.tab.Panel',
	alias : 'widget.maincenter',

	uses : ['Ext.ux.TabCloseMenu','app.main.view.region.HomePage'],
	closeAction : 'hide',
	autoDestroy : false,
	tabPosition : 'top',
	plugins : [{

		ptype : 'tabclosemenu',
		closeAllTabsText : '关闭所有',
		closeOthersTabsText : '关闭其他',
		closeTabText : '关闭',

		extraItemsTail : ['-', {
					text : '可关闭',
					itemId : 'canclose',
					checked : true,
					hideOnClick : false,
					handler : function(item) {
						item.ownerCt.tabPanel.tab.setClosable(item.checked);
					}
				}],
		listeners : {
			beforemenu : function(menu, tabPanel) {
				// 此插件有bug,需要加入这个参数
				menu.tabPanel = tabPanel;
				if (tabPanel.reorderable==false) {
					menu.down('#canclose').setChecked(false);
					menu.down('#canclose').disable();
				} else {
					menu.down('#canclose').setChecked(tabPanel.tab.closable);
					menu.down('#canclose').enable();
					
				}
			}
		}
	}],

	initComponent : function() {

		this.items = [{
					glyph : 0xf015,
				    xtype : 'homepage',
					border : true,
					frame : false,
					reorderable : false
				}]
		this.callParent();
	}

})