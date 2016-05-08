/**
 * center grid层
 */
Ext.define('app.module.view.GridCenter', {
			extend : 'Ext.grid.Panel',
			alias : 'widget.modulecenter',
			name:'modulegridcenter',
			bind : {
				title : '{tc_selectedNames} {tc_title} ' 
				// 加上表格线
				//columnLines : '{grid_columnLines}',
			},
			//selModel: { selType: 'checkboxmodel' },   //选择框
		   initComponent : function() {
		   
				var me = this, vm = this.up('modulecontainer').getViewModel();
				
				 this.store = Ext.create('app.module.store.DataStoreModule',{
			 	 	/*此方式时第一次获取会出现异常
			 	 	 * model:Ext.create('Ext.data.Model',{
			 	 	      fields : vm.get('tc_fields')
			 	 	})*/
				 	fields:vm.get('tc_fields'),
				 	autoLoad:vm.get('tc_isAutoLoad'),
					id: 'datastoremodule'
			 	 });
			 	  //配置列
	 	         me.columns =  vm.get('tc_gridSchemes');
	 	          //配置选择模式
	 	         me.selModel = {
	 	               selType : vm.get('tc_gridseltype'),
	 	               mode  : vm.get('tc_gridselmodel')
	 	         };
	 	         //配置是否自适应
	 	         forceFit:vm.get('tc_isgridforcefit'),
	 	         //配置点击模式
			     me.plugins =[{
							ptype : 'cellediting',
							clicksToEdit :  vm.get('tc_gridclickedit')
						}];
				//grid表格内容复制
				me.viewConfig={
				    enableTextSelection: '{tc_istextSelection}'
			   };		
				 //配置分页
			 	this.dockedItems = [{
							xtype : 'pagingtoolbar', // grid数据分页
							store : this.store,
							displayInfo : true,
							prependButtons : true,
							dock : 'bottom'
						   }
						];
				//配置north button
				 if(vm.get('tc_isNorthButton'))
					 vm.initGridNorthButton(vm,me.dockedItems);
			   
				 me.callParent();
			 	}
			
		})