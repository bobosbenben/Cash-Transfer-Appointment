/**
 * ClassName 角色管理
 * 
 */
Ext.define('app.basis.view.region.LUserRoleWinView', {
			extend : 'app.module.view.BaseWindow',
			info : 'luserrolewin',
			selectedId : null,
			initComponent : function() {
				var me = this, vm = me.getViewModel();
				me.items = Ext.create('Ext.form.Panel', {
							items:[Ext.create('Ext.form.FieldContainer',{
							     fieldLabel: '角色选择',
							     defaultType: 'checkboxfield',
							     items:this.customitems
							})]
			    });
		    me.callParent(arguments);
			},
			dockedItems : [{
						xtype : 'toolbar',
						dock : 'top',
						defaults : {
							xtype : 'buttontransparent'
						},
						items : [{
									text : '取消选择',
									glyph : 0xf064,
									handler:'onLUserRoleSelectClear'
								}]
					},{
						xtype : 'toolbar',
						dock : 'bottom',
						style : 'padding:10px 50px 10px 50px',// 上, 右,下,左
						defaults : {
							xtype : 'buttontransparent'
						},
						items : [{
									text : '保   存',
									glyph : 0xf0c7,
									handler:'onLuserRoleSave'
								},{xtype:'tbfill'}, {
									text : '取  消',
									glyph : 0xf00d,
									handler:'onLUserRoleWinCancle'
								}]
					}]
		})