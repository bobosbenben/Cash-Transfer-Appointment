package en.sys.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import en.basisc.entity.LoginUser;
import en.common.copybean.ICopyBean;
import en.common.dto.DTO;
import en.common.frame.shiro.SessionUser;
import en.common.service.impl.GridServiceImpl;
import en.common.util.helper.DatetimeUtil;
import en.common.util.helper.JacksonsUtil;
import en.common.util.helper.ListUtil;
import en.common.util.helper.LongUtil;
import en.common.util.helper.ResultEntity;
import en.common.util.helper.StringUtil;
import en.sys.copybean.impl.RoleCopyBeanImpl;
import en.sys.dto.RoleDTO;
import en.sys.entity.Menus;
import en.sys.service.IRoleService;
@Service
public class RoleServiceImpl extends GridServiceImpl implements IRoleService {
	
	protected ICopyBean getCopyBean() {
		
		return new RoleCopyBeanImpl();
	}

	public String getRoleDialogTreeViewData(List<?> menuList,List<?> mymenuIdList) throws Exception {
			
		    StringBuffer sb = new StringBuffer();
			sb.append("[");
			
			boolean first = true;
		  for (int i = 0,nrow = menuList.size(); i < nrow; i++) {
			  Menus menu = (Menus)menuList.get(i);
			  if(menu.getDeepth() == 0){
				  if(first){
					  first = false;
	            	}else{
	            		sb.append(",");
	            	}
				  sb.append("{id : '"+menu.getId());
				  sb.append("',text : '"+ menu.getName()+"'");
				  if(ListUtil.isEmpty(mymenuIdList) ||  !mymenuIdList.contains(String.valueOf(menu.getId())))
      	        	  sb.append(",checked : false");
            	  else
      	        	 sb.append(",checked : true");
				  if(!StringUtil.isEmpty(menu.getUrl()) && !StringUtil.isEmpty(menu.getMenuLimits())){
					  String temp = dealwithMenuLimits(mymenuIdList,menu);
					  sb.append(",leaf:false");
					  sb.append(",children:").append(temp);
					  sb.append("}");
				  }else{
				      //以根据当前节点作为父节点查询其子节点
	                 StringBuffer s  = getLevelByParent(menuList, mymenuIdList, menu.getId());
	                  //判断当前节点下是否有子节点
		        		if (!s.toString().equals("[]")) {
		        			sb.append(",leaf:false");
							sb.append(",children:").append(s);
						} else {
							sb.append(",leaf:true");
						}
		        		 sb.append("}");
				  }
			  }
		  }
		  
		  sb.append("]");
	      return sb.toString();
	}
	
	 private StringBuffer getLevelByParent(List<?> allMenuList, List<?> myMenuIdList, Long nodeId) {
	        StringBuffer sb = new StringBuffer();
	        sb.append("[");
	        boolean first = true;
	        
	        for (int i = 0; i < allMenuList.size(); i++) {
				Menus menuItem = (Menus) allMenuList.get(i);
				if (menuItem != null && !LongUtil.isNewId(menuItem.getParentId())
						&& menuItem.getParentId().equals(nodeId)) {
					if(!first){
	            		sb.append(",");
	            	}else{
	            		first = false;
	            	}
					sb.append("{id : '"+menuItem.getId());
	            	sb.append("',text : '"+ menuItem.getName()+"'");
	            	  if(ListUtil.isEmpty(myMenuIdList) || !myMenuIdList.contains(String.valueOf(menuItem.getId())))
	      	        	  sb.append(",checked : false");
	            	  else
	      	        	 sb.append(",checked : true");
	            	  
	            	  if(!StringUtil.isEmpty(menuItem.getUrl()) && !StringUtil.isEmpty(menuItem.getMenuLimits())){
						  String temp = dealwithMenuLimits(myMenuIdList,menuItem);
						  sb.append(",leaf:false");
						  sb.append(",children:").append(temp);
						  sb.append("}");
					  }else{
		            	  
		            	  StringBuffer s  = getLevelByParent(allMenuList, myMenuIdList, menuItem.getId());
		            	  if (!s.toString().equals("[]")) {
		            			sb.append(",leaf:false");
		    					sb.append(",children:").append(s);
		    				} else {
		    					sb.append(",leaf:true");
		    				}
		            		 sb.append("}");
					  }
					
				}
				
	        }  	
	        sb.append("]");
	        return sb;
	    }
	 
