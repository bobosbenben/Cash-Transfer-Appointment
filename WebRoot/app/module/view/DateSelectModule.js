/**
 * ClassName DateSelectModule公共组件类
 *
 */
Ext.define('app.module.view.DateSelectModule',{
    extend  : 'Ext.form.field.Date',

    format: "Y-m-d",
    //fieldLabel: "选择日期",
    anchor: '100%',
    value: new Date()

})
