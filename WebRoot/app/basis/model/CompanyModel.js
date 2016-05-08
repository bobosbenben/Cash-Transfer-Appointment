Ext.define('app.basis.model.CompanyModel', {
			extend : 'app.module.model.ModelModule',
			alias : 'viewmodel.companymodel',
			constructor : function() {  
			    var me = this;  
			    this.callParent(arguments);  
			    var datas = {
			        module_isNorth:true,
			        module_isWest:true,
			        
			         tn_otherNorthButton:['->',
										{text : ' 操作指引 ',
										glyph : 0xf184,
					                    tooltip : '在这里可以新增和删除机构'}
										],		
			         tn_searchfields : [Ext.create('app.module.view.CacheComboModule', {
											name : "gsdm",
											fieldLabel : "公司",
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
										test : 'name',
										name : '名称'
									},{
										test : 'shortName',
										name : '简称'
									},{
										test : 'sjgsDm',
										name : '上级公司'
									}],
					 tc_fields:[
						 		{name:'nodeNumber',type:'string'},
						        {name: 'id', type : 'int'},
						        //{name: 'gsDm',type: 'string'},
						        {name: 'name',type: 'string'},
						        {name: 'shortName',type: 'string'},
						        {name: 'sjgsDm',type: 'string'},
						         {name: 'sjgsMc',type: 'string'},
						         {name: 'deepth',type: 'int'},
						        {name: 'isIndependentAccounting',type: 'boolean'},
						        {name: 'town',type: 'string'},
						        {name: 'status',type: 'boolean'},
						        {name: 'isDayknot',type: 'boolean'},
						        {name: 'bz',type: 'string'}
						       ],
					 tc_gridSchemes:[{
									xtype : 'rownumberer'
								},
						 		//{
								//	header : "代码",
								//	width : 100,
								//	dataIndex : 'gsDm'
								//},
						 		{
						 			header: "网点名称",
									width: 120,
									dataIndex: 'nodeNumber'
					 			},
						 		{
									header : "名称",
									width : 180,
									dataIndex : 'name',
									field : { 
										xtype : 'textfield'
									}
								}, {
									header : "简称",
									width : 100,
									dataIndex : 'shortName',
									field : { 
										xtype : 'textfield'
									}
								}, {
									header : "上级",
									width : 100,
									dataIndex : 'sjgsMc',
									align : 'center'
								},{
									header : "独立核算",
									width : 100,
									dataIndex : 'isIndependentAccounting',
									align : 'center',
									xtype: 'checkcolumn'
								},{
									header : "镇区",
									width : 100,
									dataIndex : 'town',
									align : 'center',
									editor: {
					                 xtype: 'numberfield',
					                 minValue: 1
									}
					              },{
									header : "是否取消日结",
									width : 100,
									dataIndex : 'isDayknot',
									align : 'center',
									value : true,
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
								} ]
			   /** ********other************ */								
			  };
			   Ext.apply(me.data, datas);  
			}
			
})