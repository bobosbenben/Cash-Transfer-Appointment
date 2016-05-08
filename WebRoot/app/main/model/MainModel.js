/**
 * This class is the view model for the Main view of the application.
 */
Ext.define('app.main.model.MainModel', {
			extend : 'Ext.app.ViewModel',
			alias : 'viewmodel.main',
			constructor : function() {  
			    var me = this;  
			    this.callParent(arguments);  
			    Ext.Ajax.request({  
			                url : actionUrl+'/getappinfo', 
			                async : false, // 同步,必须获取完信息后才能将执行下面的动作,
			                success : function(response) {
			                    var ret = response.responseText; 
			                    if (processError(ret))
		                          return;
			                    var applicationInfo = Ext.decode(ret, true);
			                    Ext.apply(me.data, applicationInfo);  
			                }  
			            });  
			}, 
			data : {
				name : 'app',
				/**********系统相关 win************/
				sysInfoWin:{
					win_title:'关于系统',
					win_glyph:0xf1eb,
					win_width : 600,
					win_Height : -1
				},
				changepwdwin:{win_title:'密码更改',win_width:400,win_Height:200,win_glyph:0xf084}
			}
});