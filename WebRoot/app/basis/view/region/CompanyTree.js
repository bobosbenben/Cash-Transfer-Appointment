/**
 * west 菜单树
 */
Ext.define('app.basis.view.region.CompanyTree', {
			extend : 'Ext.tree.Panel',
			//id : 'companytreepanel',
			enableDD : false,
			title : '组织架构',
			width : 200,
			border : 0,
			split : true,
			collapseMode : "mini",
			collapsible : true,
			rootVisible:false,
			split:true,
			glyph : 0xf038,
			autoScroll : true,
			initComponent : function() {
				var me = this,vm = this.getViewModel();
				this.store = Ext.create('app.module.store.TreeStoreModule',{
				 	fields:vm.get('tw_treefields'),
				 	proxy:{
				 		url : actionUrl+'/'+vm.get('tw_treeload')
				 	}
			 	 });
			 	  me.callParent();
			},
		   listeners: {  
		        itemclick: function(obj, record, item, index, e, eOpts){  
		        	var view =  this.up('basis-company');
		           view.getController().onCompanytreeClick(record,e);  
		        }  
		    } 
		})