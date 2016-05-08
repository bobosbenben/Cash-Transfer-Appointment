Ext.define('app.module.view.ToolButtonModule', {
			extend : 'Ext.toolbar.Toolbar',
			border:0,
			defaults : {
				xtype : 'buttontransparent'
			},
			items : [
				    {
						text : '新增',
						bind : {
							hidden : '{!tn_isToolbarAdd}'
						},
						glyph : 0xf055,
						handler:'onBtnAdd'
					},  {
						text : '删除',
						bind : {
							hidden : '{!tn_isToolbarDel}'
						},
						glyph : 0xf014,
						handler:'onBtnDel'
					}, {
						text : '修改/保存',
						bind : {
							hidden : '{!tn_isToolbarSave}'
						},
						glyph : 0xf0c7,
						handler:'onBtnSave'
					},{
						text : '查询',
						bind : {
							hidden : '{!tn_isToolbarSearch}'
						},
						glyph : 0xf002,
						handler:'onBtnSearch'
					}]

		})