	 public String dealwithMenuLimits(List<?> lists,Menus menu){
		 StringBuffer sb2 = new StringBuffer();
		 sb2.append("[");
		 boolean first = true;
		 if(menu.getMenuLimits().substring(0,1).equals("1")){
			 if(first){
				 sb2.append("{");
				 first = false;
			 }
			 else
				 sb2.append(",{");
			 
				 
			 Long tempId = menu.getId()+10000;
		     sb2.append("id : '"+tempId);
             sb2.append("',text : '增'");
             if(ListUtil.isEmpty(lists) || !lists.contains(String.valueOf(tempId)))
            	 sb2.append(",checked : false");
             else
            	 sb2.append(",checked : true");
             sb2.append(",leaf:true");
             sb2.append("}");
				 
		 }
		 if(menu.getMenuLimits().substring(1,2).equals("1")){
			 if(first){
				 sb2.append("{");
				 first = false;
			 }
			 else
				 sb2.append(",{");
			 Long tempId = menu.getId()+20000;
		     sb2.append("id : '"+tempId);
             sb2.append("',text : '删'");
             if(ListUtil.isEmpty(lists) || !lists.contains(String.valueOf(tempId)))
            	 sb2.append(",checked : false");
             else
            	 sb2.append(",checked : true");
             sb2.append(",leaf:true");
             sb2.append("}");
				 
		 }
		 if(menu.getMenuLimits().substring(2,3).equals("1")){
			 if(first){
				 sb2.append("{");
				 first = false;
			 }
			 else
				 sb2.append(",{");
			 Long tempId = menu.getId()+30000;
		     sb2.append("id : '"+tempId);
             sb2.append("',text : '改'");
             if(ListUtil.isEmpty(lists) || !lists.contains(String.valueOf(tempId)))
            	 sb2.append(",checked : false");
             else
            	 sb2.append(",checked : true");
             sb2.append(",leaf:true");
             sb2.append("}");
				 
		 }
		 if(menu.getMenuLimits().substring(3,4).equals("1")){
			 if(first){
				 sb2.append("{");
				 first = false;
			 }
			 else
				 sb2.append(",{");
			 Long tempId = menu.getId()+40000;
		     sb2.append("id : '"+tempId);
             sb2.append("',text : '打'");
             if(ListUtil.isEmpty(lists) || !lists.contains(String.valueOf(tempId)))
            	 sb2.append(",checked : false");
             else
            	 sb2.append(",checked : true");
             sb2.append(",leaf:true");
             sb2.append("}");
				 
		 }
		 if(menu.getMenuLimits().substring(4,5).equals("1")){
			 if(first){
				 sb2.append("{");
				 first = false;
			 }
			 else
				 sb2.append(",{");
			 Long tempId = menu.getId()+50000;
		     sb2.append("id : '"+tempId);
             sb2.append("',text : '查'");
             
             if(ListUtil.isEmpty(lists) || !lists.contains(String.valueOf(tempId)))
            	 sb2.append(",checked : false");
             else
            	 sb2.append(",checked : true");
             sb2.append(",leaf:true");
             sb2.append("}");
				 
		 }
		 if(menu.getMenuLimits().substring(5,6).equals("1")){
			 if(first){
				 sb2.append("{");
				 first = false;
			 }
			 else
				 sb2.append(",{");
			 Long tempId = menu.getId()+60000;
		     sb2.append("id : '"+tempId);
             sb2.append("',text : '审'");
             if(ListUtil.isEmpty(lists) || !lists.contains(String.valueOf(tempId)))
            	 sb2.append(",checked : false");
             else
            	 sb2.append(",checked : true");
             sb2.append(",leaf:true");
             sb2.append("}");
				 
		 }
		 if(menu.getMenuLimits().substring(6,7).equals("1")){
			 if(first){
				 sb2.append("{");
				 first = false;
			 }
			 else
				 sb2.append(",{");
			 Long tempId = menu.getId()+70000;
		     sb2.append("id : '"+tempId);
             sb2.append("',text : '撤'");
             if(ListUtil.isEmpty(lists) || !lists.contains(String.valueOf(tempId)))
            	 sb2.append(",checked : false");
             else
            	 sb2.append(",checked : true");
             sb2.append(",leaf:true");
             sb2.append("}");
				 
		 }
		 sb2.append("]");
		 return sb2.toString();
	 }
	/*
	 private StringBuffer getLevelView(List allMenuList, List myMenuIdList, Menu node) {
	        StringBuffer sb = new StringBuffer();
	        String check = "";
	        if (myMenuIdList.contains(String.valueOf(node.getId())))
	            check = " checked=\"1\" ";

	        sb
	                .append("<item text=\"")
	                .append(node.getName())
	                .append(
	                        "\" id=\""
	                                + node.getId()
	                                + "\" im0=\"book.gif\" im1=\"books_open.gif\" im2=\"books_close.gif\" ");

	        StringBuffer sonstr = new StringBuffer();
	        if(!StringUtil.isEmpty(node.getUrl()) && !StringUtil.isEmpty(node.getMenuQx()))
	        	 sonstr = getDetailXml(myMenuIdList,node);
	        	
	        else{
	        	
	        	 for (int j = 0,nrow = allMenuList.size(); j < nrow; j++) {
		            Menu sonNode = (Menu) allMenuList.get(j);
		            if (sonNode.getParentId() == null)
		                continue;
		            if (sonNode.getParentId().equals(node.getId())) {
		                sonstr.append(getLevelView(allMenuList, myMenuIdList, sonNode));
		            }
		        }
	        }
	        if (sonstr.length() == 0 && check.length() > 0)
	           sb.append(check);
	        sb.append(">");
	        sb.append(sonstr);
	        sb.append("</item>");
	        return sb;
	    }
	 
	 private StringBuffer getDetailXml(List lists,Menu menu){
		 StringBuffer sb = new StringBuffer();
		 if(menu.getMenuQx().substring(0,1).equals("1")){
				Long tempId = menu.getId()+10000;
			 sb.append("<item text=\"").append("增").append(
                     "\" id=\""
                             + tempId
                             + "\" im0=\"book.gif\" im1=\"books_open.gif\" im2=\"books_close.gif\" ");
		
			if(!ListUtil.isEmpty(lists) && lists.contains(String.valueOf(tempId)))
				sb.append(" checked=\"1\"").append("></item>");
			else
				sb.append("></item>");
		 }
		 if("1".equals(menu.getMenuQx().substring(1,2))){
				Long tempId = menu.getId()+20000;
			 sb.append("<item text=\"").append("删").append(
                  "\" id=\""
                          + tempId
                          + "\" im0=\"book.gif\" im1=\"books_open.gif\" im2=\"books_close.gif\" ");
		
			if(!ListUtil.isEmpty(lists) && lists.contains(String.valueOf(tempId)))
				sb.append(" checked=\"1\"").append("></item>");
			else
				sb.append("></item>");
		 }
		 if("1".equals(menu.getMenuQx().substring(2,3))){
				Long tempId = menu.getId()+30000;
			 sb.append("<item text=\"").append("改").append(
               "\" id=\""
                       + tempId
                       + "\" im0=\"book.gif\" im1=\"books_open.gif\" im2=\"books_close.gif\" ");
		
			if(!ListUtil.isEmpty(lists) && lists.contains(String.valueOf(tempId)))
				sb.append(" checked=\"1\"").append("></item>");
			else
				sb.append("></item>");
		 }
		 if("1".equals(menu.getMenuQx().substring(3,4))){
				Long tempId = menu.getId()+40000;
			 sb.append("<item text=\"").append("打").append(
            "\" id=\""
                    + tempId
                    + "\" im0=\"book.gif\" im1=\"books_open.gif\" im2=\"books_close.gif\" ");
		
			if(!ListUtil.isEmpty(lists) && lists.contains(String.valueOf(tempId)))
				sb.append(" checked=\"1\"").append("></item>");
			else
				sb.append("></item>");
		 }
		 if("1".equals(menu.getMenuQx().substring(4,5))){
				Long tempId = menu.getId()+50000;
			 sb.append("<item text=\"").append("查").append(
         "\" id=\""
                 + tempId
                 + "\" im0=\"book.gif\" im1=\"books_open.gif\" im2=\"books_close.gif\" ");
		
			if(!ListUtil.isEmpty(lists) && lists.contains(String.valueOf(tempId)))
				sb.append(" checked=\"1\"").append("></item>");
			else
				sb.append("></item>");
		 }
		 if("1".equals(menu.getMenuQx().substring(5,6))){
				Long tempId = menu.getId()+60000;
			 sb.append("<item text=\"").append("审").append(
      "\" id=\""
              + tempId
              + "\" im0=\"book.gif\" im1=\"books_open.gif\" im2=\"books_close.gif\" ");
		
			if(!ListUtil.isEmpty(lists) && lists.contains(String.valueOf(tempId)))
				sb.append(" checked=\"1\"").append("></item>");
			else
				sb.append("></item>");
		 }
		 if("1".equals(menu.getMenuQx().substring(6,7))){
				Long tempId = menu.getId()+70000;
			 sb.append("<item text=\"").append("撤").append(
   "\" id=\""
           + tempId
           + "\" im0=\"book.gif\" im1=\"books_open.gif\" im2=\"books_close.gif\" ");
		
			if(!ListUtil.isEmpty(lists) && lists.contains(String.valueOf(tempId)))
				sb.append(" checked=\"1\"").append("></item>");
			else
				sb.append("></item>");
		 }
		 return sb;
	 }
	 
	 public void  dealwithListForRoleMenu(String str,BillActor ba,Long roleId,String updatefiled) throws Exception{
			String sql  = "update xt_rolemenu set "+updatefiled+" ='Y' where roleid = '"+roleId+"' and menuid in("+str+")";
			gridDao.updateSql(sql);
		}
	 
	 protected void checkValid(BillActor billActor, Entity entity) throws Exception {
		 Role items = (Role) entity;
		 long id;
			if (LongUtil.isNewId(items.getId()))
				id = -1;
			else
				id = items.getId();

			List list = gridDao.gets(getCopyBean().getEntityClass(), "&gsDm ='"
					+ items.getGsDm() + "' &name='" + items.getName()
					+ "' & id<>" + id
					+ "&yxbz<>'" + "W'");
			if(list.size() > 0) {
				throw new ServiceException("名称重复！：" + items.getName()
						+ "如果未启用可以在未启用里面启用!");
			}
	 }

	@Override
	public void updateMenuQxForLUser(Long luserid, String menuIds, BillActor ba,String isIndependentRole) throws Exception {
		
			//String jsonstring = new XMLParseUtil().parXMl2JsonStringByDom4j(menuIds);
			//List<Object> roles = JacksonsUtil.me().json2List(jsonstring, new DmxxDTO());
		    String[] roles = menuIds.split(",");
		    
			String stringOne = "";
			 boolean temp1 = true;
			String stringTwo = "";
			boolean temp2 = true;
			String stringThree = "";
			boolean temp3 = true;
			String stringFoure = "";
			boolean temp4 = true;
			String stringFive = "";
			boolean temp5 = true;
			String stringSix = "";
			boolean temp6 = true;
			String stringSeven = "";
			boolean temp7 = true;
			
			//清除已有数据
					String delsql = "delete from xt_lusermenu where luserid = '"+luserid+"'";
					gridDao.updateSql(delsql);
					
			LoginUser luser = (LoginUser)gridDao.getById(LoginUser.class, luserid);
			if(luser == null)
			    return;
			boolean temp = false;
			if(StringUtil.isEmpty(isIndependentRole) || !"true".equals(isIndependentRole))		
			           luser.setIsIndependentRole("false");
			else{
				luser.setIsIndependentRole(isIndependentRole);
				temp = true;
			}
			gridDao.save(luser);
			System.out.println("==========updateMenuQxForLUser ================="+ isIndependentRole +"|"+temp);
			if(!temp)
				return;
			//处理接收的数据
				for(int i=0;i<roles.length;i++){	
					Long id  = Long.parseLong(roles[i]);
					//DmxxDTO role = (DmxxDTO)roles.get(i);
					if(id < 10000){
						//新增数据
						String sql = "insert into xt_lusermenu(operateadd,operatedelete,operateedit,operateprint,operatesearch,operateaudit,operateundoaudit,luserid,menuid,yxbz,lrrq,lrrdm,gsdm)"+
					                 "values('N','N','N','N','N','N','N','"+luserid+"','"+id+"','Y','"+DatetimeUtil.getCurrentDateTimeString()+"','"+ba.getLuserId()+"','"+ba.getBigGsdm()+"')";
					    gridDao.updateSql(sql);
					 }else{
						 if(id >10000 && id < 20000){
							 if(temp1){
								 temp1 = false;
								 stringOne = stringOne+(id % 10000); 
							 }else{
								 stringOne= stringOne+(","+id % 10000); 
							 }
							 
						 }
						 if(id >20000 && id < 30000){
							 if(temp2){
								 temp2 = false;
								 stringTwo  = stringTwo +(id % 10000); 
							 }else{
								 stringTwo= stringTwo +(","+id % 10000); 
							 }
						 }
							 
						 if(id >30000 && id < 40000){
							 if(temp3){
								 temp3 = false;
								 stringThree = stringThree + (id % 10000); 
							 }else{
								 stringThree= stringThree + (","+id % 10000); 
							 }
						 }
						 if(id >40000 && id < 50000){
							 if(temp4){
								 temp4 = false;
								 stringFoure = stringFoure +(id % 10000); 
							 }else{
								 stringFoure = stringFoure +(","+id % 10000); 
							 }
						 }
						 if(id >50000 && id < 60000){
							 if(temp5){
								 temp5 = false;
								 stringFive = stringFive +(id % 10000); 
							 }else{
								 stringFive = stringFive +(","+id % 10000); 
							 }
						 }
						 if(id >60000 && id < 70000){
							 if(temp6){
								 temp6 = false;
								 stringSix = stringSix +(id % 10000); 
							 }else{
								 stringSix = stringSix +(","+id % 10000); 
							 }
						 }
						 if(id >70000 && id < 80000){
							 if(temp7){
								 temp7 = false;
								 stringSeven = stringSeven +(id % 10000); 
							 }else{
								 stringSeven = stringSeven +(","+id % 10000); 
							 }
						 }
					 }
				  }
				 *//**
				  * 处理list
				  *//*
				 if(!StringUtil.isEmpty(stringOne.toString()))
					 dealwithListForLuserMenu(stringOne,ba,luserid,"operateadd");
				 if(!StringUtil.isEmpty(stringTwo.toString()))
					 dealwithListForLuserMenu(stringTwo,ba,luserid,"operatedelete");
				 if(!StringUtil.isEmpty(stringThree.toString()))
					 dealwithListForLuserMenu(stringThree,ba,luserid,"operateedit");
				 if(!StringUtil.isEmpty(stringFoure.toString()))
					 dealwithListForLuserMenu(stringFoure,ba,luserid,"operateprint");
				 if(!StringUtil.isEmpty(stringFive.toString()))
					 dealwithListForLuserMenu(stringFive,ba,luserid,"operatesearch");
				 if(!StringUtil.isEmpty(stringSix.toString()))
					 dealwithListForLuserMenu(stringSix,ba,luserid,"operateaudit");
				 if(!StringUtil.isEmpty(stringSeven.toString()))
					 dealwithListForLuserMenu(stringSeven,ba,luserid,"operateundoaudit");
	}
	
	public void  dealwithListForLuserMenu(String str,BillActor ba,Long luserid,String updatefiled) throws Exception{
		String sql  = "update xt_lusermenu set "+updatefiled+" ='Y' where luserid = '"+luserid+"' and menuid in("+str+")";
		gridDao.updateSql(sql);
	}
	*/
	
