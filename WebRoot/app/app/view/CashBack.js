Ext.define('app.app.view.CashBack', {
    extend : 'app.module.view.GridBillModule',
    xtype : 'cashback',
    requires : ['app.app.controller.CashBackController','app.app.model.CashBackModel'],
    uses:['app.ux.ButtonTransparent'],
    controller : 'cashbackcontroller',
    viewModel : {
        type : 'cashbackmodel'
    }

});