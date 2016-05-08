/**
 * ClassName TreeFilter
 * 
 */
 Ext.define('app.ux.TreeFilter',{
    filterByText:function(text){
    	this.filterBy(text, 'text');
    },
    filterBy: function(text, by) {  
    	this.clearFilter();
    	var view;
     if(this.xtype=='mainmenutree'){
     	 view = this.getView(); 
    	var me = this;
    	//save the finded id
    	var nodesAndParents = [];  
    	//cascade search
    	this.getRootNode().cascadeBy(function(tree, view){  
    		var currNode = this; 
    		 if(currNode && currNode.data[by] && currNode.data[by].toString().toLowerCase().indexOf(text.toLowerCase()) > -1) {
    		 	me.expandPath(currNode.getPath()); 
    		 	//Matches only to  the root
    		 	if(!currNode.parentNode){
    		 		nodesAndParents.push(currNode.id);
    		 	}
    		 	while(currNode.parentNode) {  
    		 		nodesAndParents.push(currNode.id);
    		 		currNode = currNode.parentNode;
    		 		//next is the root 
    		 		if(!currNode.parentNode){
    		 			nodesAndParents.push(currNode.id);
    		 	    }
    		 	  } 
    		 	} 
    	},null,[me, view]);  
    	//Hide the not need data
    	 this.getRootNode().cascadeBy(function(tree, view){           
    	 	var uiNode = view.getNodeByRecord(this);         
    	 	if(uiNode && !Ext.Array.contains(nodesAndParents, this.id)) {  
    	 		Ext.get(uiNode).setDisplayed('none');            
    	 		}         
    	 	}, null, [me, view]);  
      } 	
    	 if(this.xtype=='mainmenuaccordion'){
    	 	if(text !=''){
    	 		 var buttons = this.query("button");
    	 		 var blag = false;
    	 		 if(buttons.length >0){
    	 		 	for(var i in buttons){
    	 		 		var button = buttons[i];
    	 		 		if(button.itemId.indexOf(text) >= 0){
    	 		 		    blag = true;
    	 		 		    break;
    	 		 		}
    	 		 	}
    	 		 }
    	 		 if(blag){
    	 		 	  var item = this.items.items;
    	 		 	  for (var j in item) {
	    	 			var content = item[j];
	    	 			var buttons = content.query("button");
	    	 			 var temp = false;
	    	 			if(buttons.length >0){
		    	 		 	for(var k in buttons){
		    	 		 		var button = buttons[k];
		    	 		 		if(button.itemId.indexOf(text) >=0){
		    	 		 			temp = true;
		    	 		 			break ;
		    	 		 		}
		    	 		 		    
		    	 		 	}
	    	 			}
	    	 			if(temp)
	    	 			   content.setCollapsed(false)
	    	 			else
	    	 			   content.setCollapsed(true)
	    	 		}
    	 		 }
    	 	}
    	 }
    },
    //clear the filter
     clearFilter: function() { 
     	var view =this;
     	if(this.xtype=='mainmenutree'){
     	   view = this.getView(); 
	       this.getRootNode().cascadeBy(function(tree, view){ 
	        	var uiNode = view.getNodeByRecord(this);           
	        	if(uiNode) {             
	        		Ext.get(uiNode).setDisplayed('table-row');       
	        		}      
	        		},null,[this, view]);   
	      }
      }
 })