/**
 * Created by syb on 2016/5/8.
 */
Ext.define('app.app.view.MakeCashAppointmentWindow', {
    extend : 'Ext.window.Window',
    xtype : 'add-window',
    //controller: 'cashsummarycontroller',
    //requires:['app.app.view.Cash'],
    //requires : ['app.app.controller.CashSummaryController','app.app.model.CashSummaryModel'],
    //uses:['app.ux.ButtonTransparent'],
    modal: true,
    title: "新增现金预约",
    layout: 'fit',
    width: 330,
    height: 400,
    resizable: false,
    defaultButton: 1,//默认为'确定'按钮
    items:[
        {
            xtype:'form',
            reference: 'form',
            bodyPadding: '10 1 10 1',//上、右、下、左
            border: false,
            //layout:{
            //    type:'vbox',
            //    align: 'stretch'
            //},
            layout:'absolute',
            items:[
                {
                    anchor: '100%',
                    //margin: '10 10 10 200',
                    //align: 'center',
                    x: 10,
                    y: 10,
                    xtype: "container",
                    layout: "hbox",
                    items: [
                        {
                            xtype: "numberfield",
                            name: "number",
                            fieldLabel: "预约金额",
                            allowBlank: false,
                            listeners: {
                                specialkey: function(field, e){
                                    if (e.getKey() == Ext.EventObject.ENTER) {
                                        Ext.getCmp('date').focus(false,100);
                                    }
                                }
                            }
                        }
                    ]
                },
                {
                    xtype: "container",
                    layout: "hbox",
                    anchor: '100%',
                    x: 10,
                    y: 60,
                    items: [
                        {
                            xtype: "datefield",
                            name: "date",
                            fieldLabel: "提款日期",
                            format:"Y年m月d日",
                            //vtype: "age",
                            emptyText:"不需非常精确",
                            id: "date",
                            listeners: {
                                specialkey: function(field, e){
                                    if (e.getKey() == Ext.EventObject.ENTER) {
                                        Ext.getCmp('nodeNumber').focus(false,100);
                                    }
                                }
                            }
                        }]
                },
                {
                    xtype: "container",
                    layout: "hbox",
                    anchor: '100%',
                    x: 10,
                    y: 110,
                    items: [
                        {
                            xtype: "combo",
                            name: "nodeNumber",
                            fieldLabel: "网点名称",
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
                            displayField: 'name',
                            valueField: 'nodeNumber',
                            mode: 'local',
                            emptyText:'请选择支行',
                            listeners: {
                                specialkey: function(field, e){
                                    if (e.getKey() == Ext.EventObject.ENTER) {
                                        Ext.getCmp('staff').focus(false,100);
                                    }
                                }
                            }
                        }]
                },

                {
                    xtype: "container",
                    layout: "hbox",
                    anchor: '100%',
                    x: 10,
                    y: 160,
                    items: [
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
                        }]
                },
                {
                    xtype: "textareafield",
                    name: "remark",
                    fieldLabel: "备&nbsp&nbsp注",
                    anchor: '90%',
                    x: 10,
                    y: 210,
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
            buttons:[
                {
                    text:'保存',
                    handler:'OnSaveClick'
                }

                //{
                //    text:'取消',
                //    handler:
                //        function(){
                //            this.up("window").close();
                //        }
                //        //'onCancelClick'
                //}
            ],
            listeners :{
                close:'onCloseForm'
            }
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
