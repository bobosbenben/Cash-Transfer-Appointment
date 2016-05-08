Ext.onReady(function(){
    var form = Ext.create("Ext.form.FormPanel", {
        url:'',
        width: 700,
        height: 400,
        margin: 20,
        title: "大额预约",
        frame:true,
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
                    { xtype: "textfield", name: "name", fieldLabel: "账户名称", allowBlank: false },
                    { xtype: "textfield", name: "account", fieldLabel: "账&nbsp&nbsp号", allowBlank: false }
                    //{ xtype: "numberfield", name: "age", fieldLabel: "年龄", decimalPrecision: 0, vtype: "age" }
                ]
            },
            {
                xtype: "container",
                layout: "hbox",
                items: [
                    { xtype: "numberfield", name: "number", fieldLabel: "金&nbsp&nbsp额", decimalPrecision: 0, vtype: "age",emptyText:"不需非常精确" },
                    { xtype: "textfield", name: "phone", fieldLabel: "联系电话", allowBlank: false, emptyText: "客户财务人员的联系电话" },
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
                        fieldLabel: "支取日期",
                        allowBlank: false
                    },
                    {
                        xtype: "combo",
                        name: "way",
                        fieldLabel: "途&nbsp&nbsp径",
                        allowBlank: false,
                        store: new Ext.data.ArrayStore({
                            fields: ['value', 'text'],
                            data: [
                                ['guimianzhuanzhang', '柜面转账'],
                                ['wangyinzhuanzhang', '网银转账'],
                                ['guimianquxian', '柜面取现']
                            ]
                        }),
                        displayField: 'text',
                        valueField: 'value',
                        mode: 'local',
                        emptyText:'请选择途径'
                    }
                    //{ xtype: "numberfield", name: "age", fieldLabel: "年龄", decimalPrecision: 0, vtype: "age" }
                ]
            },
            {
                xtype: "textareafield",
                name: "remark",
                fieldLabel: "备&nbsp&nbsp注",
                height: 50
            }
        ],
        buttonAlign:"center",
        buttons: [
            {
                xtype: "button",
                text:"重置",
                handler: function(){
                    var form = this.up('form').getForm();
                    form.reset();
                }
            },
            {
                style:'margin-left:50px;',
                xtype: "button",
                text: "保存",
                handler: function(){
                    var form = this.up('form').getForm();
                    if(form.isValid()){
                        form.submit({
                            success: function(form,action){
                                Ext.Msg.alert('成功', action.result.msg);
                            },
                            failure: function(form, action) {
                                Ext.Msg.alert('失败', action.result.msg);
                            },
                            waitTitle:"请稍候",
                            waitMsg:"正在提交表单数据，请稍候"
                        });
                    }
                }
            }]
    });


    form.render("divFormPanel");
});