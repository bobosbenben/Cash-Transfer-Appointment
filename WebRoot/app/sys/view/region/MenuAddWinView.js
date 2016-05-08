/**
 * ClassName 角色管理
 * 
 */
Ext.define('app.sys.view.region.MenuAddWinView', {
	extend : 'app.module.view.BaseWindow',
	info:'menutreewin',
	selectedId : null,
	parentName:null,
	deepth:0,
	initComponent : function() {
		var me = this,vm = me.getViewModel();
		Ext.applyIf(me, {

			items : [ Ext.create('Ext.form.Panel', {
				        name: 'menuaddformpanel',
				        animate : false,
						bodyPadding: 5,
						layout: 'anchor',
						buttonAlign:'center',
						defaults: {
					        anchor: '100%'
					    },
					    defaultType: 'textfield',
					     items: [{
					        fieldLabel: '父节点Id',
					        name: 'parentId',
					       readOnly :true,
					       value:this.selectedId,
					       hidden:true
					    },{
					        fieldLabel: '父节点',
					        name: 'parentName',
					       readOnly :true,
					       value:this.parentName
					    },{
					        fieldLabel: '新增菜单名称',
					        name: 'name',
					        allowBlank: false
					    },{
					        fieldLabel: '资源Url',
					        name: 'url'
					    },{
					        fieldLabel: '菜单级数',
					        name: 'deepth',
					        value:this.deepth,
					        readOnly :true
					    },{
					        fieldLabel: '排序',
					        name: 'menuIndex'
					    },{
					        fieldLabel: '显示权限',
					        name: 'menuLimits',
					        maxLength:7,
					        regex:/^[0-1][0-1][0-1][0-1][0-1][0-1][0-1]/,
					        regexText:"参照下面的提示设置权限！"
					    },{
				        xtype: 'label',
				        html: "<font color='red'>显示权限设置示例:1111110则对应(增删改打查审撤)1表示有0表示没有！</font>",
				        margin: '0 0 0 10'
				    }],
				    dockedItems : [{
						xtype : 'toolbar',
						dock : 'bottom',
						//style : 'padding:10px 50px 10px 50px',// 上, 右,下,左
						defaults : {
							xtype : 'buttontransparent'
						},
						items : ['->',{ 
							        text: '重置',
							        glyph : 0xf0e2,
							        handler: function() {
							            this.up('form').getForm().reset();
							        }
							    },{
									text : '提交',
									glyph : 0xf0c7,
									formBind: true, 
							        disabled: true,
									padding:'0 30px 0px 30px',
									handler:'menuSubmit'
								}, {
									 text: '关闭',
							         glyph : 0xf00d,
							         handler: function() {
							            this.up('window').close();
							            Ext.getBody().unmask();
							        }
								},'->']
					}]
		      })
		  ]
		});
		me.callParent(arguments);
	}
	
})