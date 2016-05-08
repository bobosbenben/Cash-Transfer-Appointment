   /**
	 * 启用提示
	 * 
	 */					
	Ext.QuickTips.init();
	/**
	 * 设置字体
	 * 
	 */			
	Ext.setGlyphFontFamily('FontAwesome'); 
	/**
	 * 设置动态加载
	 * 
	 */			
	Ext.Loader.setConfig({
		enabled : true,
		paths : {// 设置路径
		'Ext.ux' : 'app/ux'   //动态加载，类名前缀+包名，相当于java中的导入包申明
		}
	}); 
Ext.application({  
    name: 'app',
    extend: 'app.Application',
    autoCreateViewport: 'app.main.view.Main',
    launch : function() {
		      cacheData = Ext.create('app.module.model.CacheDataModule', {
		                       });
		                     cacheData.set('ListCompanysCache',listCompanys);
		                     cacheData.set('ListHsCompanysAndJTCache',listCompanysHsAndJT);
		                     ///*cacheData.set('listSellerCache',listSellers);
		                     //cacheData.set('ListHsCompanysNoJTCache',listCompanysHsNoJT);
                             //
		                     //cacheData.set('ListCompanysIdCache',listCompanysId);
		                     //cacheData.set('TreeCompnayCacheForBj',TreeCopmanyDataForBj);
		                     // cacheData.set('TreeCompnayCache',TreeCopmanyData);
		                     //cacheData.set('ListMerchandiseCache',lisetMerchandises);
		                     //cacheData.set('ListBrandsCache',listBrands);
		                     //cacheData.set('ListMTypeCache',listMType);
		                     //cacheData.set('TreeMerchandiseData',TreeMerchandiseData);
		                     //cacheData.set('accountTypeCache',accountTypeCache);
		                     //cacheData.set('ListClienteleCache',listClienteles);
		                     //cacheData.set('ListSuppliersCache',listSuppliers);
		                     //cacheData.set('ListWarehousesCache',listWarehouses);
		                     //cacheData.set('NullData',{ value : '<s:property value=""/>' , name : '<s:property value=""/>'});*/
		                     
			}
});