	public void remove(SessionUser sessionUser,String selectedId) throws Exception{
		
		//更新rolemenu
			selectedId = StringUtil.realStr(selectedId);
		 //String rolemenusql = "delete from xt_rolemenu where roleid  in("+selectedId+")";
		 //gridDao.executeSql(rolemenusql);
		//更新role	
		//String menusql = "delete from xt_role where tid in("+selectedId+")";
		//gridDao.updateSql(menusql);	
		 super.remove(sessionUser, selectedId);
	}
	
	public void checkValidBeforeUpdate(SessionUser sessionuser, DTO billDTO) throws Exception{
		RoleDTO dto = (RoleDTO) billDTO;
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(getCopyBean().getEntityClass());
		detachedCriteria.setProjection(Projections.rowCount());
		detachedCriteria.add(Restrictions.eq("name", dto.getName()));
		if(!LongUtil.isNewId(dto.getId()))
		   detachedCriteria.add(Restrictions.ne("id", dto.getId()));
		detachedCriteria.add(Restrictions.eq("status",true ));
		List<?> list = gridDao.find(detachedCriteria);
		if (!ListUtil.isEmpty(list) && LongUtil.getDoubleByObject(list.get(0)) > 0) {
			throw new Exception("已有角色名为:" + dto.getName() + ",请更改名称");
		}
	};
	
