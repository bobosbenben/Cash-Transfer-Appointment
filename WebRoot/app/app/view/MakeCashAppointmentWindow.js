/**
 * Created by syb on 2016/5/8.
 */
Ext.define('app.app.view.MakeCashAppointmentWindow', {
    extend : 'Ext.Window',
    //xtype : 'cashsummary',
    //requires : ['app.app.controller.CashSummaryController','app.app.model.CashSummaryModel'],
    //uses:['app.ux.ButtonTransparent'],
    title: "新增现金预约",
    width: 500,
    height: 300,
    buttons: [
        {
            text: "确定",
            handler: function () {
                this.up("window").close();
            }
        }
    ],
    autoShow: true,

    initComponent : function() {
        var me = this, vm = me.getViewModel();
        me.callParent(arguments);
    }
});
