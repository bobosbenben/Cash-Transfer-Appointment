/**
 * ClassName 角色管理
 * 
 */
Ext.define('app.sys.view.region.RoleEditWinView', {
			extend : 'app.module.view.BaseWindow',
			info : 'roleeditwin',
			selectedId : null,
			initComponent : function() {
				var me = this, vm = me.getViewModel();
				me.items = [Ext.create('Ext.tree.Panel', {
							name : 'roleedittreepanel',
							animate : false,
							store : Ext.create(
									'app.module.store.TreeStoreModule', {
										fields : vm.get('tw_treefields'),
										proxy : {
											url : actionUrl
													+ '/getrolemenus?selectedId='
													+ this.selectedId
										}
									}),
							rootVisible : false,

							dockedItems : [Ext.create('Ext.toolbar.Toolbar', {
								       defaults : {
											xtype : 'buttontransparent'
										},

										items : [{
													text : '展开全部',
													glyph : 0xf065,
													handler:'onTreeExpand'
												}, {
													text : '收起全部',
													glyph : 0xf066,
													handler:'onTreeCollapse'
												}]
									})],
							 listeners: {  
						        checkchange: function( node, checked, eOpts ){ 
						        	this.up('window').getController().onTreeCheckChange(node, checked, eOpts);
						        }  
						    }
						})],
					
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
									handler:'onRoleEditSave'
								},{xtype:'tbfill'}, {
									text : '取  消',
									glyph : 0xf00d,
									handler:'onRoleEditCancle'
								}]
					}]
		})