	public ResultEntity updateRoleMenu(String roleId, String menuIds,
			SessionUser sessioinuser) throws Exception {
		
		ResultEntity rey = createResultEntity();
		
		List<Object> roles = JacksonsUtil.me().json2List(menuIds, new RoleDTO());
			
		String stringOne = "";
		 boolean temp1 = true;
		String stringTwo = "";
		boolean temp2 = true;
		String stringThree = "";
		boolean temp3 = true;
		String stringFoure = "";
		boolean temp4 = true;
		String stringFive = "";
		boolean temp5 = true;
		String stringSix = "";
		boolean temp6 = true;
		String stringSeven = "";
		boolean temp7 = true;
		
		
		//清除已有数据
		String delsql = "delete from xt_role_menu where role_id = '"+roleId+"'";
		gridDao.executeSql(delsql);
		//处理接收的数据
		 for(int i=0;i<roles.size();i++){
			 RoleDTO role = (RoleDTO)roles.get(i);
			
			 if(role.getId() < 10000){
				//新增数据
//				String sql = "insert into xt_role_menu(operate_add,operate_delete,operate_edit,operate_print,operate_search,operate_audit,operate_undo_audit,role_id,menu_id,status,lrrq,lrrdm,gsdm)"+
//			                // "values('N','N','N','N','N','N','N','"+roleId+"','"+role.getId()+"','1','"+DatetimeUtil.getCurrentDateTimeString()+"','"+sessioinuser.getLoginId()+"','"+sessioinuser.getBigGsdm()+"')";
//			                   "values('N','N','N','N','N','N','N','"+roleId+"','"+role.getId()+"','true','"+DatetimeUtil.getCurrentDateTimeString()+"','"+sessioinuser.getId()+"','"+sessioinuser.getBigGsdm()+"')";
				 String sql = "insert into xt_role_menu(operate_add,operate_delete,operate_edit,operate_print,operate_search,operate_audit,operate_undo_audit,role_id,menu_id,status,lrrq,lrrdm,gsdm)"+
						 // "values('N','N','N','N','N','N','N','"+roleId+"','"+role.getId()+"','1','"+DatetimeUtil.getCurrentDateTimeString()+"','"+sessioinuser.getLoginId()+"','"+sessioinuser.getBigGsdm()+"')";
						 "values('','','','','','','','"+roleId+"','"+role.getId()+"',1,'"+DatetimeUtil.getCurrentDateTimeString()+"','"+sessioinuser.getId()+"','"+sessioinuser.getBigGsdm()+"')";
				 System.out.println("sql是： " + sql);
			    gridDao.executeSql(sql);
			 }else{
				 if(role.getId() >10000 && role.getId() < 20000){
					 if(temp1){
						 temp1 = false;
						 stringOne = stringOne+(role.getId() % 10000); 
					 }else{
						 stringOne= stringOne+(","+role.getId() % 10000); 
					 }
					 
				 }
				 if(role.getId() >20000 && role.getId() < 30000){
					 if(temp2){
						 temp2 = false;
						 stringTwo  = stringTwo +(role.getId() % 10000); 
					 }else{
						 stringTwo= stringTwo +(","+role.getId() % 10000); 
					 }
				 }
					 
				 if(role.getId() >30000 && role.getId() < 40000){
					 if(temp3){
						 temp3 = false;
						 stringThree = stringThree + (role.getId() % 10000); 
					 }else{
						 stringThree= stringThree + (","+role.getId() % 10000); 
					 }
				 }
				 if(role.getId() >40000 && role.getId() < 50000){
					 if(temp4){
						 temp4 = false;
						 stringFoure = stringFoure +(role.getId() % 10000); 
					 }else{
						 stringFoure = stringFoure +(","+role.getId() % 10000); 
					 }
				 }
				 if(role.getId() >50000 && role.getId() < 60000){
					 if(temp5){
						 temp5 = false;
						 stringFive = stringFive +(role.getId() % 10000); 
					 }else{
						 stringFive = stringFive +(","+role.getId() % 10000); 
					 }
				 }
				 if(role.getId() >60000 && role.getId() < 70000){
					 if(temp6){
						 temp6 = false;
						 stringSix = stringSix +(role.getId() % 10000); 
					 }else{
						 stringSix = stringSix +(","+role.getId() % 10000); 
					 }
				 }
				 if(role.getId() >70000 && role.getId() < 80000){
					 if(temp7){
						 temp7 = false;
						 stringSeven = stringSeven +(role.getId() % 10000); 
					 }else{
						 stringSeven = stringSeven +(","+role.getId() % 10000); 
					 }
				 }
			 }
		 }
		 
		 /**
		  * 处理list
		  */
		 if(!StringUtil.isEmpty(stringOne.toString()))
			 dealwithListForRoleMenu(stringOne,sessioinuser,roleId,"operate_add");
		 if(!StringUtil.isEmpty(stringTwo.toString()))
			 dealwithListForRoleMenu(stringTwo,sessioinuser,roleId,"operate_delete");
		 if(!StringUtil.isEmpty(stringThree.toString()))
			 dealwithListForRoleMenu(stringThree,sessioinuser,roleId,"operate_edit");
		 if(!StringUtil.isEmpty(stringFoure.toString()))
			 dealwithListForRoleMenu(stringFoure,sessioinuser,roleId,"operate_print");
		 if(!StringUtil.isEmpty(stringFive.toString()))
			 dealwithListForRoleMenu(stringFive,sessioinuser,roleId,"operate_search");
		 if(!StringUtil.isEmpty(stringSix.toString()))
			 dealwithListForRoleMenu(stringSix,sessioinuser,roleId,"operate_audit");
		 if(!StringUtil.isEmpty(stringSeven.toString()))
			 dealwithListForRoleMenu(stringSeven,sessioinuser,roleId,"operate_undo_audit");
		    
		 rey.setResultType(0);
		 rey.setResultDesc("OK");
		return rey;
	}
	
