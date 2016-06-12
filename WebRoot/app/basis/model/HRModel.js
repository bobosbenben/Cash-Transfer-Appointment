Ext.define('app.basis.model.HRModel', {
			extend : 'app.module.model.ModelModule',
			alias : 'viewmodel.hrmodel',
			constructor : function() {  
			    var me = this;  
			    this.callParent(arguments);  
			    var datas = {
			        module_isNorth:true,
			        module_isWest:true,
					module_isSerach:false,
			        
			         tn_otherNorthButton:[{
											text : ' 更改更多信息 ',
											tooltip : '修改更多的客户信息',
											glyph : 0xf086,
											handler:'onUpdateMore'
										},'->',
										{text : ' 操作指引 ',
										glyph : 0xf184,
					                    tooltip : '小提示:1.新建时选择关联登陆可创建默认用户'}
										],		
			         tn_searchfields : [Ext.create('app.module.view.CacheComboModule', {
											name : "dept",
											fieldLabel : "入职部门",
											valueField:'name',
											dataname:'ListCompanysCache'
										}),
			                              Ext.create('app.module.view.LocalComboModule',{
								            name : "isResignation",
											fieldLabel : "是否离职",
											datas:[
											        {"value":"true", "name":"是"},
											        {"value":"false", "name":"否"}
											],
											    value:'false'
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
					 tc_isDeleteData:true,
					 tc_gridseltype:'rowmodel',
						tc_checkFields:[{
										test : 'dept',
										name : '部门'
									},{
										test : 'name',
										name : '名称'
									}],
					 tc_fields:[
						        {name: 'id', type : 'int'},
						        {name: 'gsDm',type: 'string'},
						        {name: 'name',type: 'string'},
						        {name: 'zjm',type: 'string'},
						        {name: 'post',type: 'string'},
						        {name: 'dept',type: 'string'},
						        {name: 'sex',type: 'string'},
						        {name: 'isResignation',type: 'boolean'},
						        {name: 'status',type: 'boolean'},
						        {name: 'bz',type: 'string'}
						       ],
					 tc_gridSchemes:[{
										xtype : 'rownumberer'
									}, {
										header : "所属部门",
										width : 150,
										dataIndex : 'dept',
										align : 'center',
										editor : Ext.create('app.module.view.CacheComboModule', {
											       dataname:'ListCompanysCache',
											       valueField:'name',
												   multiSelect:true
											    })
									}, {
										header : "工号",
										width : 100,
										dataIndex : 'zjm'
									}, {
										header : "姓名",
										width : 180,
										dataIndex : 'name',
										//renderer : renderYxbz,
										field : { 
											xtype : 'textfield'
										}
									}, {
										header : "岗位",
										width : 150,
										dataIndex : 'post',
										editor : Ext.create('app.module.view.LocalComboModule', {
														datas : listPosts.length == 0 ? listPosts :  eval('['+listPosts+']'),
												        multiSelect:true
					                             })
									}, {
										header : "性别",
										width : 100,
										dataIndex : 'sex',
										renderer: Ext.util.Format.sexRenderer,
										editor :Ext.create('app.module.view.LocalComboModule', {
																datas : [{
																			"value" : "M",
																			"name" : "男"
																		}, {
																			"value" : "F",
																			"name" : "女"
																		}]
										})
									},{
										header : "是否离职",
										width : 100,
										dataIndex : 'isResignation',
										align : 'center',
										xtype: 'checkcolumn'
						              },{
										header : "是否启用",
										width : 100,
										dataIndex : 'status',
										align : 'center',
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
				hraddwin:{win_title:'人事新增',win_width:820,win_Height:500,win_glyph:0xf067},
				hreditwin:{win_title:'人事修改',win_width:820,win_Height:500,win_glyph:0xf086}
			  };
			   Ext.apply(me.data, datas);  
			}
			
})