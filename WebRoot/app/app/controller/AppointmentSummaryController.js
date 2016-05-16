Ext.define('app.app.controller.AppointmentSummaryController', {
    extend : 'app.module.controller.ControllerModule',
    alias : 'controller.appsummarycontroller',
    requires:['app.app.view.AddAppointmentWindow'],
    initComponent : function() {
        this.callParent();
    },
    //设置增加一条大额预约记录
    onBtnAdd : function(btn, e) {
        var view =  this.getView();

        this.dialog = view.add({
            xtype: 'add-window',
            session:true
            //viewModel : view.getViewModel(),
            //controller : view.getController()
        });
        this.dialog.show();

    },
    //getDefaultRecord : function(view) {
    //    var formpanel = view.query("form[name='northformpanel']")[0],
    //        gridpanel = view.query("gridpanel[name='modulegridcenter']")[0],
    //        hsgs = formpanel.getForm().findField('gsdm'),
    //        dataType = formpanel.getForm().findField('dataType');
    //    if (isEmpty(hsgs.getValue())) {
    //        Ext.Msg.alert("小提示","请选择公司!");
    //        return;
    //    }
    //    var add = {
    //        gsDm: hsgs.getValue(), gsMc: hsgs.getRawValue(),dataType:dataType.getValue(),status : true
    //    };
    //    return add;
    //}

});