	public void  dealwithListForRoleMenu(String str,SessionUser sessioinuser,String roleId,String updatefiled) throws Exception{
		String sql  = "update xt_role_menu set "+updatefiled+" ='Y' where role_id = '"+roleId+"' and menu_id in("+str+")";
		gridDao.executeSql(sql);
	}
	
	public void updateIndependentLUserRole(String roleId,String menuIds, Boolean checkb, SessionUser sessioinuser)throws Exception {
		
		List<Object> roles = JacksonsUtil.me().json2List(menuIds, new RoleDTO());
			
		String stringOne = "";
		 boolean temp1 = true;
		String stringTwo = "";
		boolean temp2 = true;
		String stringThree = "";
		boolean temp3 = true;
		String stringFoure = "";
		boolean temp4 = true;
		String stringFive = "";
		boolean temp5 = true;
		String stringSix = "";
		boolean temp6 = true;
		String stringSeven = "";
		boolean temp7 = true;
		
		
		String delsql = "delete from xt_loginuser_menu where loginuser_id = '"+roleId+"'";
		gridDao.executeSql(delsql);
		 
			LoginUser luser = (LoginUser)gridDao.getById(LoginUser.class, Long.parseLong(roleId));
			if(luser != null){
				luser.setIsIndependentRole(checkb);
				gridDao.save(luser);
			}
		 if(checkb && !ListUtil.isEmpty(roles))	{
			 
				 for(int i=0;i<roles.size();i++){
					 RoleDTO role = (RoleDTO)roles.get(i);
					
					 if(role.getId() < 10000){
//						String sql = "insert into xt_loginuser_menu(operate_add,operate_delete,operate_edit,operate_print,operate_search,operate_audit,operate_undo_audit,loginuser_id,menu_id,status,lrrq,lrrdm,gsdm)"+
//					                 "values('N','N','N','N','N','N','N','"+roleId+"','"+role.getId()+"','true','"+DatetimeUtil.getCurrentDateTimeString()+"','"+sessioinuser.getId()+"','"+sessioinuser.getBigGsdm()+"')";
						 String sql = "insert into xt_loginuser_menu(operate_add,operate_delete,operate_edit,operate_print,operate_search,operate_audit,operate_undo_audit,loginuser_id,menu_id,status,lrrq,lrrdm,gsdm)"+
								 "values('','','','','','','','"+roleId+"','"+role.getId()+"','true','"+DatetimeUtil.getCurrentDateTimeString()+"','"+sessioinuser.getId()+"','"+sessioinuser.getBigGsdm()+"')";
						 gridDao.executeSql(sql);
					 }else{
						 if(role.getId() >10000 && role.getId() < 20000){
							 if(temp1){
								 temp1 = false;
								 stringOne = stringOne+(role.getId() % 10000); 
							 }else{
								 stringOne= stringOne+(","+role.getId() % 10000); 
							 }
							 
						 }
						 if(role.getId() >20000 && role.getId() < 30000){
							 if(temp2){
								 temp2 = false;
								 stringTwo  = stringTwo +(role.getId() % 10000); 
							 }else{
								 stringTwo= stringTwo +(","+role.getId() % 10000); 
							 }
						 }
							 
						 if(role.getId() >30000 && role.getId() < 40000){
							 if(temp3){
								 temp3 = false;
								 stringThree = stringThree + (role.getId() % 10000); 
							 }else{
								 stringThree= stringThree + (","+role.getId() % 10000); 
							 }
						 }
						 if(role.getId() >40000 && role.getId() < 50000){
							 if(temp4){
								 temp4 = false;
								 stringFoure = stringFoure +(role.getId() % 10000); 
							 }else{
								 stringFoure = stringFoure +(","+role.getId() % 10000); 
							 }
						 }
						 if(role.getId() >50000 && role.getId() < 60000){
							 if(temp5){
								 temp5 = false;
								 stringFive = stringFive +(role.getId() % 10000); 
							 }else{
								 stringFive = stringFive +(","+role.getId() % 10000); 
							 }
						 }
						 if(role.getId() >60000 && role.getId() < 70000){
							 if(temp6){
								 temp6 = false;
								 stringSix = stringSix +(role.getId() % 10000); 
							 }else{
								 stringSix = stringSix +(","+role.getId() % 10000); 
							 }
						 }
						 if(role.getId() >70000 && role.getId() < 80000){
							 if(temp7){
								 temp7 = false;
								 stringSeven = stringSeven +(role.getId() % 10000); 
							 }else{
								 stringSeven = stringSeven +(","+role.getId() % 10000); 
							 }
						 }
					 }
				 }
				 /**
				  * 处理list
				  */
				 if(!StringUtil.isEmpty(stringOne.toString()))
					 dealwithListForIndependentRole(stringOne,sessioinuser,roleId,"operate_add");
				 if(!StringUtil.isEmpty(stringTwo.toString()))
					 dealwithListForIndependentRole(stringTwo,sessioinuser,roleId,"operate_delete");
				 if(!StringUtil.isEmpty(stringThree.toString()))
					 dealwithListForIndependentRole(stringThree,sessioinuser,roleId,"operate_edit");
				 if(!StringUtil.isEmpty(stringFoure.toString()))
					 dealwithListForIndependentRole(stringFoure,sessioinuser,roleId,"operate_print");
				 if(!StringUtil.isEmpty(stringFive.toString()))
					 dealwithListForIndependentRole(stringFive,sessioinuser,roleId,"operate_search");
				 if(!StringUtil.isEmpty(stringSix.toString()))
					 dealwithListForIndependentRole(stringSix,sessioinuser,roleId,"operate_audit");
				 if(!StringUtil.isEmpty(stringSeven.toString()))
					 dealwithListForIndependentRole(stringSeven,sessioinuser,roleId,"operate_undo_audit");
				 }  
	}
	
	public void  dealwithListForIndependentRole(String str,SessionUser sessioinuser,String roleId,String updatefiled) throws Exception{
		String sql  = "update xt_loginuser_menu set "+updatefiled+" ='Y' where loginuser_id = '"+roleId+"' and menu_id in("+str+")";
		gridDao.executeSql(sql);
	}
	
	
	/*********************重写***************/
	public void updateBill(SessionUser sessionuser, String data) throws Exception {
		 super.updateBill(sessionuser, data);
	};
}
