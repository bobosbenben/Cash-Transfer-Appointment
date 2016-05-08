Ext.define('app.sys.controller.RoleController', {
	extend : 'app.module.controller.ControllerModule',
	alias : 'controller.rolecontroller',
	setRolePermission : function(btn, e) {
		var view = this.getView(), 
		    gridpanel = btn.up('sys-role').query("gridpanel[name='modulegridcenter']")[0], 
		    data = gridpanel.getSelectionModel().getSelection();

		if (data.length <= 0) {
			Ext.Msg.alert("提示", "此操作前请选择一条记录");
			return;
		};
		var selectId = data[0].get('id');

		if (selectId > 0) {
			//遮照本层
			Ext.getBody().mask();
			Ext.create('app.sys.view.region.RoleEditWinView', {
					viewModel : view.getViewModel(),
					controller : view.getController(),
					title:"『<em>"+data[0].data['name']+"</em>』角色权限设置",
					selectedId : selectId
				}).show();
		} else {
			Ext.Msg.alert("提示", "请先保存新增角色信息再设置角色权限");
			return;
		}
	},
	onTreeExpand:function(btn,e){
		var tree = btn.up("treepanel");
		tree.expandAll();
	},
	onTreeCollapse:function(btn,e){
		var tree = btn.up("treepanel");
		tree.collapseAll();
	},
	onRoleEditSave:function(btn,e){
		var win = this.getView(),
		    selectedId = win.selectedId,
		     tree = win.query('treepanel[name=roleedittreepanel]')[0],
		     node = tree.getChecked();
		 if(selectedId == null || selectedId <=0){
			Ext.Msg.alert("异常操作");
		  return ;
		};
		showLoading();
		 Ext.Ajax.request({
		              	url : actionUrl + '/updateroleforwin',
		              	params : {
									jsonData : getjsonForTree(node),
									selectedId:selectedId
								},
		            	success : function(response) {
										var ret = response.responseText;
										 Ext.MessageBox.hide();
										if (processError(ret))
		                                 return;
										Ext.MessageBox.confirm('确认', '保存成功，返回主界面吗?', function(btn){
										if (btn == 'yes') {
											win.close();
					                        Ext.getBody().unmask();
										}
										})
									},
			            scope : this
	              });
		
	},
	onRoleEditCancle:function(btn, e){
		var win = btn.ownerCt.ownerCt;
		win.close();
		Ext.getBody().unmask();
	},
	onTreeCheckChange:function(node, checked, eOpts){
		//判断子节点
					if (node.data.leaf == false) {
						//是否为选中节点
						if (checked) {
							//级联子节点,将其状态改为选中状态
							node.cascadeBy(function(child) {
										child.set("checked", checked);
									})
							//级联父节点,将其状态改为选中状态		
							node.bubble(function(pre) {
										pre.set("checked", checked);
									})
						} else {
							//级联子节点,将其状态改为未选中状态
							node.cascadeBy(function(child) {
										child.set("checked", checked);
									})
							//级联父节点,将其状态改为未选中状态
							node.bubble(function(pre) {
										pre.set("checked", checked);
										//遍历子节点,如果有子节点被选中则将当前父节点置为选中状态
										pre.eachChild(function(child) {
													if (child.data.checked == true) {
														pre
																.set("checked",
																		true);
													}

												})
									})		
						}
					} else {
						if (checked) {
							//级联父节点,将其状态改为选中状态
							node.bubble(function(pre) {
										pre.set("checked", checked);
									})
						} else {
							//级联父节点,将其状态改为未选中状态
							node.bubble(function(pre) {
										pre.set("checked", checked);
										//遍历子节点,如果有子节点被选中则将当前父节点置为选中状态
										pre.eachChild(function(child) {
													if (child.data.checked == true) {
														pre
																.set("checked",
																		true);
													}

												})
									})
						}
					}
	},
	getDefaultRecord : function(view) {
		var add = {
			name : ''
		};
		return add;
	}
});
