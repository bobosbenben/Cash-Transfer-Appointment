Ext.define('app.sys.model.MenuModel', {
			extend : 'app.module.model.ModelModule',
			alias : 'viewmodel.menumodel',
			constructor : function() {  
			    var me = this;  
			    this.callParent(arguments);  
			    var datas = {
			    	
			        module_viewtype:'sys-menu',
			        module_isWest:true,
			        /************treepanel****************/
			        tw_treeload:'menutreedata',
			        tw_treetitle:'功能菜单',
			        tw_treerootvisible:true,
			   
					/************gridcenter****************/
					 tc_title:'属性',
					 tc_isNorthButton:true,
					 tc_fields:[{name: 'id',   type: 'int'},
								{name: 'gsDm',   type: 'string'},
								{name: 'name',   type: 'string'},
								{name: 'url',   type: 'string'},
								{name: 'status',   type: 'boolean'},
								{name: 'deepth',   type: 'int'},
								{name: 'menuLimits',   type: 'string'},
								{name: 'bz',   type: 'string'},
								{name: 'parentId',   type: 'string'},
							    {name: 'parentName',   type: 'string'},
								{name: 'menuIndex',   type: 'string'},
								{name: 'glyph',   type: 'string'}
							],
					 tc_gridSchemes:[{
									xtype : 'rownumberer'
									}, {
												header : "父节点",
												width : 100,
												dataIndex : 'parentName'
											}, {
												header : "名称",
												width : 100,
												dataIndex : 'name',
												field : { 
													xtype : 'textfield'
												}
											}, {
												header : "资源Url",
												width : 250,
												dataIndex : 'url',
												field : { 
													xtype : 'textfield'
												}
											}, {
												header : "排序号",
												width : 150,
												dataIndex : 'menuIndex',
												field : { 
													xtype : 'textfield'
												}
											}, {
												header : "显示权限",
												width : 150,
												dataIndex : 'menuLimits',
												field : { 
													xtype : 'textfield'
												}
											}, {
												header : "显示图标",
												width : 150,
												dataIndex : 'glyph',
												field : { 
													xtype : 'textfield'
												}
											}, {
												header : "备注",
												flex:1,
												dataIndex : 'bz',
												field : {
													xtype : 'textfield'
												}
											}],
			   /**********other*************/								
				menutreewin:{win_title:'新增功能菜单',win_width:400,win_Height:400,win_glyph:0xf067}
			  };
			   Ext.apply(me.data, datas);  
			},
			initGridNorthButton:function(vm,e){
				  var d = {
							xtype : 'toolbar', 
							dock : 'top',
							items:['->',{xtype:'buttontransparent',
							            text:'保存',
							            glyph : 0xf0c7, 
							            bind : {
											hidden : '{!tn_isToolbarSave}'
										},
							            handler:'onBtnUpdate'}
							]
						};
					e.push(d);	
			}
})