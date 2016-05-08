Ext.define('app.app.view.AppointmentSummary', {
    extend : 'app.module.view.GridBillModule',
    xtype : 'appsummary',
    requires : ['app.app.controller.AppointmentSummaryController','app.app.model.AppointmentSummaryModel'],
    uses:['app.ux.ButtonTransparent'],
    controller : 'appsummarycontroller',
    viewModel : {
        type : 'appsummarymodel'
    }

    //initComponent : function() {
    //    this.callParent();
    //
    //    var storemodule = Ext.ComponentQuery.query('#datastoremodule');
    //    if(storemodule){
    //        storemodule.on('load',function(){
    //            alert("shujujiazaiwancheng");
    //        });
    //    }
    //    else alert("muyou");
    //}
});
