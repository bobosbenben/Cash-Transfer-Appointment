Ext.define('app.module.store.DataStoreModule', {
			extend : 'Ext.data.Store',
			//model:null,
			proxy :
			{
						type : 'ajax',
						url : actionUrl+'/loaddata',
						reader : {
							type : 'json',
		                    root : 'record',
		                    totalProperty:'totalRecords'
						},
						writer : {
							type : 'json'
						},listeners:{
			                exception:function( theproxy, response, operation, options ){
			                    var resText  = response.responseText; 
			                    	resText = Ext.JSON.decode(resText);
			                    	Ext.Msg.alert("错误:",resText.msg);
			                    	return false;
			                }
			            }
			}
		})