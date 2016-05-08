/**
 * ClassName 角色管理
 * 
 */
Ext.define('app.basis.view.region.HRAddWinView', {
			extend : 'app.module.view.BaseWindow',
			info : 'hraddwin',
			items:Ext.create('Ext.form.Panel', {
				buttonAlign : 'center',
				border : false,
				layout : 'column',
				autoScroll : true,
				defaults : {
					bodyPadding : 4
				},
				items : [{
					xtype : 'fieldset',
					title : "入/离职信息(<font color='red'>*</font>为必填项)",
					collapsible : true,
					width : 780,
					layout : 'column',
					items : [{

						columnWidth : 0.32,
						layout : 'anchor',
						border : false,
						items : [{
									xtype : 'textfield',
									fieldLabel : "工号<font color='red'>*</font>",
									labelWidth : 70,
									anchor : '90%',
									maxLength : 10,
									allowBlank : false,
									maxLengthText : "可输入的最大长度为10",
									regex : /^\w+$/,
									regexText : '只能输入英文或数字',
									//width:20,
									name : 'zjm',
									listeners:{
									  change :function(field,newValue,oldValue,eOpts ) {
									  	var formpanel = field.up('form'),
									  	    form = formpanel.getForm(),
									  	    loginName = form.findField('loginName');
										    loginName.setValue(newValue);
									  }
									
									}
								}]
					}, {
						columnWidth : 0.32,
						layout : 'anchor',
						border : false,
						items : [{
									xtype : 'textfield',
									fieldLabel : "姓名<font color='red'>*</font>",
									labelWidth : 70,
									anchor : '90%',
									maxLength : 10,
									allowBlank : false,
									maxLengthText : "可输入的最大长度为10",
									name : 'name'
								}]
					}, {
						columnWidth : 0.36,
						layout : 'anchor',
						border : false,
						items : Ext.create('app.module.view.CacheComboModule',
								{
									name : "dept",
									fieldLabel : "所属部门<font color='red'>*</font>",
									labelWidth : 70,
									valueField:'name',
									multiSelect:true,
									//forceSelection:true,
									anchor : '90%',
									dataname : 'ListCompanysCache',
									allowBlank : false
								})
					}, {
						columnWidth : 0.32,
						layout : 'anchor',
						border : false,
						items :
							//Ext.create('Ext.form.field.Date',
							//	{
							//		fieldLabel : "入职日期<font color='red'>*</font>",
							//		name : 'entryDate',
							//		labelWidth : 70,
							//		anchor : '90%',
							//		format : 'Y-m-d',
							//		allowBlank : false
							//	})
								[{
									xtype: "datefield",
									fieldLabel : "入职日期<font color='red'>*</font>",
									name : 'entryDate',
									labelWidth : 70,
									anchor : '90%',
									format : 'Y-m-d',
									allowBlank : false
								}]
					}, {
						columnWidth : 0.32,
						layout : 'anchor',
						border : false,
						items : Ext.create('app.module.view.LocalComboModule', {
									fieldLabel : '是否转正',
									name : 'isPositive',
									labelWidth : 70,
									forceSelection:true,
									anchor : '90%',
									datas : [{
												"value" : "true",
												"name" : "是"
											}, {
												"value" : "false",
												"name" : "否"
											}],
									value : 'false'
								})
					}, {
						columnWidth : 0.36,
						layout : 'anchor',
						border : false,
						items : Ext.create('Ext.form.field.Date',
								{
									fieldLabel : '转正日期',
									name : 'zzrq',
									labelWidth : 70,
									anchor : '90%',
									format : 'Y-m-d'
								})
					}, {
						columnWidth : 0.32,
						layout : 'anchor',
						border : false,
						items : Ext.create('app.module.view.LocalComboModule', {
									fieldLabel : '是否离职',
									name : 'isResignation',
									labelWidth : 70,
									forceSelection:true,
									anchor : '90%',
									datas : [{
												"value" : "true",
												"name" : "是"
											}, {
												"value" : "false",
												"name" : "否"
											}],
									value : 'false'
								})
					}, {
						columnWidth : 0.32,
						layout : 'anchor',
						border : false,
						items : Ext.create('Ext.form.field.Date',
								{
									fieldLabel : '离职日期',
									name : 'resignationDate',
									labelWidth : 70,
									anchor : '90%',
									format : 'Y-m-d'
								})
					}, {
						columnWidth : 0.36,
						layout : 'anchor',
						border : false,
						items : Ext.create('app.module.view.LocalComboModule', {
									fieldLabel : '离职类型',
									name : 'resignationType',
									forceSelection:true,
									labelWidth : 70,
									anchor : '90%',
									datas : listResignationType.length == 0 ? listResignationType :  eval('['+listResignationType+']')

								})
					}, {
						columnWidth : 0.32,
						layout : 'anchor',
						border : false,
						items : Ext.create('Ext.form.field.Date',
								{
									fieldLabel : '参加社保日期',
									name : 'socialSecurityDate',
									labelWidth : 90,
									anchor : '90%',
									format : 'Y-m-d'
								})
					}, {
						columnWidth : 0.32,
						layout : 'anchor',
						border : false,
						items : [{
									xtype : 'textfield',
									fieldLabel : '担保人',
									labelWidth : 70,
									anchor : '90%',
									maxLength : 10,
									maxLengthText : "可输入的最大长度为10",
									name : 'guarantor'
								}]
					}, {
						columnWidth : 0.36,
						layout : 'anchor',
						border : false,
						items : [{
									xtype : 'textfield',
									fieldLabel : '担保人关系',
									labelWidth : 80,
									anchor : '90%',
									maxLength : 10,
									maxLengthText : "可输入的最大长度为10",
									name : 'guarantorRelations'
								}]
					}, {
						columnWidth : 0.32,
						layout : 'anchor',
						border : false,
						items : Ext.create('app.module.view.LocalComboModule', {
									fieldLabel : '岗位',
									name : 'post',
									multiSelect :true,//多选,为true则不能与forceSelection同时使用，会造成多先时无法显示的情况
									//forceSelection:true,
									labelWidth : 70,
									anchor : '90%',
									datas : listPosts.length == 0 ? listPosts :  eval('['+listPosts+']')

								})
					}, {
						columnWidth : 0.32,
						layout : 'anchor',
						border : false,
						items : Ext.create('app.module.view.LocalComboModule', {
									fieldLabel : '是否签订劳动合同',
									name : 'islaborcontract',
									forceSelection:true,
									labelWidth : 120,
									anchor : '90%',
									datas : [{
												"value" : "true",
												"name" : "是"
											}, {
												"value" : "false",
												"name" : "否"
											}],
									value : 'false'
								})
					}, {
						xtype : 'textfield',
						hidden : true,
						name : 'id'
					}]
				}, {
					xtype : 'fieldset',
					title : '个人信息',
					collapsible : true,
					width : 780,
					layout : 'column',
					items : [{

								columnWidth : 0.32,
								layout : 'anchor',
								border : false,
								items : [{
											xtype : 'textfield',
											fieldLabel : '民族',
											labelWidth : 70,
											anchor : '90%',
											maxLength : 30,
											maxLengthText : "可输入的最大长度为30",
											regex : /[\u4e00-\u9fa5]/,
											regexText : '只能输入中文',
											//width:20,
											name : 'nation'
										}]
							}, {
								columnWidth : 0.32,
								layout : 'anchor',
								border : false,
								items : Ext.create('app.module.view.LocalComboModule', {
											fieldLabel : '性别',
											name : 'sex',
											labelWidth : 70,
											forceSelection:true,
											anchor : '90%',
											datas : [{
														"value" : "M",
														"name" : "男"
													}, {
														"value" : "F",
														"name" : "女"
													}],
											value : '男'
										})
							}, {
								columnWidth : 0.36,
								layout : 'anchor',
								border : false,
								items : Ext.create(
										'Ext.form.field.Date', {
											fieldLabel : '出生日期',
											name : 'birthday',
											labelWidth : 70,
											anchor : '90%',
											format : 'Y-m-d'
										})
							}, {
								columnWidth : 0.32,
								layout : 'anchor',
								border : false,
								items : [{
											xtype : 'textfield',
											fieldLabel : '身份证',
											labelWidth : 70,
											anchor : '90%',
											maxLength : 50,
											maxLengthText : "可输入的最大长度为50",
											regex : /^\w+$/,
											regexText : '只能输入英文或数字',
											//width:20,
											name : 'idNumber'
										}]
							}, {
								columnWidth : 0.32,
								layout : 'anchor',
								border : false,
								items : [{
											xtype : 'textfield',
											fieldLabel : '电话号码',
											labelWidth : 70,
											anchor : '90%',
											maxLength : 11,
											maxLengthText : "可输入的最大长度为11",
											regex : /1[0-9]{10}/,
											regexText : '请输入有效的电话号码',
											name : 'phone'
										}]
							}, {
								columnWidth : 0.36,
								layout : 'anchor',
								border : false,
								items : Ext.create(
										'app.module.view.LocalComboModule', {
											fieldLabel : '是否已婚',
											name : 'isMarital',
											labelWidth : 70,
											forceSelection:true,
											anchor : '90%',
											datas : [{
														"value" : "true",
														"name" : "是"
													}, {
														"value" : "false",
														"name" : "否"
													}],
											value : 'true'
										})
							}, {
								columnWidth : 0.32,
								layout : 'anchor',
								border : false,
								items : Ext.create(
										'app.module.view.LocalComboModule', {
											fieldLabel : '学历',
											name : 'diploma',
											labelWidth : 70,
											forceSelection:true,
											anchor : '90%',
											datas : listDiploma.length == 0 ? listDiploma : eval('['+listDiploma+']')

										})
							}, {
								columnWidth : 0.32,
								layout : 'anchor',
								border : false,
								items : [{
											xtype : 'textfield',
											fieldLabel : '工资卡所属银行',
											//labelWidth : 100,
											anchor : '90%',
											maxLength : 20,
											maxLengthText : "可输入的最大长度为20",
											name : 'bank'
										}]
							}, {
								columnWidth : 0.36,
								layout : 'anchor',
								border : false,
								items : [{
											xtype : 'textfield',
											fieldLabel : '工资卡号',
											labelWidth : 70,
											anchor : '90%',
											maxLength : 20,
											maxLengthText : "可输入的最大长度为20",
											regex : /^[0-9]*$/,
											regexText : '只能输入数字',
											name : 'bankAccount'
										}]
							}, {
								columnWidth : 0.9,
								layout : 'anchor',
								border : false,
								items : [{
											xtype : 'textfield',
											fieldLabel : '家庭地址',
											labelWidth : 70,
											anchor : '90%',
											maxLength : 200,
											maxLengthText : "可输入的最大长度为200",
											name : 'idAddr'
										}]
							}, {
								columnWidth : 0.9,
								layout : 'anchor',
								border : false,
								items : [{
											xtype : 'textarea',
											fieldLabel : '备注',
											labelWidth : 70,
											anchor : '90%',
											maxLength : 200,
											maxLengthText : "可输入的最大长度为200",
											name : 'bz'
										}]
							}]
				}, {
					xtype : 'fieldset',
					title : '关联登陆(添加登陆信息)',
					width : 780,
					id : 'relationloginfieldset',
					layout : 'anchor',
					collapsed : true,
					checkboxToggle : true,
					items : [{
								xtype : 'textfield',
								fieldLabel : '登陆名',
								name : 'loginName',
								readOnly : true
							}, {
								width : 780,
								layout : 'column',
								border : false,
								items : [{
											columnWidth : 0.365,
											layout : 'anchor',
											border : false,
											items : [{
														xtype : 'textfield',
														fieldLabel : '登陆密码',
														name : 'loginpwd',
														inputType : 'password',
														anchor : '90%'
													}]
										}, {
											columnWidth : 0.6,
											xtype : 'checkbox',
											inputValue : true,
											name : 'relationLogin',
											boxLabel : "以此做为用户登陆<font color='red'>(勾选后将自动创建一个角色为空的登陆用户)</font>"
										}]
							}]
				}],
				dockedItems : [{
						xtype : 'toolbar',
						dock : 'bottom',
						//style : 'padding:10px 100px 10px 100px',// 上, 右,下,左
						defaults : {
							xtype : 'buttontransparent'
						},
						items : ['->',{
									text : '保   存',
									glyph : 0xf0c7,
									formBind: true, 
							        disabled: true,
									handler:'onHRAddViewSave'
								},{ 
							        text: '重置',
							        glyph : 0xf0e2,
							        handler: function() {
							            this.up('form').getForm().reset();
							        }
							    }, {
									text : '取  消',
									glyph : 0xf00d,
									handler:'onHRAddViewCancle'
								},'->']
					}]
			}),
			initComponent : function() {
				var me = this, vm = me.getViewModel();
				me.callParent(arguments);
			}
			
		})