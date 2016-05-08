package en.common.controller;


import java.util.List;

import en.basis.dto.UserDTO;
import en.basisc.entity.LoginUser;
import en.basisc.entity.User;
import en.common.util.helper.Base64;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;

import en.common.dto.DTO;
import en.common.frame.controller.BaseController;
import en.common.frame.engine.IConstants;
import en.common.frame.shiro.SessionUser;
import en.common.service.IGridSerivce;
import en.common.util.helper.ListUtil;
import en.common.util.helper.RoleListsEntity;
import en.common.util.helper.StringUtil;

public class GridController extends BaseController{

	protected IGridSerivce gridSerivce;
	
	public void updateBillByDTO(DTO headDTO)  {
		try{
			long start = System.currentTimeMillis();
			SessionUser sessionuser = getSessionUser();
			gridSerivce.checkValidBeforeUpdate(sessionuser, headDTO);
			
			 gridSerivce.updateBillByDTO(sessionuser, headDTO);
			
			this.setResultValue("{success:true,Msg:'保存成功'}");
				 logger.info("更新单据完成@@@" + this.getClass().getSimpleName()
							+ "@@@update@@@" + (float) (System.currentTimeMillis() - start) / 1000);
		}catch(Exception e){
			e.printStackTrace();
			this.setResultValue("{failure:true,Msg:\""+getErrMsg(e)+"\"}");
		}
	} 
//	/*
//	public void getJsonDTODataById(SessionUser sessionuser,String selectedId){
//		if (StringUtil.isEmpty(selectedId))
//			this.setErrorResultValue("无法获取用户ID");
//		else{
//			try{
//			String resultMsg = gridSerivce.getJsonDTOById(sessionuser,selectedId);
//			this.setResultValue(resultMsg);
//			}catch (Exception e) {
//				e.printStackTrace();
//				String err = e.getCause() == null ? e.getMessage() : e.getCause().getMessage();
//				this.setErrorResultValue(err);
//			}
//		}
//	}
//
//	protected void remove(SessionUser sessionuser,String selectedId){
//		try{
//		   gridSerivce.remove(sessionuser, selectedId);
//		   this.setResultValue("OK");
//		}catch (Exception e) {
//			e.printStackTrace();
//			String err = e.getCause() == null ? e.getMessage() : e.getCause().getMessage();
//			this.setErrorResultValue(err);
//		}
//	}*/
	
	protected void setPageCss(Subject user,Model model){
		
		model.addAttribute("pageCss",user.getSession().getAttribute(IConstants.SYSTEM+"_PAGECSS"));
		
	}
	
	protected String getComRole(Subject user,String menuCode){
		String str = "";
		List<?> menus = (List<?>) user.getSession().getAttribute(IConstants.SESSION_USER_MENU);
		if(!ListUtil.isEmpty(menus)){
			for(int i=0,nsize = menus.size();i<nsize;i++){
				RoleListsEntity item = (RoleListsEntity)menus.get(i);
				if(menuCode.equals(item.getBz())){
					  str = str+" isRole : 'Y' ";
					  if("Y".equals(item.getOperateAdd()))
						str = str+",tn_isToolbarAdd : true ";  
					  if("Y".equals(item.getOperateDelete()))
							str = str+",tn_isToolbarDel : true ";  
					  if("Y".equals(item.getOperateEdit()))
							str = str+",tn_isToolbarSave : true ";  
					  if("Y".equals(item.getOperatePrint()))
							str = str+",tn_isToolbarPrint : true ";  
					  if("Y".equals(item.getOperateSearch()))
							str = str+",tn_isToolbarSearch : true "; 
					}
				}
		}
		if(!StringUtil.isEmpty(str))
			str = "{"+str+"}"; 
		return str ;
	}

	//将UserDTO对象转换为LoginUser对象
	protected LoginUser UserDTO2LoginUser(UserDTO userDTO,User user){

		LoginUser loginUser = new LoginUser();
		loginUser.setStatus(true);
		loginUser.setXgrDm(new Long(1));
		loginUser.setLoginAccount(userDTO.getLoginName());
		loginUser.setLoginPassword(new Base64().encode64(userDTO.getLoginpwd()));
		loginUser.setName(userDTO.getName());
		loginUser.setUser(user);
		loginUser.setIsIndependentRole(false);


		return loginUser;
	}

}
