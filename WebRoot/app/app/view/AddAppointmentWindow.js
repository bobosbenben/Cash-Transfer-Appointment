/**
 * Created by syb on 2016/5/16.
 */
Ext.define('app.app.view.AddAppointmentWindow', {
    extend : 'Ext.window.Window',
    xtype : 'add-window',
    reference: 'window',
    //controller: 'cashsummarycontroller',
    requires:['app.app.view.Appointment'],
    //requires : ['app.app.controller.CashSummaryController','app.app.model.CashSummaryModel'],
    //uses:['app.ux.ButtonTransparent'],
    modal: true,
    title: "新增大额预约",
    layout: 'fit',
    //width: 330,
    //height: 400,
    resizable: false,
    defaultButton: 1,//默认为'确定'按钮
    items:[
        //{
        //    xtype:'form',
        //    reference: 'form',
        //    bodyPadding: '10 1 10 1',//上、右、下、左
        //    border: false,
        //    //layout:{
        //    //    type:'vbox',
        //    //    align: 'stretch'
        //    //},
        //    layout:'absolute',
        //    items:[
        //        {
        //            anchor: '100%',
        //            //margin: '10 10 10 200',
        //            //align: 'center',
        //            x: 10,
        //            y: 10,
        //            xtype: "container",
        //            layout: "hbox",
        //            items: [
        //                {
        //                    xtype: "numberfield",
        //                    name: "number",
        //                    fieldLabel: "预约金额",
        //                    allowBlank: false,
        //                    listeners: {
        //                        specialkey: function(field, e){
        //                            if (e.getKey() == Ext.EventObject.ENTER) {
        //                                Ext.getCmp('date').focus(false,100);
        //                            }
        //                        }
        //                    }
        //                }
        //            ]
        //        },
        //        {
        //            xtype: "container",
        //            layout: "hbox",
        //            anchor: '100%',
        //            x: 10,
        //            y: 60,
        //            items: [
        //                {
        //                    xtype: "datefield",
        //                    name: "date",
        //                    fieldLabel: "提款日期",
        //                    format:"Y年m月d日",
        //                    //vtype: "age",
        //                    emptyText:"不需非常精确",
        //                    id: "date",
        //                    listeners: {
        //                        specialkey: function(field, e){
        //                            if (e.getKey() == Ext.EventObject.ENTER) {
        //                                Ext.getCmp('nodeNumber').focus(false,100);
        //                            }
        //                        }
        //                    }
        //                }]
        //        },
        //        {
        //            xtype: "container",
        //            layout: "hbox",
        //            anchor: '100%',
        //            x: 10,
        //            y: 110,
        //            items: [
        //                {
        //                    xtype: "combo",
        //                    name: "nodeNumber",
        //                    fieldLabel: "网点名称",
        //                    allowBlank: false,
        //                    id: "nodeNumber",
        //                    store: new Ext.data.Store({
        //                        fields: ['name','nodeNumber'],
        //                        proxy:{
        //                            type: 'ajax',
        //                            //url: nodesUrl,
        //                            url: 'app/getNodes',
        //                            reader:{
        //                                type: 'json',
        //                                root: 'data'
        //                            }
        //                        },
        //                        //autoLoad:true
        //                    }),
        //                    displayField: 'name',
        //                    valueField: 'nodeNumber',
        //                    mode: 'local',
        //                    emptyText:'请选择支行',
        //                    listeners: {
        //                        specialkey: function(field, e){
        //                            if (e.getKey() == Ext.EventObject.ENTER) {
        //                                Ext.getCmp('staff').focus(false,100);
        //                            }
        //                        }
        //                    }
        //                }]
        //        },
        //
        //        {
        //            xtype: "container",
        //            layout: "hbox",
        //            anchor: '100%',
        //            x: 10,
        //            y: 160,
        //            items: [
        //                {
        //                    xtype: "textfield",
        //                    name: "staff",
        //                    fieldLabel: "柜员名称",
        //                    allowBlank: true,
        //                    emptyText: "做出此次预约的网点柜员名称",
        //                    id: "staff",
        //                    listeners: {
        //                        specialkey: function(field, e){
        //                            if (e.getKey() == Ext.EventObject.ENTER) {
        //                                Ext.getCmp('remark').focus(false,100);
        //                            }
        //                        }
        //                    }
        //                }]
        //        },
        //        {
        //            xtype: "textareafield",
        //            name: "remark",
        //            fieldLabel: "备&nbsp&nbsp注",
        //            anchor: '90%',
        //            x: 10,
        //            y: 210,
        //            height: 50,
        //            id: "remark",
        //            listeners: {
        //                specialkey: function(field, e){
        //                    if (e.getKey() == Ext.EventObject.ENTER) {
        //                        Ext.getCmp('save').focus(false,100);
        //                    }
        //                }
        //            }
        //        }
        //
        //
        //    ],
        //    buttons:[
        //        {
        //            text:'保存',
        //            handler:'OnSaveClick'
        //        }
        //
        //        //{
        //        //    text:'取消',
        //        //    handler:
        //        //        function(){
        //        //            this.up("window").close();
        //        //        }
        //        //        //'onCancelClick'
        //        //}
        //    ],
        //    listeners :{
        //        close:'onCloseForm'
        //    }
        //}
        {
            xtype: 'form',
            //height: 5,
            autoWidth: true,
            autoHeight: true,
            margin: 20,
            //title: "大额预约",
            //frame:true,
            bodyPadding : '10 50 10 1',//上、右、下、左
            collapsible: false,  //可折叠
            autoScroll: true,   //自动创建滚动条
            defaultType: 'textfield',
            defaults: {
                anchor: '100%',
            },
            fieldDefaults: {
                labelWidth: 80,
                labelAlign: "right",
                flex: 1,
                margin: 15
            },
            items: [
                {
                    xtype: "container",
                    layout: "hbox",
                    items: [
                        {
                            xtype: "textfield",
                            name: "name",
                            fieldLabel: "账户名称",
                            allowBlank: false,
                            listeners: {
                                specialkey: function(field, e){
                                    if (e.getKey() == Ext.EventObject.ENTER) {
                                        Ext.getCmp('account').focus(false,100);
                                    }
                                }
                            }
                        },
                        {
                            xtype: "textfield",
                            name: "account",
                            fieldLabel: "账&nbsp&nbsp号",
                            allowBlank: true,
                            id:"account",
                            listeners: {
                                specialkey: function(field, e){
                                    if (e.getKey() == Ext.EventObject.ENTER) {
                                        Ext.getCmp('number').focus(false,100);
                                    }
                                }
                            }
                        }
                        //{ xtype: "numberfield", name: "age", fieldLabel: "年龄", decimalPrecision: 0, vtype: "age" }
                    ]
                },
                {
                    xtype: "container",
                    layout: "hbox",
                    items: [
                        {
                            xtype: "numberfield",
                            name: "number",
                            fieldLabel: "金&nbsp&nbsp额",
                            decimalPrecision: 0,
                            //vtype: "age",
                            //emptyText:"不需非常精确",
                            id: "number",
                            listeners: {
                                specialkey: function(field, e){
                                    if (e.getKey() == Ext.EventObject.ENTER) {
                                        Ext.getCmp('phone').focus(false,100);
                                    }
                                }
                            }
                        },
                        {
                            xtype: "textfield",
                            name: "phone",
                            fieldLabel: "联系电话",
                            allowBlank: true,
                            emptyText: "客户财务人员的联系电话",
                            id: "phone",
                            listeners: {
                                specialkey: function(field, e){
                                    if (e.getKey() == Ext.EventObject.ENTER) {
                                        Ext.getCmp('date').focus(false,100);
                                    }
                                }
                            }
                        }
                        //{ xtype: "textfield", name: "phone", fieldLabel: "邮箱", allowBlank: false, emptyText: "Email地址", vtype: "email" }
                    ]
                },
                {
                    xtype: "container",
                    layout: "hbox",
                    items: [
                        {
                            xtype: "datefield",
                            name: "date",
                            format:"Y年m月d日",
                            fieldLabel: "支取日期",
                            allowBlank: false,
                            id:"date",
                            listeners: {
                                specialkey: function(field, e){
                                    if (e.getKey() == Ext.EventObject.ENTER) {
                                        Ext.getCmp('way').focus(false,100);
                                    }
                                }
                            }
                        },
                        {
                            xtype: "combo",
                            name: "way",
                            fieldLabel: "途&nbsp&nbsp径",
                            allowBlank: false,
                            id: "way",
                            store: new Ext.data.ArrayStore({
                                fields: ['value', 'text'],
                                data: [
                                    ['柜面转账', '柜面转账'],
                                    ['网银转账', '网银转账'],
                                    ['柜面取现', '柜面取现']
                                ]
                            }),
                            displayField: 'text',
                            valueField: 'value',
                            mode: 'local',
                            emptyText:'请选择途径',
                            listeners: {
                                specialkey: function(field, e){
                                    if (e.getKey() == Ext.EventObject.ENTER) {
                                        Ext.getCmp('nodeNumber').focus(false,100);
                                    }
                                }
                            }
                        }
                        //{ xtype: "numberfield", name: "age", fieldLabel: "年龄", decimalPrecision: 0, vtype: "age" }
                    ]
                },
                {
                    xtype: "container",
                    layout: "hbox",
                    items: [
                        {
                            xtype: "combo",
                            name: "nodeNumber",
                            fieldLabel: "预约网点",
                            allowBlank: false,
                            id: "nodeNumber",
                            store: new Ext.data.Store({
                                fields: ['name','nodeNumber'],
                                proxy:{
                                    type: 'ajax',
                                    //url: nodesUrl,
                                    url: 'app/getNodes',
                                    reader:{
                                        type: 'json',
                                        root: 'data'
                                    }
                                },
                                //autoLoad:true
                            }),
                            //store: new Ext.data.ArrayStore({
                            //    fields: ['value', 'text'],
                            //    data: [
                            //        ['78003', '营业部'],
                            //        ['78004', '中汇支行'],
                            //        ['78005', '达尔扈特支行'],
                            //        ['78006','兆丰支行'],
                            //        ['78007','中金支行'],
                            //        ['78009','银泰支行'],
                            //        ['78011','元丰支行'],
                            //        ['78013','新庙支行'],
                            //        ['78014','聚源支行'],
                            //        ['78016','台格支行'],
                            //        ['78018','伊金霍洛支行'],
                            //        ['78020','红庆河支行'],
                            //        ['78022','公尼召支行'],
                            //        ['78024','公尼召支行'],
                            //        ['78026','台吉召支行'],
                            //        ['78028','阿勒腾席热支行'],
                            //        ['78029','银通支行'],
                            //        ['78030','广元支行'],
                            //        ['78031','金桌支行'],
                            //        ['78033','纳林陶亥支行'],
                            //        ['78035','纳林希里支行'],
                            //        ['78037','红海子支行'],
                            //        ['78038','阿吉奈支行'],
                            //        ['78039','大通支行'],
                            //        ['78040','札萨克支行'],
                            //        ['78041','金地支行'],
                            //        ['78042','金税支行'],
                            //        ['78043','金茂支行'],
                            //        ['78044','北城支行'],
                            //        ['78045','康城支行'],
                            //        ['78046','益民支行'],
                            //        ['78047','朝阳支行'],
                            //        //['',''],
                            //    ]
                            //}),
                            displayField: 'name',
                            valueField: 'nodeNumber',
                            //mode: 'local',
                            emptyText:'请选择支行',
                            listeners: {
                                specialkey: function(field, e){
                                    if (e.getKey() == Ext.EventObject.ENTER) {
                                        Ext.getCmp('staff').focus(false,100);
                                    }
                                }
                            }
                        },
                        {
                            xtype: "textfield",
                            name: "staff",
                            fieldLabel: "柜员名称",
                            allowBlank: true,
                            emptyText: "做出此次预约的网点柜员名称",
                            id: "staff",
                            listeners: {
                                specialkey: function(field, e){
                                    if (e.getKey() == Ext.EventObject.ENTER) {
                                        Ext.getCmp('remark').focus(false,100);
                                    }
                                }
                            }
                        }
                    ]
                },
                {
                    xtype: "textareafield",
                    name: "remark",
                    fieldLabel: "备&nbsp&nbsp注",
                    height: 50,
                    id: "remark",
                    listeners: {
                        specialkey: function(field, e){
                            if (e.getKey() == Ext.EventObject.ENTER) {
                                Ext.getCmp('save').focus(false,100);
                            }
                        }
                    }
                }
            ],

            //buttonAlign:"center",
            buttons: [
                //{
                //    xtype: "button",
                //    text:"重置",
                //    handler: function(){
                //        var form = this.up('form').getForm();
                //        form.reset();
                //    }
                //},
                {
                    style:'margin-left:50px;',
                    xtype: "button",
                    text: "提交",
                    id: "save",
                    handler: function(){
                        var form = this.up('form').getForm();
                        var dialog = this.up('add-window');
                        if(form.isValid()){
                            form.submit({
                                url: contextPath+'/app/appointment',
                                method:'post',
                                success: function(form,action){
                                    //Ext.Msg.alert('成功', action.result.msg);
                                    Ext.Msg.alert('成功', '预约信息提交成功');
                                },
                                failure: function(form, action) {
                                    //Ext.Msg.alert('shibaile',action.result);
                                    Ext.Msg.alert('失败', action.result.Msg);
                                },
                                waitTitle:"请稍候",
                                waitMsg:"正在提交表单数据，请稍候"
                            });

                            setTimeout(
                                function(){
                                    dialog = Ext.destroy(dialog);
                                },
                                500);//如果不设置timeout会报错，因为窗口还在等待返回信息就被直接销毁了。
                        }
                        else{
                            Ext.Msg.alert('错误','请输入正确的大额预约信息');
                        }

                    },
                    listeners: {
                        specialkey: function(field, e){
                            if (e.getKey() == Ext.EventObject.ENTER) {
                                var form = this.up('form').getForm();
                                form.submit();
                            }
                        }
                    }
                }]

        }
    ],
    //buttons: [
    //    {
    //        text: "取消",
    //        handler: function () {
    //            this.up("window").close();
    //
    //        }
    //    },{
    //        text: '确定',
    //        handler: 'OnSaveClick'
    //        //    function(){
    //        //    Ext.Msg.alert("tishi","点击了保存");
    //        //}
    //    }
    //],
    autoShow: true,

    initComponent : function() {
        var me = this, vm = me.getViewModel();
        me.callParent(arguments);
    }
});
