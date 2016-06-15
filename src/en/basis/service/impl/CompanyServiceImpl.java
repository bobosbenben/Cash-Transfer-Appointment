package en.basis.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import en.basis.copybean.impl.CompanyCopyBeanImpl;
import en.basis.service.ICompanyService;
import en.common.copybean.ICopyBean;
import en.common.entity.BaseEntity;
import en.common.frame.shiro.SessionUser;
import en.common.service.impl.GridServiceImpl;
import en.common.util.helper.ListUtil;
import en.common.util.helper.LongUtil;
import en.common.util.helper.StringUtil;
import en.dm.entity.Company;

@Service
public class CompanyServiceImpl extends GridServiceImpl implements ICompanyService{
	
	protected ICopyBean getCopyBean() {
		CompanyCopyBeanImpl cp = new CompanyCopyBeanImpl();
		cp.setGridDao(gridDao);
		return cp;
	}
	
//	/*@Resource
//	private ISysParamService sysParamService;
//
//	public Company getGsxxByGsDm(String dm,String gsDm)  throws Exception{
//		 DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getCopyBean().getEntityClass());
//		 detachedCriteria.add(Restrictions.sqlRestriction(" gsdm ='"+dm+"' and gsdm like'"+gsDm+"%' and status='true' "));
//		 try{
//				//List<Company> list =   gridDao.find(detachedCriteria);
//			 List<Company> list =   gridDao.getsCacheEntityList(detachedCriteria);
//				if(ListUtil.isEmpty(list)){
//					return null;
//				}else{
//					Company company = list.get(0);
//					 return company;
//				 }
//		 }catch (Exception e) {
//					  e.printStackTrace();
//					  String err = e.getCause() == null ? e.getMessage() : e.getCause().getMessage();
//					  throw new Exception(err);
//				}
//	}
//
//
//
//	public List getCompanyList(SessionUser sessionUser, String cond)
//			throws Exception {
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getCopyBean().getEntityClass());
//		detachedCriteria = dealTrueCond(sessionUser,cond,true,detachedCriteria);
//		detachedCriteria.add(Restrictions.eq("status", true));
//		//List<Company> list =   gridDao.find(detachedCriteria);
//		List<Company> list =   gridDao.getsCacheEntityList(detachedCriteria);
//		return list;
//	}
//
//
//	 private StringBuffer buildJsonByParent( List<Company> gsxx,	String pid) throws Exception{
//			StringBuffer sb = new StringBuffer();
//			sb.append("[");
//			boolean first = true;
//			for (int i = 0; i < gsxx.size(); i++) {
//				Company sonGs = (Company) gsxx.get(i);
//				if (sonGs != null && !StringUtil.isEmpty(sonGs.getSjgsDm())
//						&& sonGs.getSjgsDm().equals(pid)) {
//					if (first){
//						first = false;
//					}else{
//						sb.append(",");
//					}
//					sb.append("{text:'").append(sonGs.getShortName()).append("',id:'")
//					.append(sonGs.getId()).append("',gsDm:'").append(
//							sonGs.getGsDm()).append("'");
//
//					StringBuffer s = buildJsonByParent(gsxx, sonGs.getGsDm());
//					if (!s.toString().equals("[]")) {
//						sb.append(",children:").append(s);
//					} else {
//						sb.append(",leaf:true");
//					}
//					sb.append("}");
//				}
//			}
//			sb.append("]");
//			return sb;
//		}
//
//	 public ResultEntity updateBill(SessionUser sessionuser, String data) throws Exception{
//			return super.updateBill(sessionuser, data);
//		}
//
//	 */

		protected void checkValid(SessionUser billActor, BaseEntity entity) throws Exception {
			 Company item = (Company) entity;   
			/**
			 * 处理独立核算代码的问题
			 */
			if(true == item.getIsIndependentAccounting()){
				if(item.getStatus()){
					item.setHsgsDm(item.getGsDm());
					if(LongUtil.isNewId(item.getId()))
						return;
					dealwithChildCompany(billActor,item.getGsDm(),item.getGsDm());
				}
			}else{
				if(!LongUtil.isNewId(item.getId())){
					if( "changeHs".equals(item.getBz2())){
					String gsdmstr = item.getGsDm();
					 for(int i=(gsdmstr.length()-3);i>0;i-=3){
							DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getCopyBean().getEntityClass());
							detachedCriteria.add(Restrictions.eq("gsDm", gsdmstr.substring(0,i)));
							detachedCriteria.add(Restrictions.eq("status", true));
							List<?> list = gridDao.find(detachedCriteria);
							if(!ListUtil.isEmpty(list)){
								Company com = (Company)list.get(0);
								boolean dlhs = (com.getIsIndependentAccounting() == null) ? false :com.getIsIndependentAccounting();
								if(dlhs){
									item.setHsgsDm(com.getGsDm());
									dealwithChildCompany(billActor,item.getGsDm(),com.getGsDm());
									break;
								}
								if(com.getGsDm().equals(billActor.getBigGsdm())){
									//dealwithChildCompany(billActor,item.getGsDm(),billActor.getBigGsdm());
									throw new Exception("此操作导致下属公司无核算单位");
								}
								
								
							}
			    		}
					}
				}else{
					
				String gsdmstr = item.getGsDm();
				for(int i=(gsdmstr.length()-3);i>0;i-=3){
					
					DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getCopyBean().getEntityClass());
					detachedCriteria.add(Restrictions.eq("gsDm", gsdmstr.substring(0,i)));
					detachedCriteria.add(Restrictions.eq("status", true));
					List<?> list = gridDao.find(detachedCriteria);
					if(!ListUtil.isEmpty(list)){
						Company com = (Company)list.get(0);
						boolean dlhs = (com.getIsIndependentAccounting() == null) ? false :com.getIsIndependentAccounting();
						if(dlhs){
							item.setHsgsDm(com.getGsDm());
							break;
						}
						if(com.getGsDm().equals(billActor.getBigGsdm()))
							break;
								
					}
	    		  }
				
				   if(StringUtil.isEmpty(item.getHsgsDm()))
					   throw new Exception("所添加的公司无核算单位,无法添加");
				}
			}

		}
		
		
		private void dealwithChildCompany(SessionUser billActor,String gsDm,String hsgsdm) throws Exception{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getCopyBean().getEntityClass());
			detachedCriteria.add(Restrictions.eq("sjgsDm", gsDm));
			detachedCriteria.add(Restrictions.eq("status", true));
			List<?> list = gridDao.find(detachedCriteria);
			if(!ListUtil.isEmpty(list)){
				for(int i=0,nsize=list.size();i<nsize;i++){
					Company com = (Company)list.get(i);
					if(true != com.getIsIndependentAccounting() && !com.getHsgsDm().equals(hsgsdm)){
						com.setHsgsDm(hsgsdm);
						gridDao.save(com);
						dealwithChildCompany(billActor,com.getGsDm(),hsgsdm);
					}
				}
			}
		}
		
		public List<?> getCompanyList(SessionUser sessionUser, String cond)
				throws Exception {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getCopyBean().getEntityClass());
			detachedCriteria = dealTrueCond(sessionUser,cond,true,detachedCriteria);
			detachedCriteria.add(Restrictions.eq("status", true));
			List<?> list =   gridDao.getsCacheEntityList(detachedCriteria);
			return list;
		}
		
	
	public List<?> getHsCompanys(SessionUser sessionUser, Boolean bz) throws Exception {
		String query = "";
		String hsgsdm = sessionUser.getBigGsdm().equals(sessionUser.getGsdm()) ? sessionUser.getBigGsdm() : sessionUser.getHsgsDm();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getCopyBean().getEntityClass());
		if(bz)
			query = " and (isnull(is_independent_accounting) = 1 or gsdm = '"+sessionUser.getBigGsdm()+"' ) and gsdm like'"+hsgsdm+"%' and status=1";
		else
			query = " and status = 1 and isnull(is_independent_accounting) = 1  and gsdm like '"+hsgsdm+"%'";
		detachedCriteria.add(Restrictions.sqlRestriction(" 1=1 "+query));
		
		return  gridDao.find(detachedCriteria);
	}
