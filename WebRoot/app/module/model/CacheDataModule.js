/**
 * ClassName :定义的各种基类
 * 
 */
 
Ext.define('app.module.model.CacheDataModule',{
     data : {},
     push : function(lx, value) {
		if (isEmpty(this.data[lx]))
			this.data[lx] = [];
		this.data[lx].push(value);
	},
	set : function(lx, value) {
		this.data[lx] = value;
	},
	setJson : function(lx, value) {
		this.data[lx] = eval("(" + value + ")");
	},
	get : function(lx) {
		return this.data[lx];
	}
})