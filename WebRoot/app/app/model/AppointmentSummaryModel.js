Ext.define('app.app.model.AppointmentSummaryModel', {
    extend : 'app.module.model.ModelModule',
    alias : 'viewmodel.appsummarymodel',
    constructor : function() {
        var me = this;
        this.callParent(arguments);
        var datas = {
            module_isNorth:true,


            tn_searchfields : [
            //    Ext.create('app.module.view.CacheComboModule', {
            //    name : "gsdm",
            //    fieldLabel : "公司",
            //    dataname:'ListHsCompanysAndJTCache'
            //}),Ext.create('app.module.view.LocalComboModule', {
            //    fieldLabel : '辅助类型',
            //    name : 'dataType',
            //    forceSelection:true,
            //    datas :listDataType
            //}),Ext.create('app.module.view.LocalComboModule',{
            //    name : "status",
            //    fieldLabel : "启用标志",
            //    datas:[
            //        {"value":"true", "name":"启用"},
            //        {"value":"false", "name":"不启用"}
            //    ],
            //    value:'true'
            //})
                Ext.create('app.module.view.DateSelectModule',{
                    fieldLabel: "选择日期",
                    name:'date'
                }),
                //Ext.create('app.ux.ButtonTransparent',{
                //    text:'<font color="black">查询</font>',
                //    handler: function() {
                //        //alert('You clicked the button!');
                //    }
                //}),
                Ext.create('Ext.panel.Title',{
                    id:'zhanweilabel',
                    text: '<font style="color:white;">_____________________</font>'//占位的作用
                }),Ext.create('Ext.form.field.Text',{
                    fieldLabel: "当日总金额",
                    value: '0',
                    id: 'totalNumber'
                })
            ],

            /************gridcenter****************/
            tc_title:'选定日期下的预约明细',
            tc_isgridforcefit:false,
            tc_isDeleteData:true,
            tc_gridseltype:'rowmodel',
            tc_checkFields:[
            //    {
            //    test : 'dataType',
            //    name : '数据类型'
            //},{
            //    test : 'data',
            //    name : '数据内容'
            //},{
            //    test : 'gsDm',
            //    name : '公司'
            //}
            ],
            tc_fields: [
                {name: 'name', type : 'string'},
                {name: 'number',type: 'string'},
                {name: 'nodeNumber',type: 'string'},
                {name: 'way',type: 'string'},
                {name: 'reserve',type: 'string'}, //提交人
                {name: 'account',type: 'string'}, //账号
                {name: 'remark',type: 'string'},   //备注
                {name: 'date',type:'string'},
                {name: 'gsDm',type:'string'},
                {name: 'lrrq',type:'string'}
            ],
            tc_gridSchemes:[{
                xtype : 'rownumberer'
            }, {
                header : "账户名称",
                width : 120,
                dataIndex : 'name'
            }, {
                header : "金额",
                width : 150,
                dataIndex : 'number',
                align : 'left',
                field : {
                    xtype : 'textfield'
                }
            }, {
                header : "网点号",
                width : 150,
                dataIndex : 'nodeNumber',
                align : 'left',
                field : {
                    xtype : 'textfield'
                }
            },
                {
                    header : "途径",
                    width : 100,
                    dataIndex : 'way',
                    align : 'center',
                    //field : {
                    //    xtype : 'textfield'
                    //}

                }, {
                    header : "提交人",
                    width : 200,
                    dataIndex : 'reserve',
                    //field : {
                    //    xtype : 'textfield'
                    //}
                },
                {
                    header: "账号",
                    width: 200,
                    dataIndex: 'account',
                    field: {
                        xtype: 'textfield'
                    }
                },
                {
                    header: "备注",
                    width: 200,
                    dataIndex: 'remark',
                    field: {
                        xtype: 'textfield'
                    }
                },
                {
                    header: "日期",
                    width: 200,
                    dataIndex: 'date',
                    field: {
                        xtype: 'textfield'
                    }
                },
                {
                    header: "公司代码",
                    width: 200,
                    dataIndex: 'gsDm',
                    hidden:true
                },
                {
                    header: "录入日期",
                    width: 200,
                    dataIndex: 'lrrq',
                    hidden: true
                }
            ]

            /**********other*************/
        };
        Ext.apply(me.data, datas);
    }
})





























/**
 * Created by syb on 2016/2/1.
 */