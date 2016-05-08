Ext.define('app.sys.controller.MenuController', {
	extend : 'app.module.controller.BaseControllerModule',
	alias : 'controller.menucontroller',
	initComponent : function() {
		this.callParent();
	},
	btnAdd : function(btn, e) {
		var tree = btn.up('treepanel'),
		   view = this.getView();
		var data = tree.getSelectionModel().getSelection();
		if (data.length <= 0) {
			Ext.Msg.alert("小提示", "此操作前请选择要增加节点的父节点");
			return;
		};
		var id = data[0].get('id'), name = data[0].get('text'), deepth = 0;
		if (id != "root") {
			var depth = data[0].raw.deepth;
			deepth = Ext.Number.from(depth, 0) + 1;
		};
		Ext.getBody().mask();
		Ext.create('app.sys.view.region.MenuAddWinView', {
					viewModel : view.getViewModel(),
					controller : view.getController(),
					selectedId : id,
					parentName : name,
					deepth : deepth
				}).show();
	},
	btnDel : function(btn, e) {
		var tree = btn.up('treepanel');
		var data = tree.getSelectionModel().getSelection();
		if (data.length <= 0) {
			Ext.Msg.alert("小提示", "此操作前请选择要删除的节点");
			return;
		}
		var id = data[0].get('id');
		var leaf = data[0].get('leaf');
		if (id == "roots") {
			Ext.Msg.alert("小提示", "不能删除最上层节点");
			return;
		} else {
			var selectedId = "";
			if (leaf == false) {
				var model = tree.getSelectionModel();
				var store = tree.getStore();
				var selectedNode = store.getNodeById(id);
				selectedNode.cascadeBy(function(child) {
							selectedId = selectedId + "," + child.data.id
						})
				selectedId = selectedId.substring(1);
			} else {
				selectedId = id;
			}
			Ext.Msg.confirm("删除提示", "如果此节点下面有子节点也将被删除,是否确认删除?",
					function(btn, e) {
						if (btn == "yes") {
							showLoading();
							Ext.Ajax.request({
										url : actionUrl + '/delmenu',
										method : 'POST',
										timeout : 6000,
										params : {
											selectedIds : selectedId
										},
										success : function(response) {
											Ext.MessageBox.hide();
											var ret = response.responseText;
											if (processError(ret))
												return;
											if (ret == "OK") {
												Ext.Msg.alert("小提示", "更新成功！");
												tree.getStore().reload();
											}
										}
									})
						}

					})
		}
	},
	menuSubmit : function(btn, e) {
		var form = btn.up('form'), tree = Ext.getCmp('menutreepanel'), win = this
				.getView();
		showLoading();
		form.getForm().submit({
					url : actionUrl + '/saveaddmenu',
					success : function(form, action) {
						Ext.Msg.alert("Success", action.result.Msg,
								function(e) {
									if (e == "ok" || e == "确定") {
										Ext.MessageBox.hide();
										win.close();
										tree.getStore().reload();
										Ext.getBody().unmask();
									}
								});
					},
					failure : function(form, action) {
						Ext.MessageBox.hide();
						Ext.Msg.alert("Failed", action.result.Msg);
					}

				});
	},
	onNavigationTreeClick:function(record, view, e){
		e.preventDefault();
		var data = record.data,
		    selectId = data.id,
		    gridpanel = view.query("gridpanel[name='modulegridcenter']")[0];
		if (selectId == "root") {
			gridpanel.getStore().removeAll();
			view.getViewModel().set('tc_selectedNames', '');
		} else {
			var baseParams = {};
			baseParams['selectedId'] = selectId;
			gridpanel.getStore().proxy.extraParams = baseParams;
			gridpanel.getStore().reload();
			view.getViewModel().set('tc_selectedNames',
					'『<em>' + data.text + '</em>』');
		}
	},
	onBtnUpdate : function(btn, e) {

		var grid = btn.up("gridpanel[name='modulegridcenter']"), modified = grid
				.getStore().getModifiedRecords(), data = [{
					test : 'name',
					name : '名称'
				}];

		if (modified.length <= 0) {
			Ext.Msg.alert("小提示", "<center>没有修改数据,无需保存</center>");
			return;
		};
		var id = modified[0].get('id'), baseParams = {};
		baseParams['selectedId'] = id;

		if (checkUpdateBefore(modified, data)) {
			showLoading();
			Ext.Ajax.request({
				url : actionUrl + '/updatemenu',
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
						Ext.Msg.alert("Success", "更新成功", function(btn, e) {
									if (btn == "ok" || btn == "确定") {
										grid.getStore().proxy.extraParams = baseParams;
										grid.getStore().reload();
									}
								})
					}
				}
			})
		}
	}
});
