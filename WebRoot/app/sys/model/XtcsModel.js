/**
 * Created by syb on 2016/6/27.
 */
Ext.define('app.sys.model.XtcsModel', {
    extend : 'app.module.model.ModelModule',
    alias : 'viewmodel.xtcsmodel',
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
            //    //datas :listDataType
            //}),Ext.create('app.module.view.LocalComboModule',{
            //    name : "status",
            //    fieldLabel : "启用标志",
            //    datas:[
            //        {"value":"true", "name":"启用"},
            //        {"value":"false", "name":"不启用"}
            //    ],
            //    value:'true'
            //})
            ],

            /************gridcenter****************/
            tc_title:'系统参数设置',
            tc_isgridforcefit:false,
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
                //{name: 'id', type : 'int'},
                //{name: 'gsDm',type: 'string'},
                //{name: 'gsMc',type: 'string'},
                //{name: 'dataType',type: 'string'},
                //{name: 'data',type: 'string'},
                //{name: 'status',type: 'boolean'},
                //{name: 'bz',type: 'string'}
                {name: 'timeLimit',type:'string'},
                {name: 'switchLimit',type: 'boolean'}
            ],
            tc_gridSchemes:[{
                xtype : 'rownumberer'
            }, {
                header : "截止时间",
                width : 150,
                dataIndex : 'timeLimit',
                align : 'center',
                field : {
                    xtype : 'textfield'
                }

            },{
                header : "开启/关闭时间限制",
                width : 200,
                dataIndex : 'switchLimit',
                align : 'center',
                xtype: 'checkcolumn'
            }
            //    {
            //    header : "公司",
            //    width : 120,
            //    dataIndex : 'gsMc'
            //}, {
            //    header : "辅助类型",
            //    width : 150,
            //    dataIndex : 'dataType',
            //    align : 'left',
            //    field : {
            //        xtype : 'textfield'
            //    }
            //}, {
            //    header : "数据内容",
            //    width : 300,
            //    dataIndex : 'data',
            //    align : 'left',
            //    field : {
            //        xtype : 'textfield'
            //    }
            //},
            //
            //    {
            //        header : "是否启用",
            //        width : 100,
            //        dataIndex : 'status',
            //        align : 'center',
            //        xtype: 'checkcolumn'
            //
            //    }, {
            //        header : "备注",
            //        width : 200,
            //        dataIndex : 'bz',
            //        field : {
            //            xtype : 'textfield'
            //        }
            //    }
            ]

            /**********other*************/
        };
        Ext.apply(me.data, datas);
    }
})
