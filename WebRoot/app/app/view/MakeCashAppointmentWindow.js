/**
 * Created by syb on 2016/5/8.
 */
Ext.define('app.app.view.MakeCashAppointmentWindow', {
    extend : 'Ext.window.Window',
    xtype : 'add-window',
    //controller: 'cashsummarycontroller',
    //requires : ['app.app.controller.CashSummaryController','app.app.model.CashSummaryModel'],
    //uses:['app.ux.ButtonTransparent'],
    modal: true,
    title: "新增现金预约",
    layout: 'fit',
    width: 500,
    height: 300,
    defaultButton: 1,//默认为'确定'按钮
    buttons: [
        {
            text: "取消",
            handler: function () {
                this.up("window").close();

            }
        },{
            text: '确定',
            handler: 'OnSaveClick'
            //    function(){
            //    Ext.Msg.alert("tishi","点击了保存");
            //}
        }
    ],
    autoShow: true,

    initComponent : function() {
        var me = this, vm = me.getViewModel();
        me.callParent(arguments);
    }
});
