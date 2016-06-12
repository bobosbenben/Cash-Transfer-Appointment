/**
 * grid 查询条件
 * 
 */
Ext.define('app.module.view.SearchFormModule', {
      extend:'Ext.form.FieldSet',
      title: '查询条件',
      name:'northsearchformmodule',
      layout: 'column',
      collapsible: true,
      uses:['app.module.factory.WidgetFactory'],
      vm:'',
      defaults:{
      	margin: '0px 0px 5px 5px'
      },
      items:[
        //  {
        //   xtype:'textfield',
        //   fieldLabel : "模糊匹配",
        //   name : "anyField"
        //}
      ],
	 initComponent : function() {
	 	var data = this.vm.get('tn_searchfields');
	 	if(!isEmpty(data) && data.length >0){
	 	  for(var i in data){
	 	  	//var item;
	 	  	//if(data[i]['type'] ='localcombobox'){
	 	  	//	 item  =  new app.module.factory.WidgetFactory.getLocalComboboxBymodule(data[i])();
	 	  	//}
	 	  	
	 	  	this.items.splice(i,0,data[i]);
	 	  }
	 	}
	 	this.callParent();
	 }
	
})