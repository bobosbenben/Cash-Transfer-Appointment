/**
 * ClassName ComboBoxUtil公共类
 * 
 */
 Ext.define('app.module.view.CacheComboModule',{
    extend    : 'Ext.form.field.ComboBox',
    dataname  : null,
    displayField : 'name',
    valueField : 'value',
    queryMode : 'local',
    initComponent : function() {
		var me = this;
		Ext.applyIf(me, {
              store : Ext.create('Ext.data.Store', {
				 fields : [{
				 name : 'name',
				 type : 'string'
				 }, {
				  name : 'value',
				  type : 'string'
				  }],
				 data : getDdlbCache(this.dataname)
				}) 
		})
		me.callParent(arguments);
	},
	listeners:{
	    beforequery :function( e, obj ){
									var combo = e.combo;
									if(!e.forceAll){
						                var value = e.query;
						                combo.store.filterBy(function(record,id){
						                var text = record.get(combo.displayField);
						                return (text.indexOf(value)!=-1);
						                });
						                combo.expand();
						                return false;
						                }
								}
	}
 })