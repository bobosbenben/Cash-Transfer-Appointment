Ext.define('app.basis.controller.CompanyController', {
	extend : 'app.module.controller.ControllerModule',
	alias : 'controller.companycontroller',
	initComponent : function() {
		this.callParent();
	},
	onNavigationTreeClick:function(record,view,e){
		e.preventDefault();
		var data = record.data,
		    gsDm = data.gsDm,
		    gridpanel = view.query("gridpanel[name='modulegridcenter']")[0];
		    formpanel = view.query("form[name='northformpanel']")[0];
		    //设置公司
			 formpanel.getForm().findField('gsdm').setValue(gsDm);
			 formpanel.getForm().findField('status').setValue(true);
			var baseParams = {};
			baseParams['gsdm'] = gsDm;
			gridpanel.getStore().proxy.extraParams = baseParams;
			gridpanel.getStore().reload();
			view.getViewModel().set('tc_selectedNames',
					'『<em>' + data.text + '</em>』所属公司');
	},
	getDefaultRecord : function(view) {
		 var tree = view.query("treepanel")[0],
		     data = tree.getSelectionModel().getSelection();
		 if (data.length <= 0) {
			  Ext.Msg.alert("提示","请先在右侧公司信息里选择新建公司的上级公司!");
			  return null;
		 }else{
		 	var sjgsMc = data[0].get('text'),
		 	    sjgsdm =  data[0].raw.gsDm,
			    add = {
				name : '',
				sjgsDm:sjgsdm,
				sjgsMc:sjgsMc, //上级公司名称
				status:true
			};
			return add;
		 }
		
	}
});
