/**
 * This class is the main view for the application. It is specified in app.js as
 * the "autoCreateViewport" property. That setting automatically applies the
 * "viewport" plugin to promote that instance of this class to the body element.
 * 
 * TODO - Replace this content of this view to suite the needs of your
 * application.
 */
Ext.define('app.main.controller.MainController', {
			extend : 'app.module.controller.BaseControllerModule',
			alias : 'controller.maincontroller',
			showSysInfo:function(btn){
				 var win = Ext.widget('sysinfowin', {  
		                 viewModel : this.getView().getViewModel()
		            });  
		         win.show();  
			},
			showHomePage: function(menuitem) {
				this.getView().down('maincenter').setActiveTab(0);
			},
			logoff:function(btn){
				 self.location.href = contextpath+"/logout";
			},
			onMainMenuClick : function(record,e) {
				 var ev = e || window.event;
				 ev.preventDefault();
				 var maincenter = this.getView().down('maincenter');
				 var id = record.id;
				 var url = record.action;
				 //console.log('action是：' + record.action +"   id is :"+id);
				 if(!isEmpty(url)){
					var panel = maincenter.getComponent('panel'+id);  
					if(panel){
						maincenter.setActiveTab(panel); //如果已经存在就激活Tab
					}else{
						Ext.suspendLayouts(); //暂停布局
						panel = new Ext.Panel({
												title : record.text,
												itemId : 'panel'+id,
												glyph : eval(record.glyph),//如果不在数据库里为菜单添加glyph，就会造成前端Syntax错误
												closable : true,
												html:'<iframe width="100%" height="100%" scrolling="yes" frameborder="0" src="' + url + '" name="workAreaFame"></iframe>',
												//layoutConfig: {
												//	animate: true //动画效果启用
												//},
												//loadMask:true
												//autoLoad: {
												//	//url: url,
												//	url: '/pro/jsp/app/test.html',
												//	//params: {
												//	//	InProId: InProId
												//	//},
												//	scripts: true,
												//	//text:Moudle_Loading_Text,
												//	//callback: function(e,success,response,o) {
												//	//	if (success)
												//	//	{
												//	//		Print_NgReport_Button.setDisabled(false);
												//	//	}
												//	//	else{
												//	//		Ext.Msg.alert(S_Action,Server_Error_Text);
												//	//	}
												//	//}
												//}
									});
						maincenter.add(panel);
						maincenter.setActiveTab(panel);
						Ext.resumeLayouts(true); //恢复布局
					}
				 }
			},

			onMainResize : function() {
				if (this.showButton && !this.showButton.hidden) {
					this.showButton.setX(document.body.clientWidth - 32);
				}
			},
			changePwd : function(btn,e){
				var view = this.getView();
				Ext.create('app.main.view.region.ChangePwdWinView', {
						viewModel : view.getViewModel()
					}).show();
			},
			onChangePwdwinSave:function(btn,e){
				 var win = btn.ownerCt.ownerCt.ownerCt,
				     form = btn.ownerCt.ownerCt.getForm(),
				     oldPwd = form.findField('oldPwd'),
					 newPwd1 = form.findField('newPwd1'),
					  newPwd2 = form.findField('newPwd2');
				       if (oldPwd.getValue() == newPwd1.getValue()) {
					         	Ext.Msg.alert("小提示","新密码不能与旧密码一致！");
					         	newPwd1.reset();
					         	newPwd2.reset();
					         	return;
					  };
					   if (newPwd2.getValue() != newPwd1.getValue()) {
					         	Ext.Msg.alert("小提示","新密码两次输入的不一致！");
					         	newPwd1.reset();
					         	newPwd2.reset();
					         	return;
					  };
					  showLoading();
					  Ext.Ajax.request({
								url : actionUrl	+ '/changepwd',
								method : 'POST',
								timeout : 6000,
								params : {
												oldPwd : encode64(oldPwd.getValue()),
												newPwd: encode64(newPwd1.getValue()),
												random:genRandom()
									},
								success : function(response) {
									Ext.MessageBox.hide();
									var ret = response.responseText;
									if (processError(ret))
		                               return;
									if(ret=="OK"){
									  Ext.Msg.alert("小提示","密码修改成功！");
									  form.reset();
									  win.close();
									}
								}
							})
			}

});


