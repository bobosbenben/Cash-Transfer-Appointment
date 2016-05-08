Ext.define('app.main.view.menu.MainMenuSearchField', {
			extend : 'Ext.ux.form.SearchField',
			alias : 'widget.mainmenusearchfield',
			margin:'0 0 0 0',
			onSearchClick : function(){
		        var me = this,
		            value = me.getValue();
		        if (value.length > 0) {
		        	me.activeFilter = new Ext.util.Filter({
			                property: me.paramName,
			                value: value
			            });
		             if (me.store && me.store.isStore) {
			             me.store.getFilters().add(me.activeFilter);
			        };
			        var view = this.up('app-main');
			        var tree = view.down('mainmenutree');
			        if(!tree)
			           tree = view.down('mainmenuaccordion');
			        tree.filterByText(value);
		            me.getTrigger('clear').show();
		            me.updateLayout();
		        }
		    },
		    onClearClick : function(){
		        var me = this,
		            activeFilter = me.activeFilter;
		        if (activeFilter) {
		            me.setValue('');
		            if (me.store && me.store.isStore) {
		            me.store.getFilters().remove(activeFilter);
		            };
		            me.activeFilter = null;
		            var view = this.up('app-main');
			       var tree = view.down('mainmenutree');
			        if(!tree)
			           tree = view.down('mainmenuaccordion');
		             tree.filterByText('');
		            me.getTrigger('clear').hide();
		            me.updateLayout();
		        }
		    }
		})