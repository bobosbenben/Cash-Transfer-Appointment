   /**
	 * 启用提示
	 * 
	 */					
	Ext.QuickTips.init();
	/**
	 * 设置字体
	 * 
	 */			
	Ext.setGlyphFontFamily('FontAwesome'); 
	/**
	 * 设置动态加载
	 * 
	 */			
	Ext.Loader.setConfig({
		enabled : true,
		paths : {// 设置路径
		'Ext.ux' : 'app/ux'
		}
	}); 
Ext.application({  
    name: 'app',
    extend: 'app.Application',
    autoCreateViewport: 'app.basis.view.LoginUser'
});