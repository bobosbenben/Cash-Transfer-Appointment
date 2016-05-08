/**
 * 模块的数据模型
 */

Ext.define('app.module.model.ModelModule', {
			extend : 'Ext.app.ViewModel',
			data : {
				//主页面view 是否显示west
				module_isWest:false,
				//主页面view 是否显示north
				module_isNorth:false,
				//新增
				tn_isToolbarAdd:false,
				//删除
				tn_isToolbarDel:false,
				//保存
				tn_isToolbarSave:false,
				//查询
				tn_isToolbarSearch:false,
				//新建button
				tn_otherNorthButton:[],
				
				//load方法
				tw_treeload:'',
				//导航树菜单
				tw_treetitle:'功能导航树',
				//是否显示根结节
				tw_treerootvisible:false,
				//tree model
				tw_treefields:[{
						name : 'id',
						type : 'string'
					}, {
						name : 'text',
						type : 'string'
					}, {
						name : 'leaf',
						type : 'boolean'
					}],
				
				
				// gridcenter标题
				tc_title : '',
				// 选中的记录的names显示在title上
				tc_selectedNames : '', 
				//是否显示gridpanel north 按钮
				tc_isNorthButton:false,
				//是否可以复制grid表格数据
				tc_istextSelection:true,
				//是否自动加载
				tc_isAutoLoad:false,
				//是否允许彻底删除数据
				tc_isDeleteData:false,
				//默认grid为单元格选择模式
				tc_gridseltype:'cellmodel',
				//grid是否可多选 MULTI/SINGLE/SIMPLE
				tc_gridselmodel:'MULTI',
				//grid点击进入编辑状态
				tc_gridclickedit:1,
				//grid表格自适应
				tc_isgridforcefit:true,
				// model
				tc_fields : [],
				// columns
				tc_gridSchemes : [],
				//需要检查的属性
				tc_checkFields:[{
					test : 'name',
					name : '名称'
				}]
			},constructor : function() {  
			    var me = this;  
			   this.callParent(arguments);  
			   if(!isEmpty(detailRole))
			   	  Ext.apply(me.data, Ext.decode(detailRole, true));  
			},
				// 根据字段id,找到字段相应的定义
				getFieldDefine : function(fieldId) {
					var result = null;
					Ext.Array.each(this.data.tf_fields, function(field) {
								if (field.tf_fieldId == fieldId) {
									result = field;
									return false;
								}
							});
					return result;
				},
				getSearchForm : function() {
					var data = [];
					return data;
				},initGridNorthButton :function(vm){
				}
		})