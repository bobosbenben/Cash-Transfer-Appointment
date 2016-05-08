package en.common.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import en.common.dto.DTO;
import en.common.entity.BaseEntity;
import en.common.frame.shiro.SessionUser;
import en.common.util.helper.JacksonsUtil;
import en.common.util.helper.ResultJsonUtil;
import en.common.util.helper.ResultPageInit;
import en.common.util.helper.StringUtil;

public abstract class BaseServiceImpl {
	
	protected Logger log = LogManager.getLogger(this.getClass());
	
	 public String dealTrueCondForSql(SessionUser sessionuser,String cond,Boolean isRoleCompany){

		 if(isRoleCompany && !StringUtil.isEmpty(sessionuser.getLimitsCompanys())){ 
			   if(!StringUtil.isEmpty(cond)){
				   if(cond.indexOf("1=1") >=0)
					   cond = cond +  "and  gsdm in(select gsdm from dm_company where tid in ("+sessionuser.getLimitsCompanys()+") and status = 'true')" ;
				   else
					   cond = " 1=1 "+cond +  " and  gsdm in(select gsdm from dm_company where tid in ("+sessionuser.getLimitsCompanys()+") and status = 'true')" ;   
			   }else{
				   cond = "1=1 and  gsdm in(select gsdm from cc_basis_company where tid in ("+sessionuser.getLimitsCompanys()+") and status = 'true')" ;
				   System.out.println("这里用到了cc_basis_company这个数据库，大概是作者没有给，出错了");
			   }
		   }
			if(StringUtil.isEmpty(cond))
				 cond =  " 1=1 and gsdm like '"+ sessionuser.getGsdm()+"%'" ;
			else{
				if(cond.indexOf("gsdm")< 0){
					if(cond.indexOf("1=1") <0)
				     cond =  " 1=1 "+ cond + "  and gsdm like '"+ sessionuser.getGsdm()+"%'" ;
					else
					 cond =  cond + "  and gsdm like '"+ sessionuser.getGsdm()+"%'" ;
				}
			}
		   return cond;
	   }
	   /**
		 * 处理其他
		 */
		 public DetachedCriteria dealTrueCond(SessionUser sessionuser,String cond,Boolean isRoleCompany,DetachedCriteria detachedCriteria){
			 //System.out.println("cond 是："+cond);
			 if(isRoleCompany && !StringUtil.isEmpty(sessionuser.getLimitsCompanys())){
				 //当cond里包含5<>4时不加入权限仓库
				  if(!StringUtil.isEmpty(cond)){
					     detachedCriteria.add(Restrictions.sqlRestriction(cond));
				       if( cond.indexOf("5<>4") < 0)
					     detachedCriteria.add(Restrictions.sqlRestriction( " this_.gsdm in(select gsdm from cc_basis_company where tid in ("+sessionuser.getLimitsCompanys()+") and status = 'true')")) ;
				  }
				  else{
					      detachedCriteria.add(Restrictions.sqlRestriction( " this_.gsdm in(select gsdm from cc_basis_company where tid in ("+sessionuser.getLimitsCompanys()+") and status = 'true')")) ;
					}
				  if(StringUtil.isEmpty(cond) || cond.indexOf("5<>4") < 0){
				    if(!StringUtil.isEmpty(cond))
					     detachedCriteria.add(Restrictions.sqlRestriction(cond));
				  }
				  else{
					  if(!StringUtil.isEmpty(cond))
					     detachedCriteria.add(Restrictions.sqlRestriction(cond));
				  }
			   }else{
				   if(StringUtil.isEmpty(cond))
					   detachedCriteria.add(Restrictions.like("gsDm", sessionuser.getGsdm()+"%")) ;
				   
				   else{
					   if(cond.indexOf("gsdm") < 0)
						   detachedCriteria.add(Restrictions.like("gsDm", sessionuser.getGsdm()+"%")) ;
					   detachedCriteria.add(Restrictions.sqlRestriction(cond));
				   }
			   }
			   return detachedCriteria;
		   }
	
	 protected <T> String dealGridPage(SessionUser sessionuser,ResultPageInit page) throws Exception{
			
			ResultJsonUtil rju = new ResultJsonUtil(page.getRowCount());

			BaseEntity obj;
			List<?> list = page.getResult();
			//{totalRecords:2,record : [{name : '超级管理员',gsDm : '00002',yxbz : 'true',id : '1',updateLx : ''},},{name : '系统管理员',gsDm : '00002',yxbz : 'true',id : '2',updateLx : ''},}]}
			int nsize = list != null ? list.size() : 0;

		 	//System.out.println("nsize: "+nsize);
			for (int i = 0; i < nsize; i++) {
				obj =  (BaseEntity)list.get(i);
				rju.addContainers(JacksonsUtil.me().readAsString(getDTOByBill(sessionuser,obj)));
				if((i+1!=nsize))
				  rju.addsign();
			}
			String resultMsg = rju.getJsonStr().replace("\"", "'");
			//String url= "{totalRecords:1,record : [{'deptName' : '超级管理员','address':'fdsfsdf','bz': '00002','yxbz' : 'true','id' : '1'}]}";
			System.out.println("load数据:"+resultMsg);
			
			return resultMsg;
		}
	 
	   public abstract DTO getDTOByBill(SessionUser sessionuser,Object en) throws Exception;
}
