/*******************************************************************************
 * ClassName : 公共表格类
 * 
 ******************************************************************************/
Ext.define('app.module.view.GridBillModule', {
			extend : 'Ext.container.Container',
			alias : 'widget.modulecontainer',
			uses : ['app.module.view.GridCenter'],
			layout : 'border',
			westModule : '',
			northForm : '',
			initComponent : function() {
				var vm = this.getViewModel();
				this.items = [{
							xtype : 'modulecenter', // 模块的grid显示区域
							region : 'center'

						}];
				if (vm.get('module_isNorth')) {
					//console.log('开始初始化north');
					this.initnorth(vm);
					this.items.push(this.northForm);
				};

				if (vm.get('module_isWest')) {
					this.initwest(vm);
					this.items.push(this.westModule);
				};

				this.callParent();
			},
			initwest : function(vm) {
				this.westModule = {};
			},
			initnorth : function(vm) {
				var tb = Ext.create('app.module.view.ToolButtonModule');
				//console.log('这里已经产生了一个ToolButton')
				if (!isEmpty(vm.get('tn_otherNorthButton')))
					tb.add(vm.get('tn_otherNorthButton'));

				var sform = Ext.create('app.module.view.SearchFormModule', {
							vm : vm
						});

				//var test=true;
				if(vm.get('module_isSerach')){
					this.northForm = Ext.create('Ext.form.Panel', {
								name:'northformpanel',
								region : 'north',
								//items : [tb, sform]
								items : [tb]
					});
				}
				else{
					this.northForm = Ext.create('Ext.form.Panel', {
						name:'northformpanel',
						region : 'north',
						items : [tb, sform]
					});
				}
			}
		})