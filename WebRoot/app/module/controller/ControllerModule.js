Ext.define('app.module.controller.ControllerModule', {
	extend : 'app.module.controller.BaseControllerModule',
	onBtnAdd : function(btn, e) {
		var view = this.getView();
		var record = this.getDefaultRecord(view);
		if (record != null) {
			//var gridpanel = view.query("gridpanel[name='modulegridcenter']")[0];
			var  gridpanel =  btn.up('modulecontainer').query("gridpanel[name='modulegridcenter']")[0] ;
			gridpanel.getStore().add(record);
			// 获取当前store数据量
			var rowdos = gridpanel.getStore().getCount();
			// 将插入的当前行设置为编辑状态
			gridpanel.getPlugin().startEditByPosition({
						row : rowdos - 1,
						column : 1
					})
		}
	},
	onBtnSave : function(btn, e) {
		//Ext.Msg.alert("tishi","进入save了");
		//alert("jinrul")
		var view = this.getView(),
		    gridpanel =  btn.up('modulecontainer').query("gridpanel[name='modulegridcenter']")[0] ;
		var modified = gridpanel.getStore().getModifiedRecords();
		if (modified.length <= 0) {
			Ext.Msg.alert("提示", "没有修改数据,无需保存");
			return;
		}
		var data = view.getViewModel().get('tc_checkFields');
		if (checkUpdateBefore(modified, data)) {
			showLoading();
			Ext.Ajax.request({
						url : actionUrl + '/update',
						method : 'POST',
						timeout : 6000,
						params : {
							jsonData : getUpdateJson(modified)
						},
						success : function(response) {
							Ext.MessageBox.hide();
							var ret = response.responseText;
							if (processError(ret))
								return;
							if (ret == "OK") {
								Ext.Msg.alert("Success", "更新成功", function(btn,
												e) {
											if (btn == "ok" || btn == "确定")
												gridpanel.getStore().reload();

										})
							}
						}
					})

		}
	},
	onBtnDel : function(btn, e) {
		var view = this.getView(), gridpanel = view
				.query("gridpanel[name='modulegridcenter']")[0], data = gridpanel
				.getSelectionModel().getSelection();
		if (data.length <= 0) {
			Ext.Msg.alert("提示", "此操作前请选择一条记录");
			return;
		}
		var selectId = data[0].get('id');
		if (selectId > 0) {
			if (view.getViewModel().get('tc_isDeleteData')) {
				//Ext.Msg.confirm("删除提示", "注意：数据删除后不能还原，确定要删除:"
				//				+ data[0].get('name') + " ？", function(btn, e) {
				Ext.Msg.confirm("删除提示", "注意：数据删除后不能还原，确定要删除该条记录"
					+ " ？", function(btn, e) {
							if (btn == "yes") {
								showLoading();
								Ext.Ajax.request({
											url : actionUrl + '/remove',
											method : 'POST',
											timeout : 6000,
											params : {
												selectedId : selectId
											},
											success : function(response) {
												Ext.MessageBox.hide();
												var ret = response.responseText;
												if (processError(ret))
													return;
												if (ret == "OK") {
													Ext.Msg
															.alert("提示",
																	"删除成功！");
													gridpanel.getStore()
															.reload();
												}

											}
										})
							}
						})
			} else {
				Ext.Msg.alert("操作提示:", "不能删除,如不需要请将是否启用改为不启用");
				return;
			}
		} else {
			var st = gridpanel.getStore();
			Ext.Array.each(data, function(record) {
						st.remove(record);
					})
		}
	},
	onBtnSearch:function(btn,e){
		var view = this.getView(), 
		    gridpanel = view.query("gridpanel[name='modulegridcenter']")[0],
		    formpanel = view.query("fieldset[name='northsearchformmodule']")[0];
					var baseParams = getParamsByForm(formpanel);

		//这一部分是针对AppointmentSummary中的总金额做的
		var store = gridpanel.getStore();
		store.addListener('load', function(store, records, options) {

			var tnumber=parseFloat(0);
			Ext.each(records, function(rec) {
				if (rec.get('number')) {
					tnumber = tnumber + parseFloat(rec.get('number'));
				}
			});

			var textField = Ext.getCmp('totalNumber');
			if(textField) textField.setValue(tnumber);
		});//到这里结束，其实应该是定制一个ControllerModule让AppointmentSummary来继承

		gridpanel.getStore().proxy.extraParams = baseParams;
		gridpanel.getStore().reload();


	},
	getDefaultRecord : function(view) {
		var add = {
			name : ''
		};
		return add;
	}
});
