Ext.define('app.basis.controller.LUserController', {
	extend : 'app.module.controller.ControllerModule',
	alias : 'controller.lusercontroller',
	OnGridCellEditor:function(editor,e,  eOpts){
		if(e.column.dataIndex == 'loginAccount' ){
					 var record = e.record;
				     var fvalue = record.get('loginAccount');
				     if(isEmpty(fvalue))
				        return;
					 showLoading();
						Ext.Ajax.request({
								url : actionUrl + "/getuser?userId=" + fvalue+ "&"+genRandom(),
								method : 'POST',
								timeout : 6000,
								success : function(response) {
									var ret = response.responseText;
									 Ext.MessageBox.hide();
									if (processError(ret)){
									   e.record.set('name', '');
									   e.record.set('loginAccount', '');
									    return;
									}
									var ids = {};
										ids = eval("(" + ret + ")");
										if (!isEmpty(ids)) {
											e.record.set('name', ids['name']);
											
										}
								}
							})
					}
	},
	onNavigationTreeClick:function(record,view,e){
		e.preventDefault();
		var data = record.data,
		    gsDm = data.gsDm,
		    gridpanel = view.query("gridpanel[name='modulegridcenter']")[0],
		    formpanel = view.query("form[name='northformpanel']")[0];
		    //设置公司
			 formpanel.getForm().findField('gsdm').setValue(gsDm);
			 formpanel.getForm().findField('status').setValue(true);
			var baseParams = {};
			baseParams['gsdm'] = gsDm;
			gridpanel.getStore().proxy.extraParams = baseParams;
			gridpanel.getStore().reload();
			view.getViewModel().set('tc_selectedNames',
					'『<em>' + data.text + '</em>』所属用户');
	},
	getDefaultRecord : function(view) {
		 var gridpanel = view.query("gridpanel[name='modulegridcenter']")[0],
		     formpanel = view.query("form[name='northformpanel']")[0],
		     gsxx =  formpanel.getForm().findField('gsdm');
		 var add = {
				name : '',
				gsDm:gsxx.getValue(),
				loginPassword:'123456',
				status:true
			};
			return add;
	},
	onSetRole:function(btn ,e ){
		var view =  this.getView(),
		    gridpanel = btn.up('basis-luser').query("gridpanel[name='modulegridcenter']")[0],
		    record = gridpanel.getSelectionModel().getSelection();
		  if (record.length <= 0) {
						Ext.Msg.alert("小提示","此操作前请选择一条记录");
						return;
		  };
		  var selectId = record[0].get('id');
		     
		if (selectId > 0) {
			var  selectedRole = record[0].get('roleString');
		  if(isEmpty(listRoles)){
		      	Ext.Msg.alert('提示','无可用的角色');
		      	return;
		  } ;
		 var  itemcheckbox = getCheckBocxByListNoId(listRoles,'roles',selectedRole);
		  Ext.create('app.basis.view.region.LUserRoleWinView', {
		        	    customitems:Ext.JSON.decode(itemcheckbox),
						viewModel : view.getViewModel(),
						controller : view.getController(),
						title:"『<em>"+record[0].data['name']+"</em>』角色设置",
						selectedId : selectId
					}).show();
		} else{
			Ext.Msg.alert("小提示","请先保存新增的登陆用户 再设置其角色");
			return;
		}
	},
	onLUserRoleWinCancle:function(btn,e){
		var win = btn.ownerCt.ownerCt;
		win.close();
		Ext.getBody().unmask();
	},
	onLuserRoleSave:function(btn,e){
		var  win = btn.ownerCt.ownerCt, 
		    gridpanel = Ext.getCmp("lusermodule").query("gridpanel[name='modulegridcenter']")[0];
		    fileds = win.query("fieldcontainer")[0];
				   var selects = '';
				   fileds.cascade(function(child){
				   	   if(child.name=='roles' && child.checked == true){
				   	        selects = selects + (isEmpty(selects) ? '' : ',' )+child.filedId;
				   	   }
				   },this);
			
				   if(isEmpty(selects)){
				   	Ext.Msg.alert('小提示',"没有选择相应角色权限")
				   }else{
				   	 showLoading();
				   	Ext.Ajax.request({
								url : actionUrl + '/updaterole',
								method : 'POST',
								timeout : 6000,
								params : {
									selectedId :win.selectedId,
									roleString:selects,
									random:Random()
								},
								success : function(response) {
									Ext.MessageBox.hide();
									var ret = response.responseText;
								    if (processError(ret))
		                                 return;
									if (ret == "OK") {
										Ext.Msg.alert("提示", "更保存成功!", function(btn,
												e) {
											if (btn == "ok" || btn == "确定"){
												win.close();
							                    Ext.getBody().unmask();
							                    gridpanel.getStore().reload();
							                    gridpanel.getSelectionModel().deselectAll();
											}
										})
										
									}
								}
							})
				   }
	},
	onLUserRoleSelectClear:function(btn,e){
		 var win = btn.ownerCt.ownerCt;
		 var filedc = win.query("fieldcontainer")[0];
				   filedc.cascade(function(child){
				   	   if(child.name=='roles'){
				   	       child.setValue('checked', false);
				   	   }
				   },this)
	},
	onSetDlRole:function(btn , e ){
		var view =  this.getView(),
		    gridpanel = btn.up('basis-luser').query("gridpanel[name='modulegridcenter']")[0],
		    record = gridpanel.getSelectionModel().getSelection();
		    if (record.length != 1) {
						Ext.Msg.alert("小提示","此操作前请选择一条记录");
						return;
			};
			var selectId = record[0].get('id') ,
			    independentbz = false;
			if(!isEmpty(record[0].data['isIndependentRole']) && record[0].data['isIndependentRole'] )
					independentbz =true;
			if (selectId <= 0) {
						Ext.Msg.alert("小提示","请先保存新增的登陆用户 再设置其角色");
						return;
			};
			Ext.create('app.basis.view.region.LUserIndependentRoleWinView', {
						selectedId : selectId,
						independentbz:independentbz,
						viewModel : view.getViewModel(),
						controller : view.getController(),
						title:"『<em>"+record[0].data['name']+"</em>』角色设置"
					}).show();
	},
	onDlRoleTreeExpand:function(btn,e){
		var tree = btn.up("treepanel");
		tree.expandAll();
	},
	onDlRoleTreeCollapse:function(btn,e){
		var tree = btn.up("treepanel");
		tree.collapseAll();
	},
	onDlLUserRoleWinCancle:function(btn,e){
		var win = btn.ownerCt.ownerCt;
		win.close();
		Ext.getBody().unmask();
	},
	onDlLuserRoleSave:function(btn,e){
		 var win = btn.ownerCt.ownerCt;
				   var checkb = win.query("checkboxfield[name='strawproluserindependentrolebz']")[0];
				    var gridpanel = Ext.getCmp('detailgridpanel');
				   var selectedId = win.selectedId;
				  
		var  win = btn.ownerCt.ownerCt, 
		     checkb = win.query("checkbox")[0],
		     gridpanel = Ext.getCmp("lusermodule").query("gridpanel[name='modulegridcenter']")[0],
		     selectedId = win.selectedId;
		     if(selectedId == null || selectedId <=0){
					  alert("异常操作");
					  return ;
					};
		     var tree = win.query("treepanel[name='strawproluserindependentrolewintreepanel']")[0];
		     var node = tree.getChecked();
				   if(isEmpty(node) && checkb.getValue()){
				   	Ext.Msg.alert("小提示","没有为独立权限用户设置相应权限,如不需要设置可点击取消或将独立核算标记改为不选择")
				   	return;
			};
			showLoading();
			 Ext.Ajax.request({
		              	url : actionUrl + '/updateindependentrole',
		              	params : {
									jsonData : getjsonForTree(node),
									selectedId:selectedId,
									checkb:checkb.getValue()
								},
		            	success : function(response) {
										var ret = response.responseText;
										 Ext.MessageBox.hide();
										if (processError(ret))
		                                 return;
										Ext.MessageBox.confirm('确认', '保存成功，返回主界面吗?', function(btn){
										if (btn == 'yes') {
											win.close();
											//取消遮照
					                       // Ext.getBody().unmask();
					                        gridpanel.getStore().reload();
					                        gridpanel.getSelectionModel().deselectAll();
										}
										})
									},
			            scope : this
	              });
	},
	onDlRoleTreeCheckChange:function(node, checked, eOpts){
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
	selectRoles : function   (value, cellmeta, record, rowIndex, columnIndex, store) {
			var values =  eval("[" + value + "]");
			var re = "";
			if(isEmpty(value))
		         return "";
		    else{
		    	for(var i=0,nrow = listRoles.length;i< nrow;i++){
		    		var curdata =  Ext.JSON.decode(listRoles[i]);
		    		var kk = curdata['value'];
		    		 for(var k=0,nr = values.length;k < nr;k++){
		    		 	var tt = values[k];
		    		 	if(tt == curdata['value']){
		    		 		re = re+","+curdata['name'];
		    		 	}
		    		 }
		    	}
		    }
		    if(!isEmpty(re) && re.indexOf(",") >=0)
		       return re.substring(1);
		    else
		       return re;
    }
});;
