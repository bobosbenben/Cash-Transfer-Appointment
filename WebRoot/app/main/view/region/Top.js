/**
 * 系统主页的顶部区域，主要放置系统名称，菜单，和一些快捷按钮
 */
Ext.define('app.main.view.region.Top', {

			extend : 'Ext.toolbar.Toolbar',
			alias : 'widget.maintop',
			enableOverflow:true,
			overflowHandler :'menu',
			uses : ['app.ux.ButtonTransparent','app.main.view.menu.MainMenuSearchField'],

			defaults : {
				xtype : 'buttontransparent'
			},

			height : 40,

			items : [{
						bind : {
							text : '用户 : {user.name}'
						},
						glyph : 0xf007
					},{
						bind : {
							text : '公司 : {user.company} {user.department}'
						},
						glyph : 0xf0f8
					} ,'->',{
						text : '首页',
						glyph : 0xf015,
						handler : 'showHomePage'
					},{
						text : '搜索',
						glyph : 0xf002,
						menu:[{
						  text:'菜单搜索',
						   glyph:0xf0ec,
						   menu:[{xtype:'mainmenusearchfield'}]
						}]
						
					},{
						text : '高级',
						glyph : 0xf013,
						menu: [{
					        text: '修改密码',
					        glyph:0xf0ec,
					        handler : 'changePwd'
					    }]
					}, {
						text : '帮助',
						glyph : 0xf059
					}, {
						text : '关于',
						glyph : 0xf06a,
						handler : 'showSysInfo'
					}, {
						text : '注销',
						glyph : 0xf011,
						handler : 'logoff'
					}]

		});