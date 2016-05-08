Ext.define('app.app.controller.CashSummaryController', {
    extend : 'app.module.controller.ControllerModule',
    alias : 'controller.cashsummarycontroller',
    getDefaultRecord : function(view) {
        var formpanel = view.query("form[name='northformpanel']")[0],
            gridpanel = view.query("gridpanel[name='modulegridcenter']")[0],
            hsgs = formpanel.getForm().findField('gsdm'),
            dataType = formpanel.getForm().findField('dataType');
        if (isEmpty(hsgs.getValue())) {
            Ext.Msg.alert("小提示","请选择公司!");
            return;
        }
        var add = {
            gsDm: hsgs.getValue(), gsMc: hsgs.getRawValue(),dataType:dataType.getValue(),status : true
        };
        return add;
    }

});

