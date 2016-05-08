/**
 * 
 * 一个显示、修改、新增的的窗口基类
 * 
 */
Ext.define('app.main.view.win.SysInfoWin', {
			extend : 'app.module.view.BaseWindow',
			alias : 'widget.sysinfowin',
			maximizable : false,
			closable : true,//取消关闭按钮
			info:'sysInfoWin',
			initComponent : function() {
				var vm = this.getViewModel();
				this.sys = vm.get('system');
				this.user= vm.get('user');
				this.service= vm.get('service');
				this.items=[{
				      xtype:'panel',
				      border:0,
				      items:[{
				        xtype: 'displayfield',
				        fieldLabel: '使用系统:',
				        value: this.sys.sys_name
				    }, {
				        xtype: 'displayfield',
				        fieldLabel: '系统版本号:',
				        value: this.sys.sys_version
				    }, {
				        xtype: 'displayfield',
				        fieldLabel: '授权用户:',
				        value: this.user.company
				    }, {
				        xtype: 'displayfield',
				        fieldLabel: '授权版本:',
				        value: this.sys.sys_edition
				    },{
				        xtype: 'displayfield',
				        fieldLabel: '服务公司:',
				        value: this.service.service_company
				    }, {
				        xtype: 'displayfield',
				        fieldLabel: '服务电话:',
				        value: this.service.service_tel
				    }, {
				        xtype: 'displayfield',
				        fieldLabel: '服务QQ:',
				        value: this.service.service_qq
				    }, {
				        xtype: 'displayfield',
				        fieldLabel: '服务邮箱:',
				        value: this.service.service_email
				    }, {
				        xtype: 'displayfield',
				        fieldLabel: '版权所有:',
				        value: "©"+this.service.service_copyright
				    }]
				
				}]
				this.callParent(arguments);
			}

		});