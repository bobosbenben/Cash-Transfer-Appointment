Ext.define('app.app.view.CashSummary', {
    extend : 'app.module.view.GridBillModule',
    xtype : 'cashsummary',
    requires : ['app.app.controller.CashSummaryController','app.app.model.CashSummaryModel'],
    uses:['app.ux.ButtonTransparent'],
    controller : 'cashsummarycontroller',
    viewModel : {
        type : 'cashsummarymodel'
    }

});