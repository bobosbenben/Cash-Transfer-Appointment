/**
 * 
 * 一个显示、修改、新增的的窗口基类
 * 
 */
Ext.define('app.module.view.BaseWindow', {
			extend : 'Ext.window.Window',
			layout : 'fit',
			maximizable : true,
			modal:true,
			closeAction : 'hide',
			bodyStyle : 'padding : 2px 2px 0',
			shadowOffset : 30,
			info:'',//记录什么win
			draggable : false,
			closable : false,//取消关闭按钮
			autoScroll : true,
			initComponent : function() {
				this.maxHeight = document.body.clientHeight * 0.98;
				var me = this;
				this.winInfo = this.getViewModel().get(this.info);
				this.title = isEmpty(this.title) ? me.winInfo.win_title :this.title;
				this.glyph = me.winInfo.win_glyph;

				var w = me.winInfo.win_width;
				var h = me.winInfo.win_Height;
				// 高度为-1表示是自适应
				if (w == -1 && h == -1) {
					this.width = 600;
					this.height = 400;
					this.maximized = true;
				} else {
					if (w != -1)
						this.width = Math.min(w, document.body.clientWidth - 2);
					if (h != -1)
						this.height = Math.min(h, document.body.clientHeight - 2);
				};
				if (w == -1 && h != -1) { 
					this.width = document.body.clientWidth - 40;
				}
				
				this.callParent(arguments);
			}

		});