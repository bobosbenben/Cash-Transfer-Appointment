Ext.define('app.app.controller.CashSummaryController', {
    extend : 'app.module.controller.ControllerModule',
    alias : 'controller.cashsummarycontroller',
    initComponent : function() {
        this.callParent();
    },
    //设置增加一条现金预约记录
    onBtnAdd : function(btn, e) {
        var view =  this.getView();
        //Ext.create('app.basis.view.region.HRAddWinView',{
        Ext.create('app.app.view.MakeCashAppointmentWindow',{
            viewModel : view.getViewModel(),
            controller : view.getController()
        }).show();
        Ext.getBody().mask();
        //Ext.Msg.alert("tishi","this is a message");
    },
    onHRAddViewCancle:function(btn ,e){
        var win = btn.ownerCt.ownerCt.ownerCt;
        win.close();
        Ext.getBody().unmask();
    },
    onHRAddViewSave:function(btn,e){
        var formpanel =  btn.ownerCt.ownerCt,
            win = formpanel.ownerCt,
            form = formpanel.getForm(),
            relationlogin = form.findField('relationLogin'),
            password = form.findField('loginpwd');
        if(relationlogin.getValue()){
            if(isEmpty(password.getValue())){
                Ext.Msg.alert('小提示','关联登陆时登陆密码为必填项');
                return;
            }
        }
        if (form.isValid()) {
            form.submit({
                url : actionUrl+"/updatewin",
                waitMsg : '信息保存中....',
                success : function(form1, action) {
                    Ext.Msg.confirm(action.result.Msg,
                        '是否继续添加资料', function(btn) {
                            if (btn == 'no') {
                                win.close();
                                Ext.getBody().unmask();
                            }else
                                form.reset();
                        }, this)

                },
                failure : function(form1, action) {
                    Ext.Msg.alert('保存失败', '异常原因:'+action.result.Msg);
                }

            });
        }else{
            Ext.Msg.alert('小提示','有必填项目没有填写哦');
        }
    },//设置增加记录结束

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

