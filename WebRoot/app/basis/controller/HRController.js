Ext.define('app.basis.controller.HRController', {
	extend : 'app.module.controller.ControllerModule',
	alias : 'controller.hrcontroller',
	initComponent : function() {
		this.callParent();
	},
	onBtnAdd : function(btn, e) {
		var view =  this.getView();
		Ext.create('app.basis.view.region.HRAddWinView',{
		     viewModel : view.getViewModel(),
		     controller : view.getController()
		}).show()
		Ext.getBody().mask();
	},
	onHRAddViewCancle:function(btn ,e){
		var win = btn.ownerCt.ownerCt.ownerCt;
		win.close();
		Ext.getBody().unmask();
	},
	onHRAddViewSave:function(btn,e){
		var formpanel =  btn.ownerCt.ownerCt,
		    win = formpanel.ownerCt,
		    form = formpanel.getForm(),
		    relationlogin = form.findField('relationLogin'),
		    password = form.findField('loginpwd');
			if(relationlogin.getValue()){
				if(isEmpty(password.getValue())){
						Ext.Msg.alert('小提示','关联登陆时登陆密码为必填项');
						return;
				}
			}
					if (form.isValid()) {
						form.submit({
							url : actionUrl+"/updatewin",
							waitMsg : '信息保存中....',
							success : function(form1, action) {
								Ext.Msg.confirm(action.result.Msg,
										'是否继续添加资料', function(btn) {
											if (btn == 'no') {
												win.close();
												Ext.getBody().unmask();
											}else
											  form.reset();
										}, this)

							},
							failure : function(form1, action) {
								Ext.Msg.alert('保存失败', '异常原因:'+action.result.Msg);
							}

						});
					}else{
						Ext.Msg.alert('小提示','有必填项目没有填写哦');
				}
	},
	onNavigationTreeClick:function(record,view,e){
		e.preventDefault();
		var data = record.data,
		    gridpanel = view.query("gridpanel[name='modulegridcenter']")[0];
		    formpanel = view.query("form[name='northformpanel']")[0];
		   
		    //设置公司
			 formpanel.getForm().findField('dept').setValue(data.text);
			 formpanel.getForm().findField('status').setValue(true);
			var baseParams = {};
			baseParams['dept'] = data.text;
			gridpanel.getStore().proxy.extraParams = baseParams;
			gridpanel.getStore().reload();
			view.getViewModel().set('tc_selectedNames',
					'『<em>' + data.text + '</em>』所属人员');
	},
	onUpdateMore:function(btn,e){
		var view = this.getView(),
	        gridpanel =  btn.up('modulecontainer').query("gridpanel[name='modulegridcenter']")[0] ,
			record= gridpanel.getSelectionModel().getSelection();
				 if (record.length <= 0) {
						Ext.Msg.alert("小提示","此操作前请选择要修改的信息");
						return;
					}
					var selectId = record[0].get('id');
					if (selectId <= 0) {
						Ext.Msg.alert("小提示","请先保存新增人事信息再设置进行修改");
						return;
					}
					//遮照本层
					Ext.getBody().mask();
					Ext.Ajax.request({
						url : actionUrl + '/getHRInfo',
						method : 'POST',
						timeout : 6000,
						params : {
							selectedId : selectId,
							romdam:Random()
						},
						success : function(response) {
							Ext.MessageBox.hide();
							var ret = response.responseText;
							if (processError(ret))
								return;
							var win =  Ext.create('app.basis.view.region.HREditWinView',{
								 viewModel : view.getViewModel(),
								 controller : view.getController()
								});
								win.show();
							 var form = win.down("form");
							// console.log(ret)
							 var records = {"zjm":"001"};
							 var temp = Ext.JSON.decode(ret)
			                     form.getForm().setValues(Ext.JSON.decode(ret));
						}
					})
	},
	onHREditViewSave:function(btn,e){
		var formpanel =  btn.ownerCt.ownerCt,
		    win = formpanel.ownerCt,
		    form = formpanel.getForm(),
		    gridpanel = Ext.getCmp("hrmodule").query("gridpanel[name='modulegridcenter']")[0];
					if (form.isValid()) {
						form.submit({
							url : actionUrl+"/updatewin",
							waitMsg : '信息保存中....',
							success : function(form1, action) {
								Ext.Msg.alert("提示", "保存成功!", function(btn,
												e) {
											if (btn == "ok" || btn == "确定"){
												win.close();
							                    Ext.getBody().unmask();
							                    gridpanel.getStore().reload();
							                    gridpanel.getSelectionModel().deselectAll();
											}
										})
							},
							failure : function(form1, action) {
								Ext.Msg.alert('保存失败', '异常原因:'+action.result.Msg);
							}

						});
					}else{
						Ext.Msg.alert('小提示','有必填项目没有填写哦');
				}
	},
	getDefaultRecord : function(view) {
		 var tree = view.query("treepanel")[0],
		     data = tree.getSelectionModel().getSelection();
		 
		 	var gsMc = data[0].get('text'),
		 	    gsdm =  data[0].raw.gsDm,
			    add = {
				name : '',
				sjgsDm:sjgsdm,
				sjgsMc:sjgsMc,
				status:true,
				sex:'F'
			};
			return add;
		 }
});
