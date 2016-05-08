/**
 * 定义了一个背景透明的Button类，继承于Button
 */
Ext.define('app.ux.ButtonTransparent', {
			extend : 'Ext.button.Button', 
			alias : 'widget.buttontransparent',
			disableMouseOver : false,

			initComponent : function() {
				if (!this.listeners)
					this.listeners = {};

				Ext.apply(this.listeners, {
							mouseout : function() {
								this.setTransparent(document.getElementById(this.id));
							},
							mouseover : function(button) {
								var b = document.getElementById(this.id);
								if (!button.disableMouseOver) {
									b.style.backgroundImage = '';
									b.style.backgroundColor = '';
									b.style.borderColor = '';
								} else
									b.style.borderColor = '';
							},
							afterrender : function() {
								this.setTransparent(document.getElementById(this.id));
							}
						});
				this.callParent(arguments);
			},
			setTransparent : function(b) {
				b.style.backgroundImage = 'inherit';
				b.style.backgroundColor = 'inherit';
				b.style.borderColor = 'transparent';
			}
		});