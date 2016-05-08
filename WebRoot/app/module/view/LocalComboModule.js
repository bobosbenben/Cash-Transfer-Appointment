/**
 * ClassName ComboBoxBillModule公共组件类
 * 
 */
 Ext.define('app.module.view.LocalComboModule',{
    extend    : 'Ext.form.field.ComboBox',
    datas  : null,
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
				 data : this.datas
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