/**
 * ClassName 角色管理
 * 
 */
Ext.define('app.main.view.region.ChangePwdWinView', {
			extend : 'app.module.view.BaseWindow',
			info : 'changepwdwin',
			initComponent : function() {
		         var me = this;
			me.items= Ext.create('Ext.form.Panel', {
								bodyPadding : 5,
								buttonAlign : 'center',
								border : false,
								defaults : {
									xtype:'textfield',
								    bodyStyle : 'padding:10px 0px 1px 1px'
								},
							    items : [ {
															fieldLabel : "原密码",
															name : "oldPwd",
															inputType : 'password',
															allowBlank : false
										},
										{
															fieldLabel : "新密码",
															name : "newPwd1",
															inputType : 'password',
															allowBlank : false
														},
										{				
															fieldLabel : "密码确认",
															name : "newPwd2",
															inputType : 'password',
															allowBlank : false
								}],
								dockedItems : [Ext.create('Ext.toolbar.Toolbar',{
						            xtype : 'toolbar',
									dock : 'bottom',
									defaults : {
										xtype : 'buttontransparent'
									},
									items : ['->',{
												text : '保   存',
												glyph : 0xf0c7,
												formBind: true, 
										        disabled: true,
												handler:'onChangePwdwinSave'
											},{ 
										        text: '重置',
										        glyph : 0xf0e2,
										        handler: function() {
										            this.up('form').getForm().reset();
										        }
										    }, {
												text : '取  消',
												glyph : 0xf00d,
												handler:function(btn,e){
													var win = btn.up('window');
													win.close();
												}
											},'->']
								})]
			})
			me.callParent(arguments);
		}
})