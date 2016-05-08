Ext.define('app.sys.model.RoleModel', {
			extend : 'app.module.model.ModelModule',
			alias : 'viewmodel.rolemodel',
			constructor : function() {  
			    var me = this;  
			    this.callParent(arguments);  
			    var datas = {
			       module_isNorth:true,
			       
			       tn_otherNorthButton:[{text : ' 角色权限设置 ',
										tooltip : '为角色制定哪些功能可以用！',
										glyph : 0xf085,
										handler:'setRolePermission'
										},'->',
										{text : ' 操作指引 ',
										glyph : 0xf184,
					                    tooltip : '操作指引：1.点击新增,新增一个角色并保存;<br>2.点击角色权限设置以设置指定角色的相应权限'}
										],		
			       
					/************gridcenter****************/
					 tc_title:'角色',
					 tc_isgridforcefit:false,
					 tc_isAutoLoad:true,
					 tc_isDeleteData:true,
					 tc_fields: [
						        {name: 'id',   type: 'int'},
						        {name: 'gsDm',   type: 'string'},
						        {name: 'name',   type: 'string'}
						       ],
					 tc_gridSchemes:[{
										xtype : 'rownumberer'
									}, {
										header : "角色名称",
										width : 100,
										dataIndex : 'name',
										field : { 
											xtype : 'textfield'
										}
									}],
				
			   /**********other*************/								
				roleeditwin:{win_title:'角色权限设置',win_width:400,win_Height:400,win_glyph:0xf085}
			  };
			   Ext.apply(me.data, datas);  
			}
})