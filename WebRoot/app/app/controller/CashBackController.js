Ext.define('app.app.controller.CashBackController', {
    extend : 'app.module.controller.ControllerModule',
    alias : 'controller.cashbackcontroller',
    requires : ['app.app.view.CashBackAppointmentWindow'],
    initComponent : function() {
        this.callParent();
    },
    //设置增加一条现金送款预约记录
    onBtnAdd : function(btn, e) {
        var view =  this.getView();

        this.dialog = view.add({
            xtype: 'back-window',
            session:true
            //viewModel : view.getViewModel(),
            //controller : view.getController()
        });
        this.dialog.show();

    },
    OnSaveClick: function(){
        //Ext.Msg.alert("提示","点击了保存按钮");
        //console.log('contextPath是： '+contextPath);
        var me = this,
        //dialog = me.dialog,
            form = this.lookupReference('form');

        if(!form.isValid()){
            Ext.Msg.alert("错误","请输入正确的现金送款信息");
        }
        else{
            form.submit({
                url: contextPath+'/app/cashback/make',
                //url:'en/app/cash/make',
                method:'post',
                success: function(form,action){
                    //Ext.Msg.alert('成功', action.result.msg);
                    Ext.Msg.alert('成功', '现金送款信息提交成功');
                },
                failure: function(form, action) {
                    Ext.Msg.alert('失败', action.result.msg);
                },
                waitTitle:"请稍候",
                waitMsg:"正在提交现金送款信息，请稍候"

            });

            setTimeout(
                function(){
                    me.dialog = Ext.destroy(me.dialog);
                },
                500);//如果不设置timeout会报错，因为窗口还在等待返回信息就被直接销毁了。
        }
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

