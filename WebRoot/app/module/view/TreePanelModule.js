/**
 * west 菜单树模型
 */
Ext.define('app.module.view.TreePanelModule', {
			extend : 'Ext.tree.Panel',
			enableDD : false,
			width : 200,
			border : 0,
			split : true,
			loadtreeurl:null,
			collapseMode : "mini",
			collapsible : true,
			split:true,
			glyph : 0xf038,
			autoScroll : true,
			initComponent : function() {
				var me = this,vm = this.getViewModel();
				//标题
				this.title =  vm.data['tw_treetitle'];
				//隐藏根结点
				this.rootVisible=vm.data['tw_treerootvisible'];
				//store
				this.store = Ext.create('app.module.store.TreeStoreModule',{
				 	fields:vm.data['tw_treefields'],
				 	proxy:{
				 		url : (isEmpty(this.loadtreeurl) ? actionUrl : this.loadtreeurl)+'/'+vm.data['tw_treeload'],
				 		listeners:{
			                exception:function( theproxy, response, operation, options ){
			                	var ret = response.responseText;
			                	if(ret.indexOf("html") >=0)
			                	   Ext.Msg.alert("错误:","无权限获取相应信息,请联系系统管理员");
			                	 else
			                	   Ext.Msg.alert("错误:", Ext.JSON.decode(ret).msg);
			                    return false;
			                   }
			                }
				 	}
				 	
			 	 });
			 	 this.listeners= {  
		        itemclick: function(obj, record, item, index, e, eOpts){  
		        	var view = this.up('modulecontainer');
		        	 view.getController().onNavigationTreeClick(record,view,e);  
			        }  
			    }
			 	  me.callParent();
			}
		    
		})