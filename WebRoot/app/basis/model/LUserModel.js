Ext.define('app.basis.model.LUserModel', {
			extend : 'app.module.model.ModelModule',
			alias : 'viewmodel.lusermodel',
			constructor : function() {  
			    var me = this;  
			    this.callParent(arguments);  
			    var datas = {
			        module_isNorth:true,
			        module_isWest:true,
					module_isSerach:false, //不显示搜索框，查询条件那一栏,但是这样会导致问题
			        
				      tn_otherNorthButton:[{
											text : ' 设置角色权限 ',
											tooltip : '为用户配置角色！',
											glyph : 0xf0f0,
											handler:'onSetRole'
										},{
											text : ' 设置独立权限 ',
											tooltip : '为用户配置一个独立的权限,系统优先判断此权限',
											glyph : 0xf21b,
											handler:'onSetDlRole'
										},'->',
										{text : ' 操作指引 ',
										glyph : 0xf184,
					                    tooltip : '操作指引：1.点击新增,新增一个用户并保存;<br>2.点击角色权限设置以设置指定用户的相应功能权限;<br>3.一人管多店时则设置权限店面'}
										],		
			         tn_searchfields : [Ext.create('app.module.view.CacheComboModule', {
											name : "gsdm",
											fieldLabel : "公司",
											//dataname:'ListHsCompanysAndJTCache'
						 					dataname:'ListCompanysCache'
										}),Ext.create('app.module.view.LocalComboModule',{
								            name : "status",
											fieldLabel : "启用标志",
											datas:[
											        {"value":"true", "name":"启用"},
											        {"value":"false", "name":"不启用"}
											],
											    value:'true'
			                              })
			                         ],
			        
			        /** **********treepanel*************** */
			        tw_treeload:'companytreedata',
			        tw_treetitle:'公司信息',
			   
					/** **********gridcenter*************** */
					 tc_title:'信息',
						tc_checkFields:[{
										test : 'loginAccount',
										name : '登陆用户'
									},{
										test : 'loginPassword',
										name : '密码'
									},{
										test : 'gsDm',
										name : '所属公司'
									}],
					 tc_fields:[
						        {name: 'id', type : 'int'},
						        {name: 'gsDm',type: 'string'},
						        {name: 'name',type: 'string'},
						        {name: 'loginAccount',type: 'string'},
						        {name: 'loginPassword',type: 'string'},
						        {name: 'roleString',type: 'string'},
						        {name: 'searchDay',type: 'int'},
						        {name: 'isLookPrice',type: 'boolean'},
						        {name: 'isModifyPrice',type: 'boolean'},
						        {name: 'status',type: 'boolean'},
						        {name: 'limitsCompanys',type: 'string'},
						        {name: 'isIndependentRole',type: 'boolean'},
						        {name: 'bz',type: 'string'}
						       ],
					 tc_gridSchemes:[{
										xtype : 'rownumberer'
									}, {
										header : "公司",
										width : 100,
										dataIndex : 'gsDm',
										align : 'center',
										renderer: renderCompany,
										editor : Ext.create('app.module.view.CacheComboModule', {
											       forceSelection:true,
											       dataname:'ListCompanysCache'
											    })
									}, {
										header : "登陆用户",
										width : 80,
										dataIndex : 'loginAccount',
										field : { 
											xtype : 'textfield'
										}
									}, {
										header : "密码",
										width : 100,
										dataIndex : 'loginPassword',
										field : { 
											xtype : 'textfield'
										}
									}, {
										header : "名称",
										width : 100,
										dataIndex : 'name'
									}, {
										header : "角色",
										width : 150,
										dataIndex : 'roleString',
										renderer:'selectRoles'
									}, {
									header: "查询天数", 
									width: 100, 
									align:"right",
									dataIndex: 'searchDay',
									field : { 
											xtype : 'numberfield',
											minValue:0,
											allowDecimals:false
										}
					                }, {
									header: "查看成本", 
									width: 120, 
									dataIndex : 'isLookPrice',
								    align : 'center',
									xtype: 'checkcolumn'
									
					                } , {
									header: "修改单据", 
									width: 100, 
									dataIndex : 'isModifyPrice',
								    align : 'center',
									xtype: 'checkcolumn'
									
					                } ,{
										header : "是否启用",
										width : 100,
										dataIndex : 'status',
										align : 'center',
										value : true,
									    xtype: 'checkcolumn'
										
									}, {
										header : "独立权限",
										width : 100,
										align : 'center',
										dataIndex : 'isIndependentRole',
										disabled :true,
										xtype: 'checkcolumn'
									}, {
										header : "备注",
										width : 100,
										dataIndex : 'bz',
										editor: {
						                xtype: 'textfield'
						              }
									} ],
			   /** ********other************ */	
				luserrolewin:{win_title:'角色设置',win_width:400,win_Height:400,win_glyph:0xf0f0},
				luserdlrolewin:{win_title:'独立角色设置',win_width:500,win_Height:400,win_glyph:0xf182}
			  };
			   Ext.apply(me.data, datas);  
			}
			
})