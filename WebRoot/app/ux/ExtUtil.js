/**
 * 禁用鼠标右键
 * 
 */
Ext.getDoc().on("contextmenu", function(e) {
	e.preventDefault();
		// e.stopEvent();firefox不支持些方法，故使用e.preventDefault();
	});
// 可以制作一个控件，来修改这二个属性，达到可以修改金额单位的目的
Ext.monetaryText = '万'; // 加在数字后面的金额单位
Ext.monetaryUnit = 10000;

// Ext.monetary = '亿';
// Ext.monetaryUnit = 10000*10000;

if (Ext.util && Ext.util.Format) {

	Ext.apply(Ext.util.Format, {

		// 金额字段
		monetaryRenderer : function(val, metaData, model, row, col, store,
				gridview) {
			if (val) {
				if (Ext.monetaryUnit && Ext.monetaryUnit != 1)
					val = val / Ext.monetaryUnit;
				// 正数用蓝色显示，负数用红色显示,必须css和返回的值分开来设置，否则不能autoSize()
				metaData.style = 'color:' + (val > 0 ? 'blue' : 'red') + ';'
				return Ext.util.Format.number(val, '0,000.00')
						+ Ext.monetaryText;
			} else
				return ''; // 如果为0,则不显示
		},

		// 日期
		dateRenderer : function(val, metaData, model, row, col, store, gridview) {
			metaData.style = 'color:#a40;';
			return Ext.util.Format.date(val, 'Y-m-d');
		},
		// boolean
		booleanRenderer : function(val, metaData, model, row, col, store,
				gridview) {
					if (val == "false" || !val)
				        return '<span style="color:red;"> </span>';
				    else
				        return '<span style="color:green;">√</span>';
			//metaData.style = 'color:#a40;';
			//return Ext.util.Format.date(val, 'Y-m-d');
		},

		// 整型变量
		floatRenderer : function(val, metaData, model, row, col, store,
				gridview) {
			metaData.style = 'color:' + (val > 0 ? 'blue' : 'red') + ';'
			return val == 0 ? '' : val;
					
		},

		// 整型变量
		intRenderer : function(val, metaData, model, row, col, store, gridview) {
			metaData.style = 'color:' + (val > 0 ? 'blue' : 'red') + ';';
			// ';float:right;'; 这个去掉了，不然行业编辑的时候位置不对
			return val == 0 ? '' : val;
		},

		// 百分比
		percentRenderer : function(v, metaData, model) {
			v = v * 100;
			var v1 = v > 100 ? 100 : v;
			v1 = v1 < 0 ? 0 : v1;
			var v2 = parseInt(v1 * 2.55).toString(16);
			if (v2.length == 1)
				v2 = '0' + v2;
			return Ext.String
					.format(
							'<div>'
									+ '<div style="float:left;border:1px solid #008000;height:15px;width:100%;">'
									+ '<div style="float:left;text-align:center;width:100%;color:blue;">{0}%</div>'
									+ '<div style="background: #FAB2{2};width:{1}%;height:13px;"></div>'
									+ '</div></div>', v, v1, v2);
		},

		// 对模块的namefields字段加粗显示
		nameFieldRenderer : function(val, metaData, model, row, col, store,
				gridview) {
			metaData.style = 'font-weight:bold;';
			return val;
		},
		sexRenderer : function(val, metaData, model, row, col, store,
				gridview) {
			if("F" == val)
			 return "女";
			else
			  return "男";
		}
	})
};
/**
 * 等待画面
 */
function showLoading() {
	Ext.MessageBox.show({
				title : '请稍后',
				closable : false,
				msg : '正在处理数据......',
				width : 150
			});
};
//结果判断
function processError(ret) {
	if (isEmpty(ret)) {
		Ext.Msg.alert('错误', '操作失败：服务器无返回结果！');
		return true;
	}
	if (ret.indexOf('systemerror') >= 0 || ret.indexOf('SYSTEMERROR') >= 0) {
		Ext.Msg.alert('错误', '系统超时，请重新登陆');
		return true;
	}
	if (ret.indexOf('error') >= 0 || ret.indexOf('ERROR') >= 0) {
		var ids = ret.split("&");
		Ext.Msg.alert('错误', '操作失败：' + ids[1]);
		return true;
	}
	if (ret.indexOf('alarm') >= 0 || ret.indexOf('ALARM') >= 0) {
		var ids = ret.split("&");
		Ext.Msg.alert('提示', '操作提示：' + ids[1] + "，可以继续");
	}
	return false;
};
function checkUpdateBefore(modified, data) {
	var re = true;
	for (var i = 0; i < modified.length; i++) {
		for (var j = 0; j < data.length; j++) {
			if (isEmpty(modified[i].get(data[j]['test']))) {
				Ext.Msg.alert("小提示", '第' + (i + 1) + '行' + data[j]['name']
								+ '不能为空!');
				re = false;
				return false;
			}
		}
	}
	return re;
};
/**
 * 组装修改的gird data为json数据
 * @param {} record
 * @return {}
 */