//	/*
//	private List<Company> getTopCompany(List<Company> oldcompanys){
//		List<Company> newcompanys = new ArrayList<Company>();
//		System.out.println("==============getTopCompany============="+oldcompanys.size());
//		for(Company  company : oldcompanys){
//			boolean top = true;
//			boolean son = false;
//			for(Company  company3 : oldcompanys){
//				if(company.getGsDm().equals(company3.getSjgsDm())){
//					top = true;
//				}
//			}
//			for(Company  company2 : oldcompanys){
//				if(company.getSjgsDm().equals(company2.getGsDm())){
//					son = true;
//					top = false;
//				}
//			}
//			System.out.println("======================"+top+"|"+son);
//		 if(top && !son)
//			 newcompanys.add(company);
//		}
//
//		System.out.println("==============getTopCompany============="+oldcompanys.size());
//
//		return newcompanys;
//	}
//
//	@Override
//	public String getCompanyTreeForBj(SessionUser sessionUser, String cond)
//			throws Exception {
//System.out.println("=================获取权限树开始======================");
//
//		StringBuffer sb = new StringBuffer();
//	String isbjlimit = sysParamService.getSySParamDefault(sessionUser, "COMPANYBJ_LIMIT", "N");
//	if("Y".equals(isbjlimit)){
//		  sb .append(getCompanyTree(sessionUser, cond));
//	}else{
//		if(StringUtil.isEmpty(sessionUser.getHsgsDm()))
//			return null;
//		else{
//			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getCopyBean().getEntityClass());
//			detachedCriteria.add(Restrictions.eq("status", true));
//			detachedCriteria.add(Restrictions.eq("gsDm", sessionUser.getHsgsDm()));
//			List<Company> listcompany =   gridDao.getsCacheEntityList(detachedCriteria);
//			if (ListUtil.isEmpty(listcompany))
//				return null;
//		   else{
//			   Company company = listcompany.get(0);
//			   sb.append("{text:'").append(company.getShortName()).append("',id:'").append(company.getId()).append("',gsDm :'").append(company.getGsDm()).append("',leaf:true").append("}");
//		   }
//		}
//
//	}
//		return sb.toString();
//  }
//
//	@Override
//	public String getCompanyIdByCompanyName(SessionUser sessionUser,
//			String compnayName) throws Exception{
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getCopyBean().getEntityClass());
//		detachedCriteria.add(Restrictions.eq("status", true));
//		detachedCriteria.add(Restrictions.eq("shortName", compnayName));
//		detachedCriteria.add(Restrictions.like("gsDm", sessionUser.getHsgsDm()+"%"));
//		List<Company> listcompany =   gridDao.getsCacheEntityList(detachedCriteria);
//		if(ListUtil.isEmpty(listcompany))
//			return null;
//		else
//			return listcompany.get(0).getGsDm();
//	}*/
	
	@Override
	public String getCompanyTree(SessionUser sessionUser, String cond)
			throws Exception {
		System.out.println("=================获取权限树开始======================");
		
		StringBuffer sb = new StringBuffer();
		if(StringUtil.isEmpty(sessionUser.getLimitsCompanys())){
				DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getCopyBean().getEntityClass());
				detachedCriteria.add(Restrictions.eq("status", true));
				detachedCriteria.add(Restrictions.like("gsDm", sessionUser.getGsdm()+"%"));
				//detachedCriteria = dealTrueCond(sessionUser,cond,true,detachedCriteria);
				List<?> listcompany =   gridDao.getsCacheEntityList(detachedCriteria);
				if (!ListUtil.isEmpty(listcompany)) {
					for(int i=0,nsize=listcompany.size();i<nsize;i++){
						Company company = (Company)listcompany.get(i);
						if( null != company && company.getGsDm().equals(sessionUser.getGsdm())){
							sb.append("{text:'").append(company.getShortName()).append("',id:'").append(company.getId()).append("',gsDm :'").append(company.getGsDm()).append("'");
							StringBuffer s = buildJsonByParent(listcompany, company.getGsDm());
							if (!s.toString().equals("[]")) {
								sb.append(",children:").append(s);
							} else {
								sb.append(",leaf:true");
							}
							sb.append("}");
						}
					}
			   }
			}else{
				String[] strs  = sessionUser.getLimitsCompanys().split(",");
				Integer ids[] = new Integer[strs.length];
				for(int i=0;i< strs.length;i++){
					ids[i] =  Integer.parseInt(strs[i]);
				}
				DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getCopyBean().getEntityClass());
				detachedCriteria.add(Restrictions.eq("status", true));
				detachedCriteria.add(Restrictions.sqlRestriction("tid in("+sessionUser.getLimitsCompanys()+")"));
				
				List<?> listcompany =   gridDao.getsCacheEntityList(detachedCriteria);
				if(ListUtil.isEmpty(listcompany))
					throw new Exception("无法获取相应公司信息");
				List<Company> newList = getTopCompany(listcompany);
				if(ListUtil.isEmpty(newList))
					throw new Exception("无法获取相应公司权限信息");
				String revalue = "[";
				for(Company company : newList){
					sb.append("{text:'").append(company.getShortName()).append("',id:'").append(company.getId()).append("',gsDm :'").append(company.getGsDm()).append("'");
					StringBuffer s = buildJsonByParent(listcompany, company.getGsDm());
					if (!s.toString().equals("[]")) {
						sb.append(",children:").append(s);
					} else {
						sb.append(",leaf:true");
					}
					sb.append("},");
				}
				revalue = revalue+ sb.toString().substring(0, sb.toString().length()-1);
				revalue = revalue +"]";
				return revalue;
			}
		return sb.toString();
	}
	private StringBuffer buildJsonByParent( List<?> gsxx,	String pid) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		boolean first = true;
		for (int i = 0; i < gsxx.size(); i++) {
			Company sonGs = (Company) gsxx.get(i);
			if (sonGs != null && !StringUtil.isEmpty(sonGs.getSjgsDm())
					&& sonGs.getSjgsDm().equals(pid)) {
				if (first){
					first = false;
				}else{
					sb.append(",");
				}
				sb.append("{text:'").append(sonGs.getShortName()).append("',id:'")
				.append(sonGs.getId()).append("',gsDm:'").append(
						sonGs.getGsDm()).append("'");

				StringBuffer s = buildJsonByParent(gsxx, sonGs.getGsDm());
				if (!s.toString().equals("[]")) {
					sb.append(",children:").append(s);
				} else {
					sb.append(",leaf:true");
				}
				sb.append("}");
			}
		}
		sb.append("]");
		return sb;
	}

	private List<Company> getTopCompany(List<?> oldcompanys){
		List<Company> newcompanys = new ArrayList<Company>();
		for(int i=0,nsize=oldcompanys.size();i<nsize;i++){
		    Company company = (Company)oldcompanys.get(i);
			boolean top = true;
			boolean son = false;
			for(int j=0,tsize=oldcompanys.size();j<tsize;j++){
				Company company2 = (Company)oldcompanys.get(j);
				if(company.getSjgsDm().equals(company2.getGsDm())){
					son = true;
					top = false;
				}
			}
		 if(top && !son)
			 newcompanys.add(company);
		}
		return newcompanys;
	}
	
	public Company getGsxxByGsDm(String dm,String gsDm)  throws Exception{
		 DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getCopyBean().getEntityClass());
		 detachedCriteria.add(Restrictions.sqlRestriction(" gsdm ='"+dm+"' and gsdm like'"+gsDm+"%' and status=1"));
			 List<?> list =   gridDao.getsCacheEntityList(detachedCriteria);
				if(ListUtil.isEmpty(list))
					return null;
				else
					return (Company)list.get(0);
	}
	
	/********************other*************************/
	 public void updateBill(SessionUser sessionuser, String data) throws Exception{
			 super.updateBill(sessionuser, data);
		}

	public Company getGsxxByName(String name) throws Exception {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getCopyBean().getEntityClass());
		detachedCriteria.add(Restrictions.sqlRestriction(" short_name ='"+name+"' and status=1"));
		List<?> list =   gridDao.getsCacheEntityList(detachedCriteria);
		if(ListUtil.isEmpty(list))
			return null;
		else
			return (Company)list.get(0);
	}

	public List<Company> getCompaniesList() throws Exception{
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getCopyBean().getEntityClass());
		detachedCriteria.add(Restrictions.sqlRestriction(" status=1 and node_number<>'78000'"));
		List<?> list =   gridDao.getsCacheEntityList(detachedCriteria);

		List<Company> companyList = new ArrayList<>();
		for(int i=0;i<list.size();i++){
			companyList.add((Company)list.get(i));
		}
		if(ListUtil.isEmpty(list))
			return null;
		else
			return companyList;
	}

}
