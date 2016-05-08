/**
 * ClassName treestore module
 */
Ext.define('app.module.store.TreeStoreModule', {
			extend : 'Ext.data.TreeStore',
			actions : '',
			models : '',
			root : {
				expanded : true,
				text : "总节点"
			},
			proxy : {
				type : 'ajax',
				url : '',
				reader : {
					type : 'json'
				},
				async : false,
				writer : {
					type : 'json'
				},
				listeners : {
					exception : function(theproxy, response, operation, options) {
						var resText = response.responseText;
						resText = Ext.JSON.decode(resText);
						Ext.Msg.alert("错误:", resText.msg);
						return false;
					}
				}
			}

		})