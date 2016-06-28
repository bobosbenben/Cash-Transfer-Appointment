/**
 * Created by syb on 2016/6/27.
 */
Ext.define('app.sys.view.Xtcs', {
    extend : 'app.module.view.GridBillModule',
    xtype : 'sys-xtcs',
    requires : ['app.sys.controller.XtcsController','app.sys.model.XtcsModel'],
    uses:['app.ux.ButtonTransparent'],
    controller : 'xtcscontroller',
    viewModel : {
        type : 'xtcsmodel'
    }
});