function getUpdateJson(record) {
	var jsonstr = "[";
	var k = 0;
	for (var i = 0, rlen = record.length; i < rlen; i++) {
		jsonstr += '{' + getFieldJson(record[i]) + " }";
		k++;
		if (k != record.length) {
			jsonstr += ','
		};
	}
	jsonstr += "]";

	return jsonstr;
}
function getFieldJson(model) {
	var jsonstr = "";
	var value;
	var temp = true;
	if (/^[a-zA-Z]/.test(model.data['id'])) {
		model.data['id'] = '-1';
	};
	for (var i = 0, rlen = model.fields.length; i < rlen; i++) {
		var item = model.fields.items[i];
		//处理id

		if (temp == true) {
			jsonstr += '"' + item['name'] + '" : "';
			temp = false;
		} else {
			jsonstr += ',"' + item['name'] + '" : "';
		}

		value = model.data[item['name']];
		if (value instanceof Date)
			jsonstr += formatGMTtoDate(value) + '" '
		else
			jsonstr += value + '" ';
	}
	return jsonstr;
};
/**
 * 将被选择的treepanel组装成json数据与后台交互
 * @param {} node
 * @return {}
 */
function getjsonForTree(node) {

	var jsonstr = "[";
	var k = 1;
	//不需要根结点,故从1开始
	for (var i = 1, nsize = node.length; i < nsize; i++) {
		jsonstr += '{ "id" : "' + node[i].data['id'] + '","name" : "'
				+ node[i].data['text'] + '"}';
		k++;

		if (k != nsize) {
			jsonstr += ','
		}
	}
	jsonstr += "]";

	return jsonstr;
};
/**
 * 获取north form表单里的查询条件
 * @param {} formpanel
 * @return {}
 */
function getParamsByForm(formpanel) {
	var baseParams = {};
	formpanel.items.each(function(v) {
				if ((v.xtype == 'combobox' || v.xtype == 'combo') && !isEmpty(v.getValue())) {
					baseParams[v.name] = v.getValue();
				};
				
				if (v.getXType == 'textfield' && !isEmpty(v.getRawValue())) {
					xtype[v.name] = v.getRawValue();
				};
				if (v.xtype == 'datefield' && !isEmpty(v.getRawValue())) {
					baseParams[v.name] = v.getRawValue();
				};
			})
	return baseParams;
};
/**
 * 获取缓存数据
 */
 function getDdlbCache (lx) {
 	
	var ddata = [];
	  var fdata = [];
	  fdata = getCache(lx);
	  for(var i = 0;i < fdata.length; i++){
	  ddata.push(Ext.JSON.decode(fdata[i]))
	  }
	return ddata;
};
var getCache = function(lx) {
	var r = getParentCache().get(lx);
    return r;
};
var getParentCache = function() {
	var d = parent.cacheData;
	return d;
};
/**
 * renderer company
 * @param {} value
 * @param {} cellmeta
 * @param {} record
 * @param {} rowIndex
 * @param {} columnIndex
 * @param {} store
 * @return {}
 */
function renderCompany (value, cellmeta, record, rowIndex, columnIndex, store) {
	  return publicSelect(value, 'ListCompanysCache',"value","name");
};
/**
 * select 通过类
 * @param {} value
 * @param {} lx
 * @param {} findvalue
 * @param {} returnvalue
 * @return {}
 */
function publicSelect (value, lx,findvalue,returnvalue) {
	var cdata = [];
	cdata = getDdlbCache(lx);
		if (!isEmpty(cdata)){
			for (var i = 0; i < cdata.length; i++) {
				
				if (cdata[i][findvalue] == value)
					return cdata[i][returnvalue];
			}
	}
	return value;

};
/**
 * 产生随机时间戳
 * @return {}
 */
function genRandom() {
    return "random=" + encode64((new Date()).getTime());
};
function Random() {
    return  encode64((new Date()).getTime());
}
/**
 * 将id换为value
 * @param {} listObj
 * @param {} obj
 * @param {} selectedobj
 * @return {String}
 */
function getCheckBocxByListNoId(listObj,obj,selectedobj){
	if(isEmpty(listObj))
	  return "";
	var re = "[";
	var array = selectedobj == null ? "" : eval("[" + selectedobj + "]");
	if(isEmpty(array)){
		for(var i=0,nrow = listObj.length;i<nrow;i++){
			var curdata = Ext.JSON.decode(listObj[i]);
			var k = curdata['value'];
			if(i==(nrow-1)){
				re = re + "{filedId:'"+k+"',boxLabel:'"+curdata['name']+"',name:'"+obj+"'}";
			}else{
				re = re + "{filedId:'"+k+"',boxLabel:'"+curdata['name']+"',name:'"+obj+"'},";
			}
	   }
	}else{
		for(var i=0,nrow = listObj.length;i<nrow;i++){
			var curdata = Ext.JSON.decode(listObj[i]);
			var k = curdata['value'];
			var name = curdata['name'];
			var temptrue = false;
			for(var j = 0,nr = array.length;j<nr;j++){
			    var temp = array[j];
			    if(temp == k)
			    	temptrue = true;
			}
			if(i==(nrow-1) ){
				re = re + "{filedId:'"+k+"',boxLabel:'"+curdata['name']+"',name:'"+obj+"',checked:"+temptrue+" }";
			}else{
				re = re + "{filedId:'"+k+"',boxLabel:'"+curdata['name']+"',name:'"+obj+"',checked: "+temptrue+" },";
			}
		}
	}
	return re+"]";
}