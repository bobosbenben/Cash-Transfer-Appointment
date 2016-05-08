/**
 * ClassName 角色管理
 * 
 */
Ext.define('app.basis.view.region.LUserIndependentRoleWinView', {
			extend : 'app.module.view.BaseWindow',
			info : 'luserdlrolewin',
			selectedId : null,
			initComponent : function() {
				var me = this, vm = me.getViewModel();
				me.items =[Ext.create('Ext.tree.Panel', {
					        name:'strawproluserindependentrolewintreepanel',
							animate : false,
							store : Ext.create(
									'app.module.store.TreeStoreModule', {
										fields : vm.get('tw_treefields'),
										proxy : {
											url : actionUrl
													+ '/getindependentrole?selectedId='+this.selectedId
										}
									}),
							rootVisible : false,

							dockedItems : [{
							       xtype: 'fieldcontainer',
							      defaultType: 'checkboxfield',
							      items: [{
							      	  boxLabel:"独立核算标记<font color='red'>(注意：勾选上设置的权限才能升效)</font>",
							      	  name:'strawproluserindependentrolebz',
							      	  checked:this.independentbz
							        }
							      ]
								
							},Ext.create('Ext.toolbar.Toolbar', {
								       defaults : {
											xtype : 'buttontransparent'
										},

										items : [{
													text : '展开全部',
													glyph : 0xf065,
													handler:'onDlRoleTreeExpand'
												}, {
													text : '收起全部',
													glyph : 0xf066,
													handler:'onDlRoleTreeCollapse'
												}]
									})],
							 listeners: {  
						        checkchange: function( node, checked, eOpts ){ 
						        	this.up('window').getController().onDlRoleTreeCheckChange(node, checked, eOpts);
						        }  
						    }
						})];
		    me.callParent(arguments);
			},
			dockedItems : [{
						xtype : 'toolbar',
						dock : 'bottom',
						style : 'padding:10px 50px 10px 50px',// 上, 右,下,左
						defaults : {
							xtype : 'buttontransparent'
						},
						items : [{
									text : '保   存',
									glyph : 0xf0c7,
									handler:'onDlLuserRoleSave'
								},{xtype:'tbfill'}, {
									text : '取  消',
									glyph : 0xf00d,
									handler:'onDlLUserRoleWinCancle'
								}]
					}]